package com.vix.nvix.wx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.wx.entity.WxpSysSequence;

public class PadStrUtil extends StrUtils {

	public static String sequence_mark_trade_no = "trade_no";
	/**
	 * Map<siteId, Map<seqMark,sequenceObj>>
	 */
	static Map<String, Map<String, WxpSysSequence>> seqMap;
	static Object seq_lock;

	/**
	 * 生成序列号,并不能保证生成的序号从未被使用过，尽量保证scopeMark不重复使用（如使用时间）
	 * 
	 * @param siteId
	 * @param seqMark
	 *            序列标示
	 * @param prefix
	 *            序列前缀
	 * @param scopeMark
	 *            序列重置标示，不同则重置,如果使用之前使用过的标示，可能出现重复的序号
	 * @param seqLength
	 *            序列部分长度，不足补0
	 * @param service
	 * @return
	 */
	public static String genSequenceNo(String appId, String seqMark, String prefix, String scopeMark, int seqLength, IBaseHibernateService service) {/*
																																						 * try{
																																						 * if
																																						 * (
																																						 * PadStrUtil
																																						 * .
																																						 * isEmpty
																																						 * (
																																						 * seqMark)
																																						 * ||
																																						 * service
																																						 * =
																																						 * =
																																						 * null
																																						 * ||
																																						 * appId
																																						 * =
																																						 * =
																																						 * null
																																						 * )
																																						 * return
																																						 * null;
																																						 * if
																																						 * (
																																						 * seqMap
																																						 * =
																																						 * =
																																						 * null
																																						 * )
																																						 * {
																																						 * seqMap
																																						 * =
																																						 * new
																																						 * HashMap
																																						 * <
																																						 * String,
																																						 * Map
																																						 * <
																																						 * String
																																						 * ,
																																						 * WxpSysSequence
																																						 * >
																																						 * >
																																						 * (
																																						 * )
																																						 * ;
																																						 * seq_lock
																																						 * =
																																						 * new
																																						 * Object
																																						 * (
																																						 * )
																																						 * ;
																																						 * }
																																						 * Map
																																						 * <
																																						 * String
																																						 * ,
																																						 * WxpSysSequence>
																																						 * siteSeqMap
																																						 * =
																																						 * seqMap
																																						 * .
																																						 * get
																																						 * (
																																						 * appId
																																						 * )
																																						 * ;
																																						 * if
																																						 * (
																																						 * siteSeqMap
																																						 * =
																																						 * =
																																						 * null
																																						 * )
																																						 * {
																																						 * siteSeqMap
																																						 * =
																																						 * new
																																						 * HashMap
																																						 * <
																																						 * String
																																						 * ,
																																						 * WxpSysSequence
																																						 * >
																																						 * (
																																						 * )
																																						 * ;
																																						 * seqMap
																																						 * .
																																						 * put
																																						 * (
																																						 * appId,
																																						 * siteSeqMap
																																						 * )
																																						 * ;
																																						 * }
																																						 * synchronized
																																						 * (
																																						 * seq_lock)
																																						 * {
																																						 * WxpSysSequence
																																						 * seq
																																						 * =
																																						 * siteSeqMap
																																						 * .
																																						 * get
																																						 * (
																																						 * seqMark
																																						 * )
																																						 * ;
																																						 * if
																																						 * (
																																						 * seq
																																						 * =
																																						 * =
																																						 * null)
																																						 * {
																																						 * seq
																																						 * =
																																						 * service
																																						 * .
																																						 * findEntityByAttribute
																																						 * (
																																						 * WxpSysSequence
																																						 * .
																																						 * class,
																																						 * "scopeMark"
																																						 * ,
																																						 * scopeMark
																																						 * ,
																																						 * appId
																																						 * )
																																						 * ;
																																						 * if
																																						 * (
																																						 * seq
																																						 * ==
																																						 * null)
																																						 * {
																																						 * seq
																																						 * =
																																						 * new
																																						 * WxpSysSequence
																																						 * (
																																						 * )
																																						 * ;
																																						 * seq
																																						 * .
																																						 * setSeqMark
																																						 * (
																																						 * seqMark
																																						 * )
																																						 * ;
																																						 * seq
																																						 * .
																																						 * setScopeMark
																																						 * (
																																						 * scopeMark
																																						 * )
																																						 * ;
																																						 * seq
																																						 * .
																																						 * setSeqVal
																																						 * (
																																						 * 1L
																																						 * )
																																						 * ;
																																						 * seq
																																						 * .
																																						 * setAppId
																																						 * (
																																						 * appId
																																						 * )
																																						 * ;
																																						 * service
																																						 * .
																																						 * merge
																																						 * (
																																						 * seq
																																						 * )
																																						 * ;
																																						 * }
																																						 * 
																																						 * siteSeqMap
																																						 * .
																																						 * put
																																						 * (
																																						 * seqMark,
																																						 * seq
																																						 * )
																																						 * ;
																																						 * }
																																						 * 
																																						 * long
																																						 * seqVal
																																						 * =
																																						 * seq
																																						 * .
																																						 * getSeqVal
																																						 * (
																																						 * )
																																						 * ;
																																						 * 
																																						 * if
																																						 * (
																																						 * PadStrUtil
																																						 * .
																																						 * isNotEmpty
																																						 * (
																																						 * scopeMark
																																						 * )
																																						 * )
																																						 * {
																																						 * String
																																						 * sMark
																																						 * =
																																						 * seq
																																						 * .
																																						 * getScopeMark
																																						 * (
																																						 * )
																																						 * ;
																																						 * if
																																						 * (
																																						 * !
																																						 * scopeMark
																																						 * .
																																						 * equals
																																						 * (
																																						 * sMark
																																						 * )
																																						 * )
																																						 * {
																																						 * /
																																						 * /
																																						 * 处理scopeMark
																																						 * ，
																																						 * 与输入不同则复位
																																						 * seq
																																						 * .
																																						 * setScopeMark
																																						 * (
																																						 * scopeMark
																																						 * )
																																						 * ;
																																						 * seqVal
																																						 * =
																																						 * 1;
																																						 * }
																																						 * }
																																						 * 
																																						 * 
																																						 * /
																																						 * /
																																						 * 如果有seqLength
																																						 * 用0补位
																																						 * String
																																						 * seqStr
																																						 * =
																																						 * "";
																																						 * if
																																						 * (
																																						 * seqLength
																																						 * >
																																						 * 0)
																																						 * {
																																						 * String
																																						 * fromStr
																																						 * =
																																						 * "10000000000000000000000000000000000000000";
																																						 * String
																																						 * baseStr
																																						 * =
																																						 * fromStr
																																						 * .
																																						 * substring
																																						 * (
																																						 * 0
																																						 * ,
																																						 * seqLength
																																						 * +
																																						 * 1
																																						 * )
																																						 * ;
																																						 * long
																																						 * baseVal
																																						 * =
																																						 * Long
																																						 * .
																																						 * parseLong
																																						 * (
																																						 * baseStr
																																						 * )
																																						 * ;
																																						 * if
																																						 * (
																																						 * baseVal
																																						 * >
																																						 * seqVal)
																																						 * seqStr
																																						 * =
																																						 * String
																																						 * .
																																						 * valueOf
																																						 * (
																																						 * baseVal
																																						 * +
																																						 * seqVal
																																						 * )
																																						 * ;
																																						 * else
																																						 * seqStr
																																						 * =
																																						 * String
																																						 * .
																																						 * valueOf
																																						 * (
																																						 * baseVal
																																						 * +
																																						 * 1
																																						 * )
																																						 * ;
																																						 * 
																																						 * seqStr
																																						 * =
																																						 * seqStr
																																						 * .
																																						 * substring
																																						 * (
																																						 * 1
																																						 * )
																																						 * ;
																																						 * }
																																						 * else
																																						 * {
																																						 * seqStr
																																						 * =
																																						 * String
																																						 * .
																																						 * valueOf
																																						 * (
																																						 * seqVal
																																						 * )
																																						 * ;
																																						 * }
																																						 * 
																																						 * /
																																						 * /
																																						 * 更新sequence到下一个未用值
																																						 * seqVal
																																						 * +
																																						 * +
																																						 * ;
																																						 * seq
																																						 * .
																																						 * setSeqVal
																																						 * (
																																						 * seqVal
																																						 * )
																																						 * ;
																																						 * service
																																						 * .
																																						 * merge
																																						 * (
																																						 * seq
																																						 * )
																																						 * ;
																																						 * 
																																						 * /
																																						 * /
																																						 * 拼接完整字符串
																																						 * seqStr
																																						 * =
																																						 * prefix
																																						 * +
																																						 * seqStr;
																																						 * 
																																						 * return
																																						 * seqStr;
																																						 * }
																																						 * }catch
																																						 * (
																																						 * Exception
																																						 * e)
																																						 * {
																																						 * e
																																						 * .
																																						 * printStackTrace
																																						 * (
																																						 * )
																																						 * ;
																																						 * return
																																						 * null;
																																						 * }
																																						 */

		return null;
	}

