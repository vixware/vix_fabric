/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 保险登记
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class InsuranceRegister extends BaseEntity {
	private static final long serialVersionUID = -6204356741159445179L;
	/** 保单号 */
	private String insurancePolicyCode;
	/** 投保日期 */
	private Date insuranceDate;
	/** 车牌号 */
	private String vechileNumber;
	/** 发动机号 */
	private String engineNumber;
	/** 车架号 */
	private String frameNumber;
	/** 车辆类型 */
	private String vechileType;
	/** 保险公司 */
	private String insuracenCompany;
	/** 联系电话 */
	private String phone;
	/** 投保单位 */
	private String coverInsuranceCompany;
	/** 投保人电话 */
	private String applicantPhone;
	/** 保险办理人 */
	private String transactor;
	/** 保险生效日期 */
	private Date effectiveDate;
	/** 保险失效日期 */
	private Date expiryDate;
	/** 预通知天数 */
	private int noticeDay;
	/** 保险受益人 */
	private String beneficiary;
	/** 保险类型 */
	private String insuranceType;
	/** 交纳保险金额 */
	private String insuranceAmount;
	/** 保额 */
	private String coverage;
	/** 车辆 */
	private Vehicle vehicle;

	public String getInsurancePolicyCode() {
		return insurancePolicyCode;
	}

	public void setInsurancePolicyCode(String insurancePolicyCode) {
		this.insurancePolicyCode = insurancePolicyCode;
	}

	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public String getVechileNumber() {
		return vechileNumber;
	}

	public void setVechileNumber(String vechileNumber) {
		this.vechileNumber = vechileNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getVechileType() {
		return vechileType;
	}

	public void setVechileType(String vechileType) {
		this.vechileType = vechileType;
	}

	public String getInsuracenCompany() {
		return insuracenCompany;
	}

	public void setInsuracenCompany(String insuracenCompany) {
		this.insuracenCompany = insuracenCompany;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCoverInsuranceCompany() {
		return coverInsuranceCompany;
	}

	public void setCoverInsuranceCompany(String coverInsuranceCompany) {
		this.coverInsuranceCompany = coverInsuranceCompany;
	}

	public String getApplicantPhone() {
		return applicantPhone;
	}

	public void setApplicantPhone(String applicantPhone) {
		this.applicantPhone = applicantPhone;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getNoticeDay() {
		return noticeDay;
	}

	public void setNoticeDay(int noticeDay) {
		this.noticeDay = noticeDay;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
