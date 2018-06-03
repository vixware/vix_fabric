package com.vix.nvix.common.message;

import java.util.HashMap;
import java.util.Map;

import com.vix.core.utils.StrUtils;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * 华信科技短信接口
 * @author Administrator
 * http://dx.ipyy.net/sms.aspx 对应UTF-8
 * http://dx.ipyy.net/smsGBK.aspx 对应GB2312
 * http://dx.ipyy.net/smsJson.aspx 对应UTF-8(返回值为json格式)
 * http://dx.ipyy.net/ensms.ashx 对应UTF-8(加密传输,使用json)
 */
public class HuaXinKeJiMessage {
	
	/**
	 * @param @param url          短信接口的url
	 * @param @param account      短信接口的用户帐号
	 * @param @param password     短信接口的用户密码
	 * @param @param mobile       下发信息的手机号码，多个手机号用半角逗号分隔
	 * @param @param content      下发信息的内容，70个字，超长系统自动截取,按每67个字计费一条。
	 * @return void               返回类型
	 * @throws
	 */
	public static String sendSms(String url,String account, String password, String mobile, String content) throws Exception{
		/** 校验参数 */
		if(StrUtils.objectIsNull(url) || StrUtils.objectIsNull(account) || StrUtils.objectIsNull(password) || StrUtils.objectIsNull(mobile) || StrUtils.objectIsNull(content) ){
			throw new Exception("参数未提交完整！");
		}
		
		/** 配置参数 */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("account", account);
		params.put("password", password);
		params.put("mobile",mobile);
		params.put("content", content);
		params.put("action", "send");
		
		System.out.println(params);
		
		/** 调用接口 */
		HttpResponse response = HttpRequest.post(url).form(params).send();
		
		return response.bodyText();
	}
}
