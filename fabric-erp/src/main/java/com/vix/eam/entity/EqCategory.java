package com.vix.eam.entity;

import com.vix.eam.common.entity.EamBaseEntity;


public class EqCategory extends EamBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** * 设备类别名称	 */
	private String catName;
	/** * 设备类别编码	 */
	private String catCode;
	
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
}
