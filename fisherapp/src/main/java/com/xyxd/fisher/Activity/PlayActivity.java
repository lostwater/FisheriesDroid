package com.xyxd.fisher.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.xyxd.fisher.R;
import com.lecloud.test.usetime.UseTimeResult;
import com.letv.controller.PlayContext;
import com.xyxd.utils.LetvNormalVideoHelper;
import com.xyxd.utils.LetvParamsUtils;
import com.xyxd.utils.LetvNormalAndPanoHelper;
import com.letv.skin.v4.V4PlaySkin;
import com.letv.universal.iplay.ISplayer;

public class PlayActivity extends Activity {
    public final static String DATA = "data";

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////

    private V4PlaySkin skin;
    //如果不使用全景 请将LetvNormalAndPanoHelper 换成 LetvNormalVideoHelper
    private LetvNormalAndPanoHelper playHelper;
    private Bundle bundle;
    private TextView console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        loadDataFromIntent();// load data
        skin = (V4PlaySkin) findViewById(R.id.videobody);
        playHelper = new LetvNormalAndPanoHelper();
        playHelper.init(this.getApplicationContext(), bundle, skin);
        /*
        console = (TextView) findViewById(R.id.console);
        playHelper.renderCallback = new LetvNormalAndPanoHelper.PlayerRenderCallback() {
            @Override
            public void onRender() {
                console.setText(UseTimeResult.print());
            }
        };
        initBtn();
        */

    }

    private void loadDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getBundleExtra("data");
            if (bundle == null) {
                Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playHelper != null) {
            playHelper.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playHelper != null) {
            playHelper.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playHelper != null) {
            playHelper.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (playHelper != null) {
            playHelper.onConfigurationChanged(newConfig);
        }
    }


}