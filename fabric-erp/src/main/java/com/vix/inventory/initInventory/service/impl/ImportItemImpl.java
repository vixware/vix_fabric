/**
 * 
 */
package com.vix.inventory.initInventory.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.initInventory.service.ImportItem;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.service.IItemService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("importItem")
public class ImportItemImpl implements ImportItem {
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private IItemService itemService;
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	@Override
	public boolean saveOrUpdate(String skuCode, Date massunitEndTime, String warehouseId, Double quantity, String itemCode, String itemName, String masterUnit, String itemtype, Double price) {
		boolean isSave = true;
		try {
			// 需要通过仓库 及sku编码进行唯一存储
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isQualfied", 1);
			params.put("skuCode", skuCode);
			params.put("massunitEndTime", massunitEndTime);
			if (warehouseId != null) {
				params.put("invWarehouseId", warehouseId);
			}
			StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
			InventoryCurrentStock inventory = itemService.findObjectByHql(hql.toString(), params);
			if (inventory != null) {
				inventory.setQuantity(inventory.getQuantity() + quantity);
				inventory.setAvaquantity(inventory.getAvaquantity() + quantity);
				itemService.merge(inventory);
			} else {
				inventory = new InventoryCurrentStock();
				inventory.setItemcode(itemCode);
				inventory.setItemname(itemName);
				inventory.setMasterUnit(masterUnit);
				inventory.setItemtype(itemtype);
				if (warehouseId != null) {
					InvWarehouse invWarehouse = itemService.findEntityById(InvWarehouse.class, warehouseId);
					if (invWarehouse != null) {
						inventory.setWarehouse(invWarehouse.getName());
						inventory.setWarehousecode(invWarehouse.getCode());
						inventory.setInvWarehouse(invWarehouse);
					}
				}
				inventory.setPrice(price);
				inventory.setQuantity(quantity);
				inventory.setAvaquantity(quantity);
				inventory.setSkuCode(skuCode);
				inventory.setIsBinding("2");
				inventory.setIsTemp("");
				inventory.setIsQualfied(1);
				initEntityBaseController.initEntityBaseAttribute(inventory);
				itemService.merge(inventory);
			}
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}
}
