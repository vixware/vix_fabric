package com.vix.common.org.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IOrganizationChargeOfDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationChargeOf;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationChargeOfService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.hr.organization.entity.Employee;

/**
 * @ClassName: OrganizationChargeOfServiceImpl
 * @Description: 分管公司或者部门
 * @author wangmingchen
 * @date 2014-5-19 下午10:27:22
 * 
 */
@Service("organizationChargeOfService")
@Transactional
public class OrganizationChargeOfServiceImpl extends BaseHibernateServiceImpl implements IOrganizationChargeOfService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationChargeOfDao organizationChargeOfDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#
	 * findChargeOfByEmpId(java.lang.String)
	 */
	@Override
	public OrganizationChargeOf findChargeOfByEmpId(String empolyeeId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		hql.append("select organizationChargeOf from ").append(OrganizationChargeOf.class.getName()).append(" organizationChargeOf ");// .append(ename);
		hql.append(" where organizationChargeOf.employee.id = :empolyeeId ");
		params.put("empolyeeId", empolyeeId);

		List<OrganizationChargeOf> empList = organizationChargeOfDao.findAllByHql2(hql.toString(), params);

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
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#
	 * findChargeOfOrgByEmpId(java.lang.String)
	 */
	@Override
	public Set<Organization> findChargeOfOrgByEmpId(String empolyeeId) throws Exception {
		OrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empolyeeId);
		if (chargeOfEmp != null) {
			return chargeOfEmp.getOrganizations();
		}
		return null;

	}
	@Override
	public List<Organization> findChargeOfOrgListByEmpId(String empolyeeId) throws Exception {
		Set<Organization> orgSet = findChargeOfOrgByEmpId(empolyeeId);
		List<Organization> resList = new LinkedList<Organization>();
		if (orgSet != null) {
			resList.addAll(orgSet);
		}
		return resList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.org.service.IOrganizationChargeOfService#
	 * findChargeOfOrgUnitListByEmpId(java.lang.String)
	 */
	@Override
	public List<OrganizationUnit> findChargeOfOrgUnitListByEmpId(String empolyeeId) throws Exception {
		Set<OrganizationUnit> orgUnitSet = findChargeOfOrgUnitByEmpId(empolyeeId);
		List<OrganizationUnit> resList = new LinkedList<OrganizationUnit>();
		if (orgUnitSet != null) {
			resList.addAll(orgUnitSet);
		}
		return resList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.service.IOrganizationChargeOfService#saveChargeOf(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveChargeOf(String orgType, String empId, String orgId, String orgUnitIds) throws Exception {
		OrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empId);
		// return chargeOfEmp.getOrganizations();
		if (chargeOfEmp == null) {
			chargeOfEmp = new OrganizationChargeOf();
			Employee emp = findEntityById(Employee.class, empId);
			chargeOfEmp.setEmployee(emp);
		}

		if (orgType.equals("C") && StringUtils.isNotEmpty(orgId)) {
			Organization org = findEntityById(Organization.class, orgId);

			Set<Organization> orgs = chargeOfEmp.getOrganizations();
			if (orgs == null)
				orgs = new HashSet<Organization>();

			orgs.add(org);

			chargeOfEmp.setOrganizations(orgs);
			saveOrUpdate(chargeOfEmp);
		} else if (orgType.equals("O")) {
			Set<OrganizationUnit> orgUnits = chargeOfEmp.getOrganizationUnits();
			if (orgUnits == null)
				orgUnits = new HashSet<OrganizationUnit>();

			if (StringUtils.isNotEmpty(orgUnitIds)) {
				String[] orgUnitsArray = StringUtils.split(orgUnitIds, "\\,");
				OrganizationUnit unit = null;
				for (String orgUnitId : orgUnitsArray) {
					if (StringUtils.isNotEmpty(orgUnitId)) {
						unit = findEntityById(OrganizationUnit.class, orgUnitId);
						orgUnits.add(unit);
					}
				}

				chargeOfEmp.setOrganizationUnits(orgUnits);
				saveOrUpdate(chargeOfEmp);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.service.IOrganizationChargeOfService#deleteChargeOf(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteChargeOf(String orgType, String empId, String orgOrUnitId) throws Exception {
		OrganizationChargeOf chargeOfEmp = findChargeOfByEmpId(empId);
		if (chargeOfEmp != null) {
			if (orgType.equals("C")) {
				Organization org = findEntityById(Organization.class, orgOrUnitId);
				Set<Organization> orgs = chargeOfEmp.getOrganizations();
				orgs.remove(org);

				chargeOfEmp.setOrganizations(orgs);
				update(chargeOfEmp);
			} else if (orgType.equals("O")) {
				Set<OrganizationUnit> orgUnits = chargeOfEmp.getOrganizationUnits();
				OrganizationUnit unit = findEntityById(OrganizationUnit.class, orgOrUnitId);
				orgUnits.remove(unit);

				chargeOfEmp.setOrganizationUnits(orgUnits);
				update(chargeOfEmp);
			}
		}
	}

}
