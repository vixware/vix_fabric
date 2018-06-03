package com.vix.nvix.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PadPropFileUtil {
	public static Properties loadSysPropertyFile(String fileName) {
		Properties properties = new Properties();
		ClassLoader clsLoader = PadPropFileUtil.class.getClassLoader();
		InputStream in = clsLoader.getResourceAsStream(fileName);
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
