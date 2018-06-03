package com.vix.crm.member.entity;

import com.vix.crm.business.entity.BaseCondition;

/**
 * 会员标签明细 com.vix.crm.member.entity.MemberTagDetail
 *
 * @author zhanghaibing
 *
 * @date 2015年1月30日
 */
public class MemberTagDetail extends BaseCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MemberTag memberTag;

	public MemberTag getMemberTag() {
		return memberTag;
	}

	public void setMemberTag(MemberTag memberTag) {
		this.memberTag = memberTag;
	}

}
