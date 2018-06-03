package com.vix.mdm.item.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCostProperties;

@Controller
@Scope("prototype")
public class ItemCostPropertiesAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private ItemCostProperties itemCostProperties;
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				Item item = baseHibernateService.findEntityById(Item.class,id);
				itemCostProperties = item.getItemCostProperties();
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
			if(null != itemCostProperties.getId()){
				isSave = false;
			}
			itemCostProperties = baseHibernateService.merge(itemCostProperties);
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
 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
 
	public ItemCostProperties getItemCostProperties() {
		return itemCostProperties;
	}

	public void setItemCostProperties(ItemCostProperties itemCostProperties) {
		this.itemCostProperties = itemCostProperties;
	}
}