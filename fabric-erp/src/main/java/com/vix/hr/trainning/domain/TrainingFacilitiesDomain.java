package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.service.ITrainingFacilitiesService;

@Transactional
@Component("trainingfacilitiesdomain")
public class TrainingFacilitiesDomain {
	@Autowired
	private ITrainingFacilitiesService iTrainingFacilitiesService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingFacilitiesService.findPagerByHqlConditions(pager, TrainingFacilities.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingFacilitiesService.findPagerByOrHqlConditions(pager, TrainingFacilities.class, params);
		return p;
	}

	public TrainingFacilities findEntityById(String id) throws Exception {
		return iTrainingFacilitiesService.findEntityById(TrainingFacilities.class, id);
	}

	public TrainingFacilities merge(TrainingFacilities trainingFacilities) throws Exception {
		return iTrainingFacilitiesService.merge(trainingFacilities);
	}

	public void deleteByEntity(TrainingFacilities trainingFacilities) throws Exception {
		iTrainingFacilitiesService.deleteByEntity(trainingFacilities);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingFacilitiesService.batchDelete(TrainingFacilities.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingFacilities> findTrainingFacilitiesIndex() throws Exception {
		return iTrainingFacilitiesService.findAllByConditions(TrainingFacilities.class, null);
	}

}
