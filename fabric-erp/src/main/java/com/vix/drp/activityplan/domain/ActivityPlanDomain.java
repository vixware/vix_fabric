/**
 * 
 */
package com.vix.drp.activityplan.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.activityplan.entity.ActivityPlan;
import com.vix.drp.activityplan.entity.ActivityPlanDetail;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("activityPlanDomain")
@Transactional
public class ActivityPlanDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ActivityPlan.class, params);
		return p;
	}

	public ActivityPlan findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ActivityPlan.class, id);
	}

	public ActivityPlan merge(ActivityPlan activityPlan) throws Exception {
		return baseHibernateService.merge(activityPlan);
	}

	public ActivityPlanDetail saveOrUpdateActivityPlanDetail(ActivityPlanDetail activityPlanDetail) throws Exception {
		return baseHibernateService.merge(activityPlanDetail);
	}

	public void deleteByEntity(ActivityPlan activityPlan) throws Exception {
		baseHibernateService.deleteByEntity(activityPlan);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ActivityPlan.class, ids);
	}

	/** 索引对象列表 */
	public List<ActivityPlan> findActivityPlanList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ActivityPlan.class, params);
	}
}
