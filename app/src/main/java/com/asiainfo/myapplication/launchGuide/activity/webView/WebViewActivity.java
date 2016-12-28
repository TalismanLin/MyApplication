/**
 * Package:com.jialin.launchGuide.activity.webView
 * Author:Zhu JL
 * Date:2015��10��19��
 */
package com.asiainfo.myapplication.launchGuide.activity.webView;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

/**
 * @author Zhu JL
 *
 */
public class WebViewActivity extends BaseActivity {

	
	private WebView myWebView;
	
	
	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.webview);
		myWebView  = (WebView) findViewById(R.id.myWebView);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new LocalJavascriptInterface(this), "Android");
		myWebView.loadUrl("file:///android_asset/index.html");
	
	}
	
}
