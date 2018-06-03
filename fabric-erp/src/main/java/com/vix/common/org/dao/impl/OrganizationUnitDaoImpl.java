package com.vix.common.org.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IOrganizationUnitDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.organization.entity.Employee;

@Repository("organizationUnitDao")
public class OrganizationUnitDaoImpl extends BaseHibernateDaoImpl implements
		IOrganizationUnitDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationDao#getTopOrganizationUnitByUnitId
	 * (java.lang.String)
	 */
	@Override
	public Organization getTopOrganizationUnitByUnitId(String oranizationUnitId) throws Exception {
		OrganizationUnit organizationUnit = findEntityById(OrganizationUnit.class, oranizationUnitId);
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
	public OrganizationUnit findCurrentUserAccountOrgUnit() throws Exception{
		String empId = SecurityUtil.getCurrentEmpId();
		OrganizationUnit unit = null;
		if(empId!=null){
			Employee emp = findEntityById(Employee.class, empId);
			unit = emp.getOrganizationUnit();
		}
		return unit;
	}

}
