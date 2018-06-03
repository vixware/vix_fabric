package com.vix.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class NumberUtil {
	
	public static String formatDouble(double number,Integer pointLength){
		Assert.notNull(pointLength,"长度不能为空!");
		DecimalFormat df = new DecimalFormat("######0.0000");   
		
		return df.format(number);
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
	 * 格式化BigDecimal
	 * @param value
	 * @param scale
	 * @param roundingMode
	 * @return
	 */
	public static double roundBigDecimal(BigDecimal value, int scale, int roundingMode) {  
		value = value.setScale(scale, roundingMode);  
		double d = value.doubleValue();  
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
	 * 四舍五入  四位小数
	 * @param bd
	 * @param scale
	 * @param roundingMode
	 * @return
	 */
	public static double round4(BigDecimal bd) {  
		bd = bd.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);  
		double d = bd.doubleValue();  
		bd = null;  
		return d;  
	 }  
	
	/**
	 * 四舍五入
	 * @param num
	 * @return
	 */
	public static double round4(Double num) { 
		if(num==null){
			num = 0D;
		}
		BigDecimal bd = new BigDecimal(num); 
		bd = bd.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);  
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
		return pattern.matcher(str).matches();
	}  
	
	public static Long convertoLong(String id){
		Long idVal = null;
		try {
			idVal = Long.parseLong(id);
		} catch (Exception e) {
		}
		return idVal;
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
