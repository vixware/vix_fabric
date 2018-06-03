/**
 * 
 */
package com.vix.common.id;

import java.util.Random;
import java.util.UUID;

/**
 * 产生随机不重复UUID码
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-28
 */
public class VixUUID {
	/**
	 * 产生随机编码
	 * 
	 * length表示生成字符串的长度
	 * 
	 * 产生的编码有可能重复
	 * 
	 * @return
	 */
	public static String createCode(Integer length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length() - 1);
			sb.append(base.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成纯数字编码
	 */
	public static String createCodeByNumber(Integer length) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length() - 1);
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成随机重复的uuid编码
	 * 
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.replaceAll("-", "");
	}
}
