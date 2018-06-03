package com.vix.sales.forecast.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class QuotationBudgetAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Override
	public String goList(){
		return GO_LIST;
	}
	
	public String goSingleList(){
		return GO_SINGLE_LIST;
	}
	
	public String goSaveOrUpdate(){
		return GO_SAVE_OR_UPDATE;
	}
}