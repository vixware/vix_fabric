/**
 * 
 */
package com.vix.nvix.common.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.dao.IBusinessOrgDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.dao.IAuthorityDao;
import com.vix.common.security.entity.Authority;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.bom.entity.BomNode;
import com.vix.mdm.bom.util.BomUnit;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.OrderDetaiVo;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.pm.common.entity.ProjectRole;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.AttendanceHqlProvider;
import com.vix.nvix.common.base.hql.VixntBaseHqlProvider;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.common.base.vo.EmpCardRecords;
import com.vix.nvix.common.base.vo.EmpDayCardRecords;
import com.vix.nvix.common.base.vo.EmpMonthCardRecords;
import com.vix.nvix.oa.bo.AllWorkLogStatisticsBo;
import com.vix.nvix.oa.bo.TaskStatisticsBo;
import com.vix.nvix.oa.bo.WorkLogStatisticsBo;
import com.vix.nvix.project.util.ProjectUnit;
import com.vix.nvix.task.vo.TaskStatisticsVo;
import com.vix.nvix.warehouse.vo.CustomerSalesVo;
import com.vix.nvix.warehouse.vo.InAndOutStatisticsBo;
import com.vix.nvix.warehouse.vo.OrderDetailStatisticsBo;
import com.vix.nvix.warehouse.vo.SalesDataVo;
import com.vix.nvix.warehouse.vo.SalesOrderItemDay;
import com.vix.nvix.warehouse.vo.SalesOrderItemVo;
import com.vix.nvix.warehouse.vo.StockRecordLinesVo;
import com.vix.oa.bulletin.dao.IAnnouncementNotificationDao;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassFullName com.vix.nvix.common.base.service.impl.VixntBaseServiceImpl
 *
 * @author bjitzhang
 *
 * @date 2016年4月7日
 *
 */
