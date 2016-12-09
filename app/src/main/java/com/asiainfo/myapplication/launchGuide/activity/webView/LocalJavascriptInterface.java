/**
 * Package:com.jialin.launchGuide.activity.webView
 * Author:Zhu JL
 * Date:2015��10��19��
 */
package com.asiainfo.myapplication.launchGuide.activity.webView;


import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.asiainfo.myapplication.launchGuide.activity.SuccessLaunchActivity;

/**
 * @author Zhu JL
 *
 */
public class LocalJavascriptInterface {
	private Activity mActivity;
	
	
	public LocalJavascriptInterface(Activity activity){
		this.mActivity = activity;
	}
	
	@JavascriptInterface
	public void startActivity(){
		Intent mIntent = new Intent();
		mIntent.setClass(mActivity,SuccessLaunchActivity.class);
		mActivity.startActivity(mIntent);
		mActivity.finish();
	}
}
