package com.vix.drp.employeeManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.employeeManagement.service.IEmployeeManagementService;
import com.vix.drp.storePersonnelinformation.entity.ChannelDistributorEmployee;
import com.vix.hr.organization.entity.Employee;

@Component("employeeManagementDomain")
@Transactional
public class EmployeeManagementDomain extends BaseDomain{
	@Autowired
	private IEmployeeManagementService employeeManagementService;

	/** 获取列表数据 */
	public Pager findEmployeePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = employeeManagementService.findPagerByHqlConditions(pager, Employee.class, params);
		return p;
	}

	public Employee findEntityById(String id) throws Exception {
		return employeeManagementService.findEntityById(Employee.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		employeeManagementService.batchDelete(ChannelDistributorEmployee.class, ids);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return employeeManagementService.findEntityById(ChannelDistributor.class, id);
	}

	public ChannelDistributorEmployee merge(ChannelDistributorEmployee distributorEmployee) throws Exception {
		return employeeManagementService.merge(distributorEmployee);
	}

	public void saveOrUpdateEmployee(Employee employee) throws Exception {
		employeeManagementService.merge(employee);
	}

	public void deleteByEntity(Employee employee) throws Exception {
		employeeManagementService.deleteByEntity(employee);
	}

	/** 索引对象列表 */
	public List<Employee> findEmployeeList(Map<String, Object> params) throws Exception {
		return employeeManagementService.findAllByConditions(Employee.class, params);
	}
}
