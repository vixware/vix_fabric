package com.vix.common.message.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 短信通知
 * 
 * @类全名 com.vix.common.message.entity.MessageNotice
 *
 * @author yhl
 *
 * @date 2017年9月28日
 */
public class MessageGroupSend extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 群发类别 0节假日群发 1新店开业群发 2新品上市群发 3会员唤醒群发
	 */
	private Integer sendType;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/**
	 * 消息状态 初始状态 0 失效状态 3
	 * */
	private String status;
	/** 发送时间 */
	private Date postTime;

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

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

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

}
