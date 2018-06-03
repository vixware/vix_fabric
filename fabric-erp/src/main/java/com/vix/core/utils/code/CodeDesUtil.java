package com.vix.core.utils.code;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class CodeDesUtil {

	/** 默认密钥 不要修改 */
	private static String strDefaultKey = "vixErp";

	private Cipher encryptCipher = null;

	private Cipher decryptCipher = null;

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 * @throws Exception
	 */
	public CodeDesUtil() throws Exception {
		this(strDefaultKey);
	}

	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	public CodeDesUtil(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	
	
	/**
	 * 生成用户的ticket
	 * @param userAccount
	 * @param tenantId
	 * @return
	 * @throws Exception 
	 */
	public static String encodeTicket(String userAccount,String tenantId) {
		String ticket = null;
		/*
		Md5EncoderImpl md5 = new Md5EncoderImpl();
		ticket = md5.encodeString(tenantId + "#" +userAccount, UserAccountSalt);
		*/
		//AESCodec
		
		
		/*try {
			ticket = encodeStr(tenantId + "####" +userAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;*/
		
		try {
			CodeDesUtil des = new CodeDesUtil();
			ticket = des.encrypt(tenantId + "####" +userAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}
	
	/**
	 * 解码ticket  
	 * @param userAccount
	 * @param tenantId
	 * @return  0  tenantId   1 account
	 * @throws Exception 
	 */
	public static String[] decodeTicket(String ticket) {
		/*Assert.notNull(ticket,"ticket为空！");
		String[] resArray = null;
		try {
			String codeStr = decodeStr(ticket);
			resArray = StringUtils.split(codeStr, "\\####");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resArray;*/
		Assert.notNull(ticket,"ticket为空！");
		
		String[] resArray = null;
		try {
			CodeDesUtil des = new CodeDesUtil();
			String codeStr = des.decrypt(ticket);
			resArray = StringUtils.split(codeStr, "\\####");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resArray;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/*String test = "Hellow Word!";
			CodeDesUtil des = new CodeDesUtil();// 默认密钥
			// DESPlus des = new DESPlus("leemenz");//自定义密钥
			System.out.println("加密前的字符：" + test);
			System.out.println("加密后的字符：" + des.encrypt(test));
			System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));
			
			
			
			
			
			String userAccount = "gw";
			String tenantId = "123";
			
			userAccount = "gw_nr";
			tenantId = "123";//a2859b53854196b567ff3b6439cca7ef
			String ticket = encodeTicket(userAccount, tenantId);
			System.out.println("ticket:"+ticket);
			String[] resA = decodeTicket(ticket);
			System.out.println(resA[0] + "--------" +resA[1]);
			*/
			
			String userAccount = "gw";
			String pwd = "1";
			String tenantId = "123";
			//String res =  byteArr2HexStr(new byte[]{userAccount.getBytes("utf-8"),pwd.getBytes("utf-8"),tenantId.getBytes("utf-8")});
			
			CodeDesUtil cdu = new CodeDesUtil();
			System.out.println(cdu.encrypt( "wx4469d6fdf60c1bff"));//wx4469d6fdf60c1bff
			String userAccount_enc =  cdu.encrypt( userAccount);
			String pwd_enc = cdu.encrypt( pwd);
			String tenantId_enc =  cdu.encrypt( tenantId);
			System.out.println("JM:"+userAccount_enc);
			System.out.println("JM:"+pwd_enc);
			System.out.println("JM:"+tenantId_enc);
			String userAccount_dec =  cdu.decrypt( userAccount_enc);
			String pwd_dec = cdu.decrypt(pwd_enc);
			String tenantId_dec =  cdu.decrypt( tenantId_enc);
			System.out.println(userAccount_dec);
			System.out.println(pwd_dec);
			System.out.println(tenantId_dec);
			//qLSmm5uyxQv+wzQSoSv3EQ==
			//System.out.println(URLParamsUtil.urlDecode("qLSmm5uyxQv%2BwzQSoSv3EQ%3D%3D%0D%0A"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
