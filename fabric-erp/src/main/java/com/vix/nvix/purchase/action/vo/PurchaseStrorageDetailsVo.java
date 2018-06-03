/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** PurchaseStrorageDetailsVo 采购智能分析>入库明细:入库明细表 **/
public class PurchaseStrorageDetailsVo {
	private String date;//入库时间
	private String orderCode;
	private String supplierName;  
	private String warehouse;//仓库  
	private String location;//货位
	private String agentPerson;//经办人
	private String itemCode;
	private String itemName;
	private String amount;
	private String unit;
	private String price;//单价
	private String totalPrice;//金额,合计
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAgentPerson() {
		return agentPerson;
	}
	public void setAgentPerson(String agentPerson) {
		this.agentPerson = agentPerson;
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
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(totalPrice)  ){
			totalPrice = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   totalPrice  ));
		}
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "PurchaseStrorageDetailsVo [date=" + date + ", orderCode=" + orderCode + ", supplierName=" + supplierName
				+ ", warehouse=" + warehouse + ", location=" + location + ", agentPerson=" + agentPerson + ", itemCode="
				+ itemCode + ", itemName=" + itemName + ", amount=" + amount + ", unit=" + unit + ", price=" + price
				+ ", totalPrice=" + totalPrice + "]";
	}
}