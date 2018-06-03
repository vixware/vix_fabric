package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 供应商指标
 * 
 * @author Ivan 2013-05-22
 */
public class SupplierIndicators extends BaseEntity {

	private static final long serialVersionUID = 741012497971880452L;
	/** 供应商编码 */
	private String supplierCode;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 权重 */
	private String weights;
	/** 配额数量 */
	private Double quota;
	/** 金额限度 */
	private Double priceLimit;
	/** 供应商 */
	private Supplier supplier;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

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

	public String getWeights() {
		return weights;
	}

	public void setWeights(String weights) {
		this.weights = weights;
	}

	public Double getQuota() {
		return quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

	public Double getPriceLimit() {
		return priceLimit;
	}

	public void setPriceLimit(Double priceLimit) {
		this.priceLimit = priceLimit;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
