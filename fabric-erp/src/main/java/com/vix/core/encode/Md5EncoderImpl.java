package com.vix.core.encode;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * MD5密码加密
 * 
 * @author arron
 * 
 */
public class Md5EncoderImpl implements Md5Encoder {
	
	@Override
	public String encodeString(String plaintext) {
		return encodeString(plaintext, defaultSalt);
	}

	@Override
	public String encodeString(String plaintext, String salt) {
		String saltedPass = mergeStringAndSalt(plaintext, salt, false);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		return new String(Hex.encodeHex(digest));
	}

	@Override
	public boolean isEncodeValid(String ciphertext, String plaintext) {
		return isEncodeValid(ciphertext, plaintext, defaultSalt);
	}

	@Override
	public boolean isEncodeValid(String ciphertext, String plaintext, String salt) {
		if (ciphertext == null) {
			return false;
		}
		String pass2 = encodeString(plaintext, salt);
		return ciphertext.equals(pass2);
	}

	protected final MessageDigest getMessageDigest() {
		String algorithm = "MD5";
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm ["
					+ algorithm + "]");
		}
	}

	protected String mergeStringAndSalt(String plaintext, Object salt,
			boolean strict) {
		if (plaintext == null) {
			plaintext = "";
		}
		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1)
					|| (salt.toString().lastIndexOf("}") != -1)) {
				throw new IllegalArgumentException(
						"Cannot use { or } in salt.toString()");
			}
		}
		if ((salt == null) || "".equals(salt)) {
			return plaintext;
		} else {
			return plaintext + "{" + salt.toString() + "}";
		}
	}

	/** 混淆码。防止破解。 */
	private String defaultSalt;

	/**
	 * 获得混淆码
	 * @return 混淆码
	 */
	public String getDefaultSalt() {
		return defaultSalt;
	}

	/**
	 * 设置混淆码
	 * @param salt
	 */
	public void setDefaultSalt(String defaultSalt) {
		this.defaultSalt = defaultSalt;
	}
}
