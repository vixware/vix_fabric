/**
 * 
 */
package com.vix.system.billTypeSet.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 单据性质
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class BillsPropertyDictionary extends BaseEntity {

	private static final long serialVersionUID = -4613757386606245604L;
	/** 分类编码 */
	private String propertyCode;
	/** 分类名称 */
	private String propertyName;
	/** 分类描述 */
	private String propertyDescription;
	/**
	 * 单据分类
	 */
	private BillsCategoryDictionary billsCategoryDictionary;

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

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public BillsCategoryDictionary getBillsCategoryDictionary() {
		return billsCategoryDictionary;
	}

	public void setBillsCategoryDictionary(BillsCategoryDictionary billsCategoryDictionary) {
		this.billsCategoryDictionary = billsCategoryDictionary;
	}

}
