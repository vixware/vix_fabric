package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 用于数据集权限的一些常量的配置
 * 用户登陆后 需要把当前对象放到容器的常量中
 * @author Administrator
 *
 */
public class DataResRowParam extends BaseEntity {

	/** 变量名称 */
	private String paramName;
	
	/** 变量显示名称 */
	private String dspParamName;
	
	/** 
	 * C：常量值 直接读取paramValue即可
	 * CUR_USER_ID 当前用户id 需要调用业务方法获取
	 * CUR_ROLE_ID 当前用户角色
	 * 其他待定
	 *  */
	private String paramType;
	
	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	/** 变量值   取值有 常量 、sql、系统登录后常量（如用户登陆后的当前用户id  当前用户角色等等 ） */
	private String paramValue;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getDspParamName() {
		return dspParamName;
	}

	public void setDspParamName(String dspParamName) {
		this.dspParamName = dspParamName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
}
