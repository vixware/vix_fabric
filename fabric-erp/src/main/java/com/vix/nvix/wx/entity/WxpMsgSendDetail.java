package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMsgSendDetail
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpMsgSendDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// private Integer status; //默认0,1
	private Integer isSent; // 默认0,是否发送
	private Integer isDirectMsg; // 默认0,是否直接回复信息，0标示使用微信群发
	private String retCode; // 简单标识发送结果
	private Date createTime; // 创建时间

	private WxpUser user; // 粉丝
	private WxpMsgSend msgSend;

	public Integer getIsSent() {
		return isSent;
	}

	public void setIsSent(Integer isSent) {
		this.isSent = isSent;
	}

	public Integer getIsDirectMsg() {
		return isDirectMsg;
	}

	public void setIsDirectMsg(Integer isDirectMsg) {
		this.isDirectMsg = isDirectMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public WxpUser getUser() {
		return user;
	}

	public void setUser(WxpUser user) {
		this.user = user;
	}

	public WxpMsgSend getMsgSend() {
		return msgSend;
	}

	public void setMsgSend(WxpMsgSend msgSend) {
		this.msgSend = msgSend;
	}
}
