package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人能力->专业技术职务
 * @author 李大鹏
 */
public class Techtitle extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 员工编码 */
	private String employeeCode;
	/** 专业技术职务系列 */
	private String professionalAndTechnicalQualifications;
	/** 专业技术职务名称 */
	private String professionalAndTechnicalQualificationName;
	/** 专业级别 */
	private String professionallevel;
	/** 评定机构 */
	private String assessmentOrganization;
	/** 认定机构 */
	private String accreditationInstitution;
	/** 是否国家专业技术资格考试 */
	private String whetherTheNationalProfessionalQualificationExamination;
	/** 是否通过认定 */
	private String whetherThroughTheIdentificationOf;
	/** 是否最高资格 */
	private String ifTheHighestQualification;
	/** 资格证号 */
	private String certificateNo;
	/** 文件号 */
	private String fileNumber;
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

	public String getProfessionalAndTechnicalQualifications() {
		return professionalAndTechnicalQualifications;
	}

	public void setProfessionalAndTechnicalQualifications(String professionalAndTechnicalQualifications) {
		this.professionalAndTechnicalQualifications = professionalAndTechnicalQualifications;
	}

	public String getProfessionalAndTechnicalQualificationName() {
		return professionalAndTechnicalQualificationName;
	}

	public void setProfessionalAndTechnicalQualificationName(String professionalAndTechnicalQualificationName) {
		this.professionalAndTechnicalQualificationName = professionalAndTechnicalQualificationName;
	}

	public String getProfessionallevel() {
		return professionallevel;
	}

	public void setProfessionallevel(String professionallevel) {
		this.professionallevel = professionallevel;
	}

	public String getAssessmentOrganization() {
		return assessmentOrganization;
	}

	public void setAssessmentOrganization(String assessmentOrganization) {
		this.assessmentOrganization = assessmentOrganization;
	}

	public String getAccreditationInstitution() {
		return accreditationInstitution;
	}

	public void setAccreditationInstitution(String accreditationInstitution) {
		this.accreditationInstitution = accreditationInstitution;
	}

	public String getWhetherTheNationalProfessionalQualificationExamination() {
		return whetherTheNationalProfessionalQualificationExamination;
	}

	public void setWhetherTheNationalProfessionalQualificationExamination(String whetherTheNationalProfessionalQualificationExamination) {
		this.whetherTheNationalProfessionalQualificationExamination = whetherTheNationalProfessionalQualificationExamination;
	}

	public String getWhetherThroughTheIdentificationOf() {
		return whetherThroughTheIdentificationOf;
	}

	public void setWhetherThroughTheIdentificationOf(String whetherThroughTheIdentificationOf) {
		this.whetherThroughTheIdentificationOf = whetherThroughTheIdentificationOf;
	}

	public String getIfTheHighestQualification() {
		return ifTheHighestQualification;
	}

	public void setIfTheHighestQualification(String ifTheHighestQualification) {
		this.ifTheHighestQualification = ifTheHighestQualification;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
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
