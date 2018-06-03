package com.vix.nvix.chain.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 会员退货
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixCustomerSalesReturnAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("createTime");
			params.put("status,"+SearchCondition.EQUAL, "6");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			String phone = getDecodeRequestParameter("phone");
			if(StringUtils.isNotEmpty(phone)){
				params.put("customerAccount.phone,"+SearchCondition.ANYLIKE, phone);
			}
			params.put("type,"+SearchCondition.EQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
