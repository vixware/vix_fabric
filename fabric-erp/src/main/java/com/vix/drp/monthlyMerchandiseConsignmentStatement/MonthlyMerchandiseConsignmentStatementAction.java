package com.vix.drp.monthlyMerchandiseConsignmentStatement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class MonthlyMerchandiseConsignmentStatementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
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
