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
package com.vix.sales.delivery.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.ApprovalOpinion;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.sales.delivery.domain.SalesReturnDomain;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;

/**
 * @Description: 销售退货
 * @author ivan 
 * @date 2013-08-26
 */
@Controller("salesReturnController")
@Scope("prototype")
public class SalesReturnController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("SalesReturnController");

	@Autowired
	private SalesReturnDomain salesReturnDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = salesReturnDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = salesReturnDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public SaleReturnForm doSaveSaleReturnForm(
			SaleReturnForm saleReturnForm) {
		SaleReturnForm saleReturnFormTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			saleReturnFormTemp = salesReturnDomain.merge(saleReturnForm);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + saleReturnForm + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + saleReturnForm + "失败！失败原因：" + ex.getMessage());
		}
		return saleReturnFormTemp;
	}

	public SaleReturnForm doSaveSaleReturnForm(SaleReturnForm saleReturnForm, List<SaleReturnFormItem> fiList, List<ApprovalOpinion> aoList)
			throws Exception {

		SaleReturnForm saleReturnFormTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != saleReturnForm) {
				saleReturnFormTemp2 = salesReturnDomain.merge(saleReturnForm);
				if (null != fiList && fiList instanceof List) {
					for (SaleReturnFormItem fi : fiList) {
						if (null != fi && null != fi.getItemCode() && !"".equals(fi.getItemCode())) {
							fi.setSaleReturnForm(saleReturnFormTemp2);
							salesReturnDomain.merge(fi);
						}
					}
				}
				if (null != aoList && aoList instanceof List) {
					for (ApprovalOpinion ao : aoList) {
						if (null != ao) {
							//ao.setSaleReturnForm(saleReturnFormTemp2);
							salesReturnDomain.merge(ao);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + saleReturnForm + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + saleReturnForm + "失败！失败原因：" + ex.getMessage());
			
		}
		return saleReturnFormTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public SaleReturnForm doListEntityById(String id) {
		SaleReturnForm saleReturnFormTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			saleReturnFormTemp = salesReturnDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return saleReturnFormTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(SaleReturnForm saleReturnForm) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			salesReturnDomain.deleteByEntity(saleReturnForm);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return salesReturnDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments)
			throws Exception {
		salesReturnDomain.deleteAttachmentsEntity(attachments);
	}

	public Attachments mergeAttachments(Attachments attachments)
			throws Exception {
		return salesReturnDomain.merge(attachments);
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
		Pager p = salesReturnDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public SaleReturnForm findEntityById(String id) throws Exception {
		return salesReturnDomain.findEntityById(id);
	}

	public PurchaseReturn merge(SaleReturnForm saleReturnForm)
			throws Exception {
		salesReturnDomain.merge(saleReturnForm);
		return null;
	}

	public void deleteByEntity(SaleReturnForm saleReturnForm)
			throws Exception {
		salesReturnDomain.deleteByEntity(saleReturnForm);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		salesReturnDomain.deleteByIds(ids);
	}

	public List<SaleReturnForm> findSaleReturnFormIndex() throws Exception {
		return salesReturnDomain.findSaleReturnFormIndex();
	}
}
