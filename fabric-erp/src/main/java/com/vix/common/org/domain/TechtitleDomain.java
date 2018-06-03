package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.Techtitle;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("techtitleDomain")
public class TechtitleDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id专业技术职务明细数据
	 */
	public Techtitle findTechtitleById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Techtitle.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 专业技术职务明细
	public Techtitle merge(Techtitle techtitle) throws Exception {
		return iEmployeeHrService.merge(techtitle);
	}

	// 删除专业技术职务明细
	public void deleteTechtitleEntity(Techtitle techtitle) throws Exception {
		iEmployeeHrService.deleteByEntity(techtitle);
	}
}
