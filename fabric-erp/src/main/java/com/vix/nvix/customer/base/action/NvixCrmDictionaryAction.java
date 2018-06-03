package com.vix.nvix.customer.base.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class NvixCrmDictionaryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/** 跳转到字典维护页面  */
	public String goDictionaryEdit(){
		return "dictionaryEdit";
	}
}
