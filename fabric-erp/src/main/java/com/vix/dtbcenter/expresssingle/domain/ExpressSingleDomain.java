/**
 * 
 */
package com.vix.dtbcenter.expresssingle.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.expresssingle.entity.ExpressSingle;
import com.vix.dtbcenter.expresssingle.entity.ExpressSingleDetail;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("expressSingleDomain")
@Transactional
public class ExpressSingleDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findExpressSinglePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ExpressSingle.class, params);
		return p;
	}

	public ExpressSingle findExpressSingleById(String id) throws Exception {
		return baseHibernateService.findEntityById(ExpressSingle.class, id);
	}

	public ExpressSingleDetail findExpressSingleDetailById(String id) throws Exception {
		return baseHibernateService.findEntityById(ExpressSingleDetail.class, id);
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Pager findItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public ExpressSingle mergeExpressSingle(ExpressSingle expressSingle) throws Exception {
		return baseHibernateService.merge(expressSingle);
	}

	public ExpressSingleDetail mergeExpressSingleDetail(ExpressSingleDetail expressSingleDetail) throws Exception {
		return baseHibernateService.merge(expressSingleDetail);
	}

	public void deleteExpressSingleByEntity(ExpressSingle expressSingle) throws Exception {
		baseHibernateService.deleteByEntity(expressSingle);
	}

	public void deleteExpressSingleDetailByEntity(ExpressSingleDetail expressSingleDetail) throws Exception {
		baseHibernateService.deleteByEntity(expressSingleDetail);
	}

	/** 索引对象列表 */
	public List<ExpressSingle> findExpressSingleList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ExpressSingle.class, params);
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
