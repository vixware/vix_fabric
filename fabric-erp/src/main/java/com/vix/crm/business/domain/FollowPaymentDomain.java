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
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("followPaymentDomain")
@Transactional
public class FollowPaymentDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findSalesOrderPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, SalesOrder.class, params);
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
