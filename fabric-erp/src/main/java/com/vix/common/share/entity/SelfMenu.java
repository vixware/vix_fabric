package com.vix.common.share.entity;


/**
 * 用户自定义菜单
 * @author Administrator
 *
 */
public class SelfMenu extends BaseEntity{
 
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private String serialNumber;
	/** 菜单名称 */
	private String menuName;
	/** 功能 */
	private String url;
	/** 级别	 */
	private String level;
	/** 菜单类型 */
	private String type;
	
	public SelfMenu(){}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
