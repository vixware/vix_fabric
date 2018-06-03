package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.RetireInfo;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("retireInfoDomain")
public class RetireInfoDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取离退情况明细数据
	 */
	public RetireInfo findRetireInfoById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(RetireInfo.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 离退情况明细
	public RetireInfo merge(RetireInfo retireInfo) throws Exception {
		return iEmployeeHrService.merge(retireInfo);
	}

	// 删除离退情况明细
	public void deleteRetireInfoEntity(RetireInfo retireInfo) throws Exception {
		iEmployeeHrService.deleteByEntity(retireInfo);
	}
}
