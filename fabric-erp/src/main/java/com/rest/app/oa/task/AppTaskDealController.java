package com.rest.app.oa.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.rest.core.exception.RestException;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 任务分配
 * 
 * @ClassFullName com.rest.app.oa.task.AppTaskDealController
 *
 * @author bjitzhang
 *
 * @date 2016年1月26日
 *
 */

@Controller
@RequestMapping(value = "restService/app/taskdeal")
public class AppTaskDealController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取任务列表
	 * 
	 * @param request
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping(value = "findTaskList.rs", method = RequestMethod.POST)
	public void findTaskList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<VixTask> vixTaskList = traceabilityService.findAllDataByConditions(VixTask.class, params);
		List<VixTask> tList = new ArrayList<VixTask>();
		for (VixTask vixTask : vixTaskList) {
			VixTask t = new VixTask();
			BeanUtils.copyProperties(vixTask, t, new String[] { "organizationUnits", "employees", "employees", "taskSourceType", "taskType", "difficultyCoefficient", "taskLevel", "completionMark", "executionFeedbacks", "uploader", "subCategorys", "parentCategory" });
			tList.add(t);
		}
		renderResult(response, tList);
	}

	/**
	 * 获取任务分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param vixTask
	 * @throws Exception
	 */
	@RequestMapping(value = "findTaskPager.rs", method = RequestMethod.POST)
	public void findTaskPager(HttpServletRequest request, HttpServletResponse response, Pager pager, VixTask vixTask) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(vixTask.getName())) {
			params.put("name", vixTask.getName());
		}
		if (StrUtils.isNotEmpty(vixTask.getCode())) {
			params.put("code", vixTask.getCode());
		}
		Pager pagerRes = traceabilityService.findPagerByHqlConditions(pager, VixTask.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 根据ID获取单个任务
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		VixTask t = null;
		if (StringUtils.isNotEmpty(id)) {
			VixTask taskDefinition = traceabilityService.findEntityByAttribute(VixTask.class, "id", id);
			String message = "";
			if (taskDefinition == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new VixTask();
				BeanUtils.copyProperties(taskDefinition, t, new String[] { "organizationUnits", "employees", "employees", "taskSourceType", "taskType", "difficultyCoefficient", "taskLevel", "completionMark", "executionFeedbacks", "uploader", "subCategorys", "parentCategory" });
			}
		}
		renderResult(response, t);
	}

	/**
	 * 保存任务
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTask.rs", method = RequestMethod.POST)
	public void saveTask(HttpServletRequest request, HttpServletResponse response, VixTask vixTask) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				//String field, String errorKey, String errorMessage
				//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
				//如果是自定义的特殊格式教研，请使用 addError
				//addError("moduleCodeKey", "模块编码重复！");
				//System.out.println(getMsgMap());
				validateRequiredString("code", "codeMsg", "请输任务编码!");
				validateRequiredString("name", "nameMsg", "请输任务名称!");
			}
		}, vixTask, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		vixTask = traceabilityService.mergeOriginal(vixTask);
		renderResult(response, vixTask);
	}

	/**
	 * 删除任务
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "delete.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			VixTask taskDefinition = traceabilityService.findEntityByAttribute(VixTask.class, "id", id);
			if (taskDefinition == null) {
				throw new ValidateException("没有查询到待删除的任务id！");
			} else {
				traceabilityService.deleteByEntity(taskDefinition);
			}
		}
		renderResult(response, null);
	}

	/**
	 * 更新状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "update.rs", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			VixTask vixTask = traceabilityService.findEntityByAttribute(VixTask.class, "id", id);
			if (vixTask == null) {
			} else {
				vixTask.setIsNew("O");
				traceabilityService.mergeOriginal(vixTask);
			}
		}
		renderResult(response, null);
	}
}
