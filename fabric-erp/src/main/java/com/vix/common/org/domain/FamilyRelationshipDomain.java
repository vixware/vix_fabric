package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.FamilyRelationship;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("familyrelationshipDomain")
public class FamilyRelationshipDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取家庭成员及社会关系明细数据
	 */
	public FamilyRelationship findFamilyRelationshipById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(FamilyRelationship.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 家庭成员及社会关系明细
	public FamilyRelationship merge(FamilyRelationship familyRelationship) throws Exception {
		return iEmployeeHrService.merge(familyRelationship);
	}

	// 删除家庭成员及社会关系明细
	public void deleteFamilyRelationshipEntity(FamilyRelationship familyRelationship) throws Exception {
		iEmployeeHrService.deleteByEntity(familyRelationship);
	}
}
