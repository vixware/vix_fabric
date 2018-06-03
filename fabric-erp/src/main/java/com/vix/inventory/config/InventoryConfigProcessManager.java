/*
 * Copyright (C) 2013 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  演示模块参数处理的调用过程
 * Author:   Jackie
 * Date:     2013.07.03
 * Version:  1.0
 *
 */
package com.vix.inventory.config;

public class InventoryConfigProcessManager {

	// 假设一参数: INV_CONTROL, 此参数对于库存管理的入库、出库和盘点的业务逻辑都有影响
	// 方法命名要求：
	//      processInvControlInbound()
	//      process 参数名 功能名称
	
	/**
	 * 对于入库业务逻辑的处理
	 * 
	 * InBound Class -> Save() 中调用
	 * public void save(){
	 * 		
	 * 		...
	 *      processInvControllInbound
	 * 
	 * }
	 * 
	 */
	public static void processInvControllInbound(){
		System.out.print("");
	}
	
	
	/**
	 * 对于出库业务逻辑的处理
	 * 
	 * Outbound Class -> Save() 中调用
	 * public void save(){
	 * 		
	 * 		...
	 *      processInvControllOutbound
	 * 
	 * }
	 * 
	 */
	public static void processInvControllOutbound(){
		System.out.print("");
	}	
	
	
	
	
	
	
}
