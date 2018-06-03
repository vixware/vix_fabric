package com.vix.hr.position.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.position.entity.PerformanceAppraisal;

@Transactional
@Component("performanceappraisalDomain")
public class PerformanceAppraisalDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取绩效指标明细数据
	 */
	public PerformanceAppraisal findPerformanceAppraisalById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(PerformanceAppraisal.class, id);
	}

	public OrgPosition findOrgPositionById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(OrgPosition.class, id);
	}

	// 绩效指标明细
	public PerformanceAppraisal merge(PerformanceAppraisal performanceAppraisal) throws Exception {
		return iEmployeeHrService.merge(performanceAppraisal);
	}

	// 删除绩效指标明细
	public void deletePerformanceAppraisalEntity(PerformanceAppraisalDomain performanceAppraisalDomain) throws Exception {
		iEmployeeHrService.deleteByEntity(performanceAppraisalDomain);
	}
}
