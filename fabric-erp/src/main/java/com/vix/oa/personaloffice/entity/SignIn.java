package com.vix.oa.personaloffice.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/*
 考勤打卡实体类
 */
public class SignIn extends BaseEntity {

	// 创建人
	private String creator;
	// 考勤日期
	private Date createTime;
	// 考勤状态
	private String status;
	// 签到时间
	private Date startTime;
	// 签退时间
	private Date endTime;
	// 考勤规则(自身属性)
	private String rule;
	// 排序号(自身属性)
	private String rank;
	// 工作时长（自身属性）
	private String workLength;
	// 是否请假(自身属性)
	private String isLeave;
	// 关键字(自身属性)
	private String keywords;
	// 考勤范围(自身属性)
	private String scope;
	// ip考勤(自身属性)
	private String ip;
	// 提前时间(自身属性)
	private String advanceTime;
	// 延迟时间(自身属性)
	private String delayTime;
	// 限制开始时间(自身属性)
	//private Date limitStartTime;
	// 限制结束时间(自身属性)
	//private Date limitEndTime;
	// 所有人(自身属性)
	private String everyone;
	// 特定对象(自身属性)
	private String targetone;
	//新时间
	//private Date newTime;

	@Override
	public String getCreator() {
		return creator;
	}

	@Override
	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}

	public String getWorkLength() {
		return workLength;
	}

	public void setWorkLength(String workLength) {
		this.workLength = workLength;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAdvanceTime() {
		return advanceTime;
	}

	public void setAdvanceTime(String advanceTime) {
		this.advanceTime = advanceTime;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

/*	public Date getLimitStartTime() {
		return limitStartTime;
	}

	public void setLimitStartTime(Date limitStartTime) {
		this.limitStartTime = limitStartTime;
	}*/

/*	public Date getLimitEndTime() {
		return limitEndTime;
	}

	public void setLimitEndTime(Date limitEndTime) {
		this.limitEndTime = limitEndTime;
	}*/

	public String getEveryone() {
		return everyone;
	}

	public void setEveryone(String everyone) {
		this.everyone = everyone;
	}

	public String getTargetone() {
		return targetone;
	}

	public void setTargetone(String targetone) {
		this.targetone = targetone;
	}

/*	public Date getNewTime() {
		return newTime;
	}

	public void setNewTime(Date newTime) {
		this.newTime = newTime;
	}*/
	
	

}
