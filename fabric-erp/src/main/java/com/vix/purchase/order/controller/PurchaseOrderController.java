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
package com.vix.purchase.order.controller;

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
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.apply.entity.PurchaseApplyDetails;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquiryDetail;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoice;
import com.vix.mdm.purchase.order.entity.ApprovalOpinion;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.DeliveryPlan;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.mdm.purchase.order.entity.PriceConditions;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.order.domain.PurchaseOrderDomain;

/**
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author ivan
 * @date 2013-06-27
 */
@Controller("purchaseOrderController")
@Scope("prototype")
public class PurchaseOrderController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PurchaseOrderController");

	@Autowired
	private PurchaseOrderDomain purchaseOrderDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSupplierList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findSupplierPager(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSingleList3(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPagerByHqlConditions3(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSingleList4(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPagerByHqlConditions4(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/** 获取采购计划列表数据 */
	public Pager goPurchasePlans(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPurchasePlan(params, pager);
		return p;
	}

	/** 获取采购计划列表数据 */
	public Pager goPurchaseApply(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPurchaseApply(params, pager);
		return p;
	}

	public Pager goPurchaseArrivalPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPurchaseArrivalPager(params, pager);
		return p;
	}

	/** 获取采购计划列表数据 */
	public Pager goPurchaseInquire(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseOrderDomain.findPurchaseInquire(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public PurchaseOrder doSavePurchaseOrder(PurchaseOrder purchaseOrder) {
		PurchaseOrder purchaseOrderTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			purchaseOrderTemp = purchaseOrderDomain.merge(purchaseOrder);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseOrder + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseOrder + "失败！失败原因：" + ex.getMessage());
		}
		return purchaseOrderTemp;
	}

	/**
	 * doSaveExecute实现保存采购订单明细的业务逻辑处理
	 * 
	 * @param salesOrder
	 * @return
	 */
	public PurchaseOrderLineItem doSavePurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
		PurchaseOrderLineItem purchaseOrderLineItemTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			purchaseOrderLineItemTemp = purchaseOrderDomain.merge(purchaseOrderLineItem);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseOrderLineItem + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseOrderLineItem + "失败！失败原因：" + ex.getMessage());
		}
		return purchaseOrderLineItemTemp;
	}

	/**
	 * doSaveExecute实现保存供应商的业务逻辑处理
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
			supplierTemp = purchaseOrderDomain.merge(supplier);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplier + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + supplier + "失败！失败原因：" + ex.getMessage());
		}
		return supplierTemp;
	}

	public PurchaseOrder doSavePurchaseOrder(PurchaseOrder purchaseOrder, List<ReceivedAddress> raList, List<DeliveryPlan> dpList, List<PurchaseInvoice> piList, List<PriceConditions> pcList, List<ApprovalOpinion> aoList) throws Exception {

		PurchaseOrder purchaseOrderTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != purchaseOrder) {
				purchaseOrderTemp2 = purchaseOrderDomain.merge(purchaseOrder);
				if (null != raList) {
					for (ReceivedAddress ra : raList) {
						if (null != ra) {
							ra.setPurchaseOrder(purchaseOrderTemp2);
							purchaseOrderDomain.merge(ra);
						}
					}
				}
				if (null != dpList) {
					for (DeliveryPlan dp : dpList) {
						if (null != dp) {
							dp.setPurchaseOrder(purchaseOrderTemp2);
							purchaseOrderDomain.merge(dp);
						}
					}
				}
				if (null != piList) {
					for (PurchaseInvoice pi : piList) {
						if (null != pi) {
							pi.setPurchaseOrder(purchaseOrderTemp2);
							purchaseOrderDomain.merge(pi);
						}
					}
				}
				if (null != pcList) {
					for (PriceConditions pc : pcList) {
						if (null != pc) {
							pc.setPurchaseOrder(purchaseOrderTemp2);
							purchaseOrderDomain.merge(pc);
						}
					}
				}
				if (null != aoList) {
					for (ApprovalOpinion ao : aoList) {
						if (null != ao) {
							ao.setPurchaseOrder(purchaseOrderTemp2);
							purchaseOrderDomain.merge(ao);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + purchaseOrder + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + purchaseOrder + "失败！失败原因：" + ex.getMessage());
		}
		return purchaseOrderTemp2;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PurchaseOrder doListEntityById(String id) throws Exception {
		PurchaseOrder purchaseOrderTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchaseOrderTemp = purchaseOrderDomain.findEntityById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchaseOrderTemp;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public Supplier doListSupplierById(String id) {
		Supplier supplierTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			supplierTemp = purchaseOrderDomain.findSupplierById(id);
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
	 * @param id
	 * @return
	 */
	public PurchasePlan doListPurchasePlanById(String id) {
		PurchasePlan purchasePlanTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			purchasePlanTemp = purchaseOrderDomain.findPurchasePlanById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return purchasePlanTemp;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseApply doListPurchaseApplyById(String id) {
		PurchaseApply purchaseApplyTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			purchaseApplyTemp = purchaseOrderDomain.findPurchaseApplyById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return purchaseApplyTemp;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseInquire doListPurchaseInquireById(String id) {
		PurchaseInquire purchaseInquireTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			purchaseInquireTemp = purchaseOrderDomain.findPurchaseInquireById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return purchaseInquireTemp;
	}

	public void converterPlanToOrder(PurchaseOrder purchaseOrder, PurchasePlan purchasePlan) throws Exception {
		// 计划单明细
		Set<PurchasePlanItems> purchasePlanItemsSet = new HashSet<PurchasePlanItems>();
		// 采购订单明细
		List<PurchaseOrderLineItem> purchaseOrderLineItemList = new ArrayList<PurchaseOrderLineItem>();
		if (purchasePlan != null) {
			purchasePlanItemsSet = purchasePlan.getPurchasePlanItems();
			for (PurchasePlanItems entity : purchasePlanItemsSet) {
				// 将采购订单明细转化成入库单明细
				if (entity != null) {
					PurchaseOrderLineItem temp = new PurchaseOrderLineItem();
					temp.setPpCode(purchasePlan.getCode());
					temp.setPpItemsCode(entity.getCode());
					temp.setItemCode(entity.getItemCode());
					temp.setItemName(entity.getItemName());
					temp.setSpecification(entity.getSpecification());
					temp.setItemType(entity.getItemType());
					temp.setUnit(entity.getUnit());
					temp.setPrice(entity.getPrice());
					temp.setAmount(entity.getAmount());
					temp.setNetTotal(entity.getTotal());
					purchaseOrderLineItemList.add(temp);
				}
			}
		}
		try {
			if (purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0) {
				for (PurchaseOrderLineItem var : purchaseOrderLineItemList) {
					if (var != null) {
						var.setPurchaseOrder(purchaseOrder);
						purchaseOrderDomain.merge(var);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void converterApplyToOrder(PurchaseOrder purchaseOrder, PurchaseApply purchaseApply) throws Exception {
		// 申请单明细
		Set<PurchaseApplyDetails> purchaseApplyItemsSet = new HashSet<PurchaseApplyDetails>();
		// 采购订单明细
		List<PurchaseOrderLineItem> purchaseOrderLineItemList = new ArrayList<PurchaseOrderLineItem>();
		if (purchaseApply != null) {
			purchaseApplyItemsSet = purchaseApply.getPurchaseApplyDetails();
			for (PurchaseApplyDetails entity : purchaseApplyItemsSet) {
				// 将采购订单明细转化成入库单明细
				if (entity != null) {
					PurchaseOrderLineItem temp = new PurchaseOrderLineItem();
					temp.setPpCode(purchaseApply.getCode());
					temp.setPpItemsCode(entity.getCode());
					temp.setItemCode(entity.getItemCode());
					temp.setItemName(entity.getItemName());
					temp.setSpecification(entity.getSpecification());
					temp.setItemType(entity.getItemType());
					temp.setUnit(entity.getUnit());
					temp.setPrice(entity.getPrice());
					temp.setAmount(entity.getAmount());
					temp.setNetTotal(entity.getTotal());
					purchaseOrderLineItemList.add(temp);
				}
			}
		}
		try {
			if (purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0) {
				for (PurchaseOrderLineItem var : purchaseOrderLineItemList) {
					if (var != null) {
						var.setPurchaseOrder(purchaseOrder);
						purchaseOrderDomain.merge(var);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void converterInquireToOrder(PurchaseOrder purchaseOrder, PurchaseInquire purchaseInquire) throws Exception {
		// 申请单明细
		Set<PurchaseInquiryDetail> purchaseInquireItemsSet = new HashSet<PurchaseInquiryDetail>();
		// 采购订单明细
		List<PurchaseOrderLineItem> purchaseOrderLineItemList = new ArrayList<PurchaseOrderLineItem>();
		if (purchaseInquire != null) {
			purchaseInquireItemsSet = purchaseInquire.getPurchaseInquiryDetails();
			for (PurchaseInquiryDetail entity : purchaseInquireItemsSet) {
				// 将采购订单明细转化成入库单明细
				if (entity != null) {
					PurchaseOrderLineItem temp = new PurchaseOrderLineItem();
					temp.setPiCode(purchaseInquire.getCode());
					temp.setPiItemsCode(entity.getCode());
					temp.setItemCode(entity.getItemCode());
					temp.setItemName(entity.getItemName());
					temp.setSpecification(entity.getSpecification());
					temp.setItemType(entity.getItemType());
					temp.setUnit(entity.getUnit());
					temp.setPrice(entity.getPrice());
					temp.setAmount(entity.getAmount());
					temp.setNetTotal(entity.getTotal());
					purchaseOrderLineItemList.add(temp);
				}
			}
		}
		try {
			if (purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0) {
				for (PurchaseOrderLineItem var : purchaseOrderLineItemList) {
					if (var != null) {
						var.setPurchaseOrder(purchaseOrder);
						purchaseOrderDomain.merge(var);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(PurchaseOrder purchaseOrder) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			purchaseOrderDomain.deleteByEntity(purchaseOrder);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return purchaseOrderDomain.findAttachmentsEntityById(id);
	}

	public void deleteAttachmentsEntity(Attachments attachments) throws Exception {
		purchaseOrderDomain.deleteAttachmentsEntity(attachments);
	}

	public Attachments mergeAttachments(Attachments attachments) throws Exception {
		return purchaseOrderDomain.merge(attachments);
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
		Pager p = purchaseOrderDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PurchaseOrder findEntityById(String id) throws Exception {
		return purchaseOrderDomain.findEntityById(id);
	}

	public PurchaseOrderLineItem findPurchaseOrderLineItemById(String id) throws Exception {
		return purchaseOrderDomain.findPurchaseOrderLineItemById(id);
	}

	public PurchaseOrder merge(PurchaseOrder purchaseOrder) throws Exception {
		purchaseOrderDomain.merge(purchaseOrder);
		return null;
	}

	public void deleteByEntity(PurchaseOrder purchaseOrder) throws Exception {
		purchaseOrderDomain.deleteByEntity(purchaseOrder);
	}

	public List<PurchaseOrder> findPurchaseOrderIndex() throws Exception {
		return purchaseOrderDomain.findPurchaseOrderIndex();
	}

	public List<ReceivedAddress> findReceivedAddressIndex() throws Exception {
		return purchaseOrderDomain.findReceivedAddressIndex();
	}

	public List<OrderType> findOrderTypeIndex() throws Exception {
		return purchaseOrderDomain.findOrderTypeIndex();
	}

	public List<BizType> findBizTypeIndex() throws Exception {
		return purchaseOrderDomain.findBizTypeIndex();
	}

	public List<CurrencyType> findCurrencyTypeIndex() throws Exception {
		return purchaseOrderDomain.findCurrencyTypeIndex();
	}
}
