package com.vix.oa.adminMg.conference.entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
/**
 * 
 * @ClassName: MeetingRequest
 * @Description: 会议室管理
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-19 下午1:51:10
 */
public class MeetingRequest extends BaseEntity {

	private static final long serialVersionUID = 6113386415996390066L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 会议名称 */
	private String meetingName;
	/** 可容纳人数 */
	private String capacity;
	/** 设备情况 */
	private String equipment;
	/** 所在地点 */
	private String address;
	/** 申请权限(人员) */
	private String jurisdiction;
	/** 可申请开始时间 */
	private Date allowedStartTime;
	/** 可申请结束时间 */
	private Date allowedEndTime;
	/** 会议室状态*/
	public Integer meetingRoomStatus;
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 申请会议 */
	private Set<ApplicationMg> applicationMg = new HashSet<ApplicationMg>();
	/** 设备明细 */
	private Set<EquipmentDetails> equipmentDetails;
	
	private Employee employee;

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public Date getAllowedStartTime() {
		return allowedStartTime;
	}

	public void setAllowedStartTime(Date allowedStartTime) {
		this.allowedStartTime = allowedStartTime;
	}
	
	public String getAllowedStartTimeStr(){
		if(null != allowedStartTime ){
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(allowedStartTime);
		}else {
			return "";
		}
	}
	
	public String getAllowedEndTimeStr(){
		if(null != allowedEndTime ){
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(allowedEndTime);
		}else {
			return "";
		}
	}

	public Date getAllowedEndTime() {
		return allowedEndTime;
	}

	public void setAllowedEndTime(Date allowedEndTime) {
		this.allowedEndTime = allowedEndTime;
	}

	public Integer getMeetingRoomStatus() {
		return meetingRoomStatus;
	}

	public void setMeetingRoomStatus(Integer meetingRoomStatus) {
		this.meetingRoomStatus = meetingRoomStatus;
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

	public Set<ApplicationMg> getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(Set<ApplicationMg> applicationMg) {
		this.applicationMg = applicationMg;
	}

	public Set<EquipmentDetails> getEquipmentDetails() {
		return equipmentDetails;
	}

	public void setEquipmentDetails(Set<EquipmentDetails> equipmentDetails) {
		this.equipmentDetails = equipmentDetails;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
