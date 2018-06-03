package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.service.IDemandApplyService;

@Transactional
@Component("demandApplyDomain")
public class DemandApplyDomain {
	@Autowired
	private IDemandApplyService iDemandApplyService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, DemandApply.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByOrHqlConditions(pager, DemandApply.class, params);
		return p;
	}

	public DemandApply findEntityById(String id) throws Exception {
		return iDemandApplyService.findEntityById(DemandApply.class, id);
	}

	/** 获取培训讲师列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingCourse.class, params);
		return p;
	}

	/** 获取资源列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingFacilities.class, params);
		return p;
	}

	/** 获取设施列表数据 */
	public Pager findPagerByHqlConditions3(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingData.class, params);
		return p;
	}

	public DemandApply merge(DemandApply demandApply) throws Exception {
		return iDemandApplyService.merge(demandApply);
	}

	public void deleteByEntity(DemandApply demandApply) throws Exception {
		iDemandApplyService.deleteByEntity(demandApply);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandApplyService.batchDelete(DemandApply.class, ids);
	}

	/** 索引对象列表 */
	public List<DemandApply> findTrainingCMIndex() throws Exception {
		return iDemandApplyService.findAllByConditions(DemandApply.class, null);
	}

	/** 根据id获取培训讲师明细数据 */
	public TrainingCourse findTrainingLecturerById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingCourse.class, id);
	}

	/** 根据对象删除培训讲师 */
	public void deleteTrainingLecturerEntity(TrainingCourse trainingCourse) throws Exception {
		iDemandApplyService.deleteByEntity(trainingCourse);
	}

	/** 培训讲师明细 */
	public TrainingCourse merge(TrainingCourse trainingCourse) throws Exception {
		return iDemandApplyService.merge(trainingCourse);
	}

	/*************** 资料 ************************/
	/** 根据id获取培训资料明细数据 */
	public TrainingFacilities findTrainingChannelById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingFacilities.class, id);
	}

	/** 根据对象删除培训资料 */
	public void deleteTrainingChannelEntity(TrainingFacilities trainingFacilities) throws Exception {
		iDemandApplyService.deleteByEntity(trainingFacilities);
	}

	/** 培训设备明细 */
	public TrainingFacilities merge(TrainingFacilities trainingFacilities) throws Exception {
		return iDemandApplyService.merge(trainingFacilities);
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

	/** 培训设施明细 */
	public TrainingData merge(TrainingData trainingData) throws Exception {
		return iDemandApplyService.merge(trainingData);
	}
}
