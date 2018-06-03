package com.vix.hr.job.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.service.IOrgChartService;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.entity.TrainingLecturer;
@Transactional
@Component("orgchartdomain")
public class OrgChartDomain {
	@Autowired
	private IOrgChartService iOrgChartService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iOrgChartService.findPagerByHqlConditions(pager, DemandApply.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iOrgChartService.findPagerByOrHqlConditions(pager, DemandApply.class, params);
		return p;
	}

	public DemandApply findEntityById(String id) throws Exception {
		return iOrgChartService.findEntityById(DemandApply.class, id);
	}

	/** 获取教师列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iOrgChartService.findPagerByHqlConditions(pager, TrainingLecturer.class, params);
		return p;
	}

	/** 获取课程列表数据 */
	public Pager findPagerByHqlConditions3(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iOrgChartService.findPagerByHqlConditions(pager, TrainingCourse.class, params);
		return p;
	}

	/** 获取资源列表数据 */
	public Pager findPagerByHqlConditions4(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iOrgChartService.findPagerByHqlConditions(pager, TrainingData.class, params);
		return p;
	}

	/** 获取设施列表数据 */
	public Pager findPagerByHqlConditions5(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iOrgChartService.findPagerByHqlConditions(pager, TrainingFacilities.class, params);
		return p;
	}

	public DemandApply merge(DemandApply demandApply) throws Exception {
		return iOrgChartService.merge(demandApply);
	}

	public void deleteByEntity(DemandApply demandApply) throws Exception {
		iOrgChartService.deleteByEntity(demandApply);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iOrgChartService.batchDelete(DemandApply.class, ids);
	}

	/** 索引对象列表 */
	public List<DemandApply> findDemandApplyIndex() throws Exception {
		return iOrgChartService.findAllByConditions(DemandApply.class, null);
	}

	/** 根据id获取培训课程明细数据 */
	public TrainingCourse findTrainingCourseById(String id) throws Exception {
		return iOrgChartService.findEntityById(TrainingCourse.class, id);
	}

	/** 根据对象删除培训课程 */
	public void deleteTrainingCourseEntity(TrainingCourse trainingCourse) throws Exception {
		iOrgChartService.deleteByEntity(trainingCourse);
	}

	/** 培训课程明细 */
	public TrainingCourse merge(TrainingCourse trainingCourse) throws Exception {
		return iOrgChartService.merge(trainingCourse);
	}

	/*************** 资料 ************************/
	/** 根据id获取培训资料明细数据 */
	public TrainingData findTrainingDataById(String id) throws Exception {
		return iOrgChartService.findEntityById(TrainingData.class, id);
	}

	/** 根据对象删除培训资料 */
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		iOrgChartService.deleteByEntity(trainingData);
	}

	/** 培训资料明细 */
	public TrainingData merge(TrainingData trainingData) throws Exception {
		return iOrgChartService.merge(trainingData);
	}

	/*************** 设施 ************************/
	/** 根据id获取培训设施明细数据 */
	public TrainingFacilities findTrainingFacilitiesById(String id) throws Exception {
		return iOrgChartService.findEntityById(TrainingFacilities.class, id);
	}

	/** 根据对象删除培训设施 */
	public void deleteTrainingFacilitiesEntity(TrainingFacilities trainingFacilities) throws Exception {
		iOrgChartService.deleteByEntity(trainingFacilities);
	}

	/** 培训设施明细 */
	public TrainingFacilities merge(TrainingFacilities trainingFacilities) throws Exception {
		return iOrgChartService.merge(trainingFacilities);
	}
}
