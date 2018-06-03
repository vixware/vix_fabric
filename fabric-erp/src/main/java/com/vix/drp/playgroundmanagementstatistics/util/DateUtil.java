/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * com.vix.drp.playgroundmanagementstatistics.util.DateUtil
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class DateUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	public static List<String> getSevenDay() {
		List<String> dateList = new LinkedList<String>();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus1 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus2 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus3 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus4 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus5 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus6 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus7 = c.getTime();
		dateList.add("'" + sdf.format(today_plus7) + "'");
		dateList.add("'" + sdf.format(today_plus6) + "'");
		dateList.add("'" + sdf.format(today_plus5) + "'");
		dateList.add("'" + sdf.format(today_plus4) + "'");
		dateList.add("'" + sdf.format(today_plus3) + "'");
		dateList.add("'" + sdf.format(today_plus2) + "'");
		dateList.add("'" + sdf.format(today_plus1) + "'");
		return dateList;
	}

	public static List<String> getSevenDay1() {
		List<String> dateList = new LinkedList<String>();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus1 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus2 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus3 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus4 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus5 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus6 = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, -1);
		Date today_plus7 = c.getTime();
		dateList.add(sdf1.format(today_plus7));
		dateList.add(sdf1.format(today_plus6));
		dateList.add(sdf1.format(today_plus5));
		dateList.add(sdf1.format(today_plus4));
		dateList.add(sdf1.format(today_plus3));
		dateList.add(sdf1.format(today_plus2));
		dateList.add(sdf1.format(today_plus1));
		return dateList;
	}

	public static void main(String[] args) throws Exception {

		String start = "2012-02-01";
		String end = "2012-03-02";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = sdf.parse(start);
		Date dEnd = sdf.parse(end);
		/* List<String> lDate = getSevenDay1(); */
		List<String> lDate = findDates1(dBegin, dEnd);
		for (String date : lDate) {
			System.out.println(date);
		}
	}

	public static List<String> findDates(Date dBegin, Date dEnd) {
		List<String> lDate = new ArrayList<String>();
		lDate.add("'" + sdf.format(dBegin) + "'");
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add("'" + sdf.format(calBegin.getTime()) + "'");
		}
		return lDate;
	}

	public static List<String> findDates1(Date dBegin, Date dEnd) {
		List<String> lDate = new ArrayList<String>();
		lDate.add(sdf1.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(sdf1.format(calBegin.getTime()));
		}
		return lDate;
	}

}
