package com.vix.contract.top.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * @ClassName: ContractMainAction
 * @Description: 快速新增 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-8-14 下午4:32:48
 */
@Controller
@Scope("prototype")
public class ContractMainAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 加载边栏 */
	public String goMenuContent() {
		return "goMenuContent";
	}
}
