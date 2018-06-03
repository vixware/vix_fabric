package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 个人关系->伤残病
 * @author 李大鹏
 */
public class DisabilityDiseaseInfor extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 伤残病类型 */
	private String disabilityDiseaseType;
	/** 伤残病名称 */
	private String disabilityDiseaseName;
	/** 伤残病原因 */
	private String disabilityDiseaseReasons;
	/** 伤残病程度 */
	private String disabilityDiseaseExtent;
	/** 受伤日期 */
	private Date dateOfInjury;
	/** 确诊日期 */
	private Date dateOfConfirmation;
	/** 康复日期 */
	private Date rehabilitationDate;
	/** 诊断机构 */
	private String diagnosisAgencies;
	/** 工伤证号 */
	private String workInjuryCardNumber;
	/** 职业病名称 */
	private String occupationalDiseaseName;
	/** 残疾证号 */
	private String disabilityCardNumber;
	/** 鉴定类别 */
	private String ldentificationCategory;
	/** 鉴定日期 */
	private Date ldentificationDate;
	/** 伤残病鉴定等级 */
	private String disabilityDiseaseIdentificationLevel;
	/** 护理等级 */
	private String careLevel;
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

	public String getDisabilityDiseaseType() {
		return disabilityDiseaseType;
	}

	public void setDisabilityDiseaseType(String disabilityDiseaseType) {
		this.disabilityDiseaseType = disabilityDiseaseType;
	}

	public String getDisabilityDiseaseName() {
		return disabilityDiseaseName;
	}

	public void setDisabilityDiseaseName(String disabilityDiseaseName) {
		this.disabilityDiseaseName = disabilityDiseaseName;
	}

	public String getDisabilityDiseaseReasons() {
		return disabilityDiseaseReasons;
	}

	public void setDisabilityDiseaseReasons(String disabilityDiseaseReasons) {
		this.disabilityDiseaseReasons = disabilityDiseaseReasons;
	}

	public String getDisabilityDiseaseExtent() {
		return disabilityDiseaseExtent;
	}

	public void setDisabilityDiseaseExtent(String disabilityDiseaseExtent) {
		this.disabilityDiseaseExtent = disabilityDiseaseExtent;
	}

	public Date getDateOfInjury() {
		return dateOfInjury;
	}

	public void setDateOfInjury(Date dateOfInjury) {
		this.dateOfInjury = dateOfInjury;
	}

	public Date getDateOfConfirmation() {
		return dateOfConfirmation;
	}

	public void setDateOfConfirmation(Date dateOfConfirmation) {
		this.dateOfConfirmation = dateOfConfirmation;
	}

	public Date getRehabilitationDate() {
		return rehabilitationDate;
	}

	public void setRehabilitationDate(Date rehabilitationDate) {
		this.rehabilitationDate = rehabilitationDate;
	}

	public String getDiagnosisAgencies() {
		return diagnosisAgencies;
	}

	public void setDiagnosisAgencies(String diagnosisAgencies) {
		this.diagnosisAgencies = diagnosisAgencies;
	}

	public String getWorkInjuryCardNumber() {
		return workInjuryCardNumber;
	}

	public void setWorkInjuryCardNumber(String workInjuryCardNumber) {
		this.workInjuryCardNumber = workInjuryCardNumber;
	}

	public String getOccupationalDiseaseName() {
		return occupationalDiseaseName;
	}

	public void setOccupationalDiseaseName(String occupationalDiseaseName) {
		this.occupationalDiseaseName = occupationalDiseaseName;
	}

	public String getDisabilityCardNumber() {
		return disabilityCardNumber;
	}

	public void setDisabilityCardNumber(String disabilityCardNumber) {
		this.disabilityCardNumber = disabilityCardNumber;
	}

	public String getLdentificationCategory() {
		return ldentificationCategory;
	}

	public void setLdentificationCategory(String ldentificationCategory) {
		this.ldentificationCategory = ldentificationCategory;
	}

	public Date getLdentificationDate() {
		return ldentificationDate;
	}

	public void setLdentificationDate(Date ldentificationDate) {
		this.ldentificationDate = ldentificationDate;
	}

	public String getDisabilityDiseaseIdentificationLevel() {
		return disabilityDiseaseIdentificationLevel;
	}

	public void setDisabilityDiseaseIdentificationLevel(String disabilityDiseaseIdentificationLevel) {
		this.disabilityDiseaseIdentificationLevel = disabilityDiseaseIdentificationLevel;
	}

	public String getCareLevel() {
		return careLevel;
	}

	public void setCareLevel(String careLevel) {
		this.careLevel = careLevel;
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
