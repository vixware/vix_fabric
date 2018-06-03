package com.vix.mdm.crm.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.business.entity.Coupon;
import com.vix.mdm.item.entity.Item;
/**
 * 会员卡
 * @author jackie
 *
 */
public class CustomerAccountClipDetail extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 会员信息*/
	private CustomerAccount customerAccount;
	/** 会员卡类型*/
	private String type;
	/** 优惠券*/
	private Set<Coupon> coupons;
	/** 会员有效期限*/
	private String expiryDate;
	/** 会员卡余额*/
	private Double money;
	/** 会员卡余次*/
	private Long number;
	/** 会员卡积分*/
	private Long pointValue;
	/**
	 *  是否启用: Y,启用  N,禁用
	 */
	private String isUse;
	/**
	 * 是否挂失:Y,是 	N,否
	 */
	private String isReport;
	/**
	 * 会员卡
	 */
	private CustomerAccountClip customerAccountClip;
	/**
	 * 服务项目
	 */
	private Item item;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
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
	public Long getCustomerPointValue(){
		if(customerAccount != null){
			return customerAccount.getPointValue();
		}
		return 0L;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getPointValue() {
		return pointValue;
	}

	public void setPointValue(Long pointValue) {
		this.pointValue = pointValue;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}

	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	public String getItemServiceContent(){
		if(item != null){
			return item.getServiceContent();
		}
		return "";
	}
	public String getItemName(){
		if(item != null){
			return item.getName();
		}
		return "";
	}
}
