/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         工作日设定
 */
public class WorkDayRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 星期一 */
	private Integer monday;
	/** 休息开始时间 */
	private Integer mondayStarTime;
	/** 休息结束时间 */
	private Integer mondayEndTime;
	/** 星期二 */
	private Integer tuesday;
	/** 休息开始时间 */
	private Integer tuesdayStarTime;
	/** 休息结束时间 */
	private Integer tuesdayEndTime;
	/** 星期三 */
	private Integer wednesday;
	/** 休息开始时间 */
	private Integer wednesdayStarTime;
	/** 休息结束时间 */
	private Integer wednesdayEndTime;
	/** 星期四 */
	private Integer thursday;
	/** 休息开始时间 */
	private Integer thursdayStarTime;
	/** 休息结束时间 */
	private Integer thursdayEndTime;
	/** 星期五 */
	private Integer friday;
	/** 休息开始时间 */
	private Integer fridayStarTime;
	/** 休息结束时间 */
	private Integer fridayEndTime;
	/** 星期六 */
	private Integer saturday;
	/** 休息开始时间 */
	private Integer saturdayStarTime;
	/** 休息结束时间 */
	private Integer saturdayEndTime;
	/** 星期日 */
	private Integer sunday;
	/** 休息开始时间 */
	private Integer sundayStarTime;
	/** 休息结束时间 */
	private Integer sundayEndTime;
	/** 按日期方式设定 */
	private Integer modeByDate;
	/** 休息日 */
	private Integer restDay;
	/** 休息日开始时间 */
	private Integer restDayStartTime;
	/** 休息日结束时间 */
	private Integer restDayEndTime;
	/** 每月月底休息 */
	private Integer endOfMonthRest;
	/** 每天工作时数 */
	private Double maxWorkHours;
	/** 每天工时大于 */
	private Double minWorkHours;
	/** 是否启用 */
	private Integer isEnable;

	public Integer getMonday() {
		return monday;
	}

	public void setMonday(Integer monday) {
		this.monday = monday;
	}

	public Integer getMondayStarTime() {
		return mondayStarTime;
	}

	public void setMondayStarTime(Integer mondayStarTime) {
		this.mondayStarTime = mondayStarTime;
	}

	public Integer getMondayEndTime() {
		return mondayEndTime;
	}

	public void setMondayEndTime(Integer mondayEndTime) {
		this.mondayEndTime = mondayEndTime;
	}

	public Integer getTuesday() {
		return tuesday;
	}

	public void setTuesday(Integer tuesday) {
		this.tuesday = tuesday;
	}

	public Integer getTuesdayStarTime() {
		return tuesdayStarTime;
	}

	public void setTuesdayStarTime(Integer tuesdayStarTime) {
		this.tuesdayStarTime = tuesdayStarTime;
	}

	public Integer getTuesdayEndTime() {
		return tuesdayEndTime;
	}

	public void setTuesdayEndTime(Integer tuesdayEndTime) {
		this.tuesdayEndTime = tuesdayEndTime;
	}

	public Integer getWednesday() {
		return wednesday;
	}

	public void setWednesday(Integer wednesday) {
		this.wednesday = wednesday;
	}

	public Integer getWednesdayStarTime() {
		return wednesdayStarTime;
	}

	public void setWednesdayStarTime(Integer wednesdayStarTime) {
		this.wednesdayStarTime = wednesdayStarTime;
	}

	public Integer getWednesdayEndTime() {
		return wednesdayEndTime;
	}

	public void setWednesdayEndTime(Integer wednesdayEndTime) {
		this.wednesdayEndTime = wednesdayEndTime;
	}

	public Integer getThursday() {
		return thursday;
	}

	public void setThursday(Integer thursday) {
		this.thursday = thursday;
	}

	public Integer getThursdayStarTime() {
		return thursdayStarTime;
	}

	public void setThursdayStarTime(Integer thursdayStarTime) {
		this.thursdayStarTime = thursdayStarTime;
	}

	public Integer getThursdayEndTime() {
		return thursdayEndTime;
	}

	public void setThursdayEndTime(Integer thursdayEndTime) {
		this.thursdayEndTime = thursdayEndTime;
	}

	public Integer getFriday() {
		return friday;
	}

	public void setFriday(Integer friday) {
		this.friday = friday;
	}

	public Integer getFridayStarTime() {
		return fridayStarTime;
	}

	public void setFridayStarTime(Integer fridayStarTime) {
		this.fridayStarTime = fridayStarTime;
	}

	public Integer getFridayEndTime() {
		return fridayEndTime;
	}

	public void setFridayEndTime(Integer fridayEndTime) {
		this.fridayEndTime = fridayEndTime;
	}

	public Integer getSaturday() {
		return saturday;
	}

	public void setSaturday(Integer saturday) {
		this.saturday = saturday;
	}

	public Integer getSaturdayStarTime() {
		return saturdayStarTime;
	}

	public void setSaturdayStarTime(Integer saturdayStarTime) {
		this.saturdayStarTime = saturdayStarTime;
	}

	public Integer getSaturdayEndTime() {
		return saturdayEndTime;
	}

	public void setSaturdayEndTime(Integer saturdayEndTime) {
		this.saturdayEndTime = saturdayEndTime;
	}

	public Integer getSunday() {
		return sunday;
	}

	public void setSunday(Integer sunday) {
		this.sunday = sunday;
	}

	public Integer getSundayStarTime() {
		return sundayStarTime;
	}

	public void setSundayStarTime(Integer sundayStarTime) {
		this.sundayStarTime = sundayStarTime;
	}

	public Integer getSundayEndTime() {
		return sundayEndTime;
	}

	public void setSundayEndTime(Integer sundayEndTime) {
		this.sundayEndTime = sundayEndTime;
	}

	public Integer getModeByDate() {
		return modeByDate;
	}

	public void setModeByDate(Integer modeByDate) {
		this.modeByDate = modeByDate;
	}

	public Integer getRestDay() {
		return restDay;
	}

	public void setRestDay(Integer restDay) {
		this.restDay = restDay;
	}

	public Integer getRestDayStartTime() {
		return restDayStartTime;
	}

	public void setRestDayStartTime(Integer restDayStartTime) {
		this.restDayStartTime = restDayStartTime;
	}

	public Integer getRestDayEndTime() {
		return restDayEndTime;
	}

	public void setRestDayEndTime(Integer restDayEndTime) {
		this.restDayEndTime = restDayEndTime;
	}

	public Integer getEndOfMonthRest() {
		return endOfMonthRest;
	}

	public void setEndOfMonthRest(Integer endOfMonthRest) {
		this.endOfMonthRest = endOfMonthRest;
	}

	public Double getMaxWorkHours() {
		return maxWorkHours;
	}

	public void setMaxWorkHours(Double maxWorkHours) {
		this.maxWorkHours = maxWorkHours;
	}

	public Double getMinWorkHours() {
		return minWorkHours;
	}

	public void setMinWorkHours(Double minWorkHours) {
		this.minWorkHours = minWorkHours;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

}
