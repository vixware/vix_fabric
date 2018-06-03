package com.vix.wechat.taskAndPlan.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//import static java.util.Comparator.comparing;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.typeSettings.entity.TaskType;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

/**
 * 任务与计划
 * 
 * @ClassFullName com.vix.wechat.taskAndPlan.action.WeChatTaskPlanAction
 *
 * @author bjitzhang
 *
 * @date 2016年3月18日
 *
 */
@Controller
@Scope("prototype")
public class WeChatTaskPlanAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private VixTask vixTask;
	private String ids;
	private List<VixTask> vixTaskList;
	private List<TaskType> taskTypeList;
	private Set<WxpQyPicture> wxpQyPictureList;
	private List<Uploader> uploaderList;
	private List<Employee> employeeList;
	private Integer employeeNum = 0;
	private Integer evaluationReviewNum = 0;
	private Integer docNum = 0;
	private Integer taskSchedule = 0;
	private Integer schedule = 0;
	private String flog;
	private String vixTaskMemo;
	private String userIdStr = "";
	private String uploaderId;
	private String wxpQyPictureId;
	private EvaluationReview evaluationReview;
	private String userId;
	private Employee employee;
	private List<EvaluationReview> evaluationReviewList;
	private ExecutionFeedback executionFeedback;
	private String vixTaskId;
	private String check = "0";
	private String isFeedback;
	private String corpid;// 企业号ID
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	// 我下发的任务
	public String goTaskList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);

			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goTaskList.action?corpid=" + corpid);
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			String searchname = getDecodeRequestParameter("searchname");
			if (StringUtils.isNotEmpty(searchname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchname);
			}
			if (StringUtils.isNotEmpty(userId)) {
				params.put("employee.userId," + SearchCondition.EQUAL, userId);
			}
			params.put("complete," + SearchCondition.NOEQUAL, 2);
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			vixTaskList = wechatBaseService.findAllDataByConditions(VixTask.class, params);

			// vixTaskList.sort(comparing(VixTask::getCreateTime));

			// 倒序
			Collections.sort(vixTaskList, new Comparator<VixTask>() {
				@Override
				public int compare(VixTask o1, VixTask o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTaskList";
	}

	public void saveOrUpdate() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (vixTask != null && StringUtils.isNotEmpty(vixTask.getId())) {
				VixTask task = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", vixTask.getId());
				BeanUtils.copyProperties(task, vixTask, new String[] { "questName", "taskDescription", "priority", "taskStartTime", "taskEndTime", "employees" });
			} else {
				if (site != null) {
					vixTask.setTenantId(site.getTenantId());
					vixTask.setCompanyInnerCode(site.getCompanyInnerCode());
				}
				vixTask.setCreateTime(new Date());

				// 未开始
				vixTask.setComplete(0);
				// 正常任务
				vixTask.setStatus("0");
				// 任务进度
				vixTask.setTaskSchedule(0);

				vixTask.setIsDeleted("0");
			}
			// 任务类型
			String sub = getRequestParameter("sub");
			if (StringUtils.isNotEmpty(sub)) {
				if ("0".equals(sub)) {
					// 草稿
					vixTask.setIsTemp("0");
				} else {
					vixTask.setIsTemp("1");
				}
			}
			if (employee != null) {
				vixTask.setEmployee(employee);
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						subEmployees.add(employee);
						userIdStr += "|" + employee.getUserId();
					}
				}
				vixTask.setEmployees(subEmployees);
			}
			if (vixTask.getTaskType() == null || StringUtils.isEmpty(vixTask.getTaskType().getId()) || "-1".equals(vixTask.getTaskType().getId())) {
				vixTask.setTaskType(null);
			}
			vixTask = wechatBaseService.mergeOriginal(vixTask);

			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setVixTask(vixTask);
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
				vixTask.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				vixTask = wechatBaseService.mergeOriginal(vixTask);
			}
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 3) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setTaskDefinition(vixTask);
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				uploader = wechatBaseService.mergeOriginal(uploader);
			}
			if (StringUtils.isNotEmpty(userIdStr)) {
				if ("1".equals(sub)) {
					// 更新企业号token
					WxQyUtil.messageSendNews(taskAgentId, userIdStr.substring(1), vixTask.getQuestName(), site.getQiyeAccessToken(), vixTask.getTaskDescription(), redirect_ip + "/wechat/weChatTaskPlanAction!goMyTask.action?id=" + vixTask.getId() + "&corpid=" + site.getCorpId(), "");
				}
			}
			renderText(vixTask.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回复
	 */
	public void saveOrUpdateEvaluationReview() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (vixTask != null && StringUtils.isNotEmpty(vixTask.getId())) {
				evaluationReview.setVixTask(vixTask);
			}
			if (employee != null) {
				evaluationReview.setEmployee(employee);
				evaluationReview.setTenantId(employee.getTenantId());
				evaluationReview.setEvaluationTime(new Date());
				evaluationReview.setCompanyCode(employee.getCompanyCode());
				evaluationReview.setCompanyInnerCode(employee.getCompanyInnerCode());
				evaluationReview.setCreateTime(new Date());
				evaluationReview = wechatBaseService.mergeOriginal(evaluationReview);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 评论内容区切换
	 * 
	 * @return
	 */
	public String goEvaluationReviewList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
				if (vixTask != null && vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(vixTask.getSubEvaluationReviews());
					evaluationReviewNum = evaluationReviewList.size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goEvaluationReviewList";
	}

	public String goTaskDetail() {
		try {
			vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
			if (vixTask != null) {

				if (vixTask.getEmployee() != null && vixTask.getEmployee().getUserId() != null && vixTask.getEmployee().getUserId().equals(userId)) {
					check = "1";
				}
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					employeeList = new ArrayList<Employee>();
					employeeList.addAll(vixTask.getEmployees());
					employeeNum = employeeList.size();
				}
				wxpQyPictureList = vixTask.getSubWxpQyPictures();
				if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
					uploaderList = new ArrayList<Uploader>();
					uploaderList.addAll(vixTask.getUploader());
					docNum = uploaderList.size();
				}
				if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(vixTask.getSubEvaluationReviews());
					evaluationReviewNum = evaluationReviewList.size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTaskDetail";
	}

	// 新增任务
	public String goWechatTask() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + id + "&corpid=" + corpid);
				}
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("companyInnerCode," + SearchCondition.EQUAL, site.getCompanyInnerCode());
			taskTypeList = wechatBaseService.findAllDataByConditions(TaskType.class, params);
			if (StringUtils.isNotEmpty(id)) {
				vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
				if (vixTask != null) {
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(vixTask.getEmployees());
						for (Employee employee : employeeList) {
							ids += "," + employee.getId();
						}
					}
					// 获取图片
					if (vixTask.getSubWxpQyPictures() != null && vixTask.getSubWxpQyPictures().size() > 0) {
						wxpQyPictureList = vixTask.getSubWxpQyPictures();
					}
					// 获取附件列表
					if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
						uploaderList = new ArrayList<Uploader>();
						uploaderList.addAll(vixTask.getUploader());
						docNum = uploaderList.size();
					}
				}
			}
			if (StringUtils.isNotEmpty(ids)) {
				// 获取选定成员
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWechatTask";
	}

	/**
	 * 保存反馈更新进度
	 */
	public void saveOrUpdateExecutionFeedback() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(vixTaskId)) {
				vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", vixTaskId);
				if (vixTask != null && taskSchedule != null) {
					vixTask.setTaskSchedule(taskSchedule);
					if (taskSchedule > 0 && taskSchedule < 100) {
						vixTask.setComplete(1);
					} else if (taskSchedule <= 0) {
						vixTask.setComplete(0);
					} else if (taskSchedule == 100) {
						vixTask.setComplete(2);
					}
					vixTask = wechatBaseService.mergeOriginal(vixTask);
					executionFeedback.setTaskDefinition(vixTask);
				}
				if (employee != null) {
					executionFeedback.setCreator(employee.getName());
					executionFeedback.setTenantId(employee.getTenantId());
					executionFeedback.setCompanyInnerCode(employee.getCompanyInnerCode());
				}
				executionFeedback.setFeedbackTime(new Date());
				executionFeedback = wechatBaseService.mergeOriginal(executionFeedback);
				renderText(vixTaskId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束任务
	 * 
	 * @return
	 */
	public void goFinishTask() {
		try {
			if (vixTask != null && StringUtils.isNotEmpty(vixTask.getId())) {
				VixTask task = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", vixTask.getId());
				if (task != null) {
					task.setComplete(2);
					task.setTaskSchedule(100);
					task.setMemo(vixTask.getMemo());
					task = wechatBaseService.mergeOriginal(task);
					renderText(task.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 完成任务
	public void saveTaskSchedule() {
		try {
			if (StringUtils.isNotEmpty(vixTaskId)) {
				vixTask = wechatBaseService.findEntityById(VixTask.class, vixTaskId);
				if (vixTask != null) {
					if (StringUtils.isNotEmpty(vixTaskMemo)) {
						vixTask.setMemo(vixTaskMemo);
					}
					// 完成任务
					vixTask.setComplete(2);
					vixTask.setTaskSchedule(100);
					vixTask = wechatBaseService.mergeOriginal(vixTask);
				}
				renderText(vixTaskId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭任务
	public void closeTask() {
		try {
			if (StringUtils.isNotEmpty(vixTaskId)) {
				vixTask = wechatBaseService.findEntityById(VixTask.class, vixTaskId);
				if (vixTask != null) {
					vixTask.setStatus("1");
					vixTask = wechatBaseService.mergeOriginal(vixTask);
				}
				renderText(vixTaskId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重启任务
	 */
	public void goReStartTask() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			if (StringUtils.isNotEmpty(id)) {
				vixTask = wechatBaseService.findEntityById(VixTask.class, id);
				if (vixTask != null) {
					// 未开始
					vixTask.setComplete(0);
					// 正常任务
					vixTask.setStatus("0");
					// 任务进度
					vixTask.setTaskSchedule(0);

					vixTask.setIsDeleted("0");
					vixTask = wechatBaseService.mergeOriginal(vixTask);
					if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
						for (Employee employee : vixTask.getEmployees()) {
							if (employee != null) {
								userIdStr += "|" + employee.getUserId();
							}
						}
					}
					if (StringUtils.isNotEmpty(userIdStr)) {
						WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
						if (site != null) {
							WxQyUtil.messageSendNews(taskAgentId, userIdStr.substring(1), vixTask.getQuestName(), site.getQiyeAccessToken(), vixTask.getTaskDescription(), redirect_ip + "/wechat/weChatTaskPlanAction!goMyTask.action?id=" + vixTask.getId() + "&corpid=" + site.getCorpId(), "");
						}
					}
					renderText(vixTask.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 负责人
	 * 
	 * @return
	 */
	public String goResponsiblePersonChoose() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				vixTask = wechatBaseService.findEntityById(VixTask.class, id);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("empType," + SearchCondition.EQUAL, "S");
			employeeList = wechatBaseService.findAllDataByConditions(Employee.class, params);
			if (employeeList != null && employeeList.size() > 0) {
				employeeNum = employeeList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goResponsiblePersonChoose";
	}

	/**
	 * 我的任务详情
	 * 
	 * @return
	 */
	public String goMyTask() {
		try {
			corpid = api_qiye_corpid;
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId")) && !"null".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goMyTask.action?id=" + id + "&corpid=" + corpid);
				}
			}
			System.out.println("userId2:" + userId);
			vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
			if (vixTask != null) {
				System.out.println(vixTask.getEmployee().getUserId());
				if (vixTask.getEmployee() != null && vixTask.getEmployee().getUserId() != null && vixTask.getEmployee().getUserId().equals(userId)) {
					check = "1";
				}
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					employeeList = new ArrayList<Employee>();
					employeeList.addAll(vixTask.getEmployees());
					employeeNum = employeeList.size();
				}
				wxpQyPictureList = vixTask.getSubWxpQyPictures();
				if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
					uploaderList = new ArrayList<Uploader>();
					uploaderList.addAll(vixTask.getUploader());
					docNum = uploaderList.size();
				}
				if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(vixTask.getSubEvaluationReviews());
					evaluationReviewNum = evaluationReviewList.size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
				if (vixTask != null) {
					if (StringUtils.isNotEmpty(vixTask.getStartFeedbackTime()) && StringUtils.isNotEmpty(vixTask.getEndFeedbackTime())) {
						boolean check = isInTime(vixTask.getStartFeedbackTime() + "-" + vixTask.getEndFeedbackTime(), sdf.format(new Date()));
						if (check) {
							isFeedback = "1";
						} else {
							isFeedback = "0";
						}
					} else {
						isFeedback = "1";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyTask";
	}

	/** 批量处理删除操作 */
	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids) && !"undefined".equals(ids)) {
				for (String idStr : ids.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						VixTask vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", idStr);
						if (vixTask != null) {
							vixTask.setIsDeleted("1");
							wechatBaseService.mergeOriginal(vixTask);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 我的草稿任务详情
	 * 
	 * @return
	 */
	public String goMyDraftTask() {
		try {
			Map<String, Object> params = getParams();
			taskTypeList = wechatBaseService.findAllDataByConditions(TaskType.class, params);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}
			vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
			if (vixTask != null) {

				if (vixTask.getEmployee() != null && vixTask.getEmployee().getUserId() != null && vixTask.getEmployee().getUserId().equals(userId)) {
					check = "1";
				}
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					employeeList = new ArrayList<Employee>();
					employeeList.addAll(vixTask.getEmployees());
					employeeNum = employeeList.size();
				}
				wxpQyPictureList = vixTask.getSubWxpQyPictures();
				if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
					uploaderList = new ArrayList<Uploader>();
					uploaderList.addAll(vixTask.getUploader());
					docNum = uploaderList.size();
				}
				if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(vixTask.getSubEvaluationReviews());
					evaluationReviewNum = evaluationReviewList.size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyDraftTask";
	}

	/**
	 * 我的草稿列表
	 * 
	 * @return
	 */
	public String goMyDraftTaskList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goMyDraftTaskList.action?corpid=" + corpid);
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			String searchname = getDecodeRequestParameter("searchname");
			if (StringUtils.isNotEmpty(searchname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchname);
			}
			if (StringUtils.isNotEmpty(userId)) {
				params.put("employee.userId," + SearchCondition.EQUAL, userId);
			}
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			vixTaskList = wechatBaseService.findAllDataByConditions(VixTask.class, params);
			// 倒序
			Collections.sort(vixTaskList, new Comparator<VixTask>() {
				@Override
				public int compare(VixTask o1, VixTask o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyDraftTaskList";
	}

	/**
	 * 我的任务列表
	 * 
	 * @return
	 */
	public String goMyTaskList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goMyTaskList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}

			Map<String, Object> params = getParams();
			params.put("complete," + SearchCondition.NOEQUAL, 2);
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());

			String searchname = getDecodeRequestParameter("searchname");
			if (StringUtils.isNotEmpty(searchname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchname);
			}
			List<VixTask> taskList = wechatBaseService.findAllDataByConditions(VixTask.class, params);

			Map<String, VixTask> empMap = new HashMap<String, VixTask>();
			for (VixTask vixTask : taskList) {
				Set<Employee> subEmployees = vixTask.getEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + vixTask.getId(), vixTask);
				}
			}
			vixTaskList = new ArrayList<VixTask>();
			for (VixTask vixTask : taskList) {
				if (empMap.containsKey(e.getUserId() + vixTask.getId())) {
					vixTaskList.add(empMap.get(e.getUserId() + vixTask.getId()));
				}
			}
			// 倒序
			Collections.sort(vixTaskList, new Comparator<VixTask>() {
				@Override
				public int compare(VixTask o1, VixTask o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyTaskList";
	}

	/**
	 * 结束的任务详情
	 * 
	 * @return
	 */
	public String goFinishedTask() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}
			vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
			if (vixTask != null) {

				if (vixTask.getEmployee() != null && vixTask.getEmployee().getUserId() != null && vixTask.getEmployee().getUserId().equals(userId)) {
					check = "1";
				}
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					employeeList = new ArrayList<Employee>();
					employeeList.addAll(vixTask.getEmployees());
					employeeNum = employeeList.size();
				}
				wxpQyPictureList = vixTask.getSubWxpQyPictures();
				if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
					uploaderList = new ArrayList<Uploader>();
					uploaderList.addAll(vixTask.getUploader());
					docNum = uploaderList.size();
				}
				if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(vixTask.getSubEvaluationReviews());
					evaluationReviewNum = evaluationReviewList.size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goFinishedTask";
	}

	public String goCloseedTask() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}
			vixTask = wechatBaseService.findEntityByAttributeNoTenantId(VixTask.class, "id", id);
			if (vixTask != null) {

				if (vixTask.getEmployee() != null && vixTask.getEmployee().getUserId() != null && vixTask.getEmployee().getUserId().equals(userId)) {
					check = "1";
				}
				if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
					employeeList = new ArrayList<Employee>();
					employeeList.addAll(vixTask.getEmployees());
					employeeNum = employeeList.size();
				}
				wxpQyPictureList = vixTask.getSubWxpQyPictures();
				if (vixTask.getUploader() != null && vixTask.getUploader().size() > 0) {
					uploaderList = new ArrayList<Uploader>();
					uploaderList.addAll(vixTask.getUploader());
					docNum = uploaderList.size();
				}
				if (vixTask.getSubEvaluationReviews() != null && vixTask.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(vixTask.getSubEvaluationReviews());
					evaluationReviewNum = evaluationReviewList.size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCloseedTask";
	}

	/**
	 * 已完成的任务列表
	 * 
	 * @return
	 */
	public String goFinishedTaskList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goFinishedTaskList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();

			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("complete," + SearchCondition.EQUAL, 2);
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");

			String searchname = getDecodeRequestParameter("searchname");
			if (StringUtils.isNotEmpty(searchname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchname);
			}
			List<VixTask> taskList = wechatBaseService.findAllDataByConditions(VixTask.class, params);

			Map<String, VixTask> empMap = new HashMap<String, VixTask>();
			for (VixTask vixTask : taskList) {
				Set<Employee> subEmployees = vixTask.getEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + vixTask.getId(), vixTask);
				}
			}
			vixTaskList = new ArrayList<VixTask>();
			for (VixTask vixTask : taskList) {
				if (empMap.containsKey(e.getUserId() + vixTask.getId())) {
					vixTaskList.add(empMap.get(e.getUserId() + vixTask.getId()));
				}
			}
			if (evaluationReviewList != null && evaluationReviewList.size() > 0) {
				evaluationReviewNum = evaluationReviewList.size();
			}
			// 倒序
			Collections.sort(vixTaskList, new Comparator<VixTask>() {
				@Override
				public int compare(VixTask o1, VixTask o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goFinishedTaskList";
	}

	/**
	 * 关闭的任务列表
	 * 
	 * @return
	 */
	public String goCloseedTaskList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatTaskPlanAction!goCloseedTaskList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();

			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			// params.put("complete," + SearchCondition.EQUAL, 2);
			params.put("status," + SearchCondition.EQUAL, "1");
			params.put("isTemp," + SearchCondition.EQUAL, "1");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");

			String searchname = getDecodeRequestParameter("searchname");
			if (StringUtils.isNotEmpty(searchname)) {
				params.put("questName," + SearchCondition.ANYLIKE, searchname);
			}
			List<VixTask> taskList = wechatBaseService.findAllDataByConditions(VixTask.class, params);

			Map<String, VixTask> empMap = new HashMap<String, VixTask>();
			for (VixTask vixTask : taskList) {
				Set<Employee> subEmployees = vixTask.getEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + vixTask.getId(), vixTask);
				}
			}
			vixTaskList = new ArrayList<VixTask>();
			for (VixTask vixTask : taskList) {
				if (empMap.containsKey(e.getUserId() + vixTask.getId())) {
					vixTaskList.add(empMap.get(e.getUserId() + vixTask.getId()));
				}
			}
			if (evaluationReviewList != null && evaluationReviewList.size() > 0) {
				evaluationReviewNum = evaluationReviewList.size();
			}
			// 倒序
			Collections.sort(vixTaskList, new Comparator<VixTask>() {
				@Override
				public int compare(VixTask o1, VixTask o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCloseedTaskList";
	}

	/**
	 * 删除图片
	 */
	public void deletePictureById() {
		try {
			if (StringUtils.isNotEmpty(wxpQyPictureId) && !"undefined".equals(wxpQyPictureId)) {
				for (String idStr : wxpQyPictureId.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						WxpQyPicture wxpQyPicture = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", idStr);
						if (null != wxpQyPicture) {
							wechatBaseService.deleteByEntity(wxpQyPicture);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUploaderById() {
		try {
			String vixTaskId = "";
			Uploader uploader = wechatBaseService.findEntityByAttributeNoTenantId(Uploader.class, "id", uploaderId);
			if (null != uploader) {
				vixTaskId = uploader.getTaskDefinition().getId();
				wechatBaseService.deleteByEntity(uploader);
			}
			renderText(vixTaskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开启回调,成员进入应用的事件推送
	 */
	public void getWechatMessage() {
		String sToken = "JDu5fI6bifO8lOXMqRq6jP9va";
		String sCorpID = "wx1a67071803f6077f";
		String sEncodingAESKey = "V4CU4qYwtYvV3OLVqYfIh3dp6xLYEFo6TwklrQm5abd";
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
			// 解析出url上的参数值如下：
			String sVerifyMsgSig = getRequest().getParameter("msg_signature");
			String sVerifyTimeStamp = getRequest().getParameter("timestamp");
			String sVerifyNonce = getRequest().getParameter("nonce");
			String sVerifyEchoStr = getRequest().getParameter("echostr");
			if (StringUtils.isNotEmpty(sVerifyEchoStr)) {
				// 需要返回的明文
				String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
				System.out.println("verifyurl echostr: " + sEchoStr);
				renderText(sEchoStr);
			}

			String postStrXml = wxcpt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, this.readStreamParameter(getRequest().getInputStream()));
			System.out.println("postStrXml:" + postStrXml);
			if (StringUtils.isNotEmpty(postStrXml)) {
				Document xmlData = DocumentHelper.parseText(postStrXml);
				Element root = xmlData.getRootElement();
				String qiyeCorpId = root.elementText("ToUserName");
				String userId = root.elementText("FromUserName");
				if (StringUtils.isNotEmpty(qiyeCorpId)) {
					System.out.println("企业号CorpID:" + qiyeCorpId);
				}
				if (StringUtils.isNotEmpty(userId)) {
					System.out.println("企业号UserID:" + userId);
				}
			}
		} catch (Exception e) {
			// 验证URL失败，错误原因请查看异常
			// e.printStackTrace();
		}
	}

	/**
	 * 改变任务状态
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	void updateTaskComplete() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<VixTask> vixTaskList = wechatBaseService.findAllDataByConditions(VixTask.class, params);
			if (vixTaskList != null) {
				for (VixTask vixTask : vixTaskList) {
					Date date = new Date();
					if (vixTask != null && vixTask.getTaskEndTime() != null) {
						if (vixTask.getTaskEndTime().getTime() < date.getTime()) {
							vixTask.setComplete(2);
							wechatBaseService.mergeOriginal(vixTask);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the flog
	 */
	public String getFlog() {
		return flog;
	}

	/**
	 * @param flog
	 *            the flog to set
	 */
	public void setFlog(String flog) {
		this.flog = flog;
	}

	/**
	 * @return the wxpQyPictureId
	 */
	public String getWxpQyPictureId() {
		return wxpQyPictureId;
	}

	/**
	 * @param wxpQyPictureId
	 *            the wxpQyPictureId to set
	 */
	public void setWxpQyPictureId(String wxpQyPictureId) {
		this.wxpQyPictureId = wxpQyPictureId;
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
	 * @return the vixTaskMemo
	 */
	public String getVixTaskMemo() {
		return vixTaskMemo;
	}

	/**
	 * @param vixTaskMemo
	 *            the vixTaskMemo to set
	 */
	public void setVixTaskMemo(String vixTaskMemo) {
		this.vixTaskMemo = vixTaskMemo;
	}

	/**
	 * @return the uploaderId
	 */
	public String getUploaderId() {
		return uploaderId;
	}

	/**
	 * @param uploaderId
	 *            the uploaderId to set
	 */
	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
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
	 * @return the vixTaskId
	 */
	public String getVixTaskId() {
		return vixTaskId;
	}

	/**
	 * @param vixTaskId
	 *            the vixTaskId to set
	 */
	public void setVixTaskId(String vixTaskId) {
		this.vixTaskId = vixTaskId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
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
	 * @return the taskTypeList
	 */
	public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}

	/**
	 * @param taskTypeList
	 *            the taskTypeList to set
	 */
	public void setTaskTypeList(List<TaskType> taskTypeList) {
		this.taskTypeList = taskTypeList;
	}

	/**
	 * @return the taskSchedule
	 */
	public Integer getTaskSchedule() {
		return taskSchedule;
	}

	/**
	 * @param taskSchedule
	 *            the taskSchedule to set
	 */
	public void setTaskSchedule(Integer taskSchedule) {
		this.taskSchedule = taskSchedule;
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
	 * @return the wxpQyPictureList
	 */
	public Set<WxpQyPicture> getWxpQyPictureList() {
		return wxpQyPictureList;
	}

	/**
	 * @param wxpQyPictureList
	 *            the wxpQyPictureList to set
	 */
	public void setWxpQyPictureList(Set<WxpQyPicture> wxpQyPictureList) {
		this.wxpQyPictureList = wxpQyPictureList;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the docNum
	 */
	public Integer getDocNum() {
		return docNum;
	}

	/**
	 * @param docNum
	 *            the docNum to set
	 */
	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
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
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * @param check
	 *            the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the employee
	 */
	@Override
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	/**
	 * @return the evaluationReviewList
	 */
	public List<EvaluationReview> getEvaluationReviewList() {
		return evaluationReviewList;
	}

	/**
	 * @param evaluationReviewList
	 *            the evaluationReviewList to set
	 */
	public void setEvaluationReviewList(List<EvaluationReview> evaluationReviewList) {
		this.evaluationReviewList = evaluationReviewList;
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getIsFeedback() {
		return isFeedback;
	}

	public void setIsFeedback(String isFeedback) {
		this.isFeedback = isFeedback;
	}

}
