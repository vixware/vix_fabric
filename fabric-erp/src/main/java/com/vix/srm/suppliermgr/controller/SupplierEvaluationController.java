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
package com.vix.srm.suppliermgr.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierEvaluation;
import com.vix.mdm.srm.share.entity.SupplierEvaluationItem;
import com.vix.srm.suppliermgr.domain.SupplierEvaluationDomain;

/**
 * @Description: 供应商预选与评估
 * @author ivan 
 * @date 2013-08-13
 */
@Controller("SupplierEvaluationController")
@Scope("prototype")
public class SupplierEvaluationController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("SupplierEvaluationController");

	@Autowired
	private SupplierEvaluationDomain supplierEvaluationDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierEvaluationDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierEvaluationDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public SupplierEvaluation doSaveSupplierEvaluation(SupplierEvaluation supplierEvaluation) {
		SupplierEvaluation supplierEvaluationTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			supplierEvaluationTemp = supplierEvaluationDomain.merge(supplierEvaluation);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplierEvaluation + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplierEvaluation + "失败！失败原因：" + ex.getMessage());
		}
		return supplierEvaluationTemp;
	}

	public SupplierEvaluation doSaveSupplierEvaluation(SupplierEvaluation supplierEvaluation, List<SupplierEvaluationItem> eiList) throws Exception {

		SupplierEvaluation supplierEvaluationTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != supplierEvaluation) {
				supplierEvaluationTemp2 = supplierEvaluationDomain.merge(supplierEvaluation);
				if (null != eiList) {
					for (SupplierEvaluationItem ei : eiList) {
						if (null != ei) {
							ei.setSupplierEvaluation(supplierEvaluationTemp2);
							supplierEvaluationDomain.merge(ei);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplierEvaluation + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplierEvaluation + "失败！失败原因：" + ex.getMessage());
			
		}
		return supplierEvaluationTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public SupplierEvaluation doListEntityById(String id) {
		SupplierEvaluation supplierEvaluationTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			supplierEvaluationTemp = supplierEvaluationDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return supplierEvaluationTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(SupplierEvaluation supplierEvaluation) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			supplierEvaluationDomain.deleteByEntity(supplierEvaluation);
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
		Pager p = supplierEvaluationDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public SupplierEvaluation findEntityById(String id) throws Exception {
		return supplierEvaluationDomain.findEntityById(id);
	}

	public SupplierEvaluation merge(SupplierEvaluation supplierEvaluation) throws Exception {
		supplierEvaluationDomain.merge(supplierEvaluation);
		return null;
	}

	public void deleteByEntity(SupplierEvaluation supplierEvaluation) throws Exception {
		supplierEvaluationDomain.deleteByEntity(supplierEvaluation);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		supplierEvaluationDomain.deleteByIds(ids);
	}

	public List<SupplierEvaluation> findSalesOrderIndex() throws Exception {
		return supplierEvaluationDomain.findSupplierEvaluationIndex();
	}
}
