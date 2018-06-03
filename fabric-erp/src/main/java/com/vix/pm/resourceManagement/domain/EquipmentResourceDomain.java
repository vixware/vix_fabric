package com.vix.pm.resourceManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.pm.resourceManagement.entity.SortingDevice;

@Component("equipmentResourceDomain")
@Transactional
public class EquipmentResourceDomain extends BaseDomain {

	/** 索引对象列表 */
	public List<SortingDevice> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(SortingDevice.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SortingDevice.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, SortingDevice.class, params);
		return p;
	}

	public SortingDevice findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SortingDevice.class, id);
	}

	public SortingDevice merge(SortingDevice checkListtemplate) throws Exception {
		return baseHibernateService.merge(checkListtemplate);
	}

}