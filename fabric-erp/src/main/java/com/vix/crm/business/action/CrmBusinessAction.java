package com.vix.crm.business.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * com.vix.drp.main.action.DrpAction
 *
 * @author bjitzhang
 *
 * @date 2015年8月26日
 */
@Controller
@Scope("prototype")
public class CrmBusinessAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}
}
