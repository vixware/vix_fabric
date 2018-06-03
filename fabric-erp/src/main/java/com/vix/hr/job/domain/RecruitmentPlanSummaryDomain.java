package com.vix.hr.job.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrRecruitmentPlansummary;
import com.vix.hr.job.entity.HrRecruitplan;
import com.vix.hr.job.service.RecruitmentPlanSummaryService;
@Transactional
@Component("recruitmentPlanSummaryDomain")
public class RecruitmentPlanSummaryDomain {
	@Autowired
	private RecruitmentPlanSummaryService recruitmentPlanSummaryService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitmentPlanSummaryService.findPagerByHqlConditions(pager, HrRecruitmentPlansummary.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitmentPlanSummaryService.findPagerByOrHqlConditions(pager, HrRecruitmentPlansummary.class, params);
		return p;
	}

	public HrRecruitmentPlansummary findEntityById(String id) throws Exception {
		return recruitmentPlanSummaryService.findEntityById(HrRecruitmentPlansummary.class, id);
	}

	/** 获取招聘计划列表数据 */
	public Pager findPagerByHqlHrRecruitplan(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitmentPlanSummaryService.findPagerByHqlConditions(pager, HrRecruitplan.class, params);
		return p;
	}

	public HrRecruitmentPlansummary merge(HrRecruitmentPlansummary hrRecruitmentPlansummary) throws Exception {
		return recruitmentPlanSummaryService.merge(hrRecruitmentPlansummary);
	}

	public void deleteByEntity(HrRecruitmentPlansummary hrRecruitmentPlansummary) throws Exception {
		recruitmentPlanSummaryService.deleteByEntity(hrRecruitmentPlansummary);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		recruitmentPlanSummaryService.batchDelete(HrRecruitmentPlansummary.class, ids);
	}

	/** 索引对象列表 */
	public List<HrRecruitmentPlansummary> findRecruitmentPlansummaryIndex() throws Exception {
		return recruitmentPlanSummaryService.findAllByConditions(HrRecruitmentPlansummary.class, null);
	}

	/** 根据id获取招聘计划明细数据 */
	public HrRecruitplan findHrRecruitplanById(String id) throws Exception {
		return recruitmentPlanSummaryService.findEntityById(HrRecruitplan.class, id);
	}

	/** 根据对象删除招聘计划 */
	public void deleteHrRecruitplanEntity(HrRecruitplan hrRecruitplan) throws Exception {
		recruitmentPlanSummaryService.deleteByEntity(hrRecruitplan);
	}

	/** 招聘计划明细 */
	public HrRecruitplan merge(HrRecruitplan hrRecruitplan) throws Exception {
		return recruitmentPlanSummaryService.merge(hrRecruitplan);
	}
}
