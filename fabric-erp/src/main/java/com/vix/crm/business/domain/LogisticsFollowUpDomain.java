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
import com.vix.ebusiness.entity.Shipping;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("logisticsFollowUpDomain")
@Transactional
public class LogisticsFollowUpDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findShippingPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, Shipping.class, params);
		return p;
	}

	public MessageLog doSaveMessageLog(MessageLog messageLog) throws Exception {
		return orderManagementService.merge(messageLog);
	}

	public Shipping doSaveShipping(Shipping shipping) throws Exception {
		return orderManagementService.merge(shipping);
	}

	public Shipping doListShippingById(String id) throws Exception {
		return orderManagementService.findEntityById(Shipping.class, id);
	}

	public MyAffairs doSaveMyAffairs(MyAffairs myAffairs) throws Exception {
		return orderManagementService.merge(myAffairs);
	}

	public List<ChannelDistributor> findChannelDistributor(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(ChannelDistributor.class, params);
	}

}
