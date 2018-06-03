package com.vix.drp.natureAndScaleBranch.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.natureAndScaleBranch.entity.OperatingConditions;
import com.vix.drp.natureAndScaleBranch.entity.PropertiesScale;

@Component("branchOrganizationDomain")
@Transactional
public class BranchOrganizationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public OperatingConditions findOperatingConditionsById(String id) throws Exception {
		return baseHibernateService.findEntityById(OperatingConditions.class, id);
	}

	public PropertiesScale findPropertiesScaleById(String id) throws Exception {
		return baseHibernateService.findEntityById(PropertiesScale.class, id);
	}

	public ChannelDistributor saveOrUpdateChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public OperatingConditions saveOrUpdateOperatingConditions(OperatingConditions operatingConditions) throws Exception {
		return baseHibernateService.merge(operatingConditions);
	}

	public PropertiesScale saveOrUpdatePropertiesScale(PropertiesScale propertiesScale) throws Exception {
		return baseHibernateService.merge(propertiesScale);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteOperatingConditionsByEntity(OperatingConditions operatingConditions) throws Exception {
		baseHibernateService.deleteByEntity(operatingConditions);
	}

	public void deletePropertiesScaleByEntity(PropertiesScale propertiesScale) throws Exception {
		baseHibernateService.deleteByEntity(propertiesScale);
	}

}
