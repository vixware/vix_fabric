package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 工作情况->工作履历
 * @author 李大鹏
 */
public class WorkRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 所在单位 */
	private String unitToWhichTheyBelong;
	/** 所在部门 */
	private String workdepartment;
	/** 所在岗位 */
	private String whereThePost;
	/** 职级 */
	private String workrank;
	/** 专业经历 */
	private String professionalExperience;
	/** 证明人 */
	private String witnesses;
	/** 任职文号 */
	private String officeSymbol;
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

	public String getUnitToWhichTheyBelong() {
		return unitToWhichTheyBelong;
	}

	public void setUnitToWhichTheyBelong(String unitToWhichTheyBelong) {
		this.unitToWhichTheyBelong = unitToWhichTheyBelong;
	}

	public String getWhereThePost() {
		return whereThePost;
	}

	public void setWhereThePost(String whereThePost) {
		this.whereThePost = whereThePost;
	}

	public String getWorkdepartment() {
		return workdepartment;
	}

	public void setWorkdepartment(String workdepartment) {
		this.workdepartment = workdepartment;
	}

	public String getWorkrank() {
		return workrank;
	}

	public void setWorkrank(String workrank) {
		this.workrank = workrank;
	}

	public String getProfessionalExperience() {
		return professionalExperience;
	}

	public void setProfessionalExperience(String professionalExperience) {
		this.professionalExperience = professionalExperience;
	}

	public String getWitnesses() {
		return witnesses;
	}

	public void setWitnesses(String witnesses) {
		this.witnesses = witnesses;
	}

	public String getOfficeSymbol() {
		return officeSymbol;
	}

	public void setOfficeSymbol(String officeSymbol) {
		this.officeSymbol = officeSymbol;
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
