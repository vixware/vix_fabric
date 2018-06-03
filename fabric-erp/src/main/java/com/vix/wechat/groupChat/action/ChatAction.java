package com.vix.wechat.groupChat.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class ChatAction extends BaseAction {

	
	/**
	 * np  群聊
	 */
	private static final long serialVersionUID = 1L;

	public String groupChat(){
		return "groupChat";
	}
}
