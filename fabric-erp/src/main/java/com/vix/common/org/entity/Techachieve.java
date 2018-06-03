package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人能力->专业技术成果
 * @author 李大鹏
 */
public class Techachieve extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 专家名称 */
	private String expertName;
	/** 专业类别 */
	private String professionalCategory;
	/** 专家级别 */
	private String expertLevel;
	/** 专业名称 */
	private String professionalName;
	/** 专业技术成果 */
	private String professionalAndTechnicalAchievements;
	/** 年度考评结果 */
	private String resultsOfTheAnnualAppraisal;
	/** 状态 */
	private String satus;
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

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getProfessionalCategory() {
		return professionalCategory;
	}

	public void setProfessionalCategory(String professionalCategory) {
		this.professionalCategory = professionalCategory;
	}

	public String getExpertLevel() {
		return expertLevel;
	}

	public void setExpertLevel(String expertLevel) {
		this.expertLevel = expertLevel;
	}

	public String getProfessionalName() {
		return professionalName;
	}

	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}

	public String getProfessionalAndTechnicalAchievements() {
		return professionalAndTechnicalAchievements;
	}

	public void setProfessionalAndTechnicalAchievements(String professionalAndTechnicalAchievements) {
		this.professionalAndTechnicalAchievements = professionalAndTechnicalAchievements;
	}

	public String getResultsOfTheAnnualAppraisal() {
		return resultsOfTheAnnualAppraisal;
	}

	public void setResultsOfTheAnnualAppraisal(String resultsOfTheAnnualAppraisal) {
		this.resultsOfTheAnnualAppraisal = resultsOfTheAnnualAppraisal;
	}

	public String getSatus() {
		return satus;
	}

	public void setSatus(String satus) {
		this.satus = satus;
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
