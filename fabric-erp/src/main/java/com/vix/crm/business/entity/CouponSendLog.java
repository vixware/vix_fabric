package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 
 * com.vix.crm.business.entity.CouponSendLog
 *
 * @author bjitzhang
 *
 * @date 2015年6月12日
 */
public class CouponSendLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private CouponDetail couponDetail;
	private CustomerAccount customerAccount;
	private MembershipSubdivision membershipSubdivision;

	public CouponDetail getCouponDetail() {
		return couponDetail;
	}

	public void setCouponDetail(CouponDetail couponDetail) {
		this.couponDetail = couponDetail;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public MembershipSubdivision getMembershipSubdivision() {
		return membershipSubdivision;
	}

	public void setMembershipSubdivision(MembershipSubdivision membershipSubdivision) {
		this.membershipSubdivision = membershipSubdivision;
	}

}
