package com.vix.common.message.entity;

import java.util.Date;

import com.vix.common.message.factory.MessageTemplateFactory;
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
public class MessageNotice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 通知类别 0 消费通知 1 生日通知 2纪念日通知 3服务计划提醒 4营业额通知 5来电宝挂机通知
	 */
	private Integer noticeType;
	/**
	 * 消费类别 0开卡 1充值 2消费 4兑换
	 */
	private Integer consumeType;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/**
	 * 消息状态 初始状态 0 失效状态 3
	 * */
	private String status;
	/** 发送时间  */
	private Date postTime;

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public Integer getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(Integer consumeType) {
		this.consumeType = consumeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return MessageTemplateFactory.getMessageText(content);
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
