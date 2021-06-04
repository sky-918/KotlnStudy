package com.example.kotlinstudy.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/4/7.
 */
public class DateUtils {

    /**
     * 时间戳转换成字符窜
     *
     * @param time time
     * @return time
     */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param time time
     * @return long
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取当天日期
     *
     * @return 返回当天日期字符串
     */
    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    /**
     * 获取昨天日期
     *
     * @return 返回昨天日期字符串
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * 用于判断是否是打开App的key值
     *
     * @return
     */
    public static String getTodayIndexBannerKey() {
        return getToday() + "_index_banner_key";
    }

    /**
     * 昨天是否打开App的key值
     *
     * @return
     */
    public static String getYesterdayIndexBannerKey() {
        return getYesterday() + "_index_banner_key";
    }
}
