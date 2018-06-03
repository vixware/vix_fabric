package com.rest.app.oa.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 任务反馈
 * 
 * @ClassFullName com.rest.app.oa.task.AppExecutionFeedbackController
 *
 * @author bjitzhang
 *
 * @date 2016年1月26日
 *
 */

@Controller
@RequestMapping(value = "restService/app/executionfeedback")
public class AppExecutionFeedbackController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取任务反馈列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findExecutionFeedbackList.rs", method = RequestMethod.POST)
	public void findExecutionFeedbackList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String taskid = request.getParameter("taskid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskDefinition.id", taskid);
		List<ExecutionFeedback> executionFeedbackList = traceabilityService.findAllDataByConditions(ExecutionFeedback.class, params);
		List<ExecutionFeedback> eList = new ArrayList<ExecutionFeedback>();
		for (ExecutionFeedback executionFeedback : executionFeedbackList) {
			ExecutionFeedback e = new ExecutionFeedback();
			BeanUtils.copyProperties(executionFeedback, e, new String[] {});
			eList.add(e);
		}
		renderResult(response, eList);
	}

}
