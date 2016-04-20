package com.xyxd.fisher.Fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Information;
import com.xyxd.fisher.model.InformationType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HomeFragment extends MainTabRefreshFragment
        implements RadioGroup.OnCheckedChangeListener
{
    LayoutInflater layoutInflater;
    List<InformationType> typeList = new ArrayList<>();
    int selectTypeId = 1;
    List segButtons = new ArrayList();
    SegmentedGroup typeSegment;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setDataList() {
        setInfoList();
    }

    void setInfoList()
    {
        IClient instance = Client.instance();
        Call<List<Information>> call = instance.getInformation(selectTypeId, page, pageSize);
        call.enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Response<List<Information>> response) {
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

    void setTypeList()
    {
        IClient instance = Client.instance();
        Call<List<InformationType>> callTypes = instance.getInformationTypes();
        callTypes.enqueue(new Callback<List<InformationType>>() {
            @Override
            public void onResponse(Response<List<InformationType>> response) {
                typeList = response.body();
                typeSegment = (SegmentedGroup) getActivity().findViewById(R.id.typeSeg);
                typeSegment.setOnCheckedChangeListener(HomeFragment.this);
                segButtons.clear();
                for (InformationType informationType : typeList) {
                    RadioButton segButton =( RadioButton) layoutInflater.inflate(R.layout.sample_seg_button,null);
                    segButton.setId(informationType.getId());
                    segButton.setText(informationType.getName());
                    segButtons.add(segButton);
                }
                typeSegment.removeAllViews();
                for(Object segbutton : segButtons)
                {
                    typeSegment.addView((View)segbutton);
                }
                typeSegment.updateBackground();
                RadioButton b1 = (RadioButton)segButtons.get(0);
                b1.setChecked(true);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        mRecyclerViewAdapter = new MyHomeRecyclerViewAdapter(mDatalist,mListener);
        mAdCat = 0;

        setTypeList();


        View header = (View)inflater.inflate(R.layout.homeheader,null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
// set LayoutManager for your RecyclerView
        //header.attachTo(recyclerView);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        selectTypeId = checkedId;
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        });
    }
}
