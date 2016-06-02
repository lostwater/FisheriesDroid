package com.xyxd.fisher.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.google.gson.Gson;
import com.xyxd.fisher.Activity.EventActivity;
import com.xyxd.fisher.Activity.InformationActivity;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.adapter.ImageAdapter;
import com.xyxd.fisher.adapter.MyHomeRecyclerViewAdapter;
import com.xyxd.fisher.adapter.OnItemClickListener;
import com.xyxd.fisher.model.Ad;
import com.xyxd.fisher.model.Celebrity;
import com.xyxd.fisher.view.HomeBanner;
import com.xyxd.fisher.view.header.GoogleRefreshHeaderView;
import com.xyxd.fisher.widget.BannerView;
import com.xyxd.fisher.widget.ClassicRefreshHeaderView;
import com.xyxd.fisher.widget.LoadMoreFooterView;
import com.xyxd.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xyxd.fisher.Fragment.BaseRefreshRecylerFragment.ARG_COLUMN_COUNT;
import static com.xyxd.fisher.R.id.bannerView;

/**
 * Created by lostw on 2016/6/1.
 */

public class BaseMainTabFragment extends Fragment implements  OnRefreshListener, OnLoadMoreListener {
    // TODO: Customize parameter argument names
    protected static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    protected int mColumnCount = 1;
    public int mAdCat = 0;
    protected List mDataList = new ArrayList();
    public int mPage = 0;
    public int mPageSize = 10;
    protected List mAdList = new ArrayList();
    protected IRecyclerView mRecyclerView;
    protected HomeBanner mBannerView;
    protected LoadMoreFooterView loadMoreFooterView;
    protected RecyclerView.Adapter mRecyclerViewAdapter;
    protected OnListFragmentInteractionListener mListener;
    public int fragmentLayoutId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(fragmentLayoutId, container, false);
        Context context = view.getContext();
        mRecyclerView = (IRecyclerView) view.findViewById(R.id.iRecyclerView);
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(context);
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(context, 80)));
        LoadMoreFooterView footerView = new LoadMoreFooterView(context);
        footerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(context, 80)));
        // we can set view
        mRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);
        mRecyclerView.setLoadMoreFooterView(footerView);

        if (mColumnCount <= 1) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        mBannerView = (HomeBanner) getLayoutInflater(savedInstanceState).inflate(R.layout.layout_home_banner, mRecyclerView.getHeaderContainer(), false);
        mRecyclerView.addHeaderView(mBannerView);
        loadMoreFooterView = (LoadMoreFooterView) mRecyclerView.getLoadMoreFooterView();
        mRecyclerView.setIAdapter(mRecyclerViewAdapter);
        mRecyclerView.setOnRefreshListener(this);
        mRecyclerView.setOnLoadMoreListener(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setRefreshing(true);
            }
        });
        */
    }


    @Override
    public void onRefresh() {
        loadBanner();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        refresh();
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        if (loadMoreFooterView.canLoadMore() && mRecyclerViewAdapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            loadMore();
        }
    }

    protected void clearList()
    {
        mDataList.clear();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    protected void appendList(List list)
    {
        mDataList.addAll(list);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    protected void setDataList()
    {
    }


    private void loadBanner() {
       setAdsList();
    }



    private void refresh() {
        mPage = 0;
        mDataList.clear();
        setDataList();
    }

    private void loadMore() {
        setDataList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void setAdsList()
    {
        IClient instance = Client.instance();
        Call<List<Ad>> callAds = instance.getHomeAds();
        if(mAdCat == 1)
            callAds = instance.getFameAds();
        if(mAdCat == 2)
            callAds = instance.getEventsAds();
        if(mAdCat == 3)
            callAds = instance.getLiveAds();
        callAds.enqueue(new Callback<List<Ad>>() {
            @Override
            public void onResponse(Response<List<Ad>> response) {
                if(response!=null) {
                    if (response.body().size() > 0) {
                        mAdList = response.body();
                        //setImageViews();
                        if(mBannerView!=null) {
                            mBannerView.setSource(mAdList)
                                    .startScroll();
                            mBannerView.setOnItemClickL(new HomeBanner.OnItemClickL() {
                                @Override
                                public void onItemClick(int position) {
                                    Ad _ad = (Ad)mAdList.get(position);
                                    if(_ad.getAdType() == 2)
                                    {
                                        Intent intent = new Intent(getActivity(), EventActivity.class);
                                        Gson gson = new Gson();
                                        String event = gson.toJson(_ad.getEvent());
                                        intent.putExtra("event",event);
                                        startActivity(intent);
                                    }
                                    if(_ad.getAdType() == 3)
                                    {
                                        Intent intent = new Intent(getActivity(), InformationActivity.class);
                                        Gson gson = new Gson();
                                        String info = gson.toJson(_ad.getInformation());
                                        intent.putExtra("info",info);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
}
