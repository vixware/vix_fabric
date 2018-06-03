package com.vix.wechat.base.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * @ClassFullName com.vix.wechat.base.action.WechatAction
 *
 * @author bjitzhang
 *
 * @date 2016年3月15日
 *
 */
@Controller
@Scope("prototype")
public class WechatAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String goIndex() {
		return "goIndex";
	}

}
