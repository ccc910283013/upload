package com.ewell.upload.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 返回当前日期时间字符串<br>
     * 默认格式:yyyy-mm-dd hh:mm:ss
     *
     * @return String 返回当前字符串型日期时间
     */
    public static String getCurrentTime() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }


    /**
     * 返回自定义格式的当前日期时间字符串
     *
     * @param format
     *            格式规则
     * @return String 返回当前字符串型日期时间
     */
    public static String getCurrentTime(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }



    public static String timeStampToDate(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    public static String timeStampToDate(long timeStamp,String format) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取一个日期加上天数得到最后的日期
     *
     * @param d   日期
     * @param day 加的天数 如果天数是负数，即是减
     * @return 当前日期是星期几
     */
    public static Date addDate(Date d, long day) {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }

    /**
     *
     * @param d  日期
     * @param second 加的秒数
     * @return 当前日期
     */
    public static Date addSecondToDate(Date d, long second) {
        long time = d.getTime();
        second = second * 1000;
        time += second ;
        return new Date(time);
    }
}
