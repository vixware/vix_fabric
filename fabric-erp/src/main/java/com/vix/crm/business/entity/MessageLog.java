package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 短信记录 com.vix.crm.business.entity.MessageLog
 *
 * @author zhanghaibing
 *
 * @date 2014年12月25日
 */
public class MessageLog extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单编码
	 */
	private String orderCode;
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	/**
	 * 催付短信内容
	 */
	private String messageContent;
	/**
	 * 手机号码
	 */
	private String mobilePhone;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
