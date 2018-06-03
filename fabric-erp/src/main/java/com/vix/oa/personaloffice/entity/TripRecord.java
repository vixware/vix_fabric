
package com.vix.oa.personaloffice.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: TripRecord
 * @Description: 出差记录
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-4-16 下午5:49:24
 */
public class TripRecord extends BaseEntity {

	private static final long serialVersionUID = 1396865628678655168L;

	/** 外出人 */
	private String outPeople;

	/** 外出原因 */
	private String outReason;

	/** 外出开始时间 */
	private Date outstartDate;

	/** 外出结束日期 */
	private Date outendDate;

	/** 申请时间 */
	private Date applicationTime;

	/** 是否用车 0 否 1 是 */
	private Boolean areCar;

	/** 请假人 */
	private String vacatePeople;

	/** 请假原因 */
	private String vacateReason;

	/** 请假开始时间 */
	private Date vacateStartdate;
	/** 请假结束日期 */
	private Date vacateendDate;

	/** 请假类型 */
	private String vacateType;

	/** 使用年休假 */
	/* private String leave; */

	/** 出差人 */
	private String businessPeople;

	/** 审批人 */
	private String approver;

	/** 出差地点 */
	private String businessLocation;

	/** 出差开始时间 */
	private Date businessstartDate;

	/** 出差结束日期 */
	private Date businessendDate;

	/** 原因 */
	private String reason;

	/** 是否用车 1 否 0是 */
	public Integer isPublish;

	/** 天 */
	private String dates;

	/** 小时 */
	private String minutes;

	/** 申请加班日期 */
	private Date applicationovertimeDate;

	/** 加班开始时间 */
	private Date overtimestartDate;

	/** 加班结束日期 */
	private Date overtimesendDate;

	/** 加班内容 */
	private String overtimeContent;

	/** 上传人id */
	private String uploadPersonId;

	/** id上传人 */
	private String uploadPerson;

	private String uploadPersonName;
	/**
	 * 请假人
	 */
	private Employee employee;
	/**
	 * 审批人
	 */
	private Set<Employee> subEmployees = new HashSet<Employee>();
	/**
	 * 图片
	 */
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();
	/**
	 * 评论
	 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();

	public TripRecord() {
		super();
	}

	public TripRecord(String id) {
		super();
		setId(id);
	}

	public String getVacateStartdateTimeStr() {
		if (null != vacateStartdate) {
			return DateUtil.formatTime(vacateStartdate);
		}
		return "";
	}

	public String getVacateendDateTimeStr() {
		if (null != vacateendDate) {
			return DateUtil.formatTime(vacateendDate);
		}
		return "";
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

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the subEmployees
	 */
	public Set<Employee> getSubEmployees() {
		return subEmployees;
	}

	/**
	 * @param subEmployees
	 *            the subEmployees to set
	 */
	public void setSubEmployees(Set<Employee> subEmployees) {
		this.subEmployees = subEmployees;
	}

	public Boolean getAreCar() {
		return areCar;
	}

	public void setAreCar(Boolean areCar) {
		this.areCar = areCar;
	}

	/**
	 * @return the subWxpQyPictures
	 */
	public Set<WxpQyPicture> getSubWxpQyPictures() {
		return subWxpQyPictures;
	}

	/**
	 * @param subWxpQyPictures
	 *            the subWxpQyPictures to set
	 */
	public void setSubWxpQyPictures(Set<WxpQyPicture> subWxpQyPictures) {
		this.subWxpQyPictures = subWxpQyPictures;
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

	/**
	 * @return the subEvaluationReviews
	 */
	public Set<EvaluationReview> getSubEvaluationReviews() {
		return subEvaluationReviews;
	}

	/**
	 * @param subEvaluationReviews
	 *            the subEvaluationReviews to set
	 */
	public void setSubEvaluationReviews(Set<EvaluationReview> subEvaluationReviews) {
		this.subEvaluationReviews = subEvaluationReviews;
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

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

}
