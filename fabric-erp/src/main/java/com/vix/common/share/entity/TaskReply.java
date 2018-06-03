package com.vix.common.share.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.hr.organization.entity.Employee;

/**
 * 任务回复
 */
public class TaskReply extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 回复人 */
	private Employee replyPerson;
	/** 内容 */
	private String content;
	/** 回复时间 */
	private Date replyTime;
	/** 附件 */
	private Set<CMNAttachment> cMNAttachments = new HashSet<CMNAttachment>();
	
	public TaskReply(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Employee getReplyPerson() {
		return replyPerson;
	}

	public void setReplyPerson(Employee replyPerson) {
		this.replyPerson = replyPerson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Set<CMNAttachment> getcMNAttachments() {
		return cMNAttachments;
	}

	public void setcMNAttachments(Set<CMNAttachment> cMNAttachments) {
		this.cMNAttachments = cMNAttachments;
	}
}
