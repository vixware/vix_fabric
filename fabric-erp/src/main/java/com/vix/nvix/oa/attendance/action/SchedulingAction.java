/**
 * 
 */
package com.vix.nvix.oa.attendance.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.properties.Utils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.oa.attendance.entity.AttendanceRuleSet;
import com.vix.nvix.oa.attendance.entity.PeriodRule;
import com.vix.nvix.oa.attendance.entity.Scheduling;

/**
 * @author Bluesnow 2016年7月15日
 * 
 *         排班管理
 */

@Controller
@Scope("prototype")
public class SchedulingAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private Scheduling scheduling;
	private String id;
	private String startTime;
	private String endTime;
	private String treeType;
	private String treeId;
	private String empCode;
	private String empName;
	private String orgId;
	private String unitId;
	private String empId;
	private List<PeriodRule> periodRuleList;
	private List<AttendanceRuleSet> attRuleSetList;

	@Override
	public String goList() {
		return GO_LIST;
	}

	@SuppressWarnings("unchecked")
	public void getSchedulingJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			if (treeType.equals("C")) {
				orgId = treeId;
			} else if (treeType.equals('O')) {
				unitId = treeId;
			} else {
				empId = treeId;
			}

			if (StrUtils.isNotEmpty(startTime) && StrUtils.isNotEmpty(endTime)) {
				params.put("scheduleStartTime," + SearchCondition.BETWEENAND,startTime + "!" + endTime);
			}

			String empCode = getDecodeRequestParameter("empCode");
			if (StrUtils.isNotEmpty(empCode)) {
				params.put("scheduling.emp.code," + SearchCondition.ANYLIKE,empCode);
			}

			String empName = getDecodeRequestParameter("empName");
			if (StrUtils.isNotEmpty(empName)) {
				params.put("scheduling.emp.name," + SearchCondition.ANYLIKE,empName);
			}

			baseHibernateService.findPagerByHqlConditions(pager, Scheduling.class,params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new Scheduling());
				}
			}

			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			periodRuleList = baseHibernateService.findAllByEntityClass(PeriodRule.class);
			attRuleSetList = baseHibernateService.findAllByEntityClass(AttendanceRuleSet.class);
			if (StrUtils.isNotEmpty(id)) {
				scheduling = baseHibernateService.findEntityById(Scheduling.class,id);
			} else {
				scheduling = new Scheduling();
				if (treeType.equals("C")) {
					Organization org = baseHibernateService.findEntityById(Organization.class, treeId);
					if (Utils.isNotEmpty(org)) {
						scheduling.setOrg(org);
					}
				} else if (treeType.equals("O")) {
					OrganizationUnit nuit = baseHibernateService.findEntityById(OrganizationUnit.class, treeId);
					if (Utils.isNotEmpty(nuit)) {
						scheduling.setUnit(nuit);
					}
				} else {
					Employee emp = baseHibernateService.findEntityById(Employee.class, treeId);
					if (Utils.isNotEmpty(emp)) {
						scheduling.setEmp(emp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(scheduling.getId())) {
				isSave = false;
				scheduling.setUpdateTime(new Date());
			} else {
				scheduling.setCreator(SecurityUtil.getCurrentUserName());
				scheduling.setCreateTime(new Date());
				scheduling.setIsTemp("0");
			}
			loadCommonData(scheduling);
			/** 检查空值对象 */
			String[] attrArray = {"periodRule","attRuleSet","org","unit","emp"};
			checkEntityNullValue(scheduling, attrArray);

			baseHibernateService.merge(scheduling);

			if (isSave) {
				setMessage(scheduling.getId() + ":" + SAVE_SUCCESS);
			} else {
				setMessage(scheduling.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0:" + SAVE_FAIL);
			} else {
				this.setMessage("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Scheduling getScheduling() {
		return scheduling;
	}

	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling;
	}

	public List<PeriodRule> getPeriodRuleList() {
		return periodRuleList;
	}

	public void setPeriodRuleList(List<PeriodRule> periodRuleList) {
		this.periodRuleList = periodRuleList;
	}

	public List<AttendanceRuleSet> getAttRuleSetList() {
		return attRuleSetList;
	}

	public void setAttRuleSetList(List<AttendanceRuleSet> attRuleSetList) {
		this.attRuleSetList = attRuleSetList;
	}

}
