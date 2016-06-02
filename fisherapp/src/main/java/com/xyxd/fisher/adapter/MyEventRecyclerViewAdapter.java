package com.xyxd.fisher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xyxd.fisher.Fragment.dummy.DummyContent.DummyItem;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Shop;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyEventRecyclerViewAdapter(List<Event> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        Event event = holder.mItem;
        Shop shop = event.getShop();

        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = Client.toUri(event.getAvatarUrl());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageLoader.displayImage(path, holder.mImageView, options);

        holder.mTitleView.setText(shop.getName());

        DateFormat df = new SimpleDateFormat("MM月dd");
        String dateString = df.format(event.getEventFrom());
        holder.mTextTime.setText(dateString);

        Locale locale = new Locale("zh", "CN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.mTextPrice.setText(currencyFormatter.format(event.getDiscountPrice()));

        holder.mTextIntro.setText(event.getIntro());

        if(!shop.getVerified())
        {
            holder.mIVContract.setImageResource(R.drawable.ic_noncontract);
        }
        if(shop.getLiveId() == null)
        {
            holder.mIVLive.setImageResource(R.drawable.ic_nonlive);
        }
        if(Double.compare(event.getDiscountPrice(),event.getPrice())  == 0)
        {
            holder.mIVDiscount.setImageResource(R.drawable.ic_nondiscount);
        }



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
        public final ImageView mImageView;
        public final TextView mTextTime;
        public final TextView mTextPrice;
        public final TextView mTextIntro;

        public final ImageView mIVContract;
        public final ImageView mIVDiscount;
        public final ImageView mIVLive;

        public Event mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.text_title);
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextTime  = (TextView) view.findViewById(R.id.text_time);
            mTextPrice  = (TextView) view.findViewById(R.id.text_price);
            mTextIntro  = (TextView) view.findViewById(R.id.text_intro);
            mIVContract = (ImageView) view.findViewById(R.id.iv_contract);
            mIVDiscount = (ImageView) view.findViewById(R.id.iv_discount);
            mIVLive = (ImageView) view.findViewById(R.id.iv_live);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
