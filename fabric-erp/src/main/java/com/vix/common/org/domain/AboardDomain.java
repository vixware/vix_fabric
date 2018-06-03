package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.Aboard;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("aboardDomain")
public class AboardDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取出国（出境）情况明细数据
	 */
	public Aboard findAboardById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Aboard.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	/** 出国（出境）情况明细 */
	public Aboard merge(Aboard aboard) throws Exception {
		return iEmployeeHrService.merge(aboard);
	}

	/** 删除出国（出境）情况明细 */
	public void deleteAboardEntity(Aboard aboard) throws Exception {
		iEmployeeHrService.deleteByEntity(aboard);
	}
}
