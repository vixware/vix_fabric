package com.vix.drp.operationsBranch.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class OperationsBranchAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/** 获取列表数据 */
	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		return UPDATE;
	}

}
