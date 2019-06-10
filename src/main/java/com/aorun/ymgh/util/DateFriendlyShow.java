package com.aorun.ymgh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  * 日期处理工具类  *   * @author whm  *  
 */
public class DateFriendlyShow {
    public static final String YMD = "yyyyMMdd";
    public static final String YMD_YEAR = "yyyy";
    public static final String YMD_BREAK = "yyyy-MM-dd";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String YMDHMS_BREAK = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS_BREAK_HALF = "yyyy年MM月dd日 HH:mm";
    public static final String HHmm = "HH:mm";
    /**
     * 计算时间差
     */
    public static final int CAL_MINUTES = 1000 * 60;
    public static final int CAL_HOURS = 1000 * 60 * 60;
    public static final int CAL_DAYS = 1000 * 60 * 60 * 24;


    /**
     * 获取当前时间格式化后的值  
     *
     * @param pattern
     * @return
     */
    public static String getNowDateText(String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 获取日期格式化后的值  
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateText(Date date, String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串时间转换成Date格式  
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date, String pattern)
            throws ParseException {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    private static SimpleDateFormat getSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 获取时间戳
     *
     * @param date
     * @return
     */
    public static Long getTime(Date date) {
        return date.getTime();
    }

    /**
     * 计算时间差
     *
     * @param startDate
     * @param endDate
     * @param calType   计算类型,按分钟、小时、天数计算
     * @return
     */
    public static int calDiffs(Date startDate, Date endDate, int calType) {
        Long start = DateFriendlyShow.getTime(startDate);
        Long end = DateFriendlyShow.getTime(endDate);
        int diff = (int) ((end - start) / calType);
        return diff;
    }

    /**
     * 计算时间差值以某种约定形式显示  
     *
     * @param startDate
     * @param nowDate
     * @return 时间友好提示
     * 32分钟前
     * 1小时前
     * 昨天 18:30
     * 前天 12:30
     * 12月12日
     * 2019年12月23日 12:23
     */
    public static String timeDiffText(Date startDate, Date nowDate) {
        int calDiffs = DateFriendlyShow.calDiffs(startDate, nowDate,
                DateFriendlyShow.CAL_MINUTES);
        if (calDiffs <= 0) {
            return "刚刚";
        }
        if (calDiffs < 60) {
            return calDiffs + "分钟前";
        }
        calDiffs = DateFriendlyShow.calDiffs(startDate, nowDate, DateFriendlyShow.CAL_HOURS);
        if (calDiffs < 24) {
            return calDiffs + "小时前";
        }
        if (calDiffs < 48) {
            return "昨天 " + DateFriendlyShow.getDateText(startDate, DateFriendlyShow.HHmm);
        }
        if (calDiffs < 72) {
            return "前天 " + DateFriendlyShow.getDateText(startDate, DateFriendlyShow.HHmm);
        }
        return DateFriendlyShow.getDateText(startDate, DateFriendlyShow.YMDHMS_BREAK_HALF);
    }

    /**
     * 显示某种约定后的时间值,类似微信朋友圈发布说说显示的时间那种  
     *
     * @param date
     * @return
     */
    public static String showTimeText(Date date) {
        return DateFriendlyShow.timeDiffText(date, new Date());
    }


//	public static void main(String[] args) throws ParseException {
//		Date start = DateFriendlyShow.getDate("2019年03月26日 10:54",
//				DateFriendlyShow.YMDHMS_BREAK_HALF);
//		System.out.println(DateFriendlyShow.showTimeText(start));
//	}


}
