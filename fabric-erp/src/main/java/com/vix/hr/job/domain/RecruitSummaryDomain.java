package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrRecruitSummary;
import com.vix.hr.job.service.IRecruitSummaryService;

@Transactional
@Component("recruitsummarydomain")
public class RecruitSummaryDomain {

	@Autowired
	private IRecruitSummaryService iRecruitSummaryService;

	/**
	 * 获取招聘总结列表页数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitSummaryService.findPagerByHqlConditions(pager, HrRecruitSummary.class, params);
		return p;
	}

	/**
	 * 获取招聘总结列表页搜索数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitSummaryService.findPagerByOrHqlConditions(pager, HrRecruitSummary.class, params);
		return p;
	}

	/**
	 * 根据Id获取招聘总结信息数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HrRecruitSummary findEntityById(String id) throws Exception {
		return iRecruitSummaryService.findEntityById(HrRecruitSummary.class, id);
	}

	/**
	 * 根据实体对象获取招聘总结信息数据
	 * 
	 * @param hrRecruitSummary
	 * @return
	 * @throws Exception
	 */
	public HrRecruitSummary merge(HrRecruitSummary hrRecruitSummary) throws Exception {
		return iRecruitSummaryService.merge(hrRecruitSummary);
	}

	/**
	 * 根据实体对象删除招聘总结信息数据
	 * 
	 * @param hrRecruitSummary
	 * @throws Exception
	 */
	public void deleteByEntity(HrRecruitSummary hrRecruitSummary) throws Exception {
		iRecruitSummaryService.deleteByEntity(hrRecruitSummary);
	}

	/**
	 * 
	 * 
	 * @param ids
	 */
	public void deleteByIds(List<String> ids) throws Exception {
		iRecruitSummaryService.batchDelete(HrRecruitSummary.class, ids);
	}

	/** 获取招聘总结列表页索引对象 */
	public List<HrRecruitSummary> findRecruitSummaryIndex() throws Exception {
		return iRecruitSummaryService.findAllByConditions(HrRecruitSummary.class, null);
	}
}
