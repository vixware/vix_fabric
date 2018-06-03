/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.arrivalmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-24
 */
@Component("PurchaseArrivalDomain")
@Transactional
public class PurchaseArrivalDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseArrival.class, params);
		return p;
	}

	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseArrival.class, params);
		return p;
	}

	public PurchaseArrival findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseArrival.class, id);
	}

	public PurchaseArrival merge(PurchaseArrival purchaseArrival) throws Exception {
		return baseHibernateService.merge(purchaseArrival);
	}

	public PurchaseArrivalItems merge(PurchaseArrivalItems purchaseArrivalItems) throws Exception {
		return baseHibernateService.merge(purchaseArrivalItems);
	}

	public void deleteByEntity(PurchaseArrival purchaseArrival) throws Exception {
		baseHibernateService.deleteByEntity(purchaseArrival);
	}

	/** 索引对象列表 */
	public List<PurchaseArrival> findPurchaseArrivalIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseArrival.class, null);
	}

	public Pager findPurchaseOrders(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
		return p;
	}

	public PurchaseOrder findPurchaseOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseOrder.class, id);
	}

}
