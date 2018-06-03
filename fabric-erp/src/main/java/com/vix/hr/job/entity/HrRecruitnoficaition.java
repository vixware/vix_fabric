package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘征集
 * @author 李大鹏
 * @date 2013-08-07
 */
public class HrRecruitnoficaition extends BaseEntity {

	private static final long serialVersionUID = 744363302777543609L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 通知名称 */
	private String noticeTheName;
	/** 通知部门 */
	private String companyOrDepartment;
	/** 通知内容 */
	private String noticeContent;
	/** 发送部门 */
	private String sendObj;
	/** 发布人 */
	private String issuer;
	/** 通知开始时间 */
	private Date noticeStartTime;
	/** 通知结束时间 */
	private Date noticeEndTime;

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

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
	
	public void setNoticeEndTime(Date noticeEndTime) {
		this.noticeEndTime = noticeEndTime;
	}

	/** default constructor */
	public HrRecruitnoficaition() {
	}

	/** full constructor */

	public String getNoticeTheName() {
		return this.noticeTheName;
	}

	public void setNoticeTheName(String noticeTheName) {
		this.noticeTheName = noticeTheName;
	}

	public String getCompanyOrDepartment() {
		return this.companyOrDepartment;
	}

	public void setCompanyOrDepartment(String companyOrDepartment) {
		this.companyOrDepartment = companyOrDepartment;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getSendObj() {
		return sendObj;
	}

	public void setSendObj(String sendObj) {
		this.sendObj = sendObj;
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
}