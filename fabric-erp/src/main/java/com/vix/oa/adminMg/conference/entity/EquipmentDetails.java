package com.vix.oa.adminMg.conference.entity;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * @ClassName: ApplicationMg
 * @Description: 申请会议 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-20 上午11:07:30
 */
public class EquipmentDetails extends BaseEntity {

	private static final long serialVersionUID = 6113386415996390066L;
	
	/** 中文首字母 */
	private String chineseFirstLetter;
	
	/** 设备名称 */
	private String equipmentName;
	
	/** 上传人id */
	private String uploadPersonId;	
	
	/** id上传人 */
	private String uploadPerson;
	
	private String uploadPersonName;
	
	/** 会议室设备管理*/
	private MeetingDevice meetingDevice;
	
	/** 申请会议  */
	private ApplicationMg applicationMg;

	
	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public EquipmentDetails() {
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
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

	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

}
