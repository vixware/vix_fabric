package com.vix.dtbcenter.dtbcenter.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * com.vix.dtbcenter.dtbcenter.action.DtbcenterAction
 *
 * @author bjitzhang
 *
 * @date 2015年10月15日
 */
@Controller
@Scope("prototype")
public class DtbcenterAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}
}
