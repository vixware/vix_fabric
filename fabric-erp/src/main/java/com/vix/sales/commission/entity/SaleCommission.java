package com.vix.sales.commission.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

public class SaleCommission extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 销售员 */
	private Employee salesMan;
	/** 订单编码 */
	private String orderCode;
	/** 订单金额 */
	private Double orderAmount;
	/** 订单时间 */
	private Date orderTime;
	/** 佣金 */
	private Double commission;
	/** 佣金比例 */
	private Double commissionRate;
	
	public SaleCommission(){}

	public Employee getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(Employee salesMan) {
		this.salesMan = salesMan;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}
}
