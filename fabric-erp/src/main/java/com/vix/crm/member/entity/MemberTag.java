package com.vix.crm.member.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 会员标签
 * 
 * com.vix.crm.member.entity.MemberTag
 *
 * @author zhanghaibing
 *
 * @date 2015年2月1日
 */
public class MemberTag extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Set<MemberTagDetail> subMemberTagDetails = new HashSet<MemberTagDetail>();
	private Set<MembershipSubdivision> subMembershipSubdivisions = new HashSet<MembershipSubdivision>();
	private Set<CustomerAccount> customerAccounts = new HashSet<CustomerAccount>();

	public Set<MemberTagDetail> getSubMemberTagDetails() {
		return subMemberTagDetails;
	}

	public void setSubMemberTagDetails(Set<MemberTagDetail> subMemberTagDetails) {
		this.subMemberTagDetails = subMemberTagDetails;
	}

	public Set<CustomerAccount> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public Set<MembershipSubdivision> getSubMembershipSubdivisions() {
		return subMembershipSubdivisions;
	}

	public void setSubMembershipSubdivisions(Set<MembershipSubdivision> subMembershipSubdivisions) {
		this.subMembershipSubdivisions = subMembershipSubdivisions;
	}

}
