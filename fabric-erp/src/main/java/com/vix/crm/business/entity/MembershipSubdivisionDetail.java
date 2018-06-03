package com.vix.crm.business.entity;

/**
 * 客户细分明细 com.vix.crm.business.entity.MembershipSubdivisionDetail
 *
 * @author zhanghaibing
 *
 * @date 2015年1月5日
 */
public class MembershipSubdivisionDetail extends BaseCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MembershipSubdivision membershipSubdivision;

	public MembershipSubdivision getMembershipSubdivision() {
		return membershipSubdivision;
	}

	public void setMembershipSubdivision(MembershipSubdivision membershipSubdivision) {
		this.membershipSubdivision = membershipSubdivision;
	}

}
