package com.vix.system.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 系统全局配置参数
 * 
 * @author arron
 *
 */
public class GlobalVariable extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/** 参数名称 */
	private String parameterName;
	/** 参数值  */
	private String parameterValue;
	/** 描述 */
	private String description;
	
	public GlobalVariable(){}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}
