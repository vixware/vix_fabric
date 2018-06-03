package com.vix.purchase.plan.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;

/**
 * 
 * com.vix.purchase.plan.domain.PurchasePackageDomain
 *
 * @author bjitzhang
 *
 * @date 2015年11月12日
 */
@Component("purchaseApproveDomain")
@Transactional
public class PurchaseApproveDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPurchasePlanPackagePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlanPackage.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchasePlan.class, params);
		return p;
	}

	public PurchasePlanPackage findPurchasePlanPackageById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlanPackage.class, id);
	}

	public PurchasePlan findPurchasePlanById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlan.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public PurchasePlanItems findPurchasePlanItemsById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlanItems.class, id);
	}

	public PurchasePlanPackage mergePurchasePlanPackage(PurchasePlanPackage purchasePlanPackage) throws Exception {
		return baseHibernateService.merge(purchasePlanPackage);
	}

	public PurchasePlanItems merge(PurchasePlanItems purchasePlanItems) throws Exception {
		return baseHibernateService.merge(purchasePlanItems);
	}

	public void deleteByEntity(PurchasePlan purchasePlan) throws Exception {
		baseHibernateService.deleteByEntity(purchasePlan);
	}

	/** 索引对象列表 */
	public List<PurchasePlanPackage> findPurchasePlanPackageList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchasePlanPackage.class, params);
	}

	/** 索引对象列表 */
	public List<PurchasePlan> findPurchasePlanList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchasePlan.class, params);
	}

	public void deletePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		baseHibernateService.deleteByEntity(purchasePlanItems);
	}
}
