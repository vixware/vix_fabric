/**
 * 
 */
package com.vix.dtbcenter.driver.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.driver.entity.Card;
import com.vix.dtbcenter.driver.entity.Driver;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("driverDomain")
@Transactional
public class DriverDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findDriverPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Driver.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findTakeDeliveryPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	public Driver findDriverById(String id) throws Exception {
		return baseHibernateService.findEntityById(Driver.class, id);
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Pager findItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public Driver mergeDriver(Driver driver) throws Exception {
		return baseHibernateService.merge(driver);
	}

	public Card mergeCard(Card card) throws Exception {
		return baseHibernateService.merge(card);
	}

	public void deleteDriverByEntity(Driver driver) throws Exception {
		baseHibernateService.deleteByEntity(driver);
	}

	/** 索引对象列表 */
	public List<Driver> findDriverList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Driver.class, params);
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
