package com.xyxd.fisher.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.adapter.MyLiveRecyclerViewAdapter;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Live;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyLivesFragment extends LiveFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_lives, container, false);
        mRecyclerViewAdapter = new MyLiveRecyclerViewAdapter(mDatalist,mListener);
        layoutInflater = inflater;
        return view;
    }

    @Override
    protected void setTypeList() {
    }

    @Override
    protected void setAdsList() {
    }

    @Override
    protected void setLiveList() {
        Call<List<Live>> call = Client.userClient().getMyLives();
        call.enqueue(new Callback<List<Live>>() {
            @Override
            public void onResponse(Response<List<Live>> response) {
                    if(response.body()!=null)
                    {
                        if(response.body().size()>0)
                        {
                            page++;
                            appendList(response.body());
                        }
                    }
                    mSwipeToLoadLayout.setRefreshing(false);
                    mSwipeToLoadLayout.setLoadingMore(false);
                }
                @Override
                public void onFailure(Throwable t) {
                    mSwipeToLoadLayout.setRefreshing(false);
                    mSwipeToLoadLayout.setLoadingMore(false);

                }
            });
    }
}
