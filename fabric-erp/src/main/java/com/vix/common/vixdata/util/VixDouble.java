package com.vix.common.vixdata.util;

import java.text.DecimalFormat;

/**
 * 数据精度控制
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-8
 */
public class VixDouble {

	/**
	 * 对double数据进行取精度.
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 * 
	 */

	public Double toDouble(Double value, Integer scale) {
		String s = formatDoubleToString(value, scale, false);
		if (s != null) {
			return Double.parseDouble(formatDoubleToString(value, scale, false));
		} else
			return null;
	}

	/**
	 * 
	 * 测试用的main方法.
	 * 
	 * @param arg
	 *            运行参数.
	 */

	public static void main(String[] arg) {
		System.out.println(formatDoubleToString(12.359, 5, false));// 12.35

	}

	/**
	 * 
	 * <b>Summary: double类型保留指定位数小数，返回字符串,五舍六入</b> formatDoubleToMoney()
	 * 
	 * @param value
	 *            传入的参数
	 * @param digits
	 *            指定位数, 如果为空或者小于0返回原值
	 * @param remove
	 *            是否去除0，true 去除，false 不去除
	 * @return
	 */
	public static String formatDoubleToString(Double value, Integer digits, boolean remove) {
		if (value == null) {
			return "";
		}
		if (digits == null || digits < 0) {
			return String.valueOf(value);
		} else if (digits == 0) {
			DecimalFormat df = new DecimalFormat("0");
			return df.format(value);
		} else {
			String temp = "0";
			if (remove) {
				temp = "#";
			}
			StringBuffer buffer = new StringBuffer("0.");
			for (int i = 0; i < digits; i++) {
				buffer.append(temp);
			}
			DecimalFormat df = new DecimalFormat(buffer.toString());
			return df.format(value);
		}
	}
}
