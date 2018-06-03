package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrInterviewManagement;
import com.vix.hr.job.service.IInterviewManagementService;

@Transactional
@Component("interviewmanagementdomain")
public class InterviewManagementDomain {

	@Autowired
	private IInterviewManagementService iInterviewManagementService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iInterviewManagementService.findPagerByHqlConditions(pager, HrInterviewManagement.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iInterviewManagementService.findPagerByOrHqlConditions(pager, HrInterviewManagement.class, params);
		return p;
	}

	public HrInterviewManagement findEntityById(String id) throws Exception {
		return iInterviewManagementService.findEntityById(HrInterviewManagement.class, id);
	}

	public HrInterviewManagement merge(HrInterviewManagement hrInterviewManagement) throws Exception {
		return iInterviewManagementService.merge(hrInterviewManagement);
	}

	public void deleteByEntity(HrInterviewManagement hrInterviewManagement) throws Exception {
		iInterviewManagementService.deleteByEntity(hrInterviewManagement);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iInterviewManagementService.batchDelete(HrInterviewManagement.class, ids);
	}

	/** 索引对象列表 */
	public List<HrInterviewManagement> findInterviewManagementIndex() throws Exception {
		return iInterviewManagementService.findAllByConditions(HrInterviewManagement.class, null);
	}
}
