package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.Techachieve;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("techachieveDomain")
public class TechachieveDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取专业技术成果明细数据
	 */
	public Techachieve findTechachieveById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Techachieve.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 专业技术成果明细
	public Techachieve merge(Techachieve techachieve) throws Exception {
		return iEmployeeHrService.merge(techachieve);
	}

	// 删除专业技术成果明细
	public void deleteTechachieveEntity(Techachieve techachieve) throws Exception {
		iEmployeeHrService.deleteByEntity(techachieve);
	}
}
