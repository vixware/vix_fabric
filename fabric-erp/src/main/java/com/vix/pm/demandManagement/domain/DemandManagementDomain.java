package com.vix.pm.demandManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.pm.demandManagement.entity.DemandManagement;

@Component("demandManagementDomain")
@Transactional
public class DemandManagementDomain extends BaseDomain{

	
	/** 索引对象列表 */
	public List<DemandManagement> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(DemandManagement.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				DemandManagement.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, DemandManagement.class, params);
		return p;
	}
	
	public DemandManagement findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(DemandManagement.class, id);
	}
	
	public DemandManagement merge(DemandManagement checkListtemplate) throws Exception {
		return baseHibernateService.merge(checkListtemplate);
	}
	
	
	
}