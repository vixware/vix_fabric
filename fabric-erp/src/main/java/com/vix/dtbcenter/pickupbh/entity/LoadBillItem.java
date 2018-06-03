/**
 * 
 */
package com.vix.dtbcenter.pickupbh.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 提货单明细
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-1
 */
public class LoadBillItem extends BaseBOEntity {
	private static final long serialVersionUID = -2557143076519477046L;
	/** 行编码(ID) */
	private String rowID;
	/** 行类型 */
	private String rowTyoe;
	/** 提货单编号 */
	private String loadBillCode;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 销售数量 */
	private Double amount;
	/** 辅计算量 */
	private Double assitAmount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange;
	/** 税率 */
	private Double tax;
	/** 税额 */
	private Double taxAmount;
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 净单价 */
	private Double netPrice;
	/** 含税单价 */
	private Double taxPrice;
	/** 金额(净单价*数量) */
	private Double netTotal;
	/** 价税合计金额 */
	private Double taxTotal;
	/** 累计出库数量 */
	private Double sumInventoryAmount;
	/** 需求日期 */
	private Date requireDate;
	/** 项目 */
	private String project;
	/** 仓库 */
	private String wareHouse;
	/** 收货仓库 */
	private String recieveWareHouse;
	/** 仓库组织 */
	private String wareHouseOrg;
	/** 收获地址 */
	private String recieveAddress;
	/** 是否允许替换物料 */
	private Integer isUseReplace;
	/** 替换物料标识ID */
	private String replaceItemCode;
	/** 替换物料名称 */
	private String replaceItemName;

	private LoadBill loadBill;

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getRowTyoe() {
		return rowTyoe;
	}

	public void setRowTyoe(String rowTyoe) {
		this.rowTyoe = rowTyoe;
	}

	public String getLoadBillCode() {
		return loadBillCode;
	}

	public void setLoadBillCode(String loadBillCode) {
		this.loadBillCode = loadBillCode;
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

	public Double getAssitAmount() {
		return assitAmount;
	}

	public void setAssitAmount(Double assitAmount) {
		this.assitAmount = assitAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAssitUnit() {
		return assitUnit;
	}

	public void setAssitUnit(String assitUnit) {
		this.assitUnit = assitUnit;
	}

	public Double getUnitExchange() {
		return unitExchange;
	}

	public void setUnitExchange(Double unitExchange) {
		this.unitExchange = unitExchange;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

	public Double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public Double getSumInventoryAmount() {
		return sumInventoryAmount;
	}

	public void setSumInventoryAmount(Double sumInventoryAmount) {
		this.sumInventoryAmount = sumInventoryAmount;
	}

	public Date getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getRecieveWareHouse() {
		return recieveWareHouse;
	}

	public void setRecieveWareHouse(String recieveWareHouse) {
		this.recieveWareHouse = recieveWareHouse;
	}

	public String getWareHouseOrg() {
		return wareHouseOrg;
	}

	public void setWareHouseOrg(String wareHouseOrg) {
		this.wareHouseOrg = wareHouseOrg;
	}

	public String getRecieveAddress() {
		return recieveAddress;
	}

	public void setRecieveAddress(String recieveAddress) {
		this.recieveAddress = recieveAddress;
	}

	public Integer getIsUseReplace() {
		return isUseReplace;
	}

	public void setIsUseReplace(Integer isUseReplace) {
		this.isUseReplace = isUseReplace;
	}

	public String getReplaceItemCode() {
		return replaceItemCode;
	}

	public void setReplaceItemCode(String replaceItemCode) {
		this.replaceItemCode = replaceItemCode;
	}

	public String getReplaceItemName() {
		return replaceItemName;
	}

	public void setReplaceItemName(String replaceItemName) {
		this.replaceItemName = replaceItemName;
	}

	public LoadBill getLoadBill() {
		return loadBill;
	}

	public void setLoadBill(LoadBill loadBill) {
		this.loadBill = loadBill;
	}

}
