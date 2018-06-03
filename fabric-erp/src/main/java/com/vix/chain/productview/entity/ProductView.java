package com.vix.chain.productview.entity;

/**
 * 
 * com.vix.chain.productview.entity.ProductView
 *
 * @author zhanghaibing
 *
 * @date   2014年10月28日
 */

public class ProductView {
	private String id;

	/** 承租户标识 */
	private String tenantId;
	/** 公司标识 */
	private String companyCode;

	private String companyInnerCode;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 单价 */
	private Double price;
	/** 销售数量 */
	private Double amount;
	/** 计量单位 */
	private String unit;
	/** 金额(净单价*数量) */
	private Double netTotal;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

}