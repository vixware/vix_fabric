package com.vix.nvix.project.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.mdm.pm.common.entity.ProjectRole;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.project.util.ProjectUnit;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.typeSettings.entity.TaskType;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * com.vix.nvix.project.action.NvixProjectAction
 *
 * @author bjitzhang
 *
 * @date 2015年12月18日
 */
@Controller
@Scope("prototype")
public class NvixProjectAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String treeType;
	/**
	 * 项目
	 */
	private VixTask vixTask;
	private String chooseType;
	private String entityName;
	private String employeeIds;
	private String employeeNames;
	private Project project;
	private ProjectRole projectRole;
	private List<Project> projectList;
	private List<Uploader> uploaderList;
	private List<VixTask> vixTaskList;
	private List<Employee> employeeList;
	private String userIdStr = "";
	private String parentId;
	private String imagesId;
	private String executionFeedbackId;
	private ExecutionFeedback executionFeedback;
	private List<Employee> empList;
	private String projectId;
	private String ids;
	/**
	 * 人员数量
	 */
	private Integer employeeNum = 0;
	private Employee e;
	/**
	 * 附件数量
	 */
	private Integer uploaderNum = 0;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;
	/**
	 * 新的截止时间
	 */
	private Date newEndTime;
	private Set<EvaluationReview> evaluationReviewList;
	/**
	 * 进度
	 */
	private Integer schedule;
	private EvaluationReview evaluationReview;
	private List<TaskType> taskTypeList;
	// 页面跳转来源
	private String source;
	private Uploader uploader;

	// 项目列表
	@Override
	public String goList() {
		try {
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isTemp", "1");
				params.put("isDeleted", "0");
				params.put("employeeId", e.getId());
				projectList = vixntBaseService.findProjectByHql(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 新增项目
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
				if (project != null && project.getSubEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : project.getSubEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			} else {
				project = new Project();
				project.setProjectCode(VixUUID.createCode(15));
				// 草稿
				project.setIsTemp("0");
				project = vixntBaseService.merge(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateProjectRole() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				projectRole = vixntBaseService.findEntityById(ProjectRole.class, id);
			} else {
				projectRole = new ProjectRole();
				if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
					if ("P".equals(treeType)) {
						Project project = vixntBaseService.findEntityById(Project.class, parentId);
						if (project != null) {
							projectRole.setProject(project);
						}
					} else if ("R".equals(treeType)) {
						ProjectRole p = vixntBaseService.findEntityById(ProjectRole.class, parentId);
						if (p != null) {
							projectRole.setParentProjectRole(p);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateProjectRole";
	}

	public void saveOrUpdateProjectRole() {
		try {
			if (projectRole.getParentProjectRole() == null || StringUtils.isEmpty(projectRole.getParentProjectRole().getId())) {
				projectRole.setParentProjectRole(null);
			}
			if (projectRole.getProject() == null || StringUtils.isEmpty(projectRole.getProject().getId())) {
				projectRole.setProject(null);
			}
			initEntityBaseController.initEntityBaseAttribute(projectRole);
			projectRole = vixntBaseService.merge(projectRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addEmployee() {
		try {
			Map<String, Object> employeeParams = getParams();
			if (StringUtils.isNotEmpty(employeeIds)) {
				employeeParams.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, employeeParams);
			}
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				if ("P".equals(treeType)) {
					Project project = vixntBaseService.findEntityById(Project.class, parentId);
					if (project != null) {
						Set<Employee> subEmployees = project.getSubEmployees();
						if (employeeList != null && employeeList.size() > 0) {
							for (Employee employee : employeeList) {
								subEmployees.add(employee);
							}
						}
						project.setSubEmployees(subEmployees);
						project = vixntBaseService.merge(project);
					}
				} else if ("R".equals(treeType)) {
					ProjectRole p = vixntBaseService.findEntityById(ProjectRole.class, parentId);
					if (p != null) {
						Set<Employee> subEmployees = p.getSubEmployees();
						if (employeeList != null && employeeList.size() > 0) {
							for (Employee employee : employeeList) {
								subEmployees.add(employee);
							}
						}
						p.setSubEmployees(subEmployees);
						p = vixntBaseService.merge(p);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭任务
	public void closeTask() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (null != vixTask) {
					vixTask.setStatus("1");
					vixntBaseService.merge(vixTask);
					setMessage("关闭成功！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("关闭失败！");
		}
	}

	// 启动任务
	public void openTask() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (null != vixTask) {
					vixTask.setStatus("0");
					vixntBaseService.merge(vixTask);
					setMessage("启动成功！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("启动失败！");
		}
	}

	/**
	 * 新增反馈
	 * 
	 * @return
	 */
	public String goSaveOrUpdateFeedback() {
		try {
			if (StringUtils.isNotEmpty(executionFeedbackId)) {
				executionFeedback = vixntBaseService.findEntityById(ExecutionFeedback.class, executionFeedbackId);
			} else {
				executionFeedback = new ExecutionFeedback();
				executionFeedback.setCode(VixUUID.createCode(15));
				executionFeedback.setFeedbackTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					project = vixntBaseService.findEntityById(Project.class, id);
					if (project != null) {
						executionFeedback.setProject(project);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateFeedback";
	}

	/**
	 * 删除反馈
	 */
	public void deleteExecutionFeedbackById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				executionFeedback = vixntBaseService.findEntityById(ExecutionFeedback.class, id);
				if (null != executionFeedback) {
					vixntBaseService.deleteByEntity(executionFeedback);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	// 获取任务附件
	public void goUploaderList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Uploader.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取项目附件
	public void goProjectUploaderList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("project.id," + SearchCondition.EQUAL, id);
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Uploader.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存任务附件
	 */
	public void saveOrUpdateUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					uploader.setCreator(employee.getName());
					uploader.setEmployee(employee);
				}
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				if (StringUtils.isNotEmpty(id)) {
					vixTask = vixntBaseService.findEntityById(VixTask.class, id);
					if (vixTask != null) {
						uploader.setTaskDefinition(vixTask);
					}
				}
				uploader.setFileType(saveDocPathAndName[3].toString());
				initEntityBaseController.initEntityBaseAttribute(uploader);
				uploader = vixntBaseService.merge(uploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存项目附件
	 */
	public void saveOrUpdateProjectUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					uploader.setCreator(employee.getName());
				}
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					project = vixntBaseService.findEntityById(Project.class, id);
					if (project != null) {
						uploader.setProject(project);
					}
				}
				uploader.setFileType(saveDocPathAndName[3].toString());
				uploader = vixntBaseService.merge(uploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增项目任务
	 * 
	 * @return
	 */
	public String goSaveOrUpdateTask() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			taskTypeList = vixntBaseService.findAllByConditions(TaskType.class, params);
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null && vixTask.getEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : vixTask.getEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			} else {
				vixTask = new VixTask();
				if (StringUtils.isNotEmpty(employeeIds)) {
					Employee employee = vixntBaseService.findEntityById(Employee.class, employeeIds);
					if (employee != null) {
						employeeNames = employee.getName();
					}
				}else{
					Employee e = getEmployee();
					employeeIds = "";
					employeeNames = "";
					if (e != null) {
						employeeIds += e.getId();
						employeeNames += e.getName();
					}
				}
				if (StringUtils.isNotEmpty(parentId)) {
					if (StringUtils.isNotEmpty(treeType)) {
						if ("P".equals(treeType)) {
							Project project = vixntBaseService.findEntityById(Project.class, parentId);
							if (project != null) {
								vixTask.setProject(project);
							}
						} else if ("T".equals(treeType)) {
							VixTask v = vixntBaseService.findEntityById(VixTask.class, parentId);
							if (v != null) {
								vixTask.setParentVixTask(v);
							}
						}
					} else {
						VixTask v = vixntBaseService.findEntityById(VixTask.class, parentId);
						if (v != null) {
							vixTask.setParentVixTask(v);
						}
					}
				}
				vixTask.setTaskStartTime(new Date());
				vixTask.setCode(VixUUID.createCode(12));
				vixTask.setIsTemp("0");
				initEntityBaseController.initEntityBaseAttribute(vixTask);
				vixTask = vixntBaseService.merge(vixTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTask";
	}
	
	public String goSaveOrUpdateTaskAdd() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			taskTypeList = vixntBaseService.findAllByConditions(TaskType.class, params);
			if (StringUtils.isNotEmpty(id)) {
				VixTask task = vixntBaseService.findEntityById(VixTask.class, id);
				if (task != null && task.getEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : task.getEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
					vixTask = new VixTask();
					if (StringUtils.isNotEmpty(employeeIds)) {
						Employee employee = vixntBaseService.findEntityById(Employee.class, employeeIds);
						if (employee != null) {
							employeeNames = employee.getName();
						}
					}
					if ("P".equals(treeType)) {
						vixTask.setProject(task.getProject());
					} else if ("T".equals(treeType)) {
						vixTask.setParentVixTask(task.getParentVixTask());
					}
					vixTask.setTaskStartTime(new Date());
					vixTask.setCode(VixUUID.createCode(12));
					vixTask.setIsTemp("0");
					initEntityBaseController.initEntityBaseAttribute(vixTask);
					vixTask = vixntBaseService.merge(vixTask);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTask";
	}

	/**
	 * 保存项目
	 * 
	 * @return
	 */
	public void saveOrUpdate() {
		try {
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						subEmployees.add(employee);
					}
				}
				Employee employee = getEmployee();
				if (employee != null) {
					subEmployees.add(employee);
				}
				project.setSubEmployees(subEmployees);
			}
			if ("0".equals(project.getIsTemp())) {
				project.setStatus("1");// 未开始
				project.setIsTemp("1");// 正式数据
				project.setIsDeleted("0");
				project.setTaskNum(0);
				project.setProjectSchedule(0);
				project.setProjectSchedule(0);
				initEntityBaseController.initEntityBaseAttribute(project);
			}
			project = vixntBaseService.merge(project);

			if (StringUtils.isNotEmpty(imagesId)) {
				WxpQyPicture wxpQyPicture = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", imagesId);
				wxpQyPicture.setProject(project);
				wxpQyPicture = vixntBaseService.merge(wxpQyPicture);
				project.setPictureurl(wxpQyPicture.getPictureUrl());
				project = vixntBaseService.merge(project);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	/**
	 * 保存任务图片
	 */
	public void saveOrUpdateTaskPicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setVixTask(vixTask);
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = vixntBaseService.merge(wxpQyPicture);
				if (wxpQyPicture != null) {
					renderText(wxpQyPicture.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0,保存失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	/**
	 * 跳转到项目详情
	 * 
	 * @return
	 */
	public String goProjectDetail() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
				if (project != null) {
					if (project.getSubUploaders() != null && project.getSubUploaders().size() > 0) {
						uploaderList = new ArrayList<Uploader>();
						uploaderList.addAll(project.getSubUploaders());
						uploaderNum = uploaderList.size();
					}
					if (project.getSubTaskDefinition() != null && project.getSubTaskDefinition().size() > 0) {
						vixTaskList = new ArrayList<VixTask>();
						vixTaskList.addAll(project.getSubTaskDefinition());
					}
					if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(project.getSubEmployees());
						employeeNum = empList.size();
					}
					if (project.getSubEvaluationReviews() != null && project.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = project.getSubEvaluationReviews().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectDetail";
	}

	// 首页获取项目列表
	public void goSingleList() {
		try {
			Pager pager = getPager();
			Employee emp = getEmployee();
			if (emp != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDeleted", "0");
				params.put("isTemp", "1");

				String projectName = getDecodeRequestParameter("projectName");
				if (StringUtils.isNotEmpty(projectName)) {
					params.put("projectName", projectName);
				}
				params.put("employeeId", emp.getId());
				pager = vixntBaseService.findProjectPager(pager, params);
				/*if (pager.getResultList().size() < 5) {
					int listSize = pager.getResultList().size();
					for (int i = 0; i < 5 - listSize; i++) {
						pager.getResultList().add(new Project());
					}
				}*/
			}
			String[] excludes = {"subTaskDefinition"};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存任务
	 * 
	 * @return
	 */
	public void saveOrUpdateTask() {
		try {
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						if (employee != null) {
							subEmployees.add(employee);
							userIdStr += "|" + employee.getUserId();
						}
					}
					vixTask.setEmployee(employeeList.get(0));
				}
				vixTask.setEmployees(subEmployees);
			}
			Employee e = getEmployee();
			if (e != null) {
				// vixTask.setEmployee(e);
				vixTask.setDataCreater(e);
				vixTask.setCreator(e.getName());
			}
			if ("0".equals(vixTask.getIsTemp())) {
				// 未开始
				vixTask.setComplete(0);
				// 正常任务
				vixTask.setStatus("0");
				// 任务进度
				vixTask.setTaskSchedule(0);
				vixTask.setIsTemp("1");
				vixTask.setIsDeleted("0");
				vixTask.setCreateTime(new Date());
				initEntityBaseController.initEntityBaseAttribute(vixTask);
			}

			if (vixTask.getParentVixTask() == null || StringUtils.isEmpty(vixTask.getParentVixTask().getId())) {
				vixTask.setParentVixTask(null);
			}
			if (vixTask.getProject() == null || StringUtils.isEmpty(vixTask.getProject().getId())) {
				vixTask.setProject(null);
			}

			vixTask = vixntBaseService.merge(vixTask);

			if (StringUtils.isNotEmpty(imagesId)) {
				WxpQyPicture wxpQyPicture = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", imagesId);
				wxpQyPicture.setVixTask(vixTask);
				wxpQyPicture = vixntBaseService.merge(wxpQyPicture);
				vixTask.setPictureUrl(wxpQyPicture.getPictureUrl());
				vixTask = vixntBaseService.merge(vixTask);
			}
			/*
			 * if (StringUtils.isNotEmpty(userIdStr)) { WxpQyWeixinSite site =
			 * vixntBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.
			 * class, "tenantId", vixTask.getTenantId()); if (site != null) { //
			 * 更新企业号token saveOrUpdateWxpWeixinSite(site);
			 * WxQyUtil.messageSendNews("3", userIdStr, vixTask.getQuestName(),
			 * site.getQiyeAccessToken(), vixTask.getTaskDescription(),
			 * redirect_ip + "/wechat/weChatTaskPlanAction!goMyTask.action?id="
			 * + vixTask.getId() + "&corpid=" + site.getCorpId(), ""); } }
			 */
			renderText(SAVE_SUCCESS+":"+vixTask.getId());
		} catch (Exception e) {
			renderText(SAVE_FAIL);
			e.printStackTrace();
		}
	}

	/**
	 * 保存项目反馈
	 */
	public void saveOrUpdateFeedback() {
		try {
			if (executionFeedback.getProject() != null && executionFeedback.getProject().getId() != null) {
				project = vixntBaseService.findEntityById(Project.class, executionFeedback.getProject().getId());
				schedule = executionFeedback.getSchedule();
				if (project != null && schedule != null) {
					if (schedule > 0 && schedule < 100) {
						project.setStatus("2");
					} else if (schedule <= 0) {
						project.setStatus("1");
					} else if (schedule == 100) {
						project.setStatus("3");
					}
					project.setProjectSchedule(schedule);
					project = vixntBaseService.merge(project);
					executionFeedback.setProject(project);
				}
			}
			Employee employee = getEmployee();
			if (employee != null) {
				executionFeedback.setCreator(employee.getName());
				executionFeedback.setEmployee(employee);
			}
			executionFeedback.setFeedbackTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(executionFeedback);
			executionFeedback = vixntBaseService.merge(executionFeedback);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 反馈更新项目进度内容
	 * 
	 * @return
	 */
	public String goProjectSchedule() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectSchedule";
	}

	/**
	 * 任务反馈列表
	 */
	public void goExecutionFeedbackList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), ExecutionFeedback.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除附件
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				uploader = vixntBaseService.findEntityById(Uploader.class, id);
				if (null != uploader) {
					String fileName = uploader.getFileName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					vixntBaseService.deleteByEntity(uploader);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String downloadUploader() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Uploader uploader = vixntBaseService.findEntityById(Uploader.class, this.id);
				String fileName = uploader.getFileName();
				this.setOriFileName(fileName);
				String baseFolder = "c:/img/";
				String downloadFile = baseFolder + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
				if (uploader.getDownloadNum() != null) {
					uploader.setDownloadNum(uploader.getDownloadNum() + 1);
				} else {
					uploader.setDownloadNum(1);
				}
				uploader = vixntBaseService.merge(uploader);
			} else {
				return NONE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadUploader";
	}

	/**
	 * 项目反馈列表
	 */
	public void goProjectExecutionFeedbackList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			params.put("project.id," + SearchCondition.EQUAL, parentId);
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("feedbackTime");
				pager.setOrderBy("desc");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, ExecutionFeedback.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 项目的任务列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void goVixTaskList() {
		try {
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				Map<String, Object> params = new HashMap<String, Object>();
				String vixTaskName = getDecodeRequestParameter("vixTaskName");
				if (StringUtils.isNotEmpty(vixTaskName)) {
					params.put("questName," + SearchCondition.ANYLIKE, vixTaskName);
				}
				if ("P".equals(treeType)) {
					params.put("project.id," + SearchCondition.EQUAL, parentId);
				} else if ("T".equals(treeType)) {
					params.put("parentVixTask.id," + SearchCondition.EQUAL, parentId);
				}
				// params.put("status," + SearchCondition.EQUAL, "0");
				params.put("isTemp," + SearchCondition.EQUAL, "1");
				params.put("isDeleted," + SearchCondition.EQUAL, "0");
				params.put("flag," + SearchCondition.EQUAL, "2");
				pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
			}
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new VixTask());
				}
			}
			vixRenderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取项目的所有任务
	public void goAllProjectTaskList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(parentId)) {
				params.put("project.id," + SearchCondition.EQUAL, parentId);
			}
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName," + SearchCondition.ANYLIKE, vixTaskName);
			}
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
			vixRenderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 树形结构JSON */
	public void findProjectTree() {
		try {
			List<Project> projectList = new ArrayList<Project>();
			/** 获取查询参数 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			projectList = vixntBaseService.findAllByConditions(Project.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllProject(strSb, projectList);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllProject(StringBuilder strSb, List<Project> projectList) throws Exception {
		for (int i = 0; i < projectList.size(); i++) {
			Project pc = projectList.get(i);
			strSb.append("{id:\"");
			strSb.append(pc.getId());
			strSb.append("\",name:\"");
			strSb.append(pc.getProjectName());
			strSb.append("\",open:false,isParent:false}");
			if (i < projectList.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	/**
	 * 获取项目结构树
	 */
	public void findProjectTreeToJson() {
		try {
			loadProject(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取项目树
	 * 
	 * @param nodeId
	 * @param nodeTreeType
	 */
	public void loadProject(String nodeId, String nodeTreeType) {
		try {
			List<ProjectUnit> orgUnitList = null;
			List<Project> orgList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("P") || nodeTreeType.equals("T")) {
						orgUnitList = vixntBaseService.findProjectUnitList(nodeTreeType, nodeId);
					}
				}
			} else {
				Employee e = getEmployee();
				if (e != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("isTemp", "1");
					params.put("isDeleted", "0");
					params.put("employeeId", e.getId());
					orgList = vixntBaseService.findProjectByHql(params);
				}
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<ProjectUnit>();
			}
			if (orgList != null) {
				for (Project project : orgList) {
					ProjectUnit ou1 = new ProjectUnit(project.getId(), "P", project.getProjectName(), project.getProjectCode());
					List<VixTask> bcList = vixntBaseService.findVixTaskList(project.getId());
					if (bcList != null && bcList.size() > 0) {
						List<ProjectUnit> projectUnitList = new LinkedList<ProjectUnit>();
						for (VixTask vixTask : bcList) {
							if (vixTask != null) {
								ProjectUnit ou2 = new ProjectUnit(vixTask.getId(), "T", vixTask.getQuestName(), vixTask.getCode());
								projectUnitList.add(ou2);
								List<ProjectUnit> pList = new LinkedList<ProjectUnit>();
								if (vixTask.getSubVixTasks() != null && vixTask.getSubVixTasks().size() > 0) {
									for (VixTask v : vixTask.getSubVixTasks()) {
										if("1".equals(v.getIsTemp()) && "0".equals(v.getIsDeleted())){
											pList.add(new ProjectUnit(v.getId(), "T", v.getQuestName(), v.getCode()));
										}
									}
								}
								ou2.setSubProjectUnits(pList);
							}
						}
						ou1.setSubProjectUnits(projectUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				ProjectUnit projectUnit = orgUnitList.get(i);
				if (projectUnit.getSubProjectUnits() != null && projectUnit.getSubProjectUnits().size() > 0) {
					strSb.append("{\"id\":\"");
					strSb.append(projectUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(projectUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(projectUnit.getTreeName());
					strSb.append("\",open:false,isParent:true}");
					// strSb.append("\",open:false,isParent:true,nocheck:true,icon:\"../resources/common/css/zTreeStyle/img/diy/1_open.png\"}");

				} else {
					strSb.append("{\"id\":\"");
					strSb.append(projectUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(projectUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(projectUnit.getTreeName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findProjectRoleToJson() {
		try {
			loadProjectRole(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadProjectRole(String nodeId, String nodeTreeType) {
		try {
			List<ProjectUnit> orgUnitList = null;
			List<Project> orgList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("P") || nodeTreeType.equals("R")) {
						orgUnitList = vixntBaseService.findProjectRoleList(nodeTreeType, nodeId);
					}
				}
			} else {
				Employee e = getEmployee();
				if (e != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("isTemp", "1");
					params.put("isDeleted", "0");
					params.put("employeeId", e.getId());
					orgList = vixntBaseService.findProjectByHql(params);
				}
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<ProjectUnit>();
			}
			if (orgList != null) {
				for (Project project : orgList) {
					ProjectUnit ou1 = new ProjectUnit(project.getId(), "P", project.getProjectName(), project.getProjectCode());
					List<ProjectRole> bcList = vixntBaseService.findParkingCarDistrictAndCountyList(project.getId());
					if (bcList != null && bcList.size() > 0) {
						List<ProjectUnit> projectUnitList = new LinkedList<ProjectUnit>();
						for (ProjectRole projectRole : bcList) {
							if (projectRole != null) {
								ProjectUnit ou2 = new ProjectUnit(projectRole.getId(), "R", projectRole.getName(), projectRole.getCode());
								projectUnitList.add(ou2);
								List<ProjectUnit> pList = new LinkedList<ProjectUnit>();
								if (projectRole.getSubProjectRoles() != null && projectRole.getSubProjectRoles().size() > 0) {
									for (ProjectRole v : projectRole.getSubProjectRoles()) {
										pList.add(new ProjectUnit(v.getId(), "R", v.getName(), v.getCode()));
									}
								}
								ou2.setSubProjectUnits(pList);
							}
						}
						ou1.setSubProjectUnits(projectUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				ProjectUnit projectUnit = orgUnitList.get(i);
				if (projectUnit.getSubProjectUnits() != null && projectUnit.getSubProjectUnits().size() > 0) {
					strSb.append("{\"id\":\"");
					strSb.append(projectUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(projectUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(projectUnit.getTreeName());
					strSb.append("\",open:false,isParent:true}");

				} else {
					strSb.append("{\"id\":\"");
					strSb.append(projectUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(projectUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(projectUnit.getTreeName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUploaderByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				for (String idStr : ids.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						uploader = vixntBaseService.findEntityById(Uploader.class, idStr);
						if (null != uploader) {
							String fileName = uploader.getFileName();
							String baseFolder = "c:/img/";
							String downloadFile = baseFolder + fileName;
							File f = new File(downloadFile); // 输入要删除的文件位置
							if (f.exists()) {
								f.delete();
							}
							vixntBaseService.deleteByEntity(uploader);
							renderText(DELETE_SUCCESS);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 弹框更新截止时间
	 * 
	 * @return
	 */
	public String goUpdateProjectEndTime() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUpdateProjectEndTime";
	}

	/**
	 * 跳转到任务详情
	 * 
	 * @return
	 */
	public String goProjectTaskDetails() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(vixTask.getEmployees());
						employeeNum = vixTask.getEmployees().size();
					}
					if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
						uploaderNum = vixTask.getUploader().size();
					}
					if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = vixTask.getSubEvaluationReviews().size();
						evaluationReviewList = vixTask.getSubEvaluationReviews();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectTaskDetails";
	}

	/**
	 * 更新项目截止时间
	 * 
	 * @return
	 */
	public void updateProjectEndTime() {
		try {
			if (project != null && StringUtils.isNotEmpty(project.getId())) {
				Project p = vixntBaseService.findEntityById(Project.class, project.getId());
				if (newEndTime != null) {
					p.setEstimateEndTime(newEndTime);
				}
				p = vixntBaseService.merge(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传项目附件
	 * 
	 * @return
	 */
	public String goAddUploader() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddUploader";
	}

	// 添加任务附件
	public String goAddTaskUploader() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddTaskUploader";
	}

	/**
	 * 上传项目附件
	 * 
	 * @return
	 */
	public void updateProjectUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					uploader.setCreator(employee.getName());
				}
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				if (project != null && StringUtils.isNotEmpty(project.getId())) {
					Project p = vixntBaseService.findEntityById(Project.class, project.getId());
					if (p != null) {
						uploader.setProject(p);
					}
				}
				uploader.setCreateTime(new Date());
				uploader.setFileType(saveDocPathAndName[3].toString());
				uploader = vixntBaseService.merge(uploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存任务反馈更新进度
	 */
	public void saveOrUpdateExecutionFeedback() {
		try {
			if (executionFeedback.getTaskDefinition() != null && executionFeedback.getTaskDefinition().getId() != null) {
				VixTask vixTask = vixntBaseService.findEntityById(VixTask.class, executionFeedback.getTaskDefinition().getId());
				if (vixTask != null && schedule != null) {
					vixTask.setTaskSchedule(schedule);
					if (schedule > 0 && schedule < 100) {
						vixTask.setComplete(1);
					} else if (schedule <= 0) {
						vixTask.setComplete(0);
					} else if (schedule == 100) {
						vixTask.setComplete(2);
						// vixTask.setStatus("1");// 关闭已完成的任务
					}
					vixTask = vixntBaseService.merge(vixTask);
				}
			}
			Employee employee = getEmployee();
			if (employee != null) {
				executionFeedback.setCreator(employee.getName());
			}
			executionFeedback.setFeedbackTime(new Date());
			executionFeedback = vixntBaseService.merge(executionFeedback);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示任务进度条区域
	 * 
	 * @return
	 */
	public String goTaskExecutionfeedbackContent() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTaskExecutionfeedbackContent";
	}

	/**
	 * 项目负责人列表
	 * 
	 * @return
	 */
	public String goShowProjectEmployeeList() {
		return "goShowProjectEmployeeList";
	}

	/**
	 * 项目人员内容切换
	 * 
	 * @return
	 */
	public String goProjectEmployeeListContent() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				e = vixntBaseService.findEntityById(Employee.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectEmployeeListContent";
	}

	/**
	 * 项目任务负责人内容切换
	 * 
	 * @return
	 */
	public String goProjectTaskEmployeeListContent() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				e = vixntBaseService.findEntityById(Employee.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectTaskEmployeeListContent";
	}

	/**
	 * 项目附件列表
	 * 
	 * @return
	 */
	public String goListProjectUploaderList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityByAttributeNoTenantId(Project.class, "id", id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("project.id," + SearchCondition.EQUAL, id);
				uploaderList = vixntBaseService.findAllByConditions(Uploader.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListProjectUploaderList";
	}

	/**
	 * 跳转到沟通页面
	 * 
	 * @return
	 */
	public String goProjectDiscuss() {
		try {
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isTemp", "1");
				params.put("isDeleted", "0");
				params.put("employeeId", e.getId());
				projectList = vixntBaseService.findProjectByHql(params);
				if (projectList != null && projectList.size() > 0) {
					project = projectList.get(0);
					if (project != null) {
						if (project.getSubEvaluationReviews() != null && project.getSubEvaluationReviews().size() > 0) {
							evaluationReviewNum = project.getSubEvaluationReviews().size();
							evaluationReviewList = project.getSubEvaluationReviews();
						}
						if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
							empList = new ArrayList<Employee>();
							empList.addAll(project.getSubEmployees());
							employeeNum = project.getSubEmployees().size();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectDiscuss";
	}
	
	public String loadProjectDiscuss(){
		try {
			if(StrUtils.isNotEmpty(id)){
				project = vixntBaseService.findEntityById(Project.class, id);
				if (project != null) {
					if (project.getSubEvaluationReviews() != null && project.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = project.getSubEvaluationReviews().size();
						evaluationReviewList = project.getSubEvaluationReviews();
					}
					if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(project.getSubEmployees());
						employeeNum = project.getSubEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowEvaluationReview";
	}

	/**
	 * 项目回复
	 */
	public String saveOrUpdateEvaluationReview() {
		try {
			evaluationReview.setProject(project);
			Employee employee = getEmployee();
			if (employee != null) {
				evaluationReview.setCreator(employee.getName());
				evaluationReview.setEmployee(employee);
			}
			evaluationReview.setEvaluationTime(new Date());
			evaluationReview.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(evaluationReview);
			evaluationReview = vixntBaseService.merge(evaluationReview);
			project = vixntBaseService.findEntityById(Project.class, project.getId());
			if (project != null) {
				if (project.getSubEvaluationReviews() != null && project.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = project.getSubEvaluationReviews();
					evaluationReviewNum = project.getSubEvaluationReviews().size();
				}
				if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
					empList = new ArrayList<Employee>();
					empList.addAll(project.getSubEmployees());
					employeeNum = project.getSubEmployees().size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowEvaluationReview";
	}

	/**
	 * 项目任务回复
	 */
	public String saveOrUpdateTaskEvaluationReview() {
		try {
			if (vixTask != null && vixTask.getId() != null && !"".equals(vixTask.getId())) {
				evaluationReview.setVixTask(vixTask);
				evaluationReview.setEvaluationTime(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					evaluationReview.setCreator(employee.getName());
					evaluationReview.setEmployee(employee);
				}
				evaluationReview.setCreateTime(new Date());
				initEntityBaseController.initEntityBaseAttribute(evaluationReview);
				evaluationReview = vixntBaseService.merge(evaluationReview);

				vixTask = vixntBaseService.findEntityById(VixTask.class, vixTask.getId());
				if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = vixTask.getSubEvaluationReviews();
					evaluationReviewNum = vixTask.getSubEvaluationReviews().size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTaskEvaluationReview";
	}

	public String goProjectEmployeeChoose() {
		return "goProjectEmployeeChoose";
	}

	// 获取项目组织架构
	@SuppressWarnings("unchecked")
	public void goProjectUnitManagementList() {
		try {
			String employeeids = "";
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {

				if ("P".equals(treeType)) {
					Project project = vixntBaseService.findEntityById(Project.class, parentId);
					if (project != null) {
						if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
							for (Employee employee : project.getSubEmployees()) {
								if (employee != null) {
									employeeids += "," + employee.getId();
								}
							}
						}
					}
				} else if ("R".equals(treeType)) {
					ProjectRole projectRole = vixntBaseService.findEntityById(ProjectRole.class, parentId);
					if (projectRole != null) {
						if (projectRole.getSubEmployees() != null && projectRole.getSubEmployees().size() > 0) {
							for (Employee employee : projectRole.getSubEmployees()) {
								if (employee != null) {
									employeeids += "," + employee.getId();
								}
							}
						}
					}
				}
				if (StringUtils.isNotEmpty(employeeids)) {
					params.put("id," + SearchCondition.IN, employeeids);
					pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
				}
			}
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new Employee());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取项目负责人
	public void goProjectEmployeeList() {
		try {
			String employeeids = "";
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			if (StringUtils.isNotEmpty(parentId)) {
				Project project = vixntBaseService.findEntityById(Project.class, parentId);
				if (project != null) {
					if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
						for (Employee employee : project.getSubEmployees()) {
							if (employee != null) {
								employeeids += "," + employee.getId();
							}
						}
					}
				}
				if (StringUtils.isNotEmpty(employeeids)) {
					params.put("id," + SearchCondition.IN, employeeids);
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			} else {
				pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取子任务
	 */
	public void goSubTaskList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(id)) {
				params.put("parentVixTask.id," + SearchCondition.EQUAL, id);
			}
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除任务
	 */
	public void deleteTaskById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (null != vixTask) {
					vixTask.setIsDeleted("1");
					vixntBaseService.merge(vixTask);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 弹框更新截止时间
	 * 
	 * @return
	 */
	public String goUpdateEndTime() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUpdateEndTime";
	}

	/**
	 * 更新截止时间
	 * 
	 * @return
	 */
	public void updateTaskEndTime() {
		try {
			if (vixTask != null && StringUtils.isNotEmpty(vixTask.getId())) {
				VixTask task = vixntBaseService.findEntityById(VixTask.class, vixTask.getId());
				if (newEndTime != null) {
					task.setTaskEndTime(newEndTime);
				}
				task = vixntBaseService.merge(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新任务执行者
	public void updateVixTaskEmployee() {
		try {
			if (vixTask != null && StringUtils.isNotEmpty(vixTask.getId())) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, vixTask.getId());
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = vixTask.getEmployees();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						subEmployees.add(employee);
					}
				}
				vixTask.setEmployees(subEmployees);
				vixTask = vixntBaseService.merge(vixTask);
				renderText(vixTask.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除执行人
	public void deleteProjectEmployeeById() {
		try {
			if (StringUtils.isNotEmpty(projectId)) {
				project = vixntBaseService.findEntityById(Project.class, projectId);
			}
			if (StringUtils.isNotEmpty(id)) {
				Employee emp = vixntBaseService.findEntityById(Employee.class, id);
				Set<Employee> subEmployees = project.getSubEmployees();
				if (emp != null) {
					subEmployees.remove(emp);
				}
				project.setSubEmployees(subEmployees);
				project = vixntBaseService.merge(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goShowTaskEmployeeList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					e = vixTask.getEmployee();
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(vixTask.getEmployees());
						employeeNum = vixTask.getEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTaskEmployeeList";
	}

	// 显示项目执行人员列表
	public String updateProjectEmployee() {
		try {
			if (project != null && StringUtils.isNotEmpty(project.getId())) {
				project = vixntBaseService.findEntityById(Project.class, project.getId());
				// 获取选定成员
				if (StringUtils.isNotEmpty(employeeIds)) {
					Map<String, Object> p = getParams();
					p.put("id," + SearchCondition.IN, employeeIds);
					employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
					Set<Employee> subEmployees = project.getSubEmployees();
					if (employeeList != null && employeeList.size() > 0) {
						for (Employee employee : employeeList) {
							subEmployees.add(employee);
						}
					}
					project.setSubEmployees(subEmployees);
					project = vixntBaseService.merge(project);

					if (project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(project.getSubEmployees());
						employeeNum = project.getSubEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowProjectEmployee";
	}

	// 显示任务执行人员列表
	public String goShowProjectEmployee() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
				if (project != null && project.getSubEmployees() != null && project.getSubEmployees().size() > 0) {
					empList = new ArrayList<Employee>();
					empList.addAll(project.getSubEmployees());
					employeeNum = project.getSubEmployees().size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowProjectEmployee";
	}

	public String goSaveOrUpdateExecutionFeedback() {
		try {
			if (StringUtils.isNotEmpty(executionFeedbackId)) {
				executionFeedback = vixntBaseService.findEntityById(ExecutionFeedback.class, executionFeedbackId);
			} else {
				executionFeedback = new ExecutionFeedback();
				executionFeedback.setCode(VixUUID.createCode(15));
				if (StringUtils.isNotEmpty(id)) {
					vixTask = vixntBaseService.findEntityById(VixTask.class, id);
					if (vixTask != null) {
						executionFeedback.setTaskDefinition(vixTask);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateExecutionFeedback";
	}

	/**
	 * 任务附件列表
	 * 
	 * @return
	 */
	public String goSaveOrUpdateTaskAttachment() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
				uploaderList = vixntBaseService.findAllByConditions(Uploader.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTaskAttachment";
	}

	public String goSaveOrUpdateTaskDiscuss() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = vixTask.getSubEvaluationReviews().size();
						evaluationReviewList = vixTask.getSubEvaluationReviews();
					}
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(vixTask.getEmployees());
						employeeNum = vixTask.getEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTaskDiscuss";
	}
	
	// 项目甘特图
	public String goProjectTaskGtt() {
		try{
			if(StrUtils.isNotEmpty(id)){
				project = vixntBaseService.findEntityById(Project.class, id);
				if(project != null){
					StringBuilder sb = new StringBuilder("{\"tasks\":[");
					List<VixTask> vixTasks = vixntBaseService.findVixTaskList(project.getId());
					for (VixTask vixTask : vixTasks) {
						if(vixTask != null){
							sb.append("{\"id\":\""+vixTask.getId()+"\",");
							sb.append("\"name\":\""+vixTask.getQuestName()+"\",");
							sb.append("\"code\":\""+vixTask.getCode()+"\",");
							sb.append("\"level\":0,");
							String status = "";
							if(vixTask.getComplete() == 0){
								status = "STATUS_SUSPENDED";
							}else if(vixTask.getComplete() == 1){
								status = "STATUS_ACTIVE";
							}else if(vixTask.getComplete() == 2){
								status = "STATUS_DONE";
							}else if(vixTask.getComplete() == 3){
								status = "STATUS_FAILED";
							}
							sb.append("\"status\":\""+status+"\",");
							sb.append("\"canWrite\":true,");
							sb.append("\"start\":"+vixTask.getTaskStartTime().getTime()+",");
							int dur = (int)(vixTask.getTaskEndTime().getTime() - vixTask.getTaskStartTime().getTime())/(24*60*60*1000);
							sb.append("\"duration\":"+dur+",");
							sb.append("\"end\":"+vixTask.getTaskEndTime().getTime()+",");
							sb.append("\"startIsMilestone\":false,");
							sb.append("\"endIsMilestone\":false,");
							sb.append("\"collapsed\":false,");
							sb.append("\"assigs\":[],");
							sb.append("\"description\":\""+vixTask.getEmployee().getName()+"\",");
							sb.append("\"hasChild\":true},");
							if(vixTask.getSubVixTasks() != null && vixTask.getSubVixTasks().size() > 0){
								for (VixTask task : vixTask.getSubVixTasks()) {
									if("1".equals(task.getIsTemp()) && "0".equals(task.getIsDeleted())){
										sb.append("{\"id\":\""+task.getId()+"\",");
										sb.append("\"name\":\""+task.getQuestName()+"\",");
										sb.append("\"code\":\""+task.getCode()+"\",");
										sb.append("\"level\":1,");
										String status1 = "";
										if(task.getComplete() == 0){
											status1 = "STATUS_SUSPENDED";
										}else if(task.getComplete() == 1){
											status1 = "STATUS_ACTIVE";
										}else if(task.getComplete() == 2){
											status1 = "STATUS_DONE";
										}else if(task.getComplete() == 3){
											status1 = "STATUS_FAILED";
										}
										sb.append("\"status\":\""+status1+"\",");
										sb.append("\"canWrite\":true,");
										sb.append("\"start\":"+task.getTaskStartTime().getTime()+",");
										int dur1 = (int)(task.getTaskEndTime().getTime() - task.getTaskStartTime().getTime())/(24*60*60*1000);
										sb.append("\"duration\":"+dur1+",");
										sb.append("\"end\":"+task.getTaskEndTime().getTime()+",");
										sb.append("\"startIsMilestone\":false,");
										sb.append("\"endIsMilestone\":false,");
										sb.append("\"collapsed\":false,");
										sb.append("\"assigs\":[],");
										sb.append("\"description\":\""+task.getEmployee().getName()+"\",");
										sb.append("\"hasChild\":false},");
									}
								}
							}
						}
					}
					String gtt = sb.substring(0, sb.length()-1);
					gtt += "],\"selectedRow\":0,\"canWrite\":true,\"canWriteOnParent\":true}";
					getRequest().setAttribute("gtt", gtt);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goProjectTaskGtt";
	}

	// 删除项目
	public void deleteProjectById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Project project = vixntBaseService.findEntityById(Project.class, id);
				if (project != null) {
					project.setIsDeleted("1");
					vixntBaseService.merge(project);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 完成项目
	public void updateFinishProject() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				project = vixntBaseService.findEntityById(Project.class, id);
				if (null != project) {
					project.setStatus("3");
					project.setProjectSchedule(100);
					project = vixntBaseService.merge(project);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 改变项目状态
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	void updateTaskComplete() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<Project> projectList = vixntBaseService.findAllByConditions(Project.class, params);
			if (projectList != null) {
				for (Project project : projectList) {
					Date date = new Date();
					if (project != null && project.getEstimateEndTime() != null) {
						if (project.getEstimateEndTime().getTime() < date.getTime()) {
							project.setStatus("3");
							project.setProjectSchedule(100);
							vixntBaseService.merge(project);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the treeType
	 */
	public String getTreeType() {
		return treeType;
	}

	/**
	 * @param treeType
	 *            the treeType to set
	 */
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * @return the vixTaskList
	 */
	public List<VixTask> getVixTaskList() {
		return vixTaskList;
	}

	/**
	 * @param vixTaskList
	 *            the vixTaskList to set
	 */
	public void setVixTaskList(List<VixTask> vixTaskList) {
		this.vixTaskList = vixTaskList;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the employeeNum
	 */
	public Integer getEmployeeNum() {
		return employeeNum;
	}

	/**
	 * @param employeeNum
	 *            the employeeNum to set
	 */
	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	/**
	 * @return the evaluationReview
	 */
	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	/**
	 * @param evaluationReview
	 *            the evaluationReview to set
	 */
	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public String goProjectList() {
		try{
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isTemp", "1");
				params.put("isDeleted", "0");
				params.put("employeeId", e.getId());
				projectList = vixntBaseService.findProjectByHql(params);
				if(projectList != null && projectList.size() > 0){
					for (Project project : projectList) {
						if(project != null){
							//所有任务 
							StringBuilder vixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
									+ " and hentity.isDeleted = '0' and hentity.flag = '2' and type = '0' and hentity.parentVixTask != null and "); 
							vixtaskhql.append(" (hentity.parentVixTask.project.id = '" + project.getId() + "')"); 
							Long vixtaskCount = vixntBaseService.findDataCountByHql(vixtaskhql, "hentity", params); 
							if(vixtaskCount != null){
								project.setTaskNum(Integer.parseInt(vixtaskCount.toString()));
							}
							// 未开始任务 
							StringBuilder noStartVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
									+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '0' and type = '0' and hentity.parentVixTask != null and "); 
							noStartVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
							Long noStartVixtaskCount = vixntBaseService.findDataCountByHql(noStartVixtaskhql, "hentity", params); 
							if(noStartVixtaskCount != null){
								project.setNostartTaskNum(Integer.parseInt(noStartVixtaskCount.toString()));
							}
							// 进行中任务
							StringBuilder progressVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
									+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '1' and type = '0' and hentity.parentVixTask != null and "); 
							progressVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
							Long progressVixtaskCount = vixntBaseService.findDataCountByHql(progressVixtaskhql, "hentity", params); 
							if(progressVixtaskCount != null){
								project.setProgressTaskNum(Integer.parseInt(progressVixtaskCount.toString()));
							}
							// 已完成任务
							StringBuilder completeVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
									+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '2' and type = '0' and hentity.parentVixTask != null and "); 
							completeVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
							Long completeVixtaskCount = vixntBaseService.findDataCountByHql(completeVixtaskhql, "hentity", params); 
							if(completeVixtaskCount != null){
								project.setFinishTaskNum(Integer.parseInt(completeVixtaskCount.toString()));
							}
							// 超时完成任务
							StringBuilder timeoutVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
									+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '3' and type = '0' and hentity.parentVixTask != null and "); 
							timeoutVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
							Long timeoutVixtaskCount = vixntBaseService.findDataCountByHql(timeoutVixtaskhql, "hentity", params); 
							if(timeoutVixtaskCount != null){
								project.setTimeoutTaskNum(Integer.parseInt(timeoutVixtaskCount.toString()));
							}
							project = vixntBaseService.merge(project);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goProjectList";
	}

	public String goProjectUnitManagement() {
		return "goProjectUnitManagement";
	}

	public String goProjectTracing() {
		return "goProjectTracing";
	}

	/**
	 * 项目进度管理
	 * 
	 * @return
	 */
	public String goProjectScheduleManagement() {
		return "goProjectScheduleManagement";
	}

	/**
	 * @return the executionFeedbackId
	 */
	public String getExecutionFeedbackId() {
		return executionFeedbackId;
	}

	/**
	 * @param executionFeedbackId
	 *            the executionFeedbackId to set
	 */
	public void setExecutionFeedbackId(String executionFeedbackId) {
		this.executionFeedbackId = executionFeedbackId;
	}

	/**
	 * @return the executionFeedback
	 */
	public ExecutionFeedback getExecutionFeedback() {
		return executionFeedback;
	}

	/**
	 * @param executionFeedback
	 *            the executionFeedback to set
	 */
	public void setExecutionFeedback(ExecutionFeedback executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

	/**
	 * @return the userIdStr
	 */
	public String getUserIdStr() {
		return userIdStr;
	}

	/**
	 * @param userIdStr
	 *            the userIdStr to set
	 */
	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	/**
	 * @return the newEndTime
	 */
	public Date getNewEndTime() {
		return newEndTime;
	}

	/**
	 * @param newEndTime
	 *            the newEndTime to set
	 */
	public void setNewEndTime(Date newEndTime) {
		this.newEndTime = newEndTime;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the uploaderList
	 */
	public List<Uploader> getUploaderList() {
		return uploaderList;
	}

	/**
	 * @param uploaderList
	 *            the uploaderList to set
	 */
	public void setUploaderList(List<Uploader> uploaderList) {
		this.uploaderList = uploaderList;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	public Set<EvaluationReview> getEvaluationReviewList() {
		return evaluationReviewList;
	}

	public void setEvaluationReviewList(Set<EvaluationReview> evaluationReviewList) {
		this.evaluationReviewList = evaluationReviewList;
	}

	public Uploader getUploader() {
		return uploader;
	}

	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	/**
	 * @return the chooseType
	 */
	@Override
	public String getChooseType() {
		return chooseType;
	}

	/**
	 * @param chooseType
	 *            the chooseType to set
	 */
	@Override
	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}

	/**
	 * @return the entityName
	 */
	@Override
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName
	 *            the entityName to set
	 */
	@Override
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the projectList
	 */
	public List<Project> getProjectList() {
		return projectList;
	}

	/**
	 * @param projectList
	 *            the projectList to set
	 */
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}

	public void setTaskTypeList(List<TaskType> taskTypeList) {
		this.taskTypeList = taskTypeList;
	}

	/**
	 * @return the employeeIds
	 */
	public String getEmployeeIds() {
		return employeeIds;
	}

	/**
	 * @param employeeIds
	 *            the employeeIds to set
	 */
	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	/**
	 * @return the employeeNames
	 */
	public String getEmployeeNames() {
		return employeeNames;
	}

	/**
	 * @param employeeNames
	 *            the employeeNames to set
	 */
	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	/**
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList
	 *            the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * @return the empList
	 */
	public List<Employee> getEmpList() {
		return empList;
	}

	/**
	 * @param empList
	 *            the empList to set
	 */
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	/**
	 * @return the vixTask
	 */
	public VixTask getVixTask() {
		return vixTask;
	}

	/**
	 * @param vixTask
	 *            the vixTask to set
	 */
	public void setVixTask(VixTask vixTask) {
		this.vixTask = vixTask;
	}

	/**
	 * @return the uploaderNum
	 */
	public Integer getUploaderNum() {
		return uploaderNum;
	}

	/**
	 * @param uploaderNum
	 *            the uploaderNum to set
	 */
	public void setUploaderNum(Integer uploaderNum) {
		this.uploaderNum = uploaderNum;
	}

	/**
	 * @return the imagesId
	 */
	public String getImagesId() {
		return imagesId;
	}

	/**
	 * @return the e
	 */
	public Employee getE() {
		return e;
	}

	/**
	 * @param e
	 *            the e to set
	 */
	public void setE(Employee e) {
		this.e = e;
	}

	/**
	 * @return the schedule
	 */
	public Integer getSchedule() {
		return schedule;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @param schedule
	 *            the schedule to set
	 */
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}

	/**
	 * @param imagesId
	 *            the imagesId to set
	 */
	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}

	/**
	 * @return the evaluationReviewNum
	 */
	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	/**
	 * @param evaluationReviewNum
	 *            the evaluationReviewNum to set
	 */
	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

	public ProjectRole getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}

}
