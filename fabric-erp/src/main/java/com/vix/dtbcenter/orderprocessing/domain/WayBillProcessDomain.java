/**
 * 
 */
package com.vix.dtbcenter.orderprocessing.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.orderprocessing.entity.WayBill;
import com.vix.dtbcenter.orderprocessing.entity.WayBillItem;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("wayBillProcessDomain")
@Transactional
public class WayBillProcessDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findWayBillPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, WayBill.class, params);
		return p;
	}

	public WayBill findWayBillById(String id) throws Exception {
		return baseHibernateService.findEntityById(WayBill.class, id);
	}

	public WayBillItem findWayBillItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(WayBillItem.class, id);
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Pager findItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public WayBill mergeWayBill(WayBill wayBill) throws Exception {
		return baseHibernateService.merge(wayBill);
	}

	public WayBillItem mergeWayBillItem(WayBillItem wayBillItem) throws Exception {
		return baseHibernateService.merge(wayBillItem);
	}

	public void deleteWayBillByEntity(WayBill wayBill) throws Exception {
		baseHibernateService.deleteByEntity(wayBill);
	}

	/** 索引对象列表 */
	public List<WayBill> findWayBillList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(WayBill.class, params);
	}

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		baseHibernateService.evict(obj);
	}
}
