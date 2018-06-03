package com.vix.traceability.controller;

import java.lang.reflect.Field;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 
 * com.traceability.controller.TraceabilityController
 *
 * @author bjitzhang
 *
 * @date 2015年9月25日
 */
@Controller("traceabilityController")
@Scope("prototype")
public class TraceabilityController {
	@Autowired

	public static String getTypeName(Class<?> cls, String fieldName) {
		Field[] fieldlist = cls.getDeclaredFields();
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			if (fieldName.equals(fld.getName())) {
				return fld.getType().getName();
			}
		}
		return null;
	}

	/** 解码 */
	public String decode(String str, String enc) throws Exception {
		str = URLDecoder.decode(str, enc);
		return str;
	}

	public String getCode(String codeData, int i) {
		String str_m = String.valueOf(i);
		str_m = codeData.substring(0, codeData.length() - str_m.length()) + str_m;
		return str_m;
	}

}
