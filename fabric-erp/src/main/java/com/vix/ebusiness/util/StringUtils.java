package com.vix.ebusiness.util;

import java.util.Random;

/**
 * 字符串工具类。
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-20
 */
public abstract class StringUtils {

	private StringUtils() {
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查对象是否为数字型字符串。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		String str = obj.toString();
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 过滤不可见字符
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF)) || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	/**
	 * @param d
	 * @return
	 */
	public static String doubleToString(double d) {
		if (Double.isInfinite(d) || Double.isNaN(d)) {
			return "0";
		}

		// Shave off trailing zeros and decimal point, if possible.

		String s = Double.toString(d);
		if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0) {
			while (s.endsWith("0")) {
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith(".")) {
				s = s.substring(0, s.length() - 1);
			}
		}
		return s;
	}

	/**
	 * @param value
	 * @return
	 */
	public static boolean getBoolean(Object value) {

		if (value instanceof Boolean)
			return value.equals(Boolean.TRUE);

		if (value instanceof String) {
			String val = (String) value;

			if (val.equalsIgnoreCase("true"))
				return true;

			if (val.equalsIgnoreCase("false"))
				return false;
		}

		throw new RuntimeException(value + " is not a Boolean.");
	}

	/**
	 * @param value
	 * @return
	 */
	public static double getDouble(Object value) {

		try {
			if (value == null)
				return 0;
			if (value instanceof Number)
				return ((Number) value).doubleValue();

			// This is a bit sloppy for the case where value is not a string.

			return Double.valueOf((String) value);
		} catch (Exception e) {
			throw new RuntimeException(value + " is not a number.");
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static int getInt(Object value) {

		if (value instanceof Number)
			return ((Number) value).intValue();

		return (int) getDouble(value);
	}

	/**
	 * @param value
	 * @return
	 */
	public static long getLong(Object value) {
		if (value != null) {
			try {
				return value instanceof Number ? ((Number) value).longValue() : Long.valueOf(value.toString());
			} catch (Exception e) {
			}
		}
		return 0;
	}

	/**
	 * @param value
	 * @return
	 */
	public static String getString(Object value) {
		if (value == null)
			return null;
		return String.valueOf(value);
	}

	/**
	 * @param n
	 * @return
	 */
	public static String numberToString(Number n) {
		assert n != null;

		String s = n.toString();
		if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0) {
			while (s.endsWith("0")) {
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith(".")) {
				s = s.substring(0, s.length() - 1);
			}
		}
		return s;
	}

	public static String createNO() { // length表示生成字符串的长度
		int length = 12;
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString().toUpperCase();
	}
}
