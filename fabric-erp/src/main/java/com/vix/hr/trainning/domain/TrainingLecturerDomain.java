package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.ITrainingLecturerService;

@Transactional
@Component("trainingLecturerDomain")
public class TrainingLecturerDomain {
	@Autowired
	private ITrainingLecturerService iTrainingLecturerService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingLecturerService.findPagerByHqlConditions(pager, TrainingLecturer.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingLecturerService.findPagerByOrHqlConditions(pager, TrainingLecturer.class, params);
		return p;
	}

	public TrainingLecturer findEntityById(String id) throws Exception {
		return iTrainingLecturerService.findEntityById(TrainingLecturer.class, id);
	}

	public TrainingLecturer merge(TrainingLecturer trainingLecturer) throws Exception {
		return iTrainingLecturerService.merge(trainingLecturer);
	}

	public void deleteByEntity(TrainingLecturer trainingLecturer) throws Exception {
		iTrainingLecturerService.deleteByEntity(trainingLecturer);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingLecturerService.batchDelete(TrainingLecturer.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingLecturer> findTrainingLecturerIndex() throws Exception {
		return iTrainingLecturerService.findAllByConditions(TrainingLecturer.class, null);
	}

}
