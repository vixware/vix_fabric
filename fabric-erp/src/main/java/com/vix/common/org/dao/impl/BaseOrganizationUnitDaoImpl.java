package com.vix.common.org.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IBaseOrganizationUnitDao;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("baseOrganizationUnitDao")
public class BaseOrganizationUnitDaoImpl extends BaseHibernateDaoImpl implements
		IBaseOrganizationUnitDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationDao#getTopOrganizationUnitByUnitId
	 * (java.lang.Long)
	 */
	@Override
	public BaseOrganization getTopOrganizationUnitByUnitId(String oranizationUnitId) throws Exception {
		BaseOrganizationUnit organizationUnit = findEntityById(BaseOrganizationUnit.class, oranizationUnitId);
		if (organizationUnit.getOrganization() == null) {
			// 查找上级部门
			return getTopOrganizationUnitByUnitId(organizationUnit.getParentOrganizationUnit().getId());
		} else {
			// 已经查找到组织机构
			return organizationUnit.getOrganization();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationUnitDao#findCurrentUserAccountOrgUnit()
	 */
	@Override
	public BaseOrganizationUnit findCurrentUserAccountOrgUnit() throws Exception{
		String empId = SecurityUtil.getCurrentEmpId();
		BaseOrganizationUnit unit = null;
		if(empId!=null){
			BaseEmployee emp = findEntityById(BaseEmployee.class, empId);
			unit = emp.getOrganizationUnit();
		}
		return unit;
	}

}
