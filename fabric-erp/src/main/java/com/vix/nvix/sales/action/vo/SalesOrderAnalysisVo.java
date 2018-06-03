/** 为导出excel表建立相应的类,携带各列数据 **/
package com.vix.nvix.sales.action.vo;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.properties.util.MyTool;

/** 销售智能分析 > 销售统计仪表盘 > 销售订单分析列表   **/
public class SalesOrderAnalysisVo {
	private String lineNumber;//列表的行编号
	private String customerName;//客户
	private String money;//金额
	private String latelyBuyTime;//最近购买时间
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getLatelyBuyTime() {
		return latelyBuyTime;
	}
	public void setLatelyBuyTime(String latelyBuyTime) {
		this.latelyBuyTime = latelyBuyTime;
	}
	@Override
	public String toString() {
		return "SalesOrderAnalysisVo [lineNumber=" + lineNumber + ", customerName=" + customerName + ", money=" + money
				+ ", latelyBuyTime=" + latelyBuyTime + "]";
	}
}