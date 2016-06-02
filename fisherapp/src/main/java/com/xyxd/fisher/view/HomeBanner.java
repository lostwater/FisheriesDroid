package com.xyxd.fisher.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.banner.widget.Banner.BaseIndicatorBanner;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.model.Ad;
import com.xyxd.fisher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lostw on 2016/4/19.
 */
public class HomeBanner extends BaseIndicatorBanner<Ad, HomeBanner> {
    private ColorDrawable colorDrawable;
    public int mode = 0;
    List<Ad> mAdList = new ArrayList<>();
    public HomeBanner(Context context) {
        this(context, null, 0);
    }

    public HomeBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        colorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    @Override
    public void onTitleSlect(TextView tv, int position) {
        final Ad item = mDatas.get(position);
        tv.setText("");
    }

    @Override
    public View onCreateItemView(int position) {
        //View inflate = View.inflate(mContext, R.layout.adapter_simple_image, null);
        ImageView iv =(ImageView) View.inflate(mContext,R.layout.homebanner,null);

        final Ad item = mDatas.get(position);
        int itemWidth = getWidth();
        int itemHeight =  getHeight();
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, itemHeight));
        String imgUrl = Client.toUri(item.getAvatarUrl());
        if ( itemWidth == 0)
        {
            itemWidth = 1;
        }
        if ( itemHeight == 0)
        {
            itemHeight = 1;
        }
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .override(itemWidth, itemHeight)
                    .centerCrop()
                    .placeholder(colorDrawable)
                    .into(iv);
        } else {
            iv.setImageDrawable(colorDrawable);
        }

        return iv;
    }
}