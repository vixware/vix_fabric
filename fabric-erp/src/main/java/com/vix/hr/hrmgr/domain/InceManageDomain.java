package com.vix.hr.hrmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.entity.InceManage;
import com.vix.hr.hrmgr.service.IInceManageService;

@Transactional
@Component("incemanagedomain")
public class InceManageDomain {
	@Autowired
	private IInceManageService iInceManageService;
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iInceManageService.findPagerByHqlConditions(pager, InceManage.class, params);
		return p;
	}
	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iInceManageService.findPagerByOrHqlConditions(pager, InceManage.class, params);
		return p;
	}
	public InceManage findEntityById(String id) throws Exception {
		return iInceManageService.findEntityById(InceManage.class, id);
	}

	public InceManage merge(InceManage inceManage) throws Exception {
		return iInceManageService.merge(inceManage);
	}

	public void deleteByEntity(InceManage inceManage) throws Exception {
		iInceManageService.deleteByEntity(inceManage);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iInceManageService.batchDelete(InceManage.class, ids);
	}

	/** 索引对象列表 */
	public List<InceManage> findInceManageIndex() throws Exception {
		return iInceManageService.findAllByConditions(InceManage.class, null);
	}
}
