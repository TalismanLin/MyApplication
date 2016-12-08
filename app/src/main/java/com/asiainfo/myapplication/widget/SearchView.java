package com.asiainfo.myapplication.widget;

import java.util.ArrayList;


import android.content.Context;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.asiainfo.myapplication.R;

/**
 * @author Zhu JL
 *
 */
public class SearchView extends RelativeLayout implements View.OnClickListener{

	
	private Context ctx;
	
	private EditText editInput;
	private ImageView ivSearchDelete;	
	
	private ListView lvType,lvContent;
	
	private SearchViewListener mListener;
	
	private ArrayAdapter<String> mTypeAdapter;
	private ArrayAdapter<String> mContentAdatper;	
	private ArrayAdapter<String> mAutoCompleteAdapter;
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SearchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 */
	public SearchView(Context ctx) {
		super(ctx);
		this.ctx = ctx;
		LayoutInflater.from(ctx).inflate(R.layout.search_layout, this);
		initViews();
	}
	
	private void initViews(){
		editInput = (EditText) findViewById(R.id.search_et_input);
		ivSearchDelete = (ImageView) findViewById(R.id.search_iv_delete);
		lvType = (ListView) findViewById(R.id.search_lv_tips);
		lvContent = (ListView) findViewById(R.id.search_lv_content);
		lvContent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(ctx, position, Toast.LENGTH_LONG).show();
			}
		});
		
		lvType.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(ctx, position, Toast.LENGTH_LONG).show();
			}
		});
		ivSearchDelete.setOnClickListener(this);
		
	}
	
	
	public void setContentHitAdapter(ArrayAdapter<String> adapter){
		this.mContentAdatper = adapter;
		if(lvContent.getAdapter() == null){
			lvContent.setAdapter(adapter);
		}
	}
	
	private void notifyStartSearching(String text){
		if(mListener != null){
			mListener.onSearch(editInput.getText().toString());
		}
		InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	public void setSearchViewListener(SearchViewListener listener){
		mListener = listener;
	}
	
	/* 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search_content_type:
			lvType.setVisibility(VISIBLE);
			lvContent.setVisibility(GONE);
			break;
		case R.id.search_et_input:
			lvType.setVisibility(GONE);
			lvContent.setVisibility(VISIBLE);
			break;
		default:
			break;
		}
	}
	
	
	private class EditChangedListener implements TextWatcher{

		/* 
		 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
		 */
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		/* 
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}

		/* 
		 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
		 */
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public interface SearchViewListener{
		
		void onRefreshAutoCompleted(String text);
		
		
		void onSearch(String text);
	}

}
