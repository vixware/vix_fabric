package com.vix.drp.ServiceCalendarmanagement.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class ServiceCalendarmanagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String goCanlendar() {
		return "goCanlendar";
	}

}
