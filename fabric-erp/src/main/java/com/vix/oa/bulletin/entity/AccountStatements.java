package com.vix.oa.bulletin.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

public class AccountStatements extends BaseEntity {

	private static final long serialVersionUID = -2460679357978359820L;

	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;

	/** 登录人姓名 */
	private String uploadPersonName;

	/** 登录人部门 */
	private String uploadDepartments;

	/** 是否阅读 0 未读 1 已读 */
	public Integer isPublish;

	/** 公告通知 */
	private AnnouncementNotification announcementNotification;
	/**
	 * 员工
	 */
	private Employee employee;

	public AccountStatements() {
		super();
	}

	public AccountStatements(String id) {
		super();
		setId(id);
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

	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	public String getUploadDepartments() {
		return uploadDepartments;
	}

	public void setUploadDepartments(String uploadDepartments) {
		this.uploadDepartments = uploadDepartments;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
