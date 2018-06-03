package com.vix.crm.agenda.entity;

import com.vix.common.share.entity.BaseEntity;

public class Monthly extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 月报唯一码 */
	private String monthlyCode;
	/** 本月总结 */
	private String monthSummary;
	/** 下月计划*/
	private String nextMonthPlan;
	/** 职员编码 */
	private String employeeCode;
	
	public Monthly(){}

	public String getMonthSummary() {
		return monthSummary;
	}

	public void setMonthSummary(String monthSummary) {
		this.monthSummary = monthSummary;
	}

	public String getNextMonthPlan() {
		return nextMonthPlan;
	}

	public void setNextMonthPlan(String nextMonthPlan) {
		this.nextMonthPlan = nextMonthPlan;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getMonthlyCode() {
		return monthlyCode;
	}

	public void setMonthlyCode(String monthlyCode) {
		this.monthlyCode = monthlyCode;
	}
}
