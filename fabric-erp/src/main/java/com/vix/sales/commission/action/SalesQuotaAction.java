package com.vix.sales.commission.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class SalesQuotaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	public String goQuotaList() {
		return "goQuotaList";
	}
}