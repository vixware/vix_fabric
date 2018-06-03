/**
 * 
 */
package com.vix.inventory.initInventory.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.web.Pager;
import com.vix.inventory.initInventory.service.IInitInventoryService;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.standingbook.entity.MasterInventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("initInventoryDomain")
@Transactional
public class InitInventoryDomain extends BaseDomain{

	@Autowired
	private IInitInventoryService initInventoryService;

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return initInventoryService.merge(inventoryCurrentStock);
	}

	public MasterInventoryCurrentStock mergeMasterInventoryCurrentStock(MasterInventoryCurrentStock inventoryCurrentStock) throws Exception {
		return initInventoryService.merge(inventoryCurrentStock);
	}

	public MasterInventoryCurrentStock findMasterInventoryCurrentStockByHql(String sql, Map<String, Object> params) throws Exception {
		return initInventoryService.findObjectByHql(sql, params);
	}

	public List<ObjectExpand> findObjectExpandList() throws Exception {
		return initInventoryService.findAllByEntityClass(ObjectExpand.class);
	}

	public List<Specification> findSpecificationList(Map<String, Object> params) throws Exception {
		return initInventoryService.findAllByConditions(Specification.class, params);
	}

	public Item findItemById(String id) throws Exception {
		return initInventoryService.findEntityById(Item.class, id);
	}

	public List<SpecificationDetail> findSpecificationDetailList(Map<String, Object> params) throws Exception {
		return initInventoryService.findAllByConditions(SpecificationDetail.class, params);
	}

	/** 获取列表数据 */
	public Pager findItemPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = initInventoryService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public SpecificationDetail findSpecificationDetailById(String id) throws Exception {
		return initInventoryService.findEntityById(SpecificationDetail.class, id);
	}
}
