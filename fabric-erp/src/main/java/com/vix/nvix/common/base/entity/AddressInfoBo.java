package com.vix.nvix.common.base.entity;

public class AddressInfoBo {
 
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 顺序 */
	private Long orderBy;
	/** 首字母 */
	private String firstLetter;
	/** 备注 */
	private String parentCode;

	public AddressInfoBo(){}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}
