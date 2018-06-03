package com.vix.nvix.chain.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 消费记录
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixExpenseRecordAction extends VixntBaseAction{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private RequireGoodsOrder requireGoodsOrder;
	
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("payTime");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, selectName);
			}
			String phone = getDecodeRequestParameter("phone");
			if(StringUtils.isNotEmpty(phone)){
				params.put("customerAccount.mobilePhone,"+SearchCondition.ANYLIKE, phone);
			}
			String customerAccountId = getDecodeRequestParameter("customerAccountId");
			if(StringUtils.isNotEmpty(customerAccountId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, customerAccountId);
			}
			params.put("type,"+SearchCondition.EQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goItemList(){
		try{
			if(StrUtils.isNotEmpty(id)){
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goItemList";
	}
	
	public void goItemSingleList(){
		try {
			Map<String, Object> params = getParams();
			params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, id);
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("item.name,"+SearchCondition.ANYLIKE, selectName);
			}
			String code = getDecodeRequestParameter("code");
			if(StringUtils.isNotEmpty(code)){
				params.put("item.code,"+SearchCondition.EQUAL, code);
			}
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrderItem.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}
	
	 
}
