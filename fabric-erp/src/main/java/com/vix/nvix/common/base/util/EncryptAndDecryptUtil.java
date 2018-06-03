/**
 * 
 */
package com.vix.nvix.common.base.util;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.common.base.util.EncryptAndDecryptUtil
 *
 * @date 2018年1月16日
 */
@SuppressWarnings("restriction")
public class EncryptAndDecryptUtil {
	private static final String KEY_SHA = "SHA";

	private static final String KEY_MD5 = "MD5";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 *  
	 * HmacMD5 
	 * HmacSHA1 
	 * HmacSHA256 
	 * HmacSHA384 
	 * HmacSHA512
	 * </pre>
	 */
	private static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 *            = 需要解密的密码字符串
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 *            = 需要加密的字符数组
	 * @return
	 * @throws Exception
	 */
	private static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 *            = 需要加密的字符数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 *            = 需要加密的字符数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 *            = 密匙加密过的字符数组
	 * @param key
	 *            = 需要加密的字符串
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}

	public static void main(String args[]) throws Exception {
		// 加密：
		String str = "而我却二群翁无群二无群";
		// String str2 = EncryptAndDecryptUtil.encryptBASE64(str.getBytes());

		System.out.println("加密后:" + DESUtil.encrypt(str));
		System.out.println("加密后:" + getEncrypt(str));
		// 解密：
		// String str1 = new String(EncryptAndDecryptUtil.decryptBASE64(str2));
		System.out.println("解密后:" + getDecrypt(getEncrypt(str)));
		System.out.println("解密后:" + DESUtil.decrypt(DESUtil.encrypt(str)));
	}
	// 获取加密后数据
	public static String getEncrypt(String s) throws Exception {
		return EncryptAndDecryptUtil.encryptBASE64(s.getBytes());
	}
	// 获取解密后数据
	public static String getDecrypt(String s) throws Exception {
		return new String(EncryptAndDecryptUtil.decryptBASE64(s));
	}
}
