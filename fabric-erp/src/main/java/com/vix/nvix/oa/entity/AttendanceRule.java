/**
 * 
 */
package com.vix.nvix.oa.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow
 * 2016年5月28日
 * 
 * 考勤规则类型
 */
public class AttendanceRule extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 考勤类型*/
	private String ruleType;
	/** 考勤开始时间*/
	private String startTimes;
	/** 考勤结束时间*/
	private String endTimes;
	/** 状态*/
	private String isEnable;

	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(String startTimes) {
		this.startTimes = startTimes;
	}
	public String getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
}
