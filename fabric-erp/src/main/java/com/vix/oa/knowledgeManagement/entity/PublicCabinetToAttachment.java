package com.vix.oa.knowledgeManagement.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.srm.share.entity.Attachments;

/**
 * 
 * @ClassName: PublicCabinetToAttachment
 * @Description: 附件关联文件柜
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-27 上午10:45:49
 */
public class PublicCabinetToAttachment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 文件柜
	 */
	private PublicCabinet publicCabinet;
	/**
	 * 附件
	 */
	private Attachments attachments;

	

	public PublicCabinet getPublicCabinet() {
		return publicCabinet;
	}

	public void setPublicCabinet(PublicCabinet publicCabinet) {
		this.publicCabinet = publicCabinet;
	}

	public Attachments getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachments attachments) {
		this.attachments = attachments;
	}

}
