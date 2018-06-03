package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.SoldierTuneInfo;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("soldiertuneinfoDomain")
public class SoldierTuneInfoDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取军转情况明细数据
	 */
	public SoldierTuneInfo findSoldierTuneInfoById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(SoldierTuneInfo.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 军转情况明细
	public SoldierTuneInfo merge(SoldierTuneInfo soldierTuneInfo) throws Exception {
		return iEmployeeHrService.merge(soldierTuneInfo);
	}

	// 删除军转情况明细
	public void deleteSoldierTuneInfoEntity(SoldierTuneInfo soldierTuneInfo) throws Exception {
		iEmployeeHrService.deleteByEntity(soldierTuneInfo);
	}
}
