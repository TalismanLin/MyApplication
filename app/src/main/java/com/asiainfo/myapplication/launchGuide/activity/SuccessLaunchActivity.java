/**
 * Package:com.jialin.launchGuide.activity
 * Author:Zhu JL
 * Date:2015��10��15��
 */
package com.asiainfo.myapplication.launchGuide.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.MyApplication;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Zhu JL
 */
public class SuccessLaunchActivity extends BaseActivity {

    @Bind(R.id.btnBack)
    Button btnBack;
    private MyApplication app;

    private Intent mIntent = new Intent();

    /*
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successlaunch);
        ButterKnife.bind(this);
        app = (MyApplication) this.getApplication();
        app.pushActivity(this);
        btnBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mIntent.setClass(SuccessLaunchActivity.this, LauchGuideActivity.class);
                startActivity(mIntent);
                app.popActivity();
            }
        });
    }
}
