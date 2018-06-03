package com.vix.nvix.money.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.service.IOrganizationService;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * @财务-应付款
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixPayMoneyAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;

	@Autowired
	private IOrganizationService organizationService;
	
	/** 应付单据处理*/
	public String goPayList(){
       try {
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payList";
	}
	/** 增加或修改单据*/
	public String goSaveOrUpdate() {
		try {
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/** 付款单据处理*/
	public String goPayBill(){
       try {
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payBillList";
	}
	/** 增加或修改付款单据*/
	public String goSaveOrUpdatePayBill() {
		try {
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdatePay";
	}
	/** 票据管理*/
	public String goNoteList(){
       try {
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "NoteList";
	}
	/** 增加或修改票据*/
	public String goSaveOrUpdateNote() {
		try {
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateNote";
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
}
