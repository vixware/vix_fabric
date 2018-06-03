/**
 * 
 */
package com.vix.drp.distributionsystemrelationship;

import java.util.Random;
import java.util.UUID;

/**
 * @author zhanghaibing
 * 
 * @date 2013-10-28
 */
public class Test {
	// 产生随机编码
	// length表示生成字符串的长度
	public static String createCode() {
		int length = 12;
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length() - 1);
			sb.append(base.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(createCode());
		System.out.println(getUUID());
	}
}
