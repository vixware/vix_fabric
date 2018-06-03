package com.vix.inventory.reverseflushingmaterial.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 盘点单子表
 */

public class ReverseFlushingMaterialItem extends BaseEntity {
	private static final long serialVersionUID = -1045939961180527315L;
	/**
	 * 材料编码
	 */
	private String itemcode;
	/**
	 * 材料名称
	 */
	private String itemname;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 物料类型
	 */
	private String itemtype;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 单价
	 */
	private Double price;
	/**
	 * 请领数量
	 */
	private Long requisitionCount;
	/**
	 * 实发数量
	 */
	private Long actualQuantity;
	/**
	 * 领料单
	 */
	private ReverseFlushingMaterial reverseFlushingMaterial;

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getRequisitionCount() {
		return requisitionCount;
	}

	public void setRequisitionCount(Long requisitionCount) {
		this.requisitionCount = requisitionCount;
	}

	public Long getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Long actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	public ReverseFlushingMaterial getReverseFlushingMaterial() {
		return reverseFlushingMaterial;
	}

	public void setReverseFlushingMaterial(ReverseFlushingMaterial reverseFlushingMaterial) {
		this.reverseFlushingMaterial = reverseFlushingMaterial;
	}

}