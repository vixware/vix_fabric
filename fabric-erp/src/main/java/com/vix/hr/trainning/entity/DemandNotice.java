package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description:需求通知
 * @author bobchen
 * @date 2015-9-1 下午5:37:41
 */
public class DemandNotice extends BaseEntity {

	private static final long serialVersionUID = 2377411176873416158L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 通知名称 */
	private String noticeTheName;
	/** 通知部门*/
	private OrganizationUnit departmet;
	/** 通知人*/
	private Employee noticePerson;
	/** 通知内容 */
	private String noticeContent;
	/**
	 * 发送部门或人 "O\":\"部门\",\"E\":\"人员\"
	 */
	private String departmentOrPerson;
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	private String pubTypes;
	/** 发布对象的id集合 */
	private String pubIdss;
	/** 通知开始时间 */
	private Date noticeStartTime;
	/** 通知结束时间 */
	private Date noticeEndTime;
	/** 截止时间*/
	private Date finalTime;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
    
	
	public Date getNoticeStartTime() {
		return noticeStartTime;
	}
    
	public String getNoticeStartTimeStr() {
		if (null != noticeStartTime) {
			return DateUtil.format(noticeStartTime);
		}
		return "";
	}
	
	public void setNoticeStartTime(Date noticeStartTime) {
		this.noticeStartTime = noticeStartTime;
	}

	public Date getNoticeEndTime() {
		return noticeEndTime;
	}
    
	public String getNoticeEndTimeStr() {
		if (null != noticeEndTime) {
			return DateUtil.format(noticeEndTime);
		}
		return "";
	}
	
	public Date getFinalTime() {
		return finalTime;
	}
	
	public String getFinalTimeStr() {
		if (null != finalTime) {
			return DateUtil.format(finalTime);
		}
		return "";
	}
	
	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public void setNoticeEndTime(Date noticeEndTime) {
		this.noticeEndTime = noticeEndTime;
	}

	public String getNoticeTheName() {
		return this.noticeTheName;
	}

	public void setNoticeTheName(String noticeTheName) {
		this.noticeTheName = noticeTheName;
	}

	public OrganizationUnit getDepartmet() {
		return departmet;
	}

	public void setDepartmet(OrganizationUnit departmet) {
		this.departmet = departmet;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getDepartmentOrPerson() {
		return departmentOrPerson;
	}

	public void setDepartmentOrPerson(String departmentOrPerson) {
		this.departmentOrPerson = departmentOrPerson;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
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

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubTypes() {
		return pubTypes;
	}

	public void setPubTypes(String pubTypes) {
		this.pubTypes = pubTypes;
	}

	public String getPubIdss() {
		return pubIdss;
	}

	public void setPubIdss(String pubIdss) {
		this.pubIdss = pubIdss;
	}

	public Employee getNoticePerson() {
		return noticePerson;
	}

	public void setNoticePerson(Employee noticePerson) {
		this.noticePerson = noticePerson;
	}

	
}