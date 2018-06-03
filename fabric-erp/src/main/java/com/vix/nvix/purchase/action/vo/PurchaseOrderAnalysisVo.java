/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/**  采购智能分析>采购统计仪表盘:采购订单分析列表 **/
public class PurchaseOrderAnalysisVo {
	private String supplierName;//供应商
	private String orderNum;//供应商订单数
	private String orderMoney;//供应商总金额
	private String purchaser;//最近采购人
	private String purchaserDate;//最近采购时间
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		/** 如果不为空就保留2位小数,科学计数时,返回数字 ***/
		if (StringUtils.isNotEmpty(orderMoney)  ){
			orderMoney = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   orderMoney  ));
		}
		this.orderMoney = orderMoney;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	public String getPurchaserDate() {
		return purchaserDate;
	}
	public void setPurchaserDate(String purchaserDate) {
		this.purchaserDate = purchaserDate;
	}
	@Override
	public String toString() {
		return "PurchaseOrderAnalysisVo [supplierName=" + supplierName + ", orderNum=" + orderNum + ", orderMoney="
				+ orderMoney + ", purchaser=" + purchaser + ", purchaserDate=" + purchaserDate + "]";
	}
}