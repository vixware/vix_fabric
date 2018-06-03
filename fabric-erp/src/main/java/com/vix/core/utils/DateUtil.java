package com.vix.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DateUtil {
	
	/** 默认的日期格式 */
	private static final SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd"); 
	
	/** 默认的时间格式 */
	private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	public  static Date addYear(Date date,Integer addYear){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.YEAR,   addYear);
    	return  cal.getTime();
		
    }
	
    public  static Date addMonth(Date date,Integer addMonth){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  addMonth);
    	return  cal.getTime();
    }
    
    /**
     * 
     * @Title: addMonth2
     * @Description: addMonth的减少一秒
     */
    public  static Date addMonth2(Date date,Integer addMonth){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  addMonth);
    	//cal.add(Calendar.DATE,   -1);
    	cal.add(Calendar.SECOND,   -1);
    	return  cal.getTime();
    }
    /**
     * 
     * @Title: addMonth3
     * @Description: addMonth的减少一天的  23:59:59
     */
    public  static Date addMonth3(Date date,Integer addMonth){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  addMonth);
    	cal.add(Calendar.DATE,   -1);
    	
    	String d1 = format(cal.getTime())+ " 23:59:59";
    	return praseSqlTime(d1);
    }
    
    
    /**
     * @Title: addPositiveMonth
     * @Description: 加的月份不为负数
     */
    public  static Date addPositiveMonth(Date date,Integer addMonth){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	if(addMonth>0){
    		cal.add(Calendar.MONTH,  addMonth);
    	}
    	return  cal.getTime();
    }
	 
	/**
	 * 获取本期月份
	 */
	public static int getMonth() {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return Integer.parseInt(monthstr);
	}
	
	/**
	 * 转换为sql date
	 * @return
	 */
	public static java.sql.Date getCurrentSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * 获得本期年份
	 */
	public static int getYear() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 获得当天
	 */
	public static int getDay() {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 获取China地区日期时间
	 * @return yyyy-MM-dd hh:mm:ss形的时间字符串
	 */
	public static String getDate() {
		Date b = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		return formatter.format(b);
	}

	/** 格式化时间 */
	public static String getDate(Date b,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINESE);
		return formatter.format(b);
	}
	
	/**
	 * 获取日期时间字符串
	 * @return yyyy_MM_dd_hh:mm格式的字符串
	 */
	public static String getDateTime() {
		Date b = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh:mm");
		return formatter.format(b);
	}
	
	/**
	 * @Title: getCommonDateTime
	 * @Description: 获取日期时间字符串 yyyy-MM-dd HH:mm:ss
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	public static String getCommonDateTime() {
		Date b = new Date();
		return time.format(b);
	}
	
	/**
	 * 获取日期时间字符串
	 * @return yyyyMMdd_hh:mm格式的字符串
	 */
	public static String getDateTimeFor() {
		Date b = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
		return formatter.format(b);
	}
	
	public static String getFormatedTime(String format) {
		Date b = new Date();
		if(format==null || "".equals(format.trim()))
			format = "yyyyMMddHHmm";
			
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(b);
	}
	
	/**
	 * 获取日期时间字符串
	 * @return yyyyMM格式的字符串
	 */
	public static String getDateTimeFormat() {
		Date b = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		return formatter.format(b);
	}
	
	/**
	 * 将日期格式化到以日为单位的格式的字符串
	 * @param date被格式化的日期
	 * @return yyyy-MM-dd 形的字符串
	 */
	public static String format(Date date) {
		return df.format(date);
	}
	
	/**
	 * 
	 * @Title: format
	 * @Description: 自定义格式化日期
	 * @param @param date
	 * @param @param formater
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String format(Date date,String formater){
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(date);
	}
	/**
	 * 将日期格式化到以秒为单位的格式的字符串
	 * @param date被格式化的日期
	 * @return yyyy-MM-dd HH:mm:ss 形的字符串
	 */
	public static String formatTime(Date date) {
		return time.format(date);
	}

	/**
	 * 将格式化到以日为单位的格式的字符串转回java.sql.Date的格式。 注：如果字符串不合法返回null
	 * @param source yyyy-MM-dd 格式的字符串
	 * @return java.util.Date类型的日期
	 */
	public static Date praseSqlDate(String source) {
		try {
			return new Date(df.parse(source).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将格式化到以秒为单位的格式的字符串转回java.sql.Date的格式。 注：如果字符串不合法返回null
	 * @param source yyyy-MM-dd HH:mm:ss 格式的字符串
	 * @return Date类型的日期
	 */
	public static Date praseSqlTime(String source) {
		try {
			return new Date(time.parse(source).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获得一个月的最后一天
	 */
	public static String getEnddate() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13){
			month = 12;
		}
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		// 获得当前月的天数作为最后一天
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return year + "-" + monthstr + "-" + days;
	}

	/**
	 * 获得一个月的第一天
	 */
	public static String getBegindate() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return year + "-" + monthstr + "-01";
	}

	/**
	 * 获得一个月的最前一天的Timestamp
	 */
	public static Timestamp getBeginTime() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		String begindate = year + "-" + monthstr + "-01 00:00:00";
		Date date = null;
		try {
			date = time.parse(begindate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 获得一个月的最后一天Timestamp
	 */
	public static Timestamp getEndTime() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		// 获得当前月的天数
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String enddate = year + "-" + month + "-" + days + " 23:59:59";
		Date date = null;
		try {
			date = time.parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 获得指定月的第一天的Timestamp
	 */
	public static Timestamp getBeginTime(String month) {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		if (month.length() == 1) {
			month = "0" + month;
		}
		// 获得当前月的天数
		String begindate = year + "-" + month + "-01 00:00:00";
		Date date = null;
		try {
			date = time.parse(begindate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 获得指定月的最后一天Timestamp
	 */
	public static Timestamp getEndTime(String month) {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		if (month.length() == 1) {
			month = "0" + month;
		}
		// 获得当前月的天数
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String enddate = year + "-" + month + "-" + days + " 23:59:59";
		Date date = null;
		try {
			date = time.parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 获得当前月
	 */
	public static String getCurrentMonth() {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return monthstr.toString();
	}

	/**
	 * 获得当前年
	 */
	public static String getCurrentYear() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return String.valueOf(year);
	}
	
	/**
	 * 获得当前年
	 */
	public static int getCurrentIntYear() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return year;
	}
	
	/**
	 * 获得制定年前年
	 */
	public static int getPastYear(int cutLength) {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR)+cutLength;
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		String monthstr = month + "";
		if (monthstr.length() == 1) {
			monthstr = "0" + month;
		}
		return year;
	}
	
	/**
	 * 获得当前月的天数
	 */
	public static String getCurrentDay() {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return String.valueOf(day);
	}
	/**
	 * 获得当前月的天数
	 * @throws ParseException 
	 */
	public static Date getDateFromParam(int daySkip)  {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, daySkip);
		return cal.getTime();
	}
	
	/**
	 * 获得当前时间 yyyy-mm-dd
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date b = new Date();
		String date = "";
		try {
			date = formatter.format(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获得当前时间 yyyy年MM月dd日
	 */
	public static String getCurrentChinaDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		java.util.Date b = new Date();
		String date = "";
		try {
			date = formatter.format(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获得系统当前 Timestamp
	 */
	public static Timestamp getCurrentDayTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获得某年，某月的最前一天的Timestamp
	 */
	public static Timestamp getTimestamp(String datetime) {
		datetime = datetime + " 00:00:00";
		Date date = null;
		try {
			date = time.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 将Timestamp转换为字符串
	 */
	public static String timestampTostr(Timestamp datetime) {
		return time.format(datetime);
	}

	/**
	 * 年、月获得YYYYMM组合年份
	 */
	public static String getYearMonth(String Year, String Month) {
		String sYearMonth = "";

		if (Month.length() == 1) {
			sYearMonth = Year + "0" + Month;
		} else {
			if (Year.length() != 4 || Month.length() != 2) {
				return sYearMonth;
			}
			sYearMonth = Year + Month;
		}
		return sYearMonth;
	}

	/**
	 * 获得YYYYMM格式日期的 上一月
	 */
	public static String getLastYearMonth(String YearMonth) {
		String sLastYearMonth = "";
		int iYear = Integer.parseInt(YearMonth.substring(0, 4));
		int iMonth = Integer.parseInt(YearMonth.substring(4, 6));
		if (iMonth == 1) {
			sLastYearMonth = (iYear - 1) + "12";
		} else {
			sLastYearMonth = getYearMonth(iYear + "", (iMonth - 1) + "");
		}
		return sLastYearMonth;
	}
	
	/**
     * 由生日获得年龄  
     * @param  oBirthDay   生日  
     * @return  年龄  
     */  
     public static int getAge(Date oBirthDay) {
		int iAge;
		Calendar oCalendarToday = Calendar.getInstance();
		oCalendarToday.setTime(new Date());

		Calendar oCalendarBirthday = Calendar.getInstance();
		oCalendarBirthday.setTime(oBirthDay);

		Calendar oCalendarTemp = Calendar.getInstance();
		oCalendarTemp.set(oCalendarToday.get(Calendar.YEAR), oCalendarBirthday
				.get(Calendar.MONTH), oCalendarBirthday.get(Calendar.DATE));

		iAge = oCalendarToday.get(Calendar.YEAR) - oCalendarBirthday.get(Calendar.YEAR);
		if (!oCalendarToday.after(oCalendarTemp)) {
			iAge--;
		}
		if(iAge <= 0){
			return 1;
		}
		return iAge;
	}
    
     public static Date getAddDate(Date dates,Integer addDays){
     	Calendar   cal   =   Calendar.getInstance();
     	cal.setTime(dates);
     	cal.add(Calendar.DATE,   addDays);
     	return  cal.getTime();
     }
     
     /**
      * 
      * @Title: getYesterday
      * @Description:得到昨天 YYYYMMDD
      * @param @param date
      * @param @param formatter
      * @param @return    设定文件
      * @return String    返回类型
      * @throws
      */
    public static String getYesterday(Date dates){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(dates);
    	cal.add(Calendar.DATE,   -1);
    	return  new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		
    }
    
    /**
     * 
     * @Title: getYesterday
     * @Description: 后一天
     * @param @param dates
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getNextday(Date dates){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(dates);
    	cal.add(Calendar.DATE,   1);
    	return  new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		
    }
    
    /**
     * 
     * @Title: getNextDate
     * @Description: 下一天
     * @param @param dates
     * @param @return    设定文件
     * @return Date    返回类型
     * @throws
     */
    public static Date getNextDate(Date dates){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(dates);
    	cal.add(Calendar.DATE,   1);
    	return  cal.getTime();
		
    }
    /**
     * @Title: getNextday
     * @Description: 后一天
     * @param @param dates
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static Date getNextday(Date dates,int nextCount){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(dates);
    	cal.add(Calendar.DATE,   nextCount);
    	return cal.getTime();
		
    }
    /**
     * 
     * @Title: getLastMonth
     * @Description:得到上一个月YYYYMM
     * @param @param date
     * @param @param formatter
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getLastMonth(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  -1);
    	return  new SimpleDateFormat("yyyyMM").format(cal.getTime());
		
    }
    
    /**
     * 
     * @Title: getCurrentMonth
     * @Description: 当前月
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getCurrentMonth(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	return  new SimpleDateFormat("yyyyMM").format(cal.getTime());
		
    }
    
    /**
     * 
     * @Title: getNextMonth
     * @Description: 下一个月
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getNextMonth(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  1);
    	return  new SimpleDateFormat("yyyyMM").format(cal.getTime());
		
    }
    
    public  static String getLastMonthStr(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  -1);
    	return  new SimpleDateFormat("MM").format(cal.getTime());
		
    }
    
    
    /**
     * 
     * @Title: getLastQuater
     * @Description: 上一个季度
     * @param @param date
     * @param @param formatter
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getLastQuater(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,   -3);
    	String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <3){
    		res+="1";
    	}else if(month>=3 && month <6){
    		res+="2";
    	}else if(month>=6 && month <9){
    		res+="3";
    	}else if(month>=9 && month <12){
    		res+="4";
    	}
    	return res;
		
    }
    
    /**
     * 
     * @Title: getCurrentQuater
     * @Description: 当前季度
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getCurrentQuater(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <3){
    		res+="1";
    	}else if(month>=3 && month <6){
    		res+="2";
    	}else if(month>=6 && month <9){
    		res+="3";
    	}else if(month>=9 && month <12){
    		res+="4";
    	}
    	return res;
		
    }
    /**
     * 
     * @Title: getLastQuaterStr
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getLastQuaterStr(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,   -3);
    	//String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	String res="";
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <3){
    		res="1";
    	}else if(month>=3 && month <6){
    		res="2";
    	}else if(month>=6 && month <9){
    		res="3";
    	}else if(month>=9 && month <12){
    		res="4";
    	}
    	return res;
		
    }
    
    /**
     * 
     * @Title: getNextQuater
     * @Description: 下一个季度
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getNextQuater(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,   3);
    	String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <3){
    		res+="1";
    	}else if(month>=3 && month <6){
    		res="2";
    	}else if(month>=6 && month <9){
    		res+="3";
    	}else if(month>=9 && month <12){
    		res+="4";
    	}
    	return res;
		
		
    }
    
    /**
     * 
     * @Title: getLastHalfYear
     * @Description: 得到半年前的日期 YYYYU YYYYD
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getLastHalfYear(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  -6);
    	
    	String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <6){
    		res+="U";
    	}else if(month>=6 && month <12){
    		res+="D";
    	}
    	return res;
    }
    
    /**
     * 
     * @Title: getCurrentHalfYear
     * @Description: 当前年份
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getCurrentHalfYear(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <6){
    		res+="U";
    	}else if(month>=6 && month <12){
    		res+="D";
    	}
    	return res;
    }
    
    /**
     * 
     * @Title: getLaseHalfYear
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getLastHalfYearStr(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  -6);
    	
    	//String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	String res="";
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <6){
    		res="U";
    	}else if(month>=6 && month <12){
    		res="D";
    	}
    	return res;
    }
    
    /**
     * 
     * @Title: getNextHalfYear
     * @Description: 下一个半年
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getNextHalfYear(Date date){
      	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  6);
    	
    	String res =  new SimpleDateFormat("yyyy").format(cal.getTime());
    	int month = cal.get(Calendar.MONTH);
    	if(month>=0 && month <6){
    		res+="U";
    	}else if(month>=6 && month <12){
    		res+="D";
    	}
    	return res;
    }
    /**
     * 
     * @Title: getLastYear
     * @Description: 得到上一年
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getLastYear(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.YEAR,   -1);
    	return  new SimpleDateFormat("yyyy").format(cal.getTime());
		
    }
    
    public  static String getCurrentYear(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	return  new SimpleDateFormat("yyyy").format(cal.getTime());
		
    }
    
    /**
     * 
     * @Title: getLastYear
     * @Description: 下一年
     * @param @param date
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public  static String getNextYear(Date date){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.YEAR,   1);
    	return  new SimpleDateFormat("yyyy").format(cal.getTime());
		
    }
    
    /**
	 * 获得当前月的最后一天
	 */
	public static Date getLastDayOfMonth() {
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		// 获得当前月的天数
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String enddate = year + "-" + month + "-" + days + " 23:59:59";
		Date date = null;
		try {
			date = time.parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	
	/**
	 * 
	 * @Title: getLastDayOfMonth
	 * @Description: 获得某个月的最后一天
	 * @param @param dataParam
	 * @param @return    设定文件
	 * @return Date    返回类型
	 * @throws
	 */
	public static Date getLastDayOfMonth(Date dataParam) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dataParam);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		// 获得当前月的天数
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String enddate = year + "-" + month + "-" + days + " 23:59:59";
		Date date = null;
		try {
			date = time.parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	
	/**
	 * 
	 * @Title: getFirstDayOfMonth
	 * @Description: 某个月的第一天
	 * @param @param dataParam
	 * @param @return    设定文件
	 * @return Date    返回类型
	 * @throws
	 */
	public static Date getFirstDayOfMonth(Date dataParam) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dataParam);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		// 获得当前月的天数
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		String enddate = year + "-" + month + "-" + days + " 23:59:59";
		Date date = null;
		try {
			date = time.parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	/**
	 * 
	 * @Title: getLastDayOfNextMonth
	 * @Description: 下一个月的最后一天
	 * @param @return    设定文件
	 * @return Date    返回类型
	 * @throws
	 */
	public static Date getLastDayOfNextMonth(Date now) {
		Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(now);
    	cal.add(Calendar.MONTH,  1);
    	
    	int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 13)
			month = 12;
		// 获得当前月的天数
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String enddate = year + "-" + month + "-" + days + " 23:59:59";
		Date date = null;
		try {
			date = time.parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	
	/**
	 * 
	 * @Title: getCurrentDateByParamDate
	 * @Description: 根据一个字符串的日期格式 和 要得到的日期类型  来得到其所在的日期  比如
	 * dateTye
	 * @param @param date
	 * @param @param dateType 2 季度；3半年； 4 年
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String getCurrentDateByParamDate(String date,int dateType){
		String res="";
		
		if(dateType==2){
			//季度

			if(date.length()==6){
				// date为 月份  , 则返回当前月份所在的季度
				res = getStrQuaterByMonth(date);
				
			}
		}else if(dateType==3){
			//半年
			
			if(date.length()==6){
				// date为月份 比如201102 则返回 当前月份所在的半年
				res = getStrHalfyearByMonth(date);
			}else if(date.length()==5 && NumberUtil.isNumeric(date)){
				//季度  比如20101 则返回 当前季度所在的半年
				res = getStrHalfyearByQuater(date);
			}
		}else if(dateType==4){
			//年
			if(date.length()==6){
				// 月份 比如201102
				res = date.substring(0, 4);
			}else if(date.length()==5 && NumberUtil.isNumeric(date)){
				//季度  比如20101
				res = date.substring(0, 4);
			}else if(date.length()==5 && !NumberUtil.isNumeric(date)){
				//半年 比如2010U 2010D
				res = date.substring(0, 4);
			}
		}
		return res;
	}
	
	public static String getStrQuaterByMonth(String monthDate){
		String year = monthDate.substring(0, 4);
		
		String month = monthDate.substring(4,6);
		int monthInt = Integer.parseInt(month);
		String quater = "";
		if(monthInt>0 && monthInt<4){
			quater="1";
		}else if(monthInt>3 && monthInt<7){
			quater="2";
		}else if(monthInt>6 && monthInt<10){
			quater="3";
		}else if(monthInt>9 && monthInt<13){
			quater="4";
		}
		return year+quater;
	}
	public static  String getStrHalfyearByMonth(String monthDate){
		String halfyear = monthDate.substring(0, 4);
		
		String month = monthDate.substring(4,6);
		int monthInt = Integer.parseInt(month);
		if(monthInt>=1 && monthInt <7){
			halfyear+="U";
    	}else if(monthInt>=7 && monthInt <=12){
    		halfyear+="D";
    	}
		return halfyear;
	}
	public static  String getStrHalfyearByQuater(String quaterDate){
		String halfyear = quaterDate.substring(0, 4);
		
		String quater = quaterDate.substring(4,5);
		int quaterInt = Integer.parseInt(quater);
		if(quaterInt==1 || quaterInt==2){
			halfyear+="U";
    	}else if(quaterInt==3 || quaterInt ==4){
    		halfyear+="D";
    	}
		return halfyear;
	}
	
	
	
	
	
	public static List<String> getCollectionDateStrByCmdIdAndDimendate(String dimenDate, Long cmdId){
		if(cmdId==2){
			//要累计的父考核周期是季度
			
			if(dimenDate.length()==6){
				// dimenDate为 月份  , 则得到该季度内所有的月份集合 
				return getCollectionMonthByQuater(dimenDate);
				
			}
		}else if(cmdId==3){
			//要累计的父考核周期是半年
			if(dimenDate.length()==6){
				// date为月份 比如201102 则返回 当前月份所在的半年
				return getCollectionMonthByHalfYear(dimenDate);
			}else if(dimenDate.length()==5 && NumberUtil.isNumeric(dimenDate)){
				return getCollectionQuaterByHalfYear(dimenDate);
			}
		}else if(cmdId==4){
			//要累计的父考核周期是年度
			if(dimenDate.length()==6){
				// 月份 比如201102
				return getCollectionMonthByYear(dimenDate);
			}else if(dimenDate.length()==5 && NumberUtil.isNumeric(dimenDate)){
				//季度  比如20101
				return getCollectionQuaterByYear(dimenDate);
			}else if(dimenDate.length()==5 && !NumberUtil.isNumeric(dimenDate)){
				//半年 比如2010U 2010D
				return getCollectionHalfYearByYear(dimenDate);
			}
			
		}
		return new ArrayList<String>();
	}
	/**
	 * 
	 * @Title: getCollectionMonthByQuater
	 * @Description: 某个月份所在的季度的所有月份
	 * @param @param monthDate
	 * @param @param cmdId
	 * @param @return    设定文件
	 * @return List<String>    返回类型
	 * @throws
	 */
	public static List<String> getCollectionMonthByQuater(String monthDate){
		List<String> dimenDateList = new LinkedList<String>();
		
		String year = monthDate.substring(0, 4);
		
		String month = monthDate.substring(4,6);
		int monthInt = Integer.parseInt(month);
		if(monthInt>0 && monthInt<4){
			dimenDateList.add(year+"01");
			dimenDateList.add(year+"02");
			dimenDateList.add(year+"03");
		}else if(monthInt>3 && monthInt<7){
			dimenDateList.add(year+"04");
			dimenDateList.add(year+"05");
			dimenDateList.add(year+"06");
		}else if(monthInt>6 && monthInt<10){
			dimenDateList.add(year+"07");
			dimenDateList.add(year+"08");
			dimenDateList.add(year+"09");
		}else if(monthInt>9 && monthInt<13){
			dimenDateList.add(year+"10");
			dimenDateList.add(year+"11");
			dimenDateList.add(year+"12");
		}
		return dimenDateList;
	}
	
	//某个月份所在的半年的所有月份
	public static List<String> getCollectionMonthByHalfYear(String monthDate){
		List<String> dimenDateList = new LinkedList<String>();
		
		String year = monthDate.substring(0, 4);
		
		String month = monthDate.substring(4,6);
		int monthInt = Integer.parseInt(month);
		
		if(monthInt>=1 && monthInt <7){
			dimenDateList.add(year+"01");
			dimenDateList.add(year+"02");
			dimenDateList.add(year+"03");
			dimenDateList.add(year+"04");
			dimenDateList.add(year+"05");
			dimenDateList.add(year+"06");
    	}else if(monthInt>=7 && monthInt <=12){
    		dimenDateList.add(year+"07");
			dimenDateList.add(year+"08");
			dimenDateList.add(year+"09");
			dimenDateList.add(year+"10");
			dimenDateList.add(year+"11");
			dimenDateList.add(year+"12");
    	}
		return dimenDateList;
	}
	
	//某个季度所在的半年的所有季度
	public static List<String> getCollectionQuaterByHalfYear(String quaterDate){
		List<String> dimenDateList = new LinkedList<String>();
		
		String year = quaterDate.substring(0, 4);
		String quater = quaterDate.substring(4,5);
		int quaterInt =Integer.parseInt(quater);
		if(quaterInt==1 || quaterInt==2){
			//上半年
			dimenDateList.add(year+"1");
			dimenDateList.add(year+"2");
		}else if(quaterInt==3 || quaterInt==4){
			dimenDateList.add(year+"3");
			dimenDateList.add(year+"4");
		}
		
		return dimenDateList;
	}
	
	//某个月份所在的年的所有月份
	public static List<String> getCollectionMonthByYear(String monthDate){
		List<String> dimenDateList = new LinkedList<String>();
		
		String year = monthDate.substring(0, 4);
		dimenDateList.add(year+"01");
		dimenDateList.add(year+"02");
		dimenDateList.add(year+"03");
		dimenDateList.add(year+"04");
		dimenDateList.add(year+"05");
		dimenDateList.add(year+"06");
		dimenDateList.add(year+"07");
		dimenDateList.add(year+"08");
		dimenDateList.add(year+"09");
		dimenDateList.add(year+"10");
		dimenDateList.add(year+"11");
		dimenDateList.add(year+"12");
		return dimenDateList;
	}
	
	//某个季度所在的年的所有季度
	public static List<String> getCollectionQuaterByYear(String monthDate){
		List<String> dimenDateList = new LinkedList<String>();
		
		String year = monthDate.substring(0, 4);
		
		dimenDateList.add(year+"1");
		dimenDateList.add(year+"2");
		dimenDateList.add(year+"3");
		dimenDateList.add(year+"4");
		return dimenDateList;
	}
	
	//某个半年所在的年的所有半年
	public static List<String> getCollectionHalfYearByYear(String monthDate){
		List<String> dimenDateList = new LinkedList<String>();
		
		String year = monthDate.substring(0, 4);
		
		dimenDateList.add(year+"U");
		dimenDateList.add(year+"D");
		return dimenDateList;
	}

	/** 获取当前周 */
    public static int getWeekNumber(){ 
    	Calendar calendar = Calendar.getInstance();
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    	
    /**
	  * 取得指定日期所在周的第一天 
	  */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
  		c.setFirstDayOfWeek(Calendar.MONDAY);
  		c.setTime(date);
  		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
  		return c.getTime ();
	}

    /**
     * 取得指定日期所在周的最后一天 
     */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
  		return c.getTime();
	} 
	
	/**
	 * 返回9999-12-31 23:59:59的Date
	 * @Title: getEndOfWorld
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return Date    返回类型
	 * @throws
	 */
	public static Date getEndOfWorld(){
		String dateStr = "9999-12-31 23:59:59";
		Date endDate = praseSqlTime(dateStr);
		return endDate;
	}
	
	/**
	 * 返回日期的 格式  YYYY-MM-DD 00:00:00的Date
	 */
	public static Date getBeginDateByDate(Date date){
		if(null == date){
			date = new Date();
		}
		String dateStr = df.format(date) + "  00:00:00";
		return praseSqlTime(dateStr);
	}
	
	
	/**
	 * @Title: getCronExpressionByDate
	 * @Description: 获得日期的 CronExp表达式格式
	 * @param @param date
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	public static String getCronExpressionByDate(Date date){
		return format(date, "ss mm HH dd MM ? yyyy");
	}
	
	/**
	 * @Title:  是否在时间范围内，包括beginInc，不包括endNotInc
	 * 日期格式必须为YYYY-MM-DD
	 */
	public static Boolean isInDateRange(Date obj,Date beginInc,Date endNotInc){
		if(null == obj || null == beginInc || null == endNotInc){
			return false;
		}
		Date objFt = DateUtil.praseSqlDate(DateUtil.format(obj));
		Date beginIncFt = DateUtil.praseSqlDate(DateUtil.format(beginInc));
		Date endNotIncFt = DateUtil.praseSqlDate(DateUtil.format(endNotInc));
		
		if(objFt.before(beginIncFt)){
			return false;
		}
		if(!objFt.before(endNotIncFt)){
			return false;
		}
		return true;
	}
	
	/**
	 * @Title:  obj是否在target未来，    两个是同一天则 返回false
	 * 日期格式必须为YYYY-MM-DD
	 */
	public static Boolean isFuture(Date obj,Date target){
		if(null == obj || null == target  ){
			return false;
		}
		Date objFt = DateUtil.praseSqlDate(DateUtil.format(obj));
		Date targetFt = DateUtil.praseSqlDate(DateUtil.format(target));
		
		if(objFt.after(targetFt)){
			return true;
		}
		return false;
	}
	
	
}
