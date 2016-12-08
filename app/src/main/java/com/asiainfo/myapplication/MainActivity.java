package com.asiainfo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asiainfo.myapplication.customWebView.SimpleWebViewActivity;

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
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBrowserStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.browserStart:
                startBrowser();
                break;
        }
    }

    private void startBrowser(){
        Intent mIntent = new Intent(this, SimpleWebViewActivity.class);
        mIntent.putExtra(FROM_PAGE, "HOME");
        startActivity(mIntent);
    }

}
