package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人能力->学历学位情况
 * @author 李大鹏
 */
public class Degree extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 学历 */
	private String educationalBackground;
	/** 学历证书 */
	private String academicCertificates;
	/** 入学时间 */
	private Date admissionTime;
	/** 学历证书编号 */
	private String educationCertificateNumber;
	/** 专业门类 */
	private String professionalCategory;
	/** 专业学科 */
	private String professionalDisciplinary;
	/** 学习形式 */
	private String formsOfLearning;
	/** 学制 */
	private String schoolSystem;
	/** 学校名称 */
	private String schoolName;
	/** 是否211或985 */
	private String whether;
	/** 是否最高学历 */
	private String whetherTheHighestDegree;
	/** 是否原始学历 */
	private String whetherTheOriginalQualifications;
	/** 学位 */
	private String degree;
	/** 学位证书编号 */
	private String diplomaNumber;
	/** 学位授予国家 */
	private String degreeGrantingCountries;
	/** 学位授予单位 */
	private String degreeAwarding;
	/** 是否最高学位 */
	private String degreeGrantedWhetherTheHighestDegree;
	/** 是否辅修二学位 */
	private String whetherMinorInTheSecondDegree;
	/** 辅修二学位名称 */
	private String minorSecondDegreeName;
	/** 备注 */
	private String remark;

	/** 职员 */
	private Employee employee;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public String getAcademicCertificates() {
		return academicCertificates;
	}

	public void setAcademicCertificates(String academicCertificates) {
		this.academicCertificates = academicCertificates;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	public String getEducationCertificateNumber() {
		return educationCertificateNumber;
	}

	public void setEducationCertificateNumber(String educationCertificateNumber) {
		this.educationCertificateNumber = educationCertificateNumber;
	}

	public String getProfessionalCategory() {
		return professionalCategory;
	}

	public void setProfessionalCategory(String professionalCategory) {
		this.professionalCategory = professionalCategory;
	}

	public String getProfessionalDisciplinary() {
		return professionalDisciplinary;
	}

	public void setProfessionalDisciplinary(String professionalDisciplinary) {
		this.professionalDisciplinary = professionalDisciplinary;
	}

	public String getFormsOfLearning() {
		return formsOfLearning;
	}

	public void setFormsOfLearning(String formsOfLearning) {
		this.formsOfLearning = formsOfLearning;
	}

	public String getSchoolSystem() {
		return schoolSystem;
	}

	public void setSchoolSystem(String schoolSystem) {
		this.schoolSystem = schoolSystem;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getWhether() {
		return whether;
	}

	public void setWhether(String whether) {
		this.whether = whether;
	}

	public String getWhetherTheHighestDegree() {
		return whetherTheHighestDegree;
	}

	public void setWhetherTheHighestDegree(String whetherTheHighestDegree) {
		this.whetherTheHighestDegree = whetherTheHighestDegree;
	}

	public String getWhetherTheOriginalQualifications() {
		return whetherTheOriginalQualifications;
	}

	public void setWhetherTheOriginalQualifications(String whetherTheOriginalQualifications) {
		this.whetherTheOriginalQualifications = whetherTheOriginalQualifications;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDiplomaNumber() {
		return diplomaNumber;
	}

	public void setDiplomaNumber(String diplomaNumber) {
		this.diplomaNumber = diplomaNumber;
	}

	public String getDegreeGrantingCountries() {
		return degreeGrantingCountries;
	}

	public void setDegreeGrantingCountries(String degreeGrantingCountries) {
		this.degreeGrantingCountries = degreeGrantingCountries;
	}

	public String getDegreeAwarding() {
		return degreeAwarding;
	}

	public void setDegreeAwarding(String degreeAwarding) {
		this.degreeAwarding = degreeAwarding;
	}

	public String getDegreeGrantedWhetherTheHighestDegree() {
		return degreeGrantedWhetherTheHighestDegree;
	}

	public void setDegreeGrantedWhetherTheHighestDegree(String degreeGrantedWhetherTheHighestDegree) {
		this.degreeGrantedWhetherTheHighestDegree = degreeGrantedWhetherTheHighestDegree;
	}

	public String getWhetherMinorInTheSecondDegree() {
		return whetherMinorInTheSecondDegree;
	}

	public void setWhetherMinorInTheSecondDegree(String whetherMinorInTheSecondDegree) {
		this.whetherMinorInTheSecondDegree = whetherMinorInTheSecondDegree;
	}

	public String getMinorSecondDegreeName() {
		return minorSecondDegreeName;
	}

	public void setMinorSecondDegreeName(String minorSecondDegreeName) {
		this.minorSecondDegreeName = minorSecondDegreeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
