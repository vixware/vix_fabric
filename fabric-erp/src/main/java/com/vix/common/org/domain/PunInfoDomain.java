package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.PunInfo;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("puninfoDomain")
public class PunInfoDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取惩罚情况明细数据
	 */
	public PunInfo findPunInfolById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(PunInfo.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 惩罚情况明细
	public PunInfo merge(PunInfo punInfo) throws Exception {
		return iEmployeeHrService.merge(punInfo);
	}

	// 删除惩罚情况明细
	public void deletePunInfoEntity(PunInfo punInfo) throws Exception {
		iEmployeeHrService.deleteByEntity(punInfo);
	}
}
