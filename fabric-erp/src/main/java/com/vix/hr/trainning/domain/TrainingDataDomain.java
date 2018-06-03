package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.service.ITrainingDataService;

@Transactional
@Component("trainingdatadomain")
public class TrainingDataDomain {
	@Autowired
	private ITrainingDataService iTrainingDataService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingDataService.findPagerByHqlConditions(pager, TrainingData.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingDataService.findPagerByOrHqlConditions(pager, TrainingData.class, params);
		return p;
	}

	public TrainingData findEntityById(String id) throws Exception {
		return iTrainingDataService.findEntityById(TrainingData.class, id);
	}

	public TrainingData merge(TrainingData trainingData) throws Exception {
		return iTrainingDataService.merge(trainingData);
	}

	public void deleteByEntity(TrainingData trainingData) throws Exception {
		iTrainingDataService.deleteByEntity(trainingData);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingDataService.batchDelete(TrainingData.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingData> findTrainingDataIndex() throws Exception {
		return iTrainingDataService.findAllByConditions(TrainingData.class, null);
	}

}
