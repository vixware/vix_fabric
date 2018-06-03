/**
 * 
 */
package com.vix.drp.storeenquiryrequest.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("storeGoodsRequestDomain")
@Transactional
public class StoreGoodsRequestDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
		return p;
	}

	public PurchasePlan findPurchasePlanById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlan.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public PurchasePlanItems findPurchasePlanItemsById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlanItems.class, id);
	}

	public PurchasePlan saveOrUpdatePurchasePlan(PurchasePlan purchasePlan) throws Exception {
		return baseHibernateService.merge(purchasePlan);
	}

	public PurchasePlanItems saveOrUpdatePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		return baseHibernateService.merge(purchasePlanItems);
	}

	public void deleteByEntity(PurchasePlan purchasePlan) throws Exception {
		baseHibernateService.deleteByEntity(purchasePlan);
	}

	public void deletePurchasePlanItemsByEntity(PurchasePlanItems purchasePlanItems) throws Exception {
		baseHibernateService.deleteByEntity(purchasePlanItems);
	}

}
