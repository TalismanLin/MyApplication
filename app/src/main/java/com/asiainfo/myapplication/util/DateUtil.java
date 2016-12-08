package com.asiainfo.myapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	private DateUtil() {
    }

    /**
     * 获取Calendar型系统时间
     * 
     * @return Calendar 系统时间
     */
    public static Calendar getSFCal() {
        return Calendar.getInstance();
    }

    /**
     * 获取Date型系统时间
     * 
     * @return Date 系统时间
     */
    public static Date getSysDate() {
        return getSFCal().getTime();
    }

    /**
     * 字符串转日期型
     * 
     * @param dateStr 日期字符串
     * @param format 日期类型
     * @return 日期
     */
    public static Date parse(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期型转字符串
     * 
     * @param date 日期型日期
     * @param format 日期类型
     * @return 日期字符串
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }
}