/**
 * Package:com.jialin.percentView
 * Author:Zhu JL
 * Date:2015��10��27��
 */
package com.asiainfo.myapplication.percentView;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.asiainfo.myapplication.R;

/**
 * @author Zhu JL
 *
 */
public class ItemActivity extends FragmentActivity{

	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		int contentId = this.getIntent().getIntExtra("contentId", R.layout.activity_item);
		String title = this.getIntent().getStringExtra("title");
		setContentView(contentId);
		setTitle(title);
	}
}
