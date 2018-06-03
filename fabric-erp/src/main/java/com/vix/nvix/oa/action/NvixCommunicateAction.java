package com.vix.nvix.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.WorkLogStatistics;
import com.vix.oa.personaloffice.entity.WorkLog;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: DailyJournalAction
 * @Description: 日报/工作日志
 * @author bobchen
 * @date 2016年1月4日 上午9:45:19
 *
 */
@Controller
@Scope("prototype")
public class NvixCommunicateAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private WorkLog workLog;
	private List<WorkLog> workLogList;
	private EvaluationReview evaluationReview;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;
	private List<EvaluationReview> evaluationReviewsList;
	private Date logDate;
	private String syncTag;
	private String id;
	private String ids;
	private String name;
	private String parentId;
	private String empId;
	private String treeType;
	private String taskIds;
	private List<Employee> employeeList;
	private String employeeIds;
	private String employeeNames;
	private String userIdStr;
	private List<Uploader> uploaderList;
	private Uploader uploader;
	// 页面跳转来源
	private String source;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String taskExecutionFeedbackData;

	public void goMyWorkLog() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (pageNo != null) {
				pager.setPageNo(pageNo);
			}
			/** 根据时间进行倒序排序 */
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("createTime");
				pager.setOrderBy("desc");
			}
			// 标题
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			params.put("status," + SearchCondition.EQUAL, "1");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Employee e = getEmployee();
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				pager = vixntBaseService.findPagerByHqlConditions(pager, WorkLog.class, params);
			}
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 查询工作日志数据 */
	public void goWorkLog() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccountCategory.id," + SearchCondition.EQUAL, parentId);
			}
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			/** 根据时间进行倒序排序 */
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("logDate");
				pager.setOrderBy("desc");
			}
			// 标题
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("title," + SearchCondition.ANYLIKE, searchCode);
			}
			// 创建人
			String searchCodeA = getDecodeRequestParameter("searchCodeA");
			if (StringUtils.isNotEmpty(searchCodeA)) {
				params.put("uploadPersonName," + SearchCondition.ANYLIKE, searchCodeA);
			}
			params.put("status," + SearchCondition.EQUAL, "1");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Employee e = getEmployee();
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, WorkLog.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOtherWorkLog() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", "1");
			params.put("isTemp", "1");
			// 标题
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title", "%" + title + "%");
			}
			// 创建人
			String creator = getDecodeRequestParameter("creator");
			if (StringUtils.isNotEmpty(creator)) {
				params.put("creator", "%" + creator + "%");
			}
			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findWorkLogPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取到工作日志内容，获取内容时将阅读次数加一，保存阅读次数
	 * 
	 * @author chenzhengwen
	 * @date 2016-4-28上午10:38:12
	 * @return
	 */
	public String goViewLog() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				workLog = vixntBaseService.findEntityById(WorkLog.class, id);
				if (workLog.getReadTimes() == null) {
					workLog.setReadTimes(1l);
				} else {
					workLog.setReadTimes(workLog.getReadTimes() + 1);
				}
				if (workLog.getSubEvaluationReviews() != null && workLog.getSubEvaluationReviews().size() > 0) {
					evaluationReviewNum = workLog.getSubEvaluationReviews().size();
					evaluationReviewsList = new ArrayList<EvaluationReview>();
					evaluationReviewsList.addAll(workLog.getSubEvaluationReviews());
				}
				workLog = vixntBaseService.merge(workLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewLog";
	}

	public String goTaskChoose() {
		return "goTaskChoose";
	}

	/**
	 * 评论
	 */
	public void saveOrUpdateLogComment() {
		try {
			if (workLog != null && workLog.getId() != null && !"".equals(workLog.getId())) {
				evaluationReview.setWorkLog(workLog);
				evaluationReview.setCreateTime(new Date());
				evaluationReview.setEvaluationTime(new Date());
				/** 拿到当前用户的姓名，保存 */
				this.evaluationReview.setUploadPersonId(SecurityUtil.getCurrentEmpId());
				Employee employee = getEmployee();
				if (employee != null) {
					evaluationReview.setEmployee(employee);
					evaluationReview.setUploadPersonName(employee.getName());
					evaluationReview.setUploadPerson(employee.getName());
				}
				evaluationReview = vixntBaseService.merge(evaluationReview);
				WxpQyWeixinSite site = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", evaluationReview.getTenantId());
				if (workLog != null && workLog.getEmployee() != null) {
					// 更新企业号token
					saveOrUpdateWxpWeixinSite(site);
					WxQyUtil.messageSend("3", workLog.getEmployee().getUserId(), employee.getName() + "评论了" + workLog.getTitle(), site.getQiyeAccessToken());
				}
				renderText(workLog.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理修改工作日志操作 */
	public String saveLogCommentary() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(workLog.getId())) {
				isSave = false;
			}
			this.saveBaseEntity(workLog);

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
		return UPDATE;
	}

	public String goWorkLogAttachment() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				workLog = vixntBaseService.findEntityById(WorkLog.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("workLog.id," + SearchCondition.EQUAL, id);
				uploaderList = vixntBaseService.findAllByConditions(Uploader.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWorkLogAttachment";
	}

	public void goTaskList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName", "%" + vixTaskName + "%");
			}
			String complete = getRequestParameter("complete");
			if (StringUtils.isNotEmpty(complete)) {
				params.put("complete", Integer.parseInt(complete));
			}
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());
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

	// 获取任务反馈
	public void getTaskInfo() {
		try {
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("status", "0");
				params.put("isTemp", "1");
				params.put("isDeleted", "0");
				params.put("taskStartTime", new Date());
				params.put("taskEndTime", new Date());
				params.put("employeeId", e.getId());
				params.put("taskIds", taskIds);
				List<VixTask> vixTaskList = vixntBaseService.findVixTaskListBySql(params);
				taskExecutionFeedbackData = "";
				for (VixTask vixTask : vixTaskList) {
					if (vixTask != null) {
						Set<ExecutionFeedback> executionFeedbacks = vixTask.getExecutionFeedbacks();
						if (executionFeedbacks != null && executionFeedbacks.size() > 0) {
							for (Iterator<ExecutionFeedback> iterator = executionFeedbacks.iterator(); iterator.hasNext();) {
								ExecutionFeedback executionFeedback = iterator.next();
								if (executionFeedback != null && executionFeedback.getExecutionFeedback() != null) {
									taskExecutionFeedbackData += vixTask.getQuestName() + ":" + "\n" + executionFeedback.getExecutionFeedback() + "\n";
								}
							}
						}
					}
				}
				if (StringUtils.isNotEmpty(taskExecutionFeedbackData)) {
					renderText(taskExecutionFeedbackData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转工作日志页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				workLog = vixntBaseService.findEntityById(WorkLog.class, id);
				if (workLog != null && workLog.getSubEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : workLog.getSubEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			} else {
				workLog = new WorkLog();
				workLog.setStartTime(df.parse(sdf.format(new Date()) + " 09:00:00"));
				workLog.setLogDate(new Date());
				workLog.setIsTemp("1");
				workLog.setTitle(sdf.format(new Date()) + "日报");
				workLog = vixntBaseService.merge(workLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改工作日志操作 */
	public String saveOrUpdate() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(workLog.getId())) {
				isSave = false;
			}
			/** 拿到当前用户的姓名，保存 */
			Employee employee = getEmployee();
			if (employee != null) {
				workLog.setUploadPersonName(employee.getName());
				workLog.setUploadPerson(employee.getName());
				workLog.setEmployee(employee);
				workLog.setUploadPersonId(employee.getId());
			}

			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee e : employeeList) {
						subEmployees.add(e);
						userIdStr += "|" + e.getUserId();
					}
				}
				workLog.setSubEmployees(subEmployees);
			}
			workLog.setStatus("1");
			workLog.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(workLog);
			workLog = vixntBaseService.merge(workLog);
			if (employee != null && StringUtils.isNotEmpty(userIdStr) && workLog != null) {
				String innerCode = SecurityUtil.getCurrentEmpOrgInnerCode();
				if (StringUtils.isNotEmpty(innerCode)) {
					WxpQyWeixinSite site = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "companyInnerCode", innerCode);
					if (site != null) {
						// 更新企业号token
						saveOrUpdateWxpWeixinSite(site);
						WxQyUtil.messageSendNews("4", userIdStr, employee.getName() + " 向您发送了日报,主题为" + workLog.getTitle(), site.getQiyeAccessToken(), workLog.getLogContent(), redirect_ip + "/wechat/jobLogAction!goShowDetails.action?id=" + workLog.getId(), "");
					}
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				WorkLog pb = vixntBaseService.findEntityById(WorkLog.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("workLog.id," + SearchCondition.EQUAL, id);
				uploaderList = vixntBaseService.findAllByConditions(Uploader.class, params);
				if (uploaderList != null && uploaderList.size() > 0) {
					for (Uploader uploader : uploaderList) {
						if (null != uploader) {
							String fileName = uploader.getFileName();
							String baseFolder = "c:/img/";
							String downloadFile = baseFolder + fileName;
							File f = new File(downloadFile); // 输入要删除的文件位置
							if (f.exists()) {
								f.delete();
							}
							vixntBaseService.deleteByEntity(uploader);
						}
					}
				}
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("workLog.id," + SearchCondition.EQUAL, id);
				evaluationReviewsList = vixntBaseService.findAllByConditions(EvaluationReview.class, p);
				if (evaluationReviewsList != null && evaluationReviewsList.size() > 0) {
					for (EvaluationReview evaluationReview : evaluationReviewsList) {
						vixntBaseService.deleteByEntity(evaluationReview);
					}
				}
				if (null != pb) {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 删除评论
	 */
	public void deleteEvaluationReviewById() {
		try {
			EvaluationReview evaluationReview = vixntBaseService.findEntityById(EvaluationReview.class, id);
			if (null != evaluationReview) {
				vixntBaseService.deleteByEntity(evaluationReview);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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
					workLog = vixntBaseService.findEntityById(WorkLog.class, id);
					if (workLog != null) {
						uploader.setWorkLog(workLog);
					}
				}
				uploader.setFileType(saveDocPathAndName[3].toString());
				uploader = vixntBaseService.merge(uploader);
			}
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
				params.put("workLog.id," + SearchCondition.EQUAL, id);
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

	// 添加附件
	public String goAddUploader() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				workLog = vixntBaseService.findEntityById(WorkLog.class, parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddUploader";
	}

	public void workLogEvents() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			Employee e = getEmployee();
			if (e != null) {
				params.put("empId," + SearchCondition.EQUAL, e.getId());
				List<WorkLogStatistics> workLogStatisticsList = vixntBaseService.findAllByConditions(WorkLogStatistics.class, params);
				JSONArray array = new JSONArray();
				if (workLogStatisticsList != null && workLogStatisticsList.size() > 0) {
					for (WorkLogStatistics workLogStatistics : workLogStatisticsList) {
						JSONObject object = new JSONObject();
						object.put("id", workLogStatistics.getId());
						if (workLogStatistics.getDatetemp() != null) {
							object.put("start", workLogStatistics.getDatetemp());
						}
						if (workLogStatistics.getDatetemp() != null) {
							object.put("end", workLogStatistics.getDatetemp());
						}
						object.put("allDay", "false");
						if ("1".equals(workLogStatistics.getIsCreate())) {
							object.put("title", "编写");
							object.put("className", "[\"event\",\"bg-color-greenLight\"]");
						} else {
							object.put("title", "未编写");
							object.put("className", "[\"event\",\"bg-color-orange\"]");
						}
						object.put("icon", "fa-info");
						array.add(object);
					}
				}
				json = array.toString();
			}
			if (StringUtils.isNotEmpty(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断一个给定时间是否在指定时间段内
	 * 
	 * @param date
	 * @param curTime
	 * @return
	 * @throws ParseException
	 */
	public boolean isInDateTime(Date date, Date curTime) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startDate = null;
		Date endDate = null;
		String cd = sdf1.format(date);
		startDate = sdf2.parse(cd + " 00:00:01");
		endDate = sdf2.parse(cd + " 23:59:59");
		long now = curTime.getTime();
		long start = startDate.getTime();
		long end = endDate.getTime();

		if (end < start) {
			if (now > end && now < start) {
				return false;
			} else {
				return true;
			}
		} else {
			if (now >= start && now <= end) {
				return true;
			} else {
				return false;
			}
		}
	}

	public WorkLog getWorkLog() {
		return workLog;
	}

	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	public List<WorkLog> getWorkLogList() {
		return workLogList;
	}

	public void setWorkLogList(List<WorkLog> workLogList) {
		this.workLogList = workLogList;
	}

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
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

	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

	public List<EvaluationReview> getEvaluationReviewsList() {
		return evaluationReviewsList;
	}

	public void setEvaluationReviewsList(List<EvaluationReview> evaluationReviewsList) {
		this.evaluationReviewsList = evaluationReviewsList;
	}

	public List<Uploader> getUploaderList() {
		return uploaderList;
	}

	public void setUploaderList(List<Uploader> uploaderList) {
		this.uploaderList = uploaderList;
	}

	public Uploader getUploader() {
		return uploader;
	}

	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getTaskExecutionFeedbackData() {
		return taskExecutionFeedbackData;
	}

	public void setTaskExecutionFeedbackData(String taskExecutionFeedbackData) {
		this.taskExecutionFeedbackData = taskExecutionFeedbackData;
	}

}
