/**
 * 
 */
package com.vix.dtbcenter.transpotmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.transpotmgr.entity.LoadingManagement;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("loadingManagementDomain")
@Transactional
public class LoadingManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findTruckingOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, LoadingManagement.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findTruckingOrderPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, LoadingManagement.class, params);
		return p;
	}

	public LoadingManagement findTruckingOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(LoadingManagement.class, id);
	}

	public LoadingManagement mergeTruckingOrder(LoadingManagement truckingOrder) throws Exception {
		return baseHibernateService.merge(truckingOrder);
	}

	public void deleteByEntity(LoadingManagement truckingOrder) throws Exception {
		baseHibernateService.deleteByEntity(truckingOrder);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(LoadingManagement.class, ids);
	}

	/** 索引对象列表 */
	public List<LoadingManagement> findTruckingOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(LoadingManagement.class, params);
	}

}
