package com.vix.nvix.chain.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.integralRulesSet.entity.IntegralChangeHistory;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 积分兑换历史
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixIntegraChangeHistory extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String customerAccountId = getDecodeRequestParameter("customerAccountId");
			if(StringUtils.isNotEmpty(customerAccountId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, customerAccountId);
			}
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, IntegralChangeHistory.class, params);
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
}
