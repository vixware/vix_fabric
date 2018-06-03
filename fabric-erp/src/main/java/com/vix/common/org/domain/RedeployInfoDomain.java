package com.vix.common.org.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.RedeployInfo;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("redeployInfoDomain")
public class RedeployInfoDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取流动情况明细数据
	 */
	public RedeployInfo findRedeployInfoById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(RedeployInfo.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 流动情况明细
	public RedeployInfo merge(RedeployInfo redeployInfo) throws Exception {
		return iEmployeeHrService.merge(redeployInfo);
	}

	// 删除流动情况明细
	public void deleteRedeployInfoEntity(RedeployInfo redeployInfo) throws Exception {
		iEmployeeHrService.deleteByEntity(redeployInfo);
	}
}
