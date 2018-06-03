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
import com.vix.crm.business.entity.MyAffairs;
import com.vix.crm.business.service.IOrderManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Order;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("myAffairsDomain")
@Transactional
public class MyAffairsDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, Order.class, params);
		return p;
	}

	public Pager findMyAffairsPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, MyAffairs.class, params);
		return p;
	}

	public Order findOrderById(String id) throws Exception {
		return orderManagementService.findEntityById(Order.class, id);
	}

	public MyAffairs findMyAffairsById(String id) throws Exception {
		return orderManagementService.findEntityById(MyAffairs.class, id);
	}

	public MyAffairs saveOrUpdateMyAffairs(MyAffairs myAffairs) throws Exception {
		return orderManagementService.merge(myAffairs);
	}

	public List<ChannelDistributor> findChannelDistributor(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(ChannelDistributor.class, params);
	}

}
