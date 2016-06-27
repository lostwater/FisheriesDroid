package com.xyxd.fisher.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Celebrity;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.EventStatu;
import com.xyxd.fisher.model.Order;
import com.xyxd.fisher.model.Shop;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xyxd.fisher.Http.Client.userClient;

public class EventActivity extends AppCompatActivity {
    Shop mShop;
    Event event;
    Button register;
    ImageView avatarView;
    TextView name;
    TextView intro;
    TextView price;
    TextView discountprice;
    TextView contentView;
    Button address;
    Button mMarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String str = getIntent().getExtras().get("event").toString();
        Gson gson = new Gson();
        event = gson.fromJson(str, Event.class);
        mShop = event.getShop();
        avatarView = (ImageView)findViewById(R.id.avatarView);
        name = (TextView)findViewById(R.id.name);
        intro  = (TextView)findViewById(R.id.intro);
        price = (TextView)findViewById(R.id.price);
        discountprice = (TextView)findViewById(R.id.discountprice);
        contentView = (TextView)findViewById(R.id.content);
        address = (Button)findViewById(R.id.address);
        register = (Button)findViewById(R.id.register);
        mMarkButton = (Button)findViewById(R.id.button_mark);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContent();
        setRegisterButton();
        setMarkButton();
    }

    void setContent()
    {
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = Client.toUri(event.getAvatarUrl());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageLoader.displayImage(path, avatarView, options);

        name.setText(event.getShop().getName());
        intro.setText(event.getShop().getIntro());
        price.setText("参赛价：" + event.getPrice().toString() + "元");
        discountprice.setText("优惠价：" + event.getDiscountPrice().toString() + "元");
        address.setText(event.getShop().getAddress());

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toMap();
            }
        })
        ;

        if(event.getDiscountPrice().toString().endsWith(event.getPrice().toString()))
        {
            discountprice.setVisibility(View.GONE);
        }

        DateFormat df = new SimpleDateFormat("MM月dd日HH时mm分");
        String content = event.getName();
        content += "\n鱼       种：" + event.getFishType();
        if(event.getEventFrom()!=null)
            content += "\n开始时间：" + df.format(event.getEventFrom());
        if(event.getOxygenTime()!=null)
            content += "\n打氧时间：" + df.format(event.getOxygenTime());
        content += "\n回收价格：" + event.getBuyPrice().toString() + "元";
        content += "\n放  鱼  量：" + event.getFishQuantity().toString() + "斤";
        content += "\n钓       位：" + event.getPositions().toString() + "个";
        content += "\n玩法描述：" + event.getDescription();
        content += "\n\n";

        contentView.setText(content);
    }

    void setMarkButton()
    {
        if(Client.accessToken == null)
        {

            mMarkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EventActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else
        {
            mMarkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isMarked()) {
                        mMarkButton.setText("取消关注");
                        mMarkButton.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_book, 0, 0, 0);
                        unmarkShop();
                    }
                    else {
                        mMarkButton.setText("关注渔场");
                        mMarkButton.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_unbook, 0, 0, 0);
                        markShop();
                    }
                }
            });

        }
    }

    void setRegisterButton(){
        if(Client.accessToken == null)
        {
            register.setText("登陆");
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EventActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            userClient().getEventStatu(event.getId()).enqueue(new Callback<EventStatu>() {
                @Override
                public void onResponse(Response<EventStatu> response) {
                    EventStatu statu = response.body();
                    register.setEnabled(statu.getIsOrderable());
                    register.setText(statu.getMessage());
                }

                @Override
                public void onFailure(Throwable t) {
                    register.setVisibility(View.GONE);
                }

            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmOrder();
                    // TODO Auto-generated method stub
                }
            });
        }
    }

    void CreateOrder()
    {
        userClient().postCreateOrder(event.getId()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Response<Order> response) {
                Order order = response.body();
                if(order != null)
                {
                    Intent intent = new Intent(EventActivity.this, OrderActivity.class);
                    Gson gson = new Gson();
                    String str = gson.toJson(order);
                    intent.putExtra("order",str);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "创建失败",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "创建失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void confirmOrder() {
          AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
             builder.setMessage("确定购买？");
          builder.setTitle(event.getDiscountPrice().toString() + "元");
          builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
               @Override
              public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    CreateOrder();
                   }
              });
          builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                   }
              });
          builder.create().show();
         }

    void toMap()
    {
        if(event.getShop().getLatitude()==0)
            event.getShop().setLatitude(39.916973);
        if(event.getShop().getLongitude() == 0)
            event.getShop().setLongitude(116.410046);
        String la = event.getShop().getLatitude().toString();
        String lg = event.getShop().getLongitude().toString();
        String name = event.getShop().getName();
        if(isAvilible(this,"com.autonavi.minimap")){//传入指定应用包名
            Intent intent = new Intent("android.intent.action.VIEW",
                    android.net.Uri.parse("androidamap://viewMap?sourceApplication=appname&poiname="+name+"&lat="+la+"&lon="+lg+"&dev=0&style=0"));
            intent.setPackage("com.autonavi.minimap");
            startActivity(intent);
        }
        else if(isAvilible(this,"com.baidu.BaiduMap")){//传入指定应用包名
            Intent intent = null;
            try {
                intent = Intent.getIntent("intent://map/marker?location="+la+","+lg+"&title="+name+"&content="+name+"&coord_type=gcj02&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                startActivity(intent); //启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        }
        else{//未安装
            //market为路径，id为包名
            //显示手机上所有的market商店
            //Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //startActivity(intent);
            Intent intent = new Intent(EventActivity.this, WebMapActivity.class);
            Gson gson = new Gson();
            String str = gson.toJson((Shop)event.getShop());
            intent.putExtra("shop",str);
            startActivity(intent);
        }
    }



    /**
     * 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    private boolean isAvilible(Context context, String packageName){
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName.trim());
    }

    boolean isMarked()
    {
        for (Shop shop:Client.user.getFollowedShops()
             ) {
            if(shop.getId() == mShop.getId())
            {
                return true;
            }
        }
        return false;
    }

    Shop getShop()
    {
        for (Shop shop:Client.user.getFollowedShops()
                ) {
            if(shop.getId() == mShop.getId())
            {
                return shop;
            }
        }
        return null;
    }

    void markShop()
    {
        Call<Void> call = Client.userClient().followShop(mShop.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {
                if(response.isSuccess())
                {
                    mMarkButton.setText("取消关注");
                    mMarkButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_book,0 ,0,0);
                    Client.user.getFollowedShops().add(mShop);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "操作失败",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "操作失败",
                        Toast.LENGTH_SHORT).show();
            }
         });

    }

    void unmarkShop()
    {
        Call<Void> call = Client.userClient().unfollowShop(mShop.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {
                if(response.isSuccess())
                {
                    mMarkButton.setText("关注渔场");
                    mMarkButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unbook,0 ,0,0);
                    Shop shop = getShop();
                    if(shop != null)
                    {
                        Client.user.getFollowedShops().remove(shop);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "操作失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "操作失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
