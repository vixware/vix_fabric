/**
 * 
 */
package com.vix.drp.distributionsystemrelationship.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.distributionsystemrelationship.service.IDistributionSystemRelationShipService;
import com.vix.hr.organization.entity.Employee;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("distributionSystemRelationShipDomain")
@Transactional
public class DistributionSystemRelationShipDomain extends BaseDomain{

	@Autowired
	private IDistributionSystemRelationShipService distributionSystemRelationShipService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = distributionSystemRelationShipService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findEntityById(String id) throws Exception {
		return distributionSystemRelationShipService.findEntityById(ChannelDistributor.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return distributionSystemRelationShipService.findEntityById(Employee.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		distributionSystemRelationShipService.batchDelete(ChannelDistributor.class, ids);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return distributionSystemRelationShipService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return distributionSystemRelationShipService.merge(channelDistributor);
	}

	public ChannelDistributor mergeChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return distributionSystemRelationShipService.merge(channelDistributor);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		distributionSystemRelationShipService.deleteByEntity(channelDistributor);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return distributionSystemRelationShipService.findAllByConditions(ChannelDistributor.class, params);
	}

	public List<CurrencyType> findCurrencyTypeList(Map<String, Object> params) throws Exception {
		return distributionSystemRelationShipService.findAllByConditions(CurrencyType.class, params);
	}
}
