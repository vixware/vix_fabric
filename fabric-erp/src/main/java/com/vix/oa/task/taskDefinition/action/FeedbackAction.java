
package com.vix.oa.task.taskDefinition.action;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.controller.TaskDefineController;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;
import com.vix.oa.task.typeSettings.entity.ProgressSituation;

/**
 * 
 * @ClassName: FeedbackAction
 * @Description: 执行反馈
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-5 上午10:38:54
 */
@Controller
@Scope("prototype")
public class FeedbackAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TaskDefineController.class);
	
	@Autowired
	private TaskDefineController taskDefineController;
	@Autowired
	private ITaskDefineService taskDefineService;
	
	private Uploader uploader;
	private String id;	
	private String pageNo;
	private Date updateTime;
	private String taskId;
	public Integer complete;
	private String eqId;
	
	/** 执行反馈 */
	private ExecutionFeedback executionFeedback;
	
	/** 进度情况 */
	private List<ProgressSituation> progressSituationList;
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			progressSituationList = taskDefineService.findAllByEntityClass(ProgressSituation.class);
			logger.info("获取列表数据");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				executionFeedback = taskDefineController.doExecutionFeedback(id);
				logger.info("");
			}else {
				VixTask taskDefinition=taskDefineService.findEntityById(VixTask.class, taskId);
				executionFeedback=new ExecutionFeedback();
				executionFeedback.setTaskDefinition(taskDefinition);
				
				executionFeedback.setIsTemp("1");
				executionFeedback=taskDefineService.merge(executionFeedback);
			}
			Map<String, Object> params = getParams();
			params.put("executionFeedback.id," + SearchCondition.EQUAL, id);
			Pager pager = taskDefineController.doUploader(params,getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			progressSituationList = taskDefineService.findAllByEntityClass(ProgressSituation.class);
			if (null != executionFeedback.getId()) {
				isSave = false;
			}
			VixTask taskDefinition=taskDefineService.findEntityById(VixTask.class, executionFeedback.getTaskDefinition().getId());
			taskDefinition.setSchedule(executionFeedback.getName());
			taskDefineService.merge(taskDefinition);
			
			executionFeedback = taskDefineController.doSaveSalesOrder(executionFeedback);
			this.executionFeedback.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.executionFeedback.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			executionFeedback.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			executionFeedback.setIsTemp("");
			this.saveBaseEntity(this.executionFeedback);
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
	
	
	public String eqSbwdPager()
	{
		//设备文档
		if(StringUtils.isNotEmpty(eqId) && !eqId.equals("0")){
			Map<String,Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("uploader.id,"+SearchCondition.EQUAL, this.eqId);
			
			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try{
				this.taskDefineService.findPagerByHqlConditions(pager, Uploader.class, params);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		return "eqSbwdPager";
	}
	
	
	public String eqSbwdEdit(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				uploader = taskDefineController.doUploader(id);
				logger.info("");
			}else {
				uploader=new Uploader();
				//将任务set到Uploader里边
				String executionFeedbackId= getRequestParameter("executionFeedbackId");
				executionFeedback=taskDefineService.findEntityById(ExecutionFeedback.class, executionFeedbackId);
				uploader.setExecutionFeedback(executionFeedback);
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "eqSbwdEdit";
	}
	
	public void saveEqSbwd()
	{
		String[] savePathAndName = this.saveUploadFile();
		if(savePathAndName!=null && savePathAndName.length==2)
		{
			this.uploader.setFileName(savePathAndName[1]);
			this.uploader.setFilePath(savePathAndName[0]);
		}

		this.uploader.setUploadPersonId(SecurityUtil.getCurrentUserId());
		this.uploader.setUploadPerson(SecurityUtil.getCurrentUserName());
		/**拿到当前用户的姓名，保存*/
		uploader.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
		this.uploader.setUploadTime(new Date());
		this.saveBaseEntity(this.uploader);
		try {
			uploader=this.taskDefineService.merge(uploader);
			BaseSecAction.renderText(String.valueOf(uploader.getExecutionFeedback().getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String downloadEqDocument()
	{
		if(StringUtils.isNotEmpty(id) && !id.equals("0")){
			try{
				Uploader doc = this.getTaskDefineService().findEntityById(Uploader.class, this.id);
				String fileName = doc.getFileName();
				String filePath = doc.getFilePath();
				String title = doc.getTitle();
				int idx = fileName.lastIndexOf(".");
				if(idx!=-1)
				{
					title = title + fileName.substring(idx);
				}
				
				this.setOriFileName(title);

				String downloadFile = filePath + fileName;
		        this.setInputStream(new FileInputStream(downloadFile));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			return NONE;
		}
		return "downloadEqDocument";
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


	public List<ProgressSituation> getProgressSituationList() {
		return progressSituationList;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public void setProgressSituationList(
			List<ProgressSituation> progressSituationList) {
		this.progressSituationList = progressSituationList;
	}


	public ITaskDefineService getTaskDefineService() {
		return taskDefineService;
	}


	public void setTaskDefineService(ITaskDefineService taskDefineService) {
		this.taskDefineService = taskDefineService;
	}


	public Uploader getUploader() {
		return uploader;
	}


	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}


	public String getEqId() {
		return eqId;
	}


	public void setEqId(String eqId) {
		this.eqId = eqId;
	}


}
