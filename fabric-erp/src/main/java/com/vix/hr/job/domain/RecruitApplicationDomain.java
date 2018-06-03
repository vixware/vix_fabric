package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrRecruitApplication;
import com.vix.hr.job.service.IRecruitApplicationService;

@Transactional
@Component("recruitapplicationDomain")
public class RecruitApplicationDomain {

	@Autowired
	private IRecruitApplicationService iRecruitApplicationService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitApplicationService.findPagerByHqlConditions(pager, HrRecruitApplication.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitApplicationService.findPagerByOrHqlConditions(pager, HrRecruitApplication.class, params);
		return p;
	}

	public HrRecruitApplication findEntityById(String id) throws Exception {
		return iRecruitApplicationService.findEntityById(HrRecruitApplication.class, id);
	}

	public HrRecruitApplication merge(HrRecruitApplication hrRecruitApplication) throws Exception {
		return iRecruitApplicationService.merge(hrRecruitApplication);
	}

	public void deleteByEntity(HrRecruitApplication hrRecruitApplication) throws Exception {
		iRecruitApplicationService.deleteByEntity(hrRecruitApplication);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iRecruitApplicationService.batchDelete(HrRecruitApplication.class, ids);
	}

	/** 索引对象列表 */
	public List<HrRecruitApplication> findRecruitApplicationIndex() throws Exception {
		return iRecruitApplicationService.findAllByConditions(HrRecruitApplication.class, null);
	}
}
