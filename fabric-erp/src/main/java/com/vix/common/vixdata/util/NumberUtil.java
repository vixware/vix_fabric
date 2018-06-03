package com.vix.common.vixdata.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class NumberUtil {
	
	public static double formatDoubleToTwoDecimal(Double d){
		if(null != d){
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return Double.parseDouble(myFormatter.format(d));
		}else{
			return 0.00d;
		}
	}	
	
	/**
	 * 
	 * @Title: round
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param value
	 * @param @param scale
	 * @param @param roundingMode
	 * #  * float/double的精度取值方式分为以下几种: <br> 
	 * java.math.BigDecimal.ROUND_UP <br> 
	 * java.math.BigDecimal.ROUND_DOWN <br> 
	 * java.math.BigDecimal.ROUND_CEILING <br> 
	 * java.math.BigDecimal.ROUND_FLOOR <br> 
	 * java.math.BigDecimal.ROUND_HALF_UP<br> 
	 * java.math.BigDecimal.ROUND_HALF_DOWN <br> 
	 * java.math.BigDecimal.ROUND_HALF_EVEN <br> 
	 *  
	 * 
	 * @param @return    设定文件
	 * @return double    返回类型
	 * @throws
	 */
	public static double round(double value, int scale, int roundingMode) {  
		BigDecimal bd = new BigDecimal(value);  
		bd = bd.setScale(scale, roundingMode);  
		double d = bd.doubleValue();  
		bd = null;  
		return d;  
	 }  
	
	/**
	 * 
	 * @Title: round
	 * @Description:格式化BigDecimal
	 * @param @param bd
	 * @param @param scale
	 * @param @param roundingMode
	 * @param @return    设定文件
	 * @return double    返回类型
	 * @throws
	 */
	public static double round(BigDecimal bd, int scale, int roundingMode) {  
		bd = bd.setScale(scale, roundingMode);  
		double d = bd.doubleValue();  
		bd = null;  
		return d;  
	 }  
	
	/**
	 * 
	 * @Title: roudFormat
	 * @Description: 格式化四位小数
	 * @param @param dd
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String roudFormat(double dd){
		if(dd==0D){
			return "0";
		}
		DecimalFormat  nFormat=(DecimalFormat) NumberFormat.getInstance(Locale.CHINA);
        nFormat.applyPattern("####.0000"); 
		return nFormat.format(dd);
	}
	
	/** 格式化double两位小数*/
	public static Double formatDoubleTwoDecimal(Double number){
		if(null == number || number <= 0){return number;}
		
		DecimalFormat myFormatter = new DecimalFormat("0.00");
		String tp =  myFormatter.format(number);
		return Double.parseDouble(tp);
	}
	
	/**
     *   将数字转化为字符串，并格式化为指定的长度，不够位数的前面补指定字符
     *   Input:   1,   5,   '0 '
     *   Output:   "00001 "
     *
     *   @return java.lang.String
     *   @param  value   -   要转换的数字
     *   @param  number   -   要补齐的位数
     *   @param  c char   -   补充的字符
     */ 
	public static String leftPad(long value, int number, char c){
        String s = "";
		String temp = (new Long(value)).toString();
		if (temp.length() <= number) {
			for (int i = 0; i < number; i++) {
				s += c;
			}
			s = s.substring(0, number - temp.length()) + temp;
		} else {
			System.out.println("Error:   " + number + " 's length is   "
					+ temp.length() + ", it 's bigger than " + number
					+ ". ");
			return s+value;
		}
		return s;
	}
	
	/**
	 * @Title: isNumeric
	 * @Description: 判断是否为数字
	 * @param @param str
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isNumeric(String str){
		if(null == str || "".equals(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str.trim()).matches();
	}
	
	/**
	 * @Title: isNumeric
	 * @Description: 判断是否为Double数字
	 * @param @param str
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isDoubleNumeric(String str){
		if(null == str || "".equals(str)){
			return false;
		}
		boolean isDouble = false;
		Pattern pattern = Pattern.compile("[0-9]*");
		if(str.contains(".")){
			String[] s = str.split("\\.");
			if(s.length == 2){
				isDouble = pattern.matcher(s[0].trim()).matches() && pattern.matcher(s[1].trim()).matches();
			}else{
				return false;
			}
		}else{
			isDouble = pattern.matcher(str.trim()).matches();
		}
		return isDouble;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * @param datePrefix 取值： yyyy 或 yyyyMM 或 yyyyMMdd
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static String buildRandom(String datePrefixFormat,int length) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePrefixFormat);
		Double random = Math.random();
		if (random < 0.1) {
			random += 0.1;
		}
		return sdf.format(new Date()) + random.toString().substring(2, length+2);
	}
	
	/**
	 * 生成指定长度的随机数
	 * @param charCount 随机数长度
	 * @return
	 */
	public static String getRandomNumber(int charCount) {
        String charValue = "";
        Random r = new Random();
        for (int i = 0; i < charCount; i++) {
            char c = (char) (r.nextInt(10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;

    }
}