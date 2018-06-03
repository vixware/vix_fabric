package com.vix.common.message.entity;

import java.util.Date;

import com.vix.common.message.factory.MessageTemplateFactory;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 短信发送记录
 * 
 * @类全名 com.vix.common.message.entity.MessageSendRecord
 *
 * @author yhl
 *
 * @date 2017年9月28日
 */
public class MessageSendRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 发送类型 0验证码 1 群发 2 通知
	 */
	private Integer sendType;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 发送时间 */
	private Date postTime;
	/** 发送手机号 */
	private String mobilePhone;
	/** 操作人 */
	private String operator;
	/** 操作账号 */
	private String operatePersonnel;
	/**
	 * 店铺
	 */
	private ChannelDistributor channelDistributor;

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

	public String getMsgContent() {
		return MessageTemplateFactory.getMessageText(content);
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public String getPostTimeStr() {
		if (postTime != null) {
			return DateUtil.formatTime(postTime);
		}
		return "";
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatePersonnel() {
		return operatePersonnel;
	}

	public void setOperatePersonnel(String operatePersonnel) {
		this.operatePersonnel = operatePersonnel;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}