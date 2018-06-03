package com.vix.nvix.common.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 工作台模板管理
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.common.base.entity.HomeTemplate
 *
 * @date 2018年1月8日
 */
public class HomeTemplate extends BaseEntity {

	private static final long serialVersionUID = -1l;

	public HomeTemplate() {
	}
	/**
	 * 行业类型编码
	 */
	private String typeCode;

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}