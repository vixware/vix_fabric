package com.vix.nvix.oa.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.adminMg.entity.ProjectManagement;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: WorkPlansAction
 * @Description: 工作计划
 * @author bobchen
 * @date 2016年3月18日 上午9:56:07
 *
 */
@Controller
@Scope("prototype")
public class WorkPlansAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private ProjectManagement projectManagement;
	private List<ProjectManagement> projectManagementList;
	private EvaluationReview evaluationReview;
	private List<EvaluationReview> evaluationReviewsList;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;
	// 页面跳转来源
	private String source;
	private String employeeIds;
	private String employeeNames;
	private List<Employee> employeeList;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goPlanList() {
		return "goPlanList";
	}

	/* 查询下属工作计划 */
	public void goWorkPlans() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee e = getEmployee();
			if (e != null) {
				// 标题
				String proposalTitle = getDecodeRequestParameter("title");
				if (StringUtils.isNotEmpty(proposalTitle)) {
					params.put("proposalTitle", "%" + proposalTitle + "%");
				}
				// 发布人
				String uploadPersonName = getDecodeRequestParameter("uploadPersonName");
				if (StringUtils.isNotEmpty(uploadPersonName)) {
					params.put("uploadPersonName", "%" + uploadPersonName + "%");
				}
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findProjectManagementPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 我的工作计划
	public void goMyProjectManagement() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee e = getEmployee();
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				// 标题
				String proposalTitle = getDecodeRequestParameter("title");
				if (StringUtils.isNotEmpty(proposalTitle)) {
					params.put("proposalTitle," + SearchCondition.ANYLIKE, proposalTitle);
				}
				// 发布人
				String uploadPersonName = getDecodeRequestParameter("uploadPersonName");
				if (StringUtils.isNotEmpty(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				/** 根据时间进行倒序排序 */
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderField("initiateTime");
					pager.setOrderBy("desc");
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, ProjectManagement.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转工作计划页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				projectManagement = vixntBaseService.findEntityById(ProjectManagement.class, id);
				if (projectManagement != null && projectManagement.getEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : projectManagement.getEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			} else {
				projectManagement = new ProjectManagement();
				projectManagement.setInitiateTime(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改工作计划操作 */
	public String saveOrUpdate() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(projectManagement.getId())) {
				isSave = false;
			}
			Employee employee = getEmployee();
			if (employee != null) {
				projectManagement.setEmployee(employee);
				projectManagement.setUploadPersonId(employee.getId());
				projectManagement.setUploadPersonName(employee.getName());
				projectManagement.setUploadPerson(employee.getName());
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee e : employeeList) {
						subEmployees.add(e);
					}
				}
				projectManagement.setEmployees(subEmployees);
			}
			initEntityBaseController.initEntityBaseAttribute(projectManagement);
			projectManagement = vixntBaseService.merge(projectManagement);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			ProjectManagement pb = vixntBaseService.findEntityById(ProjectManagement.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void projectManagementEvents() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			Employee e = getEmployee();
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				projectManagementList = vixntBaseService.findAllByConditions(ProjectManagement.class, params);
				if (projectManagementList != null && projectManagementList.size() > 0) {
					JSONArray array = new JSONArray();
					for (ProjectManagement projectManagement : projectManagementList) {
						JSONObject object = new JSONObject();
						object.put("id", projectManagement.getId());
						object.put("title", projectManagement.getProposalTitle());
						if (projectManagement.getInitiateTime() != null) {
							object.put("start", dateFormat.format(projectManagement.getInitiateTime()));
						}
						if (projectManagement.getOverTime() != null) {
							object.put("end", dateFormat.format(projectManagement.getOverTime()));
						}
						object.put("allDay", "false");
						object.put("className", "[\"event\",\"bg-color-greenLight\"]");
						object.put("icon", "fa-info");
						array.add(object);
					}
					json = array.toString();
				}
			}
			if (StringUtils.isNotEmpty(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 根据ID查询工作计划数据 */
	public String goViewPlan() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				projectManagement = vixntBaseService.findEntityById(ProjectManagement.class, id);
				if (projectManagement.getSubEvaluationReviews() != null && projectManagement.getSubEvaluationReviews().size() > 0) {
					evaluationReviewNum = projectManagement.getPostil().size();
					evaluationReviewsList = new ArrayList<EvaluationReview>();
					evaluationReviewsList.addAll(projectManagement.getSubEvaluationReviews());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewPlan";
	}

	/**
	 * 批注
	 */
	public void saveOrUpdatePostil() {
		try {
			if (projectManagement != null && StringUtils.isNotEmpty(projectManagement.getId())) {
				evaluationReview.setProjectManagement(projectManagement);
				Employee employee = getEmployee();
				if (employee != null) {
					evaluationReview.setEmployee(employee);
					evaluationReview.setUploadPersonId(employee.getId());
					evaluationReview.setUploadPersonName(employee.getName());
					evaluationReview.setUploadPerson(employee.getName());
				}
				initEntityBaseController.initEntityBaseAttribute(evaluationReview);
				evaluationReview = vixntBaseService.merge(evaluationReview);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public ProjectManagement getProjectManagement() {
		return projectManagement;
	}

	public void setProjectManagement(ProjectManagement projectManagement) {
		this.projectManagement = projectManagement;
	}

	public List<ProjectManagement> getProjectManagementList() {
		return projectManagementList;
	}

	public void setProjectManagementList(List<ProjectManagement> projectManagementList) {
		this.projectManagementList = projectManagementList;
	}

	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public List<EvaluationReview> getEvaluationReviewsList() {
		return evaluationReviewsList;
	}

	public void setEvaluationReviewsList(List<EvaluationReview> evaluationReviewsList) {
		this.evaluationReviewsList = evaluationReviewsList;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

}
