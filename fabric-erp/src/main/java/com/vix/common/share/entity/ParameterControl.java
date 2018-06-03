package com.vix.common.share.entity;


/**
 * 控制参数
 * @author Administrator
 *
 */
public class ParameterControl extends BaseEntity{
 
	private static final long serialVersionUID = 1L;
	
	/** 模块类型 */
	private String moduleType;
	/** 参数名称 */
	private String parameter;
	/** 参数值 */
	private String value;
	/** 是否内置 */
	private String isPreSetup;
	
	public ParameterControl(){}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsPreSetup() {
		return isPreSetup;
	}

	public void setIsPreSetup(String isPreSetup) {
		this.isPreSetup = isPreSetup;
	}
}
