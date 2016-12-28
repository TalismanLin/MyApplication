/**
 * Package:com.jialin.percentView
 * Author:Zhu JL
 * Date:2015��10��25��
 */
package com.asiainfo.myapplication.percentView;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.ButterKnife;

/**
 * @author Zhu JL
 *
 */
public class PercentLayoutActivity extends BaseActivity {

	@ViewInject(R.id.listView)
	private ListView mListView;
	
	
	private LayoutInflater mInflater;
	
	
	private String[] mTitles = {
			
            "PercentW or PercentH",
            "PercentRelativeLayout 1",
            "PercentFrameLayout",
            "PercentRelativeLayout 2",
            "PercentLinearLayout",
            "PercentLinearLayout in ScrollView2",
            "PercentInListView",
            "PercentPadding",
            "PercentScreen[Width|Height]"
	};
	
	
	private int[] mContents = {
			R.layout.percent_view1,
			R.layout.percent_view2,
			R.layout.percent_view3,
			R.layout.percent_view4,
			R.layout.percent_view5,
			R.layout.percent_view6,
			R.layout.percent_view7,
			R.layout.percent_view8,
			R.layout.percent_view9
	};
	/* 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_percent_main);
		setContentView(R.layout.blank_list);
		ButterKnife.bind(this);
		mInflater = LayoutInflater.from(this);
		mListView.setAdapter(new ArrayAdapter<String>(this, -1, mTitles){
			/* 
			 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
			 */
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if( convertView == null){
					convertView = mInflater.inflate(R.layout.item_category, parent, false);
				}
				TextView tv = (TextView) convertView.findViewById(R.id.id_title);
				tv.setText(mTitles[position]);
				return convertView;
			}
		});
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(PercentLayoutActivity.this,ItemActivity.class);
				mIntent.putExtra("contentId", mContents[position]);
				mIntent.putExtra("title", mTitles[position]);
				startActivity(mIntent);
			}
		});
	}
	
}
