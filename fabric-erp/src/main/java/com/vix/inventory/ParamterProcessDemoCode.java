/*
 * Copyright (C) 2013 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  演示具有一致功能性的全局参数处理的调用过程
 * Author:   Jackie
 * Date:     2013.07.03
 * Version:  1.0
 *
 */
package com.vix.inventory;

import org.apache.commons.logging.Log;

import com.vix.common.config.VixGlobalParameterManager;

public class ParamterProcessDemoCode {
	
	
	/**
	 * 调用VixGlobalParameterManager中的静态方法来处理全局参数
	 */
	public void processParameterDemo(){
		
		
		Log logger = null;
		String moduleName = "INVENTORY";
		
		// Call Global Paramter Process Function!
		VixGlobalParameterManager.processDebugLog(logger, moduleName, "测试库存模块性能如何");
	}

	
}
