package com.xyxd.fisher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xyxd.fisher.Activity.CelebrityActivity;
import com.xyxd.fisher.Activity.EventActivity;
import com.xyxd.fisher.Activity.InformationActivity;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Ad;
import com.xyxd.fisher.model.Celebrity;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Information;
import com.xyxd.fisher.view.HomeBanner;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lostw on 2016/4/7.
 */
public class MainTabRefreshFragment extends BaseRefreshRecylerFragment {


    List<Ad> mAdList = new ArrayList<>();
    List<View> mAdImageViewlist =  new ArrayList<>();
    BGABanner banner;
    HomeBanner homeBanner;
    public int mAdCat = 0;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainTabRefreshFragment() {
    }

    protected void setDataList()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this.getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageView.setImageResource(R.drawable.avatar);
        //imageLoader.displayImage(@D, imageView, options);
        mAdImageViewlist.add(imageView);
    }

    protected void setAdsList()
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
                        if(homeBanner!=null) {
                            homeBanner.setSource(mAdList)
                                    .startScroll();
                            homeBanner.setOnItemClickL(new HomeBanner.OnItemClickL() {
                                @Override
                                public void onItemClick(int position) {
                                    Ad _ad = mAdList.get(position);
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

    void setImageViews()
    {
        mAdImageViewlist.clear();
        for (Ad ad : mAdList) {

            ImageView imageView = (ImageView) getActivity().getLayoutInflater().inflate(R.layout.homebanner,null);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader imageLoader = ImageLoader.getInstance();
            String path = Client.toUri(ad.getAvatarUrl());
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.avatar)
                    .showImageForEmptyUri(R.drawable.avatar)
                    .showImageOnFail(R.drawable.avatar)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .build();
            imageLoader.displayImage(path, imageView, options);
            mAdImageViewlist.add(imageView);
        }
        banner.setViews(mAdImageViewlist);
        for (int i = 0; i < mAdImageViewlist.size(); i++) {
            ImageView imageView = (ImageView) mAdImageViewlist.get(i);
            String path = mAdList.get(i).getAvatarUrl();
            path =  Client.toUri(path);
            Glide.with(getActivity()).load(path).into(imageView);

        }
    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MainTabRefreshFragment newInstance(int columnCount) {
        MainTabRefreshFragment fragment = new MainTabRefreshFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout)view.findViewById(R.id.swipeToLoadLayout);
        //banner = (BGABanner)(view.findViewById(R.id.banner));
        homeBanner = (HomeBanner)(view.findViewById(R.id.homeBanner));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

//        banner.setTransitionEffect(BGABanner.TransitionEffect.Rotate);
   //     banner.setPageChangeDuration(1000);
        setAdsList();

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;

        clearList();
        setDataList();
       // banner.setViews(mAdImageViewlist);

    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        setDataList();

    }
}
