package com.vix.crm.business.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 营销活动 com.vix.crm.business.entity.MarketingActivities
 *
 * @author zhanghaibing
 *
 * @date 2015年1月6日
 */
public class MarketingActivities extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 活动流程
	 */
	private String activityFlow;
	/**
	 * 会员细分
	 */
	private Set<MembershipSubdivision> subMembershipSubdivisions = new HashSet<MembershipSubdivision>();

	public String getActivityFlow() {
		return activityFlow;
	}

	public void setActivityFlow(String activityFlow) {
		this.activityFlow = activityFlow;
	}

	public Set<MembershipSubdivision> getSubMembershipSubdivisions() {
		return subMembershipSubdivisions;
	}

	public void setSubMembershipSubdivisions(Set<MembershipSubdivision> subMembershipSubdivisions) {
		this.subMembershipSubdivisions = subMembershipSubdivisions;
	}

}
