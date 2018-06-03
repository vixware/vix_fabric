package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.AwardInfo;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("awardinfoDomain")
public class AwardInfoDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取奖励情况明细数据
	 */
	public AwardInfo findAwardInfoById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(AwardInfo.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	/** 奖励情况明细 */
	public AwardInfo merge(AwardInfo awardInfo) throws Exception {
		return iEmployeeHrService.merge(awardInfo);
	}

	/** 删除奖励情况明细 */
	public void deleteAwardInfoEntity(AwardInfo awardInfo) throws Exception {
		iEmployeeHrService.deleteByEntity(awardInfo);
	}
}
