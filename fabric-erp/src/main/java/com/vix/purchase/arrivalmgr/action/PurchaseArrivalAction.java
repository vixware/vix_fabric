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

package com.vix.purchase.arrivalmgr.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.arrivalmgr.controller.PurchaseArrivalController;
import com.vix.purchase.order.controller.PurchaseOrderController;
import com.vix.purchase.share.PurchaseAction;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-24
 */
@Controller
@Scope("prototype")
public class PurchaseArrivalAction extends PurchaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchasePlan.class);
	@Autowired
	private PurchaseArrivalController purchaseArrivalController;
	@Autowired
	private AutoCreateCode autoCreateCode;
	@Autowired
	private IDataAccuracy dataAccuracy;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private String id;
	private PurchaseArrival purchaseArrival;
	private String pageNo;
	private List<PurchaseArrival> purchaseArrivalList;
	/** 采购订单 */
	private PurchaseOrder purchaseOrder;
	/** 采购订单列表 */
	private List<PurchaseOrder> purchaseOrderList;
	/** 采购订单明细 */
	private PurchaseOrderLineItem purchaseOrderItems;
	/** 采购订单明细列表 */
	private List<PurchaseOrderLineItem> purchaseOrderItemsList;
	//币种集合
	private List<CurrencyType> currencyTypeList;
	//交货地址集合
	private List<ReceivedAddress> receivedAddressesList;
	@Autowired
	private PurchaseOrderController purchaseOrderController;
	PurchaseArrivalItems purchaseItem;

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

			this.indexEntityList = this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseArrival.class, params).getResultList();
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
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String requireDepartment = getDecodeRequestParameter("requireDepartment");
			if (requireDepartment != null && !"".equals(requireDepartment)) {
				params.put("requireDepartment," + SearchCondition.EQUAL, requireDepartment);
			}
			String supplierName = getDecodeRequestParameter("supplierName");
			if (supplierName != null && !"".equals(supplierName)) {
				params.put("supplierName," + SearchCondition.EQUAL, supplierName);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (purchasePerson != null && !"".equals(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.EQUAL, purchasePerson);
			}
			String contactPerson = getDecodeRequestParameter("contactPerson");
			if (contactPerson != null && !"".equals(contactPerson)) {
				params.put("contactPerson," + SearchCondition.EQUAL, contactPerson);
			}
			String postingDate = getRequestParameter("postingDate");
			if (postingDate != null && !"".equals(postingDate)) {
				params.put("postingDate," + SearchCondition.EQUAL, formatStringToDate(postingDate));
			}
			String deliveryDate = getRequestParameter("deliveryDate");
			if (deliveryDate != null && !"".equals(deliveryDate)) {
				params.put("deliveryDate," + SearchCondition.EQUAL, formatStringToDate(deliveryDate));
			}
			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseArrival.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 内容
			String searchContent = getRequestParameter("searchContent");
			if (null != searchContent && !"".equals(searchContent)) {
				searchContent = URLDecoder.decode(searchContent, "utf-8");
			}
			// 名称
			String name = getRequestParameter("purchaseName");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "utf-8");
			}
			// 业务员
			String purchasePerson = getRequestParameter("purchasePerson");
			if (null != purchasePerson && !"".equals(purchasePerson)) {
				purchasePerson = URLDecoder.decode(purchasePerson, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("purchasePerson," + SearchCondition.ANYLIKE, searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = purchaseArrivalController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null != purchasePerson && !"".equals(purchasePerson)) {
					params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
				}
				pager = purchaseArrivalController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = purchaseArrivalController.goSubSingleList(params, getPager());
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseArrival = purchaseArrivalController.doListEntityById(id);
			} else {
				if (StrUtils.isNotEmpty(this.purchaseType)) {
					purchaseArrival = (PurchaseArrival) this.copyPurchaseEntity(this.purchaseType, this.purchaseId, "arrival");
				} else {
					purchaseArrival = new PurchaseArrival();
					purchaseArrival.setCode(autoCreateCode.getBillNO(BillType.PUR_ARRIVAL));
					purchaseArrival.setPurchasePerson(this.currentUserName());
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
					}
				}
			}
			//			orderTypeList = purchaseOrderController.findOrderTypeIndex();
			//			bizTypeList = purchaseOrderController.findBizTypeIndex();
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
			//			purchaseArrival.setStatus("S1");
			if (purchaseArrival.getName() != null)
				purchaseArrival.setPinyin(ChnToPinYin.getPYString(purchaseArrival.getName()));

			if (StringUtils.isEmpty(purchaseArrival.getId()) || "0".equals(purchaseArrival.getId())) {
				//新建时处理groupCode
				purchaseArrival.setGroupCode(this.genNewGroupCode(BillType.PUR_ARRIVAL, purchaseArrival.getCode(), purchaseArrival.getGroupCode()));
			}
			purchaseArrival.setCreateTime(new Date());
			//处理修改留痕
			billMarkProcessController.processMark(purchaseArrival, updateField);
			purchaseArrival = purchaseArrivalController.doSavePurchaseArrival(purchaseArrival, null);

			renderText(String.valueOf(purchaseArrival.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchaseArrival purchaseArrival = purchaseArrivalController.doListEntityById(id);

			if (null != purchaseArrival) {
				this.setEntityDeleteValue(purchaseArrival);
				this.baseHibernateService.update(purchaseArrival);
				renderText("success");
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取到货明细json数据 */
	public void getPurchaseArrivalItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseArrival pa = purchaseArrivalController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseArrivalItems>(pa.getPurchaseArrivalItems()), pa.getPurchaseArrivalItems().size(), "purchaseArrival");
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

	public List<BizType> listPurchaseArrivalBizType() {
		return this.listBizType();
	}

	/** 获取明细数据行数 */
	public void getPurchaseArrivalItemsCount() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			if (null != id && !"".equals(id)) {
				PurchaseArrival pa = this.baseHibernateService.findEntityById(PurchaseArrival.class, id);
				if (null != pa && null != pa.getPurchaseArrivalItems()) {
					double totalTax = 0;
					double totalFee = 0;
					for (PurchaseArrivalItems detailItem : pa.getPurchaseArrivalItems()) {
						Double price = detailItem.getPrice();
						Double amount = detailItem.getAmount();
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

	// 跳转到添加明细页面
	public String goAddPurchaseArrivalItems() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				this.purchaseItem = this.baseHibernateService.findEntityById(PurchaseArrivalItems.class, id);
			}
			receivedAddressesList = purchaseOrderController.findReceivedAddressIndex();
			logger.info("添加采购订单明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddPurchaseArrivalItems";
	}

	/**
	 * 增加采购订单明细
	 * 
	 * @return
	 */
	public void saveOrUpdatePurchaseArrivalItem() {
		try {

			if (this.purchaseItem != null && StringUtils.isNotEmpty(this.purchaseItem.getId())) {
				//update
				PurchaseArrivalItems paItem = this.baseHibernateService.findEntityById(PurchaseArrivalItems.class, this.purchaseItem.getId());
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
				if (purchaseItem == null || purchaseItem.getItemId() == null || "0".equals(purchaseItem.getItemId())) {
					return;
				}

				Item item = this.baseHibernateService.findEntityById(Item.class, purchaseItem.getItemId());
				if (item == null) {
					return;
				}

				if (purchaseItem.getPurchaseArrival() == null || purchaseItem.getPurchaseArrival().getId() == null)
					return;
				PurchaseArrival purchaseArrival = this.baseHibernateService.findEntityById(PurchaseArrival.class, purchaseItem.getPurchaseArrival().getId());
				if (purchaseArrival == null)
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
				purchaseItem.genTatalFee();

				if (item.getSupplierId() != null) {
					Supplier supplier = this.baseHibernateService.findEntityById(Supplier.class, item.getSupplierId());
					if (supplier != null) {
						purchaseItem.setSupplier(supplier.getName());
					}
				}

				purchaseItem.setProject(purchaseArrival.getProject());
				//receivingAddress

				purchaseItem.setCode(VixUUID.getUUID());
				//处理groupCode，与主数据相同的groupCode，对应自身数据的billType,billCode				
				purchaseItem.setGroupCode(this.genNewGroupCode(BillType.PUR_ARRIVAL_ITEM, purchaseItem.getCode(), purchaseArrival.getGroupCode()));

				this.baseHibernateService.save(purchaseItem);
			}
			renderText(String.valueOf(this.purchaseItem.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePurchaseOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseArrivalItems item = this.baseHibernateService.findEntityById(PurchaseArrivalItems.class, this.id);
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

	/** 跳转到选择采购计划单页面 */
	public String goChoosePurchaseOrders() {
		return "goChoosePurchaseOrders";
	}

	/** 获取采购计划列表数据 */
	public String goPurchaseOrdersList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			// 按状态
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			this.addTimeLimitToParam(params);
			Pager pager = purchaseArrivalController.goPurchaseOrders(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseOrdersList";
	}

	public String goChoosePurchaseArrival() {
		return "goChoosePurchaseArrival";
	}

	public String goPurchaseArrivalList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			this.addTimeLimitToParam(params);
			// 按最近使用
			Pager pager = purchaseOrderController.goPurchaseArrivalPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseArrivalList";
	}

	public String getPurchaseOrdersItemList() {
		String purchaseOrderid = getRequestParameter("purchaseOrderid");
		try {
			if (null != purchaseOrderid && !"".equals(purchaseOrderid)) {
				purchaseOrder = purchaseArrivalController.doListPurchaseOrderById(purchaseOrderid);
				Set<PurchaseOrderLineItem> purchaseOrderItemsTemp = purchaseOrder.getPurchaseOrderLineItems();
				if (purchaseOrderItemsTemp.size() > 0) {
					purchaseOrderItemsList = new ArrayList<PurchaseOrderLineItem>(purchaseOrderItemsTemp);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getPurchaseOrdersItemList";
	}

	public String converterOrderToArrival() {
		String purchaseOrderids = getRequestParameter("purchaseOrderid");
		try {
			purchaseArrival = purchaseArrivalController.doListEntityById(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] purchaseOrderidArr = purchaseOrderids.split(",");
		if (purchaseOrderidArr != null && !"".equals(purchaseOrderidArr) && purchaseOrderidArr.length > 0) {
			for (String purchaseOrderid : purchaseOrderidArr) {
				if (purchaseOrderid != null && !"".equals(purchaseOrderid)) {
					// 根据id查询采购订单
					PurchaseOrder purchaseOrderTemp = purchaseArrivalController.doListPurchaseOrderById(purchaseOrderid);
					if (purchaseArrival != null) {
						try {
							purchaseArrivalController.converterOrderToArrival(purchaseArrival, purchaseOrderTemp);
							renderText(SAVE_SUCCESS);
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowPurchaseArrival() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseArrival = purchaseArrivalController.findEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseArrival";
	}

	public String goPrintPurchaseArrival() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseArrival = purchaseArrivalController.findEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintPurchaseArrival";
	}

	public String goShowBeforeAndAfterPurchaseArrival() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseArrival = purchaseArrivalController.findEntityById(id);
				if (purchaseArrival != null && purchaseArrival.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseArrival.getCreateTime()));
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseArrival = (PurchaseArrival) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseArrival.getCreateTime()), params, purchaseArrival, "before");
						} else if ("after".equals(str)) {
							purchaseArrival = (PurchaseArrival) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseArrival.getCreateTime()), params, purchaseArrival, "after");
						}
					}
					if (purchaseArrival == null || StringUtils.isEmpty(purchaseArrival.getId())) {
						purchaseArrival = purchaseArrivalController.findEntityById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseArrival";
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

	public PurchaseArrivalItems getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseArrivalItems purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public String getPurchaseType() {
		return purchaseType;
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

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public PurchaseOrderLineItem getPurchaseOrderItems() {
		return purchaseOrderItems;
	}

	public void setPurchaseOrderItems(PurchaseOrderLineItem purchaseOrderItems) {
		this.purchaseOrderItems = purchaseOrderItems;
	}

	public List<PurchaseOrderLineItem> getPurchaseOrderItemsList() {
		return purchaseOrderItemsList;
	}

	public void setPurchaseOrderItemsList(List<PurchaseOrderLineItem> purchaseOrderItemsList) {
		this.purchaseOrderItemsList = purchaseOrderItemsList;
	}
}
