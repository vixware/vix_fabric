/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS任务附件
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class WbstaskAttachement extends BaseEntity {
	/** 任务编码 */
	private String taskCode;
	/** 前置状态 */
	private String taskPreStatus;
	/** 附件名称 */
	private String attachmentName;
	/** 附件类型 */
	private String attachmentType;
	/** 大小 */
	private String size;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskPreStatus() {
		return taskPreStatus;
	}

	public void setTaskPreStatus(String taskPreStatus) {
		this.taskPreStatus = taskPreStatus;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
