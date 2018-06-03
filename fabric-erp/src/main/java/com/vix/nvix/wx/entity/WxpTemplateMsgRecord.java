package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTemplateMsgRecord
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpTemplateMsgRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String templateName;
	private String templateId;
	private String templateIdShort;
	private String title;
	private String content;
	private String msgid;
	private String sendMsg;
	private String templateMsgUrl;
	private String openId;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateIdShort() {
		return templateIdShort;
	}

	public void setTemplateIdShort(String templateIdShort) {
		this.templateIdShort = templateIdShort;
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

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public String getTemplateMsgUrl() {
		return templateMsgUrl;
	}

	public void setTemplateMsgUrl(String templateMsgUrl) {
		this.templateMsgUrl = templateMsgUrl;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
