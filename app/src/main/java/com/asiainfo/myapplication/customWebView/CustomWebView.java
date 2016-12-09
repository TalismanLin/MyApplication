package com.asiainfo.myapplication.customWebView;

import java.util.Date;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.asiainfo.myapplication.MyApplication;
import com.asiainfo.myapplication.R;
import com.asiainfo.myapplication.common.BaseWebFunction;
import com.asiainfo.myapplication.common.Const;
import com.asiainfo.myapplication.common.GenericTask;
import com.asiainfo.myapplication.common.GenericTaskListener;
import com.asiainfo.myapplication.util.DateUtil;

import cn.pedant.SafeWebViewBridge.InjectedChromeClient;


@SuppressLint("SetJavaScriptEnabled")
public class CustomWebView extends RelativeLayout {

	private Context ctx;
	private ProgressBar progressBar;
	private WebView webView;
	private LinearLayout errorLL;
	private Button refreshBtn;

	private CustomWebViewListener customWebViewListener;
	
	private boolean showLoadingProgress = false;

	public CustomWebView(Context context) {
		super(context);
		ctx = context;
	}

	public CustomWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = context;

		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_webview, this);

		progressBar = (ProgressBar) findViewById(R.id.custom_webview_pb);
		webView = (WebView) findViewById(R.id.custom_webview_wv);
		errorLL = (LinearLayout) findViewById(R.id.custom_error_ll);
		errorLL.setVisibility(View.GONE);
		refreshBtn = (Button) findViewById(R.id.custom_refresh_btn);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 开启 DOM storage API 功能
		webView.getSettings().setDomStorageEnabled(true);
		// 开启 database storage API 功能
		webView.getSettings().setDatabaseEnabled(true);
		String cacheDirPath = ctx.getFilesDir().getAbsolutePath() + "/webcache";
		// 设置 Application Caches 缓存目录
		webView.getSettings().setAppCachePath(cacheDirPath);
		// 开启 Application Caches 功能
		webView.getSettings().setAppCacheEnabled(true);
		// 设置页面默认字体,android4.0以上系统用次方法，参数为百分比
		webView.getSettings().setTextZoom(100);
		//	设置网络图片延迟加载
		webView.getSettings().setBlockNetworkImage(true);
		// 截屏设置
		webView.setDrawingCacheEnabled(true);
		webView.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
		
		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);
		

		if (Build.VERSION.SDK_INT >= 19) {
			webView.getSettings().setLoadsImagesAutomatically(true);
		} else {
			webView.getSettings().setLoadsImagesAutomatically(false);
		}

		webView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				return true;
			}
		});
		
		MyApplication app = (MyApplication) ctx.getApplicationContext();
		if (Const.DEBUG_MODE ) {
			showLoadingProgress = true;
		}
	}
	
	public Bitmap captureWebView(){
		Picture snapShot = webView.capturePicture();
		Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),snapShot.getHeight(), Bitmap.Config.ARGB_8888);  
	    Canvas canvas = new Canvas(bmp);  
	    snapShot.draw(canvas);  
	    return bmp;  
	}
	
	public Bitmap captureCurrentWebView(){
		if(webView.getDrawingCache() != null){
			webView.destroyDrawingCache();
		}
		return webView.getDrawingCache();
	}

	public void excureJs(String callback) {
		this.webView.loadUrl("javascript:" + callback);
	}

	public void setCustomWebViewListener(CustomWebViewListener customWebViewListener) {

		this.customWebViewListener = customWebViewListener;
	}
	
	/**
	 * @Description 判断Webview是否有上一页面
	 * @return
	 */
	public boolean canGoBack(){
		return this.webView.canGoBack();
	}

	/**
	 * @Description 控制WebView返回上一页面
	 */
	public void goBack(){
		this.webView.goBack();
	}
	
	public void loadUrl(String url) {

		String system = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:sss");
//		Log.i("webview", "start:" + system+"  url:"+url);

		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d("webView", url);
				if(url.contains("package=com.dianping.v1")){
				}
				else{
					view.loadUrl(url);
				}
				return true;
			}
			

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				progressBar.setVisibility(View.VISIBLE);
				webView.setVisibility(View.INVISIBLE);

				String system = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:sss");
				Log.i("webview", "start2:" + system);
			}

			@Override
			public void onPageFinished(WebView view, String url) {

				String system = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:sss");
				Log.i("webview", "end1:" + system);

				if (!webView.getSettings().getLoadsImagesAutomatically()) {
					webView.getSettings().setLoadsImagesAutomatically(true);
				}
				
				if (webView.getSettings().getBlockNetworkImage()){
					webView.getSettings().setBlockNetworkImage(false);
				}
				// 保存cookie
//				CookieManager cookieManager = CookieManager.getInstance();
//				String CookieStr = cookieManager.getCookie(url);
//				BaseApplication app = (BaseApplication) ctx.getApplicationContext();
////				if (app.getSessionId().equals(Const.STR_BLANK)) {
//					app.setSessionId(CookieStr);
////				}
				
				if (showLoadingProgress) {
//					DialogUtil.closeProgressDialog();
				}

				progressBar.setVisibility(View.INVISIBLE);
				webView.setVisibility(View.VISIBLE);
				customWebViewListener.onPageFinished();
			}

			@Override
			public void onReceivedError(final WebView view, int errorCode, String description, final String failingUrl) {
				view.loadUrl("about:blank");
				errorLL.setVisibility(View.VISIBLE);
				refreshBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						errorLL.setVisibility(View.GONE);
						view.loadUrl(failingUrl);
					}
				});
				if (showLoadingProgress) {
//					DialogUtil.closeProgressDialog();
				}
			}
		});
		webView.setWebChromeClient(new CustomChromeClient("Android", BaseWebFunction.class));

		// 同步cookie
//		 CookieUtil.synCookies(ctx, url);

		// 加载URL
		load(url);
	}

	public void reload() {
		webView.reload();
	}

	private void load(final String url) {
		GenericTask webLoadTask = new GenericTask(ctx, false);
		webLoadTask.setBaseAsyncTaskListener(new GenericTaskListener() {

			private boolean flag = true;
			private int count = 0;

			@Override
			public void doInBackground() {
				while (flag && count < 10) {
					try {
						Thread.sleep(500);
						CookieManager cookieManager = CookieManager.getInstance();
						cookieManager.setAcceptCookie(true);
						String cookie = cookieManager.getCookie(url);
						if (cookie != null) {
							flag = false;
						}
						count++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onPostExecute() {
				webView.loadUrl(url);
			}

			@Override
			public void onPreExecute() {

			}

			@Override
			public void onCancelled() {

			}
		});

		webLoadTask.execute();
	}

	public class CustomChromeClient extends InjectedChromeClient {

		public CustomChromeClient(String injectedName, Class<?> injectedCls) {
			super(injectedName, injectedCls);
		}

		@Override
		public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

//			DialogUtil.showMessageDialog(ctx, DialogUtil.TYPE_CORRECT, message);

			return super.onJsAlert(view, url, message, result);
		}

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			
			if (showLoadingProgress) {
				switch (newProgress) {
				case 0:
//					DialogUtil.showProgressDialog(ctx, "载入中", 1);
					break;

				default:
					Log.i("debug", "progress:" + newProgress);
//					DialogUtil.updateProgressDialog(newProgress);
					break;
				}
			}
		}

		@Override
		public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
			// to do your work
			// ...
			return super.onJsPrompt(view, url, message, defaultValue, result);
		}
	}
}