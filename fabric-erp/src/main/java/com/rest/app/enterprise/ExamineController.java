/**
 * 
 */
package com.rest.app.enterprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.rest.core.exception.RestException;
import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.JobTodo;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.web.Pager;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 审批接口
 * 
 * @ClassFullName com.rest.app.enterprise.ExamineController
 *
 * @author bjitzhang
 *
 * @date 2016年1月15日
 *
 */
@Controller
@RequestMapping(value = "restService/app/examine")
public class ExamineController extends BaseRestController {

	@Autowired
	private IStandardActivitiService standardActivitiService;
	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;
	@Autowired
	private TaskService taskService;

	/**
	 * 获取待审批任务列表
	 * 
	 * @param request
	 * @param response
	 * @param jobTodo
	 * @throws Exception
	 */
	@RequestMapping(value = "findJobTodoList.rs", method = RequestMethod.POST)
	public void findJobTodoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		List<Task> taskList = new ArrayList<Task>();
		TaskQuery tq = taskService.createTaskQuery().taskAssignee(userId);
		if (tq != null && tq.list() != null) {
			taskList = tq.list();
		}
		List<JobTodo> list = new ArrayList<JobTodo>();
		for (Task task : taskList) {
			if (null != task) {
				JobTodo jt = new JobTodo();
				Map<String, Object> flowParam = standardActivitiService.findProcessMapByProcessInstanceId(task.getProcessInstanceId());
				if (flowParam.containsKey("BillTitle")) {
					jt.setName(String.valueOf(flowParam.get("BillTitle")));
				}
				if (flowParam.containsKey("BillType")) {
					jt.setSourceClass(BillType.getCodeText(String.valueOf(flowParam.get("BillType"))));
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
		renderResult(response, list);
	}

	/**
	 * 获取待审批任务分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param calendars
	 * @throws Exception
	 */
	@RequestMapping(value = "findJobTodoPager.rs", method = RequestMethod.POST)
	public void findJobTodoPager(HttpServletRequest request, HttpServletResponse response, Pager pager) throws Exception {
		String userId = request.getParameter("userId");
		int totalCount = 0;
		List<Task> taskList = new ArrayList<Task>();
		TaskQuery tq = taskService.createTaskQuery().taskAssignee(userId);

		if (null != pager && pager.getPageNo() > 0 && pager.getPageSize() > 0) {
			taskList = tq.listPage((pager.getPageNo() - 1) * pager.getPageSize(), pager.getPageSize());
		} else {
			pager = new Pager();
			taskList = tq.list();
		}
		totalCount = taskService.createTaskQuery().taskAssignee(userId).list().size();
		pager.setTotalCount(totalCount);
		List<JobTodo> list = new ArrayList<JobTodo>();
		for (Task task : taskList) {
			if (null != task) {
				JobTodo jt = new JobTodo();
				Map<String, Object> flowParam = standardActivitiService.findProcessMapByProcessInstanceId(task.getProcessInstanceId());
				if (flowParam.containsKey("BillTitle")) {
					jt.setName(String.valueOf(flowParam.get("BillTitle")));
				}
				if (flowParam.containsKey("BillType")) {
					jt.setSourceClass(BillType.getCodeText(String.valueOf(flowParam.get("BillType"))));
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

		renderResultPager(response, pager);
	}

	/**
	 * 根据ID查询任务
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JobTodo t = null;
		if (StringUtils.isNotEmpty(id)) {
			JobTodo jobTodo = standardActivitiService.findJobTodoByTaskId(id);
			String message = "";
			if (jobTodo == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new JobTodo();
				BeanUtils.copyProperties(jobTodo, t, new String[] { "" });
			}
		}
		renderResult(response, t);
	}

	/**
	 * 更新查看状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "update.rs", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			JobTodo jobTodo = traceabilityService.findEntityByAttribute(JobTodo.class, "id", id);
			if (jobTodo == null) {
			} else {
				jobTodo.setIsNew("O");
				traceabilityService.mergeOriginal(jobTodo);
			}
		}
		renderResult(response, null);
	}

}
