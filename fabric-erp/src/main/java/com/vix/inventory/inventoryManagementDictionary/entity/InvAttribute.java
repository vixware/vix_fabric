/**
 * 
 */
package com.vix.inventory.inventoryManagementDictionary.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库存属性
 * 
 * @ClassFullName com.vix.inventory.inventoryManagementDictionary.entity.
 *                InvAttribute
 *
 * @author bjitzhang
 *
 * @date 2016年2月25日
 *
 */
public class InvAttribute extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 属性编码 */
	private String attributeCode;
	/** 属性名称 */
	private String attributeName;
	/** 单据性质 */
	private Set<InvAttributeValue> invAttributeValues = new HashSet<InvAttributeValue>();

	/**
	 * @return the attributeCode
	 */
	public String getAttributeCode() {
		return attributeCode;
	}

	/**
	 * @param attributeCode
	 *            the attributeCode to set
	 */
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the invAttributeValues
	 */
	public Set<InvAttributeValue> getInvAttributeValues() {
		return invAttributeValues;
	}

	/**
	 * @param invAttributeValues
	 *            the invAttributeValues to set
	 */
	public void setInvAttributeValues(Set<InvAttributeValue> invAttributeValues) {
		this.invAttributeValues = invAttributeValues;
	}

}
