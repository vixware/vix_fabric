package com.vix.ebusiness.option.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * com.vix.ebusiness.option.action.BusinessAction
 *
 * @author bjitzhang
 *
 * @date 2015年8月26日
 */
@Controller
@Scope("prototype")
public class BusinessAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}
}
