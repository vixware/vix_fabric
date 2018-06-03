package com.rest.app.project;

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
import com.vix.mdm.pm.common.entity.Project;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 
 * @ClassFullName com.rest.app.project.AppProjectController
 *
 * @author bjitzhang
 *
 * @date 2016年2月1日
 *
 */

@Controller
@RequestMapping(value = "restService/app/project")
public class AppProjectController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取项目列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findProjectList.rs", method = RequestMethod.POST)
	public void findProjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String status = request.getParameter("status");
		if (StringUtils.isNotEmpty(status)) {
			params.put("status", status);
		}
		List<Project> projectList = traceabilityService.findAllDataByConditions(Project.class, params);
		List<Project> pList = new ArrayList<Project>();
		for (Project project : projectList) {
			Project t = new Project();
			BeanUtils.copyProperties(project, t, new String[] {});
			pList.add(t);
		}
		renderResult(response, pList);
	}

	/**
	 * 获取项目任务列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findProjectTaskList.rs", method = RequestMethod.POST)
	public void findProjectTaskList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String id = request.getParameter("id");
		params.put("project.id", id);
		String status = request.getParameter("status");
		if (StringUtils.isNotEmpty(status)) {
			params.put("status", status);
		}
		List<VixTask> vixTaskList = traceabilityService.findAllDataByConditions(VixTask.class, params);
		List<VixTask> tList = new ArrayList<VixTask>();
		for (VixTask vixTask : vixTaskList) {
			VixTask t = new VixTask();
			BeanUtils.copyProperties(vixTask, t, new String[] {});
			tList.add(t);
		}
		renderResult(response, tList);
	}

	/**
	 * 获取任务附件列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findProjectUploaderList.rs", method = RequestMethod.POST)
	public void findProjectUploaderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String id = request.getParameter("id");
		params.put("taskDefinition.id", id);
		List<Uploader> uploaderList = traceabilityService.findAllDataByConditions(Uploader.class, params);
		List<Uploader> uList = new ArrayList<Uploader>();
		for (Uploader uploader : uploaderList) {
			Uploader t = new Uploader();
			BeanUtils.copyProperties(uploader, t, new String[] {});
			uList.add(t);
		}
		renderResult(response, uList);
	}

	/**
	 * 获取项目的分页数据
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param project
	 * @throws Exception
	 */
	@RequestMapping(value = "findProjectPager.rs", method = RequestMethod.POST)
	public void findProjectPager(HttpServletRequest request, HttpServletResponse response, Pager pager, Project project) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String status = request.getParameter("status");
		if (StringUtils.isNotEmpty(status)) {
			params.put("status", status);
		}
		if (StrUtils.isNotEmpty(project.getName())) {
			params.put("name", project.getName());
		}
		if (StrUtils.isNotEmpty(project.getCode())) {
			params.put("code", project.getCode());
		}
		Pager pagerRes = traceabilityService.findPagerByHqlConditions(pager, Project.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 根据ID获取单个项目
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Project t = null;
		if (StringUtils.isNotEmpty(id)) {
			Project project = traceabilityService.findEntityByAttribute(Project.class, "id", id);
			String message = "";
			if (project == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new Project();
				BeanUtils.copyProperties(project, t, new String[] {});
			}
		}
		renderResult(response, t);
	}

	/**
	 * 保存项目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "saveProject.rs", method = RequestMethod.POST)
	public void saveProject(HttpServletRequest request, HttpServletResponse response, Project project) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				//String field, String errorKey, String errorMessage
				//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
				//如果是自定义的特殊格式教研，请使用 addError
				//addError("moduleCodeKey", "模块编码重复！");
				//System.out.println(getMsgMap());
				validateRequiredString("code", "codeMsg", "请输项目编码!");
				validateRequiredString("name", "nameMsg", "请输项目名称!");
			}
		}, project, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}

		project = traceabilityService.merge(project);
		renderResult(response, project);
	}

	/**
	 * 删除项目
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
			Project project = traceabilityService.findEntityByAttribute(Project.class, "id", id);
			if (project == null) {
				throw new ValidateException("没有查询到待删除的项目id！");
			} else {
				traceabilityService.deleteByEntity(project);
			}
		}
		renderResult(response, null);
	}

	/**
	 * 获取项目数量
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findProjectListCount.rs", method = RequestMethod.POST)
	public void findProjectListCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String status = request.getParameter("status");
		if (StringUtils.isNotEmpty(status)) {
			params.put("status", status);
		}
		List<Project> projectList = traceabilityService.findAllByConditions(Project.class, params);
		if (projectList != null && projectList.size() > 0) {
			renderResult(response, projectList.size());
		} else {
			renderResult(response, 0);
		}
	}

	/**
	 * 获取项目人员的数量
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findProjectEmployeeCount.rs", method = RequestMethod.POST)
	public void findProjectEmployeeCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			Project project = traceabilityService.findEntityByAttribute(Project.class, "id", id);
			String message = "";
			if (project == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
					renderResult(response, project.getSubEmployees().size());
				} else {
					renderResult(response, 0);
				}
			}
		}
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
			Project project = traceabilityService.findEntityByAttribute(Project.class, "id", id);
			if (project == null) {
			} else {
				project.setIsNew("O");
				traceabilityService.mergeOriginal(project);
			}
		}
		renderResult(response, null);
	}
}
