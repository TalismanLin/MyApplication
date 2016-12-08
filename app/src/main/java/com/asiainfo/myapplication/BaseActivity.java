/**
 * Package:com.jialin
 * Author:Zhu JL
 * Date:2015��10��25��
 */
package com.asiainfo.myapplication;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.asiainfo.myapplication.util.SystemBarTintManager;

/**
 * @author Zhu JL
 *
 */
public class BaseActivity extends Activity {

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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {  
            Window window = getWindow();  
            // Translucent status bar  
            window.setFlags(  
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,  
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
        } 
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
	
	private static void initSystemBar(Activity activity){
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
			setTranslucentStatus(activity, true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setNavigationBarTintColor(android.R.color.transparent);
	}
	
}
