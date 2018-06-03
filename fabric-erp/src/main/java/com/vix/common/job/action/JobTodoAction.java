package com.vix.common.job.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.BaseAction;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.job.entity.JobTodo;
import com.vix.common.job.wraper.ConditionPath;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class JobTodoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String opinion;
	private String conditionRule;
	@Autowired
	private IStandardActivitiService standardActivitiService;
	private JobTodo jobTodo;
	private List<ConditionPath> listConditionPath;
	private String currentDate;

	public String goSingleList() {
		try {
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("createTime");
			Object objUser = getSession().getAttribute("userInfo");
			if (null != objUser && objUser instanceof UserAccount) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date startDate = null;
				Date endDate = null;
				if (currentDate != null && !"".equals(currentDate)) {
					Date cd = sdf1.parse(currentDate);
					String cdStr = sdf1.format(cd);
					startDate = sdf2.parse(cdStr + " 00:00:01");
					endDate = sdf2.parse(cdStr + " 23:59:59");
				}
				UserAccount user = (UserAccount) objUser;
				pager = standardActivitiService.getAgencyTaskList(user.getId(), pager, startDate, endDate);
				for (Object obj : pager.getResultList()) {
					if (null != obj) {
						JobTodo jobTodo = (JobTodo) obj;
						if (null != jobTodo.getSourceClass() && !"".equals(jobTodo.getSourceClass())) {
							jobTodo.setSourceClass(getText(jobTodo.getSourceClass()));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goAudit() {
		try {
			if (StringUtils.isEmpty(id) && !id.equals("0")) {// if (id == null
																// || id < 0) {
				setMessage("任务获取失败!");
				return UPDATE;
			}
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
			listConditionPath = standardActivitiService.getConditionPathList(id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}

	public String goBillAudit() {
		try {
			if (StringUtils.isEmpty(id)) {// if (id == null
				renderText("任务获取失败!");
				return UPDATE;
			}
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBillAudit";
	}

	public void audit() {
		boolean isSave = true;
		try {
			if (StringUtils.isEmpty(id)) {
				renderText("任务id未获取到!");
			}
			if (null == opinion || "".equals(opinion)) {
				renderText("审批意见未填写!");
			}
			Map<String, String> params = new HashMap<String, String>();
			if (null != conditionRule && !"".equals(conditionRule)) {
				if (conditionRule.contains("==")) {
					conditionRule = conditionRule.replace('$', ' ');
					conditionRule = conditionRule.replace('{', ' ');
					conditionRule = conditionRule.replace('}', ' ');
					String[] cr = conditionRule.split("==");
					if (conditionRule.contains("\"")) {
						String value = cr[0].replace('"', ' ');
						params.put(cr[0].trim(), value.trim());
					}
				}
			}
			Object objUser = getSession().getAttribute("userInfo");
			String userId = "";
			if (null != objUser && objUser instanceof UserAccount) {
				UserAccount user = (UserAccount) objUser;
				userId = String.valueOf(user.getId());
			}
			// standardActivitiService.submitTask("", id.toString(), params,
			// false);
			RestTemplate restTemplate = new RestTemplate();
			String temp = restTemplate.postForObject("http://127.0.0.1:8888/vformnt/activiti/task/submitTask?userId=" + userId + "&taskId=" + id.toString(), String.class, String.class, params);
			System.out.println(temp);
			FlowApprovalOpinion ao = new FlowApprovalOpinion();
			Employee emp = getEmployee();
			Employee employee = new Employee();
			employee.setId(emp.getId());
			ao.setApprovalPerson(employee);
			ao.setOpinion(opinion);

			renderText("审批成功!");
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public JobTodo getJobTodo() {
		return jobTodo;
	}

	public void setJobTodo(JobTodo jobTodo) {
		this.jobTodo = jobTodo;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getConditionRule() {
		return conditionRule;
	}

	public void setConditionRule(String conditionRule) {
		this.conditionRule = conditionRule;
	}

	public List<ConditionPath> getListConditionPath() {
		return listConditionPath;
	}

	public void setListConditionPath(List<ConditionPath> listConditionPath) {
		this.listConditionPath = listConditionPath;
	}

	public int getLinkDtoSize() {
		if (null != listConditionPath) {
			return listConditionPath.size();
		}
		return 0;
	}
}
