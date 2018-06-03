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
package com.vix.srm.bidmgr.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierTender;
import com.vix.mdm.srm.share.entity.SupplierTenderItems;
import com.vix.srm.bidmgr.domain.SupplierTenderDomain;

/**
 * @Description: 供应商投标
 * @author ivan 
 * @date 2013-09-25
 */
@Controller("supplierTenderController")
@Scope("prototype")
public class SupplierTenderController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("SupplierTenderController");

	@Autowired
	private SupplierTenderDomain supplierTenderDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierTenderDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierTenderDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierTenderDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesApply
	 * @return
	 */
	public SupplierTender doSaveSupplierTender(SupplierTender supplierTender) {
		SupplierTender supplierTenderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveApply(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			supplierTenderTemp = supplierTenderDomain.merge(supplierTender);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplierTender + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplierTender + "失败！失败原因：" + ex.getMessage());
		}
		return supplierTenderTemp;
	}

	public SupplierTender doSaveSupplierTender(SupplierTender supplierTender, List<SupplierTenderItems> tiList) throws Exception {

		SupplierTender supplierTenderTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != supplierTender) {
				supplierTenderTemp2 = supplierTenderDomain.merge(supplierTender);
				if (null != tiList && tiList instanceof List) {
					for (SupplierTenderItems ti : tiList) {
						if (null != ti && null != ti.getItemCode()
								&& !"".equals(ti.getItemCode())) {
							ti.setSupplierTender(supplierTenderTemp2);
							supplierTenderDomain.merge(ti);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplierTender + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplierTender + "失败！失败原因：" + ex.getMessage());
			
		}
		return supplierTenderTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public SupplierTender doListEntityById(String id) {
		SupplierTender supplierTenderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveApply(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			supplierTenderTemp = supplierTenderDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return supplierTenderTemp;
	}
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseTender doListPurchaseTenderById(String id) {
		PurchaseTender purchaseTenderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveApply(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			purchaseTenderTemp = supplierTenderDomain.findPurchaseTenderById(id);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
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
	public void doDeleteByEntity(SupplierTender supplierTender) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveApply(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			supplierTenderDomain.deleteByEntity(supplierTender);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}
	
	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return supplierTenderDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments)
			throws Exception {
		supplierTenderDomain.deleteAttachmentsEntity(attachments);
	}
	
	public Attachments mergeAttachments(Attachments attachments) throws Exception{
		return supplierTenderDomain.merge(attachments);
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
		Pager p = supplierTenderDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public SupplierTender findEntityById(String id) throws Exception {
		return supplierTenderDomain.findEntityById(id);
	}
	
	public Supplier findSupplierById(String id) throws Exception {
		return supplierTenderDomain.findSupplierById(id);
	}
	
	public PurchaseTender findPurchaseTenderById(String id) throws Exception {
		return supplierTenderDomain.findPurchaseTenderById(id);
	}

	public SupplierTender merge(SupplierTender supplierTender) throws Exception {
		supplierTenderDomain.merge(supplierTender);
		return null;
	}

	public void deleteByEntity(SupplierTender supplierTender) throws Exception {
		supplierTenderDomain.deleteByEntity(supplierTender);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		supplierTenderDomain.deleteByIds(ids);
	}

	public List<SupplierTender> findSupplierTenderIndex() throws Exception {
		return supplierTenderDomain.findSupplierTenderIndex();
	}
}
