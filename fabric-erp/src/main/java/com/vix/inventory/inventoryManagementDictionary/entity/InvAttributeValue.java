/**
 * 
 */
package com.vix.inventory.inventoryManagementDictionary.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库存属性值
 * 
 * @ClassFullName com.vix.inventory.inventoryManagementDictionary.entity.
 *                InvAttributeValue
 *
 * @author bjitzhang
 *
 * @date 2016年2月25日
 *
 */
public class InvAttributeValue extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 属性值编码 */
	private String attributeValueCode;
	/** 属性值名称 */
	private String attributeValueName;
	/**
	 * 单据分类
	 */
	private InvAttribute invAttribute;

	/**
	 * @return the attributeValueCode
	 */
	public String getAttributeValueCode() {
		return attributeValueCode;
	}

	/**
	 * @param attributeValueCode
	 *            the attributeValueCode to set
	 */
	public void setAttributeValueCode(String attributeValueCode) {
		this.attributeValueCode = attributeValueCode;
	}

	/**
	 * @return the attributeValueName
	 */
	public String getAttributeValueName() {
		return attributeValueName;
	}

	/**
	 * @param attributeValueName
	 *            the attributeValueName to set
	 */
	public void setAttributeValueName(String attributeValueName) {
		this.attributeValueName = attributeValueName;
	}

	/**
	 * @return the invAttribute
	 */
	public InvAttribute getInvAttribute() {
		return invAttribute;
	}

	/**
	 * @param invAttribute
	 *            the invAttribute to set
	 */
	public void setInvAttribute(InvAttribute invAttribute) {
		this.invAttribute = invAttribute;
	}

}
