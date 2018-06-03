/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** 采购智能分析 > 采购成本分析 > 物料采购成本分析列表  **/
public class PurchaseMaterielCostVo {
	private String lineNumber;//列表的行编号也是top几
	private String itemName;//物料名称
	private String itemCode;
	private String amount;//数量
	private String unit;
	private String price;//单价
	private String money;//采购金额
	private String proportion;//占比
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
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
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(money)  ){
			money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   money  ));
		}
		this.money = money;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	@Override
	public String toString() {
		return "PurchaseMaterielCostVo [lineNumber=" + lineNumber + ", itemName=" + itemName + ", itemCode=" + itemCode
				+ ", amount=" + amount + ", unit=" + unit + ", price=" + price + ", money=" + money + ", proportion="
				+ proportion + "]";
	}
}