/**
 * 
 */
package com.vix.common.security.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 返回N天前的日期 Set number of days before the date
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-9
 */
public class DaysUtils {
	public static final String DATEPATTERN = "yyyy-MM-dd HH:mm:ss";
	private static String startDate;

	/**
	 * 返回3天前的日期
	 * 
	 * @return
	 */
	public static String getThreeDays() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -3);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(DaysUtils.DATEPATTERN);
		return format.format(cal.getTime());
	}

	/**
	 * 返回7天前的日期
	 * 
	 * @return
	 */
	public static String getSevenDays() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(DaysUtils.DATEPATTERN);
		return format.format(cal.getTime());
	}

	/**
	 * 返回一个月前的日期
	 * 
	 * @return
	 */
	public static String getOneMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(DaysUtils.DATEPATTERN);
		return format.format(cal.getTime());
	}

	/**
	 * 返回3个月前的日期
	 * 
	 * @return
	 */
	public static String getThreeMonths() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(DaysUtils.DATEPATTERN);
		return format.format(cal.getTime());
	}

	public static String getBeginDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		startDate = preMonday + " " + "00:00:00";
		return startDate;
	}

	public static String getEndDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		startDate = preMonday + " " + "23:59:59";
		return startDate;
	}

	public static void main(String args[]) {
		System.out.println(getBeginDay(new Date()));
		System.out.println(getEndDay(new Date()));
	}
}
