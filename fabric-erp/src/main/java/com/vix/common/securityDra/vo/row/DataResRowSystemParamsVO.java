package com.vix.common.securityDra.vo.row;

import com.vix.common.security.entity.DataResRowSystemParams;

/**
 * 行集权限系统常量的定义类
 * @author Administrator
 *
 */
public class DataResRowSystemParamsVO extends DataResRowSystemParams {

	private String baseMetaDataClassName;

	public String getBaseMetaDataClassName() {
		return baseMetaDataClassName;
	}

	public void setBaseMetaDataClassName(String baseMetaDataClassName) {
		this.baseMetaDataClassName = baseMetaDataClassName;
	}
	
}
