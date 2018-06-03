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
package com.vix.purchase.plan.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.purchase.plan.domain.PurchasePlanDomain;
import com.vix.system.billTypeManagement.entity.BillsProperty;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-07-22
 */
@Controller("purchasePlanController")
@Scope("prototype")
public class PurchasePlanController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PurchasePlanController");

	@Autowired
	private PurchasePlanDomain purchasePlanDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchasePlanDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public List<MeasureUnit> doListMeasureUnitList(Map<String, Object> params) throws Exception {
		List<MeasureUnit> measureUnit = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		measureUnit = purchasePlanDomain.findMeasureUnitList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return measureUnit;
	}

	public List<PurchasePlanItems> doListPurchasePlanItemsList(Map<String, Object> params) throws Exception {
		List<PurchasePlanItems> purchasePlanItemsList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		purchasePlanItemsList = purchasePlanDomain.findPurchasePlanItemsList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchasePlanItemsList;
	}

	public List<Employee> doListEmployeeList(Map<String, Object> params) throws Exception {
		List<Employee> employeeList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		employeeList = purchasePlanDomain.findEmployeeList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return employeeList;
	}

	public List<BillsProperty> doListBillsPropertyList(Map<String, Object> params) throws Exception {
		List<BillsProperty> billsPropertyList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		billsPropertyList = purchasePlanDomain.findBillsPropertyList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return billsPropertyList;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchasePlanDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goPurchasePlanPackagePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchasePlanDomain.findPurchasePlanPackagePager(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchasePlanDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesPlan
	 * @return
	 * @throws Exception
	 */
	public PurchasePlan doSavePurchasePlan(PurchasePlan purchasePlan) throws Exception {
		PurchasePlan p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		p = purchasePlanDomain.mergePurchasePlan(purchasePlan);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public PurchasePlanItems doSavePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		PurchasePlanItems purchasePlanItems1 = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		purchasePlanItems1 = purchasePlanDomain.merge(purchasePlanItems);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchasePlanItems1;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PurchasePlan doListEntityById(String id) throws Exception {
		PurchasePlan purchasePlanTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchasePlanTemp = purchasePlanDomain.findEntityById(id);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchasePlanTemp;
	}

	public PurchasePlanItems doListPurchasePlanItemsById(String id) throws Exception {
		PurchasePlanItems purchasePlanItems = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchasePlanItems = purchasePlanDomain.findPurchasePlanItemsById(id);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchasePlanItems;
	}

	public PurchasePlanPackage doListPurchasePlanPackageById(String id) throws Exception {
		PurchasePlanPackage purchasePlanPackage = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchasePlanPackage = purchasePlanDomain.findPurchasePlanPackageById(id);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchasePlanPackage;
	}

	public Employee doListEmployeeById(String id) throws Exception {
		Employee employee = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		employee = purchasePlanDomain.findEmployeeById(id);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return employee;
	}

	public OrganizationUnit doListOrganizationUnitById(String id) throws Exception {
		OrganizationUnit organizationUnit = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		organizationUnit = purchasePlanDomain.findOrganizationUnitById(id);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return organizationUnit;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 * @throws Exception
	 */
	public void doDeleteByEntity(PurchasePlan purchasePlan) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		purchasePlanDomain.deleteByEntity(purchasePlan);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * @param purchasePlanPackage
	 * @return
	 * @throws Exception
	 */
	public PurchasePlanPackage doSavePurchasePlanPackage(PurchasePlanPackage purchasePlanPackage) throws Exception {
		PurchasePlanPackage purchasePlanPackageTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		purchasePlanPackageTemp = purchasePlanDomain.mergePurchasePlanPackage(purchasePlanPackage);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchasePlanPackageTemp;
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return purchasePlanDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments) throws Exception {
		purchasePlanDomain.deleteAttachmentsEntity(attachments);
	}

	public void deletePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		purchasePlanDomain.deletePurchasePlanItems(purchasePlanItems);
	}

	public Attachments mergeAttachments(Attachments attachments) throws Exception {
		return purchasePlanDomain.merge(attachments);
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
		Pager p = purchasePlanDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PurchasePlan findEntityById(String id) throws Exception {
		return purchasePlanDomain.findEntityById(id);
	}

	public void deleteByEntity(PurchasePlan purchasePlan) throws Exception {
		purchasePlanDomain.deleteByEntity(purchasePlan);
	}

	public List<PurchasePlan> findPurchasePlanList(Map<String, Object> params) throws Exception {
		return purchasePlanDomain.findPurchasePlanList(params);
	}

}
