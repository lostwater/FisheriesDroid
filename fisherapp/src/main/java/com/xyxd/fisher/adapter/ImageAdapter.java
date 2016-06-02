package com.xyxd.fisher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IViewHolder;
import com.bumptech.glide.Glide;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.model.Ad;
import com.xyxd.fisher.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aspsine on 16/4/5.
 */
public class ImageAdapter extends RecyclerView.Adapter<IViewHolder> {

    private List<Ad> mAds;

    private OnItemClickListener<Ad> mOnItemClickListener;

    public ImageAdapter() {
        mAds = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<Ad> listener) {
        this.mOnItemClickListener = listener;
    }

    public void setList(List<Ad> ads) {
        mAds.clear();
        append(ads);
    }

    public void append(List<Ad> ads) {
        int positionStart = mAds.size();
        int itemCount = ads.size();
        mAds.addAll(ads);
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    public void clear(){
        mAds.clear();
        notifyDataSetChanged();
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_item, parent, false);

        final ViewHolder holder = new ViewHolder(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Note:
                 * in order to get the right position, you must use the method with i- prefix in
                 * {@link IViewHolder} eg:
                 * {@code IViewHolder.getIPosition()}
                 * {@code IViewHolder.getILayoutPosition()}
                 * {@code IViewHolder.getIAdapterPosition()}
                 */
                final int position = holder.getIAdapterPosition();
                final Ad ad = mAds.get(position);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, ad, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        ImageView imageView = (ImageView) holder.itemView;
        Ad ad = mAds.get(position);
        Glide.with(imageView.getContext()).load(Client.toUri(ad.getAvatarUrl())).dontAnimate().into(imageView);
    }

    @Override
    public int getItemCount() {
        return mAds.size();
    }

    static class ViewHolder extends IViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
