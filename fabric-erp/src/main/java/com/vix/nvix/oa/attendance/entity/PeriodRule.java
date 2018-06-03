/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         时间段设定
 * 
 *         上班自动计算加班 ACOO(automaticCalculationOfOvertime) 下班自动计算加班
 *         ACWO(AutomaticCalculationWorkOvertime)
 */

public class PeriodRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 折合天数 */
	private Double discountDay;
	/** 工时 */
	private Double manhour;
	/** 是否跨天 */
	private Integer isNextDay;
	/** 是否是休息时段 */
	private Integer isRestPeriod;
	/** 扣除工时 */
	private Double catManhour;
	/** 上下班一次未刷卡算旷工 */
	private Integer isAbsenteeism;
	/** 开始休息时间范围 */
	private String restPeriodStart;
	/** 结束休息时间范围 */
	private String restPeriodEnd;
	/** 是否浮动时段 */
	private Integer isFloating;
	/** 浮动时段有效工时 */
	private Integer effectiveWorkingHour;
	/** 上班是否刷卡 */
	private Integer workOnSwipingCard;
	/** 下班是否刷卡 */
	private Integer workOffSwipingCard;
	/** 最早上班考勤时间 */
	private String earliestTime;
	/** 上班时间 */
	private String workOnTime;
	/** 迟到记缺勤时间 */
	private String lateAbsencesTime;
	/** 允许迟到时间(分钟) */
	private Integer allowTheLateTime;
	/** 上班启用自动计算加班 */
	private Integer acoo;
	/** 上班取记录原则 */
	private Integer workOnPrinciple;
	/** 早退记缺勤时间 */
	private String earlyAbsenceTime;
	/** 下班时间 */
	private String workOffTime;
	/** 最晚下班时间 */
	private String lastWorkOffTime;
	/** 允许早退时间(分钟) */
	private Integer allowedEarlyTime;
	/** 下班启用自动算加班 */
	private Integer acwo;
	/** 下班取记录原则 */
	private Integer workOffPrinciple;

	public Double getDiscountDay() {
		return discountDay;
	}

	public void setDiscountDay(Double discountDay) {
		this.discountDay = discountDay;
	}

	public Double getManhour() {
		return manhour;
	}

	public void setManhour(Double manhour) {
		this.manhour = manhour;
	}

	public Double getCatManhour() {
		return catManhour;
	}

	public void setCatManhour(Double catManhour) {
		this.catManhour = catManhour;
	}

	public Integer getIsNextDay() {
		return isNextDay;
	}

	public void setIsNextDay(Integer isNextDay) {
		this.isNextDay = isNextDay;
	}

	public Integer getIsRestPeriod() {
		return isRestPeriod;
	}

	public void setIsRestPeriod(Integer isRestPeriod) {
		this.isRestPeriod = isRestPeriod;
	}

	public Integer getIsAbsenteeism() {
		return isAbsenteeism;
	}

	public void setIsAbsenteeism(Integer isAbsenteeism) {
		this.isAbsenteeism = isAbsenteeism;
	}

	public String getRestPeriodStart() {
		return restPeriodStart;
	}

	public void setRestPeriodStart(String restPeriodStart) {
		this.restPeriodStart = restPeriodStart;
	}

	public String getRestPeriodEnd() {
		return restPeriodEnd;
	}

	public void setRestPeriodEnd(String restPeriodEnd) {
		this.restPeriodEnd = restPeriodEnd;
	}

	public Integer getIsFloating() {
		return isFloating;
	}

	public void setIsFloating(Integer isFloating) {
		this.isFloating = isFloating;
	}

	public Integer getEffectiveWorkingHour() {
		return effectiveWorkingHour;
	}

	public void setEffectiveWorkingHour(Integer effectiveWorkingHour) {
		this.effectiveWorkingHour = effectiveWorkingHour;
	}

	public Integer getWorkOnSwipingCard() {
		return workOnSwipingCard;
	}

	public void setWorkOnSwipingCard(Integer workOnSwipingCard) {
		this.workOnSwipingCard = workOnSwipingCard;
	}

	public Integer getWorkOffSwipingCard() {
		return workOffSwipingCard;
	}

	public void setWorkOffSwipingCard(Integer workOffSwipingCard) {
		this.workOffSwipingCard = workOffSwipingCard;
	}

	public String getEarliestTime() {
		return earliestTime;
	}

	public void setEarliestTime(String earliestTime) {
		this.earliestTime = earliestTime;
	}

	public String getWorkOnTime() {
		return workOnTime;
	}

	public void setWorkOnTime(String workOnTime) {
		this.workOnTime = workOnTime;
	}

	public String getLateAbsencesTime() {
		return lateAbsencesTime;
	}

	public void setLateAbsencesTime(String lateAbsencesTime) {
		this.lateAbsencesTime = lateAbsencesTime;
	}

	public Integer getAllowTheLateTime() {
		return allowTheLateTime;
	}

	public void setAllowTheLateTime(Integer allowTheLateTime) {
		this.allowTheLateTime = allowTheLateTime;
	}

	public Integer getWorkOnPrinciple() {
		return workOnPrinciple;
	}

	public void setWorkOnPrinciple(Integer workOnPrinciple) {
		this.workOnPrinciple = workOnPrinciple;
	}

	public String getEarlyAbsenceTime() {
		return earlyAbsenceTime;
	}

	public void setEarlyAbsenceTime(String earlyAbsenceTime) {
		this.earlyAbsenceTime = earlyAbsenceTime;
	}

	public String getWorkOffTime() {
		return workOffTime;
	}

	public void setWorkOffTime(String workOffTime) {
		this.workOffTime = workOffTime;
	}

	public String getLastWorkOffTime() {
		return lastWorkOffTime;
	}

	public void setLastWorkOffTime(String lastWorkOffTime) {
		this.lastWorkOffTime = lastWorkOffTime;
	}

	public Integer getAllowedEarlyTime() {
		return allowedEarlyTime;
	}

	public void setAllowedEarlyTime(Integer allowedEarlyTime) {
		this.allowedEarlyTime = allowedEarlyTime;
	}

	public Integer getAcoo() {
		return acoo;
	}

	public void setAcoo(Integer acoo) {
		this.acoo = acoo;
	}

	public Integer getAcwo() {
		return acwo;
	}

	public void setAcwo(Integer acwo) {
		this.acwo = acwo;
	}

	public Integer getWorkOffPrinciple() {
		return workOffPrinciple;
	}

	public void setWorkOffPrinciple(Integer workOffPrinciple) {
		this.workOffPrinciple = workOffPrinciple;
	}

}
