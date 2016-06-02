package com.xyxd.fisher.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.xyxd.fisher.R;

public class WebVodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_vod);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String vuid = getIntent().getExtras().get("vuid").toString();
        WebView webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);

        webView.clearCache(true);
        webView.clearHistory();
        String url = "http://yuntv.letv.com/bcloud.html?uu=nal4hqaahb&pu=1ade94a321&auto_play=1&gpcflag=1&vu="+vuid;
        webView.loadUrl(url);

    }

    @Override
    protected void onDestroy() {
        WebView webView = (WebView)findViewById(R.id.webView);
        webView.clearCache(true);
        webView.clearFormData();
        webView.clearHistory();
        webView.destroy();
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        WebView webView = (WebView)findViewById(R.id.webView);
        webView.destroy();
        super.onStop();
        //this.onDestroy();
    }


}
