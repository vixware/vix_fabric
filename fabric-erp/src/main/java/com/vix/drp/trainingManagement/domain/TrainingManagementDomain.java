package com.vix.drp.trainingManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.trainingManagement.entity.TrainingManagement;
import com.vix.drp.trainingManagement.service.ITrainingManagementService;
import com.vix.mdm.srm.share.entity.Attachments;

@Component("trainingManagementDomain")
@Transactional
public class TrainingManagementDomain extends BaseDomain{

	@Autowired
	private ITrainingManagementService trainingManagementService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingManagementService.findPagerByHqlConditions(pager, TrainingManagement.class, params);
		return p;
	}

	public TrainingManagement findTrainingManagementById(String id) throws Exception {
		return trainingManagementService.findEntityById(TrainingManagement.class, id);
	}

	public Attachments findAttachmentsById(String id) throws Exception {
		return trainingManagementService.findEntityById(Attachments.class, id);
	}

	public TrainingManagement saveOrUpdate(TrainingManagement trainingManagement) throws Exception {
		return trainingManagementService.merge(trainingManagement);
	}

	public void saveOrUpdateAttachments(Attachments attachments) throws Exception {
		trainingManagementService.merge(attachments);
	}

	public void deleteByEntity(TrainingManagement trainingManagement) throws Exception {
		trainingManagementService.deleteByEntity(trainingManagement);
	}

	/** 索引对象列表 */
	public List<TrainingManagement> findTrainingManagementList(Map<String, Object> params) throws Exception {
		return trainingManagementService.findAllByConditions(TrainingManagement.class, params);
	}
}
