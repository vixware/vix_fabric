
package com.vix.oa.personaloffice.scHedule.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ScHedule extends BaseEntity{

	private static final long serialVersionUID = 1396865628678655168L;
	
	/**
	 * 外出人
	 */
	private String outPeople;
	/**
	 * 外出原因
	 */
	private String outReason;
	/**
	 * 外出开始时间
	 */
	private Date outstartDate;
	/**
	 * 外出结束日期
	 */
	private Date outendDate;
	/**
	 * 申请时间
	 */
	private Date applicationTime;
	
	/**是否用车    0 否  1 是*/
	private Boolean areCar;
	
	/**
	 * 请假人
	 */
	private String vacatePeople;
	
	/**
	 * 请假原因
	 */
	private String vacateReason;
	
	/**
	 * 请假开始时间
	 */
	private Date vacateendDate;
	
	/**
	 * 请假结束日期
	 */
	private Date vacateStartdate;
	
	/**
	 * 请假类型
	 */
	private String vacateType;
	
	/**
	 * 使用年休假
	 *//*
	private String leave;*/
	
	/**
	 * 出差人
	 */
	private String businessPeople;
	
	/**
	 * 出差地点
	 */
	private String businessLocation;
	
	/**
	 * 出差开始时间
	 */
	private Date businessstartDate;
	
	/**
	 * 出差结束日期
	 */
	private Date businessendDate;
	
	/**
	 * 原因
	 */
	private String reason;
	
	/**
	 * 天
	 */
	private String travelDays;
	
	/**
	 * 申请加班日期
	 */
	private Date applicationovertimeDate;
	
	/**
	 * 加班开始时间
	 */
	private Date overtimestartDate;
	
	/**
	 * 加班结束日期
	 */
	private Date overtimesendDate;
	
	/**
	 * 加班内容
	 */
	private String overtimeContent;

	public ScHedule() {
        super();
    }
    
    public ScHedule(String id) {
        super();
        setId(id);
    }

	public String getOutPeople() {
		return outPeople;
	}

	public void setOutPeople(String outPeople) {
		this.outPeople = outPeople;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public Date getOutstartDate() {
		return outstartDate;
	}

	public void setOutstartDate(Date outstartDate) {
		this.outstartDate = outstartDate;
	}

	public Date getOutendDate() {
		return outendDate;
	}

	public void setOutendDate(Date outendDate) {
		this.outendDate = outendDate;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Boolean getAreCar() {
		return areCar;
	}

	public void setAreCar(Boolean areCar) {
		this.areCar = areCar;
	}

	public String getVacatePeople() {
		return vacatePeople;
	}

	public void setVacatePeople(String vacatePeople) {
		this.vacatePeople = vacatePeople;
	}

	public String getVacateReason() {
		return vacateReason;
	}

	public void setVacateReason(String vacateReason) {
		this.vacateReason = vacateReason;
	}

	public Date getVacateendDate() {
		return vacateendDate;
	}

	public void setVacateendDate(Date vacateendDate) {
		this.vacateendDate = vacateendDate;
	}

	public Date getVacateStartdate() {
		return vacateStartdate;
	}

	public void setVacateStartdate(Date vacateStartdate) {
		this.vacateStartdate = vacateStartdate;
	}

	public String getVacateType() {
		return vacateType;
	}

	public void setVacateType(String vacateType) {
		this.vacateType = vacateType;
	}

	public String getBusinessPeople() {
		return businessPeople;
	}

	public void setBusinessPeople(String businessPeople) {
		this.businessPeople = businessPeople;
	}

	public String getBusinessLocation() {
		return businessLocation;
	}

	public void setBusinessLocation(String businessLocation) {
		this.businessLocation = businessLocation;
	}

	public Date getBusinessstartDate() {
		return businessstartDate;
	}

	public void setBusinessstartDate(Date businessstartDate) {
		this.businessstartDate = businessstartDate;
	}

	public Date getBusinessendDate() {
		return businessendDate;
	}

	public void setBusinessendDate(Date businessendDate) {
		this.businessendDate = businessendDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTravelDays() {
		return travelDays;
	}

	public void setTravelDays(String travelDays) {
		this.travelDays = travelDays;
	}

	public Date getApplicationovertimeDate() {
		return applicationovertimeDate;
	}

	public void setApplicationovertimeDate(Date applicationovertimeDate) {
		this.applicationovertimeDate = applicationovertimeDate;
	}

	public Date getOvertimestartDate() {
		return overtimestartDate;
	}

	public void setOvertimestartDate(Date overtimestartDate) {
		this.overtimestartDate = overtimestartDate;
	}

	public Date getOvertimesendDate() {
		return overtimesendDate;
	}

	public void setOvertimesendDate(Date overtimesendDate) {
		this.overtimesendDate = overtimesendDate;
	}

	public String getOvertimeContent() {
		return overtimeContent;
	}

	public void setOvertimeContent(String overtimeContent) {
		this.overtimeContent = overtimeContent;
	}
	
}
