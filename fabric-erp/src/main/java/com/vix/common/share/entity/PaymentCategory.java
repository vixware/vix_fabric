package com.vix.common.share.entity;


/**
 * 付款方式
 * @author Administrator
 *
 */
public class PaymentCategory extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 付款方式编码 */
	private String paymentCode;
	/** 付款方式 */
	private String payment;

	public PaymentCategory(){}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
}