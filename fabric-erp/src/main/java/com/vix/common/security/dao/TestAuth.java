package com.vix.common.security.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.google.common.collect.ImmutableSet;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;

public class TestAuth {

	public static void main(String[] args) {
		Object obj = null;
		Assert.notNull(obj, "is null");
		
		Md5Encoder md = new Md5EncoderImpl();
		System.out.println(md.encodeString("123"));
		String sss = "1O1_123";
		sss = " ";
		System.out.println(org.springframework.util.StringUtils.hasText(sss));
		System.out.println(StringUtils.substringBefore(sss, "_"));
		
		System.out.println(ImmutableSet.builder().build()==null);
		
		
		String str = "sm_00100200300_";
		//String str2 = "SAM_002";
		str = "SAM_002";
		
		int s1 = StringUtils.indexOf(str, "_");
		int maxSize = s1+4;
		int strSize = str.length();
		//System.out.println("MaxSize:"+maxSize+";strSize"+str.length());
		if(strSize>maxSize){
			System.out.println(StringUtils.substring(str, 0, strSize-3));
		}else{
			System.out.println(str);
			//System.out.println(StringUtils.substring(str, 0, maxSize));
		}
		
		
		/*int s2 = StringUtils.indexOf(str2, "_");
		System.out.println(s2);
		System.out.println(StringUtils.substring(str2, 0, s2+4));*/
	}
	
	public static void testTopAuthcode(){
		String str = "sm_001002003_";
		String str2 = "SAM_002003004";

		int s1 = StringUtils.indexOf(str, "_");
		
		System.out.println(StringUtils.substring(str, 0, s1+4));
		
		int s2 = StringUtils.indexOf(str2, "_");
		System.out.println(s2);
		System.out.println(StringUtils.substring(str2, 0, s2+4));
	}

}
