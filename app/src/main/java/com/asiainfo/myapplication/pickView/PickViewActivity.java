package com.asiainfo.myapplication.pickView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiainfo.myapplication.BaseActivity;
import com.asiainfo.myapplication.R;
import com.pickerview.OptionsPopupWindow;
import com.pickerview.TimePopupWindow;

import java.util.ArrayList;
import java.util.Date;

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
    @Bind(R.id.result)
    TextView mResult;

    private OptionsPopupWindow quantityPickView, addressPickView;
    private TimePopupWindow timePickView;

    private ArrayList<String> optionLevel1 = new ArrayList<String>();
    private ArrayList<ArrayList<String>> optionLevel2 = new ArrayList<ArrayList<String>>();


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
                initTimePickView();
                break;
            case R.id.btn_address:
                break;
            case R.id.btn_num:
                initQuantityPickView();
                break;
        }
    }

    private void initQuantityPickView() {
        final ArrayList<String> quantityOptions = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            quantityOptions.add(String.valueOf(i));
        }
        quantityPickView = new OptionsPopupWindow(this);
        quantityPickView.setPicker(quantityOptions);
        quantityPickView.setCyclic(false);
        quantityPickView.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String result = quantityOptions.get(options1);
                mResult.setText(result);
            }
        });
        quantityPickView.showAtLocation(mPickRoot, Gravity.BOTTOM, 0, 0);
    }

    private void initTimePickView() {
        timePickView = new TimePopupWindow(this, TimePopupWindow.Type.ALL);
        timePickView.setCyclic(false);
        int[] start = new int[]{1989,7,1,4,18};
        int[] end = new int[]{2018,6,12,20,23};
        timePickView.setRange(start,end);
        Date pick = new Date(System.currentTimeMillis());
        timePickView.setTime(pick);
        timePickView.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                String time = date.toString();
                mResult.setText(time);
            }
        });
        timePickView.showAtLocation(mPickRoot, Gravity.BOTTOM, 0, 0);
    }

    private void initAddressPickView() {

    }
}
