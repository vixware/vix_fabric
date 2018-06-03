package com.vix.mdm.item.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemPurchaseProperties;

@Controller
@Scope("prototype")
public class ItemPurchasePropertiesAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private ItemPurchaseProperties itemPurchaseProperties;
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				Item item = baseHibernateService.findEntityById(Item.class,id);
				itemPurchaseProperties = item.getItemPurchaseProperties();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	 
	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != itemPurchaseProperties.getId()){
				isSave = false;
			}
			itemPurchaseProperties = baseHibernateService.merge(itemPurchaseProperties);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	private List<InvShelf> invShelfList = new ArrayList<InvShelf>();
	private String invWarehouseId;
	public String loadInvShelf(){
		try{
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				itemPurchaseProperties = baseHibernateService.findEntityById(ItemPurchaseProperties.class, id);
			}
			if(StringUtils.isNotEmpty(invWarehouseId) && !invWarehouseId.equals("0")){
				InvWarehouse invWarehouse = baseHibernateService.findEntityById(InvWarehouse.class, invWarehouseId);
				if(null != invWarehouse && null != invWarehouse.getInvShelfs() && invWarehouse.getInvShelfs().size() > 0){
					invShelfList.addAll(invWarehouse.getInvShelfs());
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "loadInvShelf";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
 
	public ItemPurchaseProperties getItemPurchaseProperties() {
		return itemPurchaseProperties;
	}

	public void setItemPurchaseProperties(ItemPurchaseProperties itemPurchaseProperties) {
		this.itemPurchaseProperties = itemPurchaseProperties;
	}

	public List<InvShelf> getInvShelfList() {
		return invShelfList;
	}

	public void setInvShelfList(List<InvShelf> invShelfList) {
		this.invShelfList = invShelfList;
	}

	public String getInvWarehouseId() {
		return invWarehouseId;
	}

	public void setInvWarehouseId(String invWarehouseId) {
		this.invWarehouseId = invWarehouseId;
	}
}