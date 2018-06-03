package com.vix.oa.task.taskDefinition.action;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.oa.task.taskDefinition.controller.TaskDefineController;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;

/**
 * 
 * @ClassName: FeedbackAction
 * @Description: 评论/评估 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-7 下午4:52:16
 */
@Controller
@Scope("prototype")
public class EvaluationReviewAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TaskDefineController.class);
	
	@Autowired
	private TaskDefineController taskDefineController;
	@Autowired
	private ITaskDefineService taskDefineService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	
	private String id;
	private String pageNo;
	private Date updateTime;
	public Integer complete;
	private ExecutionFeedback executionFeedback;
	
	/** 执行反馈 */
	private EvaluationReview evaluationReview;
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				evaluationReview = taskDefineController.doListEntityById2(id);
				this.saveBaseEntity(this.executionFeedback);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != evaluationReview.getId()) {
				isSave = false;
			}
			
			initEntityBaseController.initEntityBaseAttribute(evaluationReview);
			evaluationReview = taskDefineController.doSaveSalesOrder(evaluationReview);
			
			this.evaluationReview.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.evaluationReview.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			evaluationReview.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			evaluationReview.setCreateTime(new Date());
			this.saveBaseEntity(this.evaluationReview);
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
			this.executionFeedback = taskDefineService.findEntityById(ExecutionFeedback.class, id);
			logger.info("获取评论人员列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate1() {
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

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}


	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}


	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}


	public ITaskDefineService getTaskDefineService() {
		return taskDefineService;
	}


	public void setTaskDefineService(ITaskDefineService taskDefineService) {
		this.taskDefineService = taskDefineService;
	}


	public ExecutionFeedback getExecutionFeedback() {
		return executionFeedback;
	}


	public void setExecutionFeedback(ExecutionFeedback executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

}
