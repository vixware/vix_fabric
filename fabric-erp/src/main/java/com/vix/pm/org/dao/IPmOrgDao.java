package com.vix.pm.org.dao;

import java.util.Set;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.pm.org.entity.PmOrg;

public interface IPmOrgDao extends IBaseHibernateDao {

	/**
	 * 根据部门id得到所有的业务组织
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	Set<PmOrg> findBusinessOrgByOrgUnitId(String orgUnitId)throws Exception;
}
