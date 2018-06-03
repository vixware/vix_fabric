package com.vix.crm.member.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 会员等级 com.vix.crm.member.entity.MemberLevel
 *
 * @author zhanghaibing
 *
 * @date 2015年1月22日
 */
public class MemberLevel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 积分计算类型 1,购买金额 2,存值金额 3,积分
	 */
	private String levelType;
	/**
	 * 购买金额起
	 */
	private Double fromAmount;
	/**
	 * 等级折扣
	 */
	private Double discount;
	/**
	 * 购买金额到
	 */
	private Double toAmount;
	/**
	 * 储值金额起
	 */
	private Double storedFromAmount;
	/**
	 * 储值金额到
	 */
	private Double storedToAmount;
	/**
	 * 积分起
	 */
	private Long fromPoints;
	/**
	 * 积分止
	 */
	private Long toPoints;
	/**
	 * 是否是默认 Y是 F否
	 */
	private String isDefault;
	public Double getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(Double fromAmount) {
		this.fromAmount = fromAmount;
	}

	public Double getToAmount() {
		return toAmount;
	}

	public void setToAmount(Double toAmount) {
		this.toAmount = toAmount;
	}

	public Long getFromPoints() {
		return fromPoints;
	}

	public void setFromPoints(Long fromPoints) {
		this.fromPoints = fromPoints;
	}

	public Long getToPoints() {
		return toPoints;
	}

	public void setToPoints(Long toPoints) {
		this.toPoints = toPoints;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public Double getStoredFromAmount() {
		return storedFromAmount;
	}

	public void setStoredFromAmount(Double storedFromAmount) {
		this.storedFromAmount = storedFromAmount;
	}

	public Double getStoredToAmount() {
		return storedToAmount;
	}

	public void setStoredToAmount(Double storedToAmount) {
		this.storedToAmount = storedToAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}
