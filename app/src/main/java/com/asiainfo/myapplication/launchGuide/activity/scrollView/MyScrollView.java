/**
 * 
 */
package com.asiainfo.myapplication.launchGuide.activity.scrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author Zhu JL
 *
 */
public class MyScrollView extends ScrollView{
	
	private OnScrollChangedListener onScrollChangedListener;
	
	/**
	 * 
	 */
	public MyScrollView(Context context) {
		super(context);
	}
	/**
	 * @param context
	 * @param attrs
	 */
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.view.View#onScrollChanged(int, int, int, int)
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		
	}
}
