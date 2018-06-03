/**
 * 
 */
package com.vix.system.remindsCenter.base.task.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemInventoryProperties;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.system.remindsCenter.base.basetask.VixAlertTask;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

/**
 * 库存预警
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
@Controller("inventoryTask")
public class InventoryTask extends VixAlertTask {

	public static final String TASK_METHOD_NAME = "vix.inventory";
	@Autowired
	private IRemindsCenterService remindsCenterService;

	@Override
	public String getTaskMethodName() {
		return TASK_METHOD_NAME;
	}

	/**
	 * 库存预警任务
	 */
	@Override
	public void doTask() throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = remindsCenterService.findAllByEntityClass(InventoryCurrentStock.class);
		if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
			PurchasePlan purchasePlan = new PurchasePlan();
			purchasePlan.setParentPlanCode(VixUUID.getUUID());
			purchasePlan.setPurchasePlanName("库存预警产生");
			for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
				if (inventoryCurrentStock != null) {
					Item item = inventoryCurrentStock.getItem();
					if (item != null) {
						ItemInventoryProperties itemInventoryProperties = remindsCenterService.findEntityByAttributeNoTenantId(ItemInventoryProperties.class, "item.id", item.getId());
						Double minStockAmount = itemInventoryProperties.getMinStockAmount();
						Double safeStockAmount = itemInventoryProperties.getSafeStockAmount();
						Double purchaseAmount = 0D;
						// 判断如果库存数量小于商品最低库存 生成采购申请单 采购数量等于安全库存减去当前库存
						if (inventoryCurrentStock.getQuantity() < minStockAmount) {
							purchaseAmount = safeStockAmount - inventoryCurrentStock.getQuantity();
							PurchasePlanItems purchasePlanItems = new PurchasePlanItems();
							purchasePlanItems.setItemCode(inventoryCurrentStock.getItemcode());
							purchasePlanItems.setItemName(inventoryCurrentStock.getItemname());
							purchasePlanItems.setSpecification(inventoryCurrentStock.getSpecification());
							purchasePlanItems.setAmount(purchaseAmount);
							purchasePlanItems.setPurchasePlan(purchasePlan);

							if (purchasePlan.getId() != null) {
							} else {
								purchasePlan.setTenantId(inventoryCurrentStock.getTenantId());
								purchasePlan.setCompanyCode(inventoryCurrentStock.getCompanyCode());
								purchasePlan.setCompanyInnerCode(inventoryCurrentStock.getCompanyInnerCode());
								purchasePlan = remindsCenterService.mergeOriginal(purchasePlan);
							}
							purchasePlanItems.setPurchasePlan(purchasePlan);
							remindsCenterService.mergeOriginal(purchasePlanItems);
						}
					}
				}
			}
		}
	}
}
