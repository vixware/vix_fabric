/**
 * 
 */
package com.vix.inventory.reverseflushingmaterial.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.reverseflushingmaterial.entity.ReverseFlushingMaterial;
import com.vix.inventory.reverseflushingmaterial.entity.ReverseFlushingMaterialItem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("reverseFlushingMaterialDomain")
@Transactional
public class ReverseFlushingMaterialDomain extends BaseDomain{


	public Pager findReverseFlushingMaterialPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ReverseFlushingMaterial.class, params);
		return p;
	}

	public Pager findReverseFlushingMaterialPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ReverseFlushingMaterial.class, params);
		return p;
	}

	public ReverseFlushingMaterial findReverseFlushingMaterialById(String id) throws Exception {
		return baseHibernateService.findEntityById(ReverseFlushingMaterial.class, id);
	}

	public ReverseFlushingMaterialItem findReverseFlushingMaterialItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(ReverseFlushingMaterialItem.class, id);
	}

	public void deleteStockTakingItemEntity(ReverseFlushingMaterialItem reverseFlushingMaterialItem) throws Exception {
		baseHibernateService.deleteByEntity(reverseFlushingMaterialItem);
	}

	public ReverseFlushingMaterial mergeReverseFlushingMaterial(ReverseFlushingMaterial reverseFlushingMaterial) throws Exception {
		return baseHibernateService.merge(reverseFlushingMaterial);
	}

	public ReverseFlushingMaterialItem mergeReverseFlushingMaterialItem(ReverseFlushingMaterialItem reverseFlushingMaterialItem) throws Exception {
		return baseHibernateService.merge(reverseFlushingMaterialItem);
	}

	public void deleteReverseFlushingMaterialByEntity(ReverseFlushingMaterial stockTaking) throws Exception {
		baseHibernateService.deleteByEntity(stockTaking);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ReverseFlushingMaterial.class, ids);
	}

	public List<ReverseFlushingMaterial> findreverseFlushingMaterialList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ReverseFlushingMaterial.class, params);
	}
}
