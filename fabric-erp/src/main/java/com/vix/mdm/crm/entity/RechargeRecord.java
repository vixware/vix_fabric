package com.vix.mdm.crm.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 充值记录
 * 
 * @author jackie
 *
 */
public class RechargeRecord extends BaseEntity {

	/**
	 *  status 0,未付款 1,已付款
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会员卡
	 */
	private CustomerAccountClip customerAccountClip;
	/**
	 * 充值金额
	 */
	private Double payMoney;
	/**
	 * 支付方式 1,现金 3,银行卡 4,微信 5,支付宝
	 */
	private String payType;
	/**
	 * 充值日期
	 */
	private Date payDate;
	/**
	 * 充值规则
	 */
	private StoredValueRuleSet storedValueRuleSet;
	/**
	 * 支付二维码地址
	 */
	private String qrCodePath;
	/**
	 * 店铺
	 */
	private ChannelDistributor channelDistributor;
	
	private CustomerAccount customerAccount;
	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}

	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Date getPayDate() {
		return payDate;
	}

	public String getPayDateStr() {
		if (payDate != null) {
			return DateUtil.format(payDate);
		}
		return "";
	}
	public String getPayDateTimeStr() {
		if (payDate != null) {
			return DateUtil.formatTime(payDate);
		}
		return "";
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public StoredValueRuleSet getStoredValueRuleSet() {
		return storedValueRuleSet;
	}

	public void setStoredValueRuleSet(StoredValueRuleSet storedValueRuleSet) {
		this.storedValueRuleSet = storedValueRuleSet;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
	
	public String getGiftAmount(){
		if(storedValueRuleSet != null){
			if(storedValueRuleSet.getGiftAmount() != null){
				return String.valueOf(storedValueRuleSet.getGiftAmount());
			}else{
				return "0";
			}
		}
		return "";
	}
	public String getChannelDistributorName(){
		if(null != channelDistributor){
			return channelDistributor.getName();
		}
		return "";
	}
	
	public String getCustomerName(){
		CustomerAccount customerAccount = getCustomerAccount();
		if(null != customerAccount){
			 return customerAccount.getName();
		}
		return "";
	}
	public String getCustomerMobilePhone(){
		CustomerAccount customerAccount = getCustomerAccount();
		if(null != customerAccount){
			return customerAccount.getMobilePhone();
		}
		return "";
	}
	/** 获得会员卡类型 **/
	public String getCustomerClipType(){
		if(null != customerAccountClip){
			CardEntity card = customerAccountClip.getCard();
			if(null != card){
				return card.getType();
			}
		}
		return "";
	}
	/** 获得会员卡号 **/
	public String getCustomerClipNum(){
		if(null != customerAccountClip){
			return  customerAccountClip.getName();
		}
		return "";
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
}
