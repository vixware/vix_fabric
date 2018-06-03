package com.vix.drp.collectManagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 收款记录
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-14
 */

public class CollectBill extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6372110282661495527L;
	/**
	 * 应收
	 */
	private Double receivable;
	/**
	 * 实付
	 */
	private Double payment;
	/**
	 * 付款时间
	 */
	private Date paymentTime;
	/**
	 * 客户
	 */
	private CustomerAccount customerAccount;

	/**
	 * 销售订单
	 */
	private SalesOrder salesOrder;
	/**
	 * 币种
	 */
	private CurrencyType currencyType;

	public Double getReceivable() {
		return receivable;
	}

	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	/**
	 * @return the currencyType
	 */
	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType
	 *            the currencyType to set
	 */
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

}
