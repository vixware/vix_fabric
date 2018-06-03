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

package com.vix.srm.suppliermgr.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @Description: 供应商预选与评估
 * @author ivan 
 * @date 2013-08-13
 */
@Controller
@Scope("prototype") 
public class SupplierEvaluationAction extends ManagementBusinessPartnerAction {

	public SupplierEvaluationAction()
	{
		super();
		this.usedAction = "supplierEvaluationAction";
		this.supplierStatus=Supplier.status_formal;
	}

	@Override
	public String goSingleList()
	{
		//评估列表建档状态（待评估）
		return this.goSingleList(Supplier.status_auditing);
	}
}
