/**
 * 
 */
package com.vix.inventory.unacceptedproduct.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.unacceptedproduct.entity.NonconformingProduct;
import com.vix.inventory.unacceptedproduct.entity.NonconformingProductDetails;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("nonconformingProductDomain")
@Transactional
public class NonconformingProductDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findNonconformingProduct(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, NonconformingProduct.class, params);
		return p;
	}

	public List<NonconformingProductDetails> findNonconformingProductDetailsList(Map<String, Object> params) throws Exception {

		return baseHibernateService.findAllByConditions(NonconformingProductDetails.class, params);
	}

	/** 获取列表数据 */
	public Pager findNonconformingProductPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, NonconformingProduct.class, params);
		return p;
	}

	public NonconformingProduct findNonconformingProductById(String id) throws Exception {
		return baseHibernateService.findEntityById(NonconformingProduct.class, id);
	}

	public InventoryParameters findInventoryParametersByByAttribute(String attribute, String param) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryParameters.class, attribute, param);
	}

	public InvMainBatch findInvMainBatch(String attribute, String param) throws Exception {
		return baseHibernateService.findEntityByAttribute(InvMainBatch.class, attribute, param);
	}

	public NonconformingProductDetails findNonconformingProductDetailsById(String id) throws Exception {
		return baseHibernateService.findEntityById(NonconformingProductDetails.class, id);
	}

	public void deleteNonconformingProductDetailsEntity(NonconformingProductDetails nonconformingProductDetails) throws Exception {
		baseHibernateService.deleteByEntity(nonconformingProductDetails);
	}

	public NonconformingProduct mergeNonconformingProduct(NonconformingProduct nonconformingProduct) throws Exception {
		return baseHibernateService.merge(nonconformingProduct);
	}

	public InvMainBatch saveOrUpdateInvMainBatch(InvMainBatch invMainBatch) throws Exception {
		return baseHibernateService.merge(invMainBatch);
	}

	public InventoryCurrentStock findInventoryCurrentStockByHql(String sql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(sql, params);
	}

	public NonconformingProductDetails mergeNonconformingProductDetails(NonconformingProductDetails nonconformingProductDetails) throws Exception {
		return baseHibernateService.merge(nonconformingProductDetails);
	}

	public InventoryCurrentStock saveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public void deleteByEntity(NonconformingProduct nonconformingProduct) throws Exception {
		baseHibernateService.deleteByEntity(nonconformingProduct);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(NonconformingProduct.class, ids);
	}

	/** 索引对象列表 */
	public List<NonconformingProduct> findNonconformingProductList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(NonconformingProduct.class, params);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}

}
