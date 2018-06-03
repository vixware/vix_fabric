/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         其他规则
 */
public class OtherRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 中途外出 */
	private Integer goOut;
	/** 外出允许最大时间 */
	private Double maxGoOutHours;
	/** 请假计算起始 */
	private Integer minLeave;
	/** 请假开始时间 */
	private Integer leaveStartTime;
	/** 允许中班夜班重复计算 */
	private Integer isEnableRepeated;
	/** 忽略排班及自动套班以实际刷卡时间计算工时 */
	private Integer isEnabelNeglect;
	/** 中班计算时间 */
	private Integer timeOfMin;
	/** 夜班计算时间 */
	private Integer timeOfNight;
	/** 跨天刷卡 */
	private Integer isEnableNextDay;
	/** 卡分钟上下班 */
	private Integer exactTime;
	/** 单次打开算缺勤 */
	private Integer isOnce;
	/** 白班开始时间 */
	private Integer dayShiftStartTime;
	/** 白班结束时间 */
	private Integer dayShiftEndTime;
	/** 中班开始时间 */
	private Integer midShiftStartTime;
	/** 中班结束时间 */
	private Integer minShiftEndTime;
	/** 夜班开始时间 */
	private Integer nightShiftStartTime;
	/** 夜班开始时间 */
	private Integer nightShiftEndTime;
	/** 上班记录 */
	private Integer goToWork;
	/** 下班记录 */
	private Integer goOffWork;
	/** 是否启用 */
	private Integer isEnable;

	public Integer getGoOut() {
		return goOut;
	}

	public void setGoOut(Integer goOut) {
		this.goOut = goOut;
	}

	public Double getMaxGoOutHours() {
		return maxGoOutHours;
	}

	public void setMaxGoOutHours(Double maxGoOutHours) {
		this.maxGoOutHours = maxGoOutHours;
	}

	public Integer getMinLeave() {
		return minLeave;
	}

	public void setMinLeave(Integer minLeave) {
		this.minLeave = minLeave;
	}

	public Integer getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(Integer leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public Integer getIsEnableRepeated() {
		return isEnableRepeated;
	}

	public void setIsEnableRepeated(Integer isEnableRepeated) {
		this.isEnableRepeated = isEnableRepeated;
	}

	public Integer getIsEnabelNeglect() {
		return isEnabelNeglect;
	}

	public void setIsEnabelNeglect(Integer isEnabelNeglect) {
		this.isEnabelNeglect = isEnabelNeglect;
	}

	public Integer getTimeOfMin() {
		return timeOfMin;
	}

	public void setTimeOfMin(Integer timeOfMin) {
		this.timeOfMin = timeOfMin;
	}

	public Integer getTimeOfNight() {
		return timeOfNight;
	}

	public void setTimeOfNight(Integer timeOfNight) {
		this.timeOfNight = timeOfNight;
	}

	public Integer getIsEnableNextDay() {
		return isEnableNextDay;
	}

	public void setIsEnableNextDay(Integer isEnableNextDay) {
		this.isEnableNextDay = isEnableNextDay;
	}

	public Integer getExactTime() {
		return exactTime;
	}

	public void setExactTime(Integer exactTime) {
		this.exactTime = exactTime;
	}

	public Integer getIsOnce() {
		return isOnce;
	}

	public void setIsOnce(Integer isOnce) {
		this.isOnce = isOnce;
	}

	public Integer getDayShiftStartTime() {
		return dayShiftStartTime;
	}

	public void setDayShiftStartTime(Integer dayShiftStartTime) {
		this.dayShiftStartTime = dayShiftStartTime;
	}

	public Integer getDayShiftEndTime() {
		return dayShiftEndTime;
	}

	public void setDayShiftEndTime(Integer dayShiftEndTime) {
		this.dayShiftEndTime = dayShiftEndTime;
	}

	public Integer getMidShiftStartTime() {
		return midShiftStartTime;
	}

	public void setMidShiftStartTime(Integer midShiftStartTime) {
		this.midShiftStartTime = midShiftStartTime;
	}

	public Integer getMinShiftEndTime() {
		return minShiftEndTime;
	}

	public void setMinShiftEndTime(Integer minShiftEndTime) {
		this.minShiftEndTime = minShiftEndTime;
	}

	public Integer getNightShiftStartTime() {
		return nightShiftStartTime;
	}

	public void setNightShiftStartTime(Integer nightShiftStartTime) {
		this.nightShiftStartTime = nightShiftStartTime;
	}

	public Integer getNightShiftEndTime() {
		return nightShiftEndTime;
	}

	public void setNightShiftEndTime(Integer nightShiftEndTime) {
		this.nightShiftEndTime = nightShiftEndTime;
	}

	public Integer getGoToWork() {
		return goToWork;
	}

	public void setGoToWork(Integer goToWork) {
		this.goToWork = goToWork;
	}

	public Integer getGoOffWork() {
		return goOffWork;
	}

	public void setGoOffWork(Integer goOffWork) {
		this.goOffWork = goOffWork;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

}
