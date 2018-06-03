/** 为导出excel表建立响应的类,携带各列数据 **/
package com.vix.nvix.purchase.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** 采购智能分析 > 采购成本分析 > 供应商采购成本分析列表     &&  导出采购智能分析 > 类型结构分析 > 产品类别采购分析列表 也用这个vo  && 销售智能分析 > 销售结构分析 产品类别销售分析列表  **/
public class PurchaseSupplierCostVo {
	private String lineNumber;//列表的行编号也是top几   
	private String supplierName;//供应商  &&  产品类别采购分析列表 的 物料类别  &&  产品类别
	private String money;//采购金额  && 销售金额
	private String proportion;//占比
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
		return "PurchaseSupplierCostVo [lineNumber=" + lineNumber + ", supplierName=" + supplierName + ", money="
				+ money + ", proportion=" + proportion + "]";
	}
}