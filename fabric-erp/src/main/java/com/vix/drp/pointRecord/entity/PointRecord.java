/**
 * 
 */
package com.vix.drp.pointRecord.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;

/**
 * 积分明细 com.vix.drp.pointRecord.entity.PointRecord
 *
 * @author zhanghaibing
 *
 * @date 2015年1月29日
 */
public class PointRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 积分来源
	 */
	private String pointSource;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 积分类型:1,增加.2,减少
	 */
	private String pointType;
	/**
	 * 积分值
	 */
	private Double pointValue;
	/**
	 * 会员信息
	 */
	private CustomerAccount customerAccount;
	/**
	 * 订单信息
	 */
	private RequireGoodsOrder requireGoodsOrder;
	/**
	 * 充值记录
	 */
	private RechargeRecord rechargeRecord;
	public String getPointSource() {
		return pointSource;
	}

	public void setPointSource(String pointSource) {
		this.pointSource = pointSource;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public Double getPointValue() {
		return pointValue;
	}

	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerName(){
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
	}
	public String getCustomerPhone(){
		if(customerAccount != null){
			return customerAccount.getMobilePhone();
		}
		return "";
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}

	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
}
