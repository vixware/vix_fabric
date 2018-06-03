package com.vix.crm.business.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.member.entity.MemberTag;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 客户细分 com.vix.crm.business.entity.MembershipSubdivision
 *
 * @author zhanghaibing
 *
 * @date 2015年1月5日
 */
public class MembershipSubdivision extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<MembershipSubdivisionDetail> subMembershipSubdivisionDetails = new HashSet<MembershipSubdivisionDetail>();
	private Set<MarketingActivities> subMarketingActivities = new HashSet<MarketingActivities>();
	private Set<MemberTag> subMemberTags = new HashSet<MemberTag>();
	/**
	 * 会员标签
	 */
	private String memberTagName;
	private ChannelDistributor channelDistributor;

	public Set<MemberTag> getSubMemberTags() {
		return subMemberTags;
	}

	public void setSubMemberTags(Set<MemberTag> subMemberTags) {
		this.subMemberTags = subMemberTags;
	}

	public String getMemberTagName() {
		return memberTagName;
	}

	public void setMemberTagName(String memberTagName) {
		this.memberTagName = memberTagName;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Set<MarketingActivities> getSubMarketingActivities() {
		return subMarketingActivities;
	}

	public void setSubMarketingActivities(Set<MarketingActivities> subMarketingActivities) {
		this.subMarketingActivities = subMarketingActivities;
	}

	public Set<MembershipSubdivisionDetail> getSubMembershipSubdivisionDetails() {
		return subMembershipSubdivisionDetails;
	}

	public void setSubMembershipSubdivisionDetails(Set<MembershipSubdivisionDetail> subMembershipSubdivisionDetails) {
		this.subMembershipSubdivisionDetails = subMembershipSubdivisionDetails;
	}
}