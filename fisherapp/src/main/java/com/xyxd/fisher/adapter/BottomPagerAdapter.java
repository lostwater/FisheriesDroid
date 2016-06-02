package com.xyxd.fisher.adapter;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyxd.fisher.Activity.MainActivity;
import com.xyxd.fisher.Fragment.CelebrityFragment;
import com.xyxd.fisher.Fragment.EventFragment;
import com.xyxd.fisher.Fragment.HomeFragment;
import com.xyxd.fisher.Fragment.LiveFragment;
import com.xyxd.fisher.R;

/**
 * Created by lostw on 2016/5/31.
 */

public class BottomPagerAdapter extends FragmentPagerAdapter {

    public final int PAGE_COUNT = 4;
    private int[] mTabsIcons = {
            R.drawable.ic_action_button_home,
            R.drawable.ic_action_button_halloffame,
            R.drawable.ic_action_button_enroll,
            R.drawable.ic_action_button_live};

    public Activity mActivity;

    public BottomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View view = LayoutInflater.from(mActivity).inflate(R.layout.bottom_tab, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mTabsIcons[position]);
        Drawable drawable = icon.getDrawable().mutate();
        drawable.setColorFilter(view.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        title.setText(getPageTitle(position));
        return view;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CelebrityFragment();
            case 2:
                return new EventFragment();
            case 3:
                return new LiveFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "首页";
            case 1:
                return "名人堂";
            case 2:
                return "报名";
            case 3:
                return "直播";
        }
        return null;
    }
}
