package com.vix.nvix.common.message;

import com.bcloud.msg.http.HttpSender;
import com.vix.core.utils.StrUtils;

/**
 * 创蓝文化短信接口
 *  url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
 *  account = "jiekou-clcs-10";// 账号
 *  pswd = "Tch666777";// 密码
 *  msg = "【创蓝文化】您好，您的验证码是"+message;// 短信内容
 *  needstatus = true;// 是否需要状态报告，需要true，不需要false
 * @author Administrator
 *
 */
public class ChuangLanWenHuaMessage {
	
	/**
	 * @param @param url       短信接口的url
	 * @param @param username  短信接口的用户帐号
	 * @param @param password  短信接口的用户密码
	 * @param @param phonelist 下发信息的手机号码，多个手机号用半角逗号分隔，每个提交包控制在1000个手机号码内
	 * @param @param msg       下发信息的内容，70个字，超长系统自动截取,按每67个字计费一条。
	 * @param @param longnum   系统分配下行长号码，如果未分配可以填空字符串。
	 * @return void            返回类型
	 * @throws
	 */
	public static String sendSms(String url,String username, String password, String phonelist, String msg) throws Exception{
		/** 校验参数 */
		if(StrUtils.objectIsNull(url) || StrUtils.objectIsNull(username) || StrUtils.objectIsNull(password) || StrUtils.objectIsNull(phonelist) || StrUtils.objectIsNull(msg) ){
			throw new Exception("参数未提交完整！");
		}
		
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码
		String returnString = HttpSender.batchSend(url, username, password, phonelist, msg, needstatus, product, extno);
		
		return returnString;
	}
}
