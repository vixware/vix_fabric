package com.vix.drp.accountmanagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.OrganizationUnitHqlProvider;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.distributermanagement.hql.ChannelDistributorHqlProvider;
import com.vix.hr.organization.entity.Employee;

@Service("accountManagementService")
public class AccountManagementServiceImpl extends BaseHibernateServiceImpl implements IAccountManagementService {

	/**
	 *
	 */
	private static final long serialVersionUID = -6687113583090063720L;
	@Autowired
	private IBaseHibernateDao baseHibernateDao;
	@Autowired
	private IUserAccountDao userAccountDao;
	@Autowired
	private IOrganizationService organizationService;
	@Resource(name = "organizationUnitHqlProvider")
	private OrganizationUnitHqlProvider organizationUnitHqlProvider;
	@Resource(name = "channelDistributorHqlProvider")
	private ChannelDistributorHqlProvider channelDistributorHqlProvider;

	public List<OrganizationUnit> findSubOrganizationUnitListByOrgId(Long orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = organizationUnitHqlProvider.findOrgUnitList(params);
		List<OrganizationUnit> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	/**
	 * 拼装公司和部门的树结构
	 */
	private List<OrgUnit> makeOrgAndUnitTree(List<Organization> organizationList, List<ChannelDistributor> channelDistributorList, List<Employee> employeeList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (organizationList != null) {
			for (Organization cp : organizationList) {
				OrgUnit orgUnit = new OrgUnit(cp.getId(), "C", cp.getOrgName());
				if (cp.getSubOrganizations().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (Organization ot : cp.getSubOrganizations()) {
						subUnitList.add(new OrgUnit(ot.getId(), "C", ot.getOrgName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (cp.getChannelDistributors().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (ChannelDistributor ou : cp.getChannelDistributors()) {
						subUnitList.add(new OrgUnit(ou.getId(), "CH", ou.getName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}

				orgUnitList.add(orgUnit);
			}
		}

		if (channelDistributorList != null) {
			for (ChannelDistributor channelDistributor : channelDistributorList) {
				OrgUnit orgUnit = new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName());

				if (channelDistributor.getSubChannelDistributors().size() > 0) {

					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (ChannelDistributor ou : channelDistributor.getSubChannelDistributors()) {
						subUnitList.add(new OrgUnit(ou.getId(), "CH", ou.getName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (channelDistributor.getEmployees().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (Employee employee : channelDistributor.getEmployees()) {
						subUnitList.add(new OrgUnit(employee.getId(), "E", employee.getName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
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

	private List<OrgUnit> makeOrgAndUnitAndEmpTree(List<Organization> organizationList, List<OrganizationUnit> organizationUnitList, List<Employee> employeeList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (organizationList != null) {
			for (Organization cp : organizationList) {
				OrgUnit orgUnit = new OrgUnit(cp.getId(), "C", cp.getOrgName());
				if (cp.getSubOrganizations().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (Organization ot : cp.getSubOrganizations()) {
						subUnitList.add(new OrgUnit(ot.getId(), "C", ot.getOrgName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (cp.getOrganizationUnits().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (OrganizationUnit ou : cp.getOrganizationUnits()) {
						subUnitList.add(new OrgUnit(ou.getId(), "O", ou.getFullName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}

				orgUnitList.add(orgUnit);
			}
		}

		if (organizationUnitList != null) {
			for (OrganizationUnit organizationUnit : organizationUnitList) {
				OrgUnit orgUnit = new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFullName());

				if (organizationUnit.getSubOrganizationUnits().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (OrganizationUnit ou : organizationUnit.getSubOrganizationUnits()) {
						subUnitList.add(new OrgUnit(ou.getId(), "O", ou.getFullName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (employeeList != null && employeeList.size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (Employee employee : employeeList) {
						subUnitList.add(new OrgUnit(employee.getId(), "E", employee.getName()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}
		/*	if (employeeList != null && employeeList.size() > 0) {

				for (Employee employee : employeeList) {
					OrgUnit orgUnit = new OrgUnit(employee.getId(), "E", employee.getName());
					orgUnitList.add(orgUnit);
				}

			}*/
		return orgUnitList;
	}

	/**
	 * 根据公司ID获取其下的分销体系结构
	 * 
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelDistributor> findChannelDistributorList(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = channelDistributorHqlProvider.findChannelDistributorList(params);
		List<ChannelDistributor> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	public List<OrganizationUnit> findOrganizationUnitList(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = channelDistributorHqlProvider.findOrganizationUnitList(params);
		List<OrganizationUnit> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	/**
	 * 
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelDistributor> findChannelDistributorListById(Long channelDistributorId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("channelDistributorId", channelDistributorId);
		StringBuilder hql = channelDistributorHqlProvider.findChannelDistributorListById(params);
		List<ChannelDistributor> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> resList = new LinkedList<OrgUnit>();
		if (treeType.equals("C")) {
			// 查找子公司和子部门信息
			List<Organization> orgList = organizationService.findSubOrganizationList(id);
			List<ChannelDistributor> channelDistributorList = findChannelDistributorList(id);
			resList = makeOrgAndUnitTree(orgList, channelDistributorList, null);
		} else if (treeType.equals("CH")) {
			ChannelDistributor channelDistributor = baseHibernateDao.findEntityById(ChannelDistributor.class, id);
			List<Employee> employeeList = new ArrayList<Employee>();
			Set<Employee> employees = channelDistributor.getEmployees();
			if (employees != null && employees.size() > 0) {
				for (Employee employee : employees) {
					employeeList.add(employee);
				}
			}
			List<ChannelDistributor> cList = new ArrayList<ChannelDistributor>();
			if (channelDistributor.getSubChannelDistributors() != null && channelDistributor.getSubChannelDistributors().size() > 0) {
				for (ChannelDistributor c : channelDistributor.getSubChannelDistributors()) {
					cList.add(c);
				}
			}

			resList = makeOrgAndUnitTree(null, cList, employeeList);
		}

		return resList;
	}

	/**
	 * 查询渠道,分销商,经销商,门店的信息
	 */
	@Override
	public Pager findOrganizationPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = channelDistributorHqlProvider.findOrgPager(reqParams, pager);
		pager = baseHibernateDao.findPager2ByHql(pager, channelDistributorHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public UserAccount saveOrUpdate(String addRoleIds, String delRoleIds, String accountId, String accountBizType, String account, String password, String employeeId, String enable, String companyCode) throws Exception {
		return userAccountDao.saveOrUpdate(addRoleIds, delRoleIds, accountId, accountBizType, account, password, employeeId, enable, companyCode);
	}
	
	@Override
	public UserAccount saveOrUpdateUserAccount(String account, String password, String employeeId, String enable) throws Exception {
		return userAccountDao.saveOrUpdateUserAccount(account, password, employeeId, enable);
	}

	@Override
	public List<OrgUnit> findEmpTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> resList = new LinkedList<OrgUnit>();
		if (treeType.equals("C")) {
			// 查找子公司和子部门信息
			List<Organization> orgList = organizationService.findSubOrganizationList(id);
			List<OrganizationUnit> organizationUnitList = findOrganizationUnitList(id);
			resList = makeOrgAndUnitAndEmpTree(orgList, organizationUnitList, null);
		} else if (treeType.equals("O")) {
			OrganizationUnit organizationUnit = baseHibernateDao.findEntityById(OrganizationUnit.class, id);
			List<Employee> employeeList = baseHibernateDao.findAllByEntityClassAndAttribute(Employee.class, "organizationUnit.id", id);
			List<OrganizationUnit> cList = new ArrayList<OrganizationUnit>();
			if (organizationUnit.getSubOrganizationUnits() != null && organizationUnit.getSubOrganizationUnits().size() > 0) {
				for (OrganizationUnit c : organizationUnit.getSubOrganizationUnits()) {
					cList.add(c);
				}
			}
			resList = makeOrgAndUnitAndEmpTree(null, cList, employeeList);
		}

		return resList;
	}
	
	@Override
	public UserAccount updateUserAccount(String account, String password) throws Exception {
		return userAccountDao.updateUserAccount(account, password);
	}

	@Override
	public UserAccount queryUserAccount(String account, String password) throws Exception {
		return userAccountDao.queryUserAccount(account, password);
	}
}
