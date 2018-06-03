/**
 * 
 */
package com.vix.inventory.transfer.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.transfer.entity.WimTransvouch;
import com.vix.inventory.transfer.entity.WimTransvouchitem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("transferDomain")
@Transactional
public class TransferDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findWimTransvouchPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, WimTransvouch.class, params);
		return p;
	}

	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findTransvouchPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, WimTransvouch.class, params);
		return p;
	}

	public WimTransvouch findWimTransvouchById(String id) throws Exception {
		return baseHibernateService.findEntityById(WimTransvouch.class, id);
	}

	public List<MeasureUnit> findMeasureUnitList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MeasureUnit.class, params);
	}

	public WimTransvouchitem findWimTransvouchitemById(String id) throws Exception {
		return baseHibernateService.findEntityById(WimTransvouchitem.class, id);
	}

	/**
	 * 
	 * @param wimStockrecordlines
	 * @throws Exception
	 */
	public void deleteWimTransvouchitemEntity(WimTransvouchitem wimTransvouchitem) throws Exception {
		baseHibernateService.deleteByEntity(wimTransvouchitem);
	}

	/**
	 * 保存
	 */
	public WimTransvouch mergeWimTransvouch(WimTransvouch wimTransvouch) throws Exception {
		return baseHibernateService.merge(wimTransvouch);
	}

	public InventoryCurrentStock findInventoryCurrentStockByHql(String sql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(sql, params);
	}

	/**
	 * 保存入库单明细
	 */
	public WimTransvouchitem mergeTransvouchItem(WimTransvouchitem wimTransvouchitem) throws Exception {
		return baseHibernateService.merge(wimTransvouchitem);
	}

	public InventoryCurrentStock saveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public void deleteByEntity(WimTransvouch wimTransvouch) throws Exception {
		baseHibernateService.deleteByEntity(wimTransvouch);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(WimTransvouch.class, ids);
	}

	/** 索引对象列表 */
	public List<WimTransvouch> findWimTransvouchList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(WimTransvouch.class, params);
	}

	public List<WimTransvouchitem> findWimTransvouchitemList(Map<String, Object> params) throws Exception {

		return baseHibernateService.findAllByConditions(WimTransvouchitem.class, params);
	}
}
