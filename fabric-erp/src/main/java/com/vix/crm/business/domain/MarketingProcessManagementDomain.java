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
import com.vix.common.message.entity.MessageSendTemplate;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.EmailTemplate;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.NodesLog;
import com.vix.crm.business.entity.ProcessLog;
import com.vix.crm.business.service.IOrderManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("marketingProcessManagementDomain")
@Transactional
public class MarketingProcessManagementDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findMarketingActivitiesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, MarketingActivities.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findNodesLogPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, NodesLog.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findProcessLogPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, ProcessLog.class, params);
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

	public List<MembershipSubdivision> findMembershipSubdivisionList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(MembershipSubdivision.class, params);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(ChannelDistributor.class, params);
	}

	public List<MessageSendTemplate> findMessageSendTemplateList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(MessageSendTemplate.class, params);
	}

	public List<EmailTemplate> findEmailTemplateList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(EmailTemplate.class, params);
	}

	public List<Coupon> findCouponList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(Coupon.class, params);
	}

	public MembershipSubdivision findMembershipSubdivisionById(String id) throws Exception {
		return orderManagementService.findEntityById(MembershipSubdivision.class, id);
	}

	public MarketingActivities doSaveMarketingActivities(MarketingActivities marketingActivities) throws Exception {
		return orderManagementService.merge(marketingActivities);
	}

	public MembershipSubdivision doSaveMembershipSubdivision(MembershipSubdivision membershipSubdivision) throws Exception {
		return orderManagementService.merge(membershipSubdivision);
	}

}
