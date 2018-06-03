package com.vix.inventory.reorderPoint.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.inventory.reorderPoint.entity.ReorderPoint;
import com.vix.mdm.item.entity.Item;

/**
 * ROP再订货点
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class ReorderPointAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String itemId;
	private String treeType;
	private ReorderPoint reorderPoint;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (treeType != null && "I".equals(treeType)) {
				if (itemId != null && !"".equals(itemId)) {
					reorderPoint = baseHibernateService.findEntityByAttribute(ReorderPoint.class, "item.id", itemId);
					if (reorderPoint != null) {
					} else {
						reorderPoint = new ReorderPoint();
						Item item = baseHibernateService.findEntityById(Item.class, itemId);
						if (item != null) {
							reorderPoint.setItem(item);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != reorderPoint.getId()) {
				isSave = false;
			}
			Double reorderPointValue = 0D;
			Long normalDeliveryDays = 0L;
			Double daySales = 0D;
			Double safetyStock = 0D;
			if (reorderPoint.getSafetyStock() != null) {
				safetyStock = reorderPoint.getSafetyStock();
			}
			if (reorderPoint.getNormalDeliveryDays() != null) {
				normalDeliveryDays = reorderPoint.getNormalDeliveryDays();
			}
			if (reorderPoint.getDaySales() != null) {
				daySales = reorderPoint.getDaySales();
			}
			reorderPointValue = normalDeliveryDays * daySales + safetyStock;
			reorderPoint.setReorderPoint(reorderPointValue);
			reorderPoint = baseHibernateService.merge(reorderPoint);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public ReorderPoint getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(ReorderPoint reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

}
