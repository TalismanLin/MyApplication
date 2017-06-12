package com.pickerview.lib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.pickerview.R;
import com.pickerview.TimePopupWindow.Type;

import android.content.Context;
import android.util.Log;
import android.view.View;


public class WheelTime {
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	// 添加大小月月份并将其转换为list,方便之后的判断
	String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
	String[] months_little = { "4", "6", "9", "11" };
	final List<String> list_big = Arrays.asList(months_big);
	final List<String> list_little = Arrays.asList(months_little);

	
	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	
	private Type type;
	private static int START_YEAR = 1990, END_YEAR = 2100;
	private static int START_MONTH = 01, END_MONTH = 12;
	private static int START_DAY = 01, END_DAY = 31;
	private static int START_HOUR = 00, END_HOUR = 23;
	private static int START_MIN = 00, END_MIN = 59;
	
	private int[] currentPicker = new int[5];

	public View getView() {
		return view;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public static void setRange(int[] sSTART_TIME, int[] sEND_TIME){
		
		// year
		START_YEAR = sSTART_TIME[0];
		END_YEAR = sEND_TIME[0];
		// month
		START_MONTH = sSTART_TIME[1];
		END_MONTH= sEND_TIME[1];
		// day
		START_DAY= sSTART_TIME[2];
		END_DAY= sEND_TIME[2];
		// hour
		START_HOUR = sSTART_TIME[3];
		END_HOUR= sEND_TIME[3];
		// min
		START_MIN = sSTART_TIME[4];
		END_MIN= sEND_TIME[4];
	}

	public static int getSTART_YEAR() {
		return START_YEAR;
	}

	public static void setSTART_YEAR(int sTART_YEAR) {
		START_YEAR = sTART_YEAR;
	}

	public static int getEND_YEAR() {
		return END_YEAR;
	}

	public static void setEND_YEAR(int eND_YEAR) {
		END_YEAR = eND_YEAR;
	}
	
	public static int getSTART_MONTH() {
		return START_MONTH;
	}

	public static void setSTART_MONTH(int sTART_MONTH) {
		START_MONTH = sTART_MONTH;
	}

	public static int getEND_MONTH() {
		return END_MONTH;
	}

	public static void setEND_MONTH(int eND_MONTH) {
		END_MONTH = eND_MONTH;
	}

	public static int getSTART_DAY() {
		return START_DAY;
	}

	public static void setSTART_DAY(int sTART_DAY) {
		START_DAY = sTART_DAY;
	}

	public static int getEND_DAY() {
		return END_DAY;
	}

	public static void setEND_DAY(int eND_DAY) {
		END_DAY = eND_DAY;
	}

	public static int getSTART_HOUR() {
		return START_HOUR;
	}

	public static void setSTART_HOUR(int sTART_HOUR) {
		START_HOUR = sTART_HOUR;
	}

	public static int getEND_HOUR() {
		return END_HOUR;
	}

	public static void setEND_HOUR(int eND_HOUR) {
		END_HOUR = eND_HOUR;
	}

	public static int getSTART_MIN() {
		return START_MIN;
	}

	public static void setSTART_MIN(int sTART_MIN) {
		START_MIN = sTART_MIN;
	}

	public static int getEND_MIN() {
		return END_MIN;
	}

	public static void setEND_MIN(int eND_MIN) {
		END_MIN = eND_MIN;
	}

	public WheelTime(View view) {
		super();
		this.view = view;
		type = Type.ALL;
		setView(view);
	}
	public WheelTime(View view,Type type) {
		super();
		this.view = view;
		this.type = type;
		setView(view);
	}
	
	public void setPicker(int year ,int month,int day){
		this.setPicker(year, month, day, 0, 0);
	}
	
	/**
	 * @Description:  弹出日期时间选择器
	 */
	public void setPicker(final int year ,final int month ,final int day,final int h,final int m) {

		Log.d("setPicker","year="+year+"  month="+month+"  day="+day+"  hour="+h+" min="+m);
		Context context = view.getContext();
		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setLabel(context.getString(R.string.year));// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
		currentPicker[0] = wv_year.getCurrentItem();

		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		if(START_YEAR == END_YEAR){
			wv_month.setAdapter(new NumericWheelAdapter(START_MONTH+1,END_MONTH+1));
			wv_month.setCurrentItem(month-START_MONTH);
		}else if (wv_year.getCurrentItem()==0){
			wv_month.setAdapter(new NumericWheelAdapter(START_MONTH+1,12));
			wv_month.setCurrentItem(month-START_MONTH);
		}else if (wv_year.getCurrentItem()+START_YEAR == END_YEAR){
			wv_month.setAdapter(new NumericWheelAdapter(1,END_MONTH+1));
			wv_month.setCurrentItem(month);
		}else{
			wv_month.setAdapter(new NumericWheelAdapter(1, 12));
			wv_month.setCurrentItem(month);
		}
		wv_month.setLabel(context.getString(R.string.month));		
		currentPicker[1] = wv_month.getCurrentItem();

		// 日
		wv_day = (WheelView) view.findViewById(R.id.day);
		int maxDay = getMaxDayOfMonth();
		if(START_YEAR == END_YEAR && START_MONTH == END_MONTH){
			wv_day.setAdapter(new NumericWheelAdapter(START_DAY,END_DAY));
			wv_day.setCurrentItem(day-START_DAY);
		}else if(wv_year.getCurrentItem() == 0 && wv_month.getCurrentItem() == 0){
			wv_day.setAdapter(new NumericWheelAdapter(START_DAY,maxDay));
			wv_day.setCurrentItem(day-START_DAY);
		}else if(wv_year.getCurrentItem()+START_YEAR == END_YEAR && wv_month.getCurrentItem()==END_MONTH){
			wv_day.setAdapter(new NumericWheelAdapter(1,END_DAY));
			wv_day.setCurrentItem(day);
		}else{
			wv_day.setAdapter(new NumericWheelAdapter(1,maxDay));
			wv_day.setCurrentItem(day);
		}
		wv_day.setLabel(context.getString(R.string.day));
		currentPicker[2] = wv_day.getCurrentItem();

		// 时
		wv_hours = (WheelView)view.findViewById(R.id.hour);
		if(START_YEAR == END_YEAR && START_MONTH == END_MONTH && START_DAY == END_DAY){
			wv_hours.setAdapter(new NumericWheelAdapter(START_HOUR,END_HOUR));
			wv_hours.setCurrentItem(h-START_HOUR);
		}else if(wv_year.getCurrentItem() == 0 && wv_month.getCurrentItem() == 0 && wv_day.getCurrentItem() == 0){
			wv_hours.setAdapter(new NumericWheelAdapter(START_HOUR,23));
			wv_hours.setCurrentItem(h-START_HOUR);
		}else if(wv_year.getCurrentItem()+START_YEAR == END_YEAR && wv_month.getCurrentItem()+START_MONTH == END_MONTH
					&& wv_day.getCurrentItem()+START_DAY == END_DAY){
			wv_hours.setAdapter(new NumericWheelAdapter(0,END_HOUR));
			wv_hours.setCurrentItem(h);
		}else{
			wv_hours.setAdapter(new NumericWheelAdapter(0,23));
			wv_hours.setCurrentItem(h);
		}
		wv_hours.setLabel(context.getString(R.string.hours));// 添加文字
		currentPicker[3] = wv_hours.getCurrentItem();
		
		// 分
		wv_mins = (WheelView)view.findViewById(R.id.min);
		if(START_YEAR == END_YEAR && START_MONTH == END_MONTH && START_DAY == END_DAY 
				&& START_HOUR == END_HOUR){
			wv_mins.setAdapter(new NumericWheelAdapter(START_MIN,END_MIN));
			wv_mins.setCurrentItem(m-START_MIN);
		}else if (wv_year.getCurrentItem() == 0 && wv_month.getCurrentItem() == 0 && wv_day.getCurrentItem() == 0 
					&& wv_hours.getCurrentItem() == 0){
			wv_mins.setAdapter(new NumericWheelAdapter(START_MIN,59));
			wv_mins.setCurrentItem(m-START_MIN);
		}else if (wv_year.getCurrentItem()+START_YEAR == END_YEAR && wv_month.getCurrentItem() + START_MONTH == END_MONTH
					&& wv_day.getCurrentItem() + START_DAY == END_DAY && wv_hours.getCurrentItem() + START_HOUR == END_HOUR){
			wv_mins.setAdapter(new NumericWheelAdapter(0,END_MIN));
			wv_mins.setCurrentItem(m);
		}else {
			wv_mins.setAdapter(new NumericWheelAdapter(0,59));
			wv_mins.setCurrentItem(m);
		}
		wv_mins.setLabel(context.getString(R.string.minutes));// 添加文字
		currentPicker[4] = wv_mins.getCurrentItem();
		
		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 先设置月份的显示
				if(START_YEAR == END_YEAR){
					wv_month.setAdapter(new NumericWheelAdapter(START_MONTH,END_MONTH));
					wv_month.setCurrentItem(month-START_MONTH);
				}
				if(year_num == START_YEAR){
					wv_month.setAdapter(new NumericWheelAdapter(START_MONTH+1,12));
					wv_month.setCurrentItem(0);
				}else if(year_num == END_YEAR){
					wv_month.setAdapter(new NumericWheelAdapter(1,END_MONTH+1));
					wv_month.setCurrentItem(END_MONTH);
				}
				else {
					wv_month.setAdapter(new NumericWheelAdapter(1,12));
				}
				// 设置日的显示
				int maxDay = getMaxDayOfMonth();
				if(START_YEAR == END_YEAR && START_MONTH == END_MONTH){
					wv_day.setAdapter(new NumericWheelAdapter(START_DAY,END_DAY));
					wv_day.setCurrentItem(day);
				}
				if(year_num == START_YEAR && wv_month.getCurrentItem() == 0){
					wv_day.setAdapter(new NumericWheelAdapter(START_DAY,maxDay));
					wv_day.setCurrentItem(day-START_DAY);
				}else if (year_num == END_YEAR && wv_month.getCurrentItem() + START_MONTH == END_MONTH){
					wv_day.setAdapter(new NumericWheelAdapter(1,END_DAY));
					wv_day.setCurrentItem(day);
				}else{
					wv_day.setAdapter(new NumericWheelAdapter(1,maxDay));
				}
				
				// 设置时的显示
				if(START_YEAR == END_YEAR && START_MONTH == END_MONTH && START_DAY == END_DAY){
					wv_hours.setAdapter(new NumericWheelAdapter(START_HOUR,END_HOUR));
					wv_hours.setCurrentItem(h-START_HOUR);
				}else if(wv_year.getCurrentItem() == 0 && wv_month.getCurrentItem() == 0 && wv_day.getCurrentItem() == 0){
					wv_hours.setAdapter(new NumericWheelAdapter(START_HOUR,23));
					wv_hours.setCurrentItem(h-START_HOUR);
				}else if(year_num == END_YEAR && wv_month.getCurrentItem()+START_MONTH == END_MONTH
							&& wv_day.getCurrentItem()== END_DAY){
					wv_hours.setAdapter(new NumericWheelAdapter(0,END_HOUR));
					wv_hours.setCurrentItem(0,true);
				}else{
					wv_hours.setAdapter(new NumericWheelAdapter(0,23));
				}
				// 设置分的显示
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 设置day
				if(START_YEAR == END_YEAR && START_MONTH == END_MONTH){
					wv_day.setAdapter(new NumericWheelAdapter(START_DAY,END_DAY));
				}else if(wv_year.getCurrentItem()==0 && month_num == START_MONTH){
					// 
					wv_day.setAdapter(new NumericWheelAdapter(START_DAY,getMaxDayOfMonth()));
					wv_day.setCurrentItem(0);
				}else if((wv_year.getCurrentItem()+START_YEAR) == END_YEAR && wv_month.getCurrentItem() == END_MONTH){
					wv_day.setAdapter(new NumericWheelAdapter(1,END_DAY));
					wv_day.setCurrentItem(0);
				}else{
					wv_day.setAdapter(new NumericWheelAdapter(1,getMaxDayOfMonth()));
					if(wv_day.getCurrentItem()+1>=getMaxDayOfMonth()){
						wv_day.setCurrentItem(getMaxDayOfMonth()-1, true);
					}
				}
				// 设置时
				
				// 设置分
			}
		};
		
