package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.PoliticalStatus;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("politicalstatusDomain")
public class PoliticalStatusDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取政治面貌明细数据
	 */
	public PoliticalStatus findPoliticalStatusById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(PoliticalStatus.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 政治面貌明细
	public PoliticalStatus merge(PoliticalStatus politicalStatus) throws Exception {
		return iEmployeeHrService.merge(politicalStatus);
	}

	// 删除政治面貌明细
	public void deletePoliticalStatusEntity(PoliticalStatus politicalStatus) throws Exception {
		iEmployeeHrService.deleteByEntity(politicalStatus);
	}
}
