package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.hr.trainning.service.ITrainingPlanningService;

@Transactional
@Component("trainingplanningdomain")
public class TrainingPlanningDomain {
	@Autowired
	private ITrainingPlanningService iTrainingPlanningService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingPlanningService.findPagerByHqlConditions(pager, TrainingPlanning.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingPlanningService.findPagerByOrHqlConditions(pager, TrainingPlanning.class, params);
		return p;
	}

	public TrainingPlanning findEntityById(String id) throws Exception {
		return iTrainingPlanningService.findEntityById(TrainingPlanning.class, id);
	}

	public UserAccount findUserAccountById(String id) throws Exception {
		return iTrainingPlanningService.findEntityById(UserAccount.class, id);
	}

	/** 根据id获取培训计划明细数据 */
	public TrainingPlanningDetail findTrainingPlanningDetailById(String id) throws Exception {
		return iTrainingPlanningService.findEntityById(TrainingPlanningDetail.class, id);
	}

	public TrainingPlanning merge(TrainingPlanning trainingPlanning) throws Exception {
		return iTrainingPlanningService.merge(trainingPlanning);
	}

	// 培训计划明细
	public TrainingPlanningDetail merge(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		return iTrainingPlanningService.merge(trainingPlanningDetail);
	}

	public void deleteByEntity(TrainingPlanning trainingPlanning) throws Exception {
		iTrainingPlanningService.deleteByEntity(trainingPlanning);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingPlanningService.batchDelete(TrainingPlanning.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingPlanning> findTrainingPlanningIndex() throws Exception {
		return iTrainingPlanningService.findAllByConditions(TrainingPlanning.class, null);
	}

	public void deleteTrainingPlanningDetailEntity(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		iTrainingPlanningService.deleteByEntity(trainingPlanningDetail);
	}

	/** 附件 */
	public HrAttachments merge(HrAttachments attachments) throws Exception {
		return iTrainingPlanningService.merge(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return iTrainingPlanningService.findEntityById(HrAttachments.class, id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		iTrainingPlanningService.deleteByEntity(attachments);
	}

}
