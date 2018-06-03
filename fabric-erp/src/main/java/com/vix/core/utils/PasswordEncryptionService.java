/*
 * Copyright (C) 2010 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  对登录密码进行加密处理
 * Author:   Jackie
 * Date:     2014.01.16
 * Version:  1.0
 *
 */
package com.vix.core.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
/**
 * Class Name: PasswordEncryptionService
 * 
 * @author Jackie
 *
 *1.当增加一个用户的时候，调用generateSalt()生成盐，然后调用getEncryptedPassword(),同时存储盐和密文。
 *  再次强调，不要存储明文密码，不要存储明文密码，因为没必要！不要担心将盐和密文存储在同一张表中。
 *2.当认证用户的时候，从数据库中取出盐和密文，将他们和明文密码同时传给authenticate()，根据返回结果判断是否认证成功。  
 *3.当用户修改密码的时候，仍然可以使用原来的盐，只需要调用getEncryptedPassword()方法重新生成密文就可以了。
 */
public class PasswordEncryptionService {
	
	/**
	 * 验证输入的密码是否正确
	 * @param attemptedPassword - 输入密码
	 * @param encryptedPassword - 数据库中的加密密码
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	 public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
			   throws NoSuchAlgorithmException, InvalidKeySpecException {
			  // Encrypt the clear-text password using the same salt that was used to
			  // encrypt the original password
			  byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
		
			  // Authentication succeeds if encrypted password that the user entered
			  // is equal to the stored hash
			  return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
		 }
	
	 	 /**
	 	  * 对密码进行加密,非对称密钥
	 	  * @param password - 待加密字符串
	 	  * @param salt  - 通过generateSalt()方法获得。
	 	  * @return
	 	  * @throws NoSuchAlgorithmException
	 	  * @throws InvalidKeySpecException
	 	  */
		 public byte[] getEncryptedPassword(String password, byte[] salt)
			   throws NoSuchAlgorithmException, InvalidKeySpecException {
			  // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
			  // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
			  String algorithm = "PBKDF2WithHmacSHA1";
			  // SHA-1 generates 160 bit hashes, so that's what makes sense here
			  int derivedKeyLength = 160;
			  // Pick an iteration count that works for you. The NIST recommends at
			  // least 1,000 iterations:
			  // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
			  // iOS 4.x reportedly uses 10,000:
			  // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
			  int iterations = 20000;
		
			  KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		
			  SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		
			  return f.generateSecret(spec).getEncoded();
		 }
	
		 /**
		  * 产生Salt值 
		  * @return
		  * @throws NoSuchAlgorithmException
		  */
		 public byte[] generateSalt() throws NoSuchAlgorithmException {
			  // VERY important to use SecureRandom instead of just Random
			  SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		
			  // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
			  byte[] salt = new byte[8];
			  random.nextBytes(salt);
		
			  return salt;
		 }
}
