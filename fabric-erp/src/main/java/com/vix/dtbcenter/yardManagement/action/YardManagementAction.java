package com.vix.dtbcenter.yardManagement.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class YardManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
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

	/** 处理删除操作 */
	public String deleteById() {
		return UPDATE;
	}

}
