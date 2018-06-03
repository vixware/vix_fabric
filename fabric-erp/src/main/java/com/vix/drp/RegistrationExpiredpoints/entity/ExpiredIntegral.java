/**
 * 
 */
package com.vix.drp.RegistrationExpiredpoints.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;

/**
 * 过期积分
 * 
 * @author zhanghaibing
 * 
 * @date 2014-4-22
 */
public class ExpiredIntegral extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2894749953107543614L;
	/**
	 * 过期积分
	 */
	private Double expiredIntegral;
	/**
	 * 过期时间
	 */
	private Date expiryDate;
	/**
	 * 会员卡
	 */
	private MemberShipCard memberShipCard;

	public Double getExpiredIntegral() {
		return expiredIntegral;
	}

	public void setExpiredIntegral(Double expiredIntegral) {
		this.expiredIntegral = expiredIntegral;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public MemberShipCard getMemberShipCard() {
		return memberShipCard;
	}

	public void setMemberShipCard(MemberShipCard memberShipCard) {
		this.memberShipCard = memberShipCard;
	}

}
