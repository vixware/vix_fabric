/**
 * 
 */
package com.vix.drp.drpwarehouse.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.drpwarehouse.service.IDrpWarehouseService;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("drpWarehouseDomain")
@Transactional
public class DrpWarehouseDomain extends BaseDomain{

	@Autowired
	private IDrpWarehouseService drpWarehouseService;

	/** 获取列表数据 */
	public Pager findInvWarehousePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = drpWarehouseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
		return p;
	}

	public void deleteByIds(List<String> ids) throws Exception {
		drpWarehouseService.batchDelete(InvWarehouse.class, ids);
	}

	public InvWarehouse findInvWarehouseById(String id) throws Exception {
		return drpWarehouseService.findEntityById(InvWarehouse.class, id);
	}
	public InvShelf findInvShelfById(String id) throws Exception {
		return drpWarehouseService.findEntityById(InvShelf.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return drpWarehouseService.findEntityById(Employee.class, id);
	}

	public void mergeInvWarehouse(InvWarehouse invWarehouse) throws Exception {
		drpWarehouseService.merge(invWarehouse);
	}

	public void deleteByEntity(InvWarehouse invWarehouse) throws Exception {
		drpWarehouseService.deleteByEntity(invWarehouse);
	}

	public void deleteInvShelfByEntity(InvShelf invShelf) throws Exception {
		drpWarehouseService.deleteByEntity(invShelf);
	}

	/** 索引对象列表 */
	public List<InvWarehouse> findInvWarehouseList(Map<String, Object> params) throws Exception {
		return drpWarehouseService.findAllByConditions(InvWarehouse.class, params);
	}
}
