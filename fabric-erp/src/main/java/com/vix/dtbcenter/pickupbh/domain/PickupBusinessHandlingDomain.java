/**
 * 
 */
package com.vix.dtbcenter.pickupbh.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.pickupbh.entity.LoadBill;
import com.vix.dtbcenter.pickupbh.entity.LoadBillItem;
import com.vix.mdm.item.entity.Item;
/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("pickupBusinessHandlingDomain")
@Transactional
public class PickupBusinessHandlingDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findTakeDeliveryPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, LoadBill.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findTakeDeliveryPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	/** 获取列表数据 */
	public Pager findItemPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public SpecificationDetail findSpecificationDetailEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SpecificationDetail.class, id);
	}

	public LoadBill findLoadBillById(String id) throws Exception {
		return baseHibernateService.findEntityById(LoadBill.class, id);
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Pager findItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public LoadBill mergeLoadBill(LoadBill loadBill) throws Exception {
		return baseHibernateService.merge(loadBill);
	}

	public LoadBillItem mergeLoadBillItem(LoadBillItem loadBillItem) throws Exception {
		return baseHibernateService.merge(loadBillItem);
	}

	/** 索引对象列表 */
	public List<LoadBill> findLoadBillList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(LoadBill.class, params);
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
