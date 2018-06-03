package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.DisabilityDiseaseInfor;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("disabilitydiseaseinforDomain")
public class DisabilityDiseaseInforDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获伤残病明细数据
	 */
	public DisabilityDiseaseInfor findDisabilityDiseaseInforById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(DisabilityDiseaseInfor.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 伤残病明细
	public DisabilityDiseaseInfor merge(DisabilityDiseaseInfor disabilityDiseaseInfor) throws Exception {
		return iEmployeeHrService.merge(disabilityDiseaseInfor);
	}

	// 删除伤残病明细
	public void deleteDisabilityDiseaseInforEntity(DisabilityDiseaseInfor disabilityDiseaseInfor) throws Exception {
		iEmployeeHrService.deleteByEntity(disabilityDiseaseInfor);
	}
}
