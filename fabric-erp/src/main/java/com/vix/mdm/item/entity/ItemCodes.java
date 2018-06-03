package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 物料(产品)编码 */
public class ItemCodes extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 编码类型 */
	private String codeType;
	/** 编码值 */
	private String codeValue;
	/** 是否有效 */
	private String isValid;

	public ItemCodes(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
