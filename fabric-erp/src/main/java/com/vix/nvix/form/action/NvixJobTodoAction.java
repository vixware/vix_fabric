package com.vix.nvix.form.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.job.entity.JobTodo;
import com.vix.common.job.wraper.ConditionPath;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class NvixJobTodoAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String opinion;
	private String conditionRule;
	private JobTodo jobTodo;
	private List<ConditionPath> listConditionPath;
	private String currentDate;

	@SuppressWarnings("unchecked")
	public void goSingleList() {
		try {
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("createTime");
			pager = standardActivitiService.getAgencyTaskList(SecurityUtil.getCurrentUserId(), pager, null, null);
			if (pager.getResultList().size() < 12) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 12 - listSize; i++) {
					pager.getResultList().add(new JobTodo());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void goHomeSingleList() {
		try {
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("createTime");
			pager = standardActivitiService.getAgencyTaskList(SecurityUtil.getCurrentUserId(), pager, null, null);
			if (pager.getResultList().size() < 5) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 5 - listSize; i++) {
					pager.getResultList().add(new JobTodo());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goBillAudit() {
		try {
			if (StringUtils.isEmpty(id)) {
				renderText("任务获取失败!");
			}
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBillAudit";
	}
	public String goAudit() {
		try {
			if (StringUtils.isEmpty(id)) {
				renderText("任务获取失败!");
			}
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
			listConditionPath = standardActivitiService.getConditionPathList(id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}

	public void audit() {
		try {
			if (StringUtils.isEmpty(id)) {
				renderText("任务id未获取到!");
			}
			if (StringUtils.isEmpty(opinion)) {
				renderText("审批意见未填写!");
			}
			Map<String, String> params = new HashMap<String, String>();
			if (StringUtils.isNotEmpty(conditionRule)) {
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
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
			RestTemplate restTemplate = new RestTemplate();
			String temp = restTemplate.postForObject(flow_ip + "/activiti/task/submitTask?userId=" + SecurityUtil.getCurrentUserId() + "&taskId=" + id.toString(), String.class, String.class, params);
			System.out.println(temp);
			if (StringUtils.isNotEmpty(temp)) {
				JSONObject jsonObject = JSONObject.fromObject(temp);
				if (jsonObject.containsKey("status") && Integer.parseInt(jsonObject.get("status").toString()) == 1) {
					FlowApprovalOpinion ao = new FlowApprovalOpinion();
					Employee emp = getEmployee();
					ao.setApprovalPerson(emp);
					ao.setSourceClassPk(jobTodo.getSourceClassPk());
					ao.setOpinion(opinion);
					ao.setCreateTime(new Date());
					ao = vixntBaseService.merge(ao);
					renderText("审批成功!");
				} else {
					renderText("审批失败!");
				}
			} else {
				renderText("审批失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("审批失败!");
		}
	}
	public String goReject() {
		try {
			if (StringUtils.isEmpty(id)) {
				renderText("任务获取失败!");
			}
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goReject";
	}
	public void reject() {
		try {
			if (StringUtils.isEmpty(id)) {
				renderText("任务id未获取到!");
			}
			Map<String, String> params = new HashMap<String, String>();
			if (StringUtils.isNotEmpty(conditionRule)) {
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
			jobTodo = standardActivitiService.findJobTodoByTaskId(id);
			RestTemplate restTemplate = new RestTemplate();
			String temp = restTemplate.postForObject(flow_ip + "/activiti/task/reject?userId=" + SecurityUtil.getCurrentUserId() + "&taskId=" + id.toString() + "&taskType=B", String.class, String.class);
			if (StringUtils.isNotEmpty(temp)) {
				JSONObject jsonObject = JSONObject.fromObject(temp);
				if (jsonObject.containsKey("status") && Integer.parseInt(jsonObject.get("status").toString()) == 1) {
					FlowApprovalOpinion ao = new FlowApprovalOpinion();
					Employee emp = getEmployee();
					ao.setApprovalPerson(emp);
					ao.setSourceClassPk(jobTodo.getSourceClassPk());
					ao.setOpinion(opinion);
					ao.setCreateTime(new Date());
					ao = vixntBaseService.merge(ao);
					renderText("驳回成功!");
				} else {
					renderText("驳回失败!");
				}
			} else {
				renderText("驳回失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("驳回失败!");
		}
	}
	public void deleteById() {
		try {
			JobTodo jobTodo = vixntBaseService.findEntityById(JobTodo.class, id);
			if (null != jobTodo) {
				vixntBaseService.deleteByEntity(jobTodo);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
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
