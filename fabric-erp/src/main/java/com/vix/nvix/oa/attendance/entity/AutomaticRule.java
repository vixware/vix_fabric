/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         自动套班规则 LAA(Leave and absenteeism 请假和缺勤)
 * 
 */
public class AutomaticRule extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 允许使用自动套班 */
	private Integer isDisables;
	/** 计算请假和缺勤 */
	private Integer calculationLAA;
	/** 回溯匹配最佳刷卡记录 */
	private Integer recall;
	/** 匹配最佳班次 */
	private Integer baseShift;
	/** 排班错误时再使用自动套班 */
	private Integer selfMotion;
	/** 自动匹配所有班次 */
	private Integer selfMotionAll;
	/** 套班开始时间 */
	private Integer nestStartTime;
	/** 套班结束时间 */
	private Integer nestEndTime;
	/** 第一次打卡时间 */
	private Integer fristPunchCard;

	public Integer getIsDisables() {
		return isDisables;
	}

	public void setIsDisables(Integer isDisables) {
		this.isDisables = isDisables;
	}

	public Integer getCalculationLAA() {
		return calculationLAA;
	}

	public void setCalculationLAA(Integer calculationLAA) {
		this.calculationLAA = calculationLAA;
	}

	public Integer getRecall() {
		return recall;
	}

	public void setRecall(Integer recall) {
		this.recall = recall;
	}

	public Integer getBaseShift() {
		return baseShift;
	}

	public void setBaseShift(Integer baseShift) {
		this.baseShift = baseShift;
	}

	public Integer getSelfMotion() {
		return selfMotion;
	}

	public void setSelfMotion(Integer selfMotion) {
		this.selfMotion = selfMotion;
	}

	public Integer getSelfMotionAll() {
		return selfMotionAll;
	}

	public void setSelfMotionAll(Integer selfMotionAll) {
		this.selfMotionAll = selfMotionAll;
	}

	public Integer getNestStartTime() {
		return nestStartTime;
	}

	public void setNestStartTime(Integer nestStartTime) {
		this.nestStartTime = nestStartTime;
	}

	public Integer getNestEndTime() {
		return nestEndTime;
	}

	public void setNestEndTime(Integer nestEndTime) {
		this.nestEndTime = nestEndTime;
	}

	public Integer getFristPunchCard() {
		return fristPunchCard;
	}

	public void setFristPunchCard(Integer fristPunchCard) {
		this.fristPunchCard = fristPunchCard;
	}

}
