package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 短信模板
 * 
 * com.vix.crm.business.entity.MessageTemplate
 *
 * @author zhanghaibing
 *
 * @date 2015年1月2日
 */
public class MessageTemplate extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 买家昵称
	 */
	private String isBuyerNick;
	/**
	 * 商品名称
	 */
	private String isGoodsName;
	/**
	 * 订单编码
	 */
	private String isOrderCode;
	/**
	 * 短信内容
	 */
	private String messageContent;
	/**
	 * 是否添加退订
	 */
	private String isTd;
	/**
	 * 是否屏蔽短信黑名单
	 */
	private String isPb;
	/**
	 * 短信模板类型
	 */
	private MessageTemplateType messageTemplateType;

	public String getIsBuyerNick() {
		return isBuyerNick;
	}

	public void setIsBuyerNick(String isBuyerNick) {
		this.isBuyerNick = isBuyerNick;
	}

	public String getIsGoodsName() {
		return isGoodsName;
	}

	public void setIsGoodsName(String isGoodsName) {
		this.isGoodsName = isGoodsName;
	}

	public String getIsOrderCode() {
		return isOrderCode;
	}

	public void setIsOrderCode(String isOrderCode) {
		this.isOrderCode = isOrderCode;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getIsTd() {
		return isTd;
	}

	public void setIsTd(String isTd) {
		this.isTd = isTd;
	}

	public String getIsPb() {
		return isPb;
	}

	public void setIsPb(String isPb) {
		this.isPb = isPb;
	}

	public MessageTemplateType getMessageTemplateType() {
		return messageTemplateType;
	}

	public void setMessageTemplateType(MessageTemplateType messageTemplateType) {
		this.messageTemplateType = messageTemplateType;
	}

}
