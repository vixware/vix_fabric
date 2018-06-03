package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.WorkRecord;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("workrecordDomain")
public class WorkRecordDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取计算机能力明细数据
	 */
	public WorkRecord findWorkRecordById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(WorkRecord.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 工作履历明细
	public WorkRecord merge(WorkRecord workRecord) throws Exception {
		return iEmployeeHrService.merge(workRecord);
	}

	// 删除工作履历明细
	public void deleteWorkRecordEntity(WorkRecord workRecord) throws Exception {
		iEmployeeHrService.deleteByEntity(workRecord);
	}
}
