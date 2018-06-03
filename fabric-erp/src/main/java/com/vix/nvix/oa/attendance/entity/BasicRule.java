/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.nvix.oa.entity.AttendanceAndRecord;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         基本规则
 * 
 *         OT(overTime 加班) LAE(LateAndEarly 迟到或早退)
 */
public class BasicRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 上班允许提前时间 */
	private Integer workShift;
	/** 下班允许推迟时间 */
	private Integer offTime;
	/** 刷卡间隔时间 */
	private Integer interval;
	/** 上班先早后迟 */
	private Integer beforeStartWork;
	/** 下班先早后迟 */
	private Integer beforeEndWork;
	/** 超出规定时间刷卡范围 列出 */
	private Integer isOutTime;
	/** 上班最早刷卡优先 */
	private Integer firstPunchCard;
	/** 下班最后刷卡优先 */
	private Integer lastPunchCard;
	/** 自动套班不成功 列出打卡 */
	private Integer printOut;
	/** 允许最大迟到时间(不计迟到) */
	private Integer maxLateTime;
	/** 允许最大早退时间(不计早退) */
	private Integer maxEarlyTime;
	/** 迟到超时(刷卡无效) */
	private Integer lateTimeOut;
	/** 早退超时(刷卡无效) */
	private Integer earlyTimeOut;
	/** 工时中扣除迟到时间 */
	private Integer deductionLateTime;
	/** 工时中扣除早退时间 */
	private Integer deductionEarlyTime;
	/** 迟到早退时间不计入旷工 */
	private Integer doNotIncludedAbsenteeism;
	/** 周日或假日计算迟到早退 */
	private Integer numerationHolidayLAE;
	/** 提前或推迟加班计算迟到早退 */
	private Integer numerationOTLAE;
	/** 轮休计算迟到早退 */
	private Integer numerationAlternateLAE;
	/** 日常浮动加班计算迟到早退 */
	private Integer numerationDailyLAE;
	/** 考勤规则 */
	AttendanceAndRecord aaRecord;
	/** 是否启用 */
	private Integer isEnable;
	/** 最长班次时段不超过 */
	private Integer maxShiftTime;
	/** 最短班次时段不少于 */
	private Integer minShiftTime;

	public Integer getWorkShift() {
		return workShift;
	}

	public void setWorkShift(Integer workShift) {
		this.workShift = workShift;
	}

	public Integer getOffTime() {
		return offTime;
	}

	public void setOffTime(Integer offTime) {
		this.offTime = offTime;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getBeforeStartWork() {
		return beforeStartWork;
	}

	public void setBeforeStartWork(Integer beforeStartWork) {
		this.beforeStartWork = beforeStartWork;
	}

	public Integer getBeforeEndWork() {
		return beforeEndWork;
	}

	public void setBeforeEndWork(Integer beforeEndWork) {
		this.beforeEndWork = beforeEndWork;
	}

	public Integer getIsOutTime() {
		return isOutTime;
	}

	public void setIsOutTime(Integer isOutTime) {
		this.isOutTime = isOutTime;
	}

	public Integer getFirstPunchCard() {
		return firstPunchCard;
	}

	public void setFirstPunchCard(Integer firstPunchCard) {
		this.firstPunchCard = firstPunchCard;
	}

	public Integer getLastPunchCard() {
		return lastPunchCard;
	}

	public void setLastPunchCard(Integer lastPunchCard) {
		this.lastPunchCard = lastPunchCard;
	}

	public Integer getPrintOut() {
		return printOut;
	}

	public void setPrintOut(Integer printOut) {
		this.printOut = printOut;
	}

	public Integer getMaxLateTime() {
		return maxLateTime;
	}

	public void setMaxLateTime(Integer maxLateTime) {
		this.maxLateTime = maxLateTime;
	}

	public Integer getMaxEarlyTime() {
		return maxEarlyTime;
	}

	public void setMaxEarlyTime(Integer maxEarlyTime) {
		this.maxEarlyTime = maxEarlyTime;
	}

	public Integer getLateTimeOut() {
		return lateTimeOut;
	}

	public void setLateTimeOut(Integer lateTimeOut) {
		this.lateTimeOut = lateTimeOut;
	}

	public Integer getEarlyTimeOut() {
		return earlyTimeOut;
	}

	public void setEarlyTimeOut(Integer earlyTimeOut) {
		this.earlyTimeOut = earlyTimeOut;
	}

	public Integer getDeductionLateTime() {
		return deductionLateTime;
	}

	public void setDeductionLateTime(Integer deductionLateTime) {
		this.deductionLateTime = deductionLateTime;
	}

	public Integer getDeductionEarlyTime() {
		return deductionEarlyTime;
	}

	public void setDeductionEarlyTime(Integer deductionEarlyTime) {
		this.deductionEarlyTime = deductionEarlyTime;
	}

	public Integer getDoNotIncludedAbsenteeism() {
		return doNotIncludedAbsenteeism;
	}

	public void setDoNotIncludedAbsenteeism(Integer doNotIncludedAbsenteeism) {
		this.doNotIncludedAbsenteeism = doNotIncludedAbsenteeism;
	}

	public Integer getNumerationHolidayLAE() {
		return numerationHolidayLAE;
	}

	public void setNumerationHolidayLAE(Integer numerationHolidayLAE) {
		this.numerationHolidayLAE = numerationHolidayLAE;
	}

	public Integer getNumerationOTLAE() {
		return numerationOTLAE;
	}

	public void setNumerationOTLAE(Integer numerationOTLAE) {
		this.numerationOTLAE = numerationOTLAE;
	}

	public Integer getNumerationAlternateLAE() {
		return numerationAlternateLAE;
	}

	public void setNumerationAlternateLAE(Integer numerationAlternateLAE) {
		this.numerationAlternateLAE = numerationAlternateLAE;
	}

	public Integer getNumerationDailyLAE() {
		return numerationDailyLAE;
	}

	public void setNumerationDailyLAE(Integer numerationDailyLAE) {
		this.numerationDailyLAE = numerationDailyLAE;
	}

	public AttendanceAndRecord getAaRecord() {
		return aaRecord;
	}

	public void setAaRecord(AttendanceAndRecord aaRecord) {
		this.aaRecord = aaRecord;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getMaxShiftTime() {
		return maxShiftTime;
	}

	public void setMaxShiftTime(Integer maxShiftTime) {
		this.maxShiftTime = maxShiftTime;
	}

	public Integer getMinShiftTime() {
		return minShiftTime;
	}

	public void setMinShiftTime(Integer minShiftTime) {
		this.minShiftTime = minShiftTime;
	}

}
