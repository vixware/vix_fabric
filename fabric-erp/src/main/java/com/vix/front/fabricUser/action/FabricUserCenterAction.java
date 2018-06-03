package com.vix.front.fabricUser.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;

@Controller
@Scope("request")
public class FabricUserCenterAction extends VixSecAction {

	private static final long serialVersionUID = 1L;

	/*
	 * 个人中心 
	 */
	public String goIndex() {
		return "goIndex";
	}


}