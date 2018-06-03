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

package com.vix.purchase.pursreturn.action;

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

import com.vix.common.billtype.BillType;
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
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturnItems;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.order.controller.PurchaseOrderController;
import com.vix.purchase.pursreturn.controller.PurchaseReturnController;
import com.vix.purchase.share.PurchaseAction;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class PurchaseReturnAction extends PurchaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchaseOrder.class);
	/** 注入service */
	@Autowired
	private PurchaseReturnController purchaseReturnController;
	@Autowired
	private IDataAccuracy dataAccuracy;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private String id;
	private PurchaseReturn purchaseReturn;
	private String pageNo;

	private List<PurchaseReturn> purchaseReturnList;

	@Autowired
	private PurchaseOrderController purchaseOrderController;
	//币种集合
	private List<CurrencyType> currencyTypeList;
	//交货地址集合
	private List<ReceivedAddress> receivedAddressesList;

	PurchaseReturnItems purchaseItem;

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

			this.indexEntityList = this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseReturn.class, params).getResultList();
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
			String supplierName = getDecodeRequestParameter("supplierName");
			if (supplierName != null && !"".equals(supplierName)) {
				params.put("supplierName," + SearchCondition.ANYLIKE, supplierName);
			}
			String requireDepartment = getDecodeRequestParameter("requireDepartment");
			if (requireDepartment != null && !"".equals(requireDepartment)) {
				params.put("requireDepartment," + SearchCondition.EQUAL, requireDepartment);
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

			this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseReturn.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseReturn = purchaseReturnController.doListEntityById(id);
			} else {
				if (StrUtils.isNotEmpty(this.purchaseType)) {
					purchaseReturn = (PurchaseReturn) this.copyPurchaseEntity(this.purchaseType, this.purchaseId, "return");
				} else {
					purchaseReturn = new PurchaseReturn();
					purchaseReturn.setCode(autoCreateCode.getBillNO(BillType.PUR_RETURN));
					purchaseReturn.setCreateTime(new Date());
					purchaseReturn.setPurchasePerson(this.currentUserName());
					purchaseReturn.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));

					if (SecurityUtil.getCurrentEmpId() != null && !"0".equals(SecurityUtil.getCurrentEmpId())) {
						Employee employee = this.baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if (employee != null) {
							OrganizationUnit org = employee.getOrganizationUnit();
							if (org != null) {
								purchaseReturn.setPurchaseOrgId(org.getId());
								purchaseReturn.setPurchaseOrg(org.getFs());
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
			//			purchaseReturn.setStatus("S1");

			purchaseReturn.setPinyin(ChnToPinYin.getPYString(purchaseReturn.getName()));

			if (purchaseReturn.getId() == null || "0".equals(purchaseReturn.getId())) {
				//新建时处理groupCode
				purchaseReturn.setGroupCode(this.genNewGroupCode(BillType.PUR_RETURN, purchaseReturn.getCode(), purchaseReturn.getGroupCode()));
			}

			purchaseReturn.setTotalAmount(this.dataAccuracy.getAmountDecimal(purchaseReturn.getTotalAmount()));

			purchaseReturn.setCreateTime(new Date());
			//处理修改留痕
			billMarkProcessController.processMark(purchaseReturn, updateField);
			purchaseReturn = purchaseReturnController.doSavePurchaseInbound(purchaseReturn, null);

			renderText(String.valueOf(purchaseReturn.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchaseReturn purchaseReturn = purchaseReturnController.doListEntityById(id);

			if (null != purchaseReturn) {
				this.setEntityDeleteValue(purchaseReturn);
				this.baseHibernateService.update(purchaseReturn);
				renderText("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取采购订单明细json数据 */
	public void getPurchaseReturnItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseReturn pr = purchaseReturnController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseReturnItems>(pr.getPurchaseReturnItems()), pr.getPurchaseReturnItems().size(), "purchaseInquire");
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
	public void getPurchaseReturnItemsCount() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			if (null != id && !"".equals(id)) {
				PurchaseReturn pa = this.baseHibernateService.findEntityById(PurchaseReturn.class, id);
				if (null != pa && null != pa.getPurchaseReturnItems()) {
					double totalTax = 0;
					double totalFee = 0;
					for (PurchaseReturnItems detailItem : pa.getPurchaseReturnItems()) {
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
		try {
			Integer count = 0;
			if (null != id && !"".equals(id)) {
				PurchaseReturn pr = purchaseReturnController.findEntityById(id);
				if (null != pr && null != pr.getPurchaseReturnItems()) {
					count = pr.getPurchaseReturnItems().size();
				}
			}
			renderJson(count.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("0");
		}
	}

	public String goAddPurchaseReturnItems() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				this.purchaseItem = this.baseHibernateService.findEntityById(PurchaseReturnItems.class, id);
			}
			receivedAddressesList = purchaseOrderController.findReceivedAddressIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddPurchaseReturnItems";
	}

	public void saveOrUpdatePurchaseReturnItem() {
		try {

			if (this.purchaseItem != null && StringUtils.isNotEmpty(this.purchaseItem.getId())) {
				//update
				PurchaseReturnItems paItem = this.baseHibernateService.findEntityById(PurchaseReturnItems.class, this.purchaseItem.getId());
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

				if (purchaseItem.getPurchaseReturn() == null || StringUtils.isEmpty(purchaseItem.getPurchaseReturn().getId()))
					return;
				PurchaseReturn puchaseReturn = this.baseHibernateService.findEntityById(PurchaseReturn.class, purchaseItem.getPurchaseReturn().getId());
				if (puchaseReturn == null)
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
				//purchaseItem.setTaxRate(item.getInTax());
				purchaseItem.genTatalFee();

				if (item.getSupplierId() != null) {
					Supplier supplier = this.baseHibernateService.findEntityById(Supplier.class, item.getSupplierId());
					if (supplier != null) {
						purchaseItem.setSupplier(supplier.getName());
					}
				}

				purchaseItem.setCode(VixUUID.getUUID());
				//处理groupCode，与主数据相同的groupCode，对应自身数据的billType,billCode				
				purchaseItem.setGroupCode(this.genNewGroupCode(BillType.PUR_RETURN_ITEM, purchaseItem.getCode(), puchaseReturn.getGroupCode()));

				this.baseHibernateService.save(purchaseItem);
			}
			renderText(String.valueOf(this.purchaseItem.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePurchaseReturnItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseReturnItems item = this.baseHibernateService.findEntityById(PurchaseReturnItems.class, this.id);
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

	public List<BizType> listPurchaseReturnBizType() {
		return this.listBizType();
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowPurchaseReturn() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseReturn = purchaseReturnController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseReturn";
	}

	public String goPrintPurchaseReturn() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseReturn = purchaseReturnController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintPurchaseReturn";
	}

	public String goShowBeforeAndAfterPurchaseReturn() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseReturn = purchaseReturnController.doListEntityById(id);
				if (purchaseReturn != null && purchaseReturn.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseReturn.getCreateTime()));
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseReturn = (PurchaseReturn) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseReturn.getCreateTime()), params, purchaseReturn, "before");
						} else if ("after".equals(str)) {
							purchaseReturn = (PurchaseReturn) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseReturn.getCreateTime()), params, purchaseReturn, "after");
						}
					}
					if (purchaseReturn == null || StringUtils.isEmpty(purchaseReturn.getId())) {
						purchaseReturn = purchaseReturnController.doListEntityById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseReturn";
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
	 * @return the purchaseReturn
	 */
	public PurchaseReturn getPurchaseReturn() {
		return purchaseReturn;
	}

	/**
	 * @param purchaseReturn
	 *            the purchaseReturn to set
	 */
	public void setPurchaseReturn(PurchaseReturn purchaseReturn) {
		this.purchaseReturn = purchaseReturn;
	}

	/**
	 * @return the pageNo
	 */
	public String getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the purchaseReturnList
	 */
	public List<PurchaseReturn> getPurchaseReturnList() {
		return purchaseReturnList;
	}

	/**
	 * @param purchaseReturnList
	 *            the purchaseReturnList to set
	 */
	public void setPurchaseReturnList(List<PurchaseReturn> purchaseReturnList) {
		this.purchaseReturnList = purchaseReturnList;
	}

	/**
	 * @return the currencyTypeList
	 */
	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	/**
	 * @param currencyTypeList
	 *            the currencyTypeList to set
	 */
	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	/**
	 * @return the receivedAddressesList
	 */
	public List<ReceivedAddress> getReceivedAddressesList() {
		return receivedAddressesList;
	}

	/**
	 * @param receivedAddressesList
	 *            the receivedAddressesList to set
	 */
	public void setReceivedAddressesList(List<ReceivedAddress> receivedAddressesList) {
		this.receivedAddressesList = receivedAddressesList;
	}

	/**
	 * @return the purchaseItem
	 */
	public PurchaseReturnItems getPurchaseItem() {
		return purchaseItem;
	}

	/**
	 * @param purchaseItem
	 *            the purchaseItem to set
	 */
	public void setPurchaseItem(PurchaseReturnItems purchaseItem) {
		this.purchaseItem = purchaseItem;
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

	/**
	 * @return the purchaseType
	 */
	public String getPurchaseType() {
		return purchaseType;
	}

	/**
	 * @param purchaseType
	 *            the purchaseType to set
	 */
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

}
