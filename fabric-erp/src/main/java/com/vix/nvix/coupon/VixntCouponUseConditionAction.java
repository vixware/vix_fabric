package com.vix.nvix.coupon;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.nvix.common.base.action.VixntBaseAction;
@Controller
@Scope("prototype")
public class VixntCouponUseConditionAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();

			pager = vixntBaseService.findPagerByHqlConditions(pager, Coupon.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateBathCoupon() {
		return "goSaveOrUpdateBathCoupon";
	}
}
