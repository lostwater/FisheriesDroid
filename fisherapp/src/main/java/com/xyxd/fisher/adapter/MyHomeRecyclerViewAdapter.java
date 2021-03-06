package com.xyxd.fisher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xyxd.fisher.Fragment.dummy.DummyContent.DummyItem;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Information;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHomeRecyclerViewAdapter extends RecyclerView.Adapter<IViewHolder> {

    private final List<Information> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyHomeRecyclerViewAdapter(List<Information> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder iViewHolder, int position) {
        final ViewHolder holder = (ViewHolder)iViewHolder;
        holder.mItem = mValues.get(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = Client.toUri(holder.mItem.getImageUrl());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageLoader.displayImage(path, holder.mImageView, options);
        holder.mTitleView.setText(holder.mItem.getTitle());
        holder.mIntroView.setText(holder.mItem.getIntro());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        holder.mDateView.setText(df.format(holder.mItem.getCreatedTime()));
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mTitleView.setText(mValues.get(position).);

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


    public class ViewHolder extends IViewHolder {
        public final View mView;
       // public final TextView mIdView;
        public final TextView mTitleView;
        public final TextView mIntroView;
        public final TextView mDateView;

        public ImageView mImageView;
        public Information mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            mTitleView = (TextView) view.findViewById(R.id.title);
            mIntroView = (TextView) view.findViewById(R.id.intro);
            mDateView = (TextView) view.findViewById(R.id.time);
            mImageView = (ImageView) view.findViewById(R.id.avatar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
