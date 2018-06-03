package com.vix.mdm.item.action;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;

@Controller
@Scope("prototype")
public class ItemChooseAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IItemService itemService;
	
	private String categoryId;
	private String chooseType;

	public String goChooseItemForItemGroup(){
		return "goChooseItemForItemGroup";
	}
	
	/** 获取列表数据  */
	public String goListContentForItemGroup(){
		try {
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			params.put("itemGroup.id,"+SearchCondition.IS, null);
			getPager().setPageSize(8);
			if(null == getPager().getOrderField() || "".equals(getPager().getOrderField())){
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			if(null != categoryId && !"".equals(categoryId)){
				itemService.findPagerByItemCatalogId(getPager(),categoryId,params);
			}else{
				itemService.findPagerByHqlConditions(getPager(),Item.class,params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContentForItemGroup";
	}

	public String getChooseType() {
		return chooseType;
	}

	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}

