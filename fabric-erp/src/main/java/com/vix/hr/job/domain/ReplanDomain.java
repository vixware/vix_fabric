package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.job.entity.HrRecruitmentPlanDetails;
import com.vix.hr.job.entity.HrRecruitplan;
import com.vix.hr.job.service.IRePlanService;

@Transactional
@Component("replandomain")
public class ReplanDomain {

	@Autowired
	private IRePlanService iRePlanService;

	/**
	 * 获取招聘计划列表数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRePlanService.findPagerByHqlConditions(pager, HrRecruitplan.class, params);
		return p;
	}

	/**
	 * 获取招聘计划列表页搜索功能数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRePlanService.findPagerByOrHqlConditions(pager, HrRecruitplan.class, params);
		return p;
	}

	public HrRecruitplan findEntityById(String id) throws Exception {
		return iRePlanService.findEntityById(HrRecruitplan.class, id);
	}

	/**
	 * 根据id获取招聘计划明细数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HrRecruitmentPlanDetails findHrRecruitmentPlanDetailsById(String id) throws Exception {
		return iRePlanService.findEntityById(HrRecruitmentPlanDetails.class, id);
	}

	public HrRecruitplan merge(HrRecruitplan hrRecruitplan) throws Exception {
		return iRePlanService.merge(hrRecruitplan);
	}

	/**
	 * 根据对象获取招聘计划明细
	 * 
	 * @param hrRecruitmentPlanDetails
	 * @return
	 * @throws Exception
	 */
	public HrRecruitmentPlanDetails merge(HrRecruitmentPlanDetails hrRecruitmentPlanDetails) throws Exception {
		return iRePlanService.merge(hrRecruitmentPlanDetails);
	}

	/**
	 * 删除招聘计划主表数据
	 * 
	 * @param hrRecruitplan
	 * @throws Exception
	 */
	public void deleteByEntity(HrRecruitplan hrRecruitplan) throws Exception {
		iRePlanService.deleteByEntity(hrRecruitplan);
	}

	/**
	 * 根据Id删除招聘计划数据
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteByIds(List<String> ids) throws Exception {
		iRePlanService.batchDelete(HrRecruitplan.class, ids);
	}

	/**
	 * 招聘计划列表页索引对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrRecruitplan> findSalesOrderIndex() throws Exception {
		return iRePlanService.findAllByConditions(HrRecruitplan.class, null);
	}

	/**
	 * 根据子对象，删除招聘计划明细数据
	 * 
	 * @param hrRecruitmentPlanDetails
	 * @throws Exception
	 */
	public void deleteHrRecruitmentPlanDetailsEntity(HrRecruitmentPlanDetails hrRecruitmentPlanDetails) throws Exception {
		iRePlanService.deleteByEntity(hrRecruitmentPlanDetails);
	}

	/**
	 * 保存招聘计划明细表tab附件
	 * 
	 * @param attachments
	 * @return
	 * @throws Exception
	 */
	public HrAttachments merge(HrAttachments attachments) throws Exception {
		return iRePlanService.merge(attachments);
	}

	/**
	 * 根据Id获取招聘计划明细表tab附件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return iRePlanService.findEntityById(HrAttachments.class, id);
	}
	/**
	 * 删除附件
	 * 
	 * @param attachments
	 * @throws Exception
	 */
	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		iRePlanService.deleteByEntity(attachments);
	}
}
