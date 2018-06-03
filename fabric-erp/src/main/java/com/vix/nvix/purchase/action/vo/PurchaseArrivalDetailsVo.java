/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** PurchaseArrivalDetailsVo 采购智能分析>到货明细:到货明细表 **/
public class PurchaseArrivalDetailsVo {
	private String establishDate;//创建时间
	private String orderCode;
	private String supplierName;
	private String purchasePerson;
	private String itemName;
	private String itemCode;
	private String amount;
	private String unit;
	private String price;
	private String deliveryDate;//交货日期
	public String getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getPurchasePerson() {
		return purchasePerson;
	}
	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(price)  ){
			price = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   price  ));
		}
		this.price = price;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Override
	public String toString() {
		return "PurchaseArrivalDetailsVo [establishDate=" + establishDate + ", orderCode=" + orderCode
				+ ", supplierName=" + supplierName + ", purchasePerson=" + purchasePerson + ", itemName=" + itemName
				+ ", itemCode=" + itemCode + ", amount=" + amount + ", unit=" + unit + ", price=" + price
				+ ", deliveryDate=" + deliveryDate + "]";
	}
}