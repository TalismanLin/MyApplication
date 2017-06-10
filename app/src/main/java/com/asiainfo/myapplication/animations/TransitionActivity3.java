package com.asiainfo.myapplication.animations;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhujialin on 5/6/17.
 */

public class TransitionActivity3 extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.square_red)
    ImageView mSquareRed;
    @Bind(R.id.activity_title)
    TextView mActivityTitle;
    @Bind(R.id.exit_button)
    Button mExitButton;
    @Bind(R.id.title)
    TextView mTitle;

    private int type;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition3);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        sample = (Sample) getIntent().getSerializableExtra(EXTRA_SAMPLE);
    }


    @TargetApi(21)
    private void setupWindowAnimations() {
        Transition transition;
        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransistion();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }
        getWindow().setEnterTransition(transition);
    }

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

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @TargetApi(21)
    private Visibility buildEnterTransistion() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }

}
