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

import static com.asiainfo.myapplication.R.drawable.square;

/**
 * Created by Asiainfo-zhujl3 on 2017/2/8/008.
 */

public class AnimationActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.square_green)
    ImageView mSquareGreen;
    @Bind(R.id.sample_title)
    TextView mSampleTitle;
    @Bind(R.id.sample3_button1)
    Button mSample3Button1;
    @Bind(R.id.sample3_button2)
    Button mSample3Button2;
    @Bind(R.id.sample3_button3)
    Button mSample3Button3;
    @Bind(R.id.sample3_root)
    LinearLayout viewRoot;

    private boolean sizeChanged;
    private int savedWidth;
    private boolean positionChanged;

    private Sample mSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations1);
        ButterKnife.bind(this);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData(){
        mSample = (Sample) getIntent().getSerializableExtra(BaseActivity.EXTRA_SAMPLE);
        mTitle.setText(mSample.getName());
        DrawableCompat.setTint(mSquareGreen.getDrawable(), mSample.getColor());
    }

    private void setupWindowAnimations(){
        getWindow().setReenterTransition(new Fade());
    }

    private void setupLayout(){

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @OnClick({R.id.square_green, R.id.sample3_button1, R.id.sample3_button2, R.id.sample3_button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sample3_button1:
                changeLayout();
                break;
            case R.id.sample3_button2:
                changePosition();
                break;
            case R.id.sample3_button3:
                break;
        }
    }

    private void changeLayout(){
        TransitionManager.beginDelayedTransition(viewRoot);

        ViewGroup.LayoutParams params = mSquareGreen.getLayoutParams();
        if (sizeChanged) {
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 200;
        }
        sizeChanged = !sizeChanged;
        mSquareGreen.setLayoutParams(params);
    }

    private void changePosition(){
        TransitionManager.beginDelayedTransition(viewRoot);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mSquareGreen.getLayoutParams();
        if (positionChanged) {
            lp.gravity = Gravity.CENTER;
        } else {
            lp.gravity = Gravity.LEFT;
        }
        positionChanged = !positionChanged;
        mSquareGreen.setLayoutParams(lp);
    }
}
