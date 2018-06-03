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
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:56:42  
 */
package com.vix.purchase.arrivalmgr.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.purchase.arrivalmgr.domain.PurchaseArrivalDomain;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-07-24
 */
@Controller("purchaseArrivalController")
@Scope("prototype")
public class PurchaseArrivalController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PurchaseArrivalController");

	@Autowired
	private PurchaseArrivalDomain purchaseArrivalDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseArrivalDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseArrivalDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseArrivalDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesArrival
	 * @return
	 * @throws Exception
	 */
	public PurchaseArrival doSavePurchaseArrival(PurchaseArrival purchaseArrival) throws Exception {
		PurchaseArrival purchaseArrivalTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveArrival(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		purchaseArrivalTemp = purchaseArrivalDomain.merge(purchaseArrival);
		// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchaseArrivalTemp;
	}

	public PurchaseArrival doSavePurchaseArrival(PurchaseArrival purchaseArrival, List<PurchaseArrivalItems> aiList) throws Exception {

		PurchaseArrival purchaseArrivalTemp2 = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		if (null != purchaseArrival) {
			purchaseArrivalTemp2 = purchaseArrivalDomain.merge(purchaseArrival);
			if (null != aiList && aiList instanceof List) {
				for (PurchaseArrivalItems ad : aiList) {
					if (null != ad && null != ad.getItemCode() && !"".equals(ad.getItemCode())) {
						ad.setPurchaseArrival(purchaseArrivalTemp2);
						purchaseArrivalDomain.merge(ad);
					}
				}
			}
		}
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchaseArrivalTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PurchaseArrival doListEntityById(String id) throws Exception {
		PurchaseArrival purchaseArrivalTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveArrival(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchaseArrivalTemp = purchaseArrivalDomain.findEntityById(id);
		// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchaseArrivalTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 * @throws Exception 
	 */
	public void doDeleteByEntity(PurchaseArrival purchaseArrival) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveApply(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		purchaseArrivalDomain.deleteByEntity(purchaseArrival);
		// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

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
		Pager p = purchaseArrivalDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PurchaseArrival findEntityById(String id) throws Exception {
		return purchaseArrivalDomain.findEntityById(id);
	}

	public PurchaseArrival merge(PurchaseArrival purchaseArrival) throws Exception {
		purchaseArrivalDomain.merge(purchaseArrival);
		return null;
	}

	public void deleteByEntity(PurchaseArrival purchaseArrival) throws Exception {
		purchaseArrivalDomain.deleteByEntity(purchaseArrival);
	}

	public List<PurchaseArrival> findPurchaseArrivalIndex() throws Exception {
		return purchaseArrivalDomain.findPurchaseArrivalIndex();
	}

	/** 获取采购订单列表数据 */
	public Pager goPurchaseOrders(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseArrivalDomain.findPurchaseOrders(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseOrder doListPurchaseOrderById(String id) {
		PurchaseOrder purchaseOrderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			purchaseOrderTemp = purchaseArrivalDomain.findPurchaseOrderById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return purchaseOrderTemp;
	}

	public void converterOrderToArrival(PurchaseArrival purchaseArrival, PurchaseOrder purchaseOrder) throws Exception {
		// 订单明细
		Set<PurchaseOrderLineItem> purchaseOrderItemsSet = new HashSet<PurchaseOrderLineItem>();
		// 采购到货明细
		List<PurchaseArrivalItems> purchaseArrivalItemList = new ArrayList<PurchaseArrivalItems>();
		if (purchaseOrder != null) {
			purchaseOrderItemsSet = purchaseOrder.getPurchaseOrderLineItems();
			for (PurchaseOrderLineItem entity : purchaseOrderItemsSet) {
				// 将采购订单明细转化成入库单明细
				if (entity != null) {
					PurchaseArrivalItems temp = new PurchaseArrivalItems();
					temp.setPoCode(purchaseOrder.getCode());
					temp.setPoItemsCode(entity.getCode());
					temp.setItemCode(entity.getItemCode());
					temp.setItemName(entity.getItemName());
					temp.setSpecification(entity.getSpecification());
					temp.setItemType(entity.getItemType());
					temp.setUnit(entity.getUnit());
					temp.setPrice(entity.getPrice());
					temp.setAmount(entity.getAmount());
					temp.setNetTotal(entity.getNetTotal());
					temp.setSupplier(purchaseOrder.getSupplierName());
					temp.setRecieveWareHouse(entity.getRecieveAddress());
					temp.setRequireTime(purchaseOrder.getCreateTime());
					purchaseArrivalItemList.add(temp);
				}
			}
		}
		try {
			if (purchaseArrivalItemList != null && purchaseArrivalItemList.size() > 0) {
				for (PurchaseArrivalItems var : purchaseArrivalItemList) {
					if (var != null) {
						var.setPurchaseArrival(purchaseArrival);
						purchaseArrivalDomain.merge(var);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
