package com.asiainfo.myapplication.animations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class TransitionActivity1 extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.sample1_button1)
    Button sampleButton1;
    @Bind(R.id.sample1_button2)
    Button sampleButton2;
    @Bind(R.id.sample1_button3)
    Button sampleButton3;
    @Bind(R.id.sample1_button4)
    Button sampleButton4;
    @Bind(R.id.sample1_button6)
    Button sampleButton6;
    @Bind(R.id.sample1_button5)
    Button sampleButton5;

    private Sample sample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupToolbar();
    }

    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupWindowAnimations(){
        Visibility enterTransition = buildEnterTransistion();
        getWindow().setEnterTransition(enterTransition);
    }

    private Visibility buildEnterTransistion(){
        Fade enterTransistion = new Fade();
        enterTransistion.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        return enterTransistion;
    }
    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }

    private void bindData() {
        sample = (Sample) getIntent().getSerializableExtra(EXTRA_SAMPLE);
        if(sample == null){
            return;
        }
        mTitle.setText(sample.getName());
    }


    @OnClick({R.id.sample1_button1, R.id.sample1_button2, R.id.sample1_button3, R.id.sample1_button4, R.id.sample1_button6, R.id.sample1_button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sample1_button1:
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
                break;
            case R.id.sample1_button2:
                Intent mIntent = new Intent(TransitionActivity1.this, TransitionActivity2.class);
                mIntent.putExtra(EXTRA_SAMPLE, sample);
                mIntent.putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(mIntent);
                break;
            case R.id.sample1_button3:
                break;
            case R.id.sample1_button4:
                break;
            case R.id.sample1_button6:
                finishAfterTransition();
                break;
            case R.id.sample1_button5:
                Visibility returnTransistion = buildReturnTransition();
                getWindow().setReturnTransition(returnTransistion);
                finishAfterTransition();
                break;
        }
    }


}
