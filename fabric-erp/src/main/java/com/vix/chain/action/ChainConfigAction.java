package com.vix.chain.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class ChainConfigAction extends BaseAction {

	private static final long serialVersionUID = -4473143784163940328L;

	public String goConfig() {
		return "goConfig";
	}

	public String goBasicConfig() {
		return "goBasicConfig";
	}
}
