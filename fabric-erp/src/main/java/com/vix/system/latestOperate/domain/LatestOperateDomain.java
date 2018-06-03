package com.vix.system.latestOperate.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

@Component("latestOperateDomain")
@Transactional
public class LatestOperateDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, LatestOperateEntity.class, params);
		return p;
	}

	public LatestOperateEntity saveOrUpdateLatestOperateEntity(LatestOperateEntity latestOperateEntity) throws Exception {
		return baseHibernateService.mergeOriginal(latestOperateEntity);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public List<LatestOperateEntity> findLatestOperateEntityList() throws Exception {
		return baseHibernateService.findAllByEntityClass(LatestOperateEntity.class);
	}

}
