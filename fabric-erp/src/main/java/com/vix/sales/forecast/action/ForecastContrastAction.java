package com.vix.sales.forecast.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 销售预测对比
 */
@Controller
@Scope("prototype")
public class ForecastContrastAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String goListContent(){
		return GO_SINGLE_LIST;
	}
}
