package com.vix.crm.business.vo;

/**
 * 
 * @author Think 客户层次分析
 *
 */
public class CustomerAnalysis {
	/**
	 * 客户数量
	 */
	private Double customerNumber;
	/**
	 * 成交金额
	 */
	private Double amount;
	/**
	 * 人均成交额
	 */
	private Double averageAmount;
	/**
	 * 重复购买人数
	 */
	private Double repeatBuyNumber;
	/**
	 * 重复购买金额
	 */
	private Double repeatBuyAmount;
	/**
	 * 重复购买率
	 */
	private Double repeatBuyRate;

	public Double getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Double customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAverageAmount() {
		return averageAmount;
	}

	public void setAverageAmount(Double averageAmount) {
		this.averageAmount = averageAmount;
	}

	public Double getRepeatBuyNumber() {
		return repeatBuyNumber;
	}

	public void setRepeatBuyNumber(Double repeatBuyNumber) {
		this.repeatBuyNumber = repeatBuyNumber;
	}

	public Double getRepeatBuyAmount() {
		return repeatBuyAmount;
	}

	public void setRepeatBuyAmount(Double repeatBuyAmount) {
		this.repeatBuyAmount = repeatBuyAmount;
	}

	public Double getRepeatBuyRate() {
		return repeatBuyRate;
	}

	public void setRepeatBuyRate(Double repeatBuyRate) {
		this.repeatBuyRate = repeatBuyRate;
	}

}
