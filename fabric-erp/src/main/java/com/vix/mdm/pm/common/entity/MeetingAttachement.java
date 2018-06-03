/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 会议附件
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class MeetingAttachement extends BaseEntity {
	/** 附件名称 */
	private String attachmentName;
	/** 存储路径 */
	private String path;
	/** 附件类型 */
	private String attachmentType;
	/** 大小 */
	private String size;

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
