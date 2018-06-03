package com.vix.drp.collectManagement.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.collectManagement.entity.CollectBill;
import com.vix.sales.order.entity.SalesOrder;

@Component("collectManagementDomain")
@Transactional
public class CollectManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CollectBill.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findSalesOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public CollectBill findCollectBillById(String id) throws Exception {
		return baseHibernateService.findEntityById(CollectBill.class, id);
	}

	public void saveOrUpdate(CollectBill collectBill) throws Exception {
		baseHibernateService.saveOrUpdate(collectBill);
	}

	public void deleteByEntity(CollectBill collectBill) throws Exception {
		baseHibernateService.deleteByEntity(collectBill);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CollectBill.class, ids);
	}

	/** 索引对象列表 */
	public List<CollectBill> findCollectBillIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return baseHibernateService.findAllByConditions(CollectBill.class, params);
	}
}
