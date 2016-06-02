package com.xyxd.fisher.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyxd.fisher.Fragment.OrderFragment.OnListFragmentInteractionListener;
import com.xyxd.fisher.Fragment.dummy.DummyContent.DummyItem;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyOrderRecyclerViewAdapter extends RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder> {

    private final List<Order> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyOrderRecyclerViewAdapter(List<Order> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        DateFormat df = new SimpleDateFormat("yyyy年mm月dd日 HH:mm");
        holder.mShopView.setText(holder.mItem.getEvent().getShop().getName());
        holder.mTimeView.setText(df.format(holder.mItem.getEvent().getEventFrom()));
        int statuId = holder.mItem.getOrderStatuId();
        String statu = "";
        switch (statuId)
        {
            case 1:statu = "未支付";
                break;
            case 2:statu = "已支付";
                break;
            case 3:statu = "已使用";
                break;
            case 4:statu = "已取消";
                break;
        }
        holder.mStatuView.setText(statu);
        if(statu.equals("未支付"))
            holder.mStatuView.setTextColor(Color.parseColor("#FF9600"));
        holder.mPriceView.setText(holder.mItem.getOrderPrice()+"元");


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
        public final TextView mShopView;
        public final TextView mTimeView;
        public final TextView mStatuView;
        public final TextView mPriceView;
        public Order mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mShopView = (TextView) view.findViewById(R.id.shopName);
            mTimeView = (TextView) view.findViewById(R.id.time);
            mStatuView = (TextView) view.findViewById(R.id.statu);
            mPriceView = (TextView) view.findViewById(R.id.price);



        }

    }
}
