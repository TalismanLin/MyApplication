/**
 * Package:com.jialin.launchGuide.activity.viewPage
 * Author:Zhu JL
 * Date:2015��10��19��
 */
package com.asiainfo.myapplication.launchGuide.activity.viewPage;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhu JL
 *
 */
public class ViewPagerAdapter extends PagerAdapter{
	
	private List<ImageView> views;
	
	
	
	
	//	 Constructor
	public ViewPagerAdapter(List<ImageView> views){
		if( views == null){
			this.views = new ArrayList<ImageView>();
		}
		else {
			this.views = views;
		}
	}
	
	
	/* 
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	
	/* 
	 * @see android.support.v4.view.PagerAdapter#finishUpdate(android.view.View)
	 */
	@Override
	public void finishUpdate(View container) {
	}
	
	
	/* 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(views != null){
			return views.size();
		}
		else 
			return 0;
	}
	
	
	/* 
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.View, int)
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position), 0 );
		
		return views.get(position);
	}
	

	/* 
	 * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}

	
	/* 
	 * @see android.support.v4.view.PagerAdapter#saveState()
	 */
	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* 
	 * @see android.support.v4.view.PagerAdapter#startUpdate(android.view.View)
	 */
	@Override
	public void startUpdate(View container) {
		
	}
}
