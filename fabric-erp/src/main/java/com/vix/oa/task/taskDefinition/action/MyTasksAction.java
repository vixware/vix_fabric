package com.vix.oa.task.taskDefinition.action;
import java.io.PrintWriter;
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
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.controller.TaskDefineController;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;
import com.vix.oa.task.typeSettings.entity.CompletionMark;
import com.vix.oa.task.typeSettings.entity.DifficultyCoefficient;
import com.vix.oa.task.typeSettings.entity.TaskLevel;
import com.vix.oa.task.typeSettings.entity.TaskSourceType;
import com.vix.oa.task.typeSettings.entity.TaskType;

/**
 * 
 * @ClassName: MyTasksAction
 * @Description: 我的任务 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-2 下午2:37:35
 */
@Controller
@Scope("prototype")
public class MyTasksAction extends BaseAction {

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
	
	public Integer complete;
	
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
	
	/** 执行反馈 */
	private ExecutionFeedback executionFeedback;
	
	
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
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("complete," + SearchCondition.EQUAL, 1);*/
			//状态
			String complete = getRequestParameter("complete");
			if (null != complete && !"".equals(complete)) {
				params.put("complete," + SearchCondition.EQUAL, Integer.parseInt(complete));
			}			
			// 按最完成
			String endTime = getRequestParameter("endTime");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(endTime));
			}
			//pubids包含当前登录人id
			String employeeId = SecurityUtil.getCurrentEmpId();
			params.put("pubIds," + SearchCondition.ANYLIKE, ","+employeeId+",");
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("updateTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = taskDefineController.doSubSingleList(params,getPager());
			logger.info("获取我的任务列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取搜索任务定义列表数据 */	
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String questName = getRequestParameter("questName");
			if (null != questName && !"".equals(questName)) {
				questName = URLDecoder.decode(questName, "utf-8");
			}
			// 
			String executiveAgency = getRequestParameter("executiveAgency");
			if (null != executiveAgency && !"".equals(executiveAgency)) {
				executiveAgency = URLDecoder.decode(executiveAgency, "utf-8");
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
				if (null != executiveAgency && !"".equals(executiveAgency)) {
					params.put("executiveAgency," + SearchCondition.ANYLIKE, executiveAgency);
				}
				if (null != questName && !"".equals(questName)) {
					params.put("questName," + SearchCondition.ANYLIKE, questName);
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
			taskSourceTypeList = taskDefineService.findAllByEntityClass(TaskSourceType.class);
			taskTypeList = taskDefineService.findAllByEntityClass(TaskType.class);
			difficultyCoefficientList = taskDefineService.findAllByEntityClass(DifficultyCoefficient.class);
			taskLevelList = taskDefineService.findAllByEntityClass(TaskLevel.class);
			completionMarkList = taskDefineService.findAllByEntityClass(CompletionMark.class);
			if (null != taskDefinition.getId()) {
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
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate1(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = taskDefineController.doSubSingleList1(params,getPager());
			logger.info("获取执行反馈列表数据");
			setPager(pager);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskDefinition = taskDefineController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "";
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate1() {
		boolean isSave = true;
		try {
			if (null != taskDefinition.getId()) {
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
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate2(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				executionFeedback = taskDefineController.doExecutionFeedback(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate2() {
		boolean isSave = true;
		try {
			
			if (null != executionFeedback.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(executionFeedback);
			executionFeedback = taskDefineController.doSaveSalesOrder(executionFeedback);
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
	
	public void updateComplete(){
		PrintWriter out = null;
		try{
			
			VixTask pb = taskDefineController.findEntityById(id);
			if(pb.getComplete()==0){
				complete = 1;
				System.out.println(id+"="+complete+"===========0");
			}else if(pb.getComplete()==1){
				complete = 0;
				System.out.println(id+"="+complete+"===========1");
			}
			
			pb.setComplete(complete);
			taskDefinition = taskDefineController.doSaveSalesOrder(pb);
			getResponse().setCharacterEncoding("UTF-8");
			out = getResponse().getWriter();
			out.print(taskDefinition.getComplete());
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.close();
				out = null;
			}
		}
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

	public ExecutionFeedback getExecutionFeedback() {
		return executionFeedback;
	}

	public void setExecutionFeedback(ExecutionFeedback executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}


}
