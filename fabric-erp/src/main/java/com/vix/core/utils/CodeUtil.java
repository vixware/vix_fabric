package com.vix.core.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;
import org.springframework.web.util.UriUtils;

import com.vix.core.encode.AESCodec;

/**
 * 编码生成的相关 
 * @author shadow
 *
 */
public class CodeUtil {

	public static void main(String[] args) throws Exception {
		//String url = "http://www.163.com";
		//String url = "http://www.163.com?a=1&b=王&c=3&d=李";
		//System.out.println(appendParam4URL(url, "ticket", "王", true));
		System.out.println(key);
		String enc = Base64.encodeBase64String(key);
		System.out.println(enc);
		byte[] dec = Base64.decodeBase64(enc);
		System.out.println(dec);
		System.out.println(Base64.encodeBase64String(dec));
		System.out.println("---------------------------------");
		
		String str="AES";
		System.out.println("原文："+str);
		/*
		//加密数据
		byte[] jiamiArray = encryptByte(str);
		System.out.println("加密后："+Base64.encodeBase64String(jiamiArray));
		//解密数据
		byte[] jiemi = decryptByte(jiamiArray);
		System.out.println("解密后："+new String(jiemi));
		*/
		
		String encodeStr = encodeStr(str);
		System.out.println("密："+  encodeStr);
		System.out.println("解密："+ decodeStr(encodeStr));
		
		
		
		String userAccount = "gw";
		String tenantId = "123";
		/*
		String ticket = encodeTicket(userAccount, tenantId);
		System.out.println("ticket:"+ticket);
		String[] resA = decodeTicket(ticket);
		System.out.println(resA[0] + "--------" +resA[1]);
		*/
		//qLSmm5uyxQv+wzQSoSv3EQ==
		//System.out.println(URLParamsUtil.urlDecode("qLSmm5uyxQv%2BwzQSoSv3EQ%3D%3D%0D%0A"));
		String ticketDec = URLParamsUtil.urlDecode("qLSmm5uyxQv%2BwzQSoSv3EQ%3D%3D%0D%0A");
		System.out.println(ticketDec);
		//System.out.println(decodeTicket(ticketDec));

	}
	
	/** 密钥 */
	private static byte[] key = AESCodec.initkey();

	/**
	 * 对称加密1
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static byte[] encryptByte(String str) throws Exception{
		byte[] data=AESCodec.encrypt(str.getBytes(), key);
		return data;
	}
	
	/**
	 * 对称解密1 encryptByte
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static byte[] decryptByte(byte[] data) throws Exception{ 
		byte[] dataRes = AESCodec.decrypt(data, key);;
		return dataRes;
	}
	
	
	
	
	/**
	 * 加密编码
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encodeStr(String str) throws Exception{
		byte[] jiamiArray = encryptByte(str);
		return Base64.encodeBase64String(jiamiArray);
	}

	/**
	 * 加密解码 encodeStr方法
	 * @param encodeStr
	 * @return
	 * @throws Exception
	 */
	public static String decodeStr(String encodeStr) throws Exception{ 
		byte[] encodeStrArray = Base64.decodeBase64(encodeStr);
		byte[] dataRes = AESCodec.decrypt(encodeStrArray, key);;
		return new String(dataRes);
	}
	
	
	
	
	/**
	 * 为URL添加参数
	 * @param url
	 * @param paramName
	 * @param paramValue
	 * @param isEncodeParamValue
	 * @return
	 */
	public static String appendParam4URL(String url,String paramName,String paramValue,Boolean isEncodeParamValue){
		Assert.notNull(url, "URL不能为空！");
		Assert.isTrue(url.startsWith("http://"),"url不是http开头！");
		
		if(url.indexOf('?')==-1){
			url += "?"+paramName + "=";
		}else{
			url += "&"+paramName + "=";
		}
		
		if(isEncodeParamValue){
			try {
				paramValue = UriUtils.encodeQueryParam(paramValue, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		url += paramValue;
		return url;
	}
	

	/**
	 * 生成用户的ticket
	 * @param userAccount
	 * @param tenantId
	 * @return
	
	public static String encodeTicket(String userAccount,String tenantId){
		String ticket = null;
		//Md5EncoderImpl md5 = new Md5EncoderImpl();
		//ticket = md5.encodeString(tenantId + "#" +userAccount, UserAccountSalt);
		//AESCodec
		try {
			ticket = encodeStr(tenantId + "####" +userAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}
	 */
	/**
	 * 解码ticket  
	 * @param userAccount
	 * @param tenantId
	 * @return  0  tenantId   1 account
	 
	public static String[] decodeTicket(String ticket){
		Assert.notNull(ticket,"ticket为空！");
		String[] resArray = null;
		try {
			String codeStr = decodeStr(ticket);
			resArray = StringUtils.split(codeStr, "\\####");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resArray;
	}*/
}
