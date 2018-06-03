package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.Plan;
import com.vix.hr.trainning.service.TrainingPlanService;

@Transactional
@Component("planDomain")
public class PlanDomain {
	@Autowired
	private TrainingPlanService trainingPlanService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingPlanService.findPagerByHqlConditions(pager, Plan.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingPlanService.findPagerByOrHqlConditions(pager, Plan.class, params);
		return p;
	}

	public Plan findEntityById(String id) throws Exception {
		return trainingPlanService.findEntityById(Plan.class, id);
	}

	public Plan merge(Plan plan) throws Exception {
		return trainingPlanService.merge(plan);
	}

	public void deleteByEntity(Plan plan) throws Exception {
		trainingPlanService.deleteByEntity(plan);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingPlanService.batchDelete(Plan.class, ids);
	}

	/** 索引对象列表 */
	public List<Plan> findPlanIndex() throws Exception {
		return trainingPlanService.findAllByConditions(Plan.class, null);
	}

}
