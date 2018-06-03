package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainningImplement;
import com.vix.hr.trainning.service.ITrainningImplementService;

@Transactional
@Component("trainningimplementdomain")
public class TrainningImplementDomain {
	@Autowired
	private ITrainningImplementService iTrainningImplementService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainningImplementService.findPagerByHqlConditions(pager, TrainningImplement.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainningImplementService.findPagerByOrHqlConditions(pager, TrainningImplement.class, params);
		return p;
	}

	public TrainningImplement findEntityById(String id) throws Exception {
		return iTrainningImplementService.findEntityById(TrainningImplement.class, id);
	}

	/** 根据id获取培训活动明细数据 */
	public TrainingPlanning findTrainningImplementDetailById(String id) throws Exception {
		return iTrainningImplementService.findEntityById(TrainingPlanning.class, id);
	}

	public Pager findTrainingPlanning(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainningImplementService.findPagerByHqlConditions(pager, TrainingPlanning.class, params);
		return p;
	}

	public TrainningImplement merge(TrainningImplement trainningImplement) throws Exception {
		return iTrainningImplementService.merge(trainningImplement);
	}

	// 培训活动明细
	public TrainingPlanning merge(TrainingPlanning trainingPlanning) throws Exception {
		return iTrainningImplementService.merge(trainingPlanning);
	}

	public void deleteByEntity(TrainningImplement trainningImplement) throws Exception {
		iTrainningImplementService.deleteByEntity(trainningImplement);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainningImplementService.batchDelete(TrainningImplement.class, ids);
	}

	public TrainingPlanning findTrainingPlanningById(String id) throws Exception {
		return iTrainningImplementService.findEntityById(TrainingPlanning.class, id);
	}

	/** 索引对象列表 */
	public List<TrainningImplement> findTrainningImplementIndex() throws Exception {
		return iTrainningImplementService.findAllByConditions(TrainningImplement.class, null);
	}

	/** 根据对象删除培训明细 */
	public void deleteTrainningImplementDetailEntity(TrainingPlanning trainingPlanning) throws Exception {
		iTrainningImplementService.deleteByEntity(trainingPlanning);
	}

	/** 附件 */
	public HrAttachments merge(HrAttachments attachments) throws Exception {
		return iTrainningImplementService.merge(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return iTrainningImplementService.findEntityById(HrAttachments.class, id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		iTrainningImplementService.deleteByEntity(attachments);
	}

}
