package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 工作情况->惩罚情况
 * @author 李大鹏
 */
public class PunInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 惩罚级别 */
	private String disciplinaryCategory;
	/** 惩罚名称 */
	private String disciplinaryName;
	/** 惩罚原因 */
	private String disciplinaryReasons;
	/** 惩罚批准日期 */
	private Date disciplinaryDateOfApproval;
	/** 惩罚批准文件名 */
	private String disciplinaryApprovalOfTheFileName;
	/** 惩罚批准文件号 */
	private String disciplinaryApprovalDocument;
	/** 惩罚批准机关名称 */
	private String disciplinaryApprovalAuthorityName;
	/** 撤销惩戒日期 */
	private Date revocationOfDisciplinaryDate;
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

	public String getDisciplinaryCategory() {
		return disciplinaryCategory;
	}

	public void setDisciplinaryCategory(String disciplinaryCategory) {
		this.disciplinaryCategory = disciplinaryCategory;
	}

	public String getDisciplinaryName() {
		return disciplinaryName;
	}

	public void setDisciplinaryName(String disciplinaryName) {
		this.disciplinaryName = disciplinaryName;
	}

	public String getDisciplinaryReasons() {
		return disciplinaryReasons;
	}

	public void setDisciplinaryReasons(String disciplinaryReasons) {
		this.disciplinaryReasons = disciplinaryReasons;
	}

	public Date getDisciplinaryDateOfApproval() {
		return disciplinaryDateOfApproval;
	}

	public void setDisciplinaryDateOfApproval(Date disciplinaryDateOfApproval) {
		this.disciplinaryDateOfApproval = disciplinaryDateOfApproval;
	}

	public String getDisciplinaryApprovalOfTheFileName() {
		return disciplinaryApprovalOfTheFileName;
	}

	public void setDisciplinaryApprovalOfTheFileName(String disciplinaryApprovalOfTheFileName) {
		this.disciplinaryApprovalOfTheFileName = disciplinaryApprovalOfTheFileName;
	}

	public String getDisciplinaryApprovalDocument() {
		return disciplinaryApprovalDocument;
	}

	public void setDisciplinaryApprovalDocument(String disciplinaryApprovalDocument) {
		this.disciplinaryApprovalDocument = disciplinaryApprovalDocument;
	}

	public String getDisciplinaryApprovalAuthorityName() {
		return disciplinaryApprovalAuthorityName;
	}

	public void setDisciplinaryApprovalAuthorityName(String disciplinaryApprovalAuthorityName) {
		this.disciplinaryApprovalAuthorityName = disciplinaryApprovalAuthorityName;
	}

	public Date getRevocationOfDisciplinaryDate() {
		return revocationOfDisciplinaryDate;
	}

	public void setRevocationOfDisciplinaryDate(Date revocationOfDisciplinaryDate) {
		this.revocationOfDisciplinaryDate = revocationOfDisciplinaryDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
