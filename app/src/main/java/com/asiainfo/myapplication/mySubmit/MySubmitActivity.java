/**
 * Package:com.jialin.mySubmit
 * Author:Zhu JL
 * Date:2015��10��28��
 */
package com.asiainfo.myapplication.mySubmit;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Zhu JL
 */
public class MySubmitActivity extends BaseActivity implements OnClickListener {

    @Bind(R.id.btn_cancel)
    Button mBtnCancel;
    @Bind(R.id.btn_confirm)
    Button mBtnConfirm;
    @Bind(R.id.view_submit)
    SubmitView mViewSubmit;

    /*
         * @see android.app.Activity#onCreate(android.os.Bundle)
         */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_main);
        ButterKnife.bind(this);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initButton();
    }


    private void initView() {
        mViewSubmit.setBackColor(Color.BLUE);
        mViewSubmit.setProgress(1);
//		submitView.setClickable(false);
        mViewSubmit.setOnProgressStart(new SubmitView.OnProgressStart() {

            @Override
            public void progressStart() {
                // TODO Auto-generated method stub
                if (mBtnCancel.isClickable()) {
                    mBtnCancel.setClickable(false);
                }
                if (mBtnConfirm.isClickable()) {
                    mBtnConfirm.setClickable(false);
                }
            }
        });

        mViewSubmit.setOnProgressDone(new SubmitView.OnProgressDone() {

            @Override
            public void progressDone() {
                // TODO Auto-generated method stub
                if (!mBtnCancel.isClickable()) {
                    mBtnCancel.setClickable(true);
                }
                if (!mBtnCancel.isClickable()) {
                    mBtnCancel.setClickable(true);
                }
            }
        });
    }

    private void initButton() {
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }


    /*
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == mBtnCancel) {
            this.finish();
        } else if (v == mBtnConfirm) {
            mViewSubmit.start();
        }
    }

}
