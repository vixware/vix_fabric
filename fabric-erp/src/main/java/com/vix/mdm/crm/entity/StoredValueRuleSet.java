package com.vix.mdm.crm.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 存值规则
 * 
 * @类全名 com.vix.mdm.crm.entity.StoredValueRuleSet
 *
 * @author zhanghaibing
 *
 * @date 2017年10月17日
 */
public class StoredValueRuleSet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 存值金额
	 */
	private Double amount;
	/**
	 * 赠送金额
	 */
	private Double giftAmount;
	/**
	 * 赠送积分
	 */
	private Long giftPoints;
	/**
	 * 赠送次数
	 */
	private Long giftNumber;
	/**
	 * 是否需要授权 0:是  1:否
	 */
	private String ifAuthorize;
	/**
	 * 会员卡类型 1,余额卡 ;2,次卡3,余额+次数
	 */
	private String type;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(Double giftAmount) {
		this.giftAmount = giftAmount;
	}

	public Long getGiftPoints() {
		return giftPoints;
	}

	public void setGiftPoints(Long giftPoints) {
		this.giftPoints = giftPoints;
	}

	public Long getGiftNumber() {
		return giftNumber;
	}

	public void setGiftNumber(Long giftNumber) {
		this.giftNumber = giftNumber;
	}

	public String getIfAuthorize() {
		return ifAuthorize;
	}

	public void setIfAuthorize(String ifAuthorize) {
		this.ifAuthorize = ifAuthorize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
