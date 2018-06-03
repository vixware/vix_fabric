package com.vix.nvix.money.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.service.IOrganizationService;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * @财务-应收款
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixCollectionMoneyAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;

	@Autowired
	private IOrganizationService organizationService;
	
	/** 应收单据处理*/
	public String goCollectionList(){
       try {
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "collectionList";
	}
	/** 增加或修改单据*/
	public String goSaveOrUpdate() {
		try {
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/** 收款单据处理*/
	public String goCollectionBill(){
       try {
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "collectionBillList";
	}
	/** 增加或修改收款单据*/
	public String goSaveOrUpdateCollectionBill() {
		try {
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateCollection";
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
