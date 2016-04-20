package com.xyxd.fisher.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.xyxd.fisher.Activity.MainActivity;
import com.xyxd.fisher.BuildConfig;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;


public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_splash);

        String versionName = BuildConfig.VERSION_NAME;
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setImageResource(R.drawable.loading);

        sp = getSharedPreferences(FILE, MODE_PRIVATE);
        String lastVersion = sp.getString("lastVersion", "");
        if(lastVersion.equals(versionName)) {
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGHT);
        }
        else
        {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("lastVersion", versionName);
            edit.apply();
            new android.os.Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashActivity.this,IntroActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }

            }, SPLASH_DISPLAY_LENGHT);

        }
    } 

}
