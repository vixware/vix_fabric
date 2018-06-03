package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrSenior;
import com.vix.hr.job.service.ISeniorService;

@Transactional
@Component("seniordomain")
public class SeniorDomain {

	@Autowired
	private ISeniorService iSeniorService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iSeniorService.findPagerByHqlConditions(pager, HrSenior.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iSeniorService.findPagerByOrHqlConditions(pager, HrSenior.class, params);
		return p;
	}

	public HrSenior findEntityById(String id) throws Exception {
		return iSeniorService.findEntityById(HrSenior.class, id);
	}

	public HrSenior merge(HrSenior hrSenior) throws Exception {
		return iSeniorService.merge(hrSenior);
	}

	public void deleteByEntity(HrSenior hrSenior) throws Exception {
		iSeniorService.deleteByEntity(hrSenior);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iSeniorService.batchDelete(HrSenior.class, ids);
	}

	/** 索引对象列表 */
	public List<HrSenior> findSeniorIndex() throws Exception {
		return iSeniorService.findAllByConditions(HrSenior.class, null);
	}

}
