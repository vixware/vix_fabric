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
import com.vix.sales.delivery.domain.SalesDeliveryDomain;
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.DeliveryDocumentItem;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-07-26
 */
@Controller("salesDeliveryController")
@Scope("prototype")
public class SalesDeliveryController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("SalesDeliveryController");

	@Autowired
	private SalesDeliveryDomain salesDeliveryDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = salesDeliveryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = salesDeliveryDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public DeliveryDocument doSaveDeliveryDocument(
			DeliveryDocument deliveryDocument) {
		DeliveryDocument deliveryDocumentTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			deliveryDocumentTemp = salesDeliveryDomain.merge(deliveryDocument);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + deliveryDocument + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + deliveryDocument + "失败！失败原因：" + ex.getMessage());
		}
		return deliveryDocumentTemp;
	}

	public DeliveryDocument doSaveDeliveryDocument(
			DeliveryDocument deliveryDocument,
			List<DeliveryDocumentItem> diList, List<ApprovalOpinion> aoList)
			throws Exception {

		DeliveryDocument deliveryDocumentTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != deliveryDocument) {
				deliveryDocumentTemp2 = salesDeliveryDomain
						.merge(deliveryDocument);
				if (null != diList && diList instanceof List) {
					for (DeliveryDocumentItem di : diList) {
						if (null != di && null != di.getItemCode()
								&& !"".equals(di.getItemCode())) {
							di.setDeliveryDocument(deliveryDocumentTemp2);
							salesDeliveryDomain.merge(di);
						}
					}
				}
				if (null != aoList && aoList instanceof List) {
					for (ApprovalOpinion ao : aoList) {
						if (null != ao) {
							//ao.setDeliveryDocument(deliveryDocumentTemp2);
							salesDeliveryDomain.merge(ao);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + deliveryDocument + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + deliveryDocument + "失败！失败原因：" + ex.getMessage());
			
		}
		return deliveryDocumentTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public DeliveryDocument doListEntityById(String id) {
		DeliveryDocument deliveryDocumentTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			deliveryDocumentTemp = salesDeliveryDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return deliveryDocumentTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(DeliveryDocument deliveryDocument) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			salesDeliveryDomain.deleteByEntity(deliveryDocument);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return salesDeliveryDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments)
			throws Exception {
		salesDeliveryDomain.deleteAttachmentsEntity(attachments);
	}

	public Attachments mergeAttachments(Attachments attachments)
			throws Exception {
		return salesDeliveryDomain.merge(attachments);
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
		Pager p = salesDeliveryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public DeliveryDocument findEntityById(String id) throws Exception {
		return salesDeliveryDomain.findEntityById(id);
	}

	public PurchaseReturn merge(DeliveryDocument deliveryDocument)
			throws Exception {
		salesDeliveryDomain.merge(deliveryDocument);
		return null;
	}

	public void deleteByEntity(DeliveryDocument deliveryDocument)
			throws Exception {
		salesDeliveryDomain.deleteByEntity(deliveryDocument);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		salesDeliveryDomain.deleteByIds(ids);
	}

	public List<DeliveryDocument> findDeliveryDocumentIndex() throws Exception {
		return salesDeliveryDomain.findDeliveryDocumentIndex();
	}
}
