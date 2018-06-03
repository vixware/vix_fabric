package com.vix.crm.customer.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class CrmRemindAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	public String goBackSectionPlanRemind(){
		return "goBackSectionPlanRemind";
	}
	
	public String backSectionPlanRemindContent(){
		return "backSectionPlanRemindContent";
	}
	
	public String goCustomerComplaintRemind(){
		return "goCustomerComplaintRemind";
	}
	
	public String customerComplaintRemindContent(){
		return "customerComplaintRemindContent";
	}
}
