package com.vix.nvix.totalPay.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.service.IOrganizationService;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * @财务-综合辅助账
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixSynthesizeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;

	@Autowired
	private IOrganizationService organizationService;
	
	/** 辅助明细账*/
	public String goAssistDetail() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAssistDetail";
	}
	
	/** 辅助汇总表*/
	public String goAssistSum() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAssistSum";
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
