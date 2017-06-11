package com.asiainfo.myapplication.animations;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhujialin on 7/6/17.
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        ButterKnife.bind(this);
    }

    private void bindData() {

    }

    private void setupWindowAnimations() {

    }

    private void setupToolbar() {

    }

    private void setupLayout() {

    }
}
