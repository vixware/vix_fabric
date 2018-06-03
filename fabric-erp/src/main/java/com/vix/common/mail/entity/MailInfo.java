package com.vix.common.mail.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.system.entity.Attachment;

public class MailInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 邮件发送时间
	 */
	private Date mailSendDate;
	/**
	 * 邮件（接收）时间
	 */
	private Date mailReceiveDate;
	/**
	 * -1 临时邮件,需要定时清除 0 草稿 1 已发邮件 2 待发送 3 收邮件 4 垃圾箱(收件删除) 5 已（从垃圾箱）删除
	 */
	private Integer mailType;
	/**
	 * 邮件读取状态状态 0 已读 1 未读
	 */
	private Integer mailStatus;
	/** 发件人地址 */
	private String fromMail;
	/**
	 * 收件人地址 多个用 ; 分割
	 */
	private String toMail;
	/**
	 * 抄送地址 多个用 ; 分割
	 */
	private String toMailCs;
	/**
	 * 秘抄地址 多个用 ; 分割
	 */
	private String toMailMc;
	/** 主题 */
	private String subject;
	/** html内容 */
	private String htmlMsg;
	/**
	 * 附件路径
	 */
	private String path;
	/**
	 * 附件名称
	 */
	private String attachmentName;
	/**
	 * 文本内容
	 */
	private String textMsg;

	/** 邮件附件 */
	private Set<Attachment> attachments;
	/**
	 * 邮箱
	 */
	private PersonalEmail personalEmail;

	public MailInfo() {
		super();
	}

	public MailInfo(String id) {
		super();
		setId(id);
	}

	public Integer getMailType() {
		return mailType;
	}

	public void setMailType(Integer mailType) {
		this.mailType = mailType;
	}

	public Integer getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(Integer mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getToMailCs() {
		return toMailCs;
	}

	public void setToMailCs(String toMailCs) {
		this.toMailCs = toMailCs;
	}

	public String getToMailMc() {
		return toMailMc;
	}

	public void setToMailMc(String toMailMc) {
		this.toMailMc = toMailMc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtmlMsg() {
		return htmlMsg;
	}

	public void setHtmlMsg(String htmlMsg) {
		this.htmlMsg = htmlMsg;
	}

	public String getTextMsg() {
		return textMsg;
	}

	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

	public Date getMailSendDate() {
		return mailSendDate;
	}

	public void setMailSendDate(Date mailSendDate) {
		this.mailSendDate = mailSendDate;
	}

	public Date getMailReceiveDate() {
		return mailReceiveDate;
	}

	public void setMailReceiveDate(Date mailReceiveDate) {
		this.mailReceiveDate = mailReceiveDate;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public PersonalEmail getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(PersonalEmail personalEmail) {
		this.personalEmail = personalEmail;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * @param attachmentName
	 *            the attachmentName to set
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

}
