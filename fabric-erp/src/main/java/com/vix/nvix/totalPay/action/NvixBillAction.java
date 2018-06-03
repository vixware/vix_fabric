package com.vix.nvix.totalPay.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.service.IOrganizationService;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * @财务-账表
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixBillAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;

	@Autowired
	private IOrganizationService organizationService;
	
	/** 我的账表*/
	public String goMyForm() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyForm";
	}
	
	/** 科目账-余额表*/
	public String goBalanceBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBalanceBill";
	}
	
	/** 科目账-明细账*/
	public String goDetailBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDetailBill";
	}
	
	/** 客户往来辅助账*/
	public String goAssistBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAssistBill";
	}
	
	/** 供应商往来辅助账*/
	public String goApplyBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goApplyBill";
	}
	
	/** 个人往来账*/
	public String goPersonalBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPersonalBill";
	}
	
	/** 部们往来账*/
	public String goDepartmentBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDepartmentBill";
	}
	
	/** 项目辅助账*/
	public String goProjectBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectBill";
	}
	
	/** 现金流量表*/
	public String goCashBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCashBill";
	}
	
	/** 账簿打印*/
	public String goPrintBill() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintBill";
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
