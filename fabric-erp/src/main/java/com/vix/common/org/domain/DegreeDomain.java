package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.Degree;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("degreeDomain")
public class DegreeDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取学历学位情况明细数据
	 */
	public Degree findDegreeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Degree.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 学历学位情况明细
	public Degree merge(Degree degree) throws Exception {
		return iEmployeeHrService.merge(degree);
	}

	// 删除学历学位情况明细
	public void deleteDegreeEntity(Degree degree) throws Exception {
		iEmployeeHrService.deleteByEntity(degree);
	}
}
