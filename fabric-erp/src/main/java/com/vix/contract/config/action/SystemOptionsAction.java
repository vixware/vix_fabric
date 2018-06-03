package com.vix.contract.config.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class SystemOptionsAction extends BaseAction {
	public static final String GO_SPPLIERMGR_LIST_CONTENT = "goListContent";
	/** 列表数据页 */
	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		
		return GO_LIST;
	}

	
	
}
