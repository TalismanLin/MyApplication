package com.asiainfo.myapplication.pickView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Talisman on 2017/6/11.
 */

public class PickViewActivity extends BaseActivity {
    @Bind(R.id.btn_date)
    Button mBtnDate;
    @Bind(R.id.btn_address)
    Button mBtnAddress;
    @Bind(R.id.btn_num)
    Button mBtnNum;
    @Bind(R.id.pick_root)
    LinearLayout mPickRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_date, R.id.btn_address, R.id.btn_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_date:
                break;
            case R.id.btn_address:
                break;
            case R.id.btn_num:
                break;
        }
    }
}
