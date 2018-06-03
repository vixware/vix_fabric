/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         加班规则 OT(overTime 加班) oTHs(overTimeHours 加班时长)
 *         hORC(holidayOvertimeRateCalculation 假日加班)
 */
public class OverTimeRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 启用工时乘加班倍率 */
	private Integer isEnableOTRate;
	/** 日加班需要加班确认单 */
	private Integer dailyConfirmation;
	/** 启用日常加班倍率 */
	private Integer isDailyOTRate;
	/** 日常加班倍率 */
	private Double dailyOTRate;
	/** 周加班需要加班确认单 */
	private Integer weekendConfirmation;
	/** 启用周日加班倍率 */
	private Integer isWeekendOTRatel;
	/** 周日加班倍率 */
	private Double weekendOTRatel;
	/** 假日加班需要加班确认单 */
	private Integer holidayConfirmation;
	/** 启用假日加班倍率 */
	private Integer isHolidayOTRatel;
	/** 假日加班倍率 */
	private Double holidayOTRatel;
	/** 日倍数 */
	private Double dailyMultiple;
	/** 周倍数 */
	private Double weekendMultiple;
	/** 假日倍数 */
	private Double holidayMultiple;
	/** 工时不足一天用加班补充 */
	private Integer compensationWorkTimes;
	/** 轮休时间不计加班 */
	private Integer needOT;
	/** 周日不加班算旷工 */
	private Integer weekendAbsenteeism;
	/** 假日不加班算旷工 */
	private Integer holidayAbsenteeism;
	/** 加班时间不计入加班段平 */
	private Integer isDisableOT;
	/** 提前上班 */
	private Integer earlyWork;
	/** 加班分钟 */
	private Integer oTHs;
	/** 下班分钟 */
	private Integer offDutyHours;
	/** 推迟下班 */
	private Integer delayedWork;
	/** 周日假日加班时间超时1 */
	private Double weekendMaxOTHsOne;
	/** 周日假日加班时间超时2 */
	private Double weekendMaxOTHsTwo;
	/** 周日假日加班时间超时3 */
	private Double weekendMaxOTHsThree;
	/** 工作日加班时间超时1 */
	private Double workingDayMaxOTHsOne;
	/** 工作日加班时间超时2 */
	private Double workingDayMaxOTHsTwo;
	/** 假日加班倍率计算 */
	private Integer hORC;

	public Integer getIsEnableOTRate() {
		return isEnableOTRate;
	}

	public void setIsEnableOTRate(Integer isEnableOTRate) {
		this.isEnableOTRate = isEnableOTRate;
	}

	public Double getDailyOTRate() {
		return dailyOTRate;
	}

	public void setDailyOTRate(Double dailyOTRate) {
		this.dailyOTRate = dailyOTRate;
	}

	public Double getWeekendOTRatel() {
		return weekendOTRatel;
	}

	public void setWeekendOTRatel(Double weekendOTRatel) {
		this.weekendOTRatel = weekendOTRatel;
	}

	public Double getHolidayOTRatel() {
		return holidayOTRatel;
	}

	public void setHolidayOTRatel(Double holidayOTRatel) {
		this.holidayOTRatel = holidayOTRatel;
	}

	public Double getDailyMultiple() {
		return dailyMultiple;
	}

	public void setDailyMultiple(Double dailyMultiple) {
		this.dailyMultiple = dailyMultiple;
	}

	public Double getWeekendMultiple() {
		return weekendMultiple;
	}

	public void setWeekendMultiple(Double weekendMultiple) {
		this.weekendMultiple = weekendMultiple;
	}

	public Double getHolidayMultiple() {
		return holidayMultiple;
	}

	public void setHolidayMultiple(Double holidayMultiple) {
		this.holidayMultiple = holidayMultiple;
	}

	public Integer getDailyConfirmation() {
		return dailyConfirmation;
	}

	public void setDailyConfirmation(Integer dailyConfirmation) {
		this.dailyConfirmation = dailyConfirmation;
	}

	public Integer getWeekendConfirmation() {
		return weekendConfirmation;
	}

	public void setWeekendConfirmation(Integer weekendConfirmation) {
		this.weekendConfirmation = weekendConfirmation;
	}

	public Integer getHolidayConfirmation() {
		return holidayConfirmation;
	}

	public void setHolidayConfirmation(Integer holidayConfirmation) {
		this.holidayConfirmation = holidayConfirmation;
	}

	public Integer getCompensationWorkTimes() {
		return compensationWorkTimes;
	}

	public void setCompensationWorkTimes(Integer compensationWorkTimes) {
		this.compensationWorkTimes = compensationWorkTimes;
	}

	public Integer getNeedOT() {
		return needOT;
	}

	public void setNeedOT(Integer needOT) {
		this.needOT = needOT;
	}

	public Integer getWeekendAbsenteeism() {
		return weekendAbsenteeism;
	}

	public void setWeekendAbsenteeism(Integer weekendAbsenteeism) {
		this.weekendAbsenteeism = weekendAbsenteeism;
	}

	public Integer getHolidayAbsenteeism() {
		return holidayAbsenteeism;
	}

	public void setHolidayAbsenteeism(Integer holidayAbsenteeism) {
		this.holidayAbsenteeism = holidayAbsenteeism;
	}

	public Integer getIsDisableOT() {
		return isDisableOT;
	}

	public void setIsDisableOT(Integer isDisableOT) {
		this.isDisableOT = isDisableOT;
	}

	public Integer getEarlyWork() {
		return earlyWork;
	}

	public void setEarlyWork(Integer earlyWork) {
		this.earlyWork = earlyWork;
	}

	public Integer gethORC() {
		return hORC;
	}

	public void sethORC(Integer hORC) {
		this.hORC = hORC;
	}

	public Integer getoTHs() {
		return oTHs;
	}

	public void setoTHs(Integer oTHs) {
		this.oTHs = oTHs;
	}

	public Integer getOffDutyHours() {
		return offDutyHours;
	}

	public void setOffDutyHours(Integer offDutyHours) {
		this.offDutyHours = offDutyHours;
	}

	public Integer getDelayedWork() {
		return delayedWork;
	}

	public void setDelayedWork(Integer delayedWork) {
		this.delayedWork = delayedWork;
	}

	public Double getWeekendMaxOTHsOne() {
		return weekendMaxOTHsOne;
	}

	public void setWeekendMaxOTHsOne(Double weekendMaxOTHsOne) {
		this.weekendMaxOTHsOne = weekendMaxOTHsOne;
	}

	public Double getWeekendMaxOTHsTwo() {
		return weekendMaxOTHsTwo;
	}

	public void setWeekendMaxOTHsTwo(Double weekendMaxOTHsTwo) {
		this.weekendMaxOTHsTwo = weekendMaxOTHsTwo;
	}

	public Double getWorkingDayMaxOTHsOne() {
		return workingDayMaxOTHsOne;
	}

	public void setWorkingDayMaxOTHsOne(Double workingDayMaxOTHsOne) {
		this.workingDayMaxOTHsOne = workingDayMaxOTHsOne;
	}

	public Double getWorkingDayMaxOTHsTwo() {
		return workingDayMaxOTHsTwo;
	}

	public void setWorkingDayMaxOTHsTwo(Double workingDayMaxOTHsTwo) {
		this.workingDayMaxOTHsTwo = workingDayMaxOTHsTwo;
	}

	public Integer getIsDailyOTRate() {
		return isDailyOTRate;
	}

	public void setIsDailyOTRate(Integer isDailyOTRate) {
		this.isDailyOTRate = isDailyOTRate;
	}

	public Integer getIsWeekendOTRatel() {
		return isWeekendOTRatel;
	}

	public void setIsWeekendOTRatel(Integer isWeekendOTRatel) {
		this.isWeekendOTRatel = isWeekendOTRatel;
	}

	public Integer getIsHolidayOTRatel() {
		return isHolidayOTRatel;
	}

	public void setIsHolidayOTRatel(Integer isHolidayOTRatel) {
		this.isHolidayOTRatel = isHolidayOTRatel;
	}

	public Double getWeekendMaxOTHsThree() {
		return weekendMaxOTHsThree;
	}

	public void setWeekendMaxOTHsThree(Double weekendMaxOTHsThree) {
		this.weekendMaxOTHsThree = weekendMaxOTHsThree;
	}

}
