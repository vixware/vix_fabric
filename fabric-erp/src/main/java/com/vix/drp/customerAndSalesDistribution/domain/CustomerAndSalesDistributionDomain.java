package com.vix.drp.customerAndSalesDistribution.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.customerAndSalesDistribution.entity.Customer;
import com.vix.drp.customerAndSalesDistribution.entity.SalesDistribution;

@Component("customerAndSalesDistributionDomain")
@Transactional
public class CustomerAndSalesDistributionDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public Customer findCustomerById(String id) throws Exception {
		return baseHibernateService.findEntityById(Customer.class, id);
	}

	public SalesDistribution findSalesDistributionById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesDistribution.class, id);
	}

	public ChannelDistributor saveOrUpdateChannelDistributor(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public Customer saveOrUpdateCustomer(Customer customer) throws Exception {
		return baseHibernateService.merge(customer);
	}

	public SalesDistribution saveOrUpdateSalesDistribution(SalesDistribution salesDistribution) throws Exception {
		return baseHibernateService.merge(salesDistribution);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteCustomerByEntity(Customer customer) throws Exception {
		baseHibernateService.deleteByEntity(customer);
	}

	public void deleteSalesDistributionByEntity(SalesDistribution salesDistribution) throws Exception {
		baseHibernateService.deleteByEntity(salesDistribution);
	}

}
