package com.xyxd.fisher.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xyxd.fisher.Fragment.dummy.DummyContent.DummyItem;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Live;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLiveRecyclerViewAdapter extends RecyclerView.Adapter<MyLiveRecyclerViewAdapter.ViewHolder> {

    private final List<Live> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyLiveRecyclerViewAdapter(List<Live> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_live_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        DateFormat df = new SimpleDateFormat("MM月dd日HH时mm分");

        holder.mItem = mValues.get(position);
        Live live = holder.mItem;
        holder.mTimeView.setText("直播时间: \n" + df.format(live.getStartTime()));
        holder.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = holder.mItem.getCoverImgUrl();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageLoader.displayImage(path, holder.mImageView, options);
        holder.mTitleView.setText("  " + holder.mItem.getActivityName());
        if(holder.mItem.getActivityStatus() == 0)
            holder.mStatuView.setText("未开始");
        if(holder.mItem.getActivityStatus() == 1) {
            holder.mStatuView.setText("直播中");
            holder.mStatuView.setBackgroundResource(R.color.colorPrimary);
        }
        if(holder.mItem.getActivityStatus() == 2)
            holder.mStatuView.setText("已中断");
        if(holder.mItem.getActivityStatus() == 3)
            holder.mStatuView.setText("已结束");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mStatuView;
        public final TextView mTimeView;
        public final ImageView mImageView;
        public Live mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mTitleView = (TextView) view.findViewById(R.id.title);
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mStatuView =  (TextView) view.findViewById(R.id.statu);
            mTimeView =  (TextView) view.findViewById(R.id.time);
        }

    }
}
