/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.plan.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.system.billTypeManagement.entity.BillsProperty;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-22
 */
@Component("purchasePlanDomain")
@Transactional
public class PurchasePlanDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
		return p;
	}

	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	public Pager findPurchasePlanPackagePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlanPackage.class, params);
		return p;
	}

	public List<MeasureUnit> findMeasureUnitList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MeasureUnit.class, params);
	}

	public List<PurchasePlanItems> findPurchasePlanItemsList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchasePlanItems.class, params);
	}

	public List<Employee> findEmployeeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Employee.class, params);
	}

	public List<BillsProperty> findBillsPropertyList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(BillsProperty.class, params);
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchasePlan.class, params);
		return p;
	}

	public PurchasePlan findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlan.class, id);
	}

	public PurchasePlanItems findPurchasePlanItemsById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlanItems.class, id);
	}

	public PurchasePlanPackage findPurchasePlanPackageById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlanPackage.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public OrganizationUnit findOrganizationUnitById(String id) throws Exception {
		return baseHibernateService.findEntityById(OrganizationUnit.class, id);
	}

	public PurchasePlan mergePurchasePlan(PurchasePlan purchasePlan) throws Exception {
		return baseHibernateService.merge(purchasePlan);
	}

	public PurchasePlanItems merge(PurchasePlanItems purchasePlanItems) throws Exception {
		return baseHibernateService.merge(purchasePlanItems);
	}

	public void deleteByEntity(PurchasePlan purchasePlan) throws Exception {
		baseHibernateService.deleteByEntity(purchasePlan);
	}

	public PurchasePlanPackage mergePurchasePlanPackage(PurchasePlanPackage purchasePlanPackage) throws Exception {
		return baseHibernateService.merge(purchasePlanPackage);
	}

	/** 索引对象列表 */
	public List<PurchasePlan> findPurchasePlanList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchasePlan.class, params);
	}

	/** 附件 */
	public Attachments merge(Attachments attachments) throws Exception {
		return baseHibernateService.merge(attachments);
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Attachments.class, id);
	}

	public void deleteAttachmentsEntity(Attachments attachments) throws Exception {
		baseHibernateService.deleteByEntity(attachments);
	}

	public void deletePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		baseHibernateService.deleteByEntity(purchasePlanItems);
	}
}
