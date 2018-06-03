package com.vix.nvix.drp.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 合同审批
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntExamineContractAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  id;
	
	
	
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
}
