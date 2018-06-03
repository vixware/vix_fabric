package com.vix.drp.marketingActivity.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.marketingActivity.entity.MarketActivity;

@Component("marketingActivityDomain")
@Transactional
public class MarketingActivityDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public MarketActivity findMarketActivityById(String id) throws Exception {
		return baseHibernateService.findEntityById(MarketActivity.class, id);
	}

	public ChannelDistributor saveOrUpdateChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public MarketActivity saveOrUpdateMarketActivity(MarketActivity marketActivity) throws Exception {
		return baseHibernateService.merge(marketActivity);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteMarketActivityByEntity(MarketActivity marketActivity) throws Exception {
		baseHibernateService.deleteByEntity(marketActivity);
	}

}
