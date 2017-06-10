package com.asiainfo.myapplication.animations;

import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhujialin on 6/6/17.
 */

public class AnimationActivity1 extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.square_green)
    ImageView mSquareGreen;
    @Bind(R.id.sample3_button1)
    Button mSample3Button1;
    @Bind(R.id.sample3_button2)
    Button mSample3Button2;
    @Bind(R.id.sample3_button3)
    Button mSample3Button3;
    @Bind(R.id.sample3_root)
    LinearLayout mSample3Root;

    private Sample sample;
    private int savedWidth;
    private boolean sizeChanged;
    private boolean positionChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation1);
        ButterKnife.bind(this);
        bindData();
        setupToolbar();
        setupWindowAnimations();
    }

    private void bindData() {
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        mTitle.setText(sample.getName());
        DrawableCompat.setTint(mSquareGreen.getDrawable(), sample.getColor());
    }

    private void setupWindowAnimations() {
        getWindow().setReenterTransition(new Fade());
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @OnClick({R.id.sample3_button1, R.id.sample3_button2, R.id.sample3_button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sample3_button1:
                changeLayout();
                break;
            case R.id.sample3_button2:
                changePosition();
                break;
            case R.id.sample3_button3:
//                Intent mIntent = new Intent(AnimationActivity1.this, AnimationActivity2.class);
                break;

        }
    }

    private void changeLayout(){
        TransitionManager.beginDelayedTransition(mSample3Root);
        ViewGroup.LayoutParams params = mSquareGreen.getLayoutParams();
        if(sizeChanged){
            params.width = savedWidth;
        }else{
            savedWidth = params.width;
            params.width = 200;
        }
        sizeChanged = !sizeChanged;
        mSquareGreen.setLayoutParams(params);
    }

    private void changePosition(){
        TransitionManager.beginDelayedTransition(mSample3Root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mSquareGreen.getLayoutParams();
        if (positionChanged){
            params.gravity = Gravity.CENTER;
        }else {
            params.gravity = Gravity.LEFT;
        }
        positionChanged = !positionChanged;
        mSquareGreen.setLayoutParams(params);
    }
}
