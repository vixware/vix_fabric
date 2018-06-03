package com.vix.oa.task.taskPandect.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.controller.TaskDefineController;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;
import com.vix.oa.task.typeSettings.entity.CompletionMark;
import com.vix.oa.task.typeSettings.entity.DifficultyCoefficient;
import com.vix.oa.task.typeSettings.entity.TaskLevel;
import com.vix.oa.task.typeSettings.entity.TaskSourceType;
import com.vix.oa.task.typeSettings.entity.TaskType;

/**
 * 
 * @ClassName: TaskPandectAction
 * @Description: 任务总览 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-3 上午10:38:06
 */
@Controller
@Scope("prototype")
public class TaskPandectAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(TaskDefineController.class);
	
	@Autowired
	private ITaskDefineService taskDefineService;
	@Autowired
	private TaskDefineController taskDefineController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private VixTask taskDefinition;
	private String id;
	private String pageNo;
	private Date updateTime;
	
	/** 任务定义*/
	private List<VixTask> taskDefinitionList;
	
	/** 任务来源 */
	private List<TaskSourceType> taskSourceTypeList;
	
	/** 任务类型 */
	private List<TaskType> taskTypeList;
	
	/** 难度系数 */
	private List<DifficultyCoefficient> difficultyCoefficientList;
	
	/** 任务层级 */
	private List<TaskLevel> taskLevelList;
	
	/** 完成标志 */
	private List<CompletionMark> completionMarkList;
	
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			taskDefinitionList = taskDefineController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			//状态
			String isPublish = getRequestParameter("isPublish");
			if (null != isPublish && !"".equals(isPublish)) {
				params.put("isPublish," + SearchCondition.EQUAL, Integer.parseInt(isPublish));
			}			
			// 按最完成
			String endTime = getRequestParameter("endTime");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(endTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = taskDefineController.doSubSingleList(params,getPager());
			logger.info("获取任务总览列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取搜索任务定义列表数据 */	
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 标题
			String questName = getRequestParameter("questName");
			if (null != questName && !"".equals(questName)) {
				questName = URLDecoder.decode(questName, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 有效期
			String validity = getRequestParameter("validity");
			if (null != validity && !"".equals(validity)) {
				validity = URLDecoder.decode(validity, "utf-8");
			}
			// 任务权重
			String taskWeights = getRequestParameter("taskWeights");
			if (null != taskWeights && !"".equals(taskWeights)) {
				taskWeights = URLDecoder.decode(taskWeights, "utf-8");
			}
			// 执行人/部门
			String executiveAgency = getRequestParameter("executiveAgency");
			if (null != executiveAgency && !"".equals(executiveAgency)) {
				executiveAgency = URLDecoder.decode(executiveAgency, "utf-8");
			}
			// 考核人/部门
			String assessDepartment = getRequestParameter("assessDepartment");
			if (null != assessDepartment && !"".equals(assessDepartment)) {
				assessDepartment = URLDecoder.decode(assessDepartment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("questName," + SearchCondition.ANYLIKE, questName);
				pager = taskDefineController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != questName && !"".equals(questName)) {
					params.put("questName," + SearchCondition.ANYLIKE, questName);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != validity && !"".equals(validity)) {
					params.put("validity," + SearchCondition.ANYLIKE, validity);
				}
				if (null != taskWeights && !"".equals(taskWeights)) {
					params.put("taskWeights," + SearchCondition.ANYLIKE, taskWeights);
				}
				if (null != executiveAgency && !"".equals(executiveAgency)) {
					params.put("executiveAgency," + SearchCondition.ANYLIKE, executiveAgency);
				}
				if (null != assessDepartment && !"".equals(assessDepartment)) {
					params.put("assessDepartment," + SearchCondition.ANYLIKE, assessDepartment);
				}
				pager = taskDefineController.goSingleList(params, getPager());
			}
			logger.info("获取任务搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			taskSourceTypeList = taskDefineService.findAllByEntityClass(TaskSourceType.class);
			taskTypeList = taskDefineService.findAllByEntityClass(TaskType.class);
			difficultyCoefficientList = taskDefineService.findAllByEntityClass(DifficultyCoefficient.class);
			taskLevelList = taskDefineService.findAllByEntityClass(TaskLevel.class);
			completionMarkList = taskDefineService.findAllByEntityClass(CompletionMark.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskDefinition = taskDefineController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(taskDefinition.getId()) && !"".equals(taskDefinition.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(taskDefinition);
			taskDefinition = taskDefineController.doSaveSalesOrder(taskDefinition);
			logger.info("新增！");
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
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			VixTask pb = taskDefineController.findEntityById(id);
			if (null != pb) {
				taskDefineController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}


	public List<TaskSourceType> getTaskSourceTypeList() {
		return taskSourceTypeList;
	}


	public void setTaskSourceTypeList(List<TaskSourceType> taskSourceTypeList) {
		this.taskSourceTypeList = taskSourceTypeList;
	}


	public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}


	public void setTaskTypeList(List<TaskType> taskTypeList) {
		this.taskTypeList = taskTypeList;
	}


	public List<DifficultyCoefficient> getDifficultyCoefficientList() {
		return difficultyCoefficientList;
	}


	public void setDifficultyCoefficientList(
			List<DifficultyCoefficient> difficultyCoefficientList) {
		this.difficultyCoefficientList = difficultyCoefficientList;
	}


	public List<TaskLevel> getTaskLevelList() {
		return taskLevelList;
	}


	public void setTaskLevelList(List<TaskLevel> taskLevelList) {
		this.taskLevelList = taskLevelList;
	}


	public List<CompletionMark> getCompletionMarkList() {
		return completionMarkList;
	}


	public void setCompletionMarkList(List<CompletionMark> completionMarkList) {
		this.completionMarkList = completionMarkList;
	}


	public VixTask getTaskDefinition() {
		return taskDefinition;
	}


	public void setTaskDefinition(VixTask taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<VixTask> getTaskDefinitionList() {
		return taskDefinitionList;
	}

	public void setTaskDefinitionList(List<VixTask> taskDefinitionList) {
		this.taskDefinitionList = taskDefinitionList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
