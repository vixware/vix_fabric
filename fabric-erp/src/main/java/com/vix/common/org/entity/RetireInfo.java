package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人关系->离退情况
 * @author 李大鹏
 */
public class RetireInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 离退休类别 */
	private String retiredCategory;
	/** 职级序列 */
	private String rankSequence;
	/** 离退休日期 */
	private Date retirementDate;
	/** 离休金 */
	private String pensions;
	/** 退休金 */
	private String retirementPay;
	/** 退职生活费 */
	private String subsistenceAllowanceFor;
	/** 医疗费 */
	private String medicalFee;
	/** 补充医疗保险支付医疗费部分 */
	private String supplementaryMedicalInsuranceToPayMedicalExpensesPart;
	/** 生活补贴(企业) */
	private String livingAllowanceEnterprise;
	/** 生活补贴(行业) */
	private String livingAllowanceIndustry;
	/** 生活补贴(地方) */
	private String livingAllowanceLocal;
	/** 一次性补贴(地方) */
	private String aoneTimeSubsidyLocal;
	/** 一次性补贴(特殊工种) */
	private String aoneTimeSubsidySpecial;
	/** 一次性补贴(病退) */
	private String aoneTimeSubsidyIllnessRetreat;
	/** 应发总额 */
	private String shouldTheTotal;
	/** 备注 */
	private String remarks;

	/** 职员 */
	private Employee employee;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getRetiredCategory() {
		return retiredCategory;
	}

	public void setRetiredCategory(String retiredCategory) {
		this.retiredCategory = retiredCategory;
	}

	public String getRankSequence() {
		return rankSequence;
	}

	public void setRankSequence(String rankSequence) {
		this.rankSequence = rankSequence;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}

	public String getPensions() {
		return pensions;
	}

	public void setPensions(String pensions) {
		this.pensions = pensions;
	}

	public String getRetirementPay() {
		return retirementPay;
	}

	public void setRetirementPay(String retirementPay) {
		this.retirementPay = retirementPay;
	}

	public String getSubsistenceAllowanceFor() {
		return subsistenceAllowanceFor;
	}

	public void setSubsistenceAllowanceFor(String subsistenceAllowanceFor) {
		this.subsistenceAllowanceFor = subsistenceAllowanceFor;
	}

	public String getMedicalFee() {
		return medicalFee;
	}

	public void setMedicalFee(String medicalFee) {
		this.medicalFee = medicalFee;
	}

	public String getSupplementaryMedicalInsuranceToPayMedicalExpensesPart() {
		return supplementaryMedicalInsuranceToPayMedicalExpensesPart;
	}

	public void setSupplementaryMedicalInsuranceToPayMedicalExpensesPart(String supplementaryMedicalInsuranceToPayMedicalExpensesPart) {
		this.supplementaryMedicalInsuranceToPayMedicalExpensesPart = supplementaryMedicalInsuranceToPayMedicalExpensesPart;
	}

	public String getLivingAllowanceEnterprise() {
		return livingAllowanceEnterprise;
	}

	public void setLivingAllowanceEnterprise(String livingAllowanceEnterprise) {
		this.livingAllowanceEnterprise = livingAllowanceEnterprise;
	}

	public String getLivingAllowanceIndustry() {
		return livingAllowanceIndustry;
	}

	public void setLivingAllowanceIndustry(String livingAllowanceIndustry) {
		this.livingAllowanceIndustry = livingAllowanceIndustry;
	}

	public String getLivingAllowanceLocal() {
		return livingAllowanceLocal;
	}

	public void setLivingAllowanceLocal(String livingAllowanceLocal) {
		this.livingAllowanceLocal = livingAllowanceLocal;
	}

	public String getAoneTimeSubsidyLocal() {
		return aoneTimeSubsidyLocal;
	}

	public void setAoneTimeSubsidyLocal(String aoneTimeSubsidyLocal) {
		this.aoneTimeSubsidyLocal = aoneTimeSubsidyLocal;
	}

	public String getAoneTimeSubsidySpecial() {
		return aoneTimeSubsidySpecial;
	}

	public void setAoneTimeSubsidySpecial(String aoneTimeSubsidySpecial) {
		this.aoneTimeSubsidySpecial = aoneTimeSubsidySpecial;
	}

	public String getAoneTimeSubsidyIllnessRetreat() {
		return aoneTimeSubsidyIllnessRetreat;
	}

	public void setAoneTimeSubsidyIllnessRetreat(String aoneTimeSubsidyIllnessRetreat) {
		this.aoneTimeSubsidyIllnessRetreat = aoneTimeSubsidyIllnessRetreat;
	}

	public String getShouldTheTotal() {
		return shouldTheTotal;
	}

	public void setShouldTheTotal(String shouldTheTotal) {
		this.shouldTheTotal = shouldTheTotal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
