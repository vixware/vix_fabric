/*
 * Copyright (C) 2013 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  
 * Author:   
 * Date:     2013.07.03
 * Version:  1.0
 *
 */

package com.vix.srm.temp.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * @Description: 供应商投标
 * @author ivan
 * @date 2013-09-25
 */
@Controller
@Scope("prototype")
public class TempAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public String goCatalogManagement() {
		return "goCatalogManagement";
	}

	public String goAssessmentManagement() {
		return "goAssessmentManagement";
	}

	public String goCooperativeAgreementsList() {
		return "goCooperativeAgreementsList";
	}

	public String goCooperativeAgreementsContent() {
		return "goCooperativeAgreementsContent";
	}

	public String goBeginSetting() {
		return "goBeginSetting";
	}

	public String goBizObject() {
		return "goBizObject";
	}

	public String goProcedures() {
		return "goProcedures";
	}
	
	public String goApprovalList() {
		return "goApprovalList";
	}
	
	public String goApprovalContent() {
		return "goApprovalContent";
	}
	
	public String goImportList() {
		return "goImportList";
	}
	
	public String goImportContent() {
		return "goImportContent";
	}
}
