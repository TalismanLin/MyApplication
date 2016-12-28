/**
 * Package:com.jialin.loadingView
 * Author:Zhu JL
 * Date:2015��11��2��
 */
package com.asiainfo.myapplication.loadingView;



import android.os.Bundle;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

/**
 * @author Zhu JL
 *
 */
public class LoadingViewActivity extends BaseActivity {

	
	private MetaballView metaballView;
	
	private WhorlView whorlView1;
	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metalball);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		metaballView = (MetaballView) findViewById(R.id.metallball);
		metaballView.setPaintMode(1);
		
		whorlView1 = (WhorlView) findViewById(R.id.whorl1);
		whorlView1.start();
	}
}
