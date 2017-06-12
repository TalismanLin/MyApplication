package com.asiainfo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.animations.MainAnimationActivity;
import com.asiainfo.myapplication.animations.MainAnimationActivity$$ViewBinder;
import com.asiainfo.myapplication.launchGuide.activity.LauchGuideActivity;
import com.asiainfo.myapplication.mySubmit.MySubmitActivity;
import com.asiainfo.myapplication.percentView.PercentLayoutActivity;
import com.asiainfo.myapplication.pickView.PickViewActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{


    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.main_root)
    LinearLayout mMainRoot;

    private MainRecyclerAdapter mainAdapter;

    private Intent mIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSystemBar(this);
        ButterKnife.bind(this);
        ArrayList<String > mainItems = new ArrayList<String>();
        Collections.addAll(mainItems, getResources().getStringArray(R.array.main_items));
        mainAdapter = new MainRecyclerAdapter(this);
        mainAdapter.setData(mainItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mainAdapter);
    }

    public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder>{

        private ArrayList<String> data;
        private Context mContext;
        private LayoutInflater mInflater;

        public MainRecyclerAdapter(Context context) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<String> data){
            if(data != null){
                this.data = data;
            }
        }

        @Override
        public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainRecyclerViewHolder(mInflater.inflate(R.layout.item_text,parent , false));
        }

        @Override
        public int getItemCount() {
            return data==null?0:data.size();
        }

        @Override
        public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
            holder.mTextView.setText(data.get(position));
        }
    }


    public class MainRecyclerViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.item_tv)
        TextView mTextView;

        public MainRecyclerViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


//       <string-array name="main_items">
//       0 <item>@string/btn_start</item>
//       1 <item>@string/btn_map</item>
//       2 <item>@string/btn_percent</item>
//       3 <item>@string/btn_submit</item>
//       4 <item>HoloColorPicker</item>
//       5 <item>LoadingView</item>
//       6 <item>PickerView</item>
//       7 <item>ViewSwitcher</item>
//       8 <item>ShareSDK</item>
//       9 <item>dataSwitch</item>
//       10 <item>BrowserStart</item>
//       11 <item>Animations</item>
//       12 <item>SimpleWebView</item>
//        </string-array>
        @OnClick(R.id.item_tv)
        void onItemClic(){
            switch (getAdapterPosition()){
                case 0:
                    transitionTo(LauchGuideActivity.class);
                    break;
                case 1:
                    break;
                case 2:
                    transitionTo(PercentLayoutActivity.class);
                    break;
                case 3:
                    transitionTo(MySubmitActivity.class);
                    break;
                case 6:
                    transitionTo(PickViewActivity.class);
                    break;
                case 11:
                    transitionTo(MainAnimationActivity.class);
                    break;
            }
        }
    }

    private void transitionTo(Class clazz){
        mIntent = new Intent(MainActivity.this, clazz);
        mIntent.putExtra(BaseActivity.FROM_PAGE,"HOME");
        startActivity(mIntent);
    }
}
