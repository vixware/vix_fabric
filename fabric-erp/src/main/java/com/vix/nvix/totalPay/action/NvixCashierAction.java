package com.vix.nvix.totalPay.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.service.IOrganizationService;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * @财务-出纳
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixCashierAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;

	@Autowired
	private IOrganizationService organizationService;
	
	/** 现金日记账*/
	public String goCashDiary() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCashDiary";
	}
	/** 银行日记账*/
	public String goBankDiary() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBankDiary";
	}
	/** 资金日记账*/
	public String goFundDiary() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goFundDiary";
	}
	/** 账簿打印*/
	public String goPrintBook() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintBook";
	}
	/** 支票登记簿*/
	public String goCheckBook() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCheckBook";
	}
	/** 银行对账*/
	public String goCheckBank() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "writeProof";
	}
	/** 长期未达账审计*/
	public String goLostCheck() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goLostCheck";
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