	public static List<String> splitStr(String oriStr, String septChar) {
		if (StrUtils.isEmpty(oriStr))
			return null;
		if (StrUtils.isEmpty(septChar)) {
			List list = new ArrayList<String>();
			list.add(oriStr);
			return list;
		}

		List<String> list = new ArrayList<String>();
		int idx = oriStr.indexOf(septChar);
		while (idx != -1) {
			list.add(oriStr.substring(0, idx));
			oriStr = oriStr.substring(idx + septChar.length());
			idx = oriStr.indexOf(septChar);
		}
		// 最后一个参数
		list.add(oriStr);

		return list;
	}

	/**
	 * 将格式 x1=aaa&x2=bbb&x3=ccc这样格式的参数组转成map格式
	 * 
	 * @param paramStr
	 * @return
	 */
	public static Map<String, String> strWebParamsToMap(String paramStr) {
		Map<String, String> map = new HashMap<String, String>();
		if (StrUtils.isNotEmpty(paramStr)) {
			String[] paramPairs = paramStr.split("&");
			for (String pair : paramPairs) {
				String[] keyAndValue = pair.split("=");
				map.put(keyAndValue[0], keyAndValue[1]);
			}
		}
		return map;
	}

	public static Integer strNumToInteger(String num) {
		return strNumToInteger(num, null);
	}

	public static Integer strNumToInteger(String num, Integer defaultVal) {
		Integer ret = defaultVal;
		if (StrUtils.isNotEmpty(num)) {
			try {
				ret = Integer.parseInt(num);
			} catch (Exception e) {
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
	}
}
