package com.vix.crm.business.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 优惠券明细 com.vix.crm.business.entity.CouponDetail
 *
 * @author zhanghaibing
 *
 * @date 2015年1月27日
 */
public class CouponDetail extends BaseEntity {

	/**
	 * 状态 1 已领取 2 未领取 status;
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 面额
	 */
	private Double amount;
	/**
	 * 消费额
	 */
	private Double expenditure;
	/**
	 * 生效时间
	 */
	private Date effectiveDate;
	/**
	 * 截止时间
	 */
	private Date cutOffDate;
	/**
	 * 优惠券
	 */
	private Coupon coupon;
	/**
	 * 优惠券ID
	 */
	private String couponId;
	/**
	 * 优惠券类别
	 */
	private String type;
	/**
	 * 使用限制
	 */
	private String userule;
	/**
	 * 会员
	 */
	private CustomerAccount customerAccount;
	/** 是否被使用0标示没有被使用，1：已使用且有效，2：已作废 */
	private String isUsed;
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Double expenditure) {
		this.expenditure = expenditure;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getCutOffDate() {
		return cutOffDate;
	}

	public void setCutOffDate(Date cutOffDate) {
		this.cutOffDate = cutOffDate;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	/**
	 * @return the couponId
	 */
	public String getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserule() {
		return userule;
	}

	public void setUserule(String userule) {
		this.userule = userule;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getCustomerName(){
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
	}
	public String getEffectiveDateStr(){
		if(effectiveDate != null){
			return DateUtil.format(effectiveDate);
		}
		return "";
	}
	public String getCutOffDateStr(){
		if(cutOffDate != null){
			return DateUtil.format(cutOffDate);
		}
		return "";
	}
	public String getCouponName(){
		if(coupon != null){
			return coupon.getName();
		}
		return "";
	}
}
