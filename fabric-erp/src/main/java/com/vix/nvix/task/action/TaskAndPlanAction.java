package com.vix.nvix.task.action;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.BusinessOrgEmpView;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IBusinessViewService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.security.util.DaysUtils;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.project.util.ProjectUnit;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingSummary;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.typeSettings.entity.TaskType;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * com.vix.nvix.task.action.TaskAndPlanAction
 *
 * @author bjitzhang
 *
 * @date 2015年12月18日
 */
@Controller
@Scope("prototype")
public class TaskAndPlanAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String taskId;
	private String parentId;
	private String meetingids;
	private VixTask vixTask;
	// 我的任务
	private List<VixTask> vixTaskList;
	// 我分配的任务
	private List<VixTask> distributeVixTaskList;
	private String treeType;
	/**
	 * 进度
	 */
	private Integer schedule;
	private String executionFeedbackId;
	/**
	 * 选择按钮类型
	 */
	private String chooseType;
	private String entityName;
	private String employeeIds;
	private String employeeNames;
	private List<Employee> employeeList;
	private Set<Employee> empList;
	private ExecutionFeedback executionFeedback;
	/**
	 * 成员ID列表
	 */
	private String userIdStr = "";
	private List<Uploader> uploaderList;
	private String fileId;
	private String imagesId;
	/**
	 * 任务数量
	 */
	private Integer taskNum;
	/**
	 * 分配的任务数量
	 */
	private Integer distributeVixTaskNum;
	/**
	 * 附件数量
	 */
	private Integer uploaderNum = 0;
	/**
	 * 人员数量
	 */
	private Integer employeeNum = 0;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;
	private Set<EvaluationReview> evaluationReviewList;
	private List<TaskType> taskTypeList;
	private EvaluationReview evaluationReview;
	/**
	 * 新的截止时间
	 */
	private Date newEndTime;
	/**
	 * 任务创建人
	 */
	private Employee emp;
	private Uploader uploader;
	// 页面跳转来源
	private String source;
	private String ids;
	private String treeId;
	private String applicationMgData;
	private List<ApplicationMg> applicationMgList;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private IBusinessViewService businessViewService;

	// 我下达的当天需要做的任务
	public void goMyTaskList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName," + SearchCondition.ANYLIKE, vixTaskName);
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchtaskname);
			}
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("flag," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("complete," + SearchCondition.NOEQUAL, 2);
			params.put("taskStartTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			Employee e = getEmployee();
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
			}

			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 我下达的所有任务
	public void goMyTaskAllList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName," + SearchCondition.ANYLIKE, vixTaskName);
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchtaskname);
			}
			// params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");

			Employee e = getEmployee();
			if (e != null) {
				params.put("dataCreater.id," + SearchCondition.EQUAL, e.getId());
				pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
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
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
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

	// 我当天的代办
	public void goMyToDoTaskList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName", "%" + vixTaskName + "%");
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName", "%" + searchtaskname + "%");
			}
			String complete = getRequestParameter("complete");
			if (StringUtils.isNotEmpty(complete)) {
				params.put("complete", Integer.parseInt(complete));
			}
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			// params.put("taskStartTime", DaysUtils.getBeginDay(new Date()) +
			// "!" + DaysUtils.getEndDay(new Date()));

			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 我所有的待办任务
	public void goMyAllToDoTaskList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName", "%" + vixTaskName + "%");
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName", "%" + searchtaskname + "%");
			}
			String complete = getRequestParameter("complete");
			if (StringUtils.isNotEmpty(complete)) {
				params.put("complete", Integer.parseInt(complete));
			}
			// params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");

			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 任务与计划右侧显示内容
	 * 
	 * @return
	 */
	public String goShowTask() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTask";
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			taskTypeList = vixntBaseService.findAllByConditions(TaskType.class, params);
			SortSet ss = new SortSet("isDefault");
			Collections.sort(taskTypeList, Collections.reverseOrder(ss));
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
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
						} else if ("E".equals(treeType)) {
							// parentId = parentId.substring(0,
							// parentId.length() - 1);
							Employee e = vixntBaseService.findEntityById(Employee.class, parentId);
							Set<Employee> subEmployees = new HashSet<Employee>();
							if (e != null) {
								subEmployees.add(e);
								employeeIds = e.getId();
								employeeNames = e.getName();
							}
							vixTask.setEmployees(subEmployees);
						}
					} else {
						VixTask v = vixntBaseService.findEntityById(VixTask.class, parentId);
						if (v != null) {
							vixTask.setParentVixTask(v);
						}
					}
				}
				Employee e = getEmployee();
				employeeIds = "";
				employeeNames = "";
				if (e != null) {
					employeeIds += e.getId();
					employeeNames += e.getName();
				}
				vixTask.setTaskStartTime(new Date());
				vixTask.setIsTemp("0");
				vixTask.setIsFeedback("1");
				initEntityBaseController.initEntityBaseAttribute(vixTask);
				vixTask = vixntBaseService.merge(vixTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateTeamTask() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			taskTypeList = vixntBaseService.findAllByConditions(TaskType.class, params);
			SortSet ss = new SortSet("isDefault");
			Collections.sort(taskTypeList, Collections.reverseOrder(ss));
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
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
						} else if ("E".equals(treeType)) {
							// parentId = parentId.substring(0,
							// parentId.length() - 1);
							Employee e = vixntBaseService.findEntityById(Employee.class, parentId);
							Set<Employee> subEmployees = new HashSet<Employee>();
							if (e != null) {
								subEmployees.add(e);
								employeeIds = e.getId();
								employeeNames = e.getName();
							}
							vixTask.setEmployees(subEmployees);
						}
					} else {
						VixTask v = vixntBaseService.findEntityById(VixTask.class, parentId);
						if (v != null) {
							vixTask.setParentVixTask(v);
						}
					}
				}
				String start = getRequestParameter("start");
				if(StrUtils.isNotEmpty(start)){
					vixTask.setTaskStartTime(DateUtil.praseSqlTime(start));
				}else{
					vixTask.setTaskStartTime(new Date());
				}
				vixTask.setIsTemp("0");
				initEntityBaseController.initEntityBaseAttribute(vixTask);
				vixTask = vixntBaseService.merge(vixTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTeamTask";
	}

	// 添加附件
	public String goAddUploader() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddUploader";
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

	// 完成任务
	public void updateFinishTask() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (null != vixTask) {
					vixTask.setComplete(2);
					// vixTask.setStatus("1");
					vixTask.setTaskSchedule(100);
					vixTask = vixntBaseService.merge(vixTask);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除任务
	 */
	public void deleteById() {
		try {
			Employee employee = getEmployee();
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (null != vixTask) {
					if (vixTask.getEmployee() != null && vixTask.getEmployee().getId().equals(employee.getId())) {
						vixTask.setIsDeleted("1");
						vixTask = vixntBaseService.merge(vixTask);
						// 删除日志任务
						Calendars calendars = vixntBaseService.findEntityByAttribute(Calendars.class, "taskId", vixTask.getId());
						if(calendars != null){
							vixntBaseService.deleteByEntity(calendars);
						}
						if (vixTask != null) {
							Set<Employee> subEmployees = vixTask.getEmployees();
							if (emp != null) {
								userIdStr = emp.getUserId();
								subEmployees.remove(emp);
							}
							vixTask.setEmployees(subEmployees);
							vixTask = vixntBaseService.merge(vixTask);
							WxpQyWeixinSite site = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", vixTask.getTenantId());
							if (site != null && StringUtils.isNotEmpty(userIdStr)) {
								// 更新企业号token
								saveOrUpdateWxpWeixinSite(site);
								WxQyUtil.messageSend("3", userIdStr, employee.getName() + "取消了" + vixTask.getQuestName(), site.getQiyeAccessToken());
							}
						}
						renderText(DELETE_SUCCESS);
					} else {
						renderText("不能删除上级部署的任务!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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

	/**
	 * 任务反馈
	 * 
	 * @return
	 */
	public String goSaveOrUpdateExecutionFeedback() {
		try {
			if (StringUtils.isNotEmpty(executionFeedbackId)) {
				executionFeedback = vixntBaseService.findEntityById(ExecutionFeedback.class, executionFeedbackId);
			} else {
				executionFeedback = new ExecutionFeedback();
				executionFeedback.setCode(VixUUID.createCode(15));
				executionFeedback.setFeedbackTime(new Date());
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

	// 判断是否过了反馈时间
	public void isFeedback() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					if (StringUtils.isNotEmpty(vixTask.getStartFeedbackTime()) && StringUtils.isNotEmpty(vixTask.getEndFeedbackTime())) {
						boolean check = isInTime(vixTask.getStartFeedbackTime() + "-" + vixTask.getEndFeedbackTime(), sdf.format(new Date()));
						if (check) {
							renderText("1");
						} else {
							renderText("0");
						}
					} else {
						renderText("1");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOpenTask() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					vixTask.setComplete(1);
					vixTask.setRealityStartTime(new Date());
					vixTask = vixntBaseService.merge(vixTask);
					renderText("1:开始任务成功");
				} else {
					renderText("0:开始任务失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:开始任务失败");
		}
	}

	/**
	 * 保存任务
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
					vixTask.setEmployee(employeeList.get(0));
				}
				vixTask.setEmployees(subEmployees);
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
			Employee e = getEmployee();
			if (e != null) {
				vixTask.setCreator(e.getName());
				vixTask.setDataCreater(e);
				// vixTask.setEmployee(e);
			}
			vixTask = vixntBaseService.merge(vixTask);
			if (vixTask != null) {
				if (StringUtils.isNotEmpty(imagesId)) {
					WxpQyPicture wxpQyPicture = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", imagesId);
					wxpQyPicture.setVixTask(vixTask);
					wxpQyPicture = vixntBaseService.merge(wxpQyPicture);
					vixTask.setPictureUrl(wxpQyPicture.getPictureUrl());
					vixTask = vixntBaseService.merge(vixTask);
				}
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					for (Employee employee : vixTask.getEmployees()) {
						// 创建日程
						Calendars calendars = new Calendars();
						calendars.setScheduleName(vixTask.getQuestName());
						calendars.setStartDay(vixTask.getTaskStartTime());
						calendars.setTaskId(vixTask.getId());
						calendars.setTaskType("1");
						calendars.setEndDay(vixTask.getTaskEndTime());

						// TODO顏色需要調整
						calendars.setPriority("bg-color-pink txt-color-white");
						calendars.setCalendarsContent(vixTask.getTaskDescription());
						calendars.setEmployee(employee);
						calendars.setIsHasRemind("1");
						calendars.setStatus("0");
						calendars.setCreator(employee.getName());
						initEntityBaseController.initEntityBaseAttribute(calendars);
						defaultCalendarsAdapter.updateCalendars(calendars);
						userIdStr += "|" + employee.getUserId();
					}
				}
				/*
				 * WxpQyWeixinSite site =
				 * vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class,
				 * "tenantId", vixTask.getTenantId()); if
				 * (StringUtils.isNotEmpty(userIdStr)) { // 更新企业号token
				 * saveOrUpdateWxpWeixinSite(site);
				 * WxQyUtil.messageSendNews("3", userIdStr,
				 * vixTask.getQuestName(), site.getQiyeAccessToken(),
				 * vixTask.getTaskDescription(), redirect_ip +
				 * "/wechat/weChatTaskPlanAction!goMyTask.action?id=" +
				 * vixTask.getId() + "&corpid=" + site.getCorpId(), ""); }
				 */
			}
			renderText("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			renderText("保存失败!");
		}
	}

	// 显示任务执行人员列表
	public String updateVixTaskEmployee() {
		try {
			if (vixTask != null && StringUtils.isNotEmpty(vixTask.getId())) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, vixTask.getId());
				// 获取选定成员
				if (StringUtils.isNotEmpty(employeeIds)) {
					Map<String, Object> p = getParams();
					p.put("id," + SearchCondition.IN, employeeIds);
					employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
					Set<Employee> subEmployees = vixTask.getEmployees();
					if (employeeList != null && employeeList.size() > 0) {
						for (Employee employee : employeeList) {
							subEmployees.add(employee);
							userIdStr += "|" + employee.getUserId();
						}
					}
					vixTask.setEmployees(subEmployees);
				}
				vixTask = vixntBaseService.merge(vixTask);
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					empList = vixTask.getEmployees();
					employeeNum = vixTask.getEmployees().size();
				}
				WxpQyWeixinSite site = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", vixTask.getTenantId());
				if (site != null && StringUtils.isNotEmpty(userIdStr)) {
					// 更新企业号token
					saveOrUpdateWxpWeixinSite(site);
					WxQyUtil.messageSendNews("3", userIdStr, vixTask.getQuestName(), site.getQiyeAccessToken(), vixTask.getTaskDescription(), redirect_ip + "/wechat/weChatTaskPlanAction!goMyTask.action?id=" + vixTask.getId() + "&corpid=" + site.getCorpId(), "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTaskEmployee";
	}

	/**
	 * 保存附件
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
				}
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					vixTask = vixntBaseService.findEntityById(VixTask.class, id);
					if (vixTask != null) {
						uploader.setTaskDefinition(vixTask);
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
	public void updateEndTime() {
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

	/**
	 * 保存反馈更新进度
	 */
	public void saveOrUpdateExecutionFeedback() {
		try {
			if (executionFeedback != null && executionFeedback.getTaskDefinition() != null && executionFeedback.getTaskDefinition().getId() != null) {
				VixTask vixTask = vixntBaseService.findEntityById(VixTask.class, executionFeedback.getTaskDefinition().getId());
				if (vixTask != null && executionFeedback.getSchedule() != null) {
					schedule = executionFeedback.getSchedule();
					vixTask.setTaskSchedule(schedule);
					if (schedule > 0 && schedule < 100) {
						vixTask.setComplete(1);
						if (vixTask.getRealityStartTime() == null) {
							vixTask.setRealityStartTime(new Date());
						}
					} else if (schedule <= 0) {
						vixTask.setComplete(0);
					} else if (schedule == 100) {
						vixTask.setComplete(2);
						vixTask.setRealityStartTime(new Date());
						vixTask.setRealityEndTime(new Date());
						// vixTask.setStatus("1");
					}
					vixTask = vixntBaseService.merge(vixTask);
				}
			}
			Employee employee = getEmployee();
			if (employee != null) {
				executionFeedback.setCreator(employee.getName());
			}
			//executionFeedback.setFeedbackTime(new Date());
			executionFeedback = vixntBaseService.merge(executionFeedback);
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
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
	 * 跳转到任务详情
	 * 
	 * @return
	 */
	public String goProjectTaskDetails() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						empList = vixTask.getEmployees();
						employeeNum = vixTask.getEmployees().size();
					}
					if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
						uploaderNum = vixTask.getUploader().size();
					}
					if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
						evaluationReviewList = vixTask.getSubEvaluationReviews();
						evaluationReviewNum = vixTask.getSubEvaluationReviews().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectTaskDetails";
	}

	/**
	 * 选择人员
	 * 
	 * @return
	 */
	public String goEmployeeChoose() {
		return "goEmployeeChoose";
	}

	public String goHigherLevelEmployee() {
		return "goHigherLevelEmployee";
	}

	public String goTeamEmployeeChoose() {
		return "goTeamEmployeeChoose";
	}

	public String goMeetingChoose() {
		return "goMeetingChoose";
	}

	/**
	 * 下级人员列表
	 */
	public void goEmployeeList() {
		try {
			String employeeids = getBusinessOrgDetailBo4ReportLineEmployeeIds();
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(employeeids)) {
				params.put("id," + SearchCondition.IN, employeeids);
			}
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			String[] excludes = {"organizationUnit"};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上级人员列表
	 */
	public void goHigherLevelEmployeeList() {
		try {
			String employeeids = getHigherLevelBusinessOrgDetailBo4ReportLineEmployeeIds();
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(employeeids)) {
				params.put("id," + SearchCondition.IN, employeeids);
			}
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			String[] excludes = {"organizationUnit"};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goMeetingList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			String meetingTheme = getDecodeRequestParameter("meetingTheme");
			if (StringUtils.isNotEmpty(meetingTheme)) {
				params.put("meetingTheme", "%" + meetingTheme + "%");
			}
			Employee employee = getEmployee();
			params.put("employeeId", employee.getId());
			Pager pager = vixntBaseService.findApplicationMgPager(getPager(), params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goTeamEmployeeList() {
		try {
			String employeeids = "";
			Employee e = getEmployee();
			if (e != null) {
				employeeids = e.getId();
				List<Employee> employeeList = vixntBaseService.findBusinessOrgDetailBo4ReportLine(e.getTenantId(), e.getId());
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						if (employee != null) {
							employeeids += "," + employee.getId();
						}
					}
				}
			}
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, employeeids);
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			String[] excludes = {"organizationUnit"};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择人员
	 * 
	 * @return
	 */
	public String goBusinessOrgDetailEmployee() {
		return "goBusinessOrgDetailEmployee";
	}

	/**
	 * 人员列表
	 */
	public void goBusinessOrgDetailEmployeeList() {
		try {

			Map<String, Object> p = new HashMap<String, Object>();
			Employee e = getEmployee();
			if (e != null) {
				List<Employee> employeeList = vixntBaseService.findBusinessOrgDetailBo4ReportLine(e.getTenantId(), e.getId());
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						if (employee != null) {
							p.put(employee.getId(), employee);
						}
					}
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	// 获取附件列表
	public void goUploaderList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(id)) {
				params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
				String searchName = getDecodeRequestParameter("searchName");
				if (StringUtils.isNotEmpty(searchName)) {
					params.put("fileName," + SearchCondition.ANYLIKE, searchName);
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, Uploader.class, params);
			}
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
				Pager pager = getPager();
				if (pageNo != null) {
					pager.setPageNo(pageNo);
				}
				params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Uploader.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTaskAttachment";
	}

	/**
	 * 回复
	 */
	public String saveOrUpdateEvaluationReview() {
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
		return "goShowEvaluationReview";
	}

	public String goListTeamTask() {
		return "goListTeamTask";
	}

	// 任务沟通
	public String goSaveOrUpdateTaskDiscuss() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
						evaluationReviewList = vixTask.getSubEvaluationReviews();
						evaluationReviewNum = vixTask.getSubEvaluationReviews().size();
					}
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						empList = vixTask.getEmployees();
						employeeNum = vixTask.getEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTaskDiscuss";
	}

	public String goShowTaskEmployeeList() {
		try {
			if (StringUtils.isNotEmpty(taskId)) {
				vixTask = vixntBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", taskId);
				if (vixTask != null) {
					emp = vixTask.getEmployee();
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						empList = vixTask.getEmployees();
						employeeNum = vixTask.getEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTaskEmployeeList";
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
										pList.add(new ProjectUnit(v.getId(), "T", v.getQuestName(), v.getCode()));
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

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O") || nodeTreeType.equals("P")) {
						// 加载公司信息和部门信息
						orgUnitList = vixntBaseService.findOrgAndUnitTreeList(nodeTreeType, nodeId, null);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getOrganizationUnits().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (OrganizationUnit organizationUnit : orgTmp.getOrganizationUnits()) {
							ou2List.add(new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
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

	/**
	 * 项目的任务列表
	 * 
	 * @return
	 */
	public String goProjectTaskManagement() {
		return "goProjectTaskManagement";
	}

	/**
	 * 项目任务列表
	 */
	public void goProjectTaskList() {
		try {
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("status," + SearchCondition.EQUAL, "0");
				params.put("isTemp," + SearchCondition.EQUAL, "1");
				params.put("isDeleted," + SearchCondition.EQUAL, "0");
				String vixTaskName = getDecodeRequestParameter("vixTaskName");
				if (StringUtils.isNotEmpty(vixTaskName)) {
					params.put("questName," + SearchCondition.ANYLIKE, vixTaskName);
				}
				if ("P".equals(treeType)) {
					params.put("project.id," + SearchCondition.EQUAL, parentId);
				} else if ("T".equals(treeType)) {
					params.put("parentVixTask.id," + SearchCondition.EQUAL, parentId);
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goMyTask() {
		return "goMyTask";
	}

	private List<VixTask> noStartVixTaskList;
	private List<VixTask> progressVixTaskList;
	private List<VixTask> completeVixTaskList;
	private List<VixTask> timeoutVixTaskList;
	public String goTask() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("flag," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			// params.put("taskStartTime," + SearchCondition.BETWEENAND,
			// DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new
			// Date()));
			Employee e = getEmployee();
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				params.put("complete," + SearchCondition.EQUAL, 0);
				noStartVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, params);
				params.put("complete," + SearchCondition.EQUAL, 1);
				progressVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, params);
				params.put("complete," + SearchCondition.EQUAL, 2);
				completeVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, params);
				params.put("complete," + SearchCondition.EQUAL, 3);
				timeoutVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTask";
	}

	private List<Project> projectList;
	private Project project;
	public String goProjectTask() {
		try {
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isTemp", "1");
				params.put("isDeleted", "0");
				params.put("employeeId", e.getId());
				projectList = vixntBaseService.findProjectByHql(params);
				if (projectList != null && projectList.size() > 0) {
					SortSet ss = new SortSet("createTime");
					Collections.sort(projectList, ss);
					project = projectList.get(0);
					// Map<String, String> taskMap = new HashMap<String,
					// String>();
					Map<String, List<VixTask>> projectTaskMap = new HashMap<String, List<VixTask>>();
					for (Project pj : projectList) {
						Map<String, Object> projectParams = new HashMap<String, Object>();
						projectParams.put("project.id," + SearchCondition.EQUAL, pj.getId());
						projectParams.put("status," + SearchCondition.EQUAL, "0");
						projectParams.put("isTemp," + SearchCondition.EQUAL, "1");
						projectParams.put("isDeleted," + SearchCondition.EQUAL, "0");
						projectParams.put("flag," + SearchCondition.EQUAL, "2");
						projectParams.put("type," + SearchCondition.NOEQUAL, 0);
						List<VixTask> vixTaskList = vixntBaseService.findAllByConditions(VixTask.class, projectParams);
						Collections.sort(vixTaskList, ss);
						projectTaskMap.put(pj.getId(), vixTaskList);
						/*
						 * //所有任务 StringBuilder vixtaskhql = new
						 * StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
						 * +
						 * " and hentity.isDeleted = '0' and hentity.flag = '2' and  "
						 * ); vixtaskhql.append(" hentity.project.id = '" +
						 * pj.getId() + "' "); Long vixtaskCount =
						 * vixntBaseService.findDataCountByHql(vixtaskhql,
						 * "hentity", params); // 未开始任务 StringBuilder
						 * noStartVixtaskhql = new
						 * StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
						 * +
						 * " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '0' and "
						 * ); noStartVixtaskhql.append(" hentity.project.id = '"
						 * + pj.getId() + "' "); Long noStartVixtaskCount =
						 * vixntBaseService.findDataCountByHql(
						 * noStartVixtaskhql, "hentity", params); // 进行中任务
						 * StringBuilder progressVixtaskhql = new
						 * StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
						 * +
						 * " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '1' and  "
						 * );
						 * progressVixtaskhql.append(" hentity.project.id = '" +
						 * pj.getId() + "' "); Long progressVixtaskCount =
						 * vixntBaseService.findDataCountByHql(
						 * progressVixtaskhql, "hentity", params); // 已完成任务
						 * StringBuilder completeVixtaskhql = new
						 * StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
						 * +
						 * " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '2' and  "
						 * );
						 * completeVixtaskhql.append(" hentity.project.id = '" +
						 * pj.getId() + "' "); Long completeVixtaskCount =
						 * vixntBaseService.findDataCountByHql(
						 * completeVixtaskhql, "hentity", params); // 超时完成任务
						 * StringBuilder timeoutVixtaskhql = new
						 * StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
						 * +
						 * " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '3' and  "
						 * ); timeoutVixtaskhql.append(" hentity.project.id = '"
						 * + pj.getId() + "' "); Long timeoutVixtaskCount =
						 * vixntBaseService.findDataCountByHql(
						 * timeoutVixtaskhql, "hentity", params); DecimalFormat
						 * df = new DecimalFormat(".#"); Double noStartPercent =
						 * 0D; Double progessPercent = 0D; Double
						 * completePercent = 0D; Double timeoutPercent = 0D; if
						 * (vixtaskCount > 0) { noStartPercent = (double)
						 * noStartVixtaskCount / vixtaskCount * 100;
						 * progessPercent = (double) progressVixtaskCount /
						 * vixtaskCount * 100; completePercent = (double)
						 * completeVixtaskCount / vixtaskCount * 100;
						 * timeoutPercent = (double) timeoutVixtaskCount /
						 * vixtaskCount * 100; } String noStartPercentStr = "";
						 * if (noStartPercent != 0) { noStartPercentStr =
						 * df.format(noStartPercent) + "%"; } else {
						 * noStartPercentStr = "0%"; } String progessPercentStr
						 * = ""; if (progessPercent != 0) { progessPercentStr =
						 * df.format(progessPercent) + "%"; } else {
						 * progessPercentStr = "0%"; } String completePercentStr
						 * = ""; if (completePercent != 0) { completePercentStr
						 * = df.format(completePercent) + "%"; } else {
						 * completePercentStr = "0%"; } String timeoutPercentStr
						 * = ""; if (timeoutPercent != 0) { timeoutPercentStr =
						 * df.format(timeoutPercent) + "%"; } else {
						 * timeoutPercentStr = "0%"; } taskMap.put(pj.getId(),
						 * noStartPercentStr + ":" + progessPercentStr + ":" +
						 * completePercentStr + ":" + timeoutPercentStr);
						 */
					}
					// getRequest().setAttribute("taskMap", taskMap);
					List<VixTask> vixTasks = projectTaskMap.get(project.getId());
					if (vixTasks != null && vixTasks.size() > 0) {
						vixTask = vixTasks.get(0);
						Map<String, Object> p = new HashMap<String, Object>();
						p.put("parentVixTask.id," + SearchCondition.EQUAL, vixTask.getId());
						p.put("status," + SearchCondition.EQUAL, "0");
						p.put("isTemp," + SearchCondition.EQUAL, "1");
						p.put("isDeleted," + SearchCondition.EQUAL, "0");
						p.put("flag," + SearchCondition.EQUAL, "2");
						p.put("complete," + SearchCondition.EQUAL, 0);
						noStartVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
						p.put("complete," + SearchCondition.EQUAL, 1);
						progressVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
						p.put("complete," + SearchCondition.EQUAL, 2);
						completeVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
						p.put("complete," + SearchCondition.EQUAL, 3);
						timeoutVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
					}
					getRequest().setAttribute("projectTaskMap", projectTaskMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectTask";
	}

	public String loadProjectTask() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			if (StrUtils.isNotEmpty(parentId)) {
				p.put("parentVixTask.id," + SearchCondition.EQUAL, parentId);
				p.put("status," + SearchCondition.EQUAL, "0");
				p.put("isTemp," + SearchCondition.EQUAL, "1");
				p.put("isDeleted," + SearchCondition.EQUAL, "0");
				p.put("flag," + SearchCondition.EQUAL, "2");
				p.put("complete," + SearchCondition.EQUAL, 0);
				noStartVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
				p.put("complete," + SearchCondition.EQUAL, 1);
				progressVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
				p.put("complete," + SearchCondition.EQUAL, 2);
				completeVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
				p.put("complete," + SearchCondition.EQUAL, 3);
				timeoutVixTaskList = vixntBaseService.findAllByConditions(VixTask.class, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadProjectTask";
	}
	public String loadProjectTaskPrecent() {
		try {
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isTemp", "1");
				params.put("isDeleted", "0");
				params.put("employeeId", e.getId());
				projectList = vixntBaseService.findProjectByHql(params);
				if (projectList != null && projectList.size() > 0) {
					SortSet ss = new SortSet("createTime");
					Collections.sort(projectList, ss);
					Map<String, String> taskMap = new HashMap<String, String>();
					for (Project pj : projectList) {
						// 所有任务
						StringBuilder vixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' " + " and hentity.isDeleted = '0' and hentity.flag = '2' and  ");
						vixtaskhql.append(" hentity.project.id = '" + pj.getId() + "' ");
						Long vixtaskCount = vixntBaseService.findDataCountByHql(vixtaskhql, "hentity", params);
						// 未开始任务
						StringBuilder noStartVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' " + " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '0' and ");
						noStartVixtaskhql.append(" hentity.project.id = '" + pj.getId() + "' ");
						Long noStartVixtaskCount = vixntBaseService.findDataCountByHql(noStartVixtaskhql, "hentity", params);
						// 进行中任务
						StringBuilder progressVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' " + " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '1' and  ");
						progressVixtaskhql.append(" hentity.project.id = '" + pj.getId() + "' ");
						Long progressVixtaskCount = vixntBaseService.findDataCountByHql(progressVixtaskhql, "hentity", params);
						// 已完成任务
						StringBuilder completeVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' " + " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '2' and  ");
						completeVixtaskhql.append(" hentity.project.id = '" + pj.getId() + "' ");
						Long completeVixtaskCount = vixntBaseService.findDataCountByHql(completeVixtaskhql, "hentity", params);
						// 超时完成任务
						StringBuilder timeoutVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' " + " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '3' and  ");
						timeoutVixtaskhql.append(" hentity.project.id = '" + pj.getId() + "' ");
						Long timeoutVixtaskCount = vixntBaseService.findDataCountByHql(timeoutVixtaskhql, "hentity", params);
						DecimalFormat df = new DecimalFormat(".#");
						Double noStartPercent = 0D;
						Double progessPercent = 0D;
						Double completePercent = 0D;
						Double timeoutPercent = 0D;
						if (vixtaskCount > 0) {
							noStartPercent = (double) noStartVixtaskCount / vixtaskCount * 100;
							progessPercent = (double) progressVixtaskCount / vixtaskCount * 100;
							completePercent = (double) completeVixtaskCount / vixtaskCount * 100;
							timeoutPercent = (double) timeoutVixtaskCount / vixtaskCount * 100;
						}
						String noStartPercentStr = "";
						if (noStartPercent != 0) {
							noStartPercentStr = df.format(noStartPercent) + "%";
						} else {
							noStartPercentStr = "0%";
						}
						String progessPercentStr = "";
						if (progessPercent != 0) {
							progessPercentStr = df.format(progessPercent) + "%";
						} else {
							progessPercentStr = "0%";
						}
						String completePercentStr = "";
						if (completePercent != 0) {
							completePercentStr = df.format(completePercent) + "%";
						} else {
							completePercentStr = "0%";
						}
						String timeoutPercentStr = "";
						if (timeoutPercent != 0) {
							timeoutPercentStr = df.format(timeoutPercent) + "%";
						} else {
							timeoutPercentStr = "0%";
						}
						taskMap.put(pj.getId(), noStartPercentStr + ":" + progessPercentStr + ":" + completePercentStr + ":" + timeoutPercentStr);
					}
					getRequest().setAttribute("taskMap", taskMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadProjectTaskPrecent";
	}

	public String goTeamTask() {
		try {
			Employee e = getEmployee();
			String employeeids = getBusinessOrgDetailBo4ReportLineEmployeeIds();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(employeeids)) {
				params.put("id," + SearchCondition.IN, employeeids);
			}
			employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
			String employeeId = getRequestParameter("employeeId");
			if (StrUtils.isNotEmpty(employeeId)) {
				emp = vixntBaseService.findEntityById(Employee.class, employeeId);
			} else if (e != null) {
				emp = e;
			} else if (employeeList != null && employeeList.size() > 0) {
				emp = employeeList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTeamTask";
	}

	// 任务事件
	public void calendarEvents() {
		try {
			String json = "";
			String employeeId = getRequestParameter("employeeId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("employee.id," + SearchCondition.EQUAL, employeeId);
			vixTaskList = vixntBaseService.findAllByConditions(VixTask.class, params);
			if (vixTaskList != null && vixTaskList.size() > 0) {
				JSONArray array = new JSONArray();
				for (VixTask vixTask : vixTaskList) {
					JSONObject object = new JSONObject();
					object.put("id", vixTask.getId());
					object.put("title", vixTask.getQuestName());
					if (vixTask.getTaskStartTime() != null) {
						object.put("start", dateFormat.format(vixTask.getTaskStartTime()));
					}
					if (vixTask.getTaskEndTime() != null) {
						object.put("end", dateFormat.format(vixTask.getTaskEndTime()));
					}
					// object.put("allDay", calendars.getAllDay());
					String priority = "";
					if ("0".equals(vixTask.getFlag())) {
						priority = "bg-color-blue";
					} else if ("1".equals(vixTask.getFlag())) {
						priority = "bg-color-green";
					} else if ("2".equals(vixTask.getFlag())) {
						priority = "bg-color-orange";
					}
					object.put("className", "[\"event\", \"" + priority + "\"]");
					// object.put("icon", calendars.getIcon());
					array.add(object);
				}
				json = array.toString();
			}
			if (!"".equals(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理修改操作 */
	public void eventDropCanlendar() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				isSave = false;
			}
			String startTime = getRequestParameter("startTime");
			String endTime = getRequestParameter("endTime");
			vixTask = vixntBaseService.findEntityById(VixTask.class, id);
			if (vixTask != null) {
				if (StringUtils.isNotEmpty(startTime)) {
					vixTask.setTaskStartTime(dateFormat.parse(startTime));
				}
				if (StringUtils.isNotEmpty(endTime)) {
					vixTask.setTaskEndTime(dateFormat.parse(endTime));
				}
				vixntBaseService.merge(vixTask);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	public void goMyAllTaskList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName," + SearchCondition.ANYLIKE, vixTaskName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, VixTask.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 团队任务
	 */
	public void goTeamTaskList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			Employee e = getEmployee();
			if (e != null) {
				p.put(e.getId(), e);
				List<Employee> employeeList = vixntBaseService.findBusinessOrgDetailBo4ReportLine(e.getTenantId(), e.getId());
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						if (employee != null) {
							p.put(employee.getId(), employee);
						}
					}
				}
			}

			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName", "%" + vixTaskName + "%");
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName", "%" + searchtaskname + "%");
			}
			String complete = getRequestParameter("complete");
			if (StringUtils.isNotEmpty(complete)) {
				params.put("complete", Integer.parseInt(complete));
			}
			// params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");

			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				if ("E".equals(treeType)) {
					parentId = parentId.substring(0, parentId.length() - 1);
					if (p.containsKey(parentId)) {
						params.put("employeeId", parentId);
						pager = vixntBaseService.findTaskPager(pager, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取所有团队人员的任务列表
	public void goAllTeamTaskList() {
		try {
			String employeeIds = "";
			Employee e = getEmployee();
			Pager pager = getPager();
			if (e != null) {
				employeeIds += "," + e.getId();
				List<Employee> employeeList = vixntBaseService.findBusinessOrgDetailBo4ReportLine(e.getTenantId(), e.getId());
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						if (employee != null) {
							employeeIds += "," + employee.getId();
						}
					}
				}
				Map<String, Object> params = new HashMap<String, Object>();
				String searchtaskname = getDecodeRequestParameter("vixTaskName");
				if (StringUtils.isNotEmpty(searchtaskname)) {
					params.put("questName", "%" + searchtaskname + "%");
				}
				String complete = getRequestParameter("complete");
				if (StringUtils.isNotEmpty(complete)) {
					params.put("complete", Integer.parseInt(complete));
				}
				params.put("isTemp", "1");
				params.put("flag", "1");
				params.put("isDeleted", "0");
				if (StringUtils.isNotEmpty(employeeIds)) {
					params.put("employeeIds", employeeIds);
				}
				pager = vixntBaseService.findAllTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除执行人
	public void deleteVixTaskEmployeeById() {
		try {
			Employee employee = getEmployee();
			if (StringUtils.isNotEmpty(id)) {
				Employee emp = vixntBaseService.findEntityById(Employee.class, id);
				if (StringUtils.isNotEmpty(taskId)) {
					vixTask = vixntBaseService.findEntityById(VixTask.class, taskId);
					if (vixTask != null) {
						Set<Employee> subEmployees = vixTask.getEmployees();
						if (emp != null) {
							userIdStr = emp.getUserId();
							subEmployees.remove(emp);
						}
						vixTask.setEmployees(subEmployees);
						vixTask = vixntBaseService.merge(vixTask);
						WxpQyWeixinSite site = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", vixTask.getTenantId());
						if (StringUtils.isNotEmpty(userIdStr)) {
							// 更新企业号token
							saveOrUpdateWxpWeixinSite(site);
							WxQyUtil.messageSend("3", userIdStr, employee.getName() + "取消了你的" + vixTask.getQuestName() + "任务!", site.getQiyeAccessToken());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 显示任务执行人员列表
	public String goShowTaskEmployee() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null && vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					empList = vixTask.getEmployees();
					employeeNum = vixTask.getEmployees().size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTaskEmployee";
	}

	/**
	 * 项目人员内容切换
	 * 
	 * @return
	 */
	public String goTaskEmployeeListContent() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				emp = vixntBaseService.findEntityById(Employee.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTaskEmployeeListContent";
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

	/**
	 * 下载附件
	 * 
	 * @return
	 */
	public String downloadUploader() {
		if (StringUtils.isNotEmpty(id)) {
			try {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadUploader";
	}

	/**
	 * 改变任务状态
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	void updateTaskComplete() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("complete," + SearchCondition.EQUAL, "1");
			List<VixTask> vixTaskList = vixntBaseService.findAllByConditions(VixTask.class, params);
			if (vixTaskList != null) {
				for (VixTask vixTask : vixTaskList) {
					Date date = new Date();
					if (vixTask != null && vixTask.getTaskEndTime() != null) {
						if (vixTask.getTaskEndTime().getTime() < date.getTime()) {
							vixTask.setComplete(3);
							vixntBaseService.merge(vixTask);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void findBusinessOrgViewTreeToJson() {
		try {
			Employee employee = getEmployee();
			if (StringUtils.isNotEmpty(id)) {
				loadBizOrg(null, id);
			} else {
				if (employee != null) {
					Set<OrgPosition> orgPositions = employee.getOrgPositions();
					if (orgPositions != null && orgPositions.size() > 0) {
						for (OrgPosition orgPosition : orgPositions) {
							if (orgPosition != null) {
								orgPosition = vixntBaseService.findEntityById(OrgPosition.class, orgPosition.getId());
								if (orgPosition != null) {
									List<BusinessOrgEmpView> bizOrgViewList = new LinkedList<BusinessOrgEmpView>();
									BusinessOrgEmpView businessOrgEmpView = vixntBaseService.findEntityById(BusinessOrgEmpView.class, orgPosition.getId() + "P");
									if (businessOrgEmpView != null) {
										bizOrgViewList.add(businessOrgEmpView);
									}

									loadBizOrg(bizOrgViewList, null);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadBizOrg(List<BusinessOrgEmpView> bizOrgViewList, String nodeId) {
		try {
			if (bizOrgViewList != null && bizOrgViewList.size() > 0) {

			} else {
				bizOrgViewList = businessViewService.findBusinessOrgEmpViewList(nodeId);
			}

			if (bizOrgViewList == null) {
				bizOrgViewList = new LinkedList<BusinessOrgEmpView>();
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = bizOrgViewList.size();
			for (int i = 0; i < count; i++) {
				BusinessOrgEmpView orgView = bizOrgViewList.get(i);

				List<BusinessOrgEmpView> subList = businessViewService.findBusinessOrgEmpViewList(orgView.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{id:\"");
					strSb.append(orgView.getId());
					strSb.append("\",treeType:\"");
					strSb.append(orgView.getViewType());
					strSb.append("\",name:\"");
					strSb.append(orgView.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(orgView.getId());
					strSb.append("\",treeType:\"");
					strSb.append(orgView.getViewType());
					strSb.append("\",name:\"");
					strSb.append(orgView.getName());
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

	public void checkVixTaskEmployeeNum() {
		try {
			if (StringUtils.isNotEmpty(taskId)) {
				vixTask = vixntBaseService.findEntityById(VixTask.class, taskId);
				if (vixTask != null) {
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 1) {
						renderText("1");
					} else {
						renderText("2");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取会议纪要
	public void getMeetingSummaryData() {
		try {
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("id," + SearchCondition.IN, meetingids);
			List<ApplicationMg> mList = vixntBaseService.findAllByConditions(ApplicationMg.class, params);
			applicationMgData = "";
			for (ApplicationMg applicationMg : mList) {
				Set<MeetingSummary> subMeetingSummarys = applicationMg.getSubMeetingSummarys();
				for (MeetingSummary meetingSummary : subMeetingSummarys) {
					if (meetingSummary != null) {
						applicationMgData += meetingSummary.getName() + ":" + "\n" + meetingSummary.getMeetingContent() + "\n";
					}
				}
			}
			if (StringUtils.isNotEmpty(applicationMgData)) {
				renderText(applicationMgData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the distributeVixTaskList
	 */
	public List<VixTask> getDistributeVixTaskList() {
		return distributeVixTaskList;
	}

	/**
	 * @param distributeVixTaskList
	 *            the distributeVixTaskList to set
	 */
	public void setDistributeVixTaskList(List<VixTask> distributeVixTaskList) {
		this.distributeVixTaskList = distributeVixTaskList;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
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
	 * @return the taskId
	 */
	@Override
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getMeetingids() {
		return meetingids;
	}

	public void setMeetingids(String meetingids) {
		this.meetingids = meetingids;
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
	 * @return the schedule
	 */
	public Integer getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule
	 *            the schedule to set
	 */
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
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

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Set<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(Set<Employee> empList) {
		this.empList = empList;
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
	 * @return the distributeVixTaskNum
	 */
	public Integer getDistributeVixTaskNum() {
		return distributeVixTaskNum;
	}

	/**
	 * @param distributeVixTaskNum
	 *            the distributeVixTaskNum to set
	 */
	public void setDistributeVixTaskNum(Integer distributeVixTaskNum) {
		this.distributeVixTaskNum = distributeVixTaskNum;
	}

	/**
	 * @return the imagesId
	 */
	public String getImagesId() {
		return imagesId;
	}

	/**
	 * @param imagesId
	 *            the imagesId to set
	 */
	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the taskNum
	 */
	public Integer getTaskNum() {
		return taskNum;
	}

	/**
	 * @param taskNum
	 *            the taskNum to set
	 */
	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
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
	 * @return the employeeNum
	 */
	public Integer getEmployeeNum() {
		return employeeNum;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @param employeeNum
	 *            the employeeNum to set
	 */
	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
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

	public Set<EvaluationReview> getEvaluationReviewList() {
		return evaluationReviewList;
	}

	public void setEvaluationReviewList(Set<EvaluationReview> evaluationReviewList) {
		this.evaluationReviewList = evaluationReviewList;
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

	public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}

	public void setTaskTypeList(List<TaskType> taskTypeList) {
		this.taskTypeList = taskTypeList;
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

	public Uploader getUploader() {
		return uploader;
	}

	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public String getApplicationMgData() {
		return applicationMgData;
	}

	public void setApplicationMgData(String applicationMgData) {
		this.applicationMgData = applicationMgData;
	}

	public List<ApplicationMg> getApplicationMgList() {
		return applicationMgList;
	}

	public void setApplicationMgList(List<ApplicationMg> applicationMgList) {
		this.applicationMgList = applicationMgList;
	}

	public List<VixTask> getNoStartVixTaskList() {
		return noStartVixTaskList;
	}

	public void setNoStartVixTaskList(List<VixTask> noStartVixTaskList) {
		this.noStartVixTaskList = noStartVixTaskList;
	}

	public List<VixTask> getProgressVixTaskList() {
		return progressVixTaskList;
	}

	public void setProgressVixTaskList(List<VixTask> progressVixTaskList) {
		this.progressVixTaskList = progressVixTaskList;
	}

	public List<VixTask> getCompleteVixTaskList() {
		return completeVixTaskList;
	}

	public void setCompleteVixTaskList(List<VixTask> completeVixTaskList) {
		this.completeVixTaskList = completeVixTaskList;
	}

	public List<VixTask> getTimeoutVixTaskList() {
		return timeoutVixTaskList;
	}

	public void setTimeoutVixTaskList(List<VixTask> timeoutVixTaskList) {
		this.timeoutVixTaskList = timeoutVixTaskList;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}