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
 * Created by Asiainfo-zhujl3 on 2017/2/10/010.
 */

public class PickViewActivity extends BaseActivity {

    private OptionsPopupWindow quantityPickView,addressPickView;
    private TimePopupWindow timePickerView;

    @Bind(R.id.btn1)
    Button mBtn1;
    @Bind(R.id.btn2)
    Button mBtn2;
    @Bind(R.id.btn3)
    Button mBtn3;
    @Bind(R.id.popup_tv)
    TextView mPopupTv;
    @Bind(R.id.popup_parent)
    LinearLayout mPopupParent;

    private ArrayList<String> optionsLevel1 = new ArrayList<String>();
    private ArrayList<ArrayList<String>> optionsLevel2 = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> optionsLevel3 = new ArrayList<ArrayList<ArrayList<String>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickview);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                initTimePicker();
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                initQuantityPickView();
                break;
        }
    }

    private void initQuantityPickView(){
        final ArrayList<String> quantityOptions = new ArrayList<String>();
        for (int i = 0;i<10;i++){
            quantityOptions.add(String.valueOf(i));
        }
        quantityPickView = new OptionsPopupWindow(this);
        quantityPickView.setPicker(quantityOptions);
        quantityPickView.setCyclic(false);
        quantityPickView.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String result = quantityOptions.get(options1);
                mPopupTv.setText(result);
            }
        });
        quantityPickView.showAtLocation(mPopupParent, Gravity.BOTTOM, 0, 0);
    }

    private void initTimePicker(){
        timePickerView = new TimePopupWindow(this, TimePopupWindow.Type.ALL);
        timePickerView.setCyclic(false);
        int[] start = new int[]{1989,7,1,4,18};
        int[] end = new int[]{2017,2,13,15,00};
        timePickerView.setRange(start,end);
        Date pick = new  Date(System.currentTimeMillis());
        timePickerView.setTime(pick);
        timePickerView.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                String time = date.toString();
                mPopupTv.setText(time);
            }
        });
        timePickerView.showAtLocation(mPopupParent, Gravity.BOTTOM, 0, 0);

    }
}
