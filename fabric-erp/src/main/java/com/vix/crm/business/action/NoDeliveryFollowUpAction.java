package com.vix.crm.business.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class NoDeliveryFollowUpAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	/**
	 * 关怀
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存催付短信信息
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		return UPDATE;
	}

}
