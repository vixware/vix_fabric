package com.vix.hr.attendance.service;

import java.util.List;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IPunchCardService extends IBaseHibernateService {
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

}
