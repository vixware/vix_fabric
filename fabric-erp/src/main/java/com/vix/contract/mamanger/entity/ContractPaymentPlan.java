/**   
* @Title: ContractPaymentPlan.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午1:32:39  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractPaymentPlan
 * @Description:合同收付款计划 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午1:32:39
 */
public class ContractPaymentPlan extends BaseEntity {

	private static final long serialVersionUID = 2827273089830940586L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/**
	 * 合同编码
	 */
	private String contractCode;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 收款金额
	 */
	private Double amount;
	/**
	 * 付款类型
	 */
	private String type;
	/**
	 * 收款人
	 */
	private String aprPerson;
	/**
	 * 收款时间
	 */
	private Date aprTime;
	/**
	 * 提醒天数
	 */
	private String calertDay;
	/**
	 * 是否提醒
	 */
	private String isAlert;
	
	/**
	 * 收款单位名称
	 */
	private String beneficiaryName;
	/**
	 * 收款单位账号
	 */
	private String beneficiaryAccount;
	/**
	 * 付款金额
	 */
	private Double paymentAmount;
	/**
	 * 收款时间
	 */
	private Date paymentTime;
	/**
	 * 收款人
	 */
	private String paymentPeople;
	
	public ContractPaymentPlan() {
		super();
	}
	
	public ContractPaymentPlan(String id) {
		super();
		setId(id);
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAprPerson() {
		return aprPerson;
	}

	public void setAprPerson(String aprPerson) {
		this.aprPerson = aprPerson;
	}

	public Date getAprTime() {
		return aprTime;
	}

	public void setAprTime(Date aprTime) {
		this.aprTime = aprTime;
	}

	public String getCalertDay() {
		return calertDay;
	}

	public void setCalertDay(String calertDay) {
		this.calertDay = calertDay;
	}

	public String getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(String isAlert) {
		this.isAlert = isAlert;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryAccount() {
		return beneficiaryAccount;
	}

	public void setBeneficiaryAccount(String beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentPeople() {
		return paymentPeople;
	}

	public void setPaymentPeople(String paymentPeople) {
		this.paymentPeople = paymentPeople;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}
	
}
