package com.asiainfo.myapplication.animations;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Asiainfo-zhujl3 on 2017/2/9/009.
 */

public class RevealActivity extends BaseActivity {

    @Bind(R.id.shared_target)
    ImageView mSharedTarget;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.sample_body)
    TextView mSampleBody;
    @Bind(R.id.square_green)
    ImageView mSquareGreen;
    @Bind(R.id.square_red)
    ImageView mSquareRed;
    @Bind(R.id.square_blue)
    ImageView mSquareBlue;
    @Bind(R.id.square_yellow)
    ImageView mSquareYellow;
    @Bind(R.id.reveal_root)
    RelativeLayout mRevealRoot;

    private Interpolator interpolator;
    private Sample mSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupToolbar();
    }

    private void bindData(){
        mSample = (Sample) getIntent().getSerializableExtra(BaseActivity.EXTRA_SAMPLE);
        mTitle.setText(mSample.getName());
    }

    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupWindowAnimations(){
        interpolator = AnimationUtils.loadInterpolator(this,android.R.interpolator.linear_out_slow_in);
        setEnterAnimation();
        setExitAnimation();
    }

    private void setEnterAnimation(){
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void setExitAnimation(){
        Fade returnTransition = new Fade();
        getWindow().setReturnTransition(returnTransition);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }
}
