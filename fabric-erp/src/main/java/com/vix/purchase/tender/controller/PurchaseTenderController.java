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
package com.vix.purchase.tender.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.mdm.purchase.tender.entity.PurchaseTenderItem;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.tender.domain.PurchaseTenderDomain;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-07-31
 */
@Controller("purchaseTenderController")
@Scope("prototype")
public class PurchaseTenderController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PurchaseTenderController");

	@Autowired
	private PurchaseTenderDomain purchaseTenderDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseTenderDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(String hql, Pager pager) throws Exception {
		Pager p = purchaseTenderDomain.findPagerByOrHqlConditions(hql, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseTenderDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesTender
	 * @return
	 */
	public PurchaseTender doSavePurchaseTender(PurchaseTender purchaseTender) {
		PurchaseTender purchaseTenderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveTender(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			purchaseTenderTemp = purchaseTenderDomain.merge(purchaseTender);
			// 4. 触发事件 afterEventSaveTender(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseTender + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseTender + "失败！失败原因：" + ex.getMessage());
		}
		return purchaseTenderTemp;
	}

	/**
	 * doSaveExecute实现保存供应商的业务逻辑处理
	 * 
	 * @param salesTender
	 * @return
	 */
	public Supplier doSaveSupplier(Supplier supplier) {
		Supplier supplierTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveTender(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			supplierTemp = purchaseTenderDomain.merge(supplier);
			// 4. 触发事件 afterEventSaveTender(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplier + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + supplier + "失败！失败原因：" + ex.getMessage());
		}
		return supplierTemp;
	}

	public PurchaseTender doSavePurchaseTender(PurchaseTender purchaseTender, List<PurchaseTenderItem> tiList) throws Exception {

		PurchaseTender purchaseTenderTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != purchaseTender) {
				purchaseTenderTemp2 = purchaseTenderDomain.merge(purchaseTender);
				if (null != tiList && tiList instanceof List) {
					for (PurchaseTenderItem ti : tiList) {
						if (null != ti && null != ti.getTenderCode() && !"".equals(ti.getTenderCode())) {
							ti.setPurchaseTender(purchaseTenderTemp2);
							purchaseTenderDomain.merge(ti);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseTender + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseTender + "失败！失败原因：" + ex.getMessage());
			
		}
		return purchaseTenderTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseTender doListEntityById(String id) {
		PurchaseTender purchaseTenderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			purchaseTenderTemp = purchaseTenderDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveTender(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return purchaseTenderTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(PurchaseTender purchaseTender) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveTender(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			purchaseTenderDomain.deleteByEntity(purchaseTender);
			// 4. 触发事件 afterEventSaveTender(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return purchaseTenderDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments) throws Exception {
		purchaseTenderDomain.deleteAttachmentsEntity(attachments);
	}

	public Attachments mergeAttachments(Attachments attachments) throws Exception {
		return purchaseTenderDomain.merge(attachments);
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
		Pager p = purchaseTenderDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PurchaseTender findEntityById(String id) throws Exception {
		return purchaseTenderDomain.findEntityById(id);
	}

	public Supplier findSupplierById(String id) throws Exception {
		return purchaseTenderDomain.findSupplierById(id);
	}

	public PurchaseTender merge(PurchaseTender purchaseTender) throws Exception {
		purchaseTenderDomain.merge(purchaseTender);
		return null;
	}

	public void deleteByEntity(PurchaseTender purchaseTender) throws Exception {
		purchaseTenderDomain.deleteByEntity(purchaseTender);
	}

	public List<PurchaseTender> findPurchaseTenderIndex() throws Exception {
		return purchaseTenderDomain.findPurchaseTenderIndex();
	}

	public List<PurchaseTender> findPurchaseTenderList(String parentId) throws Exception {
		return purchaseTenderDomain.findPurchaseTenderList(parentId);
	}
}
