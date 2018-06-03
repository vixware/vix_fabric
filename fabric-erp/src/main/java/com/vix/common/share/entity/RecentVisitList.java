package com.vix.common.share.entity;

import java.util.Date;

/**
 * 最近访问对象
 * @author Administrator
 *
 */
public class RecentVisitList extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 序号 */
	private String serialNumber;
	/** 对象名称 */
	private String menuName;
	/** 对象类型 */
	private String type;
	/** 功能名称 */
	private String functionName;
	/** 功能URL */
	private String url;
	/** 模块 */
	private String module;
	/** 级别 */
	private String level;
	/** 访问时间 */
	private Date visitTime;
	/** 访问用户编码 */
	private String userCode;
	/** 访问用户 */
	private String user;
	
	public RecentVisitList(){}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
