/**
 * 
 */
package com.vix.drp.competitiveproducts.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.competitiveproducts.entity.CompetingProducts;
import com.vix.mdm.item.entity.ItemCatalog;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("competitiveProductsDomain")
@Transactional
public class CompetitiveProductsDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCompetingProductsPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CompetingProducts.class, params);
		return p;
	}

	public CompetingProducts findCompetingProductsById(String id) throws Exception {
		return baseHibernateService.findEntityById(CompetingProducts.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CompetingProducts.class, ids);
	}

	/**
	 * 保存
	 */
	public CompetingProducts mergeCompetingProducts(CompetingProducts competingProducts) throws Exception {
		return baseHibernateService.merge(competingProducts);
	}

	public void deleteByEntity(CompetingProducts competingProducts) throws Exception {
		baseHibernateService.deleteByEntity(competingProducts);
	}

	/** 索引对象列表 */
	public List<CompetingProducts> findCompetingProductsList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CompetingProducts.class, params);
	}

	/** 索引对象列表 */
	public List<ItemCatalog> findItemCatalogList(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllSubEntity(ItemCatalog.class, attributeName, parentId, params);
	}

}
