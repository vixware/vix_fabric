/**
 * 
 */
package com.vix.inventory.warehouse.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.inventory.warehouse.entity.InvWarehouselocation;
import com.vix.inventory.warehouse.service.IWarehouseService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("warehouseDomain")
@Transactional
public class WarehouseDomain extends BaseDomain{

	@Autowired
	private IWarehouseService warehouseService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = warehouseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = warehouseService.findPagerByOrHqlConditions(pager, InvWarehouse.class, params);
		return p;
	}

	public InvWarehouse findEntityById(String id) throws Exception {
		return warehouseService.findEntityById(InvWarehouse.class, id);
	}

	public InvWarehouse merge(InvWarehouse wimWarehouse) throws Exception {
		return warehouseService.merge(wimWarehouse);
	}

	public InvWarehouselocation merge(InvWarehouselocation wimWarehouselocation) throws Exception {
		return warehouseService.merge(wimWarehouselocation);
	}

	public void deleteByEntity(InvWarehouse wimWarehouse) throws Exception {
		warehouseService.deleteByEntity(wimWarehouse);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		warehouseService.batchDelete(InvWarehouse.class, ids);
	}

	/** 索引对象列表 */
	public List<InvShelf> findShelf() throws Exception {
		return warehouseService.findAllByConditions(InvShelf.class, null);
	}

}
