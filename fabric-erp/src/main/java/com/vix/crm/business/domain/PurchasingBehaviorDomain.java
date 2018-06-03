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
import com.vix.crm.business.entity.ExpeditingSetting;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.ebusiness.entity.Order;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("purchasingBehaviorDomain")
@Transactional
public class PurchasingBehaviorDomain extends BaseDomain{

	@Autowired
	private IPurchasingBehaviorService purchasingBehaviorService;

	/** 获取列表数据 */
	public Pager findSalesOrderPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchasingBehaviorService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public Order findOrderById(String id) throws Exception {
		return purchasingBehaviorService.findEntityById(Order.class, id);
	}

	public ExpeditingSetting saveExpeditingSetting(ExpeditingSetting expeditingSetting) throws Exception {
		return purchasingBehaviorService.merge(expeditingSetting);
	}

	public List<SalesOrder> findSalesOrderList(Map<String, Object> params) throws Exception {
		return purchasingBehaviorService.findAllByConditions(SalesOrder.class, params);
	}

	public List<GoodsSaleInformation> findGoodsSaleInformationList(Map<String, Object> params) throws Exception {
		return purchasingBehaviorService.findAllByConditions(GoodsSaleInformation.class, params);
	}

	public List<MessageTemplate> findMessageTemplateList(Map<String, Object> params) throws Exception {
		return purchasingBehaviorService.findAllByConditions(MessageTemplate.class, params);
	}

}
