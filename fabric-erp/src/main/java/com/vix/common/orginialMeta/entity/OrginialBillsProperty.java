package com.vix.common.orginialMeta.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrginialBillsProperty
 * @Description: 单据性质
 * @author wangmingchen
 * @date 2014年11月1日 上午9:23:11
 */
public class OrginialBillsProperty extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 分类编码 */
	private String propertyCode;
	/** 分类名称 */
	private String propertyName;
	/** 分类描述 */
	//private String propertyDescription;
	/**
	 * 单据分类
	 */
	private OrginialBillsCategory orginialBillsCategory;
	
	/** 单据类型 */
	private Set<OrginialBillsType> orginialBillsType = new HashSet<OrginialBillsType>();

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/*public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}*/

	public OrginialBillsCategory getOrginialBillsCategory() {
		return orginialBillsCategory;
	}

	public void setOrginialBillsCategory(OrginialBillsCategory orginialBillsCategory) {
		this.orginialBillsCategory = orginialBillsCategory;
	}

	public Set<OrginialBillsType> getOrginialBillsType() {
		return orginialBillsType;
	}

	public void setOrginialBillsType(Set<OrginialBillsType> orginialBillsType) {
		this.orginialBillsType = orginialBillsType;
	}
	
}
