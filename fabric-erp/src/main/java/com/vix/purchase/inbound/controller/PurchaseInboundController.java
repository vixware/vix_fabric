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
package com.vix.purchase.inbound.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.inbound.entity.PurchaseInbound;
import com.vix.mdm.purchase.inbound.entity.PurchaseInboundItems;
import com.vix.purchase.inbound.domain.PurchaseInboundDomain;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-07-17
 */
@Controller("purchaseInboundController")
@Scope("prototype")
public class PurchaseInboundController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PurchaseInboundController");

	@Autowired
	private PurchaseInboundDomain purchaseInboundDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseInboundDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseInboundDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public PurchaseInbound doSavePurchaseInbound(PurchaseInbound purchaseInbound) {
		PurchaseInbound purchaseInboundTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			purchaseInboundTemp = purchaseInboundDomain.merge(purchaseInbound);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseInbound + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseInbound + "失败！失败原因：" + ex.getMessage());
		}
		return purchaseInboundTemp;
	}

	public PurchaseInbound doSavePurchaseInbound(PurchaseInbound purchaseInbound, List<PurchaseInboundItems> iiList) throws Exception {

		PurchaseInbound purchaseInboundTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != purchaseInbound) {
				purchaseInboundTemp2 = purchaseInboundDomain.merge(purchaseInbound);
				if (null != iiList && iiList instanceof List) {
					for (PurchaseInboundItems ii : iiList) {
						if (null != ii && null != ii.getItemCode() && !"".equals(ii.getItemCode())) {
							ii.setPurchaseInbound(purchaseInboundTemp2);
							purchaseInboundDomain.merge(ii);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseInbound + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseInbound + "失败！失败原因：" + ex.getMessage());
			
		}
		return purchaseInboundTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public PurchaseInbound doListEntityById(String id) throws Exception {
		PurchaseInbound purchaseInboundTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchaseInboundTemp = purchaseInboundDomain.findEntityById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchaseInboundTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(PurchaseInbound purchaseInbound) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			purchaseInboundDomain.deleteByEntity(purchaseInbound);
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

	public Pager goSubSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseInboundDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PurchaseInbound findEntityById(String id) throws Exception {
		return purchaseInboundDomain.findEntityById(id);
	}

	public PurchaseInbound merge(PurchaseInbound purchaseInbound) throws Exception {
		purchaseInboundDomain.merge(purchaseInbound);
		return null;
	}

	public void deleteByEntity(PurchaseInbound purchaseInbound) throws Exception {
		purchaseInboundDomain.deleteByEntity(purchaseInbound);
	}

	public List<PurchaseInbound> findPurchaseInboundIndex() throws Exception {
		return purchaseInboundDomain.findPurchaseInboundIndex();
	}
}
