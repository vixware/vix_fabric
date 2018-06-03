package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 工作情况->政治面貌
 * @author 李大鹏
 */
public class PoliticalStatus extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 员工编码 */
	private String employeeCode;
	/** 姓名 */
	private String politicaName;
	/** 身份证号 */
	private String idNumber;
	/** 政治面貌 */
	private String politicalStatus;
	/** 参加党派时间 */
	private Date participateInPartyTime;
	/** 参加党派时所在单位 */
	private String joinAPartisanUnit;
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

	public String getPoliticaName() {
		return politicaName;
	}

	public void setPoliticaName(String politicaName) {
		this.politicaName = politicaName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Date getParticipateInPartyTime() {
		return participateInPartyTime;
	}

	public void setParticipateInPartyTime(Date participateInPartyTime) {
		this.participateInPartyTime = participateInPartyTime;
	}

	public String getJoinAPartisanUnit() {
		return joinAPartisanUnit;
	}

	public void setJoinAPartisanUnit(String joinAPartisanUnit) {
		this.joinAPartisanUnit = joinAPartisanUnit;
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
