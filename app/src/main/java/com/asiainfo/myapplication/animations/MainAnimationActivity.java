package com.asiainfo.myapplication.animations;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainAnimationActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.sample_list)
    RecyclerView mSampleList;
    private List<Sample> samples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animation);
        ButterKnife.bind(this);
        setupAnimations();
        setupToolbar();
        setupSamples();
        setupLayout();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupAnimations(){
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);

    }

    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupSamples() {
        samples = Arrays.asList(new Sample(ContextCompat.getColor(this, R.color.sample_red), "Transistions"),
                new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elemtents"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circle Reveal Animation"));
    }

    private void setupLayout(){
        mSampleList.setHasFixedSize(true);
        mSampleList.setLayoutManager(new LinearLayoutManager(this));
        SamplesRecyclerAdapter sampleRecyclerAdapter = new SamplesRecyclerAdapter(this,samples);
        mSampleList.setAdapter(sampleRecyclerAdapter);
    }

}
