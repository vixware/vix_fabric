package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * com.vix.crm.business.entity.BaseCondition
 *
 * @author zhanghaibing
 *
 * @date 2015年1月30日
 */
public class BaseCondition extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标准类别
	 */
	private String standardCategory;
	/**
	 * 运算符
	 */
	private String operationalCharacter;
	/**
	 * 运算符中文值
	 */
	private String operationalCharacterName;
	/**
	 * 值
	 */
	private String categoryValue;

	public String getStandardCategory() {
		return standardCategory;
	}

	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
	}

	public String getOperationalCharacter() {
		return operationalCharacter;
	}

	public void setOperationalCharacter(String operationalCharacter) {
		this.operationalCharacter = operationalCharacter;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public String getOperationalCharacterName() {
		return operationalCharacterName;
	}

	public void setOperationalCharacterName(String operationalCharacterName) {
		this.operationalCharacterName = operationalCharacterName;
	}

}
