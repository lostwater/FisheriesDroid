package com.xyxd.fisher.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Information;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InformationActivity extends AppCompatActivity {
    Information information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        String strInfo = getIntent().getExtras().get("info").toString();
        Gson gson = new Gson();
         information = gson.fromJson(strInfo,Information.class);



    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(name, context, attrs);

        return view;
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebView webView = (WebView)findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);

        webView.clearCache(true);
        webView.clearHistory();
        //settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webView.loadUrl(Client.SERVERBASEURL+"Information/Content/"+information.getId().toString());
       // webView.loadDataWithBaseURL(Client.SERVERBASEURL,content, "text/html",  "utf-8", null);

        if(information.getCelebrity()!= null)
        {
            TextView author = (TextView)findViewById(R.id.author);
            author.setText("Written by: " + information.getCelebrity().getName());
        }
        else
        {
            TextView author = (TextView)findViewById(R.id.author);
            author.setText("");
        }
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(information.getTitle());
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        TextView time = (TextView)findViewById(R.id.time);
        if(information.getPublishedTime()!= null)
        {
            time.setText(df.format(information.getPublishedTime()));
        }
        else
        {
            time.setText("");
        }
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
