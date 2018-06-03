package com.vix.hr.hrmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.service.IStaffRosterService;
import com.vix.hr.organization.entity.Employee;

@Component("staffRosterDomain")
@Transactional
public class StaffRosterDomain {

	@Autowired
	private IStaffRosterService iStaffRosterService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iStaffRosterService.findPagerByHqlConditions(pager, Employee.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iStaffRosterService.findPagerByOrHqlConditions(pager, Employee.class, params);
		return p;
	}

	public Employee findEntityById(String id) throws Exception {
		return iStaffRosterService.findEntityById(Employee.class, id);
	}

	public Employee merge(Employee employee) throws Exception {
		return iStaffRosterService.merge(employee);
	}

	public void deleteByEntity(Employee employee) throws Exception {
		iStaffRosterService.deleteByEntity(employee);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iStaffRosterService.batchDelete(Employee.class, ids);
	}

	/** 索引对象列表 */
	public List<Employee> findEmployeeIndex() throws Exception {
		return iStaffRosterService.findAllByConditions(Employee.class, null);
	}

}
