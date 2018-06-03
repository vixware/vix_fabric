package com.vix.common.message.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: Message
 * @Description: 消息
 * @author wangmingchen
 * @date 2012-7-19 下午3:26:04
 * 
 */
public class Message extends BaseEntity {

	/** 消息的来源 */
	private Integer sourceFrom;

	/**
	 * 消息类别 0 沟通 1 通知 2 应答
	 */
	private Integer msgType;
	/** 来源实体 */
	private String sourceClass;
	/** 来源实体的primarykey */
	private String sourceClassPk;
	/** 处理url */
	private String handleUrl;

	/* *//** 发送者 */
	/*
	 * private User sender;
	 *//** 接收者 */
	/*
	 * private User receiver;
	 */
	/** 标题 */
	public String title;
	/** 内容 */
	private String content;

	/** 是否阅读 */
	private Integer isRead;
	/** 阅读时间 */
	private Date readTime;
	/** 是否回复 */
	private Integer isReply;
	/** 回复的原来的消息pk */
	private String replyPk;
	/**
	 * 消息状态 初始状态 0 失效状态 3
	 * */
	private String status;

	public Message() {
		super();
	}

	public Integer getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}

	public String getSourceClassPk() {
		return sourceClassPk;
	}

	public void setSourceClassPk(String sourceClassPk) {
		this.sourceClassPk = sourceClassPk;
	}

	public String getHandleUrl() {
		return handleUrl;
	}

	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}

	/*
	 * public User getSender() { return sender; }
	 * 
	 * public void setSender(User sender) { this.sender = sender; }
	 * 
	 * public User getReceiver() { return receiver; }
	 * 
	 * public void setReceiver(User receiver) { this.receiver = receiver; }
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Integer getIsReply() {
		return isReply;
	}

	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}

	public String getReplyPk() {
		return replyPk;
	}

	public void setReplyPk(String replyPk) {
		this.replyPk = replyPk;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

}
