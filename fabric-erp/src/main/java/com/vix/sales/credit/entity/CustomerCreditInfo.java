package com.vix.sales.credit.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 客户信用状况 */
public class CustomerCreditInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 信用额度 */
	private Double creditAmount;
	/** 信用经理编码 */
	private String creditManagerCode;
	/** 信用经理名称 */
	private String creditManagerName;
	/** 已使用信息额度 */
	private Double usedCreditAmount;
	/** 是否信用控制 */
	private String isCreditControl;
	/** 风险类别 */
	private String riskType;	 
	/** 信用冻结 */
	private String isFreezeCredit;	 
	/** 单据最大值 */
	private Double maxOfBill;
	/** 允许最长逾期日期 */
	private String maxTimeOfArrears;	 
	/** 拖欠等级 */
	private String arrearsLevel;	 
	/** 允许最高拖欠登记 */
	private String arrearsMaxLevel;	 
	/** DSO(应收帐款余额与三个月内日均销售额的比率,指越小越好) */
	private Double dso;
	/** 信用状态 */
	private String status;
	/** 最近信用检查人 */
	private Employee creditChecker;
	/** 最近信用检查时间 */
	private Date creditCheckTime;
	/** 信用等级 */
	private String creditLevel;
	/** 信用截止日期 */
	private Date expirationDate;
	/** 本年度开发票金额 */
	private Double currentYearBillAmount;
	/** 上年度开发票金额 */
	private Double beforeYearBillAmount;
 
	public CustomerCreditInfo(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getCreditManagerCode() {
		return creditManagerCode;
	}

	public void setCreditManagerCode(String creditManagerCode) {
		this.creditManagerCode = creditManagerCode;
	}

	public String getCreditManagerName() {
		return creditManagerName;
	}

	public void setCreditManagerName(String creditManagerName) {
		this.creditManagerName = creditManagerName;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
 

	public Double getUsedCreditAmount() {
		return usedCreditAmount;
	}

	public void setUsedCreditAmount(Double usedCreditAmount) {
		this.usedCreditAmount = usedCreditAmount;
	}

	public String getIsCreditControl() {
		return isCreditControl;
	}

	public void setIsCreditControl(String isCreditControl) {
		this.isCreditControl = isCreditControl;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getIsFreezeCredit() {
		return isFreezeCredit;
	}

	public void setIsFreezeCredit(String isFreezeCredit) {
		this.isFreezeCredit = isFreezeCredit;
	}

	public Double getMaxOfBill() {
		return maxOfBill;
	}

	public void setMaxOfBill(Double maxOfBill) {
		this.maxOfBill = maxOfBill;
	}

	public String getMaxTimeOfArrears() {
		return maxTimeOfArrears;
	}

	public void setMaxTimeOfArrears(String maxTimeOfArrears) {
		this.maxTimeOfArrears = maxTimeOfArrears;
	}

	public String getArrearsLevel() {
		return arrearsLevel;
	}

	public void setArrearsLevel(String arrearsLevel) {
		this.arrearsLevel = arrearsLevel;
	}

	public String getArrearsMaxLevel() {
		return arrearsMaxLevel;
	}

	public void setArrearsMaxLevel(String arrearsMaxLevel) {
		this.arrearsMaxLevel = arrearsMaxLevel;
	}

	public Double getDso() {
		return dso;
	}

	public void setDso(Double dso) {
		this.dso = dso;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getCreditChecker() {
		return creditChecker;
	}

	public void setCreditChecker(Employee creditChecker) {
		this.creditChecker = creditChecker;
	}

	public Date getCreditCheckTime() {
		return creditCheckTime;
	}

	public void setCreditCheckTime(Date creditCheckTime) {
		this.creditCheckTime = creditCheckTime;
	}

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Double getCurrentYearBillAmount() {
		return currentYearBillAmount;
	}

	public void setCurrentYearBillAmount(Double currentYearBillAmount) {
		this.currentYearBillAmount = currentYearBillAmount;
	}

	public Double getBeforeYearBillAmount() {
		return beforeYearBillAmount;
	}

	public void setBeforeYearBillAmount(Double beforeYearBillAmount) {
		this.beforeYearBillAmount = beforeYearBillAmount;
	}
}
