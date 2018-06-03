package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrTitleSys;
import com.vix.hr.job.service.ITitleSysService;

@Transactional
@Component("titlesysdomain")
public class TitleSysDomain {

	@Autowired
	private ITitleSysService iTitleSysService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTitleSysService.findPagerByHqlConditions(pager, HrTitleSys.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTitleSysService.findPagerByOrHqlConditions(pager, HrTitleSys.class, params);
		return p;
	}

	public HrTitleSys findEntityById(String id) throws Exception {
		return iTitleSysService.findEntityById(HrTitleSys.class, id);
	}

	public HrTitleSys merge(HrTitleSys hrTitleSys) throws Exception {
		return iTitleSysService.merge(hrTitleSys);
	}

	public void deleteByEntity(HrTitleSys hrTitleSys) throws Exception {
		iTitleSysService.deleteByEntity(hrTitleSys);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTitleSysService.batchDelete(HrTitleSys.class, ids);
	}

	/** 索引对象列表 */
	public List<HrTitleSys> findTitleSysIndex() throws Exception {
		return iTitleSysService.findAllByConditions(HrTitleSys.class, null);
	}

}
