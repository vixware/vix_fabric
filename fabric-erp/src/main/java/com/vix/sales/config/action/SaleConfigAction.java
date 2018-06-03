package com.vix.sales.config.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class SaleConfigAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	public String goSaleConfig(){
		return "goSaleConfig";
	}
}
