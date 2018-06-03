package com.vix.oa.adminMg.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class Postil extends BaseEntity {
	private static final long serialVersionUID = -3943610172581798396L;
	/** 批准时间 */
	private Date ratifyTime;
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 计划内容 */
	private String planContent;
	
	/**  */
	private ProjectManagement projectManagement;
	
	public Postil() {
    }
    
   /* public Postil(Long id) {
        super();
        setId(id);
    }*/

	public Date getRatifyTime() {
		return ratifyTime;
	}

	public void setRatifyTime(Date ratifyTime) {
		this.ratifyTime = ratifyTime;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
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

	public ProjectManagement getProjectManagement() {
		return projectManagement;
	}

	public void setProjectManagement(ProjectManagement projectManagement) {
		this.projectManagement = projectManagement;
	}

	
    }
	

	

