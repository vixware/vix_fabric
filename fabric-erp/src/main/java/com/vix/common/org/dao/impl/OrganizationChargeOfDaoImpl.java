package com.vix.common.org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IOrganizationChargeOfDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationChargeOf;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("organizationChargeOfDao")
public class OrganizationChargeOfDaoImpl extends BaseHibernateDaoImpl implements IOrganizationChargeOfDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationChargeOfDao#findChargeOfByEmpId(java.
	 * lang.String)
	 */
	@Override
	public OrganizationChargeOf findChargeOfByEmpId(String empolyeeId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		hql.append("select organizationChargeOf from ").append(OrganizationChargeOf.class.getName()).append(" organizationChargeOf ");// .append(ename);
		hql.append(" where organizationChargeOf.employee.id = :empolyeeId ");
		params.put("empolyeeId", empolyeeId);

		List<OrganizationChargeOf> empList = findAllByHql2(hql.toString(), params);

		OrganizationChargeOf chargeOfEmp = null;
		if (empList != null && !empList.isEmpty()) {
			if (empList.size() == 1) {
				chargeOfEmp = empList.get(0);
			}
		}
		return chargeOfEmp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationChargeOfDao#findChargeOfOrgByEmpId(
	 * java.lang.String)
	 */
	@Override
	public Set<Organization> findChargeOfOrgByEmpId(String empolyeeId) throws Exception {
		OrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empolyeeId);
		if (chargeOfEmp != null) {
			return chargeOfEmp.getOrganizations();
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.org.dao.IOrganizationChargeOfDao#
	 * findChargeOfOrgUnitByEmpId(java.lang.String)
	 */
	@Override
	public Set<OrganizationUnit> findChargeOfOrgUnitByEmpId(String empolyeeId) throws Exception {
		OrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empolyeeId);
		if (chargeOfEmp != null) {
			return chargeOfEmp.getOrganizationUnits();
		}
		return null;
	}

}
