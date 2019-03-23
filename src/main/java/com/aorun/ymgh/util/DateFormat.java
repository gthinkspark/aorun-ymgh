package com.aorun.ymgh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateFormat {

	/**
	 * 日期时间转换为日期时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateBeginToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return df.format(date);
	}

	/**
	 * 日期时间转换为日期时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString2(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日 HH:mm");
		return df.format(date);
	}

	/**
	 * 日期时间转换为日期时间字符串
	 *
	 * @param date
	 * @return
	 */
	public static String dateToString3(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return df.format(date);
	}



	/**
	 * 日期时间转换为日期时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	
	

	/***
	 * 转换到分钟
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToMinuteString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(date);
	}

	/***
	 * 转换到yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToMMddHHmmString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
		return df.format(date);
	}

	/***
	 * 转换到yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToyyyyMMddString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(date);
	}

	public static String dateToMMddEEEETime(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd EEEE");
		return df.format(date);
	}

	public static String timeToHHmmString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(date);
	}

	public static String dateToTimeString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(date);
	}

	public static String dateTimeToDateString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static String dateToMMddString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("MM.dd");
		return df.format(date);
	}

	public static Date stringToDate(String str) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	public static Date stringToDateMinute(String str) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	public static Date stringToDateDay(String str) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	public static Date stringToDay(String str) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 
	 * @Title: dateToMonth
	 * @Description: (获取当前年份月份)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToYearMonth(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMM");
		return dateTimeFormat.format(date);
	}

	public static String dateMonthDay(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd");
		return dateTimeFormat.format(date);
	}

	public static String dateToDay(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd");
		return dateTimeFormat.format(date);
	}

	public static String dateToMonth(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM");
		return dateTimeFormat.format(date);
	}

	public static String dateToHour(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH");
		return dateTimeFormat.format(date);
	}

	/**
	 * 
	 * @Title: dateToMonth
	 * @Description: (获取当前年份月份日)
	 * @param @param date
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String dateToMonthDay(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd");
		return dateTimeFormat.format(date);
	}

	public static String dateToHHmmssString(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HHmmss");
		return dateTimeFormat.format(date);
	}

	/**
	 * LG 将yyyyMMdd类型的数据转换成Date类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date yyyyMMddToDate(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/***
	 * 将yyyyMMddHHmmss类型的数据转换成Date类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date yyyyMMddHHmmssToDate(String str) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/***
	 * 年月日的字符串转换成日期格式
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDAYDate(String str) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param time
	 *            时间"HH:mm:ss"
	 * @return
	 */
	public static Date stringToDate(int year, int month, int day, String time) {
		String dateStr = year + "-" + month + "-" + day + " " + time;
		return stringToDate(dateStr);
	}

	public static Date getTodayDate(String time) {
		int y, m, d;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH) + 1;
		d = cal.get(Calendar.DATE);
		Date date = DateFormat.stringToDate(y, m, d, time);
		return date;
	}

	public static Date addDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();

	}

	public static String dateToHHmm(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(date);
	}



	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static String getWeekOfMonth(Date date) {
		String[] weekMonths = { "第一周", "第二周", "第三周", "第四周", "第五周", "第六周" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.WEEK_OF_MONTH) - 1;
		if (w < 0)
			w = 0;
		return weekMonths[w];
	}

	public static String getMonthOfYear(Date date) {
		String[] weekMonths = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.MONTH);
		if (w < 0)
			w = 0;
		return weekMonths[w];
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		date = calendar.getTime();
		return date;
	}

	public static Date getNextNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +2);
		date = calendar.getTime();
		return date;
	}

	public static Date changeMonth(Date date, Integer monthNum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthNum);
		date = calendar.getTime();
		return date;
	}
	/**
	 * 判断开始时间
	 * 
	 * @param seTime
	 * @return true 现在时间大于开始时间 fasle 现在时间小于开始时间
	 */
	public static boolean selectData(Date seTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		Calendar begin = Calendar.getInstance();
		begin.setTime(seTime);
		if (date.after(begin)) {
			return true;
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
		/*
		 * Date a = yyyyMMddHHmmssToDate("20150116164480");
		 * System.out.println(a.getTime());
		 */
		/*
		 * System.out.println(DateFormat.dateToMinuteString(new Date())); String
		 * time = DateFormat.timeToDateString(new Date());
		 * System.out.println(time); int codeId
		 * =time.compareTo("12:00:00");//大于12点codeId大于0 小于12点codeId小于0
		 * System.out.println(codeId);
		 */
		// System.out.println(DateFormat.dateToPadTime(new Date()));

		System.out.println(getMonthOfYear(stringToDate("2017-12-30 00:00:00")));
		
		System.out.println(DateFormat.dateToString2(new Date()));
		
	}
}
