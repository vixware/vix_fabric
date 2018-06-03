package com.vix.dtbcenter.securityType.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class SecurityTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/**
	 * 根据条件查询派车单信息
	 */
	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改派车单 */
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

	/** 批量处理删除操作 */
	public String deleteByIds() {
		return UPDATE;
	}

}
