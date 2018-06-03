package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.entity.TrainingResources;
import com.vix.hr.trainning.service.ITrainingResourcesService;

@Transactional
@Component("trainingresourcesdomain")
public class TrainingResourcesDomain {
	@Autowired
	private ITrainingResourcesService iTrainingResourcesService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingResourcesService.findPagerByHqlConditions(pager, TrainingResources.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingResourcesService.findPagerByOrHqlConditions(pager, TrainingResources.class, params);
		return p;
	}

	public TrainingResources findEntityById(String id) throws Exception {
		return iTrainingResourcesService.findEntityById(TrainingResources.class, id);
	}

	public TrainingResources merge(TrainingResources trainingResources) throws Exception {
		return iTrainingResourcesService.merge(trainingResources);
	}

	/** 根据id获取培训课程明细数据 */
	public TrainingCourse findTrainingCourseById(String id) throws Exception {
		return iTrainingResourcesService.findEntityById(TrainingCourse.class, id);
	}

	/** 培训课程明细 */
	public TrainingCourse merge(TrainingCourse trainingCourse) throws Exception {
		return iTrainingResourcesService.merge(trainingCourse);
	}

	/** 根据对象删除培训明细 */
	public void deleteTrainingCourseEntity(TrainingCourse trainingCourse) throws Exception {
		iTrainingResourcesService.deleteByEntity(trainingCourse);
	}

	/** 根据id获取培训资料明细数据 */
	public TrainingData findTrainingDataById(String id) throws Exception {
		return iTrainingResourcesService.findEntityById(TrainingData.class, id);
	}

	/** 培训资料明细 */
	public TrainingData merge(TrainingData trainingData) throws Exception {
		return iTrainingResourcesService.merge(trainingData);
	}

	/** 根据对象删除培训资料 */
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		iTrainingResourcesService.deleteByEntity(trainingData);
	}

	/** 根据id获取培训设施明细数据 */
	public TrainingFacilities findTrainingFacilitiesById(String id) throws Exception {
		return iTrainingResourcesService.findEntityById(TrainingFacilities.class, id);
	}

	/** 培训设施明细 */
	public TrainingFacilities merge(TrainingFacilities trainingFacilities) throws Exception {
		return iTrainingResourcesService.merge(trainingFacilities);
	}

	/** 根据对象删除培训设施 */
	public void deleteTrainingFacilitiesEntity(TrainingFacilities trainingFacilities) throws Exception {
		iTrainingResourcesService.deleteByEntity(trainingFacilities);
	}

	/** 根据id获取培训教师明细数据 */
	public TrainingLecturer findTrainingLecturerById(String id) throws Exception {
		return iTrainingResourcesService.findEntityById(TrainingLecturer.class, id);
	}

	/** 培训教师明细 */
	public TrainingLecturer merge(TrainingLecturer trainingLecturer) throws Exception {
		return iTrainingResourcesService.merge(trainingLecturer);
	}

	/** 根据对象删除培训教师 */
	public void deleteTrainingLecturerEntity(TrainingLecturer trainingLecturer) throws Exception {
		iTrainingResourcesService.deleteByEntity(trainingLecturer);
	}

	public void deleteByEntity(TrainingResources trainingResources) throws Exception {
		iTrainingResourcesService.deleteByEntity(trainingResources);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingResourcesService.batchDelete(TrainingResources.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingResources> findTrainingResourcesIndex() throws Exception {
		return iTrainingResourcesService.findAllByConditions(TrainingResources.class, null);
	}

}
