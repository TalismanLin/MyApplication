package com.asiainfo.myapplication.animations;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
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

    @OnClick({R.id.sample3_button1, R.id.sample3_button2, R.id.sample3_button3, R.id.sample3_button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sample3_button1:
                break;
            case R.id.sample3_button2:
                break;
            case R.id.sample3_button3:
                break;
            case R.id.sample3_button4:
                break;
        }
    }
}
