package com.vix.nvix.coupon;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntMemberCareAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	public void goSingleList() {

	}
}
