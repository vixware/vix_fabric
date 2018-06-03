package com.vix.common.orginialMeta.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrginialVar
 * @Description: 系统变量
 * @author wangmingchen
 * @date 2014年12月20日 下午3:10:40
 */
public class OrginialVar extends BaseEntity {

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
	
	//description 描述
}
