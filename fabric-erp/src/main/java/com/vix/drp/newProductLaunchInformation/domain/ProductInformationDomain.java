package com.vix.drp.newProductLaunchInformation.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.newProductLaunchInformation.entity.ProductInformation;

@Component("productInformationDomain")
@Transactional
public class ProductInformationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public ProductInformation findProductInformationById(String id) throws Exception {
		return baseHibernateService.findEntityById(ProductInformation.class, id);
	}

	public ChannelDistributor saveOrUpdateChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public ProductInformation saveOrUpdateProductInformation(ProductInformation productInformation) throws Exception {
		return baseHibernateService.merge(productInformation);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteChannelPriceByEntity(ProductInformation productInformation) throws Exception {
		baseHibernateService.deleteByEntity(productInformation);
	}

}
