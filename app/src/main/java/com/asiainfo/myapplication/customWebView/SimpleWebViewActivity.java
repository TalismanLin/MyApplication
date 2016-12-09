package com.asiainfo.myapplication.customWebView;

import android.os.Bundle;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class SimpleWebViewActivity extends BaseActivity {

    @Bind(R.id.myWebView)
    CustomWebView mMyWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_webview);
        ButterKnife.bind(this);

        mMyWebView.setCustomWebViewListener(new CustomWebViewListener() {
            @Override
            public void onPageFinished() {

            }
        });
        mMyWebView.loadUrl("https://www.baidu.com/");
    }
}
