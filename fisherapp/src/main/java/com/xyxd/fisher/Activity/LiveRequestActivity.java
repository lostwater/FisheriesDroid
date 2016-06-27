package com.xyxd.fisher.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.letv.recorder.RecorderLetvActivity;
import com.letv.recorder.data.LetvStreamData;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.ServerError;
import com.xyxd.fisher.model.ServerErrorUtils;
import com.xyxd.fisher.model.UserLiveRequest;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveRequestActivity extends AppCompatActivity {

    @Bind(R.id.request_layout)
    LinearLayoutCompat requestLayout;

    @Bind(R.id.text_name)
    EditText etName;

    @Bind(R.id.text_id)
    EditText etId;

    @Bind(R.id.text_livename)
    EditText etLiveName;

    @Bind(R.id.text_state)
    TextView textState;

    @Bind(R.id.button_request)
    AppCompatButton buttonRequest;

    @Bind(R.id.button_start)
    AppCompatButton buttonStart;

    @Bind(R.id.button_test)
    AppCompatButton buttonTest;

    String mCloudLiveId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_request);
        buttonRequest = (AppCompatButton)findViewById(R.id.button_request);
        buttonStart = (AppCompatButton)findViewById(R.id.button_start);
        buttonTest = (AppCompatButton)findViewById(R.id.button_test);
        requestLayout = (LinearLayoutCompat)findViewById(R.id.request_layout);
        etName = (EditText)findViewById(R.id.text_name);
        etId = (EditText)findViewById(R.id.text_id);
        etLiveName = (EditText)findViewById(R.id.text_livename);
        textState =  (TextView)findViewById(R.id.text_state);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCloudLiveId==null)
                    mCloudLiveId = "A20160602000044a";
                startLivePush(mCloudLiveId);
            }
        });
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLivePush("A20160602000044a");
            }
        });
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
        initView();
    }



    void initView()
    {
        buttonStart.setVisibility(View.INVISIBLE);
        requestLayout.setVisibility(View.VISIBLE);
        textState.setText("您未开通直播");

        if(Client.user.getLive()!=null)
        {
            textState.setText("您已通过审核");
            requestLayout.setVisibility(View.GONE);
            mCloudLiveId = Client.user.getLive().getCloudLiveId();
            buttonStart.setVisibility(View.VISIBLE);
        }
        else
        {
            Call<UserLiveRequest> call = Client.userClient().getMyLiveRequest();
            call.enqueue(new Callback<UserLiveRequest>() {
                @Override
                public void onResponse(Response<UserLiveRequest> response) {
                    if(response.isSuccess())
                    {
                        UserLiveRequest request = response.body();
                        if(request.getState() ==  0)
                        {
                            textState.setText("您的申请正在审核中");
                            requestLayout.setVisibility(View.GONE);
                        }
                        else if (request.getState() ==  2)
                        {
                            textState.setText("您的申请被拒绝了，请重新申请");
                        }
                        else if (request.getState() ==  1)
                        {
                            Client.TryGetUserDetail();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    void request()
    {
        if(etName.getText() == null || etId.getText() == null || etLiveName.getText() == null)
        {
            Toast.makeText(getApplicationContext(), "请输入完整信息",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            String name =etName.getText().toString();
            String id = etId.getText().toString();
            String liveName = etLiveName.getText().toString();

            Call call = Client.userClient().requestUserLive(name,id,liveName);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Response response) {
                    if(response.isSuccess())
                    {
                        Toast.makeText(getApplicationContext(), "您的申请已提交",
                                Toast.LENGTH_SHORT).show();
                        initView();

                    }
                    ServerError error = ServerErrorUtils.parseError(response);
                    String errorMessage = error.message;
                    Toast.makeText(getApplicationContext(), "申请提交失败" + errorMessage,
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getApplicationContext(), "申请提交失败",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    void startLivePush(String activityId)
    {
        LetvStreamData letvStreamData = new LetvStreamData(this);
        Intent intent = null;
        // 乐视云直播界面
        intent = new Intent(this,RecorderLetvActivity.class);
        letvStreamData.setAppKey("2e44b05a1d3b751efc6a3a3eb1654e79");
        letvStreamData.setOrientation(false);
        letvStreamData.setUserID("823100");
        letvStreamData.setStreamId(activityId);
        intent.putExtra("letvAppKey", letvStreamData.getAppKey());
        intent.putExtra("letvStreamID", letvStreamData.getStreamId());
        intent.putExtra("letvUserId", letvStreamData.getUserID());
        intent.putExtra("isVertical", letvStreamData.isVertical());

        startActivity(intent);
    }
}
