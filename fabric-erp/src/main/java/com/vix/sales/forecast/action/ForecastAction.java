package com.vix.sales.forecast.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 销售预测
 */
@Controller
@Scope("prototype")
public class ForecastAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String goListContent(){
		return GO_SINGLE_LIST;
	}
}
