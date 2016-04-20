package com.xyxd.fisher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xyxd.fisher.Fragment.CelebrityInformationFragment;
import com.xyxd.fisher.Fragment.CelebrityVideoFragment;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.Listeners.OnListFragmentInteractionListener;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Celebrity;
import com.xyxd.fisher.model.Information;
import com.xyxd.fisher.model.Video;
import com.xyxd.utils.LetvParamsUtils;

import info.hoang8f.android.segmented.SegmentedGroup;

public class CelebrityActivity extends AppCompatActivity
        implements  RadioGroup.OnCheckedChangeListener,
        OnListFragmentInteractionListener
{

    public int celebrityId;

    public void onListFragmentInteraction(Object  item)
    {
        if(item instanceof  Video)
        {
            startLecloudVod((Video)item);
        }

        if(item instanceof Information)
        {
            Intent intent = new Intent(CelebrityActivity.this, InformationActivity.class);
            Gson gson = new Gson();
            String info = gson.toJson((Information)item);
            intent.putExtra("info",info);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String strCele = getIntent().getExtras().get("celebrity").toString();
        Gson gson = new Gson();
        Celebrity celebrity = gson.fromJson(strCele, Celebrity.class);
        celebrityId = celebrity.getId();
        //setContentView(R.layout.activity_celebrity);
        CelebrityInformationFragment infoFragment = CelebrityInformationFragment.newInstance(1, celebrityId);
        getFragmentManager().beginTransaction().replace(R.id.listContainer, infoFragment).commit();

        SegmentedGroup typeSegment = (SegmentedGroup)findViewById(R.id.typeSeg);
        typeSegment.setOnCheckedChangeListener(this);

        TextView name = (TextView)findViewById(R.id.name);
        //name.lay

        TextView intro = (TextView)findViewById(R.id.intro);
        ImageView image = (ImageView)findViewById(R.id.image);
        name.setText(celebrity.getName());
        intro.setText(celebrity.getIntro());
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = Client.toUri(celebrity.getAvatarUrl());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar)
                .showImageForEmptyUri(R.drawable.avatar)
                .showImageOnFail(R.drawable.avatar)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        imageLoader.displayImage(path, image, options);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       if (checkedId == R.id.articlebutton)
       {
           CelebrityInformationFragment infoFragment = CelebrityInformationFragment.newInstance(1, celebrityId);
           getFragmentManager().beginTransaction().replace(R.id.listContainer, infoFragment).commit();
       }
        if (checkedId == R.id.videobutton)
       {
           CelebrityVideoFragment videoFragment = CelebrityVideoFragment.newInstance(2, celebrityId);
           getFragmentManager().beginTransaction().replace(R.id.listContainer, videoFragment).commit();
       }

    }


    private void startLecloudVod(Video video) {
        Intent intent;
            intent = new Intent(CelebrityActivity.this, PlayActivity.class);
        String uuid = "nal4hqaahb";
        String vuid = video.getVu();

//        Bundle bundle = LetvParamsUtils.setVodParams(etUUID.getText().toString().trim(), etVUID.getText().toString().trim(), "", "151398", "", false);
        Bundle bundle = LetvParamsUtils.setVodParams(uuid, vuid, "", "802439", "");
        intent.putExtra(PlayActivity.DATA, bundle);
        startActivity(intent);
    }


}
