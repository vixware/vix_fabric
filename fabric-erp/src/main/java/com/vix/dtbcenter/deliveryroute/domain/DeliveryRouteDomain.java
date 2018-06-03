/**
 * 
 */
package com.vix.dtbcenter.deliveryroute.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("deliveryRouteDomain")
@Transactional
public class DeliveryRouteDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findDispatchRoutePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DispatchRoute.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findDispatchRoutePagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	public DispatchRoute findDispatchRouteById(String id) throws Exception {
		return baseHibernateService.findEntityById(DispatchRoute.class, id);
	}

	public DispatchRoute mergeDispatchRouter(DispatchRoute dispatchRoute) throws Exception {
		return baseHibernateService.merge(dispatchRoute);
	}

	/** 索引对象列表 */
	public List<DispatchRoute> findDispatchRouteList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DispatchRoute.class, params);
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
