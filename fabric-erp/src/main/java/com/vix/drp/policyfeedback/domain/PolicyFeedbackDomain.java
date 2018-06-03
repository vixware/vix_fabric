package com.vix.drp.policyfeedback.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.policyfeedback.entity.PolicyInformation;

@Component("policyFeedbackDomain")
@Transactional
public class PolicyFeedbackDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPolicyInformationPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PolicyInformation.class, params);
		return p;
	}

	public PolicyInformation findPolicyInformationById(String id) throws Exception {
		return baseHibernateService.findEntityById(PolicyInformation.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	/** 索引对象列表 */
	public List<PolicyInformation> findPolicyInformationList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PolicyInformation.class, params);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(PolicyInformation.class, ids);
	}

	public PolicyInformation saveOrUpdatePolicyInformation(PolicyInformation policyInformation) throws Exception {
		return baseHibernateService.merge(policyInformation);
	}

	public void deleteByEntity(PolicyInformation policyInformation) throws Exception {
		baseHibernateService.deleteByEntity(policyInformation);
	}

}
