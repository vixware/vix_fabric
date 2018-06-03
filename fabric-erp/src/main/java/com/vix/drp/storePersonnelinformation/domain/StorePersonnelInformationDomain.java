package com.vix.drp.storePersonnelinformation.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;

@Component("storePersonnelInformationDomain")
@Transactional
public class StorePersonnelInformationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Employee.class, params);
		return p;
	}

	public Employee findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public Employee merge(Employee distributorEmployee) throws Exception {
		return baseHibernateService.merge(distributorEmployee);
	}

	public void saveOrUpdate(Employee employee) throws Exception {
		baseHibernateService.saveOrUpdate(employee);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Employee.class, ids);
	}

	public void deleteByEntity(Employee employee) throws Exception {
		baseHibernateService.deleteByEntity(employee);
	}

	/** 索引对象列表 */
	public List<Employee> findEmployeeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Employee.class, params);
	}
}
