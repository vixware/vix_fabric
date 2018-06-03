/**
 * 
 */
package com.vix.crm.business.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.crm.business.service.IOrderManagementService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("marketingActivitiesDomain")
@Transactional
public class MarketingActivitiesDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findMarketingActivitiesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, MarketingActivities.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findMembershipSubdivisionPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, MembershipSubdivision.class, params);
		return p;
	}

	public MarketingActivities findMarketingActivitiesById(String id) throws Exception {
		return orderManagementService.findEntityById(MarketingActivities.class, id);
	}

	public MembershipSubdivision findMembershipSubdivisionById(String id) throws Exception {
		return orderManagementService.findEntityById(MembershipSubdivision.class, id);
	}

	public List<MembershipSubdivision> findMembershipSubdivisionList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(MembershipSubdivision.class, params);
	}

	public List<MessageTemplate> findMessageTemplateList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(MessageTemplate.class, params);
	}

	public MarketingActivities doSaveMarketingActivities(MarketingActivities marketingActivities) throws Exception {
		return orderManagementService.merge(marketingActivities);
	}

	public MembershipSubdivision doSaveMembershipSubdivision(MembershipSubdivision membershipSubdivision) throws Exception {
		return orderManagementService.merge(membershipSubdivision);
	}

}
