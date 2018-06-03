package com.vix.crm.business.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.CustomerAccountGroupListView;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.MembershipSubdivisionDetail;
import com.vix.crm.member.entity.MemberTag;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("membershipSubdivisionDomain")
@Transactional
public class MembershipSubdivisionDomain extends BaseDomain{

	@Autowired
	private IBaseHibernateService baseHibernateService;

	/** 获取列表数据 */
	public Pager findMembershipSubdivisionPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MembershipSubdivision.class, params);
		return p;
	}

	public Pager findCustomerAccountGroupListViewPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccountGroupListView.class, params);
		return p;
	}

	public Pager findMemberTagPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MemberTag.class, params);
		return p;
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ChannelDistributor.class, params);
	}
	public List<CustomerAccountGroupListView> findCustomerAccountGroupListViewList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CustomerAccountGroupListView.class, params);
	}

	public List<MemberTag> findMemberTagList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MemberTag.class, params);
	}

	public MembershipSubdivision findMembershipSubdivisionById(String id) throws Exception {
		return baseHibernateService.findEntityById(MembershipSubdivision.class, id);
	}

	public StoreType findStoreTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(StoreType.class, id);
	}

	public MembershipSubdivisionDetail findMembershipSubdivisionDetailById(String id) throws Exception {
		return baseHibernateService.findEntityById(MembershipSubdivisionDetail.class, id);
	}

	public MembershipSubdivision saveMembershipSubdivision(MembershipSubdivision membershipSubdivision) throws Exception {
		return baseHibernateService.merge(membershipSubdivision);
	}

	public MemberTag saveMemberTag(MemberTag memberTag) throws Exception {
		return baseHibernateService.merge(memberTag);
	}

	public void deleteMembershipSubdivisionDetail(MembershipSubdivisionDetail membershipSubdivisionDetail) throws Exception {
		baseHibernateService.deleteByEntity(membershipSubdivisionDetail);
	}

	public MembershipSubdivisionDetail saveMembershipSubdivisionDetail(MembershipSubdivisionDetail membershipSubdivisionDetail) throws Exception {
		return baseHibernateService.merge(membershipSubdivisionDetail);
	}

}
