package com.vix.common.org.dao;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IOrganizationUnitDao extends IBaseHibernateDao {

	/**
	 * 根据组织机构部门id得到最顶层的部门id
	 * @param oranizationUnitId
	 * @return
	 * @throws Exception
	 */
	Organization getTopOrganizationUnitByUnitId(String oranizationUnitId) throws Exception;
	
	
	/**
	 * 得到当前登录账户的所在部门
	 * @return
	 * @throws Exception
	 */
	OrganizationUnit findCurrentUserAccountOrgUnit() throws Exception;
}
