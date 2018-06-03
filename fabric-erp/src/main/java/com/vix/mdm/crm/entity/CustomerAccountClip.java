package com.vix.mdm.crm.entity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.business.entity.Coupon;

/**
 * 会员卡
 * 
 * @author jackie
 *
 */
public class CustomerAccountClip extends BaseEntity {
	private DecimalFormat    df   = new DecimalFormat("######0.00");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 会员信息 */
	private CustomerAccount customerAccount;
	/** 会员卡类型 */
	private String type;
	/** 优惠券 */
	private Set<Coupon> coupons;
	/** 会员有效期限 */
	private Date expiryDate;
	/** 会员卡余额 */
	private Double money;
	/** 会员卡余次 */
	private Long number;
	/** 会员卡积分 */
	private Double pointValue;
	/** 办卡金额 */
	private Double payMoney;
	/**
	 * 是否启用: Y,启用 N,禁用
	 */
	private String isUse;
	/**
	 * 是否挂失:Y,是 N,否
	 */
	private String isReport;
	/**
	 * 会员卡类型
	 */
	private CardEntity card;
	/**
	 * 会员卡明细
	 */
	private Set<CustomerAccountClipDetail> customerAccountClipDetails = new HashSet<CustomerAccountClipDetail>();

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

	public String getCustomerName() {
		if (customerAccount != null) {
			return customerAccount.getName();
		}
		return "";
	}

	public String getCustomerPhone() {
		if (customerAccount != null) {
			return customerAccount.getMobilePhone();
		}
		return "";
	}

	public Long getCustomerPointValue() {
		if (customerAccount != null) {
			return customerAccount.getPointValue();
		}
		return 0L;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public String getExpiryDateStr(){
		if(expiryDate != null){
			return DateUtil.format(expiryDate);
		}
		return "";
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getMoney() {
		return money;
	}
	public String getMoneyStr() {
		if(StringUtils.isNotEmpty(String.valueOf(money))&& money != null){
			return df.format(money);
		}
		return "";
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

	public Double getPointValue() {
		if(pointValue != null){
			return Math.floor(pointValue);
		}
		return 0D;
	}

	public void setPointValue(Double pointValue) {
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

	public Set<CustomerAccountClipDetail> getCustomerAccountClipDetails() {
		return customerAccountClipDetails;
	}

	public void setCustomerAccountClipDetails(Set<CustomerAccountClipDetail> customerAccountClipDetails) {
		this.customerAccountClipDetails = customerAccountClipDetails;
	}

	public CardEntity getCard() {
		return card;
	}

	public void setCard(CardEntity card) {
		this.card = card;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getCardType(){
		if(card != null){
			return card.getType();
		}
		return "";
	}
	/** 获取创建时间为yyyy-MM-dd HH:mm:ss **/
	public String getCreateTimeFormatA() {
		String dateString = "";
		try{
			Date createTime = this.getCreateTime();
			if(createTime != null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateString = formatter.format(createTime);
			}else{
				dateString = "";
			}
		}catch(Exception e){
			dateString = "";
		}
		return dateString;
	}
	/** 获取创建时间为yyyy-MM-dd **/
	public String getCreateTimeFormatB() {
		String dateString = "";
		try{
			Date createTime = this.getCreateTime();
			if(createTime != null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				dateString = formatter.format(createTime);
			}else{
				dateString = "";
			}
		}catch(Exception e){
			dateString = "";
		}
		return dateString;
	}
	/** 获取会员卡类型的相应名称 **/  
	public String getTypeNameStr(){
		if(type == null || "".equals(type)){
			return "";
		}else{
			if("1".equals(type)){
				return "余额卡";
			}else if("2".equals(type)){
				return "次卡";
			}else{
				return "";
			}
		}
	}
}
