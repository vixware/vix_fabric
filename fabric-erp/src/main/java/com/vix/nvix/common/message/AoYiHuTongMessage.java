package com.vix.nvix.common.message;

import java.util.HashMap;
import java.util.Map;

import com.vix.core.utils.StrUtils;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * 奥义互通短信接口
 * @author Administrator
 * 发送接口：http://101.200.228.238/smsport/default.asmx/SendSms
 * 查询接口：http://101.200.228.238/smsport/default.asmx?op=GetOrderInfo
 */
public class AoYiHuTongMessage {
	
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
	public static String sendSms(String url,String username, String password, String phonelist, String msg, String longnum) throws Exception{
		/** 校验参数 */
		if(StrUtils.objectIsNull(url) || StrUtils.objectIsNull(username) || StrUtils.objectIsNull(password) || StrUtils.objectIsNull(phonelist) || StrUtils.objectIsNull(msg) ){
			throw new Exception("参数未提交完整！");
		}
		
		/** 配置参数 */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		params.put("phonelist",phonelist);
		params.put("longnum", StrUtils.objectIsNull(longnum)?"":longnum);
		params.put("msg", msg);
		
		System.out.println(params);
		
		/** 调用接口 */
		HttpResponse response = HttpRequest.post(url).form(params).send();
		
		return response.bodyText();
	}
}
