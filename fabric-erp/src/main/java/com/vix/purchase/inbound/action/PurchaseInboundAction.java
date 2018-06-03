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

package com.vix.purchase.inbound.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.billtype.BillType;
import com.vix.common.code.AutoCreateCode;
import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.inbound.entity.PurchaseInbound;
import com.vix.mdm.purchase.inbound.entity.PurchaseInboundItems;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.inbound.controller.PurchaseInboundController;
import com.vix.purchase.order.controller.PurchaseOrderController;
import com.vix.purchase.share.PurchaseAction;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class PurchaseInboundAction extends PurchaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchaseOrder.class);
	/** 注入service */
	@Autowired
	private PurchaseInboundController purchaseInboundController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private AutoCreateCode autoCreateCode;
	@Autowired
	private IDataAccuracy dataAccuracy;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private String id;
	private PurchaseInbound purchaseInbound;
	private String pageNo;

	private List<PurchaseInbound> purchaseInboundList;

	@Autowired
	private PurchaseOrderController purchaseOrderController;
	//币种集合
	private List<CurrencyType> currencyTypeList;
	//交货地址集合
	private List<ReceivedAddress> receivedAddressesList;

	PurchaseInboundItems purchaseItem;

	private String purchaseId;
	private String purchaseType;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");
			pager.setPageSize(1000);

			this.indexEntityList = this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseInbound.class, params).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			//this.addQueryDeleteParam(params);

			String status = getRequestParameter("status");
			if (StrUtils.isNotEmpty(status))
				params.put("status," + SearchCondition.EQUAL, status);

			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String supplierName = getDecodeRequestParameter("supplierName");
			if (StringUtils.isNotEmpty(supplierName)) {
				params.put("supplierName," + SearchCondition.EQUAL, supplierName);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (StringUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.EQUAL, purchasePerson);
			}
			String postingDate = getRequestParameter("postingDate");
			if (StringUtils.isNotEmpty(postingDate)) {
				params.put("postingDate," + SearchCondition.EQUAL, postingDate);
			}
			String deliveryDate = getRequestParameter("deliveryDate");
			if (StringUtils.isNotEmpty(deliveryDate)) {
				params.put("deliveryDate," + SearchCondition.EQUAL, deliveryDate);
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.EQUAL, createTime);
			}
			String contactPerson = getDecodeRequestParameter("contactPerson");
			if (StringUtils.isNotEmpty(contactPerson)) {
				params.put("contactPerson," + SearchCondition.EQUAL, contactPerson);
			}
			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseInbound.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInbound = purchaseInboundController.doListEntityById(id);
			} else {
				if (StrUtils.isNotEmpty(this.purchaseType)) {
					purchaseInbound = (PurchaseInbound) this.copyPurchaseEntity(this.purchaseType, this.purchaseId, "inbound");
				} else {
					purchaseInbound = new PurchaseInbound();
					purchaseInbound.setCode(autoCreateCode.getBillNO(BillType.PUR_INBOUND));
					purchaseInbound.setCreateTime(new Date());
					purchaseInbound.setPurchasePerson(this.currentUserName());
					purchaseInbound.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));

					if (SecurityUtil.getCurrentEmpId() != null) {
						Employee employee = this.baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if (employee != null) {
							OrganizationUnit org = employee.getOrganizationUnit();
							if (org != null) {
								purchaseInbound.setPurchaseOrgId(org.getId());
								purchaseInbound.setPurchaseOrg(org.getFs());
							}
						}
					}
				}
			}
			currencyTypeList = purchaseOrderController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			// 设置采购订单状态为未审批
			//			purchaseInbound.setStatus("S1");
			// 明细
			purchaseInbound.setPinyin(ChnToPinYin.getPYString(purchaseInbound.getName()));

			if (StringUtils.isEmpty(purchaseInbound.getId()) || "0".equals(purchaseInbound.getId())) {
				//新建时处理groupCode
				purchaseInbound.setGroupCode(this.genNewGroupCode(BillType.PUR_INBOUND, purchaseInbound.getCode(), purchaseInbound.getGroupCode()));
			}
			purchaseInbound.setCreateTime(new Date());
			//处理修改留痕
			billMarkProcessController.processMark(purchaseInbound, updateField);
			purchaseInbound = purchaseInboundController.doSavePurchaseInbound(purchaseInbound, null);
			renderText(purchaseInbound.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchaseInbound purchaseInbound = purchaseInboundController.doListEntityById(id);

			if (null != purchaseInbound) {
				this.setEntityDeleteValue(purchaseInbound);
				this.baseHibernateService.update(purchaseInbound);
				renderText("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取采购订单明细json数据 */
	public void getPurchaseInboundItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				PurchaseInbound pi = purchaseInboundController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseInboundItems>(pi.getPurchaseInboundItems()), pi.getPurchaseInboundItems().size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取发货地址数据行数 */
	public void getPurchaseInboundItemsCount() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			if (null != id && !"".equals(id)) {
				PurchaseInbound pa = this.baseHibernateService.findEntityById(PurchaseInbound.class, id);
				if (null != pa && null != pa.getPurchaseInboundItems()) {
					double totalTax = 0;
					double totalFee = 0;
					for (PurchaseInboundItems detailItem : pa.getPurchaseInboundItems()) {
						Double price = detailItem.getPrice();
						Long amount = detailItem.getAmount();
						if (price != null && price > 0 && amount != null && amount > 0) {
							Double tax = detailItem.getTaxRate();
							if (tax == null)
								tax = 0d;

							double itemTotal = price * amount;
							double itemTax = itemTotal * tax / 100;

							totalFee += itemTotal;
							totalTax += itemTax;
						}
					}
					retMap.put("totalFee", totalFee);
					retMap.put("totalFeeTax", totalFee + totalTax);
					retMap.put("totalTax", totalTax);
					renderJson(JSonUtils.toJSon(retMap));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String goAddPurchaseInboundItems() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				this.purchaseItem = this.baseHibernateService.findEntityById(PurchaseInboundItems.class, id);
			}
			receivedAddressesList = purchaseOrderController.findReceivedAddressIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddPurchaseInboundItems";
	}

	public void saveOrUpdatePurchaseInboundItem() {
		try {

			if (this.purchaseItem != null && this.purchaseItem.getId() != null && !"".equals(this.purchaseItem.getId())) {
				//update
				PurchaseInboundItems paItem = this.baseHibernateService.findEntityById(PurchaseInboundItems.class, this.purchaseItem.getId());
				paItem.setItemId(purchaseItem.getItemId());
				paItem.setItemName(purchaseItem.getItemName());
				paItem.setItemCode(purchaseItem.getItemCode());
				paItem.setSpecification(purchaseItem.getSpecification());
				paItem.setPrice(purchaseItem.getPrice());

				paItem.setPrice(this.dataAccuracy.getAmountDecimal(paItem.getPrice()));
				paItem.setAmount(purchaseItem.getAmount());
				paItem.setTaxRate(purchaseItem.getTaxRate());
				paItem.genTatalFee();

				paItem.setTotal(this.dataAccuracy.getAmountDecimal(paItem.getTotal()));
				paItem.setRequireTime(purchaseItem.getRequireTime());
				paItem.setUnit(purchaseItem.getUnit());
				paItem.setRecieveWareHouseId(purchaseItem.getRecieveWareHouseId());
				paItem.setRecieveWareHouse(purchaseItem.getRecieveWareHouse());
				paItem.setSkuCode(purchaseItem.getSkuCode());

				this.baseHibernateService.merge(paItem);
			} else {
				if (purchaseItem == null || purchaseItem.getItemId() == null || "0".equals(purchaseItem.getItemId()) || "".equals(purchaseItem.getItemId())) {
					return;
				}

				Item item = this.baseHibernateService.findEntityById(Item.class, purchaseItem.getItemId());
				if (item == null) {
					return;
				}

				if (purchaseItem.getPurchaseInbound() == null || StringUtils.isEmpty(purchaseItem.getPurchaseInbound().getId()))
					return;
				PurchaseInbound puchaseInbound = this.baseHibernateService.findEntityById(PurchaseInbound.class, purchaseItem.getPurchaseInbound().getId());
				if (puchaseInbound == null)
					return;

				if (StrUtils.isEmpty(purchaseItem.getBarCode()))
					purchaseItem.setBarCode(item.getBarCode());
				if (StrUtils.isEmpty(purchaseItem.getSkuCode()))
					purchaseItem.setSkuCode(item.getSkuCode());

				purchaseItem.setItemCode(item.getCode());
				purchaseItem.setItemName(item.getName());
				purchaseItem.setItemType(item.getItemType());
				if (StrUtils.isEmpty(purchaseItem.getUnit()))
					purchaseItem.setUnit(item.getSaleUnit());
				//页面传过来
				//purchaseItem.setTaxRate(item.getInTax());
				purchaseItem.genTatalFee();

				if (item.getSupplierId() != null) {
					Supplier supplier = this.baseHibernateService.findEntityById(Supplier.class, item.getSupplierId());
					if (supplier != null) {
						purchaseItem.setSupplier(supplier.getName());
					}
				}

				//				purchaseItem.setProject(puchaseInbound.getProject());
				//receivingAddress

				purchaseItem.setCode(VixUUID.getUUID());
				//处理groupCode，与主数据相同的groupCode，对应自身数据的billType,billCode				
				purchaseItem.setGroupCode(this.genNewGroupCode(BillType.PUR_INBOUND_ITEM, purchaseItem.getCode(), puchaseInbound.getGroupCode()));

				this.baseHibernateService.save(purchaseItem);
			}
			renderText(purchaseItem.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePurchaseOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseInboundItems item = this.baseHibernateService.findEntityById(PurchaseInboundItems.class, this.id);
				if (item != null) {
					this.baseHibernateService.deleteByEntity(item);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadPurchaseItemInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> info = this.loadItemInfoMap(this.id);

				renderJson(JSonUtils.toJSon(info));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BizType> listPurchaseInboundBizType() {
		return this.listBizType();
	}

	public String goChoosePurchaseInbound() {
		return "goChoosePurchaseInbound";
	}

	public String goPurchaseInboundList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			this.addTimeLimitToParam(params);
			// 按最近使用
			Pager pager = this.getPager();
			this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseInbound.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseInboundList";
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowPurchaseInbound() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInbound = purchaseInboundController.findEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseInbound";
	}

	public String goPrintPurchaseInbound() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInbound = purchaseInboundController.findEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintPurchaseInbound";
	}

	public String goShowBeforeAndAfterPurchaseInbound() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseInbound = purchaseInboundController.findEntityById(id);
				if (purchaseInbound != null && purchaseInbound.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseInbound.getCreateTime()));
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseInbound = (PurchaseInbound) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseInbound.getCreateTime()), params, purchaseInbound, "before");
						} else if ("after".equals(str)) {
							purchaseInbound = (PurchaseInbound) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseInbound.getCreateTime()), params, purchaseInbound, "after");
						}
					}
					if (purchaseInbound == null || StringUtils.isEmpty(purchaseInbound.getId())) {
						purchaseInbound = purchaseInboundController.findEntityById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseInbound";
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public PurchaseInbound getPurchaseInbound() {
		return purchaseInbound;
	}

	public void setPurchaseInbound(PurchaseInbound purchaseInbound) {
		this.purchaseInbound = purchaseInbound;
	}

	public List<PurchaseInbound> getPurchaseInboundList() {
		return purchaseInboundList;
	}

	public void setPurchaseInboundList(List<PurchaseInbound> purchaseInboundList) {
		this.purchaseInboundList = purchaseInboundList;
	}

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}

	/**
	 * @param str
	 *            the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	public PurchaseInboundController getPurchaseInboundController() {
		return purchaseInboundController;
	}

	public void setPurchaseInboundController(PurchaseInboundController purchaseInboundController) {
		this.purchaseInboundController = purchaseInboundController;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public AutoCreateCode getAutoCreateCode() {
		return autoCreateCode;
	}

	public void setAutoCreateCode(AutoCreateCode autoCreateCode) {
		this.autoCreateCode = autoCreateCode;
	}

	public PurchaseOrderController getPurchaseOrderController() {
		return purchaseOrderController;
	}

	public void setPurchaseOrderController(PurchaseOrderController purchaseOrderController) {
		this.purchaseOrderController = purchaseOrderController;
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

	public PurchaseInboundItems getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseInboundItems purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the purchaseId
	 */
	public String getPurchaseId() {
		return purchaseId;
	}

	/**
	 * @param purchaseId
	 *            the purchaseId to set
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
