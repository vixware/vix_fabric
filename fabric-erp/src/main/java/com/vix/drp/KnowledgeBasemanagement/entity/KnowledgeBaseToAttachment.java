package com.vix.drp.KnowledgeBasemanagement.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.srm.share.entity.Attachments;

/**
 * 附件关联知识库
 * 
 * com.vix.drp.KnowledgeBasemanagement.entity.KnowledgeBaseToAttachment
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-12
 */
public class KnowledgeBaseToAttachment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 知识库
	 */
	private KnowledgeBase knowledgeBase;
	/**
	 * 附件
	 */
	private Attachments attachments;

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public Attachments getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachments attachments) {
		this.attachments = attachments;
	}

}
