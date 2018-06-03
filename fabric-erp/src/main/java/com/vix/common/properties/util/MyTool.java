package com.vix.common.properties.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *	        这是一个工具类  一般用于统计分析（图表）
 *    【特别说明：本工具类，方法与方法之间相互调用，请勿改动每一个方法】
 * @author guopeng
 *
 */
public class MyTool {
	//get_zifuchuan_weizhi方法，用 正则 去找出目标子字符串的所在位置（角标） regex正则  position位置    (appearNoTimes出现第几次,targetStr目标字符串,paragraphStr从什么内容中寻找目标字符串)
		public static int getStringPositionByRegex(String paragraphStr,String targetStr,int appearNoTimes){
			int i = -1;
				Pattern pattern = Pattern.compile(targetStr);
				Matcher findMatter = pattern.matcher(paragraphStr);
				int number = 0;
				while(findMatter.find()){
					number++;
					if(number == appearNoTimes){
						break;
					}
				}
				i = findMatter.start();
			return i;
		}
		//------------------↓ 分割线1  ↓ -------2017-7-4---------------------
		/** 输入合适的int y 返回年/月/日类型的昨天，前天等的字符串集合ArrayList<String>  如： 输入-6 获得最近7天的年月日日期集合 
		 * ArrayList<String> arr = list_str_zhou(-1);//今天2017-07-04    如果是-1时 得到 2017-07-04   2017-07-03
		 * ArrayList<String> arr2 = list_str_zhou(-6);//今天2017-07-04  如果是-6时  得到  2017-07-04   2017-07-03   2017-07-02   2017-07-01   2017-06-30   2017-06-29   2017-06-28 
		 * 那么得到最近30日的输入 -29 newly  [倒序的]
		 */
		public static ArrayList<String> getNewlyDateArrayByInt(int y){
			ArrayList<String>  arr = new ArrayList<String>();
			for(int x=0;x>=y;x--){
				String str = getAppointDateString(x);
				arr.add(str);
				str = "";
			}
			return arr;
		}
		/** 输入合适的int y 返回年/月/日类型的昨天，前天等的字符串集合ArrayList<String>  如： 输入-6 获得最近7天的年月日日期集合 
		 * ArrayList<String> arr = list_str_zhou(-6);//今天2017-10-16    如果是-1时 得到 [2017-10-10, 2017-10-11, 2017-10-12, 2017-10-13, 2017-10-14, 2017-10-15, 2017-10-16]
		 * 那么得到最近30日的输入 -29 newly  [正序的]
		 */
//		if("day7".equals(qCondition)){ TimeArr = MyTool.getNewlyDateArrayByInt_ZX(-6);}//最近7天            用法示例
//		if("day30".equals(qCondition)){ TimeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);}//最近30天
		public static ArrayList<String> getNewlyDateArrayByInt_ZX(int y){
			ArrayList<String>  ReturnArr = new ArrayList<String>();
			for(int x=0;x>=y;x--){
				String str = getAppointDateString(x);
				ReturnArr.add(str);
				str = "";
			}
			for (int start = 0, end = ReturnArr.size() - 1; start < end; start++, end--) {
				String temp = ReturnArr.get(end);
				 ReturnArr.set(end, ReturnArr.get(start));
				 ReturnArr.set(start, temp);
			 }
			return ReturnArr;
		}
		/** 输入合适的int x 返回年/月/日类型的昨天，前天等  如： 输入-6 获得7天日期 2017/06/07  Appoint指定**/
		public static String getAppointDateString(int x){
			String str = "";
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, x);
			java.text.SimpleDateFormat format2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
			str= format2.format(cal.getTime());
			return str;
		}	
		//------------------↑ 分割线1  ↑ ----------------------------
		
		//------------------↓ 分割线2  ↓ -------2017-7-5---------------------
		/** 把有规则的字符串，按一定的正则切割获取子串，并返回子串集合如： 输入-6 获得7天日期 2017/07/05 
		 *   例如把{a9fe3cad-59af-111c-8159-aff51c750008}{c0a82801-5ce3-1b64-815c-e3d303450019}中的uuid取出来
		 *   取出来的元素1：  {a9fe3cad-59af-111c-8159-aff51c750008}
		 *          元素2：   {c0a82801-5ce3-1b64-815c-e3d303450019}
		 */
		public static ArrayList<String> SplitStringByRegex(String bigstr,String regex){
			ArrayList<String>  arr = new ArrayList<String>();
			String str = "";
			Pattern com = Pattern.compile(regex);
			Matcher mat = com.matcher(bigstr);
			while(mat.find()){
				str = mat.group();
				arr.add(str);
				str = "";
			}
			return arr;
		}
		//------------------↑ 分割线2  ↑ ----------------------------
		
		//------------------↓ 分割线3  ↓ -------2017-7-5---------------------
		/** 下面方法是用正则从字符串中找到目标子串出现第几次的位置   regex正则  position位置  **/
		public static int getPositionByRegexTwo(String yuanzifuchuan,String mubiao,int dijici){
			int i = -1;
				Pattern pattern = Pattern.compile(mubiao);
				Matcher findMatter = pattern.matcher(yuanzifuchuan);
				int number = 0;
				while(findMatter.find()){
					number++;
					if(number == dijici){
						break;
					}
				}
				i = findMatter.start();
			return i;
		}
		//------------------↑ 分割线3  ↑ ----------------------------
		
		//------------------↓ 分割线4  ↓ -------2017-7-11-------------[经过修改解决啦本月含有31天，上月没有31天的不等问题]--------	
		//和list_str_zhou_BenYue（）方法配合使用，获得上月的相应时间集合
		//情况1：需求(功能)假设当前时间：2017-07-11
		// 		则获得集合2017-06-01 2017-06-02 	2017-06-03 ...2017-06-11
		//情况2：需求(功能)假设当前时间：2017-06-01
		//		则获得集合2017-06-01  
		//注意：这里返回的集合是已经《时间正序的》  last month 上个月
		public static ArrayList<String> getLastMonthDateStringArray() throws ParseException{
			ArrayList<String>  arr = new ArrayList<String>();
			ArrayList<String> arr2 = getBackwardsDateArrayForNow();
			String time = arr2.get(arr2.size()-1);
			Date date=null;  
		    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
		    date=formatter.parse(time); 
		    Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date); 
	        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); //设置为上一个月
	        date = calendar.getTime();
	        String format1 = formatter.format(date);
	        String strDemo = format1;         /* System.out.println(format1+"----假设当前时间为2017-07-14那么format1就是2017-06-01-------------------");*/
			String substring001 = strDemo.substring(0, 4);
			Integer year1 = Integer.parseInt(substring001);
			String substring002 = strDemo.substring(5, 7);
			Integer yue1 = Integer.parseInt(substring002);
		    String lastDay = getLastDayOfMonth(year1,yue1);
	        String substring2 = format1.substring(0,8);
	        for(int x=arr2.size()-1;x>=0;x--){
	        	String str = arr2.get(x);
	        	String substring3 = str.substring(8,10);
	        	String string4 = substring2+substring3;
	        	if(!(string4.equals(lastDay))){
	        		arr.add(string4);
	        	}
	        }
			return arr;
		}
		 /**
		  * 
	     * 获取某月的最后一天   例如String lastDay = getLastDayOfMonth(2014,02); 获得的lastDay 是 2014-02-28
	     */
	    public static String getLastDayOfMonth(int year,int month){
	        Calendar cal = Calendar.getInstance();//设置年份
	        cal.set(Calendar.YEAR,year); //设置月份
	        cal.set(Calendar.MONTH, month-1);//获取某月最大天数
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //设置日历中月份的最大天数
	        cal.set(Calendar.DAY_OF_MONTH, lastDay); //格式化日期
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String lastDayOfMonth = sdf.format(cal.getTime());
	        return lastDayOfMonth;
	    }
	    /**
	     * 获取某月的开始第一天   例如String lastDay = getLastDayOfMonth(2014,02); 获得的lastDay 是 2014-02-01
	     */
	    public static String getFirstDayOfMonth(int year, int month) {     
	        Calendar cal = Calendar.getInstance();     
	        cal.set(Calendar.YEAR, year);     
	        cal.set(Calendar.MONTH, month-1);  
	        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  
	       return   new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());  
	    }
	    //获得上一个月的时间  例如：当前时间为2017-07-15 则获得时间串 2017-06-15
	    //同时，不存在大小月问题: 例如：当前时间为2017-03-31 则获得时间串2017-02-28    last month 上个月
		public static String getLastMonthOneDayString() throws ParseException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar c = Calendar.getInstance();
	        c.setTime(new Date()); 
	        c.add(Calendar.MONTH, -1);//过去一月
	        Date m = c.getTime();
	        String mon = format.format(m);
	        return mon;
		}
		//获得当前的时间  例如：当前时间为2017-07-14 则获得时间串 2017-07-14
				public static String getNowDateBy_yyyyMMdd() {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        Date date = new Date();
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(date);
			        date = calendar.getTime();
			        String format1 = dateFormat.format(date);
			        return format1;
				}
		//情况1：需求(功能)假设当前时间：2017-07-11
		// 		则获得集合2017-07-11 2017-07-10 	2017-07-09 ...2017-07-01
		//情况2：需求(功能)假设当前时间：2017-07-01
		// 		则获得集合2017-07-01
		//backwards 向后的
		public static ArrayList<String> getBackwardsDateArrayForNow(){
			ArrayList<String>  arr = new ArrayList<String>();
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));//获取东八区时间
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM");
			String curDate = s.format(c.getTime());//当前日期2017-07
			curDate = new String(curDate +"-01");
			for(int x=0;x>=-33;x--){
				String str = getAppointDateString(x);
				arr.add(str);
				if(str.equals(curDate)){
					break;
				}
				str = "";
			}
			return arr;
		}
	 //------------------↑ 分割线4  ↑ ----------------------------
	
	//------------------↓ 分割线5  ↓ -------2017-7-13---------------------	
	/*	
	 * json_HeBing_01方法用于合并两个json对象为一个json对象串
	 * 例如
	json01：	    {"jrhy":0,"tongbi":"+0.00%","byxz":2}
	json02	    {"day30":[0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0]}
	返回	json05： {"jrhy":0,"tongbi":"+0.00%","byxz":2,"day30":[0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0]}
	*/
		//merge 合并
		public static String mergeJsonStringOne(String json01,String json02){
			String json03 = json01.substring(0, json01.length()-1);
			String json04 = json02.substring(1, json02.length());
			String json05 = json03+","+json04;
			return json05;
			
		}
		/**
		 *   对合并json串的方法进行升级，利用可变长参数，可以任意对多余两个的json串进行合并
		 *   例如：
		 *    String a ="{\"a\":"+0+"}";
			  String b ="{\"b\":"+1+"}";
			  String c ="{\"c\":"+2+"}";
			  String d ="{\"d\":"+3+"}";
			  String e ="{\"e\":"+4+"}";
			  String f ="{\"f\":"+5+"}";
			  String g ="{\"g\":"+6+"}";
			  String h ="{\"h\":"+7+"}";
			  String i ="{\"i\":"+8+"}";
			  String json_HeBing_02 = json_HeBing_02(a,b,c,d,e,f,h,i);
			 System.out.println(json_HeBing_02);
			 得到： {"a":0,"b":1,"c":2,"d":3,"e":4,"f":5,"h":7,"i":8}
		 * **/  //merge 合并
		public static String mergeJsonStringTwo(String... jsonArr) throws ParseException {
			String jsonHeBing = "";
				for(int x=0;x<jsonArr.length-1;x++){
					if(x==0){
						jsonHeBing  = mergeJsonStringOne(jsonArr[x],jsonArr[x+1]);
					}
					if(x!=0){
						jsonHeBing  = mergeJsonStringOne(jsonHeBing,jsonArr[x+1]);
					}
				}
			return jsonHeBing;
		}
		/**
		 *   对合并json串的方法进行升级，利用可变长参数，可以任意对多余两个的json串进行合并 | 可以对ArrayList<String>操作
		 *   
		 * **/  //merge 合并
		public static String mergeJsonStringThree(ArrayList<String> jsonArr) throws ParseException {
			String jsonHeBing = "";
			if(jsonArr.size()==0){
				jsonHeBing = "{\"occupyA\":"+0+"}";
				return jsonHeBing;
			}
			if(jsonArr.size()==1){
				jsonHeBing = jsonArr.get(0);
				return jsonHeBing;
			}
				for(int x=0;x<jsonArr.size()-1;x++){
					if(x==0){
						jsonHeBing  = mergeJsonStringOne(jsonArr.get(x),jsonArr.get(x+1));
					}
					if(x!=0){
						jsonHeBing  = mergeJsonStringOne(jsonHeBing,jsonArr.get(x+1));
					}
				}
			return jsonHeBing;
		}
    //------------------↑ 分割线5  ↑ ----------------------------
		
   //------------------↓ 分割线6  ↓ -------2017-7-14---------------------		
		//输入开始时间字符串  start_date_Start 2017-01-01    
		//输入结束时间字符串  start_date_End   2017-01-31
		//返回包含头包含尾的所有天数  2017-01-01 2017-01-02 .....一直到2017-01-31
		public static ArrayList<String> getDateStringArrayFromStartTOEnd(String start_date_Start ,String start_date_End) throws ParseException {
			/*Calendar dayc1 = new GregorianCalendar();*/
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(start_date_Start);
			long time1 = daystart.getTime();
			long time2 = 0l;
			ArrayList<Long> arrLong = new ArrayList<Long>();
			for(int x=0;x<=32;x++){
				time2 = time1 + 1000*60*60*24l*x ;
				arrLong.add(time2);
			}
			ArrayList<String> arrStr = new ArrayList<String>();
			for(int x=0;x<arrLong.size();x++){
				Long time = arrLong.get(x);
				 GregorianCalendar gc = new GregorianCalendar();
		         gc.setTimeInMillis(time);
		         String format001 = df.format(gc.getTime());
		         arrStr.add(format001);
		         if(format001.equals(start_date_End)){
		        	 break;
		         }
			}
			return arrStr;
		}
		//根据输入的日期获得这个月的所有天数
		//只要给我个日期我就返回这个月的所有天数，注意格式
		//例如：  strDate="2017-06-12" 我就返回从2017-06-01.....到2017-06-30
		public static ArrayList<String> getTheMonthAllDayArrayByDateString(String StringDate) throws ParseException {
			String substring001 = StringDate.substring(0, 4);
			String substring002 = StringDate.substring(5, 7);
			Integer year1 = Integer.parseInt(substring001);
			Integer yue1 = Integer.parseInt(substring002);
			String first = getFirstDayOfMonth(year1,yue1);
			String Last = getLastDayOfMonth(year1,yue1);
			ArrayList<String> arrt = MyTool.getDateStringArrayFromStartTOEnd(first,Last);
			return arrt;
		} 
		
		//------------------↑ 分割线6  ↑ ----------------------------
		
		//------------------↓ 分割线7  ↓ ----------------------------
		//get_DangQianZhou_ZX
		//获得当前周的所有日期，正序，    //[错误的本周，不过现在已经修改，修正2017-11-12周日]
		//例如当前日期 2017-07-17周一  则返回2017-07-17（周一） 2017-07-18 2017-07-19 2017-07-20 2017-07-21 2017-07-22 2017-07-23（周日）
		public static ArrayList<String>  getNowWeekArray_ZX() {
			ArrayList<String> arr = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calen =Calendar.getInstance();  
			Date daystart = new Date();    
			calen.setTime(daystart);  
			int week  = calen.get(Calendar.DAY_OF_WEEK);
			if(week == 1){//如果 week == 1 说明这一天是‘中国的周日’
				arr = MyTool.getNewlyDateArrayByInt_ZX(-6);
	        }else{
		        Calendar c = Calendar.getInstance();   
		        // 今天是一周中的第几天
		        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK );
		        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
		            c.add(Calendar.DAY_OF_MONTH, 1);
		        }
		        // 计算一周开始的日期
		        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
		        for (int i=1;i<=7;i++) {
		            c.add(Calendar.DAY_OF_MONTH, 1);
		            arr.add(sdf.format(c.getTime()));
		        }
	        }
	        return arr;
		}
		/*** 需求：获得下周的所有时间年月日，正序，每天  ***/
		//ArrayList<String> arr下周 = MyTool.getNextWeekArrAllDay_ZX();
		//System.out.println(arr下周+"    |现在时间2017-11-12周日|确实得到的是‘下周时间正序，每天’");
		//[2017-11-13, 2017-11-14, 2017-11-15, 2017-11-16, 2017-11-17, 2017-11-18, 2017-11-19]    |现在时间2017-11-12周日|确实得到的是‘下周时间正序，每天’
		public static ArrayList<String>  getNextWeekArrAllDay_ZX() {
			ArrayList<String> arr = new ArrayList<String>();
			try {
				String strDate = getNowWeekArray_ZX().get(6);//先获得本周最后一天
				for(int x=0;x<7;x++){
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
				 	Calendar c = Calendar.getInstance();  
			        c.setTime(date);  
			        c.add(Calendar.DAY_OF_MONTH, (x+1));//+N天    
			        String timeStr = sdf.format(c.getTime());
			        arr.add(timeStr);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return arr;
		}
		/*** 需求：获得下周的开始时间和结束时间即可，年月日，正序，头和尾  ***/
		//ArrayList<String> onlySE = MyTool.getNextWeekSonlyE_ZX();
		//System.out.println(onlySE+"  现在2017-11-12周日，确实仅仅获得下周开始时间和结束时间");
		//[2017-11-13, 2017-11-19]  现在2017-11-12周日，确实仅仅获得下周开始时间和结束时间
		public static ArrayList<String>  getNextWeekSonlyE_ZX() {//SonlyE代表StartOnlyAndEND
			ArrayList<String> arr = new ArrayList<String>();
			try {
				String strDate = getNowWeekArray_ZX().get(6);//先获得本周最后一天
				
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
				 	Calendar c = Calendar.getInstance();  
			        c.setTime(date);  
			        c.add(Calendar.DAY_OF_MONTH, 1);//+1天    
			        String timeStrStart = sdf.format(c.getTime());
			        arr.add(timeStrStart);
			        
			        c.setTime(date);  
			        c.add(Calendar.DAY_OF_MONTH, 7);//+7天    
			        String timeStrEnd = sdf.format(c.getTime());
			        arr.add(timeStrEnd);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return arr;
		}
		/** 
		* 根据当前日期获得上周的日期区间（上周周一和周日日期） 
		*  仅仅返回 上周的 周一和周末日期
		*  例如今天是2017-08-20（周日） 则上返回【上周一：2017-08-07】【上周日：2017-08-13】
		 * @throws ParseException 
		*/  
//		String[] arr = MyTool.getLastWeek_Only_S_AND_W_ZX();
//		System.out.println(arr[0]+"  现在是周日‘2017-11-12|实践证明：getLastWeek_Only_S_AND_W_ZX获得上周时间符合‘中国习惯’  "+arr[1]);
		//2017-10-30  现在是周日‘2017-11-12|实践证明：getLastWeek_Only_S_AND_W_ZX获得上周时间符合‘中国习惯’  2017-11-05
		public static String[] getLastWeek_Only_S_AND_W_ZX() throws ParseException { 
			 Calendar calendar = Calendar.getInstance();
		        calendar.setFirstDayOfWeek(Calendar.MONDAY);
		        calendar.add(Calendar.DATE, -7);
		        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		        Date sTime = calendar.getTime();
		        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		        Date eTime = calendar.getTime();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String ShangZhouDay1 = sdf.format(sTime);
		        String ShangZhouDay2 = sdf.format(eTime);
		      String[] shangzhouARR = new String[]{ShangZhouDay1,ShangZhouDay2};
		     return shangzhouARR;
		}
		//获得今天（今日）的日期2017-07-17的格式
		//例如今天2017-07-17 16:00 则返回 2017-07-17
		public static String getTodayBy_yyyyMMdd() {
			 Date d = new Date();  
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		     String dateNowStr = sdf.format(d);  
		     return dateNowStr;
		}
		//获得今天（今日）的日期2017-07-17的格式
				//例如今天2017-07-17 16:00 则返回 2017
				public static String getTodayBy_yyyy() {
					 Date d = new Date();  
				     SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
				     String dateNowStr = sdf.format(d);  
				     return dateNowStr;
				}
		//获得今天（今日）的日期2017-07的格式
				//例如今天2017-07-17 16:00 则返回 2017-07
				public static String getTodayBy_yyyyMM() {
					 Date d = new Date();  
				     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
				     String dateNowStr = sdf.format(d);  
				     return dateNowStr;
				}
		//获得今天（今日）的日期2017-07的格式
		//例如今天2017-07-17 16:00 则返回  07
		public static String getTodayBy_MM() {
			 Date d = new Date();  
		     SimpleDateFormat sdf = new SimpleDateFormat("MM");  
		     String dateNowStr = sdf.format(d);  
		     return dateNowStr;
		}
		/**仅仅获得昨日（昨天）2017-08-08 格式的日期      Yesterday    **/
		public static String getYesterdayBy_yyyyMMdd() {
			Date date=new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE,-1);
			date=calendar.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(date);
		    return dateString;
		}
		//------------------↑ 分割线7  ↑ ----------------------------
		
		//------------------↓ 分割线8-1  ↓ ----------------------------
		//传入字符串形如这样的{c0a8001f-5c68-119d-815c-68141b82000a}返回字符串 'c0a8001f-5c68-119d-815c-68141b82000a'
				//传入字符串形如这样的{c0a8001f-5c68-119d-815c-68141b82000a}{c0a8001f-5c68-119d-815c-68141b82111b}返回字符串 'c0a8001f-5c68-119d-815c-68141b82000a','c0a8001f-5c68-119d-815c-68141b82111b'
	/**JieXiString_ByBiaoJi方法是经过2次改进的方法：它能到达这样的效果：
	 *  例如： String dianpuIDS ="{}{12}{123}{     }{uuu}{}{hao}{c0a8001f-5c68-119d-815c-68141b82000a}";
	 *   经过 JieXiString_ByBiaoJi(dianpuIDS);
	 *   则返回  '','12','123','     ','uuu','','hao','c0a8001f-5c68-119d-815c-68141b82000a'  
	 *   【功能更加强大了】//解析analysis
	 */	
	public static String analysisJsonStringTwo(String dianpuIDS) {
		if(!dianpuIDS.contains("{") && !dianpuIDS.contains("}")){
			return "提示：请传入形如{id1}{id2}{id3}或者{id1}或者{}的字符串我才能解析，必须用{}包裹";
		}
		String ID_in = "";
			ArrayList<String> arr_IDs = new ArrayList<String>(); 
			int count = getCountToTargetString(dianpuIDS,"{");
			for(int x=1;x<=count;x++){
				String subString = SubStringFromMUBIAO(dianpuIDS,x);
				arr_IDs.add(subString);
			}
				if(arr_IDs !=null && arr_IDs.size()>0){
				for(int x=0;x<arr_IDs.size();x++){
					if(x==0){
						ID_in = "\'"+arr_IDs.get(x)+"\'";
					}
					if(x!=0){
						ID_in += ",\'"+arr_IDs.get(x)+"\'";
					}
				}
			}
			 return ID_in;
		}
		/***	analysisJsonStringFive方法专用于解析类似于‘outside{Method{NewAdd{AddDay{07}}}}’	多层结构的字符串，但是注意对{01}{02}{AA}{B}结构的字符串解析结果错误，切记！！！***/
		/* String controlSQLWd = "outside{Method{NewAdd{AddDay{07}}}}";
		 String timeStrA = MyTool.analysisJsonStringFive(controlSQLWd);
		 System.out.println(timeStrA+"     第1层");//  Method{NewAdd{AddDay{07}}}     第1层
		 String timeStrB = MyTool.analysisJsonStringFive(timeStrA);
		 System.out.println(timeStrB+"     第2层");//  NewAdd{AddDay{07}}     第2层
		 String timeStrC = MyTool.analysisJsonStringFive(timeStrB);
		 System.out.println(timeStrC+"     第3层");//  AddDay{07}     第3层
		 String timeStrD = MyTool.analysisJsonStringFive(timeStrC);
		 System.out.println(timeStrD+"     第4层");//  07     第4层*/
	/*	String newStr = "{01}{02}{AA}{B}";
	String Str错误 = MyTool.analysisJsonStringFive(newStr);
	System.out.println(Str错误+"     |本方法遇见这种需求时是错误的解析用法");//01}{02}{AA}{B     |本方法遇见这种需求时是错误的解析用法    */
	public static String analysisJsonStringFive(String dianpuIDS) {
		if(!dianpuIDS.contains("{") && !dianpuIDS.contains("}")){
			return "提示：请传入形如{id1}{id2}{id3}或者{id1}或者{}的字符串我才能解析，必须用{}包裹";
		}
			int countL = getCountToTargetString(dianpuIDS,"{");	 
			int countR = getCountToTargetString(dianpuIDS,"}");	
		if(countL != countR){
			return "提示：请传入符合规则的字符串,成对出现{},如aa{bb{cc{}}};必须用{}成对包裹";
		}
		 int zuo = getStringPositionByRegex(dianpuIDS,"\\{",1);
		 int you = getStringPositionByRegex(dianpuIDS,"\\}",countR);
		 String jiequ = "";
		 if((you-zuo)==1){
			 jiequ = "";
		 }else{
			  jiequ = dianpuIDS.substring(zuo+1, you);
		 }
		 return jiequ;
	}
	/** 新需求：在一个字符串中传入‘控制sql的参数’和‘控制时间的参数’和‘店铺集合’；形如 "Method{Today{{S---tH-d}{ID1}{ID2}{     }{ID3}{E---dT-l}}}"的字符串，解析方法  **/
	//注意必须以‘{S---tH-d}’开头，以{E---dT-l}结尾，中间以{ID1}{ID2}{     }{ID3}形式夹杂店铺ID
	/*//示例1  String str = "Method{Today{{S---tH-d}{ID5}{ID6}{     }{ID789}{E---dT-l}}}";
			String jg = MyTool.analysisJsonStringSix(str);
			System.out.println(jg);//'ID5','ID6','     ','ID789'*/	
	
	/*String strB = "Method{Today{{S---tH-d}{E---dT-l}}}";
	String jgB = MyTool.analysisJsonStringSix(strB);
	System.out.println(jgB);
	//请以{S---tH-d}开头，以{E---dT-l}结尾，并在{S---tH-d}和{E---dT-l}之间塞入{id1}{}{di2}最终形成‘Method{Today{{S---tH-d}{ID1}{ID2}{     }{ID3}{E---dT-l}}}’的字符串，然后解析*/
	
	/*示例3      String strC = "Method{Today{{S---tH-d}{}{E---dT-l}}}";
			   String jgC = MyTool.analysisJsonStringSix(strC);
			   System.out.println(jgC);//'' */
	
	public static String analysisJsonStringSix(String newStr) {
			if(!newStr.contains("{") && !newStr.contains("}")){
				return "提示：注意用{}包裹";
			}
			if(!newStr.contains("{S---tH-d}")){
				return "提示：注意必须以‘{S---tH-d}’开头,中间是{ID1}{ID2}{     }{ID3}集合";
			}
			if(!newStr.contains("{E---dT-l}")){
				return "提示：注意必须以‘{E---dT-l}’结尾,中间是{ID1}{ID2}{     }{ID3}集合";
			}
			int head = (newStr.indexOf("{S---tH-d}")+10);
			int tail = (newStr.indexOf("{E---dT-l}"));
			if(head<tail){
				 String IDResult = analysisJsonStringTwo(newStr.substring(head, tail));//'ID1','ID2','     ','ID3'
				 return IDResult;//解析后 返回    'ID1','ID2','     ','ID3'
			}else{
return "请以{S---tH-d}开头，以{E---dT-l}结尾，并在{S---tH-d}和{E---dT-l}之间塞入{id1}{}{di2}最终形成‘Method{Today{{S---tH-d}{ID1}{ID2}{     }{ID3}{E---dT-l}}}’的字符串，然后解析";
			}
	}
	/** 新需求2：在一个字符串中传入‘控制sql的参数’和‘控制时间的参数’和‘店铺集合’；形如 "Method{Today{{S---tH-d}{ID1}{ID2}{     }{ID3}{E---dT-l}}}"的字符串，解析方法
	 *  [解析其中的时间集合]【注意Today的时间字符串的位置,必须在{S---tH-d}...{E---dT-l}之前紧挨着】
	 *   **/
			/*String newStr = "AAA{OK{Method{Today{{S---tH-d}{}{1}{2}{3}{E---dT-l}}}}}";
			ArrayList<String> arr = MyTool.analysisJsonStringSeven(newStr);
			System.out.println(arr);//[2017-11-30]
			
			String newStrB = "AAA{OK{Method{CuoWu{{S---tH-d}{}{1}{2}{3}{E---dT-l}}}}}";
			ArrayList<String> arrB = MyTool.analysisJsonStringSeven(newStrB);
			System.out.println(arrB);//[]
			
			String newStrC = "AAA{OK{Method{LastMonthOT{{S---tH-d}{}{1}{2}{3}{E---dT-l}}}}}";
			ArrayList<String> arrC = MyTool.analysisJsonStringSeven(newStrC);
			System.out.println(arrC);//[2017-10-01, 2017-10-31]*/
	
	public static List<String> analysisJsonStringSeven(String newStr) {
		 List<String> timeArr = new ArrayList<String>();
		try{
						/*if(!newStr.contains("{") && !newStr.contains("}")){
							return "提示：注意用{}包裹";
						}
						if(!newStr.contains("{{S---tH-d}")){
							return "提示：注意必须以‘{{S---tH-d}’开头,中间是{ID1}{ID2}{     }{ID3}集合";
						}
						if(!newStr.contains("{E---dT-l}")){
							return "提示：注意必须以‘{E---dT-l}’结尾,中间是{ID1}{ID2}{     }{ID3}集合";
						}*/
			int head = (newStr.indexOf("{S---tH-d}")+10);
			int tail = (newStr.indexOf("{E---dT-l}"));
			if(head<tail){
				String index = "{S---tH-d}"+newStr.substring(head, tail)+"{E---dT-l}";
				newStr = new String(newStr.replace(index, "{End}"));
			}
			while(newStr.contains("{")){
				newStr = new String(MyTool.analysisJsonStringFive(newStr));
				if(newStr.endsWith("{{End}}")){
					break;
				}
			}
			newStr =  new String(newStr.replace("{{End}}", ""));
			timeArr = getTimeArrByHtmlParameterString(newStr);
		}catch(Exception e){
			timeArr = new ArrayList<String>();
		}
		return timeArr;
	}
/**
 *   【功能更加强大了】//解析analysis   
 *   对analysisJsonStringTwo再次进行需求更改为analysisJsonStringThree，（需求如下面>内容所示<，电商项目中，Hql查询  SearchCondition.IN 的传入必须是 id1,id2,id3,......样式）
 */	
/* >   StringBuilder ids = new StringBuilder();
for(String id : idSet){
	ids.append(id);
	ids.append(",");
}
ids.append("0");  //注意最后 补个0  结尾
if(StrUtils.objectIsNotNull(ids)){
	params.put("id,"+SearchCondition.IN, ids.toString());
}   <*/
//  下面是‘电商’系统中根据id集合字符串查询内容打印示例
//a9fe3cad-59af-111c-8159-aff51c750008,c0a82801-5ce3-1b64-815c-e3d303450019,0      
//上面id in 查出打印：创意家居,母婴用品,营养保健,德国直邮,香港直邮,促销专区,电器
//-------------------------------------------------------------------------------------
//c0a82801-5ce3-1b64-815c-e3d303450019,0
//上面id in 查出打印：电器
public static String analysisJsonStringThree(String dianpuIDS) {
if(!dianpuIDS.contains("{") && !dianpuIDS.contains("}")){
	return "提示：请传入形如{id1}{id2}{id3}或者{id1}或者{}的字符串我才能解析，必须用{}包裹";
}
String ID_in = "";
	ArrayList<String> arr_IDs = new ArrayList<String>(); 
	int count = getCountToTargetString(dianpuIDS,"{");
	for(int x=1;x<=count;x++){
		String subString = SubStringFromMUBIAO(dianpuIDS,x);
		arr_IDs.add(subString);
	}
		if(arr_IDs !=null && arr_IDs.size()>0){
		for(int x=0;x<arr_IDs.size();x++){
			ID_in += arr_IDs.get(x)+",";
		}
		ID_in += "0";
	}
	 return ID_in;
}
		//------------------↑ 分割线8-1  ↑ ----------------------------
//------------------↓ 分割线8  ↓ ----------------------------
//传入字符串形如这样的{c0a8001f-5c68-119d-815c-68141b82000a}返回字符串 'c0a8001f-5c68-119d-815c-68141b82000a'
		//传入字符串形如这样的{c0a8001f-5c68-119d-815c-68141b82000a}{c0a8001f-5c68-119d-815c-68141b82111b}返回字符串 'c0a8001f-5c68-119d-815c-68141b82000a','c0a8001f-5c68-119d-815c-68141b82111b'
		//JieXiString_ByBiaoJi_1 这个方法存在局限性，只能解析中间的UUID，如果不是UUID就不能用啦
public static String analysisJsonStringOne(String dianpuIDS) {
	if(!dianpuIDS.contains("{")){
		return "提示：请传入形如{id1}{id2}{id3}或者{id1}的字符串我才能解析，必须用{}包裹";
	}
String ID_in = "";
	ArrayList<String> arr_IDs = new ArrayList<String>(); 
	arr_IDs = SplitStringByRegex(dianpuIDS, "\\{{1}.{36}\\}{1}");  
		if(arr_IDs !=null && arr_IDs.size()>0){
		for(int x=0;x<arr_IDs.size();x++){
			if(x==0){
				ID_in = "\'"+arr_IDs.get(x).substring(1, arr_IDs.get(x).length()-1)+"\'";
			}
			if(x!=0){
				ID_in += ",\'"+arr_IDs.get(x).substring(1, arr_IDs.get(x).length()-1)+"\'";
			}
		}
	}
	 return ID_in;
}
//------------------↑ 分割线8  ↑ ----------------------------
		
		//------------------↓ 分割线9  ↓ -------2017-7-21---------------Zero------
		//需求：传入时间字符串集合 ArrayList<String> arr_Date_str (2017-07-01 2017-07-02 2017-07-03 ....)   
		//对arr_Date 进行加工，返回 键是日期，值是字符串"0" 的 ArrayList<Map_BeanWx> mapbeanarr 集合  [Map_BeanWx [key=2017-07-01, value=0], Map_BeanWx [key=2017-07-02, value=0]...]
		public static  ArrayList<MapBean> getMapBeanZeroArray_ByDateStringArray (ArrayList<String> arr_Date_str) {
			MapBean Map_BeanWx = new MapBean();
			ArrayList<MapBean> mapbeanarr = new ArrayList<MapBean>();
			for(int x=0;x<arr_Date_str.size();x++){
				String key1 = arr_Date_str.get(x);
				Map_BeanWx = new MapBean(key1,"0");
				mapbeanarr.add(Map_BeanWx);
				Map_BeanWx = new MapBean();
			}
			return mapbeanarr;
		}
		/** 为了查询某日每个时刻查询的数据是多少而使用此方法|sql语句中用SELECT DATE_FORMAT(ec.RQ, \'%H\')而使用此方法 |注意返回ArrayList<MapBean>类型|查询今日每小时数据用**/
		/*ArrayList<MapBean> arrTest = MyTool.getMapBeanZeroArrByDateStrArrForH();
		System.out.println(arrTest);得到
		  [MapBean [key=00, value=0], MapBean [key=01, value=0], MapBean [key=02, value=0], MapBean [key=03, value=0], 
		   MapBean [key=04, value=0], MapBean [key=05, value=0], MapBean [key=06, value=0], MapBean [key=07, value=0], 
		   MapBean [key=08, value=0], MapBean [key=09, value=0], MapBean [key=10, value=0], MapBean [key=11, value=0], 
		   MapBean [key=12, value=0], MapBean [key=13, value=0], MapBean [key=14, value=0], MapBean [key=15, value=0], 
		   MapBean [key=16, value=0], MapBean [key=17, value=0], MapBean [key=18, value=0], MapBean [key=19, value=0], 
		   MapBean [key=20, value=0], MapBean [key=21, value=0], MapBean [key=22, value=0], MapBean [key=23, value=0]]*/
		public static  ArrayList<MapBean> getMapBeanZeroArrByDateStrArrForH () {
			ArrayList<String> Arr = new ArrayList<String>(); 
			for(int i=0;i<=23;i++){//x轴的坐标是0点,1点，2点，3点....23点   
				if(i<=9){
					Arr.add("0"+i);
				}else{
					Arr.add(""+i);
				}
			}
			ArrayList<MapBean> mapbeanarr = new ArrayList<MapBean>();
			for(int x=0;x<Arr.size();x++){
				String key1 = Arr.get(x);
				mapbeanarr.add(new MapBean(key1,"0"));
			}
			return mapbeanarr;
		}
		//------------------↑ 分割线9  ↑ ----------------------------
		
		//------------------↓ 分割线10  ↓ -------2017-7-25---------------------
		//获得今年的年初时间 和 年末时间                                今年This year
		//例如今天2017-07-25 15:00 则返回 [2017-01-01, 2017-12-31] 的 ArrayList<String>
		public static ArrayList<String> getThisYearOnlySandW_ArryyyyMMdd() {
			Date d = new Date();  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
		    String dateNowStr = sdf.format(d);  
		    ArrayList<String> arr_date_nianChuHeMo = new ArrayList<String>();
			String BenNianChu = dateNowStr+"-01-01";
			String BenNianMo = dateNowStr+"-12-31";
			arr_date_nianChuHeMo.add(BenNianChu);
			arr_date_nianChuHeMo.add(BenNianMo);
		   return arr_date_nianChuHeMo;
		}
		//获得今年的每个月的时间 格式为 年-月     yyyy-MM
		//例如今天2017-07-25 15:00 则返回 [2017-01, 2017-02, 2017-03, 2017-04, 2017-05, 2017-06, 2017-07, 2017-08, 2017-09, 2017-10, 2017-11, 2017-12] 的 ArrayList<String>
		public static ArrayList<String> getThisYearAllMonth_yyyyMM() {
			Date d = new Date();  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
		    String dateNowStr = sdf.format(d);  
		    ArrayList<String> arr_date_MeiYue = new ArrayList<String>();
			String meiyue = "";
			for(int x=1;x<=9;x++){
				meiyue = dateNowStr+"-0"+x;
				arr_date_MeiYue.add(meiyue);
				meiyue = "";
			}
			for(int x=10;x<=12;x++){
				meiyue = dateNowStr+"-"+x;
				arr_date_MeiYue.add(meiyue);
				meiyue = "";
			}
		   return arr_date_MeiYue;
		}
		//获得今年的每个月的时间 格式为 年-月-日     yyyy-MM-dd  【每个月的第一天】
		//例如今天2017-07-25 15:00 则返回 [2017-01-01, 2017-02-01, .。。, 2017-12-01] 的 ArrayList<String>
		public static ArrayList<String> getThisYearAllMonth_FirstDay_yyyyMMdd() {
			ArrayList<String> arr = getThisYearAllMonth_yyyyMM();
			for(int x=0;x<arr.size();x++){
				String str = arr.get(x);
				arr.set(x, str+"-01");
			}
			return arr;
		}
		//获得某天的每个小时的时间 格式为      dd  hour小时
				//例如今天2017-07-25 15:00 则返回 [00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23] 的 ArrayList<String>
				public static ArrayList<String> getTodayAllHour_dd() {
				    ArrayList<String> arr_MeiXiaoShi = new ArrayList<String>();
					String str = "";
					for(int x=0;x<=9;x++){
						str = "0"+x;
						arr_MeiXiaoShi.add(str);
						str = "";
					}
					for(int x=10;x<=23;x++){
						str = ""+x;
						arr_MeiXiaoShi.add(str);
						str = "";
					}
				   return arr_MeiXiaoShi;
				}
		//------------------↑ 分割线10  ↑ ----------------------------
		
		//------------------↓ 分割线11  ↓ -------2017-7-26---------------------
		//需求有两个元素个数相同的ArrayList<Double>，对他们进行相同位置的Double相加返回新的ArrayList<Double>
		//例如 ArrayList<Double> shuzu1 是[1.1, 2.2, 3.3]
		//    ArrayList<Double> shuzu2 是[1.1, 9.0, 3.3]
		//则返回ArrayList<Double> shuzuReturn [2.2, 11.2, 6.6]     //merge合并
		public static ArrayList<Double> mergeArrayDouble(ArrayList<Double> shuzu1,ArrayList<Double> shuzu2) {
			ArrayList<Double> shuzuReturn = new ArrayList<Double>();
			for(int x=0;x<shuzu1.size();x++){
				Double double1 = shuzu1.get(x);
				Double double2 = shuzu2.get(x);
				Double heji =double1+double2;
				shuzuReturn.add(heji);
			}
			return shuzuReturn;
		}
		//根据项目需求，需要对7个元素个数相同的ArrayList<Double>，对他们进行相同位置的Double相加返回新的ArrayList<Double>
		//所有有了下面的方法
		public static ArrayList<Double> mergeArrayDouble_7(ArrayList<Double> shuzu1,ArrayList<Double> shuzu2,ArrayList<Double> shuzu3,ArrayList<Double> shuzu4,ArrayList<Double> shuzu5,ArrayList<Double> shuzu6,ArrayList<Double> shuzu7) {
			ArrayList<Double> heji1 = mergeArrayDouble(shuzu1,shuzu2);
			ArrayList<Double> heji2 = mergeArrayDouble(heji1,shuzu3);
			ArrayList<Double> heji3 = mergeArrayDouble(heji2,shuzu4);
			ArrayList<Double> heji4 = mergeArrayDouble(heji3,shuzu5);
			ArrayList<Double> heji5 = mergeArrayDouble(heji4,shuzu6);
			ArrayList<Double> heji6 = mergeArrayDouble(heji5,shuzu7);
			return heji6;
		}
		//------------------↑ 分割线11  ↑ ----------------------------
		
		//------------------↓ 分割线12  ↓ -------2017-7-28---------------------
		/** 获取下个月第一天的时间；  yyyy-MM-dd 格式 **/
		 /*String formatStr = nextMonthFirstDate();
		 System.out.println("现在时间是2017-11-14,   下个月第一天是    "+formatStr);//现在时间是2017-11-14,   下个月第一天是    2017-12-01 */
		    public static String nextMonthFirstDate() {
		        Calendar calendar = Calendar.getInstance();
		        calendar.set(Calendar.DAY_OF_MONTH, 1);
		        calendar.add(Calendar.MONTH, 1);
		        Date nextMonthFirstDate = calendar.getTime();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String formatStr = sdf.format(nextMonthFirstDate);
		        return formatStr;
		    }
		/**
		 * 只获得本月初和本月末两个日期。
		 * 如今日是2017-07-28 则返回一个字符串数组：[2017-07-01,2017-07-31]   This month 本月
		 */
		public static String[] getThisMonth_SOnlyAndW() {
			Calendar now = Calendar.getInstance();  
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH)+1;
		    String lastDayOfMonth = getLastDayOfMonth(year, month);//2017-07-31
		    String firstDayOfMonth = "";
	     if(month<=9){
	    	 firstDayOfMonth =year+"-0"+month+"-01";
	     }else{
	    	 firstDayOfMonth =year+"-"+month+"-01";
	     }
		     String[] BenYueChu_OnlyAnd_BenYueMo = new String[2];
		     BenYueChu_OnlyAnd_BenYueMo[0] = firstDayOfMonth;
		     BenYueChu_OnlyAnd_BenYueMo[1] = lastDayOfMonth;
		     return BenYueChu_OnlyAnd_BenYueMo;
		}
		/**
		 * 只获得下月初和下月末两个日期。S首  W尾
		 */
		 /*System.out.println("现在是2017-11-14 |下月第一天" +MyTool.getNextMonth_SOnlyAndW()[0]);
		 System.out.println("现在是2017-11-14 |下月最后1天" +MyTool.getNextMonth_SOnlyAndW()[1]);
		 现在是2017-11-14 |下月第一天2017-12-01
		 现在是2017-11-14 |下月最后1天2017-12-31*/
		public static String[] getNextMonth_SOnlyAndW() {
			String firstDayOfMonth = nextMonthFirstDate();
			int year  = Integer.parseInt(firstDayOfMonth.substring(0, 4));
			int month = Integer.parseInt(firstDayOfMonth.substring(5, 7));
		    String lastDayOfMonth = getLastDayOfMonth(year, month);
		     String[] BenYueChu_OnlyAnd_BenYueMo = new String[2];
		     BenYueChu_OnlyAnd_BenYueMo[0] = firstDayOfMonth;
		     BenYueChu_OnlyAnd_BenYueMo[1] = lastDayOfMonth;
		     return BenYueChu_OnlyAnd_BenYueMo;
		}
		//获得ArrayList<Integer> arr 所有元素的合计值   Total
		public static Integer totalArrayIntOne(ArrayList<Integer> arr) {
			Integer i =0;
			for(int x=0;x<arr.size();x++){
				i +=arr.get(x);
			}
			return i;
		}
		//获得今天是本月的第几天   position位置
		//例如今天是2017-7-31 则返回31  代表今天是本月的第31天
		public static int getTodayPositionFromThisMonth() throws ParseException {
			String benyue1 = getNowDateBy_yyyyMMdd();
			ArrayList<String> arr_BenYue = getTheMonthAllDayArrayByDateString(benyue1);
			String jintian = getTodayBy_yyyyMMdd();
			int dijitian = 0;
			for(int x=0;x<arr_BenYue.size();x++){
				if(jintian.equals(arr_BenYue.get(x))){
					dijitian = (x+1);
					break;
				}
			}
			return dijitian;
		}
				//根据输入的日期获得这个月的所有天数
				//只要给我个日期我就返回这个月的所有天数，注意格式    
				//例如：  当前时间是"2017-06-12" 我就返回从2017-06-01.....到2017-06-30
			//再例如：今天是‘2017-09-01’则获得 从2017-09-01.....到‘2017-09-30’ 的【本月时间集合】【当前月的时间集合】 【现在的月】
				public static ArrayList<String> getThisMonthAllDayArray() throws ParseException {
					String today = getTodayBy_yyyyMMdd();
					ArrayList<String> ThisMonthAllDay = getTheMonthAllDayArrayByDateString(today);
					return ThisMonthAllDay;
				}
				//根据MySql 的  QUARTER 季度查询封装季度的key 集合
				//MySql 的  QUARTER 第一季度 01,02，03    |    QUARTER 第二季度 04,05，06   |QUARTER 第三季度 07,08，09  |QUARTER 第四季度 10,11,12  阳历月份
				//MySql 的  QUARTER 的  QUARTER 查询结果返回   1,2,3,4   其中1代表第一季度
				public static ArrayList<String> getArrayString_ByMySqlQUARTER() throws ParseException {
					String a = "1";
					String b = "2";
					String c = "3";
					String d = "4";
					ArrayList<String> QuarterArr = new ArrayList<String>();
					QuarterArr.add(a);QuarterArr.add(b);QuarterArr.add(c);QuarterArr.add(d);
					return QuarterArr;
				}
				//配合  getArrayString_ByMySqlQUARTER 使用，封装格式化显示  第？季度  如显示【第1季度】
				//根据MySql 的  QUARTER 季度查询封装季度的key 集合
				//MySql 的  QUARTER 第一季度 01,02，03    |    QUARTER 第二季度 04,05，06   |QUARTER 第三季度 07,08，09  |QUARTER 第四季度 10,11,12  阳历月份
				//MySql 的  QUARTER 的  QUARTER 查询结果返回   1,2,3,4   其中1代表第一季度
				//配合  getArrayString_ByMySqlQUARTER 使用，封装格式化显示  第？季度  如显示【第1季度】
				public static ArrayList<String> getArrayStringMySqlQUARTER_Format(ArrayList<String> QuarterArr) throws ParseException {
					for(int x=0;x<QuarterArr.size();x++){
						String str = QuarterArr.get(x);
						QuarterArr.set(x, "第"+str+"季度");
					}
					return QuarterArr;
				}
				//配合  getArrayString_ByMySqlQUARTER 使用，封装格式化显示  第？季度  如显示【第1季度】  
				//第2个需求处理时
				public static ArrayList<String> getArrayStringMySqlQUARTER_FormatTwo(ArrayList<String> QuarterArr) throws ParseException {
					for(int x=0;x<QuarterArr.size();x++){
						QuarterArr.set(x, "第"+(x+1)+"季度");
					}
					return QuarterArr;
				}
		//------------------↑ 分割线13  ↑ -------2017-7-31---------------------
		/** 2017-08-06 guo  只获得上个月的月初时间和月末时间；并返回String[]{上月月初，上月月末}  @throws ParseException  **/
		public static String[] getLastMonth_SOnlyAndW() throws ParseException {
			String ShangYueChu ="";
			String ShangYueMo ="";
			Calendar now = Calendar.getInstance();  
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH)+1;
			String benyuedeshijian = "";
			 if(month<=9){
				 benyuedeshijian =year+"-0"+month+"-15";
		     }else{
		    	 benyuedeshijian =year+"-"+month+"-15";
		    }
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatB = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat formatC = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatD = new SimpleDateFormat("MM");
			Calendar calendar = Calendar.getInstance();
			Date benyuedeshijianD = format.parse(benyuedeshijian);
			calendar.setTime(benyuedeshijianD);
			calendar.add(Calendar.MONTH, -1);
			String format2 = formatB.format(calendar.getTime());
			ShangYueChu = format2 +"-01";
			int yearA = Integer.parseInt(formatC.format(calendar.getTime())); 
			int monthA = Integer.parseInt(formatD.format(calendar.getTime()));
			ShangYueMo = getLastDayOfMonth(yearA, monthA);
		    String[] ShangYueChu_OnlyAnd_ShangYueMo = new String[2];
		    ShangYueChu_OnlyAnd_ShangYueMo[0] = ShangYueChu;
		    ShangYueChu_OnlyAnd_ShangYueMo[1] = ShangYueMo;
		    return ShangYueChu_OnlyAnd_ShangYueMo;
		}
		 /**
	    	获取某字符串在整个字符串中出现的次数。  target目标
	    	例如		String s1 = "{0}{12}{123}{}{123456}";  
	     			String s2 = "{";  
	     			int count = this.getCount(s1,s2); 
	     			那么得到 s2在s1中出现了5次    ：count=5    
		  **/  
	    public static int getCountToTargetString(String str,String sub){  
	        int index = 0;  
	        int count = 0;  
	        while((index = str.indexOf(sub,index))!=-1){  
	      
	            index = index + sub.length();  
	            count++;  
	        }  
	        return count;  
	    }
	    /**从形如{}{123}目标字符串中截取花括号之间的字符串 **/ 
	    public static String SubStringFromMUBIAO(String MUBIAO,int cishu){
	    	String jiequ ="";
		     int zuo = getStringPositionByRegex(MUBIAO,"\\{",cishu);
		     int you = getStringPositionByRegex(MUBIAO,"\\}",cishu);
		     if((you-zuo)==1){
		    	 jiequ = "";
		     }else{
		    	  jiequ = MUBIAO.substring(zuo+1, you);
		     }
		     return jiequ;
		}
	    /** 获得本年的所有天数日期集合，   yyyy-MM-dd 格式   guo 2017-08-12
		 * 例如：今天是2017-08-12  则获得 2017-01-01 2017-01-02.....2017-12-31  共计365天，自动区分平年 闰年 
		 *  **/
		public static ArrayList<String> getThisYearAllDayArrayBy_yyyyMMdd() throws ParseException {
			String get_JinTianRiQI_yyyy = getTodayBy_yyyy();
			String start_date_Start = get_JinTianRiQI_yyyy+"-01-01";
			String start_date_End   = get_JinTianRiQI_yyyy+"-12-31";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(start_date_Start);
			long time1 = daystart.getTime();
			long time2 = 0l;
			ArrayList<Long> arrLong = new ArrayList<Long>();
			for(int x=0;x<=366;x++){
				time2 = time1 + 1000*60*60*24l*x ;
				arrLong.add(time2);
			}
			ArrayList<String> arrStr = new ArrayList<String>();
			for(int x=0;x<arrLong.size();x++){
				Long time = arrLong.get(x);
				 GregorianCalendar gc = new GregorianCalendar();
		         gc.setTimeInMillis(time);
		         String format001 = df.format(gc.getTime());
		         arrStr.add(format001);
		         if(format001.equals(start_date_End)){
		        	 break;
		         }
			}
			return arrStr;
		}
		/** 获得本年的仅仅两天，第一天和最后一天，正序，返回 ArrayList<String> 比如今天是2018-01-11 则此方法返回 [2018-01-01, 2018-12-31] **/
		public static ArrayList<String> getThisYearOnlyTwoDay() throws ParseException {
			String strToday = getTodayBy_yyyy();
			ArrayList<String> arr = new ArrayList<String>();
			arr.add(strToday+"-01-01");
			arr.add(strToday+"-12-31");
			return arr;
		}
		/**
		 * 把yyyy-MM-dd"的字符串的2017-12-23 变成毫秒数  其实  2017-12-23默认是 2017-12-23 00:00:00
		 * @throws ParseException 
		 */
		public static Long parseDateStryyyyMMddToLong(String nianyueri) throws ParseException{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(nianyueri);
			long time = daystart.getTime();
			return time;
		}
		/**业务需要把"2017-07-29 00:00:00" 这个格式下的时间字符串，截取出月和日  返回 07.29 **/
		public static String substringDateStrOne(String str){
			String substring1 = str.substring(5, 7);
			String substring2 = str.substring(8, 10);
			String returnStr = substring1+"."+substring2;
			return returnStr;
		}
		/**业务需要把"2017-09-17" 这个格式下的时间字符串，截取出月和日  返回 09.17 **/
		public static String substringDateStrTwo(String str){
			String substring1 = str.substring(5, 7);
			String substring2 = str.substring(8, 10);
			String returnStr = substring1+"."+substring2;
			return returnStr;
		}
		
		public static ArrayList<String> substringDateStrTwo_Array(ArrayList<String> strarr){
			ArrayList<String> arr = new ArrayList<String>();
			for(int x=0;x<strarr.size();x++){
				String strin = strarr.get(x);
				arr.add(substringDateStrTwo(strin));
			}
			return arr;
		}
		/** 获得最近30天的时间段，仅仅返回首部，尾部，两个时间点 yyyy-MM-dd 格式   Lately最近
		 *  比如今天 2017年8月14日 则返回[2017-07-16 ,2017-08-14]
		 **/
		public static String[] getLately30DayOnlySWyyyyMMdd(){
			ArrayList<String> zuijinSanShiTian = getNewlyDateArrayByInt(-29);
			String string30Start = zuijinSanShiTian.get(29);
			String string30End = zuijinSanShiTian.get(0);
			String[] arr = new String[2];
			arr[0] = string30Start;
			arr[1] = string30End;
			return arr;
		}
		/** 
		 *  根据用户的生日Date类型，对比当前日期，返回其年龄（周岁）
		 **/
		public static int getAge(Date birthDay) throws Exception { 
	        Calendar cal = Calendar.getInstance(); 
	        if (cal.before(birthDay)) {//如果出生日期大于当前时间，则抛出异常 
	        	 return 0;
	        } 
	        int yearNow = cal.get(Calendar.YEAR); 
	        int monthNow = cal.get(Calendar.MONTH); 
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
	        cal.setTime(birthDay); 
	        int yearBirth = cal.get(Calendar.YEAR); 
	        int monthBirth = cal.get(Calendar.MONTH); 
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
	        int age = yearNow - yearBirth; 
	        if (monthNow <= monthBirth) { 
	            if (monthNow == monthBirth) { 
	                if (dayOfMonthNow < dayOfMonthBirth) age--; 
	            }else{ 
	                age--; 
	            } 
	        } 
	        return age; 
	    }
		/** 
		 *  根据用户的生日Date类型，对比当前日期，返回其年龄（周岁） yyyyMMdd
		 **/
		public static Date getDateByyyyyMMdd(String yyyyMMdd) throws Exception { 
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(yyyyMMdd);
			return date;
		}
		/**
		 * 获得最近30天的时间集合（正序），yyyyMMdd格式，如今天是2017-08-16则返回 [2017-07-18, 2017-07-19,....., 2017-08-16]
		 */
		
		public static ArrayList<String> getLately30DayArray_yyyyMMdd_ZX() {
			ArrayList<String> zuijinSanShiTian =  MyTool.getNewlyDateArrayByInt(-29);
			for (int start = 0, end = zuijinSanShiTian.size() - 1; start < end; start++, end--) {
				String temp = zuijinSanShiTian.get(end);
				zuijinSanShiTian.set(end, zuijinSanShiTian.get(start));
				zuijinSanShiTian.set(start, temp);
			}
			return zuijinSanShiTian;
		}
		/**对get_Date_StartTOEnd_ByDays方法进行改进（get_Date_StartTOEnd_ByDays_B），自定义首尾时间的最大间隔天数，比如定间隔最多30天，则最多显示30天集合【正序】 **/
		//输入开始时间字符串  start_date_Start 2017-01-01    
		//输入结束时间字符串  start_date_End   2017-01-31
		//返回包含头包含尾的所有天数  2017-01-01 2017-01-02 .....一直到2017-01-31
				//get_Date_StartTOEnd_ByDays_B 方法说明2：
				//ArrayList<String> timeArr = MyTool.get_Date_StartTOEnd_ByDays_B("2017-01-01","2017-10-16",7);
				//2017-01-01 到 2017-10-16 已经超过 7天了， 则返回：[2017-01-01, 2017-01-02, 2017-01-03, 2017-01-04, 2017-01-05, 2017-01-06, 2017-01-07]
				//就是返回以开始时间为头部，后推7天的正序时间集合
				public static ArrayList<String> get_Date_StartTOEnd_ByDays_B(String start_date_Start ,String start_date_End,int ShouWeiChaTianShu) throws ParseException {
					
					// 实践证明：原先的 get_Date_StartTOEnd_ByDays_B 方法存在bug，存在问题，现在用正确的  get_Date_StartTOEnd_ByDays_C代替
					ArrayList<String> arrStr = get_Date_StartTOEnd_ByDays_C(start_date_Start,start_date_End,ShouWeiChaTianShu);
					return arrStr;
				}
				/**对get_Date_StartTOEnd_ByDays方法进行改进（get_Date_StartTOEnd_ByDays_C），自定义首尾时间的最大间隔天数，比如定间隔最多30天，则最多显示30天集合【正序】 **/
				//get_Date_StartTOEnd_ByDays_C 方法说明：
				//ArrayList<String> timeArr = MyTool.get_Date_StartTOEnd_ByDays_C("2017-01-01","2017-10-16",7);
				//2017-01-01 到 2017-10-16 已经超过 7天了， 则返回：[2017-10-10, 2017-10-11, 2017-10-12, 2017-10-13, 2017-10-14, 2017-10-15, 2017-10-16]   7天集合
				//就是返回以结束时间为尾部，倒推7天的正序时间集合
				//再比如ArrayList<String> timeArr = MyTool.get_Date_StartTOEnd_ByDays_C("2017-01-15","2017-12-01",2);//得到[2017-11-30, 2017-12-01]
				//再比如ArrayList<String> timeArr = MyTool.get_Date_StartTOEnd_ByDays_C("2017-01-01","2017-08-03",1);//得到[2017-08-03]
				//再比如ArrayList<String> timeArr = MyTool.get_Date_StartTOEnd_ByDays_C("2017-01-01","2017-01-03",90);//得到[2017-01-01, 2017-01-02, 2017-01-03]
				public static ArrayList<String> get_Date_StartTOEnd_ByDays_C(String start_date_Start ,String start_date_End,int ShouWeiChaTianShu) throws ParseException {
					/*Calendar dayc1 = new GregorianCalendar();*/
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date dayEnd = df.parse(start_date_End);
					long time1 = dayEnd.getTime();
					long time2 = 0l;
					ArrayList<Long> arrLong = new ArrayList<Long>();
					for(int x=0;x<ShouWeiChaTianShu;x++){
						time2 = time1 - 1000*60*60*24l*x ;
						arrLong.add(time2);
					}
					ArrayList<String> arrStr = new ArrayList<String>();
					for(int x=0;x<arrLong.size();x++){
						Long time = arrLong.get(x);
						 GregorianCalendar gc = new GregorianCalendar();
				         gc.setTimeInMillis(time);
				         String format001 = df.format(gc.getTime());
				         arrStr.add(format001);
				         if(format001.equals(start_date_Start)){
				        	 break;
				         }
					}
					for (int start = 0, end = arrStr.size() - 1; start < end; start++, end--) {
						String temp = arrStr.get(end);
						arrStr.set(end, arrStr.get(start));
						arrStr.set(start, temp);
					}
					return arrStr;
				}
		/** 当页面显示如：0.00元 今日总营收  环比+0.00%，昨日0.00元；其中的环比数据用本方法计算：注意分子本期数据，分母上期数据
		 *  （并注意，在页面上如何传值）,效果如下
		 * **/
