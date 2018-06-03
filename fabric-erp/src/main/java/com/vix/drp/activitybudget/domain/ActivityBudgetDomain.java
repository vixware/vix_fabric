/**
 * 
 */
package com.vix.drp.activitybudget.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.activitybudget.entity.ActivityBudget;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("activityBudgetDomain")
@Transactional
public class ActivityBudgetDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findActivityBudgetPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ActivityBudget.class, params);
		return p;
	}

	public ActivityBudget findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ActivityBudget.class, id);
	}

	public ActivityBudget merge(ActivityBudget activityPlan) throws Exception {
		return baseHibernateService.merge(activityPlan);
	}

	public void deleteByEntity(ActivityBudget activityBudget) throws Exception {
		baseHibernateService.deleteByEntity(activityBudget);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ActivityBudget.class, ids);
	}

	/** 索引对象列表 */
	public List<ActivityBudget> findActivityBudgetList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ActivityBudget.class, params);
	}
}
