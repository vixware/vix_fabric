package com.vix.wechat.taskMain.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * @ClassFullName com.vix.wechat.taskMain.action.TaskMainAction
 *
 * @author bjitzhang
 *
 * @date 2016年5月6日
 *
 */
@Controller
@Scope("prototype")
public class TaskMainAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String goTaskMain() {
		return "goTaskMain";
	}

}
