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

/**   
 * @Title: ContractController.java 
 * @Package com.vix.contract.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:56:42  
 */
package com.vix.srm.portal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;
import com.vix.srm.portal.domain.RegisteredSupplierDomain;

/**
 * @Description: 供应商网上注册
 * @author ivan 
 * @date 2013-08-20
 */
@Controller("registeredSupplierController")
@Scope("prototype")
public class RegisteredSupplierController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("SupplierController");

	@Autowired
	private RegisteredSupplierDomain registeredSupplierDomain;

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public Supplier doSaveSupplier(Supplier supplier) {
		Supplier supplierTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			supplierTemp = registeredSupplierDomain.merge(supplier);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplier + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplier + "失败！失败原因：" + ex.getMessage());
		}
		return supplierTemp;
	}
	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public SupplierAptitudeInfo doSaveSupplierAptitudeInfo(SupplierAptitudeInfo supplierAptitudeInfo) {
		SupplierAptitudeInfo supplierAptitudeInfoTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			supplierAptitudeInfoTemp = registeredSupplierDomain.merge(supplierAptitudeInfo);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplierAptitudeInfo + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplierAptitudeInfo + "失败！失败原因：" + ex.getMessage());
		}
		return supplierAptitudeInfoTemp;
	}
	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public Item doSaveItem(Item item) {
		Item itemTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			itemTemp = registeredSupplierDomain.merge(item);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + item + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + item + "失败！失败原因：" + ex.getMessage());
		}
		return itemTemp;
	}
	
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public Supplier doListSupplierById(String id) {
		Supplier supplierTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			supplierTemp = registeredSupplierDomain.findSupplierById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return supplierTemp;
	}
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public SupplierAptitudeInfo doListSupplierAptitudeInfoById(String id) {
		SupplierAptitudeInfo supplierAptitudeInfoTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			supplierAptitudeInfoTemp = registeredSupplierDomain.findSupplierAptitudeInfoById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		
		return supplierAptitudeInfoTemp;
	}
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public Item doListItemById(String id) {
		Item itemTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			itemTemp = registeredSupplierDomain.findItemById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		
		return itemTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(Supplier supplier) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			registeredSupplierDomain.deleteBySupplier(supplier);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void beforeEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName) {
		// to do something
	}

	public Supplier findSupplierById(String id) throws Exception {
		return registeredSupplierDomain.findSupplierById(id);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		registeredSupplierDomain.merge(supplier);
		return null;
	}

	public void deleteBySupplier(Supplier supplier) throws Exception {
		registeredSupplierDomain.deleteBySupplier(supplier);
	}

	public void deleteBySupplierIds(List<String> ids) throws Exception {
		registeredSupplierDomain.deleteBySupplierIds(ids);
	}
}
