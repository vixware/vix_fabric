/**
 * 
 */
package com.vix.system.warningSource.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.system.warningSource.entity.ModuleCategory;
import com.vix.system.warningSource.entity.WarningType;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("warningSourceDomain")
@Transactional
public class WarningSourceDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findModuleCategoryPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ModuleCategory.class, params);
		return p;
	}

	public ModuleCategory findModuleCategoryById(String id) throws Exception {
		return baseHibernateService.findEntityById(ModuleCategory.class, id);
	}

	public ModuleCategory mergeModuleCategory(ModuleCategory moduleCategory) throws Exception {
		return baseHibernateService.merge(moduleCategory);
	}

	public WarningType mergeWarningType(WarningType warningType) throws Exception {
		return baseHibernateService.merge(warningType);
	}

	public void deleteByEntity(ModuleCategory moduleCategory) throws Exception {
		baseHibernateService.deleteByEntity(moduleCategory);
	}

	public void deleteModuleCategoryByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ModuleCategory.class, ids);
	}

	/** 索引对象列表 */
	public List<ModuleCategory> findModuleCategoryIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ModuleCategory.class, null);
	}

}
