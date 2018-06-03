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

package com.vix.purchase.main.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * @Description: 采购主页
 * @author ivan
 * @date 2013-11-15
 */
@Controller
@Scope("prototype")
public class PurchaseMainAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 加载边栏 */
	public String goMenuContent(){
		return "goMenuContent";
	}
}
