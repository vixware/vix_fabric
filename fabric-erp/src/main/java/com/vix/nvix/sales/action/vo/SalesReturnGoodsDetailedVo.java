/** 为导出excel表建立相应的类,携带各列数据 **/
package com.vix.nvix.sales.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** 销售智能分析 > 退货订单统计表 > 退货明细表   **/
public class SalesReturnGoodsDetailedVo {
	private String returnAppTime;//申请时间
	private String returnTime;//退货时间
	private String returnOrderCode;//退货订单编号
	private String customer;//客户
	private String productCode;//产品编码
	private String productName;//产品
	private String productNum;//产品数量
	private String productPrice;//产品单价
	private String productUnit;//产品单位
	private String state;//状态
	public String getReturnAppTime() {
		return returnAppTime;
	}
	public void setReturnAppTime(String returnAppTime) {
		this.returnAppTime = returnAppTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getReturnOrderCode() {
		return returnOrderCode;
	}
	public void setReturnOrderCode(String returnOrderCode) {
		this.returnOrderCode = returnOrderCode;
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
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "SalesReturnGoodsDetailedVo [returnAppTime=" + returnAppTime + ", returnTime=" + returnTime
				+ ", returnOrderCode=" + returnOrderCode + ", customer=" + customer + ", productCode=" + productCode
				+ ", productName=" + productName + ", productNum=" + productNum + ", productPrice=" + productPrice
				+ ", productUnit=" + productUnit + ", state=" + state + "]";
	}
	
}