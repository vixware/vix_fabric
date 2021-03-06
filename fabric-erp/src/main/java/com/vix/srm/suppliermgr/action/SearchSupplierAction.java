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
 * @Description: 寻源
 * @author ivan
 * @date 2014-01-23
 */
@Controller
@Scope("prototype") 
public class SearchSupplierAction extends ManagementBusinessPartnerAction {

	public SearchSupplierAction()
	{
		super();
		this.usedAction = "searchSupplierAction";
		this.supplierStatus=Supplier.status_source;
	}

	@Override
	public String goSingleList()
	{
		//寻源列表寻源状态的
		return this.goSingleList(Supplier.status_source);
	}
}
