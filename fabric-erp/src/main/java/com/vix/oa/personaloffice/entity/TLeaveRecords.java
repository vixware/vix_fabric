
package com.vix.oa.personaloffice.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * @ClassName: TLeaveRecords
 * @Description: 请假记录 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-16 下午5:49:12
 */
public class TLeaveRecords extends BaseEntity{

	private static final long serialVersionUID = 1396865628678655168L;
	
	/**外出人*/
	private String outPeople;
	
	/**外出原因*/
	private String outReason;
	
	/**外出开始时间*/
	private Date outstartDate;
	
	/**外出结束日期*/
	private Date outendDate;
	
	/**申请时间*/
	private Date applicationTime;
	
	/**是否用车    0 否  1 是*/
	private Boolean areCar;
	
	/**请假人*/
	private String vacatePeople;
	
	/**请假原因*/
	private String vacateReason;
	
	/**请假开始时间*/
	private Date vacateendDate;
	
	/**请假结束日期*/
	private Date vacateStartdate;
	
	/** 请假类型*/
	private String vacateType;
	
	/** 使用年休假*/
	private String travelDays;
	
	/** 出差人*/
	private String businessPeople;
	
	/** 审批人*/
	private String approver;
	
	/**出差地点*/
	private String businessLocation;
	
	/**出差开始时间*/
	private Date businessstartDate;
	
	/** 出差结束日期*/
	private Date businessendDate;
	
	/** 原因*/
	private String reason;
	
	/** 天*/
	private String dates;
	
	/** 小时*/
	private String minutes;
	
	/**申请加班日期*/
	private Date applicationovertimeDate;
	
	/**加班开始时间*/
	private Date overtimestartDate;
	
	/** 加班结束日期*/
	private Date overtimesendDate;
	
	/**加班内容*/
	private String overtimeContent;
	
	/** 上传人id */
	private String uploadPersonId;	
	
	/** id上传人 */
	private String uploadPerson;
	
	private String uploadPersonName;

	public TLeaveRecords() {
        super();
    }
    
    public TLeaveRecords(String id) {
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
	
	public String getVacateendDateStr(){
		if(null != vacateendDate ){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(vacateendDate);
		}else {
			return "";
		}
	}

	public void setVacateendDate(Date vacateendDate) {
		this.vacateendDate = vacateendDate;
	}

	public Date getVacateStartdate() {
		return vacateStartdate;
	}
	
	public String getVacateStartdateStr(){
		if(null != vacateStartdate ){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(vacateStartdate);
		}else {
			return "";
		}
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

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getTravelDays() {
		return travelDays;
	}

	public void setTravelDays(String travelDays) {
		this.travelDays = travelDays;
	}
	
}
