package com.vix.oa.main.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * @ClassName: OaMainAction
 * @Description: 快速新增 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-4 下午2:19:00
 */
@Controller
@Scope("prototype")
public class OaMainAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 加载边栏 */
	public String goMenuContent() {
		return "goMenuContent";
	}
}
