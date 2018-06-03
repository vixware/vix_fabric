package com.vix.common.message.entity;

import com.vix.common.message.factory.MessageTemplateFactory;
import com.vix.common.share.entity.BaseEntity;

/**
 * 短信模板
 * 
 * @类全名 com.vix.common.message.entity.MessageTemplate
 *
 * @author yhl
 *
 * @date 2017年9月28日
 */
public class MessageSendTemplate extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String messageTemplateType;
	private String msgContent;

	public String getMsgContent() {
		return msgContent;
	}

	public String getMsgContentText() {
		return MessageTemplateFactory.getMessageText(msgContent);
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMessageTemplateType() {
		return messageTemplateType;
	}

	public void setMessageTemplateType(String messageTemplateType) {
		this.messageTemplateType = messageTemplateType;
	}

}
