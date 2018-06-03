package com.vix.oa.adminMg.conference.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: ApplicationMg
 * @Description: 申请会议
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-20 上午11:07:30
 */
public class ApplicationMg extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 中文首字母 */
	private String chineseFirstLetter;

	/** 会议室申请状态 */
	public Integer bookingSituation;

	/** 申请人 */
	private String proposer;

	/** 参与人 */
	private String participants;

	/** 会议主题 */
	private String meetingTheme;

	/** 申请会议描述 */
	private String meetingDescription;

	/** 会议开始时间 */
	private Date meetingStartTime;

	/** 会议结束时间 */
	private Date meetingEndTime;

	/** 上传人id */
	private String uploadPersonId;

	/** id上传人 */
	private String uploadPerson;

	private String uploadPersonName;

	/** 会议室管理 */
	private MeetingRequest meetingRequest;

	/** 会议室设备管理 */
	private MeetingDevice meetingDevice;

	/**
	 * 是否为正式数据
	 */
	private Integer isTemps;

	/**
	 * 参与人员类型 "O\":\"部门\",\"E\":\"人员
	 */
	private String pubType;

	/** 设备明细 */
	private Set<EquipmentDetails> equipmentDetails = new HashSet<EquipmentDetails>();
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();

	/**
	 * 发起人
	 */
	private Set<Employee> issuerEmployees = new HashSet<Employee>();
	/**
	 * 参与人
	 */
	private Set<Employee> affiliatedEmployees = new HashSet<Employee>();
	private Set<Uploader> subUploaders = new HashSet<Uploader>();
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();
	private Set<MeetingSummary> subMeetingSummarys = new HashSet<MeetingSummary>();
	/**
	 * 参加人数
	 */
	private Integer arrinliatedNum = 0;
	/**
	 * 请假人数
	 */
	private Integer leaveNum = 0;

	private Employee employee;

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public ApplicationMg() {
	}

	public String getProposer() {
		if (employee != null) {
			return employee.getName();
		} else
			return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public MeetingRequest getMeetingRequest() {
		return meetingRequest;
	}

	public void setMeetingRequest(MeetingRequest meetingRequest) {
		this.meetingRequest = meetingRequest;
	}

	public String getMeetingDescription() {
		return meetingDescription;
	}

	public void setMeetingDescription(String meetingDescription) {
		this.meetingDescription = meetingDescription;
	}

	public Date getMeetingStartTime() {
		return meetingStartTime;
	}

	public void setMeetingStartTime(Date meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
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

	public String getMeetingStartTimeStr() {
		if (null != meetingStartTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(meetingStartTime);
		} else {
			return "";
		}
	}

	public Date getMeetingEndTime() {
		return meetingEndTime;
	}

	public void setMeetingEndTime(Date meetingEndTime) {
		this.meetingEndTime = meetingEndTime;
	}

	public String getMeetingEndTimeStr() {
		if (null != meetingEndTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(meetingEndTime);
		} else {
			return "";
		}
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

	public MeetingDevice getMeetingDevice() {
		return meetingDevice;
	}

	public void setMeetingDevice(MeetingDevice meetingDevice) {
		this.meetingDevice = meetingDevice;
	}

	public Integer getBookingSituation() {
		return bookingSituation;
	}

	public void setBookingSituation(Integer bookingSituation) {
		this.bookingSituation = bookingSituation;
	}

	/**
	 * @return the issuerEmployees
	 */
	public Set<Employee> getIssuerEmployees() {
		return issuerEmployees;
	}

	/**
	 * @param issuerEmployees
	 *            the issuerEmployees to set
	 */
	public void setIssuerEmployees(Set<Employee> issuerEmployees) {
		this.issuerEmployees = issuerEmployees;
	}

	/**
	 * @return the subUploaders
	 */
	public Set<Uploader> getSubUploaders() {
		return subUploaders;
	}

	/**
	 * @param subUploaders
	 *            the subUploaders to set
	 */
	public void setSubUploaders(Set<Uploader> subUploaders) {
		this.subUploaders = subUploaders;
	}

	/**
	 * @return the affiliatedEmployees
	 */
	public Set<Employee> getAffiliatedEmployees() {
		return affiliatedEmployees;
	}

	/**
	 * @param affiliatedEmployees
	 *            the affiliatedEmployees to set
	 */
	public void setAffiliatedEmployees(Set<Employee> affiliatedEmployees) {
		this.affiliatedEmployees = affiliatedEmployees;
	}

	public Set<EquipmentDetails> getEquipmentDetails() {
		return equipmentDetails;
	}

	public void setEquipmentDetails(Set<EquipmentDetails> equipmentDetails) {
		this.equipmentDetails = equipmentDetails;
	}

	public String getParticipants() {
		String participant = "";
		if (getAffiliatedEmployees() != null && getAffiliatedEmployees().size() > 0) {
			for (Employee employee : getAffiliatedEmployees()) {
				if (employee != null) {
					participant += "," + employee.getName();
				}
			}
		}
		if (StringUtils.isNotEmpty(participant)) {
			return participant;
		} else
			return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getMeetingTheme() {
		return meetingTheme;
	}

	public void setMeetingTheme(String meetingTheme) {
		this.meetingTheme = meetingTheme;
	}

	public Integer getIsTemps() {
		return isTemps;
	}

	public void setIsTemps(Integer isTemps) {
		this.isTemps = isTemps;
	}

	/**
	 * @return the arrinliatedNum
	 */
	public Integer getArrinliatedNum() {
		return arrinliatedNum;
	}

	/**
	 * @param arrinliatedNum
	 *            the arrinliatedNum to set
	 */
	public void setArrinliatedNum(Integer arrinliatedNum) {
		this.arrinliatedNum = arrinliatedNum;
	}

	/**
	 * @return the leaveNum
	 */
	public Integer getLeaveNum() {
		return leaveNum;
	}

	/**
	 * @param leaveNum
	 *            the leaveNum to set
	 */
	public void setLeaveNum(Integer leaveNum) {
		this.leaveNum = leaveNum;
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

	public Set<MeetingSummary> getSubMeetingSummarys() {
		return subMeetingSummarys;
	}

	public void setSubMeetingSummarys(Set<MeetingSummary> subMeetingSummarys) {
		this.subMeetingSummarys = subMeetingSummarys;
	}

}
