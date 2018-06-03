package com.vix.mdm.purchase.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.NumberUtil;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 采购订单明细
 * 
 * @author Ivan 2013-05-21
 */
public class PurchaseOrderLineItem extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String barCode;
	private String skuCode;
	private String groupCode;

	/** 行编码(ID) */
	private String rowID;
	/** 行类型 */
	private String rowTyoe;
	/** 采购订单编号 */
	private String poCode;
	/** 物料编码id */
	private String itemId;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 物料类型 */
	private String itemType;
	/** 规格型号 */
	private String specification;
	/** 辅计算量 */
	private String assitAmount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange;
	/** 税率 */
	private Double taxRate;
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
	/** 金额(净单价 × 数量) */
	private Double netTotal;
	/** 价税合计金额 */
	private Double total;
	/** 累计入库数量 */
	private Double sumInventoryAmount;
	/** 建议供应商 */
	private String supplier;
	/** 需求日期 */
	private Date requireTime;
	/** 项目 */
	private String project;
	/** 仓库 */
	private String wareHouse;
	/** 收货仓库 */
	private String recieveWareHouseId;
	private String recieveWareHouse;
	/** 仓库组织 */
	private String wareHouseOrg;
	/** 收货地址代码 */
	private String recieveAddressCode;
	/** 收货地址 */
	private String recieveAddress;
	/** 允许迟到天数 */
	private Double allowLateDays;
	/** 允许早到天数 */
	private Double allowEarlyDays;
	/** 迟到和早到处理方法 */
	private String eolDealMethods;
	/** 允许到货偏差 */
	private Double deviation;
	/** 偏差处理方法 */
	private String deviationDeal;
	/** 到货数量 */
	private Double arrivalAmount;
	/** 是否允许替换物料 */
	private String isUseReplace;
	/** 替换物料标识ID */
	private String replaceItemCode;
	/** 替换物料名称 */
	private String replaceItemName;
	/** 采购订单 */
	private PurchaseOrder purchaseOrder;
	/** 计划单编码 */
	private String ppCode;
	/** 计划单明细编码 */
	private String ppItemsCode;
	/** 申请单编码 */
	private String paCode;
	/** 申请单明细编码 */
	private String paItemsCode;
	/** 询价单编码 */
	private String piCode;
	/** 询价单明细编码 */
	private String piItemsCode;
	/** 数量 */
	private Double amount;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;

	public Double getTatalFee() {
		Double inTax = this.taxRate;
		if (inTax == null)
			inTax = 0d;
		else
			inTax = (inTax / 100);

		inTax += 1d;
		if (price != null && amount != null) {
			total = price * amount * inTax;
			total = NumberUtil.round(total, 2, BigDecimal.ROUND_HALF_UP);
		}
		return total;
	}

	public String getChannelDistributorName() {
		if (purchaseOrder.getChannelDistributor() != null) {
			return purchaseOrder.getChannelDistributor().getName();
		}
		return "";
	}

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

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
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

	public String getAssitAmount() {
		return assitAmount;
	}

	public void setAssitAmount(String assitAmount) {
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

	/*public Optional<Double> getNetPrice() {
		return  Optional.ofNullable(netPrice);
	}*/
	
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

	public Double getSumInventoryAmount() {
		return sumInventoryAmount;
	}

	public void setSumInventoryAmount(Double sumInventoryAmount) {
		this.sumInventoryAmount = sumInventoryAmount;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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

	public String getRecieveAddressCode() {
		return recieveAddressCode;
	}

	public void setRecieveAddressCode(String recieveAddressCode) {
		this.recieveAddressCode = recieveAddressCode;
	}

	public String getRecieveAddress() {
		return recieveAddress;
	}

	public void setRecieveAddress(String recieveAddress) {
		this.recieveAddress = recieveAddress;
	}

	public Double getAllowLateDays() {
		return allowLateDays;
	}

	public void setAllowLateDays(Double allowLateDays) {
		this.allowLateDays = allowLateDays;
	}

	public Double getAllowEarlyDays() {
		return allowEarlyDays;
	}

	public void setAllowEarlyDays(Double allowEarlyDays) {
		this.allowEarlyDays = allowEarlyDays;
	}

	public String getEolDealMethods() {
		return eolDealMethods;
	}

	public void setEolDealMethods(String eolDealMethods) {
		this.eolDealMethods = eolDealMethods;
	}

	public Double getDeviation() {
		return deviation;
	}

	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	public String getDeviationDeal() {
		return deviationDeal;
	}

	public void setDeviationDeal(String deviationDeal) {
		this.deviationDeal = deviationDeal;
	}

	public Double getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(Double arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public String getIsUseReplace() {
		return isUseReplace;
	}

	public void setIsUseReplace(String isUseReplace) {
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

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getPpCode() {
		return ppCode;
	}

	public void setPpCode(String ppCode) {
		this.ppCode = ppCode;
	}

	public String getPpItemsCode() {
		return ppItemsCode;
	}

	public void setPpItemsCode(String ppItemsCode) {
		this.ppItemsCode = ppItemsCode;
	}

	public String getPaCode() {
		return paCode;
	}

	public void setPaCode(String paCode) {
		this.paCode = paCode;
	}

	public String getPaItemsCode() {
		return paItemsCode;
	}

	public void setPaItemsCode(String paItemsCode) {
		this.paItemsCode = paItemsCode;
	}

	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}

	public String getPiItemsCode() {
		return piItemsCode;
	}

	public void setPiItemsCode(String piItemsCode) {
		this.piItemsCode = piItemsCode;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getRequireTime() {
		return requireTime;
	}

	public void setRequireTime(Date requireTime) {
		this.requireTime = requireTime;
	}

	public String getRecieveWareHouseId() {
		return recieveWareHouseId;
	}

	public void setRecieveWareHouseId(String recieveWareHouseId) {
		this.recieveWareHouseId = recieveWareHouseId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

}
