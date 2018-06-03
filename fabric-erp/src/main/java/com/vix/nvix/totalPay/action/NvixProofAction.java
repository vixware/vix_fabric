package com.vix.nvix.totalPay.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.service.IOrganizationService;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * @财务-总账
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixProofAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;

	@Autowired
	private IOrganizationService organizationService;
	
	/** 填制凭证*/
	public String goWriteProof() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "writeProof";
	}
	
	/** 凭证列表*/
	public String goProofList() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "proofList";
	}
	
	/** 审核凭证*/
	public String goTestProof() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "testProof";
	}
	
	/** 查询凭证*/
	public String goQueryProof() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryProof";
	}
	
	/** 科目汇总*/
	public String goSumSubject() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sumSubject";
	}
	
	/** 摘要汇总表*/
	public String goSumArticle() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sumArticle";
	}
	
	/** 现金流量凭证查询*/
	public String goQueryCash() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryCash";
	}
	
	/** 记账*/
	public String goChargeList() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "chargeList";
	}
	
	/** 常用凭证*/
	public String goCommonProof() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "commonProof";
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
