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
import com.vix.hr.organization.entity.Employee;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 获取人员列表
 * 
 * @ClassFullName com.rest.app.oa.task.AppEmployeeController
 *
 * @author bjitzhang
 *
 * @date 2016年1月26日
 *
 */

@Controller
@RequestMapping(value = "restService/app/employee")
public class AppEmployeeController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取人员列表
	 * 
	 * @param request
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping(value = "findEmployeeList.rs", method = RequestMethod.POST)
	public void findEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Employee> employeeList = traceabilityService.findAllDataByConditions(Employee.class, params);
		List<Employee> eList = new ArrayList<Employee>();
		for (Employee employee : employeeList) {
			Employee t = new Employee();
			BeanUtils.copyProperties(employee, t, new String[] {});
			eList.add(t);
		}
		renderResult(response, eList);
	}

	/**
	 * 获取任务的参与人员
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findEmployeeListByTask.rs", method = RequestMethod.POST)
	public void findEmployeeListByTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder pageHql = new StringBuilder("select hentity from Employee pc right join pc.vixTasks hentity where hentity.id = '");
		pageHql.append(taskId);
		List<Employee> employeeList = traceabilityService.findAllEntityByHql(pageHql, params);
		List<Employee> eList = new ArrayList<Employee>();
		for (Employee employee : employeeList) {
			Employee t = new Employee();
			BeanUtils.copyProperties(employee, t, new String[] {});
			eList.add(t);
		}
		renderResult(response, eList);
	}

	/**
	 * 获取人员详细信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Employee e = null;
		if (StringUtils.isNotEmpty(id)) {
			Employee employee = traceabilityService.findEntityByAttribute(Employee.class, "id", id);
			String message = "";
			if (employee == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				e = new Employee();
				BeanUtils.copyProperties(employee, e, new String[] { "contactTypes", "userAccounts", "userGroups", "businessOrgs", "pmOrgs", "orgPositions", "organizationUnit", "salaryProjectGrantSet", "channelDistributor", "mailServer", "mailInfos", "languageAbilities", "computerLevels", "workQualifies", "degrees", "trainings", "techtitles", "techachieves", "retireInfos", "redeployInfos", "aboards", "familyRelationships", "disabilityDiseaseInfors", "workRecords", "politicalStatus", "soldierTuneInfos", "awardInfos", "punInfos", "partPostionInfos", "linkmans", "supplier", "attendanceRecords", "shouldAttendances", "punchCards", "hrTitleSys", "hrPostSys", "vixTasks" });
			}
		}
		renderResult(response, e);
	}
}
