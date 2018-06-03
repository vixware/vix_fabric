
package com.vix.oa.administrativeOfficeCenter.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * @ClassName: InvoiceIssuedAction
 * @Description: 发票开具申请表单 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-7-16 上午10:38:15
 */
@Controller
@Scope("prototype")
public class InvoiceIssuedAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String pageNo;
	
	
	/** 跳转至发票开具申请表单修改页面 */
	public String goInvoiceIssued(){
		
		return "goInvoiceIssued";
	}
	
	/** 处理发票开具申请表单新增修改操作 */
	public String saveInvoiceIssued() {
		
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
