package com.asiainfo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asiainfo.myapplication.animations.MainAnimationActivity;
import com.asiainfo.myapplication.customWebView.SimpleWebViewActivity;
import com.asiainfo.myapplication.launchGuide.activity.LauchGuideActivity;
import com.asiainfo.myapplication.loadingView.LoadingViewActivity;
import com.asiainfo.myapplication.mySubmit.MySubmitActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btnGuideStart)
    Button mBtnGuideStart;
    @Bind(R.id.btnMapStart)
    Button mBtnMapStart;
    @Bind(R.id.btnPercentView)
    Button mBtnPercentView;
    @Bind(R.id.btnMySubmit)
    Button mBtnMySubmit;
    @Bind(R.id.btnColorPicker)
    Button mBtnColorPicker;
    @Bind(R.id.btnLoadingView)
    Button mBtnLoadingView;
    @Bind(R.id.btnPickerView)
    Button mBtnPickerView;
    @Bind(R.id.btnViewSwitcher)
    Button mBtnViewSwitcher;
    @Bind(R.id.share)
    Button mShare;
    @Bind(R.id.dataSwitch)
    Button mDataSwitch;
    @Bind(R.id.video)
    Button mVideo;
    @Bind(R.id.browserStart)
    Button mBrowserStart;
    @Bind(R.id.animations)
    Button mAnimations;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;

    private Intent mIntent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSystemBar(this);
        ButterKnife.bind(this);
        mBrowserStart.setOnClickListener(this);
        mBtnMySubmit.setOnClickListener(this);
        mBtnGuideStart.setOnClickListener(this);
        mBtnLoadingView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.browserStart:
                mIntent.setClass(this, SimpleWebViewActivity.class);
                mIntent.putExtra(FROM_PAGE, "HOME");
                startActivity(mIntent);
                break;
            case R.id.btnMySubmit:
                mIntent.setClass(this, MySubmitActivity.class);
                mIntent.putExtra(FROM_PAGE, "HOME");
                startActivity(mIntent);
                break;
            case R.id.btnGuideStart:
                Intent mIntent = new Intent();
                mIntent.setClass(this, LauchGuideActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnLoadingView:
                mIntent = new Intent(this, LoadingViewActivity.class);
                startActivity(mIntent);
                break;
            case R.id.animations:
                mIntent = new Intent(this, MainAnimationActivity.class);
                startActivity(mIntent);
                break;
        }
    }

}
