package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.PartPostionInfo;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("partpostioninfoDomain")
public class PartPostionInfoDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取兼任情况明细数据
	 */
	public PartPostionInfo findPartPostionInfoById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(PartPostionInfo.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 兼任情况明细
	public PartPostionInfo merge(PartPostionInfo partPostionInfo) throws Exception {
		return iEmployeeHrService.merge(partPostionInfo);
	}

	// 删除兼任情况明细
	public void deletePartPostionInfoEntity(PartPostionInfo partPostionInfo) throws Exception {
		iEmployeeHrService.deleteByEntity(partPostionInfo);
	}
}
