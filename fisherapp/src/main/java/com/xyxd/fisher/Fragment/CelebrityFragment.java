package com.xyxd.fisher.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.adapter.MyCelebrityRecyclerViewAdapter;
import com.xyxd.fisher.model.Celebrity;

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
public class CelebrityFragment extends MainTabRefreshFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CelebrityFragment() {
    }

    @Override
    protected void setDataList() {
         setCeleList();
    }

    void setCeleList()
    {
        List dataList = new ArrayList();
        IClient instance = Client.instance();
        Call<List<Celebrity>> call = instance.getCelebrities(page,pageSize);
        call.enqueue(new Callback<List<Celebrity>>() {
            @Override
            public void onResponse(Response<List<Celebrity>> response) {
                if(response!=null)
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


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CelebrityFragment newInstance(int columnCount) {
        CelebrityFragment fragment = new CelebrityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_celebrity_list, container, false);
        mRecyclerViewAdapter = new MyCelebrityRecyclerViewAdapter(mDatalist,mListener);
        mAdCat = 1;
        return view;
    }



}
