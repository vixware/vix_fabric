package com.vix.drp.advertisingCampaign.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.advertisingCampaign.entity.AdvertisingCampaign;
import com.vix.drp.channel.entity.ChannelDistributor;

@Component("advertisingCampaignDomain")
@Transactional
public class AdvertisingCampaignDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public AdvertisingCampaign findAdvertisingCampaignById(String id) throws Exception {
		return baseHibernateService.findEntityById(AdvertisingCampaign.class, id);
	}

	public ChannelDistributor saveOrUpdateChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public AdvertisingCampaign saveOrUpdateAdvertisingCampaign(AdvertisingCampaign advertisingCampaign) throws Exception {
		return baseHibernateService.merge(advertisingCampaign);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteAdvertisingCampaignByEntity(AdvertisingCampaign advertisingCampaign) throws Exception {
		baseHibernateService.deleteByEntity(advertisingCampaign);
	}

}
