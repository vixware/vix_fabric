package com.vix.core.encode;

/**
 * 明密文接口
 * 
 * @author arron
 * 
 */
public interface Md5Encoder {
	/**
	 * 明文加密
	 * @param  plaintext 明文
	 * @return 密文
	 */
	public String encodeString(String plaintext);

	/**
	 * 密码加密
	 * 
	 * @param plaintext 明文
	 * @param salt 混淆码
	 * @return
	 */
	public String encodeString(String plaintext, String salt);

	/**
	 * 验证密码是否正确
	 * 
	 * @param ciphertext 密文
	 * @param plaintext 明文
	 * @return true:明密文匹配正确；false:明密文匹配错误
	 */
	public boolean isEncodeValid(String ciphertext, String plaintext);

	/**
	 * 验证密码是否正确
	 * 
	 * @param ciphertext 密文
	 * @param plaintext 明文
	 * @param salt 混淆码
	 * @return true:明密文匹配正确；false:明密文匹配错误
	 */
	public boolean isEncodeValid(String ciphertext, String plaintext, String salt);
}
