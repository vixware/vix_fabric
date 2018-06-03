/** 为导出excel表建立相应的类,携带各列数据 **/
package com.vix.nvix.sales.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** 销售智能分析 > 销售明细表 > 销售明细表   **/
public class SalesDetailedVo {
	private String orderDate;//单据日期
	private String orderCode;//单据编码
	private String regionalName;//地域
	private String customer;//客户
	private String productCode;//产品编码
	private String productName;//产品
	private String productNum;//产品数量
	private String productPrice;//产品单价
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getRegionalName() {
		return regionalName;
	}
	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(productPrice)  ){
			productPrice = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   productPrice  ));
		}
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "SalesDetailedVo [orderDate=" + orderDate + ", orderCode=" + orderCode + ", regionalName=" + regionalName
				+ ", customer=" + customer + ", productCode=" + productCode + ", productName=" + productName
				+ ", productNum=" + productNum + ", productPrice=" + productPrice + "]";
	}
}