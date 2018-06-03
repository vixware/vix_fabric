/**
 * 
 */
package com.vix.drp.marketingCampaignTracking.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignTask;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("marketingCampaignTrackingDomain")
@Transactional
public class MarketingCampaignTrackingDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MarketingCampaignTask.class, params);
		return p;
	}

	public MarketingCampaignTask findMarketingCampaignTaskById(String id) throws Exception {
		return baseHibernateService.findEntityById(MarketingCampaignTask.class, id);
	}

	public MarketingCampaignTask mergeMarketingCampaignTask(MarketingCampaignTask marketingCampaignTask) throws Exception {
		return baseHibernateService.merge(marketingCampaignTask);
	}

	/** 索引对象列表 */
	public List<MarketingCampaignTask> findMarketingCampaignTaskList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MarketingCampaignTask.class, params);
	}

	//Domain
	public void deleteByEntity(MarketingCampaignTask policyInformation) throws Exception {
		baseHibernateService.deleteByEntity(policyInformation);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(MarketingCampaignTask.class, ids);
	}
}
