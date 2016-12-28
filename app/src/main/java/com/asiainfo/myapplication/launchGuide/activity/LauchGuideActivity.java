/**
 *
 */
package com.asiainfo.myapplication.launchGuide.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;
import com.asiainfo.myapplication.launchGuide.activity.scrollView.ScrollViewActivity;
import com.asiainfo.myapplication.launchGuide.activity.splash.SplashActivity;
import com.asiainfo.myapplication.launchGuide.activity.viewFlipper.ViewFlipperActivity;
import com.asiainfo.myapplication.launchGuide.activity.viewPage.GuideActivity;
import com.asiainfo.myapplication.launchGuide.activity.webView.WebViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Zhu JL
 */
public class LauchGuideActivity extends BaseActivity implements OnClickListener {


    @Bind(R.id.btnSplash)
    Button mBtnSplash;
    @Bind(R.id.btnViewPage)
    Button mBtnViewPage;
    @Bind(R.id.btnViewFlipper)
    Button mBtnViewFlipper;
    @Bind(R.id.btnScrollView)
    Button mBtnScrollView;
    @Bind(R.id.btnWebView)
    Button mBtnWebView;
    private Intent mIntent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtity_guide_main);
        ButterKnife.bind(this);
        mBtnSplash.setOnClickListener(this);
        mBtnViewPage.setOnClickListener(this);
        mBtnViewFlipper.setOnClickListener(this);
        mBtnScrollView.setOnClickListener(this);
        mBtnWebView.setOnClickListener(this);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        if (v == mBtnSplash) {
            mIntent.setClass(LauchGuideActivity.this, SplashActivity.class);
            startActivity(mIntent);
        } else if (v == mBtnViewPage) {
            mIntent.setClass(LauchGuideActivity.this, GuideActivity.class);
            startActivity(mIntent);
        } else if (v == mBtnViewFlipper) {
            mIntent.setClass(LauchGuideActivity.this, ViewFlipperActivity.class);
            startActivity(mIntent);
        } else if (v == mBtnScrollView) {
            mIntent.setClass(LauchGuideActivity.this, ScrollViewActivity.class);
            startActivity(mIntent);
        } else if (v == mBtnWebView) {
            mIntent.setClass(LauchGuideActivity.this, WebViewActivity.class);
            startActivity(mIntent);
        }
    }
}