//				String 模拟数据1 = HandleToolwx.getHuanBi_A(18,3);System.out.println(模拟数据1+"模拟数据1");//+500.00
//				String 模拟数据2 = HandleToolwx.getHuanBi_A(3,19);System.out.println(模拟数据2+"模拟数据1");//-84.21
//				String 模拟数据3 = HandleToolwx.getHuanBi_A(18,0);System.out.println(模拟数据3+"模拟数据1");//+0.00
//				String 模拟数据4 = HandleToolwx.getHuanBi_A(0,0);System.out.println(模拟数据4+"模拟数据4");//+0.00
//              $("#hjyj20e").html(json.MKE+"%");  环比<strong class="red" id="hjyj20e">+0.00%</strong>
				//   Mom环比
				
				/*String 测试1 = MyTool.getMomOne(11.01, 22.01);
				String 测试2 = MyTool.getMomOne(11.01, 0.0);
				String 测试3 = MyTool.getMomOne(0.0, 11.01);
				String 测试4 = MyTool.getMomOne(333, 11.01);
				System.out.println(测试1);-49.98
				System.out.println(测试2);+0.00
				System.out.println(测试3);-100.00
				System.out.println(测试4);+2924.52*/
				
					/*String newA = MyTool.getMomOne(0,0);System.out.println(newA+"  最新环比规则   newA");//+0.00   最新环比规则  newA
最新的环比规则，从ERP项目开始	 String newB = MyTool.getMomOne(80,0);System.out.println(newB+"  最新环比规则   newB");//+100.00   最新环比规则  newB
					String newC = MyTool.getMomOne(-82,0);System.out.println(newC+"  最新环比规则   newC");//-100.00   最新环比规则  newA*/
				
				public static String getMomOne(double FenZi_BenQi,double FenMu_ShangQi) {
					//开始计算环比
					String huanbi = "";
					double hou_Jintian  = FenZi_BenQi;
					double qian_Zuotian = FenMu_ShangQi;
					double num= ((hou_Jintian-qian_Zuotian)/qian_Zuotian)*100; //y轴 百分数，所以乘100
					String jieguo = String.format("%.2f", num);//保留两位小数//String.format("%.2f", num)可以处理科学计数法的问题
					 /*if("Infinity".equals(jieguo) || "NaN".equals(jieguo) || jieguo.contains("f") || jieguo.contains("N") ){*/
					 if(jieguo.contains("f") || jieguo.contains("N") ){ 
						 if(qian_Zuotian == 0 && hou_Jintian >0){//如果本期大于0；  上期等于0，则 返回 +100.00 //最新的环比规则，从ERP项目开始
							 huanbi = "+100.00";
						 }else if(qian_Zuotian == 0 && hou_Jintian <0){//如果本期小于0；  上期等于0，则 返回 -100.00 //最新的环比规则，从ERP项目开始
							 huanbi = "-100.00";
						 }else{
							 huanbi = "+0.00";//（这里根据需要可以改成‘不统计’）（处理null值问题）（原先是 huanbi ="不统计"） //否则本期等于0，上期也等于0，就返回 +0.00 //最新的环比规则，从ERP项目开始
						 }
					 }else{
						 if(jieguo.contains("-")){
							 huanbi = jieguo;
						 }else{
							 huanbi = "+"+jieguo;
						 }
					 }//计算环比结束
					 return huanbi;
				}
				/** 需求：计算环比或者同比数据，getMomStr(3,5)得到-40.00；代表本期较上期增长-40.00%；需求自己组装%号   @param thisPeriodData代表本期数据 @param lastPeriodData代表上期数据 **/
				public static String getMomStr(double thisPeriodData,double lastPeriodData) {
					String momStr = "0.00";
					if(lastPeriodData==0.0 && thisPeriodData>0.0){//如果上期为0.0而且本期大于0.0；直接返回100.00，代表增长100.00%
						return "100.00";
					}else if(lastPeriodData==0.0 && thisPeriodData<0.0){//如果上期为0.0而且本期也为负数 -1.1；分母不能为0，直接返回-100.00，代表增长-100.00%
						return "-100.00";
					}else if(lastPeriodData==0.0 && thisPeriodData==0.0){//如果上期为0.0而且本期也为0.0；分母不能为0，直接返回0.00，代表增长0.00%
						return "0.00";
					}else{
						double num= ((thisPeriodData-lastPeriodData)/lastPeriodData)*100;//（本期-上期）/上期//已经测试就算num = 1115555555.8765;也不会出现科学数
						momStr = String.format("%.2f", num);//保留两位小数//String.format("%.2f", num)可以处理科学计数法的问题
					}
					return momStr; 
				}
			/**
			 * 根据需求这次不是计算数据块中的环比，而是计算一个月每日的数据集合的环比，那么就不能用getMomOne方法了，而用getMomTwo方法；（当分母为0时返回0，不返回null）
			 */
			public static Double getMomTwo(double FenZi_BenQi,double FenMu_ShangQi) {
				String huanbi = getMomOne(FenZi_BenQi,FenMu_ShangQi);
				return Double.parseDouble(huanbi);
			}
			/**
			 * 根据需求计算视图的环比问题，（当分母为0时返回0，不返回null），用于页面 ‘环比展示’
			 */
			//例如传入ArrayList<Double> dataArr：[1.1, 2.2, 3.3, 1.3] 返回计算好的环比集合：[0.0, 100.0, 50.0, -60.61]
			//再例如传入ArrayList<Double> dataArr：[6.6] 返回计算好的环比集合：[0.0]
			//再例如传入ArrayList<Double> dataArr：[0.1, 3.9] 返回计算好的环比集合：[0.0, 3800.0]
			public static ArrayList<Double> calculationMomView(ArrayList<Double> dataArr) {
				ArrayList<Double> momArr = new ArrayList<Double>();		
				if(dataArr.size()>=2){
					for(int x=0;x<dataArr.size()-1;x++){
						if(x==0){
							momArr.add(0.0);
						}
						Double benqi = dataArr.get(x+1);//本期
						Double shangqi = dataArr.get(x);//上期
						momArr.add(getMomTwo(benqi,shangqi));
					}
				}
				if(dataArr.size()==1){
					momArr.add(0.0);
				}
				return momArr;
			}
			/**  根据已知时间段，获得他的环比上期时间段，1例如已知今天的环比时间段是昨天；   2；例如[12月4日，12月5日，12月6日]时间段的环比时间段是[12月1日，12月2日，12月3日]
			 * 	 目的用于：环比计算 maxTimeSpan最大时间跨度； 默认为3年1095天，当 输入值大于1095天时，为你输入的值，否则就是默认值1095天
			 * @throws ParseException 
			 */
			/*示例：ArrayList<String> timeArrObj = MyTool.getTimeArrByHtmlParameterString("-Lately-Day{4}");
				System.out.println(timeArrObj);//[2017-12-06, 2017-12-07, 2017-12-08, 2017-12-09]//本期
				ArrayList<String> momTimeArrByArr = MyTool.getMomTimeArrByArr(timeArrObj,80);
				System.out.println(momTimeArrByArr);//[2017-12-02, 2017-12-03, 2017-12-04, 2017-12-05]//上期也就是环比*/
			public static ArrayList<String> getMomTimeArrByArr(ArrayList<String> timeArr,int maxTimeSpan) throws ParseException {
				ArrayList<String> momTimeArr = new ArrayList<String>();
				int maxTimeSpanDefault = 1095;//默认时间段跨度 默认为3年1095天
				if(maxTimeSpan >= 1096){
					maxTimeSpanDefault = maxTimeSpan;
				}
				String dateStrStart = timeArr.get(0);
				String dateStrEnd = timeArr.get(timeArr.size()-1);
				Integer timeDifference = (getTimeDifference(dateStrStart,dateStrEnd))+1;//含头部，含尾部，差几天
				String dateStrStartMom = dateReduceOrAddByInt(dateStrStart,-timeDifference);
				String dateStrEndMom = dateReduceOrAddByInt(dateStrEnd,-timeDifference);
				momTimeArr = get_Date_StartTOEnd_ByDays_C(dateStrStartMom,dateStrEndMom,maxTimeSpanDefault);
				return momTimeArr;
			}
			/**
			 * 根据需求计算视图的同比问题，（当分母为0时返回0，不返回null），用于页面 ‘同比展示’
			 */
			//同比An
			//示例1：
			//同比发展速度=本期发展水平/去年同期水平×100%；
			//本期2月比去年2月    
			//同比： （今年-去年）/去年
			//ArrayList<Double> inArrLastYear = new ArrayList<Double>();//inArrLastYear去年的数据集合   上期
			//inArrLastYear.add(0.0);inArrLastYear.add(4.0);inArrLastYear.add(5.3);inArrLastYear.add(0.0);
			//ArrayList<Double> inArrThisYear = new ArrayList<Double>();//inArrThisYear今年的数据集合   本期
			//inArrThisYear.add(0.0);inArrThisYear.add(1.0);inArrThisYear.add(2.2);inArrThisYear.add(0.0);
			//MyTool.calculationAnView(inArrThisYear,inArrLastYear);//calculationAnView(本期,上期);//则返回‘同比集合’ArrayList<Double> momArr：[0.0, -75.0, -58.49, 0.0]
			public static ArrayList<Double> calculationAnView(ArrayList<Double> inArrThisYear,ArrayList<Double> inArrLastYear) {//同比An
				ArrayList<Double> momArr = new ArrayList<Double>();
				if(inArrLastYear.size()==inArrThisYear.size()){
					for(int x=0;x<inArrThisYear.size();x++){
						Double benqi = inArrThisYear.get(x);
						Double shangqi = inArrLastYear.get(x);
						Double tongBi = getMomTwo(benqi,shangqi);
						momArr.add(tongBi);
					}
				}
				return momArr;
			}
			/**针对电商的‘同比’专门写的方法，使用此功能时请及时测试结果  (必须已知json字符串，已知其键，已知其值时数组类型)**/
			public static ArrayList<Double> calculationAnByJsonString(String jsonString,String jsonKey) {
				ArrayList<Double>  douArr = new ArrayList<Double>();
				JSONObject jsonObjB = new JSONObject(jsonString);//解析第2步
				JSONArray jsonArrayB = jsonObjB.getJSONArray(jsonKey);//解析第3步（按说这里应该判断是否存在的！！！）
				for (int x = 0; x < jsonArrayB.length(); x++) {//解析第4步
					Object shuzuYuanSuB = jsonArrayB.get(x);
					douArr.add(Double.parseDouble(shuzuYuanSuB.toString()));
				}
				return douArr;
			}
		/**
		 * 给定当前时间年月日 "2017-10-20" 类型 ，获得当前季度的开始时间和结束时间
		 * 第一季度 1-3； 第二季度 4-6； 第三季度7-9； 第四季度 10-12 ；
		 * **/  //quarter季度
		public static String[] getThisQuarter_StartOnlyAndEnd(String Datestr) throws ParseException {
			String yueStr = Datestr.substring(5, 7);
			int yue = Integer.parseInt(yueStr);
			String nianStr = Datestr.substring(0, 4);
			int nian = Integer.parseInt(nianStr);
			String StartTime = "";
			String EndTime = "";
			if(yue>=1 && yue<=3){
				StartTime = nian+"-01-01";
				EndTime = nian+"-03-31";
			}
			if(yue>=4 && yue<=6){
				StartTime = nian+"-04-01";
				EndTime = nian+"-06-30";
			}
			if(yue>=7 && yue<=9){
				StartTime = nian+"-07-01";
				EndTime = nian+"-09-30";
			}
			if(yue>=10 && yue<=12){
				StartTime = nian+"-10-01";
				EndTime = nian+"-12-31";
			}
			String[] DangQianJiDu_Start_OnlyAnd_End = new String[]{StartTime,EndTime};
			return DangQianJiDu_Start_OnlyAnd_End;
		}
		/**
		 * 给定当前时间年月日 "2017-10-20" 类型 ，获得上一个季度的开始时间和结束时间 上季度
		 * 第一季度 1-3； 第二季度 4-6； 第三季度7-9； 第四季度 10-12 ；
		 * **/
		public static String[] getLastQuarter_StartOnlyAndEnd(String Datestr) throws ParseException {
			String yueStr = Datestr.substring(5, 7);
			int yue = Integer.parseInt(yueStr);
			String nianStr = Datestr.substring(0, 4);
			int nian = Integer.parseInt(nianStr);
			String StartTime = "";
			String EndTime = "";
			if(yue>=1 && yue<=3){
				StartTime = (nian-1)+"-10-01";
				EndTime = (nian-1)+"-12-31";
			}
			if(yue>=4 && yue<=6){
				StartTime = nian+"-01-01";
				EndTime = nian+"-03-31";
			}
			if(yue>=7 && yue<=9){
				StartTime = nian+"-04-01";
				EndTime = nian+"-06-30";
			}
			if(yue>=10 && yue<=12){
				StartTime = nian+"-07-01";
				EndTime = nian+"-09-30";
			}
			String[] ShangJiDu_Start_OnlyAnd_End = new String[]{StartTime,EndTime};
			return ShangJiDu_Start_OnlyAnd_End;
		}
		/**
		 * 根据需求，只取小数的整数部分并返回这个整数 （包括科学计数法时也能处理）
		 * **/
		public static Integer getIntFromDouble(Double douStr) {
			String str = ""+douStr;
			if(str.contains("E")){//出现科学计数法时
				 str = new BigDecimal(str).toPlainString();
			}
			if(str.contains(".")){
			  return Integer.valueOf(str.substring(0,str.lastIndexOf(".")));
			}else{
			  return Integer.valueOf(str);
			}
		}
		/**
		 * 四舍五入  保留2位小数 返回           四舍五入Rounding      |   小数decimal
		 * **/
		public static Double roundingTwoDecimal(Double XiaoShu) {
			BigDecimal bg = new BigDecimal(XiaoShu);
			Double BaoLiuHou = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return BaoLiuHou;
		}
		/**
		 * 四舍五入保留指定几位小数返回 
		 *  * @param douNum 第一个参数，是Double类型小数， 
		 	* @param intAppointDecimal 第二个参数，要保留的几位小数，输入2保留2位，输入4保留4位小数
		 * **/
		public static Double roundingDoubleAppointDecimal(Double douNum,int intAppointDecimal) {
			BigDecimal bg = new BigDecimal(douNum);
			Double BaoLiuHou = bg.setScale(intAppointDecimal, BigDecimal.ROUND_HALF_UP).doubleValue();
			return BaoLiuHou;
		}
		/**
		 * 四舍五入  保留2位小数 返回           四舍五入Rounding      |   小数decimal
		 * **/
		/*Double roundingTwoDecimalTwo = MyTool.roundingTwoDecimalTwo("139.17964199999998");
		System.out.println(roundingTwoDecimalTwo+" @");//139.18 @*/
		public static Double roundingTwoDecimalTwo(String XiaoShu) {
			BigDecimal bg = new BigDecimal(Double.parseDouble(XiaoShu));
			Double BaoLiuHou = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return BaoLiuHou;
		}
		/** 只负责留2位小数，不足2位补齐0;如果是null或空字符串则返回空串;  注意：此方法不负责四舍五入 ，包括对科学计数法字符串处理
		/** <br><H1><font color="deeppink">特别注意:formatObjToNumStr方法只负责留下2位小数,2位后面的小数不负责'四舍五入'</font><H1><br> **/
		//	String A = formatObjToNumStr(null);
		//	System.out.println(A);//返回空串
			//	String B = formatObjToNumStr("");
			//	System.out.println(B);//也返回空串
				//	String C = formatObjToNumStr("6");
				//	System.out.println(C);//返回6.00
					//	String D = formatObjToNumStr("123.4");
					//	System.out.println(D);//返回123.40
						// String E = formatObjToNumStr("123.45");
						// System.out.println(E);//返回123.45
							//	String F = formatObjToNumStr("123.1122222");
							//	System.out.println(F);//返回123.45
								// String G = formatObjToNumStr("123.118888");
								// System.out.println(G);//返回123.11
	public static String formatObjToNumStr(Object obj) {
		String str = "";
		if(obj !=null){
			if(obj.toString().length()>0){
				 str = obj.toString();
/** 情况1：纯整数科学计数法：1.2345678E7 代表 12345678 一千百万...八）情况2：纯小数科学计数法：5.0E-4 代表 0.0005  零点零零零5）情况3：纯整数带小数科学计数法：8.888888866777E7 代表 88888888.66777  八千百万...八点六六七**/
				 if(str.contains("E")){
					 str = new BigDecimal(str).toPlainString();
				 }
				 if(str.contains(".")){
					 int spot = str.indexOf(".");
					 int lth = str.length();
					 int tail = lth-spot-1;
					 if(tail==1){
						 str = new String(str+"0");
					 }else if(tail >= 3){
						 str = new String(str.substring(0, spot+3));
					 }
				 }else{
					 str = new String(str+".00"); 
				 }
			}
		}
		if(obj == null){
			str = "";
		}else if(obj.toString().equals("")){
			str = "";
		}
		return str;
	}
		public static String StringUseToSql(String str) {
			str = new String("\'"+str+"\'");
			return str;
		}
		public static String StringJsonReturn(String str) {
			str = new String("\""+str+"\"");
			return str;
		}
		//获得每个季度的最后一天时间，并装入String集合
		//第一季度 01,02，03    |    QUARTER 第二季度 04,05，06   |QUARTER 第三季度 07,08，09  |QUARTER 第四季度 10,11,12  阳历月份
		public static ArrayList<String> getThisYearEveryQuarterLastDayToArray_yyyyMMdd_ZX() {
			String y = getTodayBy_yyyy();
			String a = y+"-03-31";
			String b = y+"-06-30";
			String c = y+"-09-30";
			String d = y+"-12-31";
			ArrayList<String> arr = new ArrayList<String>();
			arr.add(a);arr.add(b);arr.add(c);arr.add(d);
			return arr;
		}
		/** 将yyyy-MM-dd格式的字符串日期，加一天，再返回yyyy-MM-dd格式的字符串日期
		 *   使用此方法的原因：Hibernate的HQL语句不支持date_add方法
		 * @throws ParseException 
		 * **/
		public static String date_add_BYyyyyMMdd(String strDate) throws ParseException {
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(strDate);
		 	Calendar c = Calendar.getInstance();  
	        c.setTime(date);  
	        c.add(Calendar.DAY_OF_MONTH, 1);//+1天  
	        Date tomorrow = c.getTime();  
	        return sdf.format(tomorrow); 
		}
		/** 将yyyy-MM-dd格式的字符串日期，加1天或减少1天或者加2天或者减少3天...等等...yyyy-MM-dd格式的字符串日期
		 *   增加或减少根据 int num 参数 控制  * @throws ParseException * **/
