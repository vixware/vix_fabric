package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingCost;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.IDemandApplyService;

@Transactional
@Component("trainingCostDomain")
public class TrainingCostDomain {
	@Autowired
	private IDemandApplyService iDemandApplyService;

	/** 索引对象列表 */
	public List<TrainingCost> findTrainingCostIndex() throws Exception {
		return iDemandApplyService.findAllByConditions(TrainingCost.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingCost.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByOrHqlConditions(pager, TrainingCost.class, params);
		return p;
	}

	public TrainingCost findEntityById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingCost.class, id);
	}

	public TrainingCost merge(TrainingCost trainingCost) throws Exception {
		return iDemandApplyService.merge(trainingCost);
	}

	public void deleteByEntity(TrainingCost trainingCost) throws Exception {
		iDemandApplyService.deleteByEntity(trainingCost);
	}

	/******************************************************************
	 * 培训讲师
	 ********************************************************************************/
	/** 获取培训讲师列表数据 */
	public Pager findPagerByHqlConditionss(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingLecturer.class, params);
		return p;
	}

	/** 获取资源列表数据 */
	public Pager findPagerByHqlConditionsss(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingChannel.class, params);
		return p;
	}

	/** 获取设施列表数据 */
	public Pager findPagerByHqlConditionssss(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingData.class, params);
		return p;
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandApplyService.batchDelete(TrainingCost.class, ids);
	}

	/** 根据id获取培训讲师明细数据 */
	public TrainingLecturer findTrainingLecturerById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingLecturer.class, id);
	}

	/** 根据对象删除培训讲师 */
	public void deleteTrainingLecturerEntity(TrainingLecturer trainingLecturer) throws Exception {
		iDemandApplyService.deleteByEntity(trainingLecturer);
	}

	/** 培训讲师明细 */
	public TrainingLecturer merge(TrainingLecturer trainingLecturer) throws Exception {
		return iDemandApplyService.merge(trainingLecturer);
	}

	/*************** 资料 ************************/
	/** 根据id获取培训资料明细数据 */
	public TrainingChannel findTrainingChannelById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingChannel.class, id);
	}

	/** 根据对象删除培训资料 */
	public void deleteTrainingChannelEntity(TrainingChannel trainingChannel) throws Exception {
		iDemandApplyService.deleteByEntity(trainingChannel);
	}

	/** 培训资料明细 */
	public TrainingChannel merge(TrainingChannel trainingChannel) throws Exception {
		return iDemandApplyService.merge(trainingChannel);
	}

	/*************** 设施 ************************/
	/** 根据id获取培训设施明细数据 */
	public TrainingData findTrainingDataById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingData.class, id);
	}

	/** 根据对象删除培训设施 */
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		iDemandApplyService.deleteByEntity(trainingData);
	}

	/** 培训资料 */
	public TrainingData merge(TrainingData trainingData) throws Exception {
		return iDemandApplyService.merge(trainingData);
	}
}
