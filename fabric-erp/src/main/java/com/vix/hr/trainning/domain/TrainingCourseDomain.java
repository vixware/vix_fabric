package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.service.ITrainingCourseService;

@Transactional
@Component("trainingcoursedomain")
public class TrainingCourseDomain {
	@Autowired
	private ITrainingCourseService iTrainingCourseService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingCourseService.findPagerByHqlConditions(pager, TrainingCourse.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingCourseService.findPagerByOrHqlConditions(pager, TrainingCourse.class, params);
		return p;
	}

	public TrainingCourse findEntityById(String id) throws Exception {
		return iTrainingCourseService.findEntityById(TrainingCourse.class, id);
	}

	public TrainingCourse merge(TrainingCourse trainingCourse) throws Exception {
		return iTrainingCourseService.merge(trainingCourse);
	}

	public void deleteByEntity(TrainingCourse trainingCourse) throws Exception {
		iTrainingCourseService.deleteByEntity(trainingCourse);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingCourseService.batchDelete(TrainingCourse.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingCourse> findTrainingCourseIndex() throws Exception {
		return iTrainingCourseService.findAllByConditions(TrainingCourse.class, null);
	}

}
