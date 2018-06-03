/**
 * 
 */
package com.vix.drp.advertisingManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.advertisingManagement.entity.AdvertisingManagement;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("advertisingManagementDomain")
@Transactional
public class AdvertisingManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, AdvertisingManagement.class, params);
		return p;
	}

	public AdvertisingManagement findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(AdvertisingManagement.class, id);
	}

	public AdvertisingManagement mergeAdvertisingManagement(AdvertisingManagement advertisingManagement) throws Exception {
		return baseHibernateService.merge(advertisingManagement);
	}

	public void deleteByEntity(AdvertisingManagement advertisingManagement) throws Exception {
		baseHibernateService.deleteByEntity(advertisingManagement);
	}

	/** 索引对象列表 */
	public List<AdvertisingManagement> findAdvertisingManagementList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(AdvertisingManagement.class, params);
	}
}
