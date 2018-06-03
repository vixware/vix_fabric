package com.vix.wechat.contactList.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class ContactListAction extends BaseAction {

	/**
	 * np 通讯录管理
	 */
	private static final long serialVersionUID = 1L;

	//个人通讯录
	public String getPerContactList() {

		return "personlContactList";
	}

	//企业通讯录
	public String getEnterContact() {

		return "enterpriseContactList";
	}

	//最近联系人
	public String getfreContact() {

		return "freContactList";
	}
}
