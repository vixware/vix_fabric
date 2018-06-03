package com.vix.dtbcenter.config.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class DtbcenterConfigAction extends BaseAction {

	private static final long serialVersionUID = 3117744925490229196L;

	public String goConfig() {
		return "goConfig";
	}

	public String goBasicConfig() {
		return "goBasicConfig";
	}
}
