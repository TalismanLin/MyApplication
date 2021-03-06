package com.asiainfo.myapplication.animations;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class TransitionActivity2 extends BaseActivity {


    @Bind(R.id.exit_button)
    Button mExitButton;
    @Bind(R.id.square_red)
    ImageView mSquareRed;
    @Bind(R.id.activity_title)
    TextView mActivityTitle;
    @Bind(R.id.title)
    TextView mTitle;

    private int type;

    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        sample = (Sample) getIntent().getSerializableExtra(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Transition transition;
        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        }
        getWindow().setEnterTransition(transition);
    }

    @TargetApi(21)
    private void setupLayout() {
        mTitle.setText(sample.getName());
        mSquareRed.setColorFilter(sample.getColor());
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    @TargetApi(21)
    private Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


}
