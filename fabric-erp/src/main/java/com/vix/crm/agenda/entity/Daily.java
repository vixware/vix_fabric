package com.vix.crm.agenda.entity;

import com.vix.common.share.entity.BaseEntity;

public class Daily extends BaseEntity {
 
	private static final long serialVersionUID = 1L;

	/** 日报唯一码 */
	private String dailyCode;
	/** 今日总结 */
	private String todaySummary;
	/** 明日计划*/
	private String tomorrowPlan;
	/** 职员编码 */
	private String employeeCode;
	/** 回复*/
	private String repeat;
	/** 回复人编码*/
	private String repeatEmployeeCode;
	
	public Daily(){}

	public String getTodaySummary() {
		return todaySummary;
	}

	public void setTodaySummary(String todaySummary) {
		this.todaySummary = todaySummary;
	}

	public String getTomorrowPlan() {
		return tomorrowPlan;
	}

	public void setTomorrowPlan(String tomorrowPlan) {
		this.tomorrowPlan = tomorrowPlan;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDailyCode() {
		return dailyCode;
	}

	public void setDailyCode(String dailyCode) {
		this.dailyCode = dailyCode;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getRepeatEmployeeCode() {
		return repeatEmployeeCode;
	}

	public void setRepeatEmployeeCode(String repeatEmployeeCode) {
		this.repeatEmployeeCode = repeatEmployeeCode;
	}
}
