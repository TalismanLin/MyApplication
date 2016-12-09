/**
 * Package:com.jialin.launchGuide.activity.splash
 * Author:Zhu JL
 * Date:2015��10��15��
 */
package com.asiainfo.myapplication.launchGuide.activity.splash;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.MyApplication;
import com.asiainfo.myapplication.R;
import com.asiainfo.myapplication.launchGuide.activity.SuccessLaunchActivity;

/**
 * @author Zhu JL
 *
 */
public class SplashActivity extends BaseActivity {

	private MyApplication app;
	
	private final int TIME_DELAY = 3000;
	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		app = (MyApplication) this.getApplication();
		app.pushActivity(this);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(getIntent().setClass(SplashActivity.this, SuccessLaunchActivity.class));
				
			}
		}, TIME_DELAY);
	}
}
