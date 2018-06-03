package com.vix.common.org.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Transactional
@Component("employeepostandtieledomain")
public class EmployeePostAndTieleDomain {
	/**
	 * 
	 */
	
	@Autowired
	private IVixntBaseService vixntBaseService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = vixntBaseService.findPagerByOrHqlConditions(pager, Employee.class, params);
		return p;
	}

	public Employee findEntityById(String id) throws Exception {
		return vixntBaseService.findEntityById(Employee.class, id);
	}

	public Employee merge(Employee employee) throws Exception {
		return vixntBaseService.merge(employee);
	}

	public void deleteByEntity(Employee employee) throws Exception {
		vixntBaseService.deleteByEntity(employee);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		vixntBaseService.batchDelete(Employee.class, ids);
	}

	/** 索引对象列表 */
	public List<Employee> findEmployeeIndex() throws Exception {
		return vixntBaseService.findAllByConditions(Employee.class, null);
	}

}
