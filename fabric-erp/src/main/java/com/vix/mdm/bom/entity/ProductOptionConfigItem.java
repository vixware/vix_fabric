package com.vix.mdm.bom.entity;

import com.vix.common.share.entity.BaseEntity;

/** 产品可选配置项 */
public class ProductOptionConfigItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 主物料编码 */
	private String itemCode;
	/** 主物料名称 */
	private String itemName;
	/** 物料类型 */
	private String itemType;
	/** 规格型号 */
	private String specification;
	/** 计量单位 */
	private String unit;
	/** 是否有效 */
	private String isValid;
	/** Bom配置节点  */
	private BomNode bomNode;
	
	public ProductOptionConfigItem(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public BomNode getBomNode() {
		return bomNode;
	}

	public void setBomNode(BomNode bomNode) {
		this.bomNode = bomNode;
	}
}
