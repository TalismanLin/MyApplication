package com.asiainfo.myapplication.animations;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhujialin on 6/6/17.
 */

public class AnimationActivity2 extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.scene_root)
    FrameLayout mSceneRoot;
    @Bind(R.id.sample3_button1)
    Button mSample3Button1;
    @Bind(R.id.sample3_button2)
    Button mSample3Button2;
    @Bind(R.id.sample3_button3)
    Button mSample3Button3;
    @Bind(R.id.sample3_button4)
    Button mSample3Button4;
    @Bind(R.id.buttons_group)
    LinearLayout mButtonsGroup;
    @Bind(R.id.sample3_root)
    LinearLayout mSample3Root;

    private static final int DELAY = 100;

    private Sample sample;
    private Scene scene0, scene1, scene2, scene3, scene4;
    private List<View> viewsToAnimate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupToolbar();
        setupLayout();
    }

    private void bindData() {
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        mTitle.setText(sample.getName());

    }

    private void setupWindowAnimations() {
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom));
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getEnterTransition().removeListener(this);
                TransitionManager.go(scene0);
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

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupLayout() {
        scene0 = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_animations_scene0, this);
        scene0.setEnterAction(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < viewsToAnimate.size(); i++) {
                    View child = viewsToAnimate.get(i);
                    child.animate().setStartDelay(i * DELAY).scaleX(1).scaleY(1);
                }
            }
        });
        scene0.setExitAction(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(mSceneRoot);
                View title = scene0.getSceneRoot().findViewById(R.id.scene0_title);
                title.setScaleX(0);
                title.setScaleY(0);
            }
        });
        scene1 = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_animations_scene1, this);
        scene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_animations_scene2, this);
        scene3 = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_animations_scene3, this);
        scene4 = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_animations_scene4, this);

        mSample3Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(scene1, new ChangeBounds());
            }
        });

        mSample3Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(scene2,
                        TransitionInflater.from(AnimationActivity2.this).inflateTransition(
                                R.transition.slide_and_changebounds
                        ));
            }
        });

        mSample3Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(scene3,
                        TransitionInflater.from(AnimationActivity2.this).inflateTransition(
                                R.transition.slide_and_changebounds_sequential
                        ));
            }
        });

        mSample3Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(scene4,
                        TransitionInflater.from(AnimationActivity2.this).inflateTransition(
                                R.transition.slide_and_changebounds_sequential_with_interpolators
                        ));
            }
        });
        viewsToAnimate.add(mSample3Button1);
        viewsToAnimate.add(mSample3Button2);
        viewsToAnimate.add(mSample3Button3);
        viewsToAnimate.add(mSample3Button4);

    }

}
