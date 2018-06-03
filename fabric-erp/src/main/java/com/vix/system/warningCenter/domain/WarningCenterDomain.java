/**
 * 
 */
package com.vix.system.warningCenter.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.AlertTask;
import com.vix.common.share.entity.Notice;
import com.vix.common.share.entity.TaskPlan;
import com.vix.core.web.Pager;
import com.vix.drp.refundRule.entity.RefundRuleDetail;
import com.vix.system.warningCenter.service.IWarningCenterService;
import com.vix.system.warningSource.entity.WarningType;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("warningCenterDomain")
@Transactional
public class WarningCenterDomain extends BaseDomain{

	@Autowired
	private IWarningCenterService warningCenterService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = warningCenterService.findPagerByHqlConditions(pager, AlertTask.class, params);
		return p;
	}

	public Pager findWarningTypePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = warningCenterService.findPagerByHqlConditions(pager, WarningType.class, params);
		return p;
	}

	public AlertTask findTimedTaskById(String id) throws Exception {
		return warningCenterService.findEntityById(AlertTask.class, id);
	}

	public WarningType findWarningTypeById(String id) throws Exception {
		return warningCenterService.findEntityById(WarningType.class, id);
	}

	public Notice findNoticeById(String id) throws Exception {
		return warningCenterService.findEntityById(Notice.class, id);
	}

	public TaskPlan findTaskPlanById(String id) throws Exception {
		return warningCenterService.findEntityById(TaskPlan.class, id);
	}

	public AlertTask saveOrUpdateTask(AlertTask timedTask) throws Exception {
		return warningCenterService.merge(timedTask);
	}

	public TaskPlan saveOrUpdateTaskPlan(TaskPlan taskPlan) throws Exception {
		return warningCenterService.merge(taskPlan);
	}

	public Notice saveOrUpdateNotice(Notice notice) throws Exception {
		return warningCenterService.merge(notice);
	}

	public RefundRuleDetail saveOrUpdateRefundRuleDetail(RefundRuleDetail refundRuleDetail) throws Exception {
		return warningCenterService.merge(refundRuleDetail);
	}

	public void deleteByEntity(AlertTask timedTask) throws Exception {
		warningCenterService.deleteByEntity(timedTask);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		warningCenterService.batchDelete(AlertTask.class, ids);
	}

	/** 索引对象列表 */
	public List<AlertTask> findTimedTaskIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return warningCenterService.findAllByConditions(AlertTask.class, params);
	}
}
