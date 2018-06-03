package com.vix.crm.business.vo;

/**
 * 
 * @author Think 客户层次分析
 *
 */
public class SalesAnalysis {
	/**
	 * 成交金额
	 */
	private Double amount;
	/**
	 * 成交订单数
	 */
	private Double orderNum;
	/**
	 * 订单均价
	 */
	private Double orderPrice;

	/**
	 * 平均订单件数
	 * 
	 * @return
	 */
	private Double orderAvgNum;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Double orderNum) {
		this.orderNum = orderNum;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Double getOrderAvgNum() {
		return orderAvgNum;
	}

	public void setOrderAvgNum(Double orderAvgNum) {
		this.orderAvgNum = orderAvgNum;
	}

}
