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
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAccountingInfo;
import com.vix.mdm.srm.share.entity.SupplierAddress;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;
import com.vix.mdm.srm.share.entity.SupplierBankInfo;
import com.vix.mdm.srm.share.entity.SupplierCategory;
import com.vix.mdm.srm.share.entity.SupplierCreditInfo;
import com.vix.mdm.srm.share.entity.SupplierIndicators;
import com.vix.srm.suppliermgr.domain.SupplierDomain;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-06-27
 */
@Controller("supplierController")
@Scope("prototype")
public class SupplierController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("SupplierController");

	@Autowired
	private SupplierDomain supplierDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = supplierDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

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
			supplierTemp = supplierDomain.merge(supplier);
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
	public Employee doSaveEmployee(Employee employee) {
		Employee employeeTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			employeeTemp = supplierDomain.merge(employee);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + employee + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + employee + "失败！失败原因：" + ex.getMessage());
		}
		return employeeTemp;
	}

	public Supplier doSaveSupplier(Supplier supplier,
			List<SupplierAptitudeInfo> dlList, 
			List<SupplierAddress> sdList,
			List<SupplierBankInfo> sbList,
			List<SupplierAccountingInfo> saiList,
			List<SupplierCreditInfo> scList,
			List<SupplierIndicators> siList,String categoryId) throws Exception {

		Supplier supplierTemp2 = null;
		SupplierCategory supplierCategoryTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != supplier) {
				if(null != categoryId){
					supplierCategoryTemp2 = supplierDomain.findSupplierCategoryById(categoryId);
					supplier.setSupplierCategory(supplierCategoryTemp2);
				}
				supplierTemp2 = supplierDomain.merge(supplier);
				if (null != dlList) {
					for (SupplierAptitudeInfo da : dlList) {
						if (null != da) {
							da.setSupplier(supplierTemp2);
							supplierDomain.merge(da);
						}
					}
				}
				if (null != sdList) {
					for (SupplierAddress sd : sdList) {
						if (null != sd) {
							sd.setSupplier(supplierTemp2);
							supplierDomain.merge(sd);
						}
					}
				}
				if (null != sbList) {
					for (SupplierBankInfo sb : sbList) {
						if (null != sb) {
							sb.setSupplier(supplierTemp2);
							supplierDomain.merge(sb);
						}
					}
				}
				if (null != saiList) {
					for (SupplierAccountingInfo sa : saiList) {
						if (null != sa) {
							sa.setSupplier(supplierTemp2);
							supplierDomain.merge(sa);
						}
					}
				}
				if (null != scList) {
					for (SupplierCreditInfo sc : scList) {
						if (null != sc) {
							sc.setSupplier(supplierTemp2);
							supplierDomain.merge(sc);
						}
					}
				}
				if (null != siList) {
					for (SupplierIndicators si : siList) {
						if (null != si) {
							si.setSupplier(supplierTemp2);
							supplierDomain.merge(si);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplier + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + supplier + "失败！失败原因：" + ex.getMessage());
			
		}
		return supplierTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public Supplier doListEntityById(String id) {
		Supplier supplierTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			supplierTemp = supplierDomain.findEntityById(id);
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
	 * @param supplier
	 */
	public void doDeleteByEntity(Supplier supplier) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			supplierDomain.deleteByEntity(supplier);
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

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return supplierDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments)
			throws Exception {
		supplierDomain.deleteAttachmentsEntity(attachments);
	}

	public Attachments mergeAttachments(Attachments attachments)
			throws Exception {
		return supplierDomain.merge(attachments);
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
		Pager p = supplierDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public Supplier findEntityById(String id) throws Exception {
		return supplierDomain.findEntityById(id);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		supplierDomain.merge(supplier);
		return null;
	}

	public void deleteByEntity(Supplier supplier) throws Exception {
		supplierDomain.deleteByEntity(supplier);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		supplierDomain.deleteByIds(ids);
	}

	public List<Supplier> findSalesOrderIndex() throws Exception {
		return supplierDomain.findSalesOrderIndex();
	}
	
	public List<SupplierCategory> findAllSubEntity(String attributeName,String parentId,Map<String, Object> params) throws Exception {
		return supplierDomain.findAllSubEntity(attributeName, parentId, params);
	}
}
