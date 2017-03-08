/**
 * Package:com.jialin
 * Author:Zhu JL
 * Date:2015��10��25��
 */
package com.asiainfo.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.asiainfo.myapplication.util.SystemBarTintManager;
import com.asiainfo.myapplication.util.TransitionHelper;

/**
 * @author Zhu JL
 *
 */
public class BaseActivity extends AppCompatActivity {

	public static String FROM_PAGE = "from_page";

	public static final String EXTRA_SAMPLE = "sample";
	public static final String EXTRA_TYPE = "type";

	public static final int TYPE_PROGRAMMATICALLY = 0;
	public static final int TYPE_XML = 1;

	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		initSystemBar(this);
//		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
//			setTranslucentStatus(this, true);
//		}
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
	}
	
	private static void setTranslucentStatus(Activity activity,boolean on){
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if(on){
			winParams.flags |= bits;
		}else{
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
	
	public static void initSystemBar(Activity activity){
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
			setTranslucentStatus(activity, true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setNavigationBarTintColor(android.R.color.transparent);
	}


	public void transitionTo(Intent i) {
		final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
		ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
		startActivity(i, transitionActivityOptions.toBundle());
	}

}
