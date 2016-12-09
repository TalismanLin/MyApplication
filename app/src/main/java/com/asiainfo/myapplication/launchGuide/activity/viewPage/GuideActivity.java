/**
 * Package:com.jialin.launchGuide.activity.viewPage
 * Author:Zhu JL
 * Date:2015��10��19��
 */
package com.asiainfo.myapplication.launchGuide.activity.viewPage;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.MyApplication;
import com.asiainfo.myapplication.R;

/**
 * @author Zhu JL
 *
 */
public class GuideActivity extends BaseActivity implements OnClickListener, OnPageChangeListener{

	private MyApplication app;
	
	private ViewPager vPager;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	
	private final int[] pics = {R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5};
	
	private ImageView[] dots;
	
	private int currentIndex;
	
	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide_viewpager);
		
		app = (MyApplication) this.getApplication();
		app.pushActivity(this);
		
		views = new ArrayList<View>();
		
		initViews();
		vPager = (ViewPager) findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(views);	
		vPager.setAdapter(vpAdapter);
		vPager.setOnPageChangeListener(this);
		initDots();
	}
	
	
	
	private void initViews(){
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  
                LinearLayout.LayoutParams.WRAP_CONTENT);
		for (int i = 0 ; i < pics.length; i++){
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(mParams);
			iv.setImageResource(pics[i]);
			views.add(iv);
		}
		
	}
	
	
	private void initDots(){
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		
		dots = new ImageView[pics.length];
		
		for(int i = 0 ; i < pics.length ; i++){
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);
			dots[i].setOnClickListener(this);
			dots[i].setTag(i);
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);
		
	}
	
	
	private void setCurView(int position){
		
		if( position < 0 || position >pics.length)
			return; 
		else
			vPager.setCurrentItem(position);
	}
	
	
	private void setCurDot(int position){
		
		if( position < 0 || position > pics.length-1 || currentIndex == position){
			return;
		}
		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		
		currentIndex = position;
		
	}
	/* 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}

	/* 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	/* 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
	 */
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setCurDot(arg0);
	}
	/* 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);
		
	}
	
	
}
