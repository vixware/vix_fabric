package com.vix.core.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

	/**
	 *  将字符传转换成List
	 * @param <T>
	 * @param str        数据字符串
	 * @param separator  数据分隔符
	 * @return
	 */
	public static List<String> convertStringToLongList(String strs,String separator){
		String[] strArray = strs.split(separator);
		List<String> list = new ArrayList<String>();
		try{
			for(String str : strArray){
				list.add(str);
			}
		}catch(Exception ex){
			throw new RuntimeException("不支持的类型转换,转换类型与数据实际类型不符!",ex);
		}
		return list;
	}
}
