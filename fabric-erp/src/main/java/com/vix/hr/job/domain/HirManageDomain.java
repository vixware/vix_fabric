package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrHirManage;
import com.vix.hr.job.service.IHirManageService;

@Transactional
@Component("hirmanagedomain")
public class HirManageDomain {
	@Autowired
	private IHirManageService iHirManageService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iHirManageService.findPagerByHqlConditions(pager, HrHirManage.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iHirManageService.findPagerByOrHqlConditions(pager, HrHirManage.class, params);
		return p;
	}

	public HrHirManage findEntityById(String id) throws Exception {
		return iHirManageService.findEntityById(HrHirManage.class, id);
	}

	public HrHirManage merge(HrHirManage hrHirManage) throws Exception {
		return iHirManageService.merge(hrHirManage);
	}

	public void deleteByEntity(HrHirManage hirManage) throws Exception {
		iHirManageService.deleteByEntity(hirManage);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iHirManageService.batchDelete(HrHirManage.class, ids);
	}

	/** 索引对象列表 */
	public List<HrHirManage> findHrHirManageIndex() throws Exception {
		return iHirManageService.findAllByConditions(HrHirManage.class, null);
	}
}