		// “日”监听
		OnWheelChangedListener wheelListener_day = new OnWheelChangedListener() {
			
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int day_num = newValue + 1;
				// 设置时
				if(START_YEAR == END_YEAR && START_MONTH==END_MONTH && START_DAY==END_DAY){
					wv_hours.setAdapter(new NumericWheelAdapter(START_HOUR,END_HOUR));
				}else if (wv_year.getCurrentItem() == 0 && wv_month.getCurrentItem()==0){
					wv_hours.setAdapter(new NumericWheelAdapter(START_HOUR,23));
					wv_hours.setCurrentItem(0,true);
				}else if(wv_year.getCurrentItem()+START_YEAR == END_YEAR && wv_month.getCurrentItem()+START_MONTH == END_MONTH){
					wv_hours.setAdapter(new NumericWheelAdapter(0,END_HOUR));
					wv_hours.setCurrentItem(0, true);
				}else {
					wv_hours.setAdapter(new NumericWheelAdapter(0,23));
				}
				// 设置分
			}
		};
		
		// "时"监听
		OnWheelChangedListener wheelListener_hour = new OnWheelChangedListener() {
			
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// 设置分
			}
		};
		
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);
		wv_day.addChangingListener(wheelListener_day);
		wv_hours.addChangingListener(wheelListener_hour);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		switch(type){
		case ALL:
			textSize = (screenheight / 100) * 3;
			break;
		case YEAR_MONTH_DAY:
			textSize = (screenheight / 100) * 4;
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
			break;
		case HOURS_MINS:
			textSize = (screenheight / 100) * 4;
			wv_year.setVisibility(View.GONE);
			wv_month.setVisibility(View.GONE);
			wv_day.setVisibility(View.GONE);
			break;
		case MONTH_DAY_HOUR_MIN:
			textSize = (screenheight / 100) * 3;
			wv_year.setVisibility(View.GONE);
			break;
		case YEAR_MONTH:
			textSize = (screenheight / 100) * 4;
			wv_day.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
			wv_hours.setVisibility(View.GONE);
			break;
		}
			
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}
	
	/**
	 * 获取当月最大日数
	 * */
	public int getMaxDayOfMonth(){
		String cur_month = wv_month.getItem();
		if (list_big.contains(cur_month)) {
			return 31;
		} else if (list_little.contains(cur_month)) {
			return 30;
		} else {
			if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
					.getCurrentItem() + START_YEAR) % 100 != 0)
					|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
				return 29;
			else
				return 28;
		}
	}

	/**
	 * 设置是否循环滚动
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic){
		wv_year.setCyclic(cyclic);
		wv_month.setCyclic(cyclic);
		wv_day.setCyclic(cyclic);
		wv_hours.setCyclic(cyclic);
		wv_mins.setCyclic(cyclic);
	}
	public String getTime() {
		StringBuffer sb = new StringBuffer();
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
			.append((wv_month.getItem())).append("-")
			.append((wv_day.getItem())).append(" ")
			.append(wv_hours.getItem()).append(":")
			.append(wv_mins.getItem());
		return sb.toString();
	}
	
	
	public String getTime(String timeFormat){
		StringBuffer sb = null;
		if(dateFormat.equals(timeFormat)){
			return getTime();
		}else if("yyyyMMDD".equals(timeFormat)){
			sb = new StringBuffer();
			sb.append((wv_year.getCurrentItem()+START_YEAR))
				.append((wv_month.getCurrentItem()+1))
				.append((wv_day.getCurrentItem()+1));
			return sb.toString();
		}else if("yyyyMMDDHHMM".equals(timeFormat)){
			sb = new StringBuffer();
			sb.append((wv_year.getCurrentItem() + START_YEAR))
				.append((wv_month.getCurrentItem() + 1))
				.append((wv_day.getCurrentItem() + 1))
				.append(wv_hours.getCurrentItem())
				.append(wv_mins.getCurrentItem());
			return sb.toString();
		}else{
			return getTime();
		}
	}

	
}
