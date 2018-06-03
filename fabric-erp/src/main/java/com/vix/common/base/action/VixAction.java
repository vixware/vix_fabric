package com.vix.common.base.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class VixAction extends VixSecAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String goNvix() {
		return "goNvix";
	}

}