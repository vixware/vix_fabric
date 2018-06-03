package com.vix.sales.credit.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 风险等级
 * @author Administrator
 *
 */
public class RiskLevel extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 风险类型 */
	private String levelType;
	/** 等级 */
	private String level;
	/** 是否控制 1：控制 ， 0：不控制 */
	private String isControl;
	/** 控制方法 */
	private String controlMethod;
	
	public RiskLevel(){}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIsControl() {
		return isControl;
	}

	public void setIsControl(String isControl) {
		this.isControl = isControl;
	}

	public String getControlMethod() {
		return controlMethod;
	}

	public void setControlMethod(String controlMethod) {
		this.controlMethod = controlMethod;
	}
}
