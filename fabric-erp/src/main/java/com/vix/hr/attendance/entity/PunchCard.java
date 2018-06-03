package com.vix.hr.attendance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 打卡记录
 * @author 李大鹏
 */
public class PunchCard extends BaseEntity {

	private static final long serialVersionUID = -33980904337665855L;
	/** 卡号 */
	private String cardNumber;
	/** 人员姓名 */
	private String employeeName;
	/** 刷卡日期 */
	private Date creCardDate;
	/** 操作员 */
	private String operator;
	/** 备注 */
	private String remarks;
	/** 职员 */
	private Employee employee;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getCreCardDate() {
		return creCardDate;
	}

	public void setCreCardDate(Date creCardDate) {
		this.creCardDate = creCardDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
