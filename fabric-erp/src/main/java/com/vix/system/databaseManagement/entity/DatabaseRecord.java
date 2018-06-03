package com.vix.system.databaseManagement.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 数据库记录
 * 
 * @author arron
 */
public class DatabaseRecord extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251788585814297600L;
	/**
	 * 数据库名称
	 */
	private String dataBaseName;
	/**
	 * 数据库服务器地址
	 */
	private String dataBaseServerIp;
	/**
	 * 数据库类型
	 */
	private String dataBaseType;

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getDataBaseServerIp() {
		return dataBaseServerIp;
	}

	public void setDataBaseServerIp(String dataBaseServerIp) {
		this.dataBaseServerIp = dataBaseServerIp;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

}