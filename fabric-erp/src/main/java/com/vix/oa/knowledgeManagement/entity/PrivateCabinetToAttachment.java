package com.vix.oa.knowledgeManagement.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.srm.share.entity.Attachments;

/**
 * 
 * @ClassName: PrivateCabinetToAttachment
 * @Description: 附件关联文件柜
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-25 下午1:50:23
 */
public class PrivateCabinetToAttachment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 文件柜
	 */
	private PrivateCabinet privateCabinet;
	/**
	 * 附件
	 */
	private Attachments attachments;

	

	public PrivateCabinet getPrivateCabinet() {
		return privateCabinet;
	}

	public void setPrivateCabinet(PrivateCabinet privateCabinet) {
		this.privateCabinet = privateCabinet;
	}

	public Attachments getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachments attachments) {
		this.attachments = attachments;
	}

}
