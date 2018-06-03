package com.vix.crm.agenda.entity;

import com.vix.common.share.entity.BaseEntity;

public class Weekly extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 周报唯一码 */
	private String weeklyCode;
	/** 本周总结 */
	private String weekSummary;
	/** 下周计划*/
	private String nextWeekPlan;
	/** 职员编码 */
	private String employeeCode;
	
	public Weekly(){}

	public String getWeekSummary() {
		return weekSummary;
	}

	public void setWeekSummary(String weekSummary) {
		this.weekSummary = weekSummary;
	}

	public String getNextWeekPlan() {
		return nextWeekPlan;
	}

	public void setNextWeekPlan(String nextWeekPlan) {
		this.nextWeekPlan = nextWeekPlan;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getWeeklyCode() {
		return weeklyCode;
	}

	public void setWeeklyCode(String weeklyCode) {
		this.weeklyCode = weeklyCode;
	}
	
	public String getWeekNumber(){
		if(null != weeklyCode && !"".equals(weeklyCode)){
			return weeklyCode.substring(4,weeklyCode.length());
		}
		return "";
	}
}