//String strDate = "2017-01-03";String testA = dateReduceOrAddByInt(strDate,+1);System.out.println(testA+"testA +1 时就是 加1天 ");//2017-01-04testA +1 时就是 加1天
//String strDate = "2018-02-06";String testB = dateReduceOrAddByInt(strDate,+0);System.out.println(testB+"testB +0 或 -0 时就是 不增也不减少，用不上这个参数 ");
//String strDate = "2019-05-16";String testC = dateReduceOrAddByInt(strDate,-1);System.out.println(testC+"testC -1代表减少1天 ");
		/*String strDate = "2017-11-19";
		String testD = dateReduceOrAddByInt(strDate,-6);
		System.out.println(testD+"testD -6 代表减少6天|但是 （2017-11-19是周日）-6时得到周1(2017-11-13)也就是最近7天前的第一天 ");*/
		public static String dateReduceOrAddByInt(String strDate,int num) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(strDate);
		 	Calendar c = Calendar.getInstance();  
	        c.setTime(date);  
	        c.add(Calendar.DAY_OF_MONTH, num);//+1天  
	        Date tomorrow = c.getTime();  
	        return sdf.format(tomorrow); 
		}
//getTimeDifference方法，getAppropriateLatelyWeek方法，getAppropriateLatelyWeekForView_A方法，getAppropriateLatelyWeekForView_B方法都服务于‘电商的’‘最近90日’按周显示用，不能改
		/**
		 *  本方法用于：判断两个日期相差几天  要求strDate2016比strDate2017时间早，
		 *  如strDate2016 = "2017-03-03";//N周前
		 *    strDate2017 = "2017-04-06";//N周后
		 *    并且要求strDate2017和strDate2016都是"2017-04-06"的格式   //getTimeDifference方法获得两个时间的差几天
		 */
		public static Integer getTimeDifference(String strDate2016,String strDate2017) throws ParseException{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Date d_2016 = df.parse(strDate2016);  
	        Date d_2017 = df.parse(strDate2017);  
	        long d_cha = d_2017.getTime() - d_2016.getTime(); 
	        Integer d_cha_tian =  Integer.parseInt(""+d_cha / (1000 * 60 * 60 * 24));
			return d_cha_tian;
		}
		/**
		 *  本方法用于：根据两个日期的相差天数，做出判断获得满足需要的最近周
		 *  getAppropriateLatelyWeek  用于获得符合条件的最近周数
		 * @throws ParseException 
		 */
		public static ArrayList<String> getAppropriateLatelyWeek(String strDate,int y) throws ParseException{
			ArrayList<String> arr = new ArrayList<String>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String returnstr = "";
			for(int x=0;x<=y;x++){
				Date date = df.parse(strDate); 
				Date date2 = df.parse(strDate); 
				cal.setTime(date2);
				cal.add(Calendar.DAY_OF_MONTH, -7*x);
				returnstr=df.format(cal.getTime());
				arr.add(returnstr);
				date = df.parse(strDate);
				cal = Calendar.getInstance();
				returnstr = "";
				
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_MONTH, -7*x-6);
				returnstr=df.format(cal.getTime());
				arr.add(returnstr);
				date = df.parse(strDate);
				cal = Calendar.getInstance();
				returnstr = "";
			}
			return arr;
		}
		/**
		 *  getAppropriateLatelyWeekForView_A方法用于：根据两个日期的相差天数，做出判断获得满足需要的最近周的时间；统计‘最近90天’按周显示用
		 */
		//MyTool.getAppropriateLatelyWeekForView_A("2017-01-07","2017-01-07");//获得[2017-01-07, 2017-01-01]  倒序
		//MyTool.getAppropriateLatelyWeekForView_A("2017-01-01","2017-01-07");//获得[2017-01-07, 2017-01-01]  倒序
		//MyTool.getAppropriateLatelyWeekForView_A("2017-01-01","2017-01-07");//获得[2017-01-07, 2017-01-01, 2016-12-31, 2016-12-25]  倒序
		public static ArrayList<String> getAppropriateLatelyWeekForView_A(String searchStartTime,String searchEndTime) throws ParseException{
			Integer dateCha = getTimeDifference(searchStartTime,searchEndTime)+1;
			int xunhuancishu = 0;
			if(dateCha%7==0){
				xunhuancishu = dateCha/7;
			}
			if(dateCha%7!=0){
				double dou1 = Double.parseDouble(dateCha+"");
				double dou = dou1/7;
				int ceil = (int)Math.ceil(dou);
				xunhuancishu = ceil;
			}
	        ArrayList<String> arrReturn = getAppropriateLatelyWeek(searchEndTime,xunhuancishu-1);
	        return arrReturn;
		}
		/** getAppropriateLatelyWeekForView_B 根据需求：根据两个日期的相差天数，做出判断获得满足需要的最近周的时间；要每天的时间集合，不只是周初和周末
		 *   [正序的]
		 *  **/
		//ArrayList<String> timeArr = MyTool.getAppropriateLatelyWeekForView_B("2017-01-03","2017-01-07",90);//获得[2017-01-01, 2017-01-02, 2017-01-03, 2017-01-04, 2017-01-05, 2017-01-06, 2017-01-07]
		//【ArrayList<String> timeArr = MyTool.getAppropriateLatelyWeekForView_B("2016-12-31","2017-01-07",90);下面的集合
		//获得[2016-12-25, 2016-12-26, 2016-12-27, 2016-12-28, 2016-12-29, 2016-12-30, 2016-12-31, 2017-01-01, 2017-01-02, 2017-01-03, 2017-01-04, 2017-01-05, 2017-01-06, 2017-01-07] 】
		public static ArrayList<String> getAppropriateLatelyWeekForView_B(String searchStartTime,String searchEndTime,int ShouWeiChaTianShu) throws ParseException{
			ArrayList<String> timeArrA = getAppropriateLatelyWeekForView_A( searchStartTime, searchEndTime);
			ArrayList<String> timeArrB = MyTool.get_Date_StartTOEnd_ByDays_C(timeArrA.get(timeArrA.size()-1),timeArrA.get(0),ShouWeiChaTianShu);
			return timeArrB;
		}
		/** 对传入"yyyy-MM-dd"2017-10-01，格式的日期String进行减少一年的时间，返回字符串；可服务于‘同比’计算 
		 ***/
		//例如  dateReduceOneYear("2019-10-10");得到 2018-10-10
		public static String dateReduceOneYear(String str) throws ParseException{//Reduce 减少一年，时间减少一年，日期减少一年，减一年；
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		     Date dt = sdf.parse(str);
		     Calendar rightNow = Calendar.getInstance();
		     rightNow.setTime(dt);
		     rightNow.add(Calendar.YEAR,-1);//日期减1年
		     Date dt1 = rightNow.getTime();
		     String reStr = sdf.format(dt1);
		     return reStr;
		}
		/** 为计算‘客单价’而使用此方法：客单价=销售订单金额/销售订单笔数，多少笔交易  (Guest Unit Price  客单价)**/
		public static Double getGuestUnitPrice(double fz,double fm) {
			String returnStr = "";
			double guestUnitPrice = fz/fm;
			if(fm == 0.0){
				returnStr = "0.00";
			}else{
				returnStr = String.format("%.2f", guestUnitPrice);//保留两位小数
			}
			 return Double.parseDouble(returnStr);
		}
		/** 对传入日期字符串进行格式判断并返回，当前只有对‘yyyy-MM-dd’进行判断，后期再在优化方法进行if判断返回
		 ***/
		//例如  String dateFormatJudge = "2001-10-01";  String judgeDateStrFormat = judgeDateStrFormat(dateFormatJudge);System.out.println(judgeDateStrFormat);打印 yyyy-MM-dd
		public static String judgeDateStrFormat(String dateFormatJudge) {
			String SimpleDateFormat = "";
			 if(dateFormatJudge.length() == 10 ){
		    	  String substrA = dateFormatJudge.substring(4, 5);
		          String substrB = dateFormatJudge.substring(7, 8);
		          if(substrA.equals("-")  &&  substrB.equals("-") ){
		        	  SimpleDateFormat = "yyyy-MM-dd";// System.out.println("这个日期是2001-10-01");//System.out.println("这个日期也就是yyyy-MM-dd格式的");
		          }
		      }
			 return SimpleDateFormat;
		}
		 /** 对两个时间字符串进行判断，当前可以判断‘yyyy-MM-dd’格式的，后期可以再增加其他格式....  **/
		//例如1：int 相等时 = compareDate("1995-11-12", "1995-11-12"); System.out.println("i=="+相等时);//打印 i==0
		  //例如2： int 前小后大 = compareDate("1995-11-11", "2008-11-12"); System.out.println("i==  "+前小后大);//打印 i==  -1
		    //例如3： int 前大后小 = compareDate("2008-11-11", "1880-11-12"); System.out.println("i==  "+前大后小);//打印 i==  1
		    public static int compareDate(String DATE1, String DATE2) {
		    	String judgeDateStrFormat = judgeDateStrFormat(DATE1);
		        DateFormat df = new SimpleDateFormat(judgeDateStrFormat);
		        try {
		            Date dt1 = df.parse(DATE1);
		            Date dt2 = df.parse(DATE2);
		            if (dt1.getTime() > dt2.getTime()) {
		                return 1;
		            } else if (dt1.getTime() < dt2.getTime()) {
		                return -1;
		            } else {
		                return 0;
		            }
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
		        return 0;
		    }
			/** 对ArrayList<String>类型的查询某元素的下角标，获得指定元素的下角标 **/
//			AppointElement      Appoint指定，Element元素，Subscript下角标，getAppointElementSubscript 获得指定元素的下角标
//			例如
//			timeArrNew是[2017-08-02, 2017-08-03, 2017-08-04, 2017-08-05, 2017-08-06, 2017-08-07, 2017-08-08, 2017-08-09]
//			Integer index = getAppointElementSubscript(timeArrNew,"2017-08-03");
//			System.out.println(index);打印1
			
//			再例如timeArrNew是[2017-08-02, 2017-08-03, 2017-08-04, 2017-08-05, 2017-08-06, 2017-08-07, 2017-08-08, 2017-08-09]
//			Integer index = getAppointElementSubscript(timeArrNew,"1999-08-03");
//			System.out.println(index);打印-1
			public static Integer getAppointElementSubscript(ArrayList<String> timeArrNew,String elementStr)  {
				Integer index = -1;
				for(int x=0;x<timeArrNew.size();x++){
					String str = timeArrNew.get(x);
					if(str.equals(elementStr)){
						index = x;
						break;
					}
				}
				return index;
			}
	
			/**
			 * 根据日期获取星期
			 */
			//例如String weekDayByDateA = getWeekDayByDate("2017-10-26");System.out.println(weekDayByDateA);//打印  星期四
			//再例如String weekDayByDateB = getWeekDayByDate("2017-10-23");System.out.println(weekDayByDateB);//打印  星期一
			public static String getWeekDayByDate(String strdate) {
				final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
				SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				Date date = new Date();
				try {
					date = sdfInput.parse(strdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				calendar.setTime(date);
				int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				if (dayOfWeek < 0)
					dayOfWeek = 0;
				return dayNames[dayOfWeek];
			}

		/**
			 * 判断对象是否Empty(null或元素为0)<br>
			 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
			 * 
			 * @param pObj
			 *            待检查对象
			 * @return boolean 返回的布尔值
			 */
			//Object obj = null;boolean empty = isEmpty(null);System.out.println("打印> "+empty);// 打印> true
			//Map<String,Object> obj = new HashMap<String,Object>();obj.put("1", "123");boolean empty = isEmpty(obj);System.out.println("打印>>> "+empty);// 打印>>> false

			//Map<String,Object> obj = new HashMap<String,Object>();boolean empty = isEmpty(obj);System.out.println("打印>>> "+empty);// 打印>>> true

			//ArrayList<String> obj = new ArrayList<String>();boolean empty = isEmpty(obj);System.out.println("打印集合>>> "+empty);//打印集合>>> true
			//ArrayList<String> obj = null;boolean empty = isEmpty(obj);System.out.println("打印集合|>>> "+empty);//打印集合|>>> true
		//ArrayList<String> obj = new ArrayList<String>();obj.add("");boolean empty = isEmpty(obj);System.out.println("打印集合。。|>>> "+empty);// 打印集合。。|>>> false
			@SuppressWarnings("rawtypes")
			public static boolean isEmpty(Object pObj) {
				if (pObj == null)
					return true;
				if (pObj == "")
					return true;
				if (pObj instanceof String) {
					if (((String) pObj).length() == 0) {
						return true;
					}
				} else if (pObj instanceof Collection) {
					if (((Collection) pObj).size() == 0) {
						return true;
					}
				} else if (pObj instanceof Map) {
					if (((Map) pObj).size() == 0) {
						return true;
					}
				}
				return false;
			}


		/**
			 * 判断对象是否为NotEmpty(!null或元素>0)<br>
			 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
			 * @param pObj
			 *            待检查对象
			 * @return boolean 返回的布尔值
			 */
			/*	ArrayList<String> objArr = new ArrayList<String>();
			objArr.add("123456789");
			boolean notEmpty = isNotEmpty(objArr);
			System.out.println(">"+notEmpty+"<");//>true<     */
			@SuppressWarnings("rawtypes")
			public static boolean isNotEmpty(Object pObj) {
				if (pObj == null)
					return false;
				if (pObj == "")
					return false;
				if (pObj instanceof String) {
					if (((String) pObj).length() == 0) {
						return false;
					}
				} else if (pObj instanceof Collection) {
					if (((Collection) pObj).size() == 0) {
						return false;
					}
				} else if (pObj instanceof Map) {
					if (((Map) pObj).size() == 0) {
						return false;
					}
				}
				return true;
			}
			/** 根据时间集合拿到他所包含的所有月份，只取月份，只取不重复的重复，跟随该时间集合的顺序（正序或倒序）；返回时间月份集合  **/
			//例如 从集合[2005-05-05]获得新集合   [2005-05]
			//再例如 从集合[2005-05-04, 2005-05-05]中获得新集合[2005-05]   【注意日期类型 2005-05-02 】
			//再再例如从集合[2005-04-30, 2005-05-01, 2005-05-02, 2005-05-03, 2005-05-04, 2005-05-05]中返回新集合 [2005-04, 2005-05]
			public static ArrayList<String> getMonthArrByTimeArr(ArrayList<String> timeArr){
				 ArrayList<String> timeArrReturn = new ArrayList<String>();
				  if(timeArr.size()>=2){
					  timeArr.add("1234567890");
					  for(int x=0;x<timeArr.size()-1;x++){
						  String substrQ = timeArr.get(x).substring(0, 7);//截取到年和月  2005-04
						  String substrH = timeArr.get(x+1).substring(0, 7);
						  if(!substrQ.equals(substrH)){
							  timeArrReturn.add(substrQ);
						  }
					  }
				  }
				  if(timeArr.size()==1){
					  String substrQ = timeArr.get(0).substring(0, 7);
					  timeArrReturn.add(substrQ);
				  }
				return timeArrReturn;
			}
			/** 根据需求：求 Double num 的正 Double值，并返回**/
			/*Double ceshi = getplusDoubleByDouble(1.0);
			System.out.println(ceshi);//打印1.0*/
					/*Double ceshi = getplusDoubleByDouble(0.0);
					System.out.println(ceshi);//打印0.0*/
							/*Double ceshi = getplusDoubleByDouble(-3.11111111107);
							System.out.println(ceshi);//打印3.11111111107*/
			public static Double getplusDoubleByDouble(Double num) {  //plus 正的
				String str = num+"";
				if(str.contains("-")){
					str= new String(str.substring(1, str.length()));
				}
				return Double.parseDouble(str);
			}
			/** 根据Map<String, Object> 的键获得其值的toString字符串，返回 **/
			/*Map<String,Object> hsMap = new HashMap<String,Object>();
			hsMap.put("controlSQL", "就是我");
			String vaueToStrByHashMapKey = getValueToStrByHashMapKey(hsMap,"controlSQL");
			System.out.println(vaueToStrByHashMapKey);//打印 就是我*/
			public static String getValueToStrByHashMapKey(Map<String, Object> hsMap,String key) {  
				String returnString = "false";
				if(hsMap.containsKey(key)){
					if(hsMap.get(key) !=null ){
						if(!hsMap.get(key).toString().equals("")){
							if(hsMap.get(key).toString().length() !=0 ){
							returnString = hsMap.get(key).toString();
							}
						}
					}
				}
				return returnString;
			}
		/** 对传入的24小时字符串进行合适化返回  **/
		//formatAppointStrDateBy24Hours
		//例如String one = formatAppointStrDateBy24Hours("00");System.out.println(one+"    ONE");//00:00~01:00    ONE
		//再例如String two = formatAppointStrDateBy24Hours("08");System.out.println(two+"    two");//08:00~09:00    two
		//再例如String three = formatAppointStrDateBy24Hours("23");System.out.println(three+"    three");//23:00~00:00    three
		public static String formatAppointStrDateBy24Hours(String strDate) {  
			ArrayList<String> string24Hours = new ArrayList<String>();
			string24Hours.add("00:00~01:00");//00点
			string24Hours.add("01:00~02:00");//01点
			string24Hours.add("02:00~03:00");//02点
			string24Hours.add("03:00~04:00");//03点
			string24Hours.add("04:00~05:00");
			string24Hours.add("05:00~06:00");
			string24Hours.add("06:00~07:00");
			string24Hours.add("07:00~08:00");
			string24Hours.add("08:00~09:00");
			string24Hours.add("09:00~10:00");
			string24Hours.add("10:00~11:00");
			string24Hours.add("11:00~12:00");
			string24Hours.add("12:00~13:00");
			string24Hours.add("13:00~14:00");
			string24Hours.add("14:00~15:00");
			string24Hours.add("15:00~16:00");
			string24Hours.add("16:00~17:00");
			string24Hours.add("17:00~18:00");
			string24Hours.add("18:00~19:00");
			string24Hours.add("19:00~20:00");
			string24Hours.add("20:00~21:00");
			string24Hours.add("21:00~22:00");
			string24Hours.add("22:00~23:00");
			string24Hours.add("23:00~00:00");
			Integer in = Integer.parseInt(strDate);
			strDate = new String(string24Hours.get(in));
			return strDate;
		}
		/** 需求： 计算5个ArrayList<String> 返回总值ArrayList<String>;  对5个进行处理  **/
		public static ArrayList<String> calculationArrayStringToTotal(  ArrayList<String> arr1,
																		ArrayList<String> arr2,
																		ArrayList<String> arr3,
																		ArrayList<String> arr4,
																		ArrayList<String> arr5){
			ArrayList<String> arrReturn= new ArrayList<String>();
			for(int x=0;x<arr1.size();x++){
				String A = arr1.get(x);
				String B = arr2.get(x);
				String C = arr3.get(x);
				String D = arr4.get(x);
				String E = arr5.get(x);
				Double ABC = (Double.parseDouble(A)+Double.parseDouble(B)+Double.parseDouble(C)+Double.parseDouble(D)+Double.parseDouble(E));
				arrReturn.add(ABC+"");
			}
			return arrReturn;
		}
		/** 需求： 计算5个ArrayList<String> 返回总值ArrayList<String>;  对3个进行处理  **/
		public static ArrayList<String> calculationArrayStringToTotalC(  ArrayList<String> arr1,
																		ArrayList<String> arr2,
																		ArrayList<String> arr3){
			ArrayList<String> arrReturn= new ArrayList<String>();
			for(int x=0;x<arr1.size();x++){
				String A = arr1.get(x);
				String B = arr2.get(x);
				String C = arr3.get(x);
				Double ABC = (Double.parseDouble(A)+Double.parseDouble(B)+Double.parseDouble(C));
				arrReturn.add(ABC+"");
			}
			return arrReturn;
		}
		/** 需求： 计算2个ArrayList<String> 返回总值ArrayList<String>;  对2个进行处理  **/
		public static ArrayList<String> calculationArrayStringToTotalB(  ArrayList<String> arr1,
																		ArrayList<String> arr2){
			ArrayList<String> arrReturn= new ArrayList<String>();
			for(int x=0;x<arr1.size();x++){
				String A = arr1.get(x);
				String B = arr2.get(x);
				Double ABC = (Double.parseDouble(A)+Double.parseDouble(B));
				arrReturn.add(ABC+"");
			}
			return arrReturn;
		}
		/**
		   * getTimeArrByHtmlParameterString 根据jsp页面或者html页面传过来的字符串参数，获得对应的时间集合  ArrayList<String>，一般为正序，字符串除非特制定
		    */
			//应用示例： //NewAdd 代表新增会员 | NewAdd{Today}  NewAdd{ThisMonthOnlyTwo}  NewAdd{Yesterday} 查询今日，昨日，本月
			//ArrayList<String> timeArr = getTimeArrByHtmlParameterString("ThisMonthOnlyTwo");
			//System.out.println(timeArr);//得到 [2017-11-01, 2017-11-30]
		   public static List<String> getTimeArrByHtmlParameterString(String timeStr){
			   List<String> timeArr = new ArrayList<String>();
			   try {
				   if (StringUtils.isNotEmpty(timeStr)) {
						if(timeStr.equals("Today")){
							timeArr.add(getTodayBy_yyyyMMdd());//查询今天
						}else if(timeStr.equals("ThisMonthOnlyTwo") || timeStr.equals("ThisMonthOT")){//|ThisMonthOnlyTwo代表查询本月，但不要每天，只要首日，末日2天即可(用ThisMonthOT简写)
							String[] obj = getThisMonth_SOnlyAndW();
							timeArr.add(obj[0]);
							timeArr.add(obj[1]);
						}else if(timeStr.equals("ThisMonthAll")){//查询本月所有天数，时间正序
							timeArr = getThisMonthAllDayArray();
						}else if(timeStr.equals("Tomorrow")){//查询明天(今天+1天就是明天)
							String Tomorrow = date_add_BYyyyyMMdd(getTodayBy_yyyyMMdd());
							timeArr.add(Tomorrow);
						}else if(timeStr.equals("Yesterday")){
							timeArr.add(getYesterdayBy_yyyyMMdd());//查询昨天
						}else if(timeStr.equals("ThisWeek")){//查询‘本周’
							timeArr = getNowWeekArray_ZX();
						}else if(timeStr.equals("NextWeekOT") ){//查询‘下周’                                              
							timeArr = getNextWeekSonlyE_ZX();
						}else if(timeStr.equals("ThisQuarterOT") ){//查询‘本季度首尾两天’                                              
							String[] obj = getThisQuarter_StartOnlyAndEnd(getTodayBy_yyyyMMdd());		
							timeArr.add(obj[0]);
							timeArr.add(obj[1]);	
						}else if(timeStr.equals("LastQuarterOT") ){//查询‘上季度首尾两天’                                              
							String[] obj = getLastQuarter_StartOnlyAndEnd(getTodayBy_yyyyMMdd());		
							timeArr.add(obj[0]);
							timeArr.add(obj[1]);
						}else if(timeStr.equals("ThisQuarterAll") ){//查询‘本季度所有天数’                                              
							String[] obj = getThisQuarter_StartOnlyAndEnd(getTodayBy_yyyyMMdd());		
							timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(obj[0],obj[1],1095);	
						}else if(timeStr.equals("LastMonthOT") ){//查询‘上月’
							String[] lastMonthObj = getLastMonth_SOnlyAndW();
							timeArr.add(lastMonthObj[0]);
							timeArr.add(lastMonthObj[1]);
						}else if(timeStr.equals("ThisYearOT") ){//查询‘本年’，仅仅两天，第一天和最后一天；正序
							timeArr = getThisYearOnlyTwoDay();
						}else if(timeStr.equals("ThisYearAll") ){//查询‘本年’，所有天数，时间正序
							ArrayList<String> yea = getThisYearOnlyTwoDay();
							timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(yea.get(0),yea.get(1),1095);
						}else if(validateTimeStrOne(timeStr)){//查询 用一个字符串表示开始时间和结束时间2017-11-14格式的（直接就是开始时间+结束时间的字符串）如2017-11-112017-11-11
							String startTime = timeStr.substring(0,10);
							String endTime = timeStr.substring(10,20);
							boolean boo = false;//这个方法用于查询2017-11-112017-11-11直接拼成的开始时间和结束时间注意格式
							try {
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
								Date startTimeDate = df.parse(startTime);
						        Date endTimeDate = df.parse(endTime);
						        if (startTimeDate.getTime() <= endTimeDate.getTime()) {
						        	boo = true;//System.out.println("开始时间小于等于结束时间，可以查询了");
						        }
						 	} catch (Exception exception) {
					            boo = false;
					        }//(365*3)=1095天//默认倒排3年的时间，如果需要自定义，在想办法  控制 else if(validateTimeStrOne(timeStr)即可跳过这个方法，并把倒排天数的参数带过来即可
							if(boo){//(365*3)=1095天//默认倒排3年的时间，如果需要自定义，在想办法 else if(validateTimeStrOne(timeStr)即可跳过这个方法，并把倒排天数的参数带过来即可//包括开始时间，也包括结束时间
						     timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime,endTime,1095);//【这里的1095表示开始时间和结束时间的差最多1095天，否则返回1095天的集合】以结束时间倒退1095天正序时间集合
							}
						}else if(validateTimeStrTwo(timeStr)){//用于查询时间段'2017-01-012018-11-03{rN-pE{自定义返回页面A}}'格式的字符串，详情见 validateTimeStrTwo方法说明  【必须时间在前面】
							String startTime = timeStr.substring(0,10);
							String endTime = timeStr.substring(10,20);
							boolean boo = false;//这个方法用于查询2017-11-112017-11-11直接拼成的开始时间和结束时间注意格式
							try {
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
								Date startTimeDate = df.parse(startTime);
						        Date endTimeDate = df.parse(endTime);
						        if (startTimeDate.getTime() <= endTimeDate.getTime()) {
						        	boo = true;//System.out.println("开始时间小于等于结束时间，可以查询了");
						        }
						 	} catch (Exception exception) {
					            boo = false;
					        }//(365*3)=1095天//默认倒排3年的时间，
							if(boo){//包括开始时间，也包括结束时间
						     timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime,endTime,1095);//【这里的1095表示开始时间和结束时间的差最多1095天，否则返回1095天的集合】以结束时间倒退1095天正序时间集合
							}
						}else if(validateTimeStrThree(timeStr)){//2017-03{t-Sm-HOT} 新需求：形如前面年加月加{t-Sm-HOT}的表示，取出2017年10月的月初时间和月末时间的时间集合；
							//t-Sm-HOT是ThisMonthOT的形式变化来。首字母尾字母大小写和-符号和OT结合变化，这么做就是为了防止字符串冲突
							String year = timeStr.substring(0,4);//2017 
							String month = timeStr.substring(5,7);//03
							String firstDayOfMonth = year+"-"+month+"-01";
							String lastDayOfMonth = getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
							timeArr.add(firstDayOfMonth);
							timeArr.add(lastDayOfMonth);//String test="2020-02{t-Sm-HOT}";//解析得到[2020-02-01, 2020-02-29]
						}else if(validateTimeStrFour(timeStr)){//2017-03{l-Tm-HOT} 新需求：形如。。。2017年03月的上月初时间和上月末时间的时间集合；
							String year = timeStr.substring(0,4);//2017 
							String month = timeStr.substring(5,7);//03
							String tempDay = year+"-"+month+"-01";
							String testD = dateReduceOrAddByInt(tempDay,-6);//取出这个月的第一天再减少6天一定是上月中的时间
							String yearB = testD.substring(0,4); 
							String monthB = testD.substring(5,7);
							String lastDayOfMonth = getLastDayOfMonth(Integer.parseInt(yearB), Integer.parseInt(monthB));
							timeArr.add((yearB+"-"+monthB+"-01"));
							timeArr.add(lastDayOfMonth);//String test="2028-07{l-Tm-HOT}";//解析得到上个月[2028-06-01, 2028-06-30]
						}else if(timeStr.startsWith("-Lately-Day{")){//String str = "-Lately-Day{30}";//获得最近30天
							//String strB = "-Lately-Day{3}";//最近3天//今天‘2017-11-22’//得到[2017-11-20, 2017-11-21, 2017-11-22]最近3天正序
							String inStr = MyTool.analysisJsonStringFive(timeStr);
							Integer in = Integer.parseInt(inStr);//以‘-Lately-Day{’  开头
							timeArr = MyTool.getNewlyDateArrayByInt_ZX((-1)*(in-1));
						}
				}
			   } catch (ParseException e) {
					e.printStackTrace();
			   }
			   return timeArr;
		   }
		   /** 需求：用一个字符串表示开始时间和结束时间2017-11-14格式的（直接就是开始时间+结束时间的字符串）这里是验证字符串的格式 **/
			//这里用来验证这个字符串是不是由开始时间2017-11-14格式的和结束时间2017-11-14格式的直接拼装成的
		   /*String str = "2019-11-112011-11-14";
			boolean boo = MyTool.validateTimeStrOne(str);
			System.out.println(boo);//true*/
				   /*String str = "2019-11-112011-11-1";
					boolean boo = MyTool.validateTimeStrOne(str);
					System.out.println("测试"+boo);//测试false*/
			public static boolean validateTimeStrOne(String str) {
				boolean boo = false;
				if(str !=null){
					if(str.length()==20){
					String A = str.substring(4,5);//-
					String B = str.substring(7,8);//-
					String C = str.substring(14,15);//-
					String D = str.substring(17,18);//-
					String num = str.substring(8,14);   
						if((A+B+C+D+"").equals("----") && (isNumeric(num))){
							String myReplaceStr = str.replaceAll("-", "");
							if(isNumeric(myReplaceStr)){
								boo = true;
							}
						}
					}
				}
				return boo;
			}
			/** 新需求：不仅要一句话代表开始时间结束时间还有传递返回页面的参数，所以 写出 validateTimeStrTwo 方法  【必须时间在前面】***/
			/*应用示例
				//String qTimestr ="2017-01-012018-11-03{rN-pE{自定义返回页面A}}";boolean boo = validateTimeStrTwo(qTimestr);System.out.println(boo);//true
		        //String qTimestrB ="2017-01-012018-11-03";boolean booB = validateTimeStrTwo(qTimestrB);System.out.println(booB+" booB");//false booB
		        //String qTimestrC ="2017-01-012018-11-03{rN-pE{}}";boolean booC = validateTimeStrTwo(qTimestrC);System.out.println(booC+" booC");//true booC   */
			//String qTimestr ="2017-01-012018-11-03{rN-pE{自定义返回页面参数}}";//returnPage的代表符号首字母小写，末尾字母大写就是rN和pE拼装后的rNpE;中间再用一个日期中的'-'连接最终是rN-pE
			//说明rN-pE{自定义返回页面参数}  rN-pE是固定写法代表 返回页面的键  ;  {自定义返回页面参数}里面夹杂者返回页面的值，自定义
			//说明rN-pE{自定义返回页面参数}  rN-pE是固定写法代表 返回页面的键  ;  {自定义返回页面参数}里面夹杂者返回页面的值，自定义
			// String timeStr = MyTool.analysisJsonStringFive(queryTime);
			// System.out.println(timeStr+"     得到");//rN-pE{自定义返回页面参数}     得到
			public static boolean validateTimeStrTwo(String qTimestr) {
				boolean boo = false;
				if(qTimestr !=null){
					int leng = qTimestr.length();
					if(leng>20){
						String queryTimeHead = qTimestr.substring(0,20);//System.out.println(queryTimeHead);//2017-01-012018-11-03
						String queryTimeTail = qTimestr.substring(20,leng);//System.out.println(queryTimeTail);//{rN-pE{自定义返回页面参数}}
						if(validateTimeStrOne(queryTimeHead) && queryTimeTail.contains("{rN-pE{")){
							boo = true;
						}
					}
				}
				return boo;
			}
			/** 新需求：页面日期插件选择年月2017-10格式  2017-03格式的，查询这个月的月初和月末两天的时间集合，2017-10{t-Sm-HOT} 代表2017年10月 t-Sm-HOT是ThisMonthOT的变化形式。validateTimeStrThree用于验证字符串***/
			//String str ="2017-03{t-Sm-HOT}";boolean b = validateTimeStrThree(str);//true
			public static boolean validateTimeStrThree(String str) {
				boolean boo = false;//String str ="2017-03{t-Sm-HOT}";
				if(str !=null){
					if(str.length()==17){
						String substrHead = str.substring(0,7);//2017-10
						String substrTail = str.substring(7,17);//{t-Sm-HOT}
						String subX = str.substring(4,5);//-
						if((subX).equals("-")  && (substrTail).equals("{t-Sm-HOT}")){
							String replaNum = substrHead.replace("-", "");
							if(isNumeric(replaNum)){  
								boo = true;
							}
						}
					}
				}
				return boo;
			}
			/** 新需求：页面日期插件选择年月2017-10格式  2017-03格式的，查询这个月的上月初和上月末两天的时间集合，2017-10{l-Tm-HOT} 代表2017年10月 t-Sm-HOT是LastMonthOT的变化形式。validateTimeStrFour用于验证字符串***/
			//boolean boo = MyTool.validateTimeStrFour("2017-10{l-Tm-HOT}");//true
			public static boolean validateTimeStrFour(String str) {
				boolean boo = false;//String str ="2017-06{l-Tm-HOT}"; 当前上个月初和月末2个值
				if(str !=null){
					if(str.length()==17){
						String substrHead = str.substring(0,7);//2017-10
						String substrTail = str.substring(7,17);//{l-Tm-HOT}
						String subX = str.substring(4,5);//-
						if((subX).equals("-")  && (substrTail).equals("{l-Tm-HOT}")){
							String replaNum = substrHead.replace("-", "");
							if(isNumeric(replaNum)){  
								boo = true;
							}
						}
					}
				}
				return boo;
			}
			/** 用用正则表达式判断字符串是不是 纯数字  **/
			public static boolean isNumeric(String str){
				if (StringUtils.isEmpty(str)) {
					return false;
				}
			   Pattern pattern = Pattern.compile("[0-9]*"); 
			   Matcher isNum = pattern.matcher(str);
			   if( !isNum.matches() ){
			       return false; 
			   } 
			   return true; 
			}
		   /** 需求从mysql中查询生日是-11月13日的用户的方法   | 注意传入字符串日期必须是‘2017-11-13’格式|返回‘-11-13’格式，其他看queryBirthday...Two方法***/
//			String str = "2017-11-13";
//			String queryBirthdayBYyyyyMMdd = queryBirthdayBYyyyyMMdd(str);
//			System.out.println(queryBirthdayBYyyyyMMdd+"  |生日");//   -11-13  |生日
			public static String queryBirthdayBYyyyyMMdd(String strDate) {
				String birthday = strDate.substring(4, 10);
				return birthday;
			}
			/** 需求从mysql中查询生日是11月13日的用户的方法   | 注意传入字符串日期必须是‘2017-11-13’格式***/
//			String two = MyTool.queryBirthdayBYyyyyMMddTwo("2017-03-13");
//			System.out.println(two+"   |two|");//03-13   |two|
			public static String queryBirthdayBYyyyyMMddTwo(String strDate) {
				String birthday = strDate.substring(5, 10);
				return birthday;
			}
			/** 获取时间为yyyy-MM-dd HH:mm:ss 的字符串**/
//			String dateTime	= MyTool.dateFormatAyMdHms(new Date());
//			System.out.println(dateTime);//2017-11-13 10:09:00
			public static String dateFormatAyMdHms(Date timeDate) {
				String dateString = "";
				try{
					if(timeDate != null){
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						dateString = formatter.format(timeDate);
					}else{
						dateString = "";
					}
				}catch(Exception e){
					dateString = "";
				}
				return dateString;
			}
			/** 获取时间为yyyy-MM-dd  的字符串**/
//			String dateTime	= MyTool.dateFormatByMd(new Date());
//			System.out.println(dateTime);//2017-11-13
			public static String dateFormatByMd(Date timeDate) {
				String dateString = "";
				try{
					if(timeDate != null){
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						dateString = formatter.format(timeDate);
					}else{
						dateString = "";
					}
				}catch(Exception e){
					dateString = "";
				}
				return dateString;
			}
			
}
