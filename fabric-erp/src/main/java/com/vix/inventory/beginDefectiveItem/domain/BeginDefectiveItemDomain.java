/**
 * 
 */
package com.vix.inventory.beginDefectiveItem.domain;

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
import com.vix.inventory.beginDefectiveItem.service.IBeginDefectiveItemService;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("beginDefectiveItemDomain")
@Transactional
public class BeginDefectiveItemDomain extends BaseDomain{

	@Autowired
	private IBeginDefectiveItemService beginDefectiveItemService;

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return beginDefectiveItemService.merge(inventoryCurrentStock);
	}

	public List<ObjectExpand> findObjectExpandList() throws Exception {
		return beginDefectiveItemService.findAllByEntityClass(ObjectExpand.class);
	}

	public List<Specification> findSpecificationList(String attribute, String value, Map<String, Object> params) throws Exception {
		return beginDefectiveItemService.findAllSubEntity(Specification.class, attribute, value, params);
	}

	public Item findItemById(String id) throws Exception {
		return beginDefectiveItemService.findEntityById(Item.class, id);
	}

	public List<SpecificationDetail> findSpecificationDetailList(String attribute, String value, Map<String, Object> params) throws Exception {
		return beginDefectiveItemService.findAllSubEntity(SpecificationDetail.class, attribute, value, params);
	}

	/** 获取列表数据 */
	public Pager findItemPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = beginDefectiveItemService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public SpecificationDetail findSpecificationDetailById(String id) throws Exception {
		return beginDefectiveItemService.findEntityById(SpecificationDetail.class, id);
	}
}
