/**
 * 
 */
package com.vix.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.internal.util.WebUtils;

/**
 * com.vix.test.TestTaobao
 *
 * @author zhanghaibing
 *
 * @date 2014年9月28日
 */
public class TestTaobao {
	public static void main(String[] args) {
		String url = "https://oauth.taobao.com/token";
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");

		/* 测试时，需把test参数换成自己应用对应的值 */
		props.put("code", "8SZOJ9fCeL90Im0JBOuy4XOg2040");
		props.put("client_id", "23027418");
		props.put("client_secret", "eda475f2397fb3cb995d6d86ead9c67b");
		props.put("redirect_uri", "www.vixware.com.cn");
		props.put("view", "web");
		String s = "";
		try {
			s = WebUtils.doPost(url, props, 30000, 30000);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
