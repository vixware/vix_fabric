package com.rest.core.utils;

import com.vix.core.utils.code.CodeDesUtil;

public class RestAuthCodeUtil {

	/**
	 * @throws Exception 
	 * @Title: encodeSeviceAuth
	 * @Description: 加密
	 * @param @param userAccount
	 * @param @param pwd
	 * @param @param tenantId
	 * @param @return    设定文件
	 * @return String[]    返回类型  数组，长度为3，顺序为加密后的  账号、密码、承租户标识
	 * @throws
	 */
	public static String[] encodeSeviceAuth(String userAccount,String pwd,String tenantId) throws Exception {
		String[] resArray = new String[3];
		
		CodeDesUtil des = new CodeDesUtil();
		
		resArray[0] =  des.encrypt( userAccount);
		resArray[1] = des.encrypt( pwd);
		resArray[2] =  des.encrypt( tenantId);
		
		return resArray;
	}
	
	/**
	 * @Title: decodeSeviceAuth
	 * @Description: 解密
	 * @param @param userAccount_Enc
	 * @param @param pwd_Enc
	 * @param @param tenantId_Enc
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return String[]    返回类型  数组，长度为3，顺序为解密后的  账号、密码、承租户标识
	 * @throws
	 */
	public static String[] decodeSeviceAuth(String userAccount_Enc,String pwd_Enc,String tenantId_Enc) throws Exception {
		String[] resArray = new String[3];
		
		CodeDesUtil des = new CodeDesUtil();
		
		resArray[0] =  des.decrypt( userAccount_Enc);
		resArray[1] = des.decrypt( pwd_Enc);
		resArray[2] =  des.decrypt( tenantId_Enc);
		
		return resArray;
	}
}
