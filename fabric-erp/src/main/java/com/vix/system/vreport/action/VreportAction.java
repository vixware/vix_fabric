package com.vix.system.vreport.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class VreportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goList1() {
		return "goList1";
	}
	public String goList2() {
		return "goList2";
	}

}
