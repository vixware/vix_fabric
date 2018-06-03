package com.vix.inventory.standingbook.entity;

import java.util.Date;

/**
 * 
 * com.vix.inventory.standingbook.entity.InventoryCurrentStockView
 *
 * @author zhanghaibing
 *
 * @date 2014年10月8日
 */

public class InventoryCurrentStockView {
	private String id;

	/** 承租户标识 */
	private String tenantId;
	/** 公司标识 */
	private String companyCode;

	private String companyInnerCode;

	private String parentId;

	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 物料名称
	 */
	private String itemname;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 结存数量
	 */
	private Double quantity;
	/**
	 * 可用数量
	 */
	private Double avaquantity;
	/**
	 * 仓库
	 */
	private String warehouse;
	private String batchcode;
	/**
	 * 货位
	 */
	private String invShelfName;
	/**
	 * 到期日
	 */
	private Date massunitEndTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAvaquantity() {
		return avaquantity;
	}

	public void setAvaquantity(Double avaquantity) {
		this.avaquantity = avaquantity;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getInvShelfName() {
		return invShelfName;
	}

	public void setInvShelfName(String invShelfName) {
		this.invShelfName = invShelfName;
	}

	public Date getMassunitEndTime() {
		return massunitEndTime;
	}

	public void setMassunitEndTime(Date massunitEndTime) {
		this.massunitEndTime = massunitEndTime;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

}