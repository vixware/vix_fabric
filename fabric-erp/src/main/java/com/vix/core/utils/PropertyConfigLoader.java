package com.vix.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfigLoader {
	
	/** 加载properties文件，读取用户常量 */
	static {
		Properties properties = new Properties();
		ClassLoader clsLoader = PropertyConfigLoader.class.getClassLoader();
		InputStream in = clsLoader.getResourceAsStream("/application.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** 数据库类型 */
		dbName = properties.getProperty("dbName");
		/** 数据库类型 */
		dbType = properties.getProperty("dbType");
		/** 数据库驱动 */
		dbDriver = properties.getProperty("jdbc.driver");
		/** 数据库URL */
		dbUrl = properties.getProperty("jdbc.url");
		/** 数据库类型 */
		userName = properties.getProperty("jdbc.username");
		/** 数据库类型 */
		userPwd = properties.getProperty("jdbc.password");
		/** 规则引擎默认规则路径 */
		defaultDroolsRulePath = properties.getProperty("drools_rule");
		/** lucene 索引存放目录 */
		luceneDir = properties.getProperty("luceneDir");
		/** ip数据库路径 */
		ipDbPath = properties.getProperty("ipDbPath");
		/** 内部公告内容存放目录 */
		crm_innerBulletinPath = properties.getProperty("crm_innerBulletinPath");
		/** 系统顶级机构编码 */
		system_tenantId = properties.getProperty("system_tenantId");
		/** 系统顶级机构编码 */
		system_companyCode = properties.getProperty("system_companyCode");
		
	}
	
	/** 数据库名称 */
	public static String dbName;
	/** 数据库类型 */
	public static String dbType;
	/** 数据库驱动 */
	public static String dbDriver;
	/** 数据库url */
	public static String dbUrl;
	/** 用户名 */
	public static String userName;
	/** 密码 */
	public static String userPwd;
	/** 系统顶级机构编码 */
	public static String system_companyCode;
	/** 承租户id */
	public static String system_tenantId;
	/** lucene目录 */
	public static String luceneDir;
	/** 规则引擎默认规则路径 */
	public static String defaultDroolsRulePath;
	/** ip数据库路径 */
	public static String ipDbPath;
	/** 内部公告内容存放目录 */
	public static String crm_innerBulletinPath;
}
