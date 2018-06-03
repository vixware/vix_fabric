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
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.MessageLog;
import com.vix.crm.business.entity.MyAffairs;
import com.vix.crm.business.service.IOrderManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("membershipIntegrationDomain")
@Transactional
public class MembershipIntegrationDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findCustomerAccountPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public Pager findLatestOperateEntityPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, LatestOperateEntity.class, params);
		return p;
	}

	public Pager findCustomerPager(Map<String, Object> params, Pager pager) throws Exception {
		/*
		 * StringBuilder hql =
		 * orderManagementHqlProvider.findCustomerHql(params);
		 */
		Pager p = orderManagementService.findPagerByHqlConditions(pager, BusinessCustomer.class, params);
		return p;
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return orderManagementService.findEntityById(SalesOrder.class, id);
	}

	public MessageLog findMessageLogById(String id) throws Exception {
		return orderManagementService.findEntityById(MessageLog.class, id);
	}

	public MessageLog doSaveMessageLog(MessageLog messageLog) throws Exception {
		return orderManagementService.merge(messageLog);
	}

	public MyAffairs doSaveMyAffairs(MyAffairs myAffairs) throws Exception {
		return orderManagementService.merge(myAffairs);
	}

	public List<ChannelDistributor> findChannelDistributor(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(ChannelDistributor.class, params);
	}

}
