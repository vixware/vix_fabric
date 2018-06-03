package com.vix.oa.personaloffice.entity;

import com.vix.common.share.entity.BaseEntity;

//假期管理实体类
public class VacationManagement extends BaseEntity{
	
	//成员姓名
	private String member;
    //假期类型
	private String name;
	//年份
	private String years;
	//总时长
	private String totalTime;
	//剩余时长
	private String remainingTime;
	//失效时间
	private String deadTime;
	
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}
	public String getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}

	

}
