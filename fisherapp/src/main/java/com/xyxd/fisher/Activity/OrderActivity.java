package com.xyxd.fisher.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pingplusplus.android.PaymentActivity;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,View.OnClickListener
{

    private static final int REQUEST_CODE_PAYMENT = 1;
    ProgressDialog progress;

    TextView statu;
    TextView hint;
    TextView orderId;
    TextView time;
    Button goPay;
    TextView price;
    TextView shopName;
    TextView eventName;
    TextView eventTime;
    Button eventDetail;
    TextView validCode;
    SwipeRefreshLayout swipeLayout;

    String chargeString;
    Order order;

    public void onClick(View view) {
        if (view.getId() == R.id.gopay)
        {
            goPay();
        }
        if (view.getId() == R.id.eventDetail)
        {
            Intent intent = new Intent(OrderActivity.this, EventActivity.class);
            Gson gson = new Gson();
            String event = gson.toJson((Event)order.getEvent());
            intent.putExtra("event",event);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        String str = getIntent().getExtras().get("order").toString();
        Gson gson = new Gson();
        order = gson.fromJson(str,Order.class);

        ScrollView scroll = (ScrollView)findViewById(R.id.scrollContent);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        statu = (TextView)findViewById(R.id.statu);
        hint = (TextView)findViewById(R.id.hint);
        orderId = (TextView)findViewById(R.id.orderId);
        time = (TextView)findViewById(R.id.time);
        price = (TextView)findViewById(R.id.price);
        shopName = (TextView)findViewById(R.id.shopName);
        eventName = (TextView)findViewById(R.id.eventName);
        eventTime = (TextView)findViewById(R.id.eventTime);
        validCode = (TextView)findViewById(R.id.validCode);

        goPay  = (Button)findViewById(R.id.gopay);
        eventDetail  = (Button)findViewById(R.id.eventDetail);


        progress = new ProgressDialog(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        eventDetail.setOnClickListener(this);
        goPay.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventDetail.setOnClickListener(null);
        goPay.setOnClickListener(null);
    }

    @Override
    public void onRefresh() {
        loadData();
    }


    void loadData()
    {
        progress.setTitle("Loading");
        progress.show();
        swipeLayout.setRefreshing(true);
        Client.userClient().getOrder(order.getId()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Response<Order> response) {
                Order result = response.body();
                if (result != null) {
                    display();
                    order = result;
                }
                swipeLayout.setRefreshing(false);
                progress.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                swipeLayout.setRefreshing(false);
                showMsg("网络错误", "", "");
                progress.dismiss();
            }
        });

    }

    void goPay()
    {
        progress.setTitle("Loading");
        progress.show();
        eventDetail.setOnClickListener(null);
        goPay.setOnClickListener(null);
        Client.userClient().getPaymentCharge(order.getId()).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Response<Object> response) {
                if (response.isSuccess()) {
                    Object result = response.body();
                    chargeString = new Gson().toJson(result);
                    progress.dismiss();
                    Intent intent = new Intent(OrderActivity.this, PaymentActivity.class);
                    intent.putExtra(PaymentActivity.EXTRA_CHARGE, chargeString);
                    startActivityForResult(intent, REQUEST_CODE_PAYMENT);
                    eventDetail.setOnClickListener(OrderActivity.this);
                    goPay.setOnClickListener(OrderActivity.this);

                }
                else
                {
                    progress.dismiss();
                    showMsg("服务器错误","创建支付失败","");
                    eventDetail.setOnClickListener(OrderActivity.this);
                    goPay.setOnClickListener(OrderActivity.this);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                progress.dismiss();
                showMsg("网络错误","创建支付失败","");
            }
        });

    }

    void display()
    {
        int rsi = order.getOrderStatuId();
        if(rsi == 1) {
            statu.setText("未支付");
            hint.setVisibility(View.VISIBLE);
            goPay.setVisibility(View.VISIBLE);
        }
        if(rsi == 2) {
            statu.setText("已支付");
            hint.setVisibility(View.GONE);
            goPay.setVisibility(View.GONE);
        }
        if(rsi == 3) {
            statu.setText("已使用");
            hint.setVisibility(View.GONE);
            goPay.setVisibility(View.GONE);
        }
        if(rsi == 4) {
            statu.setText("已取消");
            hint.setVisibility(View.GONE);
            goPay.setVisibility(View.GONE);
        }
        orderId.setText("订单号: " + order.getId().toString());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        time.setText("下单时间: " + df.format(order.getOrderTime()));

        price.setText(order.getOrderPrice().toString() + "元");
        shopName.setText(order.getEvent().getShop().getName());
        eventName.setText(order.getEvent().getName());
        eventTime.setText("活动时间: " + df.format(order.getEvent().getEventFrom()));
        validCode.setVisibility(View.GONE);
        if(order.getCode()!= null) {
            validCode.setVisibility(View.VISIBLE);
            validCode.setText("验证码: " + order.getCode().toString());
        }
    }



    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //支付页面返回处理
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                showMsg(result, errorMsg, extraMsg);
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        if(!title.equals("cancel")) {
            String str = title;
            if (null != msg1 && msg1.length() != 0) {
                str += "\n" + msg1;
            }
            if (null != msg2 && msg2.length() != 0) {
                str += "\n" + msg2;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
            builder.setMessage(str);
            builder.setTitle("提示");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }
    }
}
