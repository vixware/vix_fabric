package com.vix.mdm.purchase.order.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 支付记录
 * 
 * @类全名 com.vix.nvix.common.entity.ParkingCarPaymentRecord
 *
 * @author zhanghaibing
 *
 * @date 2017年7月29日
 */
public class PaymentRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paymentType;// 支付方式 1,现金;2,余额;3,银行卡;4,微信;5,支付宝;

	private Double amount;// 金额

	private CustomerAccount customerAccount;// 用户

	private RequireGoodsOrder requireGoodsOrder;// 订单

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

}