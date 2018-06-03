package com.vix.hr.position.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.OrgPosition;
import com.vix.hr.position.service.IOrgPositionService;

@Transactional
@Component("orgpositiondomain")
public class OrgPositionDomain extends BaseDomain{
	@Autowired
	private IOrgPositionService iOrgPositionService;


	public OrgPosition findEntityById(String id) throws Exception {
		return iOrgPositionService.findEntityById(OrgPosition.class, id);
	}
}
