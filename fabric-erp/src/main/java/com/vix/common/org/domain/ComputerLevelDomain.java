package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.ComputerLevel;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("computerlevelDomain")
public class ComputerLevelDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取计算机能力明细数据
	 */
	public ComputerLevel findComputerLevelById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(ComputerLevel.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 计算机能力明细
	public ComputerLevel merge(ComputerLevel computerLevel) throws Exception {
		return iEmployeeHrService.merge(computerLevel);
	}

	// 删除计算机能力明细
	public void deleteComputerLevelEntity(ComputerLevel computerLevel) throws Exception {
		iEmployeeHrService.deleteByEntity(computerLevel);
	}
}
