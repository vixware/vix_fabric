package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 邮件模板 com.vix.crm.business.entity.EmailTemplate
 *
 * @author zhanghaibing
 *
 * @date 2015年1月27日
 */
public class EmailTemplate extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 是否包含买家昵称
	 */
	private String isBuyerNick;
	/**
	 * 是否包含商品名称
	 */
	private String isGoodsName;
	/**
	 * 是否包含订单编码
	 */
	private String isOrderCode;
	/**
	 * 邮件内容
	 */
	private String emailContent;

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

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

}
