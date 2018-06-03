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

package com.vix.nvix.purchase.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.purchase.action.VixntPurchaseArrivalAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月30日
 */
@Controller
@Scope("prototype")
public class VixntPurchaseArrivalAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private String id;
	private String purchaseArrivalId;
	private PurchaseArrival purchaseArrival;
	private List<PurchaseArrival> purchaseArrivalList;
	// 币种集合
	private List<CurrencyType> currencyTypeList;
	// 交货地址集合
	private List<ReceivedAddress> receivedAddressesList;
	private PurchaseArrivalItems purchaseArrivalItems;
	private String isAllowAudit;
	@Autowired
	private OrderProcessController orderProcessController;
	private List<BaseEntity> baseEntityList;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (StringUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("deliveryDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			String supplierId = getDecodeRequestParameter("supplierId");
			if(StringUtils.isNotEmpty(supplierId)) {
				params.put("supplierId,"+SearchCondition.EQUAL, supplierId);
			}
			Pager pager = this.getPager();
			pager = baseHibernateService.findPagerByHqlConditions(pager, PurchaseArrival.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.PUR_ARRIVAL);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, id);
			} else {
				purchaseArrival = new PurchaseArrival();
				//purchaseArrival.setCode(autoCreateCode.getBillNO(BillType.PUR_ARRIVAL));
				purchaseArrival.setName("到货订单" + dateFormat.format(new Date()));
				purchaseArrival.setCode(autoCreateCode.getBillNO(BillType.PUR_ARRIVAL));
				purchaseArrival.setCreateTime(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					purchaseArrival.setCreator(employee.getName());
					purchaseArrival.setPurchasePerson(employee.getName());
					purchaseArrival.setPurchasePersonId(employee.getId());
				}
				/*purchaseArrival.setPurchasePerson(this.currentUserName());
				purchaseArrival.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));

				if (SecurityUtil.getCurrentEmpId() != null && !"0".equals(SecurityUtil.getCurrentEmpId())) {
					Employee employee = this.baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						OrganizationUnit org = employee.getOrganizationUnit();
						if (org != null) {
							purchaseArrival.setPurchaseOrgId(org.getId());
							purchaseArrival.setPurchaseOrg(org.getFs());
						}
					}
				}*/
			}
			//currencyTypeList = purchaseOrderController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(purchaseArrival.getId())){
				isSave = false;
				purchaseArrival.setUpdateTime(new Date());
			}else {
				purchaseArrival.setCreateTime(new Date());
			}
			// 设置采购订单状态为未审批
			// purchaseArrival.setStatus("S1");
			if (purchaseArrival.getName() != null)
				purchaseArrival.setPinyin(ChnToPinYin.getPYString(purchaseArrival.getName()));

			if (StringUtils.isEmpty(purchaseArrival.getId()) || "0".equals(purchaseArrival.getId())) {
				// 新建时处理groupCode
			}
			// 处理修改留痕
			// billMarkProcessController.processMark(purchaseArrival, updateField);
			// purchaseArrival = purchaseArrivalController.doSavePurchaseArrival(purchaseArrival, null);
			purchaseArrival = vixntBaseService.merge(purchaseArrival);
			if(purchaseArrival != null){
				dealStartAndSubmitByBillsCode(BillType.PUR_ARRIVAL, purchaseArrival);
			}
			if(isSave){
				renderText("0:"+purchaseArrival.getId()+":"+SAVE_SUCCESS);
			}else{
				renderText("0:"+purchaseArrival.getId()+":"+UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave){
				renderText("1:"+SAVE_FAIL);
			}else{
				renderText("1:"+UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				PurchaseArrival purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, id);
				if (null != purchaseArrival) {
					vixntBaseService.deleteByEntity(purchaseArrival);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/**
	 * 获取到货订单明细
	 */
	public void goPurchaseArrivalItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StrUtils.isNotEmpty(searchName)) {
				params.put("itemName," + SearchCondition.ANYLIKE, searchName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("purchaseArrival.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseArrivalItems.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdatePurchaseArrivalItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseArrivalItems = vixntBaseService.findEntityById(PurchaseArrivalItems.class, id);
			} else {
				purchaseArrivalItems = new PurchaseArrivalItems();
				if (StringUtils.isNotEmpty(purchaseArrivalId)) {
					purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, purchaseArrivalId);
					purchaseArrivalItems.setPurchaseArrival(purchaseArrival);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseArrivalItem";
	}
	
	/**
	 * 增加到货订单明细
	 * 
	 * @return
	 */
	public void saveOrUpdatePurchaseArrivalItem() {
		try {
			initEntityBaseController.initEntityBaseAttribute(purchaseArrivalItems);
			vixntBaseService.merge(purchaseArrivalItems);
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			renderText(SAVE_FAIL);
			e.printStackTrace();
		}
	}
	
	/** 子项价格汇总 */
	public void getOrderItemTotal() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				PurchaseArrival po = vixntBaseService.findEntityById(PurchaseArrival.class, id);
				if (null != po) {
					Double totalAmount = 0d;
					for (PurchaseArrivalItems orderLineItem : po.getPurchaseArrivalItems()) {
						if (null != orderLineItem) {
							if ((null != orderLineItem.getPrice() && !"".equals(orderLineItem.getPrice())) && (null != orderLineItem.getAmount() && !"".equals(orderLineItem.getAmount()))) {
								totalAmount += orderLineItem.getPrice() * orderLineItem.getAmount();
								logger.info("计算采购订单的总额");
							}
						}
					}
					DecimalFormat df = new DecimalFormat(".##");
					if(totalAmount != 0){
						String st = df.format(totalAmount);
						renderJson(st);
					}else{
						renderJson(totalAmount.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSourceList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, id);
				if (purchaseArrival != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(purchaseArrival);
					if (StringUtils.isNotEmpty(purchaseArrival.getSourceClassName()) && StringUtils.isNotEmpty(purchaseArrival.getSourceBillCode())) {
						getSourceBaseEntity(baseEntityList, purchaseArrival);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public void deletePurchaseArrivalItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				PurchaseArrivalItems purchaseArrivalItems = vixntBaseService.findEntityById(PurchaseArrivalItems.class, id);
				if (purchaseArrivalItems != null) {
					vixntBaseService.deleteByEntity(purchaseArrivalItems);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public String show() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, id);
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseArrival = baseHibernateService.findEntityById(PurchaseArrival.class,id);
				if (purchaseArrival != null && purchaseArrival.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseArrival.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					params.put("tenantId", purchaseArrival.getTenantId());
					params.put("companyInnerCode", purchaseArrival.getCompanyInnerCode());
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseArrival = (PurchaseArrival) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseArrival.getCreateTime()), params, purchaseArrival, "before");
						} else if ("after".equals(str)) {
							purchaseArrival = (PurchaseArrival) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseArrival.getCreateTime()), params, purchaseArrival, "after");
						}
					}
					if (purchaseArrival == null || StrUtils.isEmpty(purchaseArrival.getId())) {
						purchaseArrival = baseHibernateService.findEntityById(PurchaseArrival.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, purchaseArrival.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String printPurchaseArrival() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printPurchaseArrival";
	}
	public PurchaseArrivalItems getPurchaseArrivalItems() {
		return purchaseArrivalItems;
	}

	public void setPurchaseArrivalItems(PurchaseArrivalItems purchaseArrivalItems) {
		this.purchaseArrivalItems = purchaseArrivalItems;
	}
	public void createPurchaseArrivalByPurchaseOrder() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				PurchaseOrder purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
				if(purchaseOrder != null) {
					purchaseArrival = new PurchaseArrival();
					purchaseArrival.setCode(autoCreateCode.getBillNO(BillType.PUR_ARRIVAL));
					purchaseArrival.setName("源自采购订单"+dateFormat.format(new Date()));
					purchaseArrival.setCreateTime(new Date());
					purchaseArrival.setUpdateTime(new Date());
					purchaseArrival.setTotalAmount(purchaseOrder.getTotalAmount());
					purchaseArrival.setSourceBillCode(purchaseOrder.getCode());
					purchaseArrival.setSourceClassName(purchaseOrder.getClass().getName());
					Employee employee = getEmployee();
					if (employee != null) {
						purchaseArrival.setCreator(employee.getName());
						purchaseArrival.setPurchasePerson(employee.getName());
						purchaseArrival.setPurchasePersonId(employee.getId());
					}
					if(purchaseOrder.getSupplier() != null) {
						purchaseArrival.setSupplierId(purchaseOrder.getSupplier().getId());
						purchaseArrival.setSupplierName(purchaseOrder.getSupplier().getName());
					}
					purchaseArrival = vixntBaseService.merge(purchaseArrival);
					Map<String, Object> params = getParams();
					params.put("purchaseOrder.id,"+SearchCondition.EQUAL, purchaseOrder.getId());
					List<PurchaseOrderLineItem> purchaseOrderLineItems = vixntBaseService.findAllByConditions(PurchaseOrderLineItem.class, params);
					if(purchaseOrderLineItems != null && purchaseOrderLineItems.size() > 0) {
						for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItems) {
							if(purchaseOrderLineItem != null) {
								PurchaseArrivalItems purchaseArrivalItems = new PurchaseArrivalItems();
								purchaseArrivalItems.setItemCode(purchaseOrderLineItem.getItemCode());
								purchaseArrivalItems.setItemName(purchaseOrderLineItem.getItemName());
								purchaseArrivalItems.setItemId(purchaseOrderLineItem.getItemId());
								purchaseArrivalItems.setPrice(purchaseOrderLineItem.getPrice());
								purchaseArrivalItems.setAmount(purchaseOrderLineItem.getAmount());
								purchaseArrivalItems.setUnit(purchaseOrderLineItem.getUnit());
								purchaseArrivalItems.setPurchaseArrival(purchaseArrival);
								purchaseArrivalItems = vixntBaseService.merge(purchaseArrivalItems);
							}
						}
					}
					renderText("1:转单成功!:"+purchaseArrival.getId());
				}
			}
		} catch (Exception e) {
			renderText("0:转单成功!");
			e.printStackTrace();
		}
	}
	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<ReceivedAddress> getReceivedAddressesList() {
		return receivedAddressesList;
	}

	public void setReceivedAddressesList(List<ReceivedAddress> receivedAddressesList) {
		this.receivedAddressesList = receivedAddressesList;
	}

	public PurchaseArrival getPurchaseArrival() {
		return purchaseArrival;
	}

	public void setPurchaseArrival(PurchaseArrival purchaseArrival) {
		this.purchaseArrival = purchaseArrival;
	}

	public List<PurchaseArrival> getPurchaseArrivalList() {
		return purchaseArrivalList;
	}

	public void setPurchaseArrivalList(List<PurchaseArrival> purchaseArrivalList) {
		this.purchaseArrivalList = purchaseArrivalList;
	}

	public String getPurchaseArrivalId() {
		return purchaseArrivalId;
	}

	public void setPurchaseArrivalId(String purchaseArrivalId) {
		this.purchaseArrivalId = purchaseArrivalId;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<FlowApprovalOpinion> getFlowApprovalOpinionList() {
		return flowApprovalOpinionList;
	}

	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}
}
