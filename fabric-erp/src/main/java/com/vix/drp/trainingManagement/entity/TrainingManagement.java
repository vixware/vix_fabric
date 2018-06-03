package com.vix.drp.trainingManagement.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.srm.share.entity.Attachments;

/**
 * 培训活动
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-3
 */
public class TrainingManagement extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 5397106171494273928L;
	/**
	 * 培训内容
	 */
	private String trainingContent;
	/**
	 * 培训时间
	 */
	private Date trainingTime;
	/**
	 * 培训地点
	 */
	private String trainingLocation;
	/* 培训对象 */
	private String trainingObjects;
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public Date getTrainingTime() {
		return trainingTime;
	}

	public void setTrainingTime(Date trainingTime) {
		this.trainingTime = trainingTime;
	}

	public String getTrainingLocation() {
		return trainingLocation;
	}

	public void setTrainingLocation(String trainingLocation) {
		this.trainingLocation = trainingLocation;
	}

	public String getTrainingObjects() {
		return trainingObjects;
	}

	public void setTrainingObjects(String trainingObjects) {
		this.trainingObjects = trainingObjects;
	}

}
