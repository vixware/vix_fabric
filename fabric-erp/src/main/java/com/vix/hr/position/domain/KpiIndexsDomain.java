package com.vix.hr.position.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.hr.position.entity.KpiIndexs;

@Transactional
@Component("kpiindexsDomain")
public class KpiIndexsDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/**
	 * 根据id获取KPI指标明细数据
	 */
	public KpiIndexs findKpiIndexsById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(KpiIndexs.class, id);
	}

	public OrgPosition findOrgPositionById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(OrgPosition.class, id);
	}

	// KPI指标明细
	public KpiIndexs merge(KpiIndexs kpiIndexs) throws Exception {
		return iEmployeeHrService.merge(kpiIndexs);
	}

	// 删除KPI指标明细
	public void deleteKpiIndexsEntity(KpiIndexs kpiIndexs) throws Exception {
		iEmployeeHrService.deleteByEntity(kpiIndexs);
	}
}
