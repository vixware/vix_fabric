/**
 * 
 */
package com.vix.drp.marketingCampaign.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.marketingCampaign.entity.MarketingCampaign;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignDetail;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignTask;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("marketingCampaignDomain")
@Transactional
public class MarketingCampaignDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MarketingCampaign.class, params);
		return p;
	}

	public MarketingCampaign findMarketingCampaignById(String id) throws Exception {
		return baseHibernateService.findEntityById(MarketingCampaign.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public MarketingCampaign mergeMarketingCampaign(MarketingCampaign marketingCampaign) throws Exception {
		return baseHibernateService.merge(marketingCampaign);
	}

	public MarketingCampaignTask mergeMarketingCampaignTask(MarketingCampaignTask marketingCampaignTask) throws Exception {
		return baseHibernateService.merge(marketingCampaignTask);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(MarketingCampaignTask.class, ids);
	}

	public MarketingCampaignDetail saveOrUpdateMarketingCampaignDetail(MarketingCampaignDetail marketingCampaignDetail) throws Exception {
		return baseHibernateService.merge(marketingCampaignDetail);
	}

	public void deleteByEntity(MarketingCampaign marketingCampaign) throws Exception {
		baseHibernateService.deleteByEntity(marketingCampaign);
	}

	/** 索引对象列表 */
	public List<MarketingCampaign> findMarketingCampaignList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MarketingCampaign.class, params);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ChannelDistributor.class, params);
	}
}
