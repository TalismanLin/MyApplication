/**
 * Package:com.jialin.launchGuide.activity.viewFlipper
 * Author:Zhu JL
 * Date:2015��10��15��
 */
package com.asiainfo.myapplication.launchGuide.activity.viewFlipper;


import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;
import com.asiainfo.myapplication.launchGuide.activity.SuccessLaunchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Zhu JL
 */
public class ViewFlipperActivity extends BaseActivity implements OnGestureListener {


    @Bind(R.id.tvInNew)
    TextView tvInNew;
    @Bind(R.id.vf_activity)
    ViewFlipper mViewFliper;
    private GestureDetector mGestureDetector;

    /*
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        ButterKnife.bind(this);
        initView();

    }

    @SuppressWarnings("deprecation")
    private void initView() {
        mGestureDetector = new GestureDetector(this);
        tvInNew.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(ViewFlipperActivity.this, SuccessLaunchActivity.class));
            }
        });
    }

    /*
     * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
     */
    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * @see android.view.GestureDetector.OnGestureListener#onShowPress(android.view.MotionEvent)
     */
    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * @see android.view.GestureDetector.OnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
     */
    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        if (e1.getX() > e2.getX()) {
            mViewFliper.showNext();
        } else if (e1.getX() < e2.getX()) {
            mViewFliper.showPrevious();
        } else {
            return false;
        }
        return true;
    }


    /*
     * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return mGestureDetector.onTouchEvent(event);
    }

}
