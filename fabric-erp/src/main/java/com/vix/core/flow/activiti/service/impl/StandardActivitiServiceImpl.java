package com.vix.core.flow.activiti.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e6soft.activiti.exception.E6ActivitiException;
import com.e6soft.activiti.service.cmd.CallBackTaskCmd;
import com.e6soft.activiti.service.cmd.RejectTaskCmd;
import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.JobTodo;
import com.vix.common.job.wraper.ConditionPath;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.web.Pager;

@Transactional(readOnly = false)
@Service
public class StandardActivitiServiceImpl implements IStandardActivitiService {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ProcessEngine processEngine;

	@Override
	public String startSubmit(String userId, String deploymentId, Map<String, Object> requestMap) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(deploymentId, requestMap);
		String billType = requestMap.get("BillType").toString();
		String billId = requestMap.get("BillId").toString();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).processVariableValueEquals("BillType", billType).processVariableValueEquals("BillId", billId).list();
		for (Task task : tasks) {
			taskService.setVariables(task.getId(), requestMap);
		}
		return processInstance.getActivityId();
	}

	@Override
	public boolean isHistory(String taskId) {
		HistoricTaskInstance task = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new E6ActivitiException("找不到任务，taskId[%s]", taskId);
		} else {
			return task.getEndTime() == null ? false : true;
		}
	}

	@Override
	public void submitTask(String userId, String taskId, Map<String, String> requestMap, boolean saveFormData) {
		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		if (task != null) {
			Map<String, Object> formData = new HashMap<String, Object>();
			formData.putAll(requestMap);
			task.getProcessDefinitionId();
			formData.put("complete", requestMap.get("form_data_1_complete"));
			processEngine.getTaskService().complete(taskId, formData);
		} else {
			throw new ActivitiException(String.format("can not find the task [%s]", taskId));
		}
	}

	@Override
	public void rejectTask(String userId, String taskId, Map<String, String> requestMap, boolean saveFormData) {
		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		if (task != null) {
			Command<Integer> cmd = new RejectTaskCmd(taskId);
			Integer result = processEngine.getManagementService().executeCommand(cmd);
			if (result == 2) {
				throw new ActivitiException("任务不能退回");
			}
		} else {
			throw new ActivitiException(String.format("任务不存在，taskid[%s]", taskId));
		}
	}

	@Override
	public void callBackTask(String userId, String taskId) {
		Command<Integer> cmd = new CallBackTaskCmd(taskId);
		Integer result = processEngine.getManagementService().executeCommand(cmd);
		if (result == 2) {
			throw new ActivitiException("任务不能收回");
		}
	}

	@Override
	public Pager getAgencyTaskList(String userId, Pager pager, Date startDate, Date endDate) {
		int totalCount = 0;
		List<Task> listTask = new ArrayList<Task>();
		TaskQuery tq = taskService.createTaskQuery().taskAssignee(userId);
		if (null != startDate) {
			tq = tq.taskCreatedAfter(startDate);
		}
		if (null != endDate) {
			tq = tq.taskCreatedBefore(endDate);
		}
		if (null != pager && pager.getPageNo() > 0 && pager.getPageSize() > 0) {
			listTask = tq.listPage((pager.getPageNo() - 1) * pager.getPageSize(), pager.getPageSize());
		} else {
			pager = new Pager();
			listTask = tq.list();
		}
		totalCount = taskService.createTaskQuery().taskAssignee(userId).list().size();
		pager.setTotalCount(totalCount);
		List<JobTodo> list = new ArrayList<JobTodo>();
		for (Task task : listTask) {
			if (null != task) {
				JobTodo jt = new JobTodo();
				Map<String, Object> flowParam = this.findProcessMapByProcessInstanceId(task.getProcessInstanceId());
				if (flowParam.containsKey("BillTitle")) {
					jt.setName(String.valueOf(flowParam.get("BillTitle")));
				}
				if (flowParam.containsKey("BillType")) {
					jt.setSourceClass(BillType.getCodeText(String.valueOf(flowParam.get("BillType"))));
					jt.setUrl(BillType.getUrl(String.valueOf(flowParam.get("BillType"))));
				}
				if (flowParam.containsKey("BillId")) {
					jt.setSourceClassPk(String.valueOf(flowParam.get("BillId")).trim());
				}
				jt.setJobName(task.getName());
				jt.setCreateTime(task.getCreateTime());
				jt.setId(task.getId());
				list.add(jt);
			}
		}
		pager.setResultList(list);
		return pager;
	}

	/** 解码 */
	public String decode(String str, String enc) throws Exception {
		str = URLDecoder.decode(str, enc);
		return str;
	}

	@Override
	public List<ConditionPath> getConditionPathList(String taskId) {
		List<ConditionPath> result = new ArrayList<ConditionPath>();
		Task task = this.processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		List<ActivityImpl> activitiList = processDefinition.getActivities();
		for (ActivityImpl activity : activitiList) {
			String id = activity.getId();
			if (task.getTaskDefinitionKey().equals(id)) {
				List<PvmTransition> ll = activity.getOutgoingTransitions();
				for (PvmTransition pt : ll) {
					if ("exclusiveGateway".equals(pt.getDestination().getProperty("type"))) {
						ActivityImpl target = (ActivityImpl) pt.getDestination();
						List<PvmTransition> toList = target.getOutgoingTransitions();
						for (PvmTransition ptv : toList) {
							ConditionPath dto = new ConditionPath();
							dto.setId(ptv.getId());
							if (null == ptv.getProperty("name")) {
								dto.setName("审批");
							} else {
								dto.setName(ptv.getProperty("name").toString());
							}
							if (null != ptv.getProperty("conditionText")) {
								dto.setConditionRule(ptv.getProperty("conditionText").toString());
							}
							result.add(dto);
						}
					} else {
						ConditionPath dto = new ConditionPath();
						dto.setId(pt.getId());
						if (null == pt.getProperty("name")) {
							dto.setName("审批");
						} else {
							dto.setName(pt.getProperty("name").toString());
						}
						if (null != pt.getProperty("conditionText")) {
							dto.setConditionRule(pt.getProperty("conditionText").toString());
						}
						result.add(dto);
					}
				}
			}
		}
		return result;
	}

	@Override
	public JobTodo findJobTodoByTaskId(String taskId) {
		JobTodo jt = new JobTodo();
		Task task = taskService.createTaskQuery().taskId(taskId.toString()).singleResult();
		if (null != task) {
			Map<String, Object> flowParam = this.findProcessMapByProcessInstanceId(task.getProcessInstanceId());
			if (flowParam.containsKey("BillTitle")) {
				jt.setName(flowParam.get("BillTitle").toString());
			}
			if (flowParam.containsKey("BillType")) {
				//jt.setSourceClass(BillType.getUrl(String.valueOf(flowParam.get("BillType"))));
				jt.setUrl(BillType.getUrl(String.valueOf(flowParam.get("BillType"))));
			}
			if (flowParam.containsKey("BillId")) {
				jt.setSourceClassPk(flowParam.get("BillId").toString().trim());
			}
			jt.setJobName(task.getName());
			jt.setCreateTime(task.getCreateTime());
			jt.setId(task.getId());
		}
		return jt;
	}

	@Override
	public String findProcessDefinitionIdByCode(String code) {
		List<ProcessDefinition> pdList = repositoryService.createProcessDefinitionQuery().processDefinitionCategory(code).list();
		int version = 0;
		ProcessDefinition processDefinition = null;
		for (ProcessDefinition pd : pdList) {
			if (null != pd) {
				if (pd.getVersion() > version) {
					version = pd.getVersion();
					processDefinition = pd;
				}
			}
		}
		if (null != processDefinition) {
			return processDefinition.getId();
		} else {
			return "";
		}
	}

	@Override
	public Map<String, Object> findProcessMapByProcessInstanceId(String processInstanceId) {
		return runtimeService.getVariables(processInstanceId);
	}

}
