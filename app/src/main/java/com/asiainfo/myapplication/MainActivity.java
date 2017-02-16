package com.asiainfo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.animations.AnimationActivity;
import com.asiainfo.myapplication.animations.MainAnimationActivity;
import com.asiainfo.myapplication.launchGuide.activity.LauchGuideActivity;
import com.asiainfo.myapplication.mySubmit.MySubmitActivity;
import com.asiainfo.myapplication.percentView.PercentLayoutActivity;
import com.asiainfo.myapplication.pickView.PickViewActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recyler_view)
    RecyclerView mRecylerView;

    MainRecycleViewAdapter mainAdapter;
    @Bind(R.id.main_layout)
    LinearLayout mMainLayout;
    private Intent mIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ArrayList<String> mainItems = new ArrayList<String>();
        Collections.addAll(mainItems, getResources().getStringArray(R.array.main_items));
        mainAdapter = new MainRecycleViewAdapter(this);
        mainAdapter.setData(mainItems);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(mainAdapter);
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.MainRecyclerViewHolder> {

        private ArrayList<String> data;
        private final LayoutInflater mInflater;
        private final Context mContext;


        public MainRecycleViewAdapter(Context context) {
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<String> data) {
            if (data == null) {
                data = new ArrayList<String>();
            }
            this.data = data;
        }

        @Override
        public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainRecyclerViewHolder(mInflater.inflate(R.layout.item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
            holder.mainTv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_tv)
            TextView mainTv;

            public MainRecyclerViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            @OnClick(R.id.item_tv)
            void onItemClick() {
//                Snackbar.make(mMainLayout,String.valueOf(getAdapterPosition()),Snackbar.LENGTH_LONG).show();
                switch (getAdapterPosition()){
                    case 0:
                        startActivity(LauchGuideActivity.class);
                        break;
                    case 1:
                        break;
                    case 2:
                        startActivity(PercentLayoutActivity.class);
                        break;
                    case 3:
                        startActivity(MySubmitActivity.class);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        // pickerview
                        startActivity(PickViewActivity.class);
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        startActivity(MainAnimationActivity.class);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void startActivity(Class clazz){
        mIntent = new Intent(MainActivity.this, clazz);
        mIntent.putExtra(BaseActivity.FROM_PAGE,"HOME");
        startActivity(mIntent);
    }

}
