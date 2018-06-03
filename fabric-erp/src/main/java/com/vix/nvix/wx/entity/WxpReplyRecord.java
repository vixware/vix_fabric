package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpReplyRecord
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpReplyRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String openId;
	private String fromUserName;
	private Date createTime;

	private WxpUserMessage userMessage;

	private String replyMsg;
	private String receiveMsg;

	private Integer directReply = 0;

	private Integer matchReply = 0;

	public WxpReplyRecord() {
	}

	public WxpReplyRecord(WxpUserMessage message, String replyMsg, String receiveMsg) {
		this.createTime = new Date();
		this.fromUserName = message.getToUserName();
		// this.openId = message.getUser().getOpenId();
		this.openId = message.getUser().getOpenId();

		this.userMessage = message;

		this.receiveMsg = receiveMsg;
		this.replyMsg = replyMsg;

		this.matchReply = message.getMatchReply();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public WxpUserMessage getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(WxpUserMessage userMessage) {
		this.userMessage = userMessage;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public String getReceiveMsg() {
		return receiveMsg;
	}

	public void setReceiveMsg(String receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public Integer getDirectReply() {
		return directReply;
	}

	public void setDirectReply(Integer directReply) {
		this.directReply = directReply;
	}

	public Integer getMatchReply() {
		return matchReply;
	}

	public void setMatchReply(Integer matchReply) {
		this.matchReply = matchReply;
	}

}
