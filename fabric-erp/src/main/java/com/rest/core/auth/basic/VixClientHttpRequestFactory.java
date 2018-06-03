package com.rest.core.auth.basic;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.userdetails.UserDetails;

import com.vix.common.security.util.SecurityUtil;


public class VixClientHttpRequestFactory extends SimpleClientHttpRequestFactory{
	//HttpComponentsClientHttpRequestFactory
	//private String account;
	//private String password;
	//private Boolean isAuthFromSession = true;
	
	public VixClientHttpRequestFactory() {
		super();
	}

	@Override
	protected void prepareConnection(HttpURLConnection connection,
			String httpMethod) throws IOException {
		//super.prepareConnection(connection, httpMethod);
		//System.out.println("prepareConnection");
		//从Session中获取Session的账号密码信息
		String account = "";
		String pwd = "";
		
		UserDetails ud = SecurityUtil.getCurrentUser();
		if(ud!=null){
			//throw new BizException("没有提供正确的认证信息！");
			account = ud.getUsername();
			pwd = ud.getPassword();
		}
		
		String b64Str = account+":"+pwd;
		String encoding = Base64.encodeBase64String(b64Str.getBytes());
		connection.setRequestProperty  ("Authorization", "Basic " + encoding);
		super.prepareConnection(connection, httpMethod);
		
	}

}
