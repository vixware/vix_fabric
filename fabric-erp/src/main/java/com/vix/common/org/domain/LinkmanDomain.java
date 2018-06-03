package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.Linkman;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("linkmanDomain")
public class LinkmanDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取联系人明细数据
	 */
	public Linkman findLinkmanById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Linkman.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 联系人明细
	public Linkman merge(Linkman linkman) throws Exception {
		return iEmployeeHrService.merge(linkman);
	}

	// 删除联系人明细
	public void deleteLinkmanEntity(Linkman linkman) throws Exception {
		iEmployeeHrService.deleteByEntity(linkman);
	}
}
