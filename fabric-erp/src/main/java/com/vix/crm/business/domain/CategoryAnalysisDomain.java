/**
 * 
 */
package com.vix.crm.business.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.ExpeditingSetting;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Order;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("categoryAnalysisDomain")
@Transactional
public class CategoryAnalysisDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findSalesOrderPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

	public ExpeditingSetting saveExpeditingSetting(ExpeditingSetting expeditingSetting) throws Exception {
		return baseHibernateService.merge(expeditingSetting);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ChannelDistributor.class, params);
	}
	public List<GoodsSaleInformation> findGoodsSaleInformationList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(GoodsSaleInformation.class, params);
	}

	public List<MessageTemplate> findMessageTemplateList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MessageTemplate.class, params);
	}

	public List<SalesOrder> findSalesOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(SalesOrder.class, params);
	}

}
