package com.xyxd.fisher.Activity;

import android.content.Intent;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;
import com.google.gson.Gson;
import com.xyxd.fisher.Fragment.ChatFragment;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.LivePlayer;
import com.xyxd.fisher.R;
import com.xyxd.fisher.lean.AVImClientManager;
import com.xyxd.fisher.lean.Constants;
import com.xyxd.fisher.leanEvent.LeftChatItemClickEvent;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Live;
import com.xyxd.fisher.model.Shop;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebLivePlayActivity extends AVBaseActivity {
    Live mLive;
    String conversationId;
    protected ChatFragment chatFragment;
    private AVIMConversation squareConversation;
    @Bind(R.id.button_mark)
    Button mMarkButton;
    @Bind(R.id.button_login)
    AppCompatButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_live_player);

        WebView webView = (WebView) findViewById(R.id.webPlayer);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.clearCache(true);
        webView.clearHistory();
        String str = getIntent().getExtras().get("live").toString();
        Gson gson = new Gson();
        mLive = gson.fromJson(str, Live.class);

        conversationId = mLive.getChatId();
        chatFragment = (ChatFragment)getFragmentManager().findFragmentById(R.id.fragment_chat);
        if(Client.user != null)
        {
            loginButton.setVisibility(View.GONE);
            openLeanClient();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebLivePlayActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        setMarkButton();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //当返回值是true的时候由webView来打开，为false的时候则由第三方或者系统默认的浏览器打开
                //return false;
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        int h = webView.getMeasuredHeight();
        String webContent = LivePlayer.H5Content(mLive.getCloudLiveId());

        String url = "http://live.lecloud.com/live/playerPage/getView?activityId="+mLive.getCloudLiveId();
        //webView.loadUrl(url);
        webView.loadDataWithBaseURL("http://yuntv.letv.com/",webContent, "text/html",  "utf-8",null);
        //webView.setMinimumHeight(webPlayer.getWidth() *9 /16);
        //webView.loadDataWithBaseURL(Client.SERVERBASEURL,webContent, "text/html",  "utf-8", Client.SERVERBASEURL);


    }


    void openLeanClient()
    {
        String userName = Client.user.getUserName();
        AVImClientManager.getInstance().open(userName, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (filterException(e)) {
                    getSquare(conversationId);
                    queryInSquare(conversationId);
                    //chatFragment
                }
            }
        });
    }

    /**
     * 根据 conversationId 查取本地缓存中的 conversation，如若没有缓存，则返回一个新建的 conversaiton
     */
    private void getSquare(String conversationId) {
        if (TextUtils.isEmpty(conversationId)) {
            throw new IllegalArgumentException("conversationId can not be null");
        }

        AVIMClient client = AVImClientManager.getInstance().getClient();
        if (null != client) {
            squareConversation = client.getConversation(conversationId);
        } else {
            finish();
            showToast("Please call AVIMClient.open first!");
        }
    }

    /**
     * 加入 conversation
     */
    private void joinSquare() {
        squareConversation.join(new AVIMConversationCallback() {
            @Override
            public void done(AVIMException e) {
                if (filterException(e)) {
                    chatFragment.setConversation(squareConversation);
                }
            }
        });
    }

    /**
     * 先查询自己是否已经在该 conversation，如果存在则直接给 chatFragment 赋值，否则先加入，再赋值
     */
    private void queryInSquare(String conversationId) {
        final AVIMClient client = AVImClientManager.getInstance().getClient();
        AVIMConversationQuery conversationQuery = client.getQuery();
        conversationQuery.whereEqualTo("objectId", conversationId);
        conversationQuery.containsMembers(Arrays.asList(AVImClientManager.getInstance().getClientId()));
        conversationQuery.findInBackground(new AVIMConversationQueryCallback() {
            @Override
            public void done(List<AVIMConversation> list, AVIMException e) {
                if (filterException(e)) {
                    if (null != list && list.size() > 0) {
                        chatFragment.setConversation(list.get(0));
                    } else {
                        joinSquare();
                    }
                }
            }
        });
    }

    /**
     * 处理聊天 item 点击事件，点击后跳转到相应1对1的对话
     */
    public void onEvent(LeftChatItemClickEvent event) {

    }

    void markButtonClicked()
    {
        if(Client.user == null)
        {
            Intent intent = new Intent(WebLivePlayActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else
        {
            if(isMarked())
            {
                unfollow();
            }
            else
            {
                follow();
            }
        }
    }

    void setMarkButton()
    {
        if(Client.user != null)
        {
            if(isMarked())
            {
                mMarkButton.setText("取消关注");
                mMarkButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_book,0 ,0,0);
            }
            else
            {
                mMarkButton.setText("关注直播");
                mMarkButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unbook,0 ,0,0);
            }
        }
        mMarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markButtonClicked();
            }
        });
    }

    boolean isMarked()
    {
        for (Live live:Client.user.getFollowedLives()
                ) {
            if(live.getId() == mLive.getId())
            {
                return true;
            }
        }
        return false;
    }

    Live getLive()
    {
        for (Live live:Client.user.getFollowedLives()
                ) {
            if(live.getId() == mLive.getId())
            {
                return live;
            }
        }
        return null;
    }

    void follow()
    {
        Call<Void> call = Client.userClient().followLive(mLive.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {
                if(response.isSuccess())
                {
                    mMarkButton.setText("取消关注");
                    mMarkButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_book,0 ,0,0);
                    Client.user.getFollowedLives().add(mLive);
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

    void unfollow()
    {
        Call<Void> call = Client.userClient().unfollowLive(mLive.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {
                if(response.isSuccess())
                {
                    mMarkButton.setText("关注直播");
                    mMarkButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unbook,0 ,0,0);
                    Live live = getLive();
                    if(live != null)
                    {
                        Client.user.getFollowedLives().remove(mLive);
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
