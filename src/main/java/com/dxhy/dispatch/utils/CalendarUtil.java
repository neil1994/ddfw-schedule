package com.dxhy.dispatch.utils;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期转换工具类
 * 
 * @author Acmen
 */
public class CalendarUtil {

    /** 带分隔符日期格式 */
    private static final String DATE_WITH_SPLIT = "yyyy-MM-dd";

    /** 不带分隔符日期格式 */
    private static final String DATE_WITHNOT_SPLIT = "yyyyMMdd";

    /** 带分隔符时间格式 */
    private static final String DATETIME_WITH_SPLIT = "yyyy-MM-dd HH:mm:ss";

    /** 不带分隔符时间格式 */
    private static final String DATETIME_WITHNOT_SPLIT = "yyyyMMddHHmmss";

    /**
     * 将日期转换成字符串
     * 
     * @param dateTime
     *            日期
     * @param withSplit
     *            true返回yyyy-MM-dd HH:mm:ss||false返回yyyyMMddHHmmss
     * @return 日期字符串
     */
    public static String convertDateTimeToString(Date dateTime, boolean withSplit) {
        SimpleDateFormat dateFormat;
        if (withSplit) {
            dateFormat = new SimpleDateFormat(DATETIME_WITH_SPLIT);
        }
        else {
            dateFormat = new SimpleDateFormat(DATETIME_WITHNOT_SPLIT);
        }
        return dateFormat.format(dateTime);
    }

    /**
     * 将日期转换成字符串
     * 
     * @param date
     *            日期
     * @param withSplit
     *            true返回yyyy-MM-dd||false返回yyyyMMdd
     * @return 日期字符串
     */
    public static String convertDateToString(Date date, boolean withSplit) {
        SimpleDateFormat dateFormat;
        if (withSplit) {
            dateFormat = new SimpleDateFormat(DATE_WITH_SPLIT);
        }
        else {
            dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        }
        return dateFormat.format(date);
    }

    /**
     * 把格式为(yyyy-MM-dd或yyyyMMdd)的字符串转换为Date型(不带时分秒)
     * 
     * @param date
     *            字符串日期
     * @return Date型日期(失败返回null)
     */
    public static Date convertStringToDate(String date) {
        int length = date.length();
        SimpleDateFormat dateFormat;
        if (8 == length || 10 == length) {
            if (10 == length) {
                dateFormat = new SimpleDateFormat(DATE_WITH_SPLIT);
            }
            else {
                dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
            }
            ParsePosition position = new ParsePosition(0);
            return dateFormat.parse(date, position);
        }
        else {
            if (19 == length || 14 == length) {
                return convertStringToDateTime(date);
            }
            else {
                return null;
            }
        }
    }

    /**
     * 把格式为(yyyy-MM-dd hh:mm:ss或yyyyMMddHHmmss)的字符串转换为DateTime型(带时分秒)
     * 
     * @param date
     *            字符串日期
     * @return DateTime型日期(失败返回null)
     */
    public static Date convertStringToDateTime(String date) {
        int length = date.length();
        SimpleDateFormat dateFormat;
        if (19 == length || 14 == length) {
            if (19 == length) {
                dateFormat = new SimpleDateFormat(DATETIME_WITH_SPLIT);
            }
            else {
                dateFormat = new SimpleDateFormat(DATETIME_WITHNOT_SPLIT);
            }
            ParsePosition position = new ParsePosition(0);
            return dateFormat.parse(date, position);
        }
        else {
            if (8 == length || 10 == length) {
                return convertStringToDate(date);
            }
            else {
                return null;
            }
        }
    }

    /**
     * 取得当前时间（ yyyy-MM-dd 00:00:00）
     * 
     * @return Date 无时分秒时间
     */
    public static Date getCurrDate() {
        return convertStringToDate(convertDateToString(new Date(), true));
    }

    /**
     * 取得当前时间（ yyyy-MM-dd HH:mm:ss）
     * 
     * @return 含时分秒时间
     */
    public static Date getCurrDateTime() {
        return new Date();
    }

    /**
     * 日期比较大小
     * 
     * @param date1
     *            日期
     * @param date2
     *            日期
     * @param canEq
     *            是否可以相等
     * @return date1-date2:true(大于)||false(小于)
     */
    public static boolean dateCompare(Date date1, Date date2, boolean canEq) {
        boolean result = false;
        long time = 1000 * 3600 * 24;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        Date firstDate = convertStringToDate(dateFormat.format(date1));
        Date lastDate = convertStringToDate(dateFormat.format(date2));
        long dateRange = (firstDate.getTime() - lastDate.getTime()) / time;
        if (canEq) {
            if (dateRange >= 0) {
                result = true;
            }
        }
        else {
            if (dateRange > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 判断是否为同一天
     * 
     * @param date1
     *            日期
     * @param date2
     *            日期
     * @return true||false
     */
    public static boolean sameDayCheck(Date date1, Date date2) {
        boolean result = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        if (dateFirst.equals(dateLast)) {
            result = true;
        }
        return result;
    }

    /**
     * 日期加N天
     * 
     * @param date
     *            日期
     * @param day
     *            要加的天数
     * @return 加完后的日期
     */
    @SuppressWarnings("static-access")
    public static Date dateAdd(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 取unix时间戳
     * 
     * @param date
     *            日期
     * @return Long 时间戳
     */
    public static Long getUnixtime(Date date) {
        Date dateTime = convertStringToDateTime(convertDateTimeToString(date, true));
        return dateTime.getTime() / 1000;
    }

    /**
     * 取系统当前时间的毫秒数
     * 
     * @return Long 毫秒数
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    /**
     * 参数时间和当前数据差
     * 
     * @return Long 毫秒数
     */
    public static long arithDate(String sendTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        FastDateFormat df12 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        String date = df12.format(new Date());// 当前时间

        Date nowDate;
        Date d2;
        try {
            nowDate = df.parse(date);
            d2 = df.parse(sendTime);
            return (nowDate.getTime() - d2.getTime()) / 1000;
        }
        catch (Exception e) {
            // e.printStackTrace();
            return -999;
        }
    }
}
