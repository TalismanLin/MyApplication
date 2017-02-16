package com.asiainfo.myapplication.animations;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class SharedElementActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.sample2_content)
    FrameLayout mSample2Content;

    private Sample mSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedelement);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData(){
        mSample = (Sample) getIntent().getSerializableExtra(BaseActivity.EXTRA_SAMPLE);
        if(mSample != null){
            mTitle.setText(mSample.getName());
        }
    }

    private void setupWindowAnimations(){
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(){
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        SharedElementFragment1 sharedElementFragment1 = SharedElementFragment1.newInstance(mSample);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.sample2_content,sharedElementFragment1
        ).commit();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
