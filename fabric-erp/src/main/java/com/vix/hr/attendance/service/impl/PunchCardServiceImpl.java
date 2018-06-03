package com.vix.hr.attendance.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.hr.attendance.service.IPunchCardService;
import com.vix.hr.organization.entity.Employee;

@Service("punchcardservice")
public class PunchCardServiceImpl extends BaseHibernateServiceImpl implements IPunchCardService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationService organizationService;

	/**
	 * 组织O 部门 D 人员 E
	 */
	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> resList = new LinkedList<OrgUnit>();
		if (treeType.equals("O")) {
			// 查找子公司和子部门信息
			List<Organization> orgList = organizationService.findSubOrganizationList(id);
			Map<String, Object> params = new HashMap<String, Object>();
			List<OrganizationUnit> organizationUnitList = this.findAllSubEntity(OrganizationUnit.class, "organization.id", id, params);
			resList = makeOrgAndUnitTree(orgList, organizationUnitList, null);
		} else if (treeType.equals("D")) {
			Map<String, Object> params = new HashMap<String, Object>();
			List<OrganizationUnit> organizationUnitList = this.findAllSubEntity(OrganizationUnit.class, "parentOrganizationUnit.id", id, params);
			List<Employee> employeeList = this.findAllSubEntity(Employee.class, "organizationUnit.id", id, params);
			resList = makeOrgAndUnitTree(null, organizationUnitList, employeeList);
		}

		return resList;
	}

	private List<OrgUnit> makeOrgAndUnitTree(List<Organization> organizationList, List<OrganizationUnit> organizationUnitList, List<Employee> employeeList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (organizationList != null) {
			for (Organization cp : organizationList) {
				OrgUnit orgUnit = new OrgUnit(cp.getId(), "O", cp.getOrgName());
				List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
				if (cp.getSubOrganizations().size() > 0) {
					for (Organization ot : cp.getSubOrganizations()) {
						subUnitList.add(new OrgUnit(ot.getId(), "O", ot.getOrgName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (cp.getOrganizationUnits().size() > 0) {
					for (OrganizationUnit ou : cp.getOrganizationUnits()) {
						subUnitList.add(new OrgUnit(ou.getId(), "D", ou.getFs()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}

		if (organizationUnitList != null) {
			for (OrganizationUnit organizationUnit : organizationUnitList) {
				OrgUnit orgUnit = new OrgUnit(organizationUnit.getId(), "D", organizationUnit.getFs());
				List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
				if (organizationUnit.getSubOrganizationUnits().size() > 0) {
					for (OrganizationUnit organizationUnit1 : organizationUnit.getSubOrganizationUnits()) {
						subUnitList.add(new OrgUnit(organizationUnit1.getId(), "D", organizationUnit1.getFs()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				Map<String, Object> params = new HashMap<String, Object>();
				try {
					List<Employee> eList = this.findAllSubEntity(Employee.class, "organizationUnit.id", organizationUnit.getId(), params);
					if (eList != null && eList.size() > 0) {
						for (Employee e1 : eList) {
							subUnitList.add(new OrgUnit(e1.getId(), "E", e1.getName()));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				orgUnitList.add(orgUnit);
			}
		}
		if (employeeList != null && employeeList.size() > 0) {

			for (Employee employee : employeeList) {
				OrgUnit orgUnit = new OrgUnit(employee.getId(), "E", employee.getName());
				orgUnitList.add(orgUnit);
			}

		}
		return orgUnitList;
	}

}
