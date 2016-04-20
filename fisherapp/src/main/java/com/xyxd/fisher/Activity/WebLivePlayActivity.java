package com.xyxd.fisher.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.LivePlayer;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Live;

public class WebLivePlayActivity extends AppCompatActivity {
    Live live;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_live_player);

        WebView webPlayer = (WebView) findViewById(R.id.webPlayer);

        String str = getIntent().getExtras().get("live").toString();
        Gson gson = new Gson();
        live = gson.fromJson(str, Live.class);

        String webContent = LivePlayer.H5Content();
        webPlayer.loadDataWithBaseURL(Client.SERVERBASEURL,webContent, "text/html",  "utf-8", Client.SERVERBASEURL);
    }
}
