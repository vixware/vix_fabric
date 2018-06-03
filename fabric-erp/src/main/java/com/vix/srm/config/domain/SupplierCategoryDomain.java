/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.srm.config.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierCategory;

/**
 * @Description: 供应商分类
 * @author ivan
 * @date 2013-12-2
 */
@Component("supplierCategoryDomain")
@Transactional
public class SupplierCategoryDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SupplierCategory.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, SupplierCategory.class, params);
		return p;
	}

	public SupplierCategory findSupplierCategoryById(String id) throws Exception {
		return baseHibernateService.findEntityById(SupplierCategory.class, id);
	}

	public SupplierCategory merge(SupplierCategory SupplierCategory) throws Exception {
		return baseHibernateService.merge(SupplierCategory);
	}

	public void deleteByEntity(SupplierCategory supplierCategory) throws Exception {
		baseHibernateService.deleteByEntity(supplierCategory);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(SupplierCategory.class, ids);
	}

	/** 索引对象列表 */
	public List<SupplierCategory> findSupplierCategoryIndex() throws Exception {
		return baseHibernateService.findAllByConditions(SupplierCategory.class, null);
	}

	public List<SupplierCategory> findAllSubEntity(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllSubEntity(SupplierCategory.class, attributeName, parentId, params);
	}
}
