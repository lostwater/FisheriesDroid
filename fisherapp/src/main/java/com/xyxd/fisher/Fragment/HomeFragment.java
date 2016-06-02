package com.xyxd.fisher.Fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Http.IClient;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.adapter.MyHomeRecyclerViewAdapter;
import com.xyxd.fisher.model.Information;
import com.xyxd.fisher.model.InformationType;
import com.xyxd.fisher.widget.LoadMoreFooterView;

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
public class HomeFragment extends BaseMainTabFragment
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
        IClient instance = instance();
        Call<List<Information>> call = instance.getInformation(selectTypeId, mPage, mPageSize);
        call.enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Response<List<Information>> response) {
                if(response!=null)
                {
                    if(response.body().size()>0)
                    {
                        mPage++;
                        appendList(response.body());
                    }
                }
                mRecyclerView.setRefreshing(false);
                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                //mRecyclerView.loa
            }

            @Override
            public void onFailure(Throwable t) {
                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                mRecyclerView.setRefreshing(false);
                //mRecyclerView.setLoadingMore(false);
            }
        });
    }

    void setTypeList()
    {
        IClient instance = instance();
        Call<List<InformationType>> callTypes = instance.getInformationTypes();
        callTypes.enqueue(new Callback<List<InformationType>>() {
            @Override
            public void onResponse(Response<List<InformationType>> response) {
                typeList = response.body();
                typeSegment = (SegmentedGroup)getView().findViewById(R.id.typeSeg);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLayoutId = R.layout.fragment_home_list;
        mRecyclerViewAdapter = new MyHomeRecyclerViewAdapter(mDataList,mListener);
        mAdCat = 0;

        View view = super.onCreateView(inflater,container,savedInstanceState);
        layoutInflater = inflater;
        setTypeList();
        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        selectTypeId = checkedId;
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setRefreshing(true);
            }
        });
    }
}
