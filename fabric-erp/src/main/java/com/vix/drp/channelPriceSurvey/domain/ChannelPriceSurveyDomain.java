package com.vix.drp.channelPriceSurvey.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channelPriceSurvey.entity.ChannelPrice;

@Component("channelPriceSurveyDomain")
@Transactional
public class ChannelPriceSurveyDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public ChannelPrice findChannelPriceById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelPrice.class, id);
	}

	public ChannelDistributor saveOrUpdateChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public ChannelPrice saveOrUpdateChannelPrice(ChannelPrice channelPrice) throws Exception {
		return baseHibernateService.merge(channelPrice);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteChannelPriceByEntity(ChannelPrice channelPrice) throws Exception {
		baseHibernateService.deleteByEntity(channelPrice);
	}

}
