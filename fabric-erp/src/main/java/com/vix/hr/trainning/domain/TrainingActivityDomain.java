package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingActivity;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.hr.trainning.service.IDemandApplyService;

@Transactional
@Component("trainingActivityDomain")
public class TrainingActivityDomain {
	@Autowired
	private IDemandApplyService iDemandApplyService;

	/** 索引对象列表 */
	public List<TrainingActivity> findTrainingActivityIndex() throws Exception {
		return iDemandApplyService.findAllByConditions(TrainingActivity.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingActivity.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByOrHqlConditions(pager, TrainingActivity.class, params);
		return p;
	}

	public TrainingActivity merge(TrainingActivity trainingActivity) throws Exception {
		return iDemandApplyService.merge(trainingActivity);
	}

	public void deleteByEntity(TrainingActivity trainingActivity) throws Exception {
		iDemandApplyService.deleteByEntity(trainingActivity);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandApplyService.batchDelete(TrainingActivity.class, ids);
	}

	public TrainingActivity findEntityById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingActivity.class, id);
	}

	/*******************************************
	 * 培训计划明细
	 ********************************************************************************/

	/** 根据id获取培训计划明细明细数据 */
	public TrainingPlanningDetail findTrainingPlanningDetailById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingPlanningDetail.class, id);
	}

	/** 培训计划明细明细 */
	public TrainingPlanningDetail merge(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		return iDemandApplyService.merge(trainingPlanningDetail);
	}

	/** 获取培训计划明细列表数据 */
	public Pager findPagerByTrainingPlanningDetail(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingPlanningDetail.class, params);
		return p;
	}

	/** 根据对象删除培训计划明细 */
	public void deleteTrainingPlanningDetailEntity(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		iDemandApplyService.deleteByEntity(trainingPlanningDetail);
	}

}
