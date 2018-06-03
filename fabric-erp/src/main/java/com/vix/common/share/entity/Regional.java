package com.vix.common.share.entity;

/** 地域 */
public class Regional extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 是否启用 */
	private String enable;
	
	public Regional(){}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}
