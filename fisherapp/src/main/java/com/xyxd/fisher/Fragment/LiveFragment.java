package com.xyxd.fisher.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Celebrity;
import com.xyxd.fisher.model.Live;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LiveFragment extends MainTabRefreshFragment {

    @Override
    protected void setDataList() {
        setLiveList();
    }

    void setLiveList()
    {
        List dataList = new ArrayList();
        IClient instance = Client.instance();
        Call<List<Live>> call = instance.getLive(page, pageSize);
        call.enqueue(new Callback<List<Live>>() {
            @Override
            public void onResponse(Response<List<Live>> response) {
                if(response.body()!=null)
                {
                    if(response.body().size()>0)
                    {;
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


    public LiveFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LiveFragment newInstance(int columnCount) {
        LiveFragment fragment = new LiveFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_list, container, false);
        mRecyclerViewAdapter = new MyLiveRecyclerViewAdapter(mDatalist,mListener);
        mAdCat = 3 ;
        return view;
    }





}
