package com.vix.drp.storereceivingrecords.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;

@Component("storeReceiveRecordsDomain")
@Transactional
public class StoreReceiveRecordsDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
		return p;
	}

	public PurchaseOrder findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseOrder.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(PurchaseOrder.class, ids);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public void saveOrUpdate(PurchaseOrder purchaseOrder) throws Exception {
		baseHibernateService.saveOrUpdate(purchaseOrder);
	}

	public void deleteByEntity(PurchaseOrder purchaseOrder) throws Exception {
		baseHibernateService.deleteByEntity(purchaseOrder);
	}

	/** 索引对象列表 */
	public List<PurchaseOrder> findPurchaseOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseOrder.class, params);
	}
}
