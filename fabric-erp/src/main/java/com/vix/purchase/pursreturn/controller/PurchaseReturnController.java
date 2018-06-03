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
package com.vix.purchase.pursreturn.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturnItems;
import com.vix.purchase.pursreturn.domain.PurchaseReturnDomain;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-07-26
 */
@Controller("purchaseReturnController")
@Scope("prototype")
public class PurchaseReturnController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PurchaseReturnController");

	@Autowired
	private PurchaseReturnDomain purchaseReturnDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = purchaseReturnDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = purchaseReturnDomain.findPagerByOrHqlConditions(params,
				pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public PurchaseReturn doSavePurchaseReturn(PurchaseReturn purchaseReturn) {
		PurchaseReturn purchaseReturnTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			purchaseReturnTemp = purchaseReturnDomain.merge(purchaseReturn);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseReturn + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + purchaseReturn + "失败！失败原因：" + ex.getMessage());
		}
		return purchaseReturnTemp;
	}

	public PurchaseReturn doSavePurchaseInbound(
			PurchaseReturn purchaseReturn, List<PurchaseReturnItems> riList)
			throws Exception {

		PurchaseReturn purchaseReturnTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != purchaseReturn) {
				purchaseReturnTemp2 = purchaseReturnDomain.merge(purchaseReturn);
				if (null != riList && riList instanceof List) {
					for (PurchaseReturnItems ri : riList) {
						if (null != ri && null != ri.getItemCode() && !"".equals(ri.getItemCode())) {
							ri.setPurchaseReturn(purchaseReturnTemp2);
							purchaseReturnDomain.merge(ri);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseReturn + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + purchaseReturn + "失败！失败原因：" + ex.getMessage());
			
		}
		return purchaseReturnTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseReturn doListEntityById(String id) {
		PurchaseReturn purchaseReturnTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			purchaseReturnTemp = purchaseReturnDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return purchaseReturnTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(PurchaseReturn purchaseReturn) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			purchaseReturnDomain.deleteByEntity(purchaseReturn);
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

	public Pager goSubSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = purchaseReturnDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PurchaseReturn findEntityById(String id) throws Exception {
		return purchaseReturnDomain.findEntityById(id);
	}

	public PurchaseReturn merge(PurchaseReturn purchaseReturn)
			throws Exception {
		purchaseReturnDomain.merge(purchaseReturn);
		return null;
	}

	public void deleteByEntity(PurchaseReturn purchaseReturn)
			throws Exception {
		purchaseReturnDomain.deleteByEntity(purchaseReturn);
	}


	public List<PurchaseReturn> findPurchaseReturnIndex() throws Exception {
		return purchaseReturnDomain.findPurchaseReturnIndex();
	}
}
