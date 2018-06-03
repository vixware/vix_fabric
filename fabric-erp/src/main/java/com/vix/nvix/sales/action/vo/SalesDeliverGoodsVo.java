/** 为导出excel表建立相应的类,携带各列数据 **/
package com.vix.nvix.sales.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** 销售智能分析 > 发货明细表 > 发货明细表   **/
public class SalesDeliverGoodsVo {
	private String date;//发货时间
	private String orderCode;//订单号
	private String consigneeName;//收货人
	private String consigneeCity;//收货城市
	private String consigneeTel;//收货人电话
	private String productCode;//产品编号
	private String productName;//产品
	private String productNum;//产品数量
	private String productPrice;//产品单价
	private String receiverState;//状态
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
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneeCity() {
		return consigneeCity;
	}
	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
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
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	@Override
	public String toString() {
		return "SalesDeliverGoodsVo [date=" + date + ", orderCode=" + orderCode + ", consigneeName=" + consigneeName
				+ ", consigneeCity=" + consigneeCity + ", consigneeTel=" + consigneeTel + ", productCode=" + productCode
				+ ", productName=" + productName + ", productNum=" + productNum + ", productPrice=" + productPrice
				+ ", receiverState=" + receiverState + "]";
	}
}