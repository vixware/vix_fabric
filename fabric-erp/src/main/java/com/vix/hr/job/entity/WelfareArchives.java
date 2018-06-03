package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 福利档案
 * @author 李大鹏
 */
public class WelfareArchives extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 人员编码 */
	private String employeeCode;
	/** 人员姓名 */
	private String employeeName;
	/** 会计年度 */
	private String accountingYear;
	/** 会计月份 */
	private String accountingMonth;
	/** 福利部门 */
	private String welfareDepartment;
	/** 职工个人账户 */
	private String individualAccount;
	/** 电脑序号 */
	private String computerNumber;
	/** 社会保障号 */
	private String socialSecurityNumber;
	/** 缴交基数 */
	private String payBases;
	/** 个人缴交比例 */
	private String individualPaymentRatios;
	/** 单位缴交比例 */
	private String proportionUnitsPay;
	/** 单位划转比例 */
	private String unitTransferRatios;
	/** 本单位开始缴费月份 */
	private String unitBeganPayingMonth;
	/** 开户日期 */
	private Date accountOpeningDate;
	/** 首次缴费日期 */
	private Date firstPaymentDate;
	/** 缴费截止日期 */
	private Date endPaymentDate;
	/** 视同缴费月 */
	private String paymentMonth;
	/** 实际缴费月 */
	private String actualPaymentMonth;
	/** 账户状态 */
	private String accountTypes;
	/** 增加原因 */
	private String increase;
	/** 减少原因 */
	private String reduceCauses;
	/** 是否转入账户 */
	private String whetherIntoAccount;
	/** 转入单位 */
	private String transferredUnit;
	/** 转入日期 */
	private Date transferredDate;
	/** 封存日期 */
	private Date sealDate;;
	/** 启封日期 */
	private Date opentingDate;
	/** 销户日期 */
	private Date closingDate;
	/** 转出日期 */
	private Date outDate;
	/** 转出单位 */
	private String outUnit;
	/** 人员类别 */
	private String employeeTypes;

	public String getWelfareDepartment() {
		return welfareDepartment;
	}

	public void setWelfareDepartment(String welfareDepartment) {
		this.welfareDepartment = welfareDepartment;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAccountingYear() {
		return accountingYear;
	}

	public void setAccountingYear(String accountingYear) {
		this.accountingYear = accountingYear;
	}

	public String getActualPaymentMonth() {
		return actualPaymentMonth;
	}

	public void setActualPaymentMonth(String actualPaymentMonth) {
		this.actualPaymentMonth = actualPaymentMonth;
	}

	public String getAccountingMonth() {
		return accountingMonth;
	}

	public void setAccountingMonth(String accountingMonth) {
		this.accountingMonth = accountingMonth;
	}

	public String getIndividualAccount() {
		return individualAccount;
	}

	public void setIndividualAccount(String individualAccount) {
		this.individualAccount = individualAccount;
	}

	public String getComputerNumber() {
		return computerNumber;
	}

	public void setComputerNumber(String computerNumber) {
		this.computerNumber = computerNumber;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getPayBases() {
		return payBases;
	}

	public void setPayBases(String payBases) {
		this.payBases = payBases;
	}

	public String getIndividualPaymentRatios() {
		return individualPaymentRatios;
	}

	public void setIndividualPaymentRatios(String individualPaymentRatios) {
		this.individualPaymentRatios = individualPaymentRatios;
	}

	public String getProportionUnitsPay() {
		return proportionUnitsPay;
	}

	public void setProportionUnitsPay(String proportionUnitsPay) {
		this.proportionUnitsPay = proportionUnitsPay;
	}

	public String getUnitTransferRatios() {
		return unitTransferRatios;
	}

	public void setUnitTransferRatios(String unitTransferRatios) {
		this.unitTransferRatios = unitTransferRatios;
	}

	public String getUnitBeganPayingMonth() {
		return unitBeganPayingMonth;
	}

	public void setUnitBeganPayingMonth(String unitBeganPayingMonth) {
		this.unitBeganPayingMonth = unitBeganPayingMonth;
	}

	public Date getAccountOpeningDate() {
		return accountOpeningDate;
	}

	public void setAccountOpeningDate(Date accountOpeningDate) {
		this.accountOpeningDate = accountOpeningDate;
	}

	public Date getFirstPaymentDate() {
		return firstPaymentDate;
	}

	public void setFirstPaymentDate(Date firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	public Date getEndPaymentDate() {
		return endPaymentDate;
	}

	public void setEndPaymentDate(Date endPaymentDate) {
		this.endPaymentDate = endPaymentDate;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public String getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(String accountTypes) {
		this.accountTypes = accountTypes;
	}

	public String getIncrease() {
		return increase;
	}

	public void setIncrease(String increase) {
		this.increase = increase;
	}

	public String getReduceCauses() {
		return reduceCauses;
	}

	public void setReduceCauses(String reduceCauses) {
		this.reduceCauses = reduceCauses;
	}

	public String getWhetherIntoAccount() {
		return whetherIntoAccount;
	}

	public void setWhetherIntoAccount(String whetherIntoAccount) {
		this.whetherIntoAccount = whetherIntoAccount;
	}

	public String getTransferredUnit() {
		return transferredUnit;
	}

	public void setTransferredUnit(String transferredUnit) {
		this.transferredUnit = transferredUnit;
	}

	public Date getTransferredDate() {
		return transferredDate;
	}

	public void setTransferredDate(Date transferredDate) {
		this.transferredDate = transferredDate;
	}

	public Date getSealDate() {
		return sealDate;
	}

	public void setSealDate(Date sealDate) {
		this.sealDate = sealDate;
	}

	public Date getOpentingDate() {
		return opentingDate;
	}

	public void setOpentingDate(Date opentingDate) {
		this.opentingDate = opentingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutUnit() {
		return outUnit;
	}

	public void setOutUnit(String outUnit) {
		this.outUnit = outUnit;
	}

	public String getEmployeeTypes() {
		return employeeTypes;
	}

	public void setEmployeeTypes(String employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

}
