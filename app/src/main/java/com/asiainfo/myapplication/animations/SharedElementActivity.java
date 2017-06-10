package com.asiainfo.myapplication.animations;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
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

    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        ButterKnife.bind(this);
        bindData();
        setupToolbar();
        setupWindowAnimations();
        setupLayout();
    }

    private void bindData() {
        sample = (Sample) getIntent().getSerializableExtra(EXTRA_SAMPLE);
    }

    @TargetApi(21)
    private void setupWindowAnimations() {
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_medium));

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @TargetApi(21)
    private void setupLayout() {
        mTitle.setText(sample.getName());
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        SharedElementFragment1 fragment1 = SharedElementFragment1.newInstance(sample);
        fragment1.setReenterTransition(slideTransition);
        fragment1.setExitTransition(slideTransition);
        fragment1.setSharedElementEnterTransition(new ChangeBounds());
        getSupportFragmentManager().beginTransaction().replace(R.id.sample2_content,fragment1).commit();
    }
}
