package com.xyxd.fisher.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xyxd.fisher.BuildConfig;
import com.xyxd.fisher.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class IntroActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        BGABanner banner = (BGABanner)findViewById(R.id.intro);
        banner.setTransitionEffect(BGABanner.TransitionEffect.Accordion);
        // banner.setPageTransformer(new RotatePageTransformer());
        // 设置page切换时长
        banner.setPageChangeDuration(1000);

        String versionName = BuildConfig.VERSION_NAME;
        List<View> views = new ArrayList<>();

            views.add(getPageView(R.drawable.intro1));
            views.add(getPageView(R.drawable.intro2));
            views.add(getPageView(R.drawable.intro3));
            views.add(getPageView(R.drawable.intro4));
            banner.setViews(views);




        Button button = (Button)findViewById(R.id.enterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });



    }

    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }


}
