package com.vix.crm.workbench.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/** 知识库 */
public class Knowledge extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 知识点编码 */
	private String kmCode;
	/** 知识点 */
	private String kmQuestion;
	/** 解答 */
	private String kmAnswer;
	/** 分类 */
	private KnowledgeCategory knowledgeCategory;
	/** 类型 */
	private String kmType;
	/** 大小*/
	private String keSize;
	/** 主题 */
	private String subject;
	/** 授权给*/
	private String assignee;
	/** 发布时间 */
	private Date publishTime;

	public Knowledge() {}

	public String getKmCode() {
		return kmCode;
	}

	public void setKmCode(String kmCode) {
		this.kmCode = kmCode;
	}

	public String getKmQuestion() {
		return kmQuestion;
	}

	public void setKmQuestion(String kmQuestion) {
		this.kmQuestion = kmQuestion;
	}

	public String getKmAnswer() {
		return kmAnswer;
	}

	public void setKmAnswer(String kmAnswer) {
		this.kmAnswer = kmAnswer;
	}

	public KnowledgeCategory getKnowledgeCategory() {
		return knowledgeCategory;
	}

	public void setKnowledgeCategory(KnowledgeCategory knowledgeCategory) {
		this.knowledgeCategory = knowledgeCategory;
	}

	public String getKmType() {
		return kmType;
	}

	public void setKmType(String kmType) {
		this.kmType = kmType;
	}

	public String getKeSize() {
		return keSize;
	}

	public void setKeSize(String keSize) {
		this.keSize = keSize;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