@Service("vixntBaseService")
public class VixntBaseServiceImpl extends BaseHibernateServiceImpl implements IVixntBaseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IBusinessOrgDao businessViewDao;
	@Resource(name = "vixntBaseHqlProvider")
	private VixntBaseHqlProvider vixntBaseHqlProvider;
	@Resource(name = "attendanceHqlProvider")
	private AttendanceHqlProvider attendanceHqlProvider;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm");
	@Resource(name = "authorityDao")
	private IAuthorityDao authorityDao;
	@Autowired
	private IAnnouncementNotificationDao announcementNotificationDao;

	public String entityAsName() {
		return "hentity";
	}

	@Override
	public Pager findTaskPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findVixTaskPager(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public Pager findStockRecordLinesPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findStockRecordLinesHql(reqParams, pager);
		reqParams.remove("stockRecordsId");
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	/**
	 * 获取任务
	 */
	@Override
	public List<VixTask> findVixTaskList(String projectId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(projectId)) {
			params.put("projectId", projectId);
		}
		// params.put("status", "0");
		params.put("isTemp", "1");
		params.put("isDeleted", "0");
		StringBuilder hql = findVixTaskList(params);
		List<VixTask> billsTypeList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	public StringBuilder findVixTaskList(Map<String, Object> params) {
		String ename = "vixTask";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from VixTask ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("projectId")) {
				Object projectId = params.get("projectId");
				if (projectId == null) {
					hql.append(" and ").append(ename).append(".project.id is null ");
					params.remove("projectId");
				} else {
					hql.append(" and ").append(ename).append(".project.id = :projectId ");
				}
			}
			if (params.containsKey("status")) {
				Object status = params.get("status");
				if (status == null) {
					hql.append(" and ").append(ename).append(".status is null");
					params.remove("status");
				} else {
					hql.append(" and ").append(ename).append(".status = :status ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isDeleted")) {
				Object isDeleted = params.get("isDeleted");
				if (isDeleted == null) {
					hql.append(" and ").append(ename).append(".isDeleted is null");
					params.remove("isDeleted");
				} else {
					hql.append(" and ").append(ename).append(".isDeleted = :isDeleted ");
				}
			}
			if (params.containsKey("complete")) {
				Object complete = params.get("complete");
				if (complete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("complete");
				} else {
					hql.append(" and ").append(ename).append(".complete = :complete ");
				}
			}
			if (params.containsKey("questName")) {
				Object questName = params.get("questName");
				if (questName == null) {
					hql.append(" and ").append(ename).append(".questName is null");
					params.remove("questName");
				} else {
					hql.append(" and ").append(ename).append(".questName like :questName ");
				}
			}
			if (params.containsKey("taskStartTime")) {
				Object taskStartTime = params.get("taskStartTime");
				if (taskStartTime == null) {
					hql.append(" and ").append(ename).append(".taskStartTime is null");
					params.remove("taskStartTime");
				} else {
					hql.append(" and ").append(ename).append(".taskStartTime <= :taskStartTime ");
				}
			}
			if (params.containsKey("taskEndTime")) {
				Object taskEndTime = params.get("taskEndTime");
				if (taskEndTime == null) {
					hql.append(" and ").append(ename).append(".taskEndTime is null");
					params.remove("taskEndTime");
				} else {
					hql.append(" and ").append(ename).append(".taskEndTime >= :taskEndTime ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findProjectRoleList(Map<String, Object> params) {
		String ename = "projectRole";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from ProjectRole ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("projectId")) {
				Object projectId = params.get("projectId");
				if (projectId == null) {
					hql.append(" and ").append(ename).append(".project.id is null ");
					params.remove("projectId");
				} else {
					hql.append(" and ").append(ename).append(".project.id = :projectId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findVixTaskListByTaskId(Map<String, Object> params) {
		String ename = "vixTask";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from VixTask ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("vixTaskId")) {
				Object vixTaskId = params.get("vixTaskId");
				if (vixTaskId == null) {
					hql.append(" and ").append(ename).append(".parentVixTask.id is null ");
					params.remove("vixTaskId");
				} else {
					hql.append(" and ").append(ename).append(".parentVixTask.id = :vixTaskId ");
				}
			}
		}
		hql.append(" and ").append(ename).append(".status = 0 ");
		hql.append(" and ").append(ename).append(".isTemp = 1 ");
		hql.append(" and ").append(ename).append(".isDeleted = 0 ");
		return hql;
	}

	public StringBuilder findProjectRoleListByProjectRoleIdHql(Map<String, Object> params) {
		String ename = "projectRole";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from ProjectRole ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("parentProjectRoleId")) {
				Object parentProjectRoleId = params.get("parentProjectRoleId");
				if (parentProjectRoleId == null) {
					hql.append(" and ").append(ename).append(".parentProjectRole.id is null ");
					params.remove("parentProjectRoleId");
				} else {
					hql.append(" and ").append(ename).append(".parentProjectRole.id = :parentProjectRoleId ");
				}
			}
		}
		return hql;
	}

	private List<ProjectUnit> makeProjectAndTaskTree(List<VixTask> vixTaskList) {
		List<ProjectUnit> orgUnitList = new LinkedList<ProjectUnit>();
		if (vixTaskList != null) {
			for (VixTask vixTask : vixTaskList) {
				ProjectUnit billTypeUnit = new ProjectUnit(vixTask.getId(), "T", vixTask.getQuestName(), vixTask.getCode());
				if (vixTask.getSubVixTasks() != null) {
					List<ProjectUnit> pList = new LinkedList<ProjectUnit>();
					for (VixTask task : vixTask.getSubVixTasks()) {
						if("1".equals(task.getIsTemp()) && "0".equals(task.getIsDeleted())){
							ProjectUnit projectUnit = new ProjectUnit(task.getId(), "T", task.getQuestName(), task.getCode());
							pList.add(projectUnit);
						}
					}
					billTypeUnit.setSubProjectUnits(pList);
				}
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	private List<ProjectUnit> makeProjectAndProjectRoleTree(List<ProjectRole> projectRoleList) {
		List<ProjectUnit> orgUnitList = new LinkedList<ProjectUnit>();
		if (projectRoleList != null) {
			for (ProjectRole projectRole : projectRoleList) {
				ProjectUnit billTypeUnit = new ProjectUnit(projectRole.getId(), "R", projectRole.getName(), projectRole.getCode());
				if (projectRole.getSubProjectRoles() != null) {
					List<ProjectUnit> pList = new LinkedList<ProjectUnit>();
					for (ProjectRole task : projectRole.getSubProjectRoles()) {
						ProjectUnit projectUnit = new ProjectUnit(task.getId(), "R", task.getName(), task.getCode());
						pList.add(projectUnit);
					}
					billTypeUnit.setSubProjectUnits(pList);
				}
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	private List<ProjectUnit> makeVixTaskTree(List<VixTask> vixTaskList) {
		List<ProjectUnit> orgUnitList = new LinkedList<ProjectUnit>();
		if (vixTaskList != null) {
			for (VixTask vixTask : vixTaskList) {
				ProjectUnit projectUnit = new ProjectUnit(vixTask.getId(), "T", vixTask.getQuestName(), vixTask.getCode());
				orgUnitList.add(projectUnit);
				List<ProjectUnit> pList = new LinkedList<ProjectUnit>();
				if (vixTask.getSubVixTasks() != null && vixTask.getSubVixTasks().size() > 0) {
					for (VixTask v : vixTask.getSubVixTasks()) {
						if("1".equals(v.getIsTemp()) && "0".equals(v.getIsDeleted())){
							pList.add(new ProjectUnit(v.getId(), "T", v.getQuestName(), v.getCode()));
						}
					}
				}
				projectUnit.setSubProjectUnits(pList);
			}
		}
		return orgUnitList;
	}

	private List<ProjectUnit> makeProjectRoleTree(List<ProjectRole> projectRoleList) {
		List<ProjectUnit> orgUnitList = new LinkedList<ProjectUnit>();
		if (projectRoleList != null) {
			for (ProjectRole projectRole : projectRoleList) {
				ProjectUnit projectUnit = new ProjectUnit(projectRole.getId(), "R", projectRole.getName(), projectRole.getCode());
				orgUnitList.add(projectUnit);
				List<ProjectUnit> pList = new LinkedList<ProjectUnit>();
				if (projectRole.getSubProjectRoles() != null && projectRole.getSubProjectRoles().size() > 0) {
					for (ProjectRole v : projectRole.getSubProjectRoles()) {
						pList.add(new ProjectUnit(v.getId(), "R", v.getName(), v.getCode()));
					}
				}
				projectUnit.setSubProjectUnits(pList);
			}
		}
		return orgUnitList;
	}

	public List<VixTask> findBillsTypeList(String vixTaskId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vixTaskId", vixTaskId);
		StringBuilder hql = findVixTaskListByTaskId(params);
		List<VixTask> billsTypeList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	@Override
	public List<ProjectUnit> findProjectUnitList(String treeType, String id) throws Exception {
		List<ProjectUnit> billTypeUnitList = new LinkedList<ProjectUnit>();
		if (treeType.equals("P")) {
			List<VixTask> vixTaskList = findVixTaskList(id);
			billTypeUnitList = makeProjectAndTaskTree(vixTaskList);
		} else if (treeType.equals("T")) {
			List<VixTask> billsTypeList = findBillsTypeList(id);
			billTypeUnitList = makeVixTaskTree(billsTypeList);
		}
		return billTypeUnitList;
	}
	
	@Override
	public List<BomUnit> findBomUnitList(String treeType, String id) throws Exception {
		List<BomUnit> billTypeUnitList = new LinkedList<BomUnit>();
		if (treeType.equals("S")) {
			List<BomNode> bomNodeList = findBomNodeList(id);
			billTypeUnitList = makeBomNodeTree(bomNodeList);
		} else if (treeType.equals("N")) {
			List<BomNode> bomNodeList = findBomNodeListByParentId(id);
			billTypeUnitList = makeBomNodeTree(bomNodeList);
		}
		return billTypeUnitList;
	}
	
	public List<BomNode> findBomNodeListByParentId(String bomNodeId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StrUtils.isNotEmpty(bomNodeId)){
			params.put("bomNodeId", bomNodeId);
		}
		String ename = "bomNode";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from BomNode ").append(ename);
		hql.append(" where 1=1 ");
		if (StrUtils.isEmpty(bomNodeId)) {
			hql.append(" and ").append(ename).append(".parentBomNode.id is null ");
		} else {
			hql.append(" and ").append(ename).append(".parentBomNode.id = :bomNodeId ");
		}
		List<BomNode> billsTypeList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}
	
	@Override
	public List<BomNode> findBomNodeList(String bomStructId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StrUtils.isNotEmpty(bomStructId)){
			params.put("bomStructId", bomStructId);
		}
		StringBuilder hql = new StringBuilder();
		String ename = "bomNode";
		hql.append("select ").append(ename);
		hql.append(" from BomNode ").append(ename);
		hql.append(" where 1=1 ");
		if (StrUtils.isEmpty(bomStructId)) {
			hql.append(" and ").append(ename).append(".bomStruct.id is null ");
		} else {
			hql.append(" and ").append(ename).append(".bomStruct.id = :bomStructId ");
		}
		List<BomNode> billsTypeList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}
	
	private List<BomUnit> makeBomNodeTree(List<BomNode> bomNodeList) {
		List<BomUnit> orgUnitList = new LinkedList<BomUnit>();
		if (bomNodeList != null) {
			for (BomNode bomNode : bomNodeList) {
				BomUnit billTypeUnit = new BomUnit(bomNode.getId(), "N", bomNode.getSubject(), bomNode.getLevel().toString());
				if (bomNode.getSubBomNodes() != null) {
					List<BomUnit> pList = new LinkedList<BomUnit>();
					for (BomNode node : bomNode.getSubBomNodes()) {
						BomUnit bomUnit = new BomUnit(node.getId(), "N", node.getSubject(), node.getLevel().toString());
						pList.add(bomUnit);
					}
					billTypeUnit.setSubBomUnits(pList);
				}
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	public StringBuilder getOrganizationUnitList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrganizationUnit ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("orgId")) {
				Object orgId = params.get("orgId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".organization.id is null ");
					params.remove("orgId");
				} else {
					hql.append(" and ").append(ename).append(".organization.id = :orgId ");
				}
			}
		}

		return hql;
	}

	public StringBuilder getEmployeeList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("orgId")) {
				Object orgId = params.get("orgId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".organizationUnit.id is null ");
					params.remove("orgId");
				} else {
					hql.append(" and ").append(ename).append(".organizationUnit.id = :orgId ");
				}
			}
		}

		return hql;
	}

	public List<OrganizationUnit> findOrganizationUnitList(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = getOrganizationUnitList(params);
		List<OrganizationUnit> orgUnitList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	public List<Employee> findEmployeeList(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = getEmployeeList(params);
		List<Employee> orgUnitList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id, String type) throws Exception {
		List<OrgUnit> resList = new LinkedList<OrgUnit>();
		if (treeType.equals("C")) {
			// 查找子公司和子部门信息
			List<Organization> orgList = organizationService.findSubOrganizationList(id);
			List<OrganizationUnit> organizationUnitList = findOrganizationUnitList(id);
			resList = makeOrgAndUnitTree(orgList, organizationUnitList, null, type);
		} else if (treeType.equals("O")) {
			OrganizationUnit organizationUnit = vixntBaseDao.findEntityById(OrganizationUnit.class, id);
			List<Employee> employeeList = findEmployeeList(organizationUnit.getId());
			List<OrganizationUnit> cList = new ArrayList<OrganizationUnit>();
			if (organizationUnit.getSubOrganizationUnits() != null && organizationUnit.getSubOrganizationUnits().size() > 0) {
				for (OrganizationUnit c : organizationUnit.getSubOrganizationUnits()) {
					cList.add(c);
				}
			}
			if ("O".equals(type)) {
				resList = makeOrgAndUnitTree(null, cList, null, type);
			} else {
				resList = makeOrgAndUnitTree(null, cList, employeeList, null);
			}
		}
		return resList;
	}

	private List<OrgUnit> makeOrgAndUnitTree(List<Organization> organizationList, List<OrganizationUnit> organizationUnitList, List<Employee> employeeList, String type) throws Exception {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (organizationList != null && organizationList.size() > 0) {
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
						subUnitList.add(new OrgUnit(ou.getId(), "O", ou.getFs()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}

		if (organizationUnitList != null && organizationUnitList.size() > 0) {
			for (OrganizationUnit organizationUnit : organizationUnitList) {
				if (organizationUnit != null) {
					OrgUnit orgUnit = new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs());
					if (organizationUnit.getSubOrganizationUnits() != null && organizationUnit.getSubOrganizationUnits().size() > 0) {
						List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
						for (OrganizationUnit ou : organizationUnit.getSubOrganizationUnits()) {
							subUnitList.add(new OrgUnit(ou.getId(), "O", ou.getFs()));
						}
						orgUnit.setSubOrgUnits(subUnitList);
					}
					if ("O".equals(type)) {
					} else {
						List<Employee> empList = findEmployeeList(organizationUnit.getId());
						if (empList != null && empList.size() > 0) {
							List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
							for (Employee e : empList) {
								subUnitList.add(new OrgUnit(e.getId(), "E", e.getName()));
							}
							orgUnit.setSubOrgUnits(subUnitList);
						}
					}
					orgUnitList.add(orgUnit);
				}
			}
		}
		if ("O".equals(type)) {
		} else {
			if (employeeList != null && employeeList.size() > 0) {
				for (Employee employee : employeeList) {
					OrgUnit orgUnit = new OrgUnit(employee.getId(), "E", employee.getName());
					orgUnitList.add(orgUnit);
				}
			}
		}
		return orgUnitList;
	}

	@Override
	public List<Employee> findBusinessOrgDetailBo4ReportLine(String tenantId, String queryBusinessOrgDetailBoId) throws Exception {
		return businessViewDao.findBusinessOrgDetailBo4ReportLine(null, tenantId, false, true, queryBusinessOrgDetailBoId, BizConstant.COMMON_ORG_E, BizConstant.COMMON_ORG_E);
	}

	@Override
	public Pager findAllTaskPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findAllOrgPager(reqParams, pager);
		reqParams.remove("employeeIds");
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public <T> List<T> findProjectByHql(Map<String, Object> params) throws Exception {
		StringBuilder hql = findProjectHql(params);
		return vixntBaseDao.findAllDataByHqlOrginial(hql.toString(), params);
	}

	public StringBuilder findProjectHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "project";
		hql.append("select ").append(ename);
		hql.append(" from Project ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isDeleted")) {
				Object isDeleted = params.get("isDeleted");
				if (isDeleted == null) {
					hql.append(" and ").append(ename).append(".isDeleted is null");
					params.remove("isDeleted");
				} else {
					hql.append(" and ").append(ename).append(".isDeleted = :isDeleted ");
				}
			}
		}
		return hql;
	}

	@Override
	public Pager findProjectPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findProjectHql(reqParams);
		pager = vixntBaseDao.findPagerByHql(pager, hql.toString(), reqParams);
		return pager;

	}

	@Override
	public Pager findWorkLogPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findWorkLogPager(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public List<Object[]> findEmpCardRecords(String hql) throws Exception {
		return vixntBaseDao.findObjectList(hql);
	}

	/**
	 * 获取员工打卡记录
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<EmpCardRecords> getEmpCardRecordsList(Map<String, Object> params) {
		try {
			List<EmpCardRecords> empCardRecordsList = new ArrayList<EmpCardRecords>();
			/*
			 * Map<String, Object> params = new HashMap<String, Object>();
			 * params.put("userCode", "3");
			 */
			StringBuilder hql = attendanceHqlProvider.findEmpCardRecordsList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 4) {
						EmpCardRecords empCardRecords = new EmpCardRecords();
						empCardRecords.setUserCode((String) obj[0]);
						empCardRecords.setDatetemp((String) obj[1]);
						empCardRecords.setStartcard((String) obj[2]);
						empCardRecords.setEndcard((String) obj[3]);
						empCardRecordsList.add(empCardRecords);
					}
				}
			}
			return empCardRecordsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取所有员工制定时间打卡记录
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<EmpDayCardRecords> getEmpDayCardRecordsList(Map<String, Object> params) {
		try {
			List<EmpDayCardRecords> empCardRecordsList = new ArrayList<EmpDayCardRecords>();
			StringBuilder hql = attendanceHqlProvider.findEmpDayCardRecordsList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 4) {
						EmpDayCardRecords empCardRecords = new EmpDayCardRecords();
						empCardRecords.setUserCode((String) obj[0]);
						empCardRecords.setStartcard((String) obj[1]);
						empCardRecords.setEndcard((String) obj[2]);
						empCardRecords.setDatetemp((String) obj[3]);
						empCardRecordsList.add(empCardRecords);
					}
				}
			}
			return empCardRecordsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public List<EmpMonthCardRecords> getEmpMonthCardRecordsList(Map<String, Object> params) throws Exception {
		try {
			List<EmpMonthCardRecords> empMonthCardRecordsList = new ArrayList<EmpMonthCardRecords>();
			StringBuilder hql = attendanceHqlProvider.findEmpMonthCardRecordsList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 12) {
						EmpMonthCardRecords empMonthCardRecords = new EmpMonthCardRecords();
						empMonthCardRecords.setUserCode((String) obj[0]);
						empMonthCardRecords.setDatetemp((String) obj[1]);
						if (obj[2] != null) {
							empMonthCardRecords.setCardNum(Double.parseDouble(obj[2].toString()));
						}
						if (obj[3] != null) {
							empMonthCardRecords.setAccumulativeWorkHours(Double.parseDouble(obj[3].toString()));
						}
						if (obj[4] != null) {
							empMonthCardRecords.setAccumulativeLeaveHours(Double.parseDouble(obj[4].toString()));
						}
						if (obj[5] != null) {
							empMonthCardRecords.setAccumulativeOverTimeHours(Double.parseDouble(obj[5].toString()));
						}
						if (obj[6] != null) {
							empMonthCardRecords.setAccumulativeEvectionHours(Double.parseDouble(obj[6].toString()));
						}
						if (obj[7] != null) {
							empMonthCardRecords.setLateTime(Double.parseDouble(obj[7].toString()));
						}
						if (obj[8] != null) {
							empMonthCardRecords.setEarlyTime(Double.parseDouble(obj[8].toString()));
						}
						if (obj[9] != null) {
							empMonthCardRecords.setAffairAbsence(Double.parseDouble(obj[9].toString()));
						}
						if (obj[10] != null) {
							empMonthCardRecords.setSickLeave(Double.parseDouble(obj[10].toString()));
						}
						if (obj[11] != null) {
							empMonthCardRecords.setAbsenteeismNum(Double.parseDouble(obj[11].toString()));
						}
						empMonthCardRecordsList.add(empMonthCardRecords);
					}
				}
			}
			return empMonthCardRecordsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.nvix.common.base.service.IVixntBaseService#
	 * getTaskStatisticsBoList(java.util.Map)
	 */
	@Override
	public List<TaskStatisticsBo> getTaskStatisticsBoList(Map<String, Object> params, String syncTag) throws Exception {
		try {
			List<TaskStatisticsBo> taskStatisticsBoList = new ArrayList<TaskStatisticsBo>();
			StringBuilder hql = attendanceHqlProvider.findTaskStatisticsBoList(params,syncTag);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					TaskStatisticsBo taskStatisticsBo = new TaskStatisticsBo();
					taskStatisticsBo.setEmpName((String) obj[0]);
					if (obj[1] != null) {
						taskStatisticsBo.setTaskNum(Double.parseDouble(obj[1].toString()));
					}
					if (obj[2] != null) {
						taskStatisticsBo.setNoStartTaskNum(Double.parseDouble(obj[2].toString()));
					}
					if (obj[3] != null) {
						taskStatisticsBo.setProgressTaskNum(Double.parseDouble(obj[3].toString()));
					}
					if (obj[4] != null) {
						taskStatisticsBo.setFinishTaskNum(Double.parseDouble(obj[4].toString()));
					}
					if (obj[5] != null) {
						taskStatisticsBo.setOvertimeTaskNum(Double.parseDouble(obj[5].toString()));
					}
					if("week".equals(syncTag)){
						taskStatisticsBo.setDatetemp("本周");
					}else if("reason".equals(syncTag)){
						taskStatisticsBo.setDatetemp("本季");
					}else if("month".equals(syncTag) || "year".equals(syncTag) || "projectTask".equals(syncTag) || "orgTask".equals(syncTag)){
						if (obj[6] != null) {
							taskStatisticsBo.setDatetemp((String) obj[6]);
						}
					}
					taskStatisticsBo.setEmpId((String) obj[7]);
					taskStatisticsBoList.add(taskStatisticsBo);
				}
			}
			return taskStatisticsBoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<TaskStatisticsVo> getCompleteTaskStatisticsVoList() throws Exception {
		try {
			List<TaskStatisticsVo> taskStatisticsVoList = new ArrayList<TaskStatisticsVo>();
			StringBuilder hql = attendanceHqlProvider.findCompleteTaskStatisticsVoList();
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					TaskStatisticsVo taskStatisticsVo = new TaskStatisticsVo();
					if (obj[0] != null) {
						taskStatisticsVo.setEmpName(obj[0].toString());
					}
					if (obj[1] != null) {
						taskStatisticsVo.setRegnum(Long.parseLong(obj[1].toString()));
					}
					taskStatisticsVoList.add(taskStatisticsVo);
				}
			}
			return taskStatisticsVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<TaskStatisticsVo> getTimeOutTaskStatisticsVoList() throws Exception {
		try {
			List<TaskStatisticsVo> taskStatisticsVoList = new ArrayList<TaskStatisticsVo>();
			StringBuilder hql = attendanceHqlProvider.findTimeOutTaskStatisticsVoList();
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					TaskStatisticsVo taskStatisticsVo = new TaskStatisticsVo();
					if (obj[0] != null) {
						taskStatisticsVo.setEmpName(obj[0].toString());
					}
					if (obj[1] != null) {
						taskStatisticsVo.setRegnum(Long.parseLong(obj[1].toString()));
					}
					taskStatisticsVoList.add(taskStatisticsVo);
				}
			}
			return taskStatisticsVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VixTask> findVixTaskListBySql(Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findVixTaskHql(reqParams);
		reqParams.remove("taskIds");
		return vixntBaseDao.findAllDataByHqlOrginial(hql.toString(), reqParams);
	}

	@Override
	public Pager findProjectManagementPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findProjectManagementPagerHql(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public Pager findTripRecordPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findTripRecordPagerHql(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public List<ProjectUnit> findProjectRoleList(String treeType, String id) throws Exception {

		List<ProjectUnit> billTypeUnitList = new LinkedList<ProjectUnit>();
		if (treeType.equals("P")) {
			List<ProjectRole> vixTaskList = findParkingCarDistrictAndCountyList(id);
			billTypeUnitList = makeProjectAndProjectRoleTree(vixTaskList);
		} else if (treeType.equals("R")) {
			List<ProjectRole> billsTypeList = findProjectRoleListByProjectRoleId(id);
			billTypeUnitList = makeProjectRoleTree(billsTypeList);
		}
		return billTypeUnitList;

	}

	public List<ProjectRole> findProjectRoleListByProjectRoleId(String parentProjectRoleId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentProjectRoleId", parentProjectRoleId);
		StringBuilder hql = findProjectRoleListByProjectRoleIdHql(params);
		List<ProjectRole> billsTypeList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	@Override
	public List<ProjectRole> findParkingCarDistrictAndCountyList(String projectId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		StringBuilder hql = findProjectRoleList(params);
		List<ProjectRole> projectRoleList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return projectRoleList;
	}

	@Override
	public List<ProjectUnit> findSupplierList(String treeType, String id) throws Exception {
		List<ProjectUnit> billTypeUnitList = new LinkedList<ProjectUnit>();
		if (treeType.equals("S")) {
			List<Employee> employeeList = findSupplierEmployeeList(id);
			billTypeUnitList = makeSupplierAndEmployee(employeeList);
		}
		return billTypeUnitList;
	}

	@Override
	public List<Employee> findSupplierEmployeeList(String supplierId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierId", supplierId);
		StringBuilder hql = getSupplierEmployeeList(params);
		List<Employee> employeeList = vixntBaseDao.findAllByHql(hql.toString(), params);
		return employeeList;
	}

	private List<ProjectUnit> makeSupplierAndEmployee(List<Employee> employeeList) {
		List<ProjectUnit> orgUnitList = new LinkedList<ProjectUnit>();
		if (employeeList != null) {
			for (Employee employee : employeeList) {
				ProjectUnit billTypeUnit = new ProjectUnit(employee.getId(), "E", employee.getName(), employee.getCode());
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	public StringBuilder getSupplierEmployeeList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" where 1=1 and empType = 'SE' ");
		if (params != null) {
			if (params.containsKey("supplierId")) {
				Object supplierId = params.get("supplierId");
				if (supplierId == null) {
					hql.append(" and ").append(ename).append(".supplier.id is null ");
					params.remove("supplierId");
				} else {
					hql.append(" and ").append(ename).append(".supplier.id = :supplierId ");
				}
			}
		}

		return hql;
	}

	@Override
	public Pager findItemPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findItemPager(reqParams, pager);
		reqParams.remove("itemIds");
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public List<StockRecordLinesVo> getStockRecordLinesVoList(Map<String, Object> params) throws Exception {
		try {
			List<StockRecordLinesVo> stockRecordLinesVoList = new ArrayList<StockRecordLinesVo>();
			StringBuilder hql = attendanceHqlProvider.findStockRecordLinesVoList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 5) {
						StockRecordLinesVo stockRecordLinesVo = new StockRecordLinesVo();
						stockRecordLinesVo.setItemcode((String) obj[0]);
						if (obj[1] != null) {
							stockRecordLinesVo.setItemname((String) obj[1]);
						}
						if (obj[2] != null) {
							stockRecordLinesVo.setQuantity(Double.parseDouble(String.valueOf(obj[2])));
						}
						if (obj[3] != null) {
							stockRecordLinesVo.setPrice(Double.parseDouble(String.valueOf(obj[3])));
						}
						if (obj[4] != null) {
							stockRecordLinesVo.setCreateTime(sdf.parse(obj[4].toString()));
						}
						stockRecordLinesVoList.add(stockRecordLinesVo);
					}
				}
			}
			return stockRecordLinesVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Item> findAllItemByHql(String hql, Map<String, Object> params) throws Exception {
		return vixntBaseDao.findAllByHql(hql, params);
	}

	@Override
	public List<MeasureUnit> findAllMeasureUnitByHql(String hql, Map<String, Object> params) throws Exception {
		return vixntBaseDao.findAllByHql(hql, params);
	}

	@Override
	public List<StockRecordLinesVo> getOutStockRecordLinesVoList(Map<String, Object> params) throws Exception {
		try {
			List<StockRecordLinesVo> stockRecordLinesVoList = new ArrayList<StockRecordLinesVo>();
			StringBuilder hql = attendanceHqlProvider.findOutStockRecordLinesVoList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 5) {
						StockRecordLinesVo stockRecordLinesVo = new StockRecordLinesVo();
						stockRecordLinesVo.setItemcode((String) obj[0]);
						if (obj[1] != null) {
							stockRecordLinesVo.setItemname((String) obj[1]);
						}
						if (obj[2] != null) {
							stockRecordLinesVo.setQuantity(Double.parseDouble(String.valueOf(obj[2])));
						}
						if (obj[3] != null) {
							stockRecordLinesVo.setPrice(Double.parseDouble(String.valueOf(obj[3])));
						}
						if (obj[4] != null) {
							stockRecordLinesVo.setCreateTime(sdf.parse(obj[4].toString()));
						}
						stockRecordLinesVoList.add(stockRecordLinesVo);
					}
				}
			}
			return stockRecordLinesVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SalesOrderItemVo> getSalesOrderItemVoList(Map<String, Object> params, String tenantId) throws Exception {
		try {
			List<SalesOrderItemVo> stockRecordLinesVoList = new ArrayList<SalesOrderItemVo>();
			StringBuilder hql = attendanceHqlProvider.findSalesOrderItemVoList(params, tenantId);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 5) {
						SalesOrderItemVo salesOrderItemVo = new SalesOrderItemVo();
						salesOrderItemVo.setItemcode((String) obj[0]);
						if (obj[1] != null) {
							salesOrderItemVo.setItemname((String) obj[1]);
						}
						if (obj[2] != null) {
							salesOrderItemVo.setQuantity(Long.parseLong(String.valueOf(obj[2])));
						}
						if (obj[3] != null) {
							salesOrderItemVo.setPrice(Double.parseDouble(String.valueOf(obj[3])));
						}
						if (obj[4] != null) {
							salesOrderItemVo.setCreateTime(sdf.parse(obj[4].toString()));
						}
						stockRecordLinesVoList.add(salesOrderItemVo);
					}
				}
			}
			return stockRecordLinesVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.nvix.common.base.service.IVixntBaseService#
	 * getSalesOrderItemDayList(java.util.Map)
	 */
	@Override
	public List<SalesOrderItemDay> getSalesOrderItemDayList(Map<String, Object> params) throws Exception {
		try {
			List<SalesOrderItemDay> stockRecordLinesVoList = new ArrayList<SalesOrderItemDay>();
			StringBuilder hql = attendanceHqlProvider.findSalesOrderItemDayList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 3) {
						SalesOrderItemDay salesOrderItemDay = new SalesOrderItemDay();
						if (obj[0] != null) {
							salesOrderItemDay.setQuantity(Long.parseLong(String.valueOf(obj[0])));
						}
						if (obj[1] != null) {
							salesOrderItemDay.setPrice(Double.parseDouble(String.valueOf(obj[1])));
						}
						if (obj[2] != null) {
							salesOrderItemDay.setCreateTime(sdf.parse(obj[2].toString()));
						}
						stockRecordLinesVoList.add(salesOrderItemDay);
					}
				}
			}
			return stockRecordLinesVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CustomerSalesVo> getCustomerSalesVoList(Map<String, Object> params) throws Exception {
		List<CustomerSalesVo> stockRecordLinesVoList = new ArrayList<CustomerSalesVo>();
		StringBuilder hql = attendanceHqlProvider.findCustomerSalesList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 2) {
					CustomerSalesVo salesOrderItemDay = new CustomerSalesVo();
					if (obj[0] != null) {
						salesOrderItemDay.setPrice(Double.parseDouble(String.valueOf(obj[0])));
					}
					if (obj[1] != null) {
						salesOrderItemDay.setCustomerName(String.valueOf(obj[1]));
					}
					stockRecordLinesVoList.add(salesOrderItemDay);
				}
			}
		}
		return stockRecordLinesVoList;
	}

	@Override
	public List<OrderDetaiVo> getOrderDetaiVoList(Map<String, Object> params) throws Exception {
		List<OrderDetaiVo> orderDetaiVoList = new ArrayList<OrderDetaiVo>();
		StringBuilder hql = attendanceHqlProvider.findOrderDetaiVoList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 6) {
					OrderDetaiVo orderDetaiVo = new OrderDetaiVo();
					if (obj[0] != null) {
						orderDetaiVo.setItemcode(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						orderDetaiVo.setItemname(String.valueOf(obj[1]));
					}
					if (obj[2] != null) {
						orderDetaiVo.setSalesDate(obj[2].toString());
					}
					if (obj[3] != null) {
						orderDetaiVo.setPrice(Double.parseDouble(String.valueOf(obj[3])));
					}
					if (obj[4] != null) {
						orderDetaiVo.setNum(Double.parseDouble(String.valueOf(obj[4])));
					}
					if (obj[5] != null) {
						orderDetaiVo.setPurchasePrice(Double.parseDouble(String.valueOf(obj[5])));
					}
					orderDetaiVoList.add(orderDetaiVo);
				}
			}
		}
		return orderDetaiVoList;
	}

	@Override
	public Pager findStoreItemPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findStoreItemPager(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public Authority findObjectBySql(String sql, Map<String, Object> params) throws Exception {
		return vixntBaseDao.findObjectBySql(sql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.nvix.common.base.service.IVixntBaseService#findStoreItemListByHql
	 * (java.util.Map)
	 */
	@Override
	public List<StoreItem> findStoreItemListByHql(Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findStoreItem(reqParams);
		reqParams.remove("itemIds");
		return vixntBaseDao.findAllDataByHqlOrginial(hql.toString(), reqParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.nvix.common.base.service.IVixntBaseService#
	 * findRoleMenuAuthorityByUserId(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Set<Authority> findRoleMenuAuthorityByUserId(String roleId, String userId, String bizType, String tenantId) throws Exception {
		return authorityDao.findRoleMenuAuthorityByUserIdForShop(roleId, userId, bizType, tenantId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.nvix.common.base.service.IVixntBaseService#getSalesDataVoList(
	 * java.util.Map)
	 */
	@Override
	public List<SalesDataVo> getSalesDataVoList(Map<String, Object> params) throws Exception {
		List<SalesDataVo> salesDataVoList = new ArrayList<SalesDataVo>();
		StringBuilder hql = attendanceHqlProvider.findSalesDataVoList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 3) {
					SalesDataVo salesDataVo = new SalesDataVo();
					if (obj[0] != null) {
						salesDataVo.setShopname(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						salesDataVo.setPrice(Double.parseDouble(String.valueOf(obj[1])));
					}
					if (obj[2] != null) {
						salesDataVo.setCreateTime(simpleDateFormat.parse(obj[2].toString()));
					}
					salesDataVoList.add(salesDataVo);
				}
			}
		}
		return salesDataVoList;
	}

	@Override
	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {

		if (params == null)
			params = new HashMap<String, Object>();

		StringBuilder hqlBuilder = this.genHqlHeadBuilder(entityClass, params);

		if (null != params && params.keySet().size() > 0) {
			hqlBuilder.append(" and ");
		}
		buildSearchQl(hqlBuilder, params);
		hqlBuilder.append(" ORDER BY createTime DESC ");
		/*
		 * if(null != hqlBuilder && !"".equals(hqlBuilder)){ hqlBuilder.append(
		 * " ORDER BY isTop DESC "); }else{ hqlBuilder.append(
		 * " ORDER BY createTime DESC "); }
		 */
		return announcementNotificationDao.findAllDataByHql(hqlBuilder.toString(), params);
	}

	/** 根据参数拼接hql */
	private void buildSearchQl(StringBuilder qlBuilder, Map<String, Object> params) {
		int keyCount = params.keySet().size();
		/** 参数里需要移除的参数key */
		StringBuilder needRemoveList = new StringBuilder();
		for (String key : params.keySet()) {
			if (!StrUtils.objectIsNull(key)) {
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.BETWEENAND)) {
							Object v = params.get(key);
							if (null != v && !"".equals(v)) {
								String[] val = v.toString().split("!");
								if (val.length == 2) {
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						} else if (k[1].equals(SearchCondition.IS)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						} else if (k[1].equals(SearchCondition.IN)) {
							Object inValue = params.get(key);
							dealHqlInCondition(inValue, needRemoveList, qlBuilder, key);
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0]);
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(k[0]);
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0]);
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0]);
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0]);
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0]);
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0]);
						} else if (k[1].equals(SearchCondition.BETWEENAND)) {
							Object v = params.get(key);
							if (null != v && !"".equals(v)) {
								String[] val = v.toString().split("!");
								if (val.length == 2) {
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						} else if (k[1].equals(SearchCondition.IS)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						} else if (k[1].equals(SearchCondition.IN)) {
							Object inValue = params.get(key);
							dealHqlInCondition(inValue, needRemoveList, qlBuilder, key);
						}
					}
				} else {
					if (key.contains(".")) {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(k[0].replace(".", "_"));
					} else {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(key);
					}
				}
				if (keyCount > 1) {
					qlBuilder.append(" and ");
				}
				keyCount--;
			}
		}
		for (String k : needRemoveList.toString().split(",")) {
			if (null != k && !"".equals(k)) {
				params.remove(k);
			}
		}
	}

	private void dealHqlInCondition(Object inValue, StringBuilder needRemoveList, StringBuilder qlBuilder, String key) {
		String[] k = key.split(",");
		if (null != inValue && !"".equals(inValue)) {
			needRemoveList.append(key).append(",");
			qlBuilder.append("hentity.").append(k[0]);
			qlBuilder.append(" in (");
			String[] ivArray = inValue.toString().split(",");
			for (int i = 0; i < ivArray.length; i++) {
				String iv = ivArray[i];
				if (null != iv && !"".equals(iv)) {
					qlBuilder.append("'");
					qlBuilder.append(iv);
					qlBuilder.append("'");
					if (i < ivArray.length - 1) {
						qlBuilder.append(",");
					}
				}
			}
			qlBuilder.append(") ");
		}
	}

	@Override
	public Pager findUploaderPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findUploaderHql(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public List<InAndOutStatisticsBo> getInAndOutStatisticsBoList(Map<String, Object> params) throws Exception {
		List<InAndOutStatisticsBo> salesDataVoList = new ArrayList<InAndOutStatisticsBo>();
		StringBuilder hql = attendanceHqlProvider.findInAndOutStatisticsBoList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 4) {
					InAndOutStatisticsBo salesDataVo = new InAndOutStatisticsBo();
					if (obj[0] != null) {
						salesDataVo.setItemcode(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						salesDataVo.setItemname(String.valueOf(obj[1]));
					}
					if (obj[2] != null) {
						salesDataVo.setQuantity(Double.parseDouble(String.valueOf(obj[2])));
					}
					if (obj[3] != null) {
						salesDataVo.setCreateTime(simpleDateFormat.parse(obj[3].toString()));
					}
					salesDataVoList.add(salesDataVo);
				}
			}
		}
		return salesDataVoList;
	}

	@Override
	public List<OrderDetailStatisticsBo> getOrderDetailStatisticsBoList(Map<String, Object> params) throws Exception {
		List<OrderDetailStatisticsBo> orderDetailStatisticsBoList = new ArrayList<OrderDetailStatisticsBo>();
		StringBuilder hql = attendanceHqlProvider.findOrderDetailStatisticsBoList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 6) {
					OrderDetailStatisticsBo orderDetailStatisticsBo = new OrderDetailStatisticsBo();
					if (obj[0] != null) {
						orderDetailStatisticsBo.setOuterGoodsId(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						orderDetailStatisticsBo.setTitle(String.valueOf(obj[1]));
					}
					if (obj[2] != null) {
						orderDetailStatisticsBo.setPrice(Double.parseDouble(String.valueOf(obj[2])));
					}
					if (obj[3] != null) {
						orderDetailStatisticsBo.setNum(Double.parseDouble(String.valueOf(obj[3])));
					}
					if (obj[4] != null) {
						orderDetailStatisticsBo.setPayment(String.valueOf(obj[4]));
					}
					if (obj[5] != null) {
						orderDetailStatisticsBo.setCreateTime(simpleDateFormat.parse(obj[5].toString()));
					}
					orderDetailStatisticsBoList.add(orderDetailStatisticsBo);
				}
			}
		}
		return orderDetailStatisticsBoList;
	}

	@Override
	public Pager findItemPagerByHql(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findItemHql(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public Pager findCommunicationPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findCommunicationPager(reqParams, pager);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception {
		return vixntBaseDao.findObjectByHqlNoTenantId(hql, params);
	}

	@Override
	public Pager findApplicationMgPager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = vixntBaseHqlProvider.findApplicationMgHql(reqParams);
		pager = vixntBaseDao.findPagerByHql(pager, vixntBaseHqlProvider.entityAsName(), hql.toString(), reqParams);
		return pager;
	}

	@Override
	public List<Employee> findHigherLevelBusinessOrgDetailBo4ReportLine(String tenantId, String queryBusinessOrgDetailBoId) throws Exception {
		return businessViewDao.findBusinessOrgDetailBo4ReportLine(null, tenantId, true, true, queryBusinessOrgDetailBoId, BizConstant.COMMON_ORG_E, BizConstant.COMMON_ORG_E);
	}

	@Override
	public List<StockRecordLinesVo> getStockRecordLinesPriceVoList(Map<String, Object> params) throws Exception {

		List<StockRecordLinesVo> stockRecordLinesVoList = new ArrayList<StockRecordLinesVo>();
		StringBuilder hql = attendanceHqlProvider.findStockRecordLinesPriceVoList(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 5) {
					StockRecordLinesVo stockRecordLinesVo = new StockRecordLinesVo();
					stockRecordLinesVo.setItemcode((String) obj[0]);
					if (obj[1] != null) {
						stockRecordLinesVo.setItemname((String) obj[1]);
					}
					if (obj[2] != null) {
						stockRecordLinesVo.setQuantity(Double.parseDouble(String.valueOf(obj[2])));
					}
					if (obj[3] != null) {
						stockRecordLinesVo.setPrice(Double.parseDouble(String.valueOf(obj[3])));
					}
					if (obj[4] != null) {
						stockRecordLinesVo.setCreateTime(sdf.parse(obj[4].toString()));
					}
					stockRecordLinesVoList.add(stockRecordLinesVo);
				}
			}
		}
		return stockRecordLinesVoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.nvix.common.base.service.IVixntBaseService#
	 * getSalesOrderItemNumVoList(java.util.Map, java.lang.String)
	 */
	@Override
	public List<SalesOrderItemVo> getSalesOrderItemNumVoList(Map<String, Object> params, String tenantId) throws Exception {
		try {
			List<SalesOrderItemVo> stockRecordLinesVoList = new ArrayList<SalesOrderItemVo>();
			StringBuilder hql = attendanceHqlProvider.findSalesOrderItemNumVoList(params, tenantId);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					if (obj.length == 5) {
						SalesOrderItemVo salesOrderItemVo = new SalesOrderItemVo();
						salesOrderItemVo.setItemcode((String) obj[0]);
						if (obj[1] != null) {
							salesOrderItemVo.setItemname((String) obj[1]);
						}
						if (obj[2] != null) {
							salesOrderItemVo.setQuantity(Long.parseLong(String.valueOf(obj[2])));
						}
						if (obj[3] != null) {
							salesOrderItemVo.setPrice(Double.parseDouble(String.valueOf(obj[3])));
						}
						if (obj[4] != null) {
							salesOrderItemVo.setCreateTime(sdf.parse(obj[4].toString()));
						}
						stockRecordLinesVoList.add(salesOrderItemVo);
					}
				}
			}
			return stockRecordLinesVoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<WorkLogStatisticsBo> getWorkLogStatisticsBoList(Map<String, Object> params) throws Exception {
		try {
			List<WorkLogStatisticsBo> workLogStatisticsBoList = new ArrayList<WorkLogStatisticsBo>();
			StringBuilder hql = attendanceHqlProvider.findWorklogStatisticsBoList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					WorkLogStatisticsBo workLogStatisticsBo = new WorkLogStatisticsBo();
					workLogStatisticsBo.setEmpName((String) obj[0]);
					if (obj[1] != null) {
						workLogStatisticsBo.setDatetemp((String) obj[1]);
					}
					workLogStatisticsBo.setEmpId((String) obj[2]);
					workLogStatisticsBoList.add(workLogStatisticsBo);
				}
			}
			return workLogStatisticsBoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AllWorkLogStatisticsBo> getAllWorkLogStatisticsBoList(Map<String, Object> params) throws Exception {
		try {
			List<AllWorkLogStatisticsBo> allWorkLogStatisticsBoList = new ArrayList<AllWorkLogStatisticsBo>();
			StringBuilder hql = attendanceHqlProvider.findAllWorkLogStatisticsBoList(params);
			List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
			if (null != objectList && objectList.size() > 0) {
				for (Object[] obj : objectList) {
					AllWorkLogStatisticsBo allWorkLogStatisticsBo = new AllWorkLogStatisticsBo();
					allWorkLogStatisticsBo.setEmpName((String) obj[0]);
					if (obj[1] != null) {
						allWorkLogStatisticsBo.setNum(Double.parseDouble(String.valueOf(obj[1])));
					}
					allWorkLogStatisticsBo.setDatetemp((String) obj[2]);
					allWorkLogStatisticsBo.setEmpcodeAndDate((String) obj[3]);
					allWorkLogStatisticsBo.setEmpId((String) obj[4]);
					allWorkLogStatisticsBoList.add(allWorkLogStatisticsBo);
				}
			}
			return allWorkLogStatisticsBoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long findDataCountByHql(StringBuilder hql, String alilasName, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findDataCountByHqlOrginial(hql, alilasName, params);
	}
}