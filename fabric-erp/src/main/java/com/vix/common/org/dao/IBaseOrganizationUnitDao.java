package com.vix.common.org.dao;

import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IBaseOrganizationUnitDao extends IBaseHibernateDao {

	/**
	 * 根据组织机构部门id得到最顶层的部门id
	 * @param oranizationUnitId
	 * @return
	 * @throws Exception
	 */
	BaseOrganization getTopOrganizationUnitByUnitId(String oranizationUnitId) throws Exception;
	
	
	/**
	 * 得到当前登录账户的所在部门
	 * @return
	 * @throws Exception
	 */
	BaseOrganizationUnit findCurrentUserAccountOrgUnit() throws Exception;
}
