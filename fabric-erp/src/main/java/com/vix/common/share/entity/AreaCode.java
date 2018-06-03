package com.vix.common.share.entity;


/**
 * 地区代码
 * @author Administrator
 *
 */
public class AreaCode extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 地区 */
	private String area;
	
	public AreaCode(){}
 
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
