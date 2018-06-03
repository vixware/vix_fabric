package com.vix.system.systemVar.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: SystemVar
 * @Description: 系统变量   按承租户隔离    不使用companyinnercode
 * @author wangmingchen
 * @date 2014年12月20日 下午3:10:40
 */
public class SystemVar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 类型变量名 */
	private String varCode;
	
	/** 默认值 */
	private String defaultValue;

	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
}
