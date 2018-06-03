
package com.vix.oa.task.taskMonitor.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @ClassName: TaskMonitorAction
 * @Description: 任务监控 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-3 上午11:00:13
 */
@Controller
@Scope("prototype")
public class TaskMonitorAction extends BaseAction {

	private static final long serialVersionUID = 1L;
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
			String code = getRequestParameter("code");
			String updateTime = getRequestParameter("updateTime");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}			
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = taskDefineController.doSubSingleList(params,getPager());
			logger.info("获取任务定义列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 计算最近使用日期 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
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
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			List<VixTask> listCategory = new ArrayList<VixTask>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				listCategory = taskDefineService.findAllSubEntity(VixTask.class, "parentCategory.id", id, params);
			}else{
				listCategory = taskDefineService.findAllSubEntity(VixTask.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listCategory.size();i++){
				VixTask cc = listCategory.get(i);
				if(cc.getSubVixTasks().size() > 0){
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getQuestName());
					strSb.append("\",open:false,isParent:true}");
				}else{
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getQuestName());
					strSb.append("\",open:false,isParent:false}");
				}
				if(i < listCategory.size() -1){
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
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

}
