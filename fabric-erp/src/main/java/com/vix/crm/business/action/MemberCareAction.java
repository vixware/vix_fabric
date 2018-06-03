package com.vix.crm.business.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class MemberCareAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/** 获取列表数据 */
	public String goListContent() {
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}
}
