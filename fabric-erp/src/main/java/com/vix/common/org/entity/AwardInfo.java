package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 工作情况->奖励情况
 * @author 李大鹏
 */
public class AwardInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 荣誉奖励级别 */
	private String awardsLevel;
	/** 荣誉奖励名称 */
	private String awardsName;
	/** 荣誉奖励原因 */
	private String awardsReasons;
	/** 荣誉奖励批准日期 */
	private Date awardsTheDateOfApproval;
	/** 荣誉奖励批准文件名 */
	private String awardsApprovedFileName;
	/** 荣誉奖励批准文件号 */
	private String awardsApprovedFileNumber;
	/** 荣誉奖励批准机关名称 */
	private String awardsApprovedFileNameOfTheAuthority;
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

	public String getAwardsLevel() {
		return awardsLevel;
	}

	public void setAwardsLevel(String awardsLevel) {
		this.awardsLevel = awardsLevel;
	}

	public String getAwardsName() {
		return awardsName;
	}

	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}

	public String getAwardsReasons() {
		return awardsReasons;
	}

	public void setAwardsReasons(String awardsReasons) {
		this.awardsReasons = awardsReasons;
	}

	public Date getAwardsTheDateOfApproval() {
		return awardsTheDateOfApproval;
	}

	public void setAwardsTheDateOfApproval(Date awardsTheDateOfApproval) {
		this.awardsTheDateOfApproval = awardsTheDateOfApproval;
	}

	public String getAwardsApprovedFileName() {
		return awardsApprovedFileName;
	}

	public void setAwardsApprovedFileName(String awardsApprovedFileName) {
		this.awardsApprovedFileName = awardsApprovedFileName;
	}

	public String getAwardsApprovedFileNumber() {
		return awardsApprovedFileNumber;
	}

	public void setAwardsApprovedFileNumber(String awardsApprovedFileNumber) {
		this.awardsApprovedFileNumber = awardsApprovedFileNumber;
	}

	public String getAwardsApprovedFileNameOfTheAuthority() {
		return awardsApprovedFileNameOfTheAuthority;
	}

	public void setAwardsApprovedFileNameOfTheAuthority(String awardsApprovedFileNameOfTheAuthority) {
		this.awardsApprovedFileNameOfTheAuthority = awardsApprovedFileNameOfTheAuthority;
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
