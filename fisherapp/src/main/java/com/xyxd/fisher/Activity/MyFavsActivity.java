package com.xyxd.fisher.Activity;


import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.gson.Gson;
import com.xyxd.fisher.Fragment.MyLivesFragment;
import com.xyxd.fisher.Fragment.MyShopEventsFragment;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Celebrity;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Information;
import com.xyxd.fisher.model.Live;

public class MyFavsActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favs);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void onListFragmentInteraction(Object  item)
    {
        if(item instanceof Live)
        {
            Intent intent = new Intent(MyFavsActivity.this, WebLivePlayActivity.class);
            Gson gson = new Gson();
            String live = gson.toJson((Live)item);
            intent.putExtra("live",live);
            startActivity(intent);
            //startLeCloudActionLive((Live)item);
        }
        if(item instanceof Information)
        {
            Intent intent = new Intent(MyFavsActivity.this, InformationActivity.class);
            Gson gson = new Gson();
            String info = gson.toJson((Information)item);
            intent.putExtra("info",info);
            startActivity(intent);
        }
        if(item instanceof Celebrity)
        {
            Intent intent = new Intent(MyFavsActivity.this, CelebrityActivity.class);
            Gson gson = new Gson();
            String celebrity = gson.toJson((Celebrity)item);
            intent.putExtra("celebrity",celebrity);
            startActivity(intent);
        }
        if(item instanceof Event)
        {
            Intent intent = new Intent(MyFavsActivity.this, EventActivity.class);
            Gson gson = new Gson();
            String event = gson.toJson((Event)item);
            intent.putExtra("event",event);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_my_favs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==0)
            {
                return new MyShopEventsFragment();
            }
            else
            {
                return new MyLivesFragment();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "收藏的渔场";
                case 1:
                    return "收藏的直播";
            }
            return null;
        }
    }
}
