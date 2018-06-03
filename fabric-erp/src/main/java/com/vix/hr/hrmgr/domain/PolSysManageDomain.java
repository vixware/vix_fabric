package com.vix.hr.hrmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.entity.PolSysManage;
import com.vix.hr.hrmgr.service.IPolSysManageService;

@Component("polSysManageDomain")
@Transactional
public class PolSysManageDomain {

	@Autowired
	private IPolSysManageService iPolSysManageService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPolSysManageService.findPagerByHqlConditions(pager, PolSysManage.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPolSysManageService.findPagerByOrHqlConditions(pager, PolSysManage.class, params);
		return p;
	}

	public PolSysManage findPolSysManageCategoryById(String id) throws Exception {
		return iPolSysManageService.findEntityById(PolSysManage.class, id);
	}

	public PolSysManage merge(PolSysManage polSysManage) throws Exception {
		return iPolSysManageService.merge(polSysManage);
	}

	public void deleteByEntity(PolSysManage polSysManage) throws Exception {
		iPolSysManageService.deleteByEntity(polSysManage);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iPolSysManageService.batchDelete(PolSysManage.class, ids);
	}

	/** 索引对象列表 */
	public List<PolSysManage> findPolSysManageIndex() throws Exception {
		return iPolSysManageService.findAllByConditions(PolSysManage.class, null);
	}

	public List<PolSysManage> findAllSubEntity(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return iPolSysManageService.findAllSubEntity(PolSysManage.class, attributeName, parentId, params);
	}
}
