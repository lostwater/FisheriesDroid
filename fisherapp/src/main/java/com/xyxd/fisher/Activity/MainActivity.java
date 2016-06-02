package com.xyxd.fisher.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.roughike.bottombar.BottomBar;
import com.squareup.picasso.Picasso;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.ImageUploader;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.*;
import com.xyxd.fisher.adapter.BottomPagerAdapter;


import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
    implements OnListFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener

{
    CircleImageView avatarView;
    TextView usernameView;

    protected void onChooserWithGalleryClicked() {
        EasyImage.openChooserWithGallery(this, "选择源", 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //Handle the image
                onPhotoReturned(imageFile);
            }
        });
    }


    void retroUpload(File file)
    {
        RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Call call = Client.userClient().changeAvatar(fbody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(final Response<ResponseBody> response) {
                if (response.isSuccess())
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "上传图成功 ", Toast.LENGTH_SHORT).show();
                        }
                    });
                else {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "上传图失败 " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "上传图失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void onPhotoReturned(File photoFile) {
        Picasso.with(this)
                .load(photoFile)
                .fit()
                .centerCrop()
                .into(avatarView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = Client.toUri(Client.user.getAvatar());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        File imageFile = imageLoader.getDiskCache().get(path);
        if (imageFile.exists()) {
            imageFile.delete();
        }
        MemoryCacheUtils.removeFromCache(path, imageLoader.getMemoryCache());
        DiskCacheUtils.removeFromCache(path, imageLoader.getDiskCache());
        imageLoader.displayImage(path, avatarView, options);

        ImageUploader.uploadFile(photoFile, Client.APIBASEURL+"api/Account/ChangeAvatar");
    }



    @Override
    protected void onDestroy() {
        // Clear any configuration that was done!
        EasyImage.clearConfiguration(this);
        super.onDestroy();
    }


    public void onListFragmentInteraction(Object  item)
    {
        if(item instanceof Live)
        {
            Intent intent = new Intent(MainActivity.this, WebLivePlayActivity.class);
            Gson gson = new Gson();
            String live = gson.toJson((Live)item);
            intent.putExtra("live",live);
            startActivity(intent);
            //startLeCloudActionLive((Live)item);
        }
        if(item instanceof Information)
        {
            Intent intent = new Intent(MainActivity.this, InformationActivity.class);
            Gson gson = new Gson();
            String info = gson.toJson((Information)item);
            intent.putExtra("info",info);
            startActivity(intent);
        }
        if(item instanceof Celebrity)
        {
            Intent intent = new Intent(MainActivity.this, CelebrityActivity.class);
            Gson gson = new Gson();
            String celebrity = gson.toJson((Celebrity)item);
            intent.putExtra("celebrity",celebrity);
            startActivity(intent);
        }
        if(item instanceof Event)
        {
            Intent intent = new Intent(MainActivity.this, EventActivity.class);
            Gson gson = new Gson();
            String event = gson.toJson((Event)item);
            intent.putExtra("event",event);
            startActivity(intent);
        }
    }

    private void startLeCloudActionLive(Live live) {
        Intent intent = getStartActivity();
        //intent.putExtra(PlayActivity.DATA, LetvParamsUtils.setActionLiveParams(live.getCloudLiveId(), false));
        startActivity(intent);
    }
    private Intent getStartActivity() {
        return new Intent(MainActivity.this, WebLivePlayActivity.class);
    }
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private BottomPagerAdapter mPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EasyImage.configuration(this)
                .setImagesFolderName("tempPics")
                .saveInAppExternalFilesDir()
                .setCopyExistingPicturesToPublicLocation(true);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPagerAdapter = new BottomPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.mActivity = MainActivity.this;
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(mPagerAdapter.getTabView(i));
            }

            tabLayout.getTabAt(0).getCustomView().setSelected(true);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    void openDrawer()
    {
        //    app:headerLayout="@layout/nav_header_main"
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nav = (NavigationView)findViewById(R.id.nav_view);
        View headerView = nav.getHeaderView(0);
        //View headerView = nav.inflateHeaderView(R.layout.nav_header_main);

        Menu menu  = nav.getMenu();
        MenuItem phoneItem = menu.findItem(R.id.phone);
        phoneItem.setTitle("电话 " + Client.user.getPhoneNumber());

        avatarView = (CircleImageView)(headerView.findViewById(R.id.avatarView));
        avatarView.setClickable(true);
        avatarView.setOnClickListener(null);
        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooserWithGalleryClicked();
            }
        });
        drawer.openDrawer(GravityCompat.START);
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = Client.toUri(Client.user.getAvatar());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageLoader.displayImage(path, avatarView, options);
        usernameView = (TextView)(headerView.findViewById(R.id.usernameView));
        usernameView.setText(Client.user.getUserName());
        usernameView.setClickable(true);
        usernameView.setOnClickListener(null);
        usernameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTitleDialog();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_user)
        {
            if(Client.accessToken != null) {
                openDrawer();
            }
            else
            {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            return false;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            Client.accessToken = null;
            String FILE = "saveUserNamePwd";
            SharedPreferences sp = getSharedPreferences(FILE, MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("phone", null);
            edit.putString("password", null);
            edit.putString("isMemory", "no");
            edit.apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.myFav){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(MainActivity.this, MyFavsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.myorders) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(MainActivity.this, MyOrdersActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            Client.accessToken = null;
            Toast.makeText(getApplicationContext(),"已登出" , Toast.LENGTH_SHORT).show();
            String FILE = "saveUserNamePwd";
            SharedPreferences sp = getSharedPreferences(FILE, MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("phone", null);
            edit.putString("password", null);
            edit.putString("isMemory", "no");
            edit.apply();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //String newUserName
    private void inputTitleDialog() {

        final EditText inputServer = new EditText(this);
        inputServer.setFocusable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新用户名").setView(inputServer).setNegativeButton(
                "取消", null);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString();
                        try {
                            Client.userClient().changeUserName(inputName).execute();
                        } catch (IOException ex) {}
                        Client.user.setUserName(inputName);
                        usernameView.setText(inputName);
                    }
                });
        builder.show();
    }








}
