package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.IDemandApplyService;

@Transactional
@Component("trainingChannelDomain")
public class TrainingChannelDomain {
	@Autowired
	private IDemandApplyService iDemandApplyService;

	/** 索引对象列表 */
	public List<TrainingChannel> findTrainingChannelIndex() throws Exception {
		return iDemandApplyService.findAllByConditions(TrainingChannel.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingChannel.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByOrHqlConditions(pager, TrainingChannel.class, params);
		return p;
	}

	public TrainingChannel findEntityById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingChannel.class, id);
	}

	/** 获取讲师列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, TrainingLecturer.class, params);
		return p;
	}

	public TrainingChannel merge(TrainingChannel trainingChannel) throws Exception {
		return iDemandApplyService.merge(trainingChannel);
	}

	public void deleteByEntity(TrainingChannel trainingChannel) throws Exception {
		iDemandApplyService.deleteByEntity(trainingChannel);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandApplyService.batchDelete(TrainingChannel.class, ids);
	}

	/** 根据id获取培训讲师明细数据 */
	public TrainingLecturer findTrainingLecturerById(String id) throws Exception {
		return iDemandApplyService.findEntityById(TrainingLecturer.class, id);
	}

	/** 根据对象删除培训课程 */
	public void deleteTrainingLecturerEntity(TrainingLecturer trainingLecturer) throws Exception {
		iDemandApplyService.deleteByEntity(trainingLecturer);
	}

	/** 培训讲师明细 */
	public TrainingLecturer merge(TrainingLecturer trainingLecturer) throws Exception {
		return iDemandApplyService.merge(trainingLecturer);
	}
}
