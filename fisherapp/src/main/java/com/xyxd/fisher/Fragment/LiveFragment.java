package com.xyxd.fisher.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.adapter.MyLiveRecyclerViewAdapter;
import com.xyxd.fisher.model.InformationType;
import com.xyxd.fisher.model.Live;
import com.xyxd.fisher.model.LiveType;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xyxd.fisher.Http.Client.instance;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LiveFragment extends MainTabRefreshFragment  implements RadioGroup.OnCheckedChangeListener {

    LayoutInflater layoutInflater;
    List<LiveType> typeList = new ArrayList<>();
    int selectTypeId = 1;
    SegmentedGroup typeSegment;
    List segButtons = new ArrayList();

    @Override
    protected void setDataList() {
        setLiveList();
    }

    int mLiveType = 1;

    protected void setLiveList()
    {
        List dataList = new ArrayList();
        IClient instance = Client.instance();
        Call<List<Live>> call = instance.getLive(mLiveType,page, pageSize);
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
        layoutInflater = inflater;
        setTypeList();
        return view;
    }


    protected void setTypeList()
    {
        IClient instance = instance();
        Call<List<LiveType>> callTypes = instance.getLiveTypes();
        callTypes.enqueue(new Callback<List<LiveType>>() {
            @Override
            public void onResponse(Response<List<LiveType>> response) {
                typeList = response.body();
                typeSegment = (SegmentedGroup)getView().findViewById(R.id.typeSeg);
                typeSegment.setOnCheckedChangeListener(LiveFragment.this);
                segButtons.clear();
                for (LiveType liveType : typeList) {
                    RadioButton segButton =( RadioButton) layoutInflater.inflate(R.layout.sample_seg_button,null);
                    segButton.setId(liveType.getId());
                    segButton.setText(liveType.getName());
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mLiveType = checkedId;
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        });
    }

}
