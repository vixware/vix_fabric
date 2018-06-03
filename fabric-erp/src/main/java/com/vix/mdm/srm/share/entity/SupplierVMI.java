package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

public class SupplierVMI extends BaseEntity {

	private static final long serialVersionUID = 7158863092168198555L;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 规格型号 */
	private String specification;
	/** 物料类型 */
	private String itemType;
	/** 主计量单位 */
	private String masterUnit;
	/** 库存数量 */
	private Double stockNumber;
	/** 所属仓库 */
	private String warehouse;
	/** 所属货位 */
	private String cargoSpace;
	/** 库存数量上限 */
	private Double stockNumberMax;
	/** 库存数量下限 */
	private Double stockNumberMin;
	/** 统计人 */
	private String statisticians;
	/** 状态描述 */
	private String statusDescription;
	/** 供应商 */
	private Supplier supplier;

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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(String masterUnit) {
		this.masterUnit = masterUnit;
	}

	public Double getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Double stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getCargoSpace() {
		return cargoSpace;
	}

	public void setCargoSpace(String cargoSpace) {
		this.cargoSpace = cargoSpace;
	}

	public Double getStockNumberMax() {
		return stockNumberMax;
	}

	public void setStockNumberMax(Double stockNumberMax) {
		this.stockNumberMax = stockNumberMax;
	}

	public Double getStockNumberMin() {
		return stockNumberMin;
	}

	public void setStockNumberMin(Double stockNumberMin) {
		this.stockNumberMin = stockNumberMin;
	}

	public String getStatisticians() {
		return statisticians;
	}

	public void setStatisticians(String statisticians) {
		this.statisticians = statisticians;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
