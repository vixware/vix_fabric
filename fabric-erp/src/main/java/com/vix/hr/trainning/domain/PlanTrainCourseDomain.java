package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.Plan;
import com.vix.hr.trainning.entity.PlanTrainCourse;
import com.vix.hr.trainning.service.IDemandApplyService;

@Transactional
@Component("planTrainCourseDomain")
public class PlanTrainCourseDomain {
	@Autowired
	private IDemandApplyService iDemandApplyService;

	/** 索引对象列表 */
	public List<PlanTrainCourse> findPlanTrainCourseIndex() throws Exception {
		return iDemandApplyService.findAllByConditions(PlanTrainCourse.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, PlanTrainCourse.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByOrHqlConditions(pager, PlanTrainCourse.class, params);
		return p;
	}

	public PlanTrainCourse merge(PlanTrainCourse planTrainCourse) throws Exception {
		return iDemandApplyService.merge(planTrainCourse);
	}

	public void deleteByEntity(PlanTrainCourse planTrainCourse) throws Exception {
		iDemandApplyService.deleteByEntity(planTrainCourse);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandApplyService.batchDelete(PlanTrainCourse.class, ids);
	}

	public PlanTrainCourse findEntityById(String id) throws Exception {
		return iDemandApplyService.findEntityById(PlanTrainCourse.class, id);
	}

	/*******************************************
	 * 填报计划
	 ********************************************************************************/

	/** 根据id获取填报计划明细数据 */
	public Plan findPlanById(String id) throws Exception {
		return iDemandApplyService.findEntityById(Plan.class, id);
	}

	/** 填报计划明细 */
	public Plan merge(Plan plan) throws Exception {
		return iDemandApplyService.merge(plan);
	}

	/** 获取填报计划列表数据 */
	public Pager findPagerByPlan(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, Plan.class, params);
		return p;
	}

	/** 根据对象删除填报计划 */
	public void deletePlanEntity(Plan plan) throws Exception {
		iDemandApplyService.deleteByEntity(plan);
	}

}
