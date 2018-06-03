package com.vix.eam.entity;

import com.vix.eam.common.entity.EamBaseEntity;


public class EqLevel extends EamBaseEntity {

	private String levelName;
	private String levelCode;
	private Integer orderNo;
	private Integer hasSubEq;
	private String levelType;
	
	private EqLevel parentLevel;
	
	
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getHasSubEq() {
		return hasSubEq;
	}
	public void setHasSubEq(Integer hasSubEq) {
		this.hasSubEq = hasSubEq;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public EqLevel getParentLevel() {
		return parentLevel;
	}
	public void setParentLevel(EqLevel parentLevel) {
		this.parentLevel = parentLevel;
	}
}
