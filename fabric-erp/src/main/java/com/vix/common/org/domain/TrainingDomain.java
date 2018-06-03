package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.Training;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("trainingDomain")
public class TrainingDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取培训情况明细数据
	 */
	public Training findTrainingById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Training.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 培训情况明细
	public Training merge(Training training) throws Exception {
		return iEmployeeHrService.merge(training);
	}

	// 删除培训情况明细
	public void deleteTrainingEntity(Training training) throws Exception {
		iEmployeeHrService.deleteByEntity(training);
	}
}
