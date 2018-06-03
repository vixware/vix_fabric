package com.vix.oa.task.taskDefinition.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;

/**
 * 
 * @ClassName: TaskDefineDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-25 下午3:41:22
 */
@Component("taskDefineDomain")
@Transactional
public class TaskDefineDomain extends BaseDomain{

	@Autowired
	private ITaskDefineService taskDefineservice;
	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = taskDefineservice.findPagerByHqlConditions(pager,VixTask.class, params);
		return p;
	}
	/** 获取附件列表数据 */
	public Pager findPagerByHqlUploader(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = taskDefineservice.findPagerByHqlConditions(pager,Uploader.class, params);
		return p;
	}
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = taskDefineservice.findPagerByHqlConditions(pager,ExecutionFeedback.class, params);
		return p;
	}
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = taskDefineservice.findPagerByHqlConditions(pager,EvaluationReview.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = taskDefineservice.findPagerByOrHqlConditions(pager, VixTask.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<VixTask> findSalesOrderIndex() throws Exception {
		return taskDefineservice.findAllByConditions(VixTask.class, null);
	}
	
	public VixTask findEntityById(String id) throws Exception {
		return taskDefineservice.findEntityById(VixTask.class, id);
	}
	
	public Uploader findUploader(String id) throws Exception {
		return taskDefineservice.findEntityById(Uploader.class, id);
	}
	
	public ExecutionFeedback findExecutionFeedback(String id) throws Exception {
		return taskDefineservice.findEntityById(ExecutionFeedback.class, id);
	}
	
	public EvaluationReview findEntityById2(String id) throws Exception {
		return taskDefineservice.findEntityById(EvaluationReview.class, id);
	}
	
	public void deleteByEntity(VixTask taskDefinition) throws Exception {
		taskDefineservice.deleteByEntity(taskDefinition);
	}
	
	public VixTask merge(VixTask taskDefinition) throws Exception {
		return taskDefineservice.merge(taskDefinition);
	}
	
	public Uploader merge(Uploader uploader) throws Exception {
		return taskDefineservice.merge(uploader);
	}
	
	public EvaluationReview merge(EvaluationReview evaluationReview) throws Exception {
		return taskDefineservice.merge(evaluationReview);
	}
	
	public ExecutionFeedback merge(ExecutionFeedback executionFeedback) throws Exception {
		return taskDefineservice.merge(executionFeedback);
	}

}
