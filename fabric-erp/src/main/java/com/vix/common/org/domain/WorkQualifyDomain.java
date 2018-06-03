package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.WorkQualify;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("workQualifyDomain")
public class WorkQualifyDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取技术工人职业资格明细数据
	 */
	public WorkQualify findWorkQualifyById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(WorkQualify.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 技术工人职业资格明细
	public WorkQualify merge(WorkQualify workQualify) throws Exception {
		return iEmployeeHrService.merge(workQualify);
	}

	// 删除技术工人职业资格明细
	public void deleteWorkQualifyEntity(WorkQualify workQualify) throws Exception {
		iEmployeeHrService.deleteByEntity(workQualify);
	}
}
