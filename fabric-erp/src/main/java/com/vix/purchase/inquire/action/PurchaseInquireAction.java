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

package com.vix.purchase.inquire.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.billtype.BillType;
import com.vix.common.code.AutoCreateCode;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquiryDetail;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.inquire.controller.PurchaseInquireController;
import com.vix.purchase.share.PurchaseAction;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class PurchaseInquireAction extends PurchaseAction {

	private static final long serialVersionUID = 1L;
	/** 注入service */
	@Autowired
	private PurchaseInquireController purchaseInquireController;
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
	private PurchaseInquire purchaseInquire;
	private String pageNo;
	/** 币种集合 */
	private List<CurrencyType> currencyTypeList;
	/** 采购询价集合 */
	private List<PurchaseInquire> purchaseInquireList;

	private String purchaseId;
	private String purchaseType;

	private PurchaseInquiryDetail purchaseInquiryDetail;

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

			this.indexEntityList = this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params).getResultList();
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

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			String status = getRequestParameter("status");
			if (StrUtils.isNotEmpty(status))
				params.put("status," + SearchCondition.EQUAL, status);
			String code = getRequestParameter("code");
			if (StrUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String name = getDecodeRequestParameter("name");
			if (StrUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (StrUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.EQUAL, purchasePerson);
			}
			String appDate = getRequestParameter("appDate");
			if (StrUtils.isNotEmpty(appDate)) {
				params.put("appDate," + SearchCondition.EQUAL, formatStringToDate(appDate));
			}
			this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInquire = purchaseInquireController.doListPurchaseInquireById(id);
			} else {
				if (StrUtils.isNotEmpty(purchaseType)) {
					purchaseInquire = (PurchaseInquire) this.copyPurchaseEntity(this.purchaseType, this.purchaseId, "inquire");
				} else {
					purchaseInquire = new PurchaseInquire();
					purchaseInquire.setCode(autoCreateCode.getBillNO(BillType.PUR_INQUERY));
					purchaseInquire.setAppDate(new Date());
					purchaseInquire.setPurchasePerson(this.currentUserName());
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			currencyTypeList = purchaseInquireController.findCurrencyTypeIndex(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goShowPurchaseInquire() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInquire = purchaseInquireController.doListPurchaseInquireById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseInquire";
	}

	public String goPrintPurchaseInquire() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInquire = purchaseInquireController.doListPurchaseInquireById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintPurchaseInquire";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			if (purchaseInquire != null) {
				// 设置采购订单状态为未审批
				//			purchaseInquire.setStatus("S1");
				purchaseInquire.setChineseCharacter(ChnToPinYin.getPYString(purchaseInquire.getName()));
				if (StringUtils.isEmpty(purchaseInquire.getId()) || "0".equals(purchaseInquire.getId())) {
					//新建时处理groupCode
					purchaseInquire.setGroupCode(this.genNewGroupCode(BillType.PUR_INQUERY, purchaseInquire.getCode(), purchaseInquire.getGroupCode()));
				}
				purchaseInquire.setTotal(this.dataAccuracy.getAmountDecimal(purchaseInquire.getTotal()));
				initEntityBaseController.initEntityBaseAttribute(purchaseInquire);
				//处理修改留痕
				billMarkProcessController.processMark(purchaseInquire, updateField);
				purchaseInquire = purchaseInquireController.doSavePurchaseInquire(purchaseInquire, null);
				renderText(String.valueOf(purchaseInquire.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BizType> listPurchaseInquireBizType() {
		return this.listBizType();
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchaseInquire purchaseInquire = purchaseInquireController.doListPurchaseInquireById(id);
			if (null != purchaseInquire) {
				//purchaseApplyController.doDeleteByEntity(purchaseApply);
				this.setEntityDeleteValue(purchaseInquire);
				this.baseHibernateService.update(purchaseInquire);
				renderText("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePurchaseInquireItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseInquiryDetail pid = this.baseHibernateService.findEntityById(PurchaseInquiryDetail.class, this.id);
				if (pid != null) {
					this.baseHibernateService.deleteByEntity(pid);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPurchaseInquireDetailsCount() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseInquire pi = this.baseHibernateService.findEntityById(PurchaseInquire.class, id);
				if (null != pi && null != pi.getPurchaseInquiryDetails()) {
					double totalTax = 0;
					double totalFee = 0;
					for (PurchaseInquiryDetail detailItem : pi.getPurchaseInquiryDetails()) {
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
			renderJson("0");
		}
	}

	public String goSaveOrUpdatePurchaseInquireItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInquiryDetail = this.baseHibernateService.findEntityById(PurchaseInquiryDetail.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseInquireItem";
	}

	public void saveOrUpdatePurchaseInquireItem() {
		try {

			if (purchaseInquiryDetail != null && StringUtils.isNotEmpty(purchaseInquiryDetail.getId())) {
				//update
				PurchaseInquiryDetail piItem = this.baseHibernateService.findEntityById(PurchaseInquiryDetail.class, this.purchaseInquiryDetail.getId());
				if (piItem != null) {
					piItem.setItemId(purchaseInquiryDetail.getItemId());
					piItem.setItemName(purchaseInquiryDetail.getItemName());
					piItem.setItemCode(purchaseInquiryDetail.getItemCode());
					piItem.setSpecification(purchaseInquiryDetail.getSpecification());
					piItem.setPrice(purchaseInquiryDetail.getPrice());

					piItem.setAmount(purchaseInquiryDetail.getAmount());
					piItem.setTaxRate(purchaseInquiryDetail.getTaxRate());
					piItem.genTatalFee();

					piItem.setTotal(this.dataAccuracy.getAmountDecimal(piItem.getTotal()));
					piItem.setRequireTime(purchaseInquiryDetail.getRequireTime());
					piItem.setSkuCode(purchaseInquiryDetail.getSkuCode());

					this.baseHibernateService.merge(piItem);
				}
			} else {
				if (purchaseInquiryDetail == null || purchaseInquiryDetail.getItemId() == null || "".equals(purchaseInquiryDetail.getItemId()) || "0".equals(purchaseInquiryDetail.getItemId())) {
					return;
				}

				Item item = this.baseHibernateService.findEntityById(Item.class, purchaseInquiryDetail.getItemId());
				if (item == null) {
					return;
				}

				if (purchaseInquiryDetail.getPurchaseInquire() == null || StringUtils.isEmpty(purchaseInquiryDetail.getPurchaseInquire().getId()))
					return;
				PurchaseInquire purchaseInquire = this.baseHibernateService.findEntityById(PurchaseInquire.class, purchaseInquiryDetail.getPurchaseInquire().getId());
				if (purchaseInquire == null)
					return;

				if (StrUtils.isEmpty(purchaseInquiryDetail.getBarCode()))
					purchaseInquiryDetail.setBarCode(item.getBarCode());
				if (StrUtils.isEmpty(purchaseInquiryDetail.getSkuCode()))
					purchaseInquiryDetail.setSkuCode(item.getSkuCode());

				purchaseInquiryDetail.setItemCode(item.getCode());
				purchaseInquiryDetail.setItemName(item.getName());
				purchaseInquiryDetail.setItemType(item.getItemType());
				if (StrUtils.isEmpty(purchaseInquiryDetail.getUnit()))
					purchaseInquiryDetail.setUnit(item.getSaleUnit());
				//purchaseItem.setTaxRate(item.getInTax());
				purchaseInquiryDetail.genTatalFee();

				if (item != null && StringUtils.isNotEmpty(item.getSupplierId())) {
					Supplier supplier = this.baseHibernateService.findEntityById(Supplier.class, item.getSupplierId());
					if (supplier != null)
						purchaseInquiryDetail.setSupplier(supplier.getName());
				}

				purchaseInquiryDetail.setProject(purchaseInquire.getProject());
				//warehouse
				purchaseInquiryDetail.setReceivingWarehouse(purchaseInquire.getRecieveWarehouse());
				//receivingAddress

				purchaseInquiryDetail.setCode(VixUUID.getUUID());
				//处理groupCode，与主数据相同的groupCode，对应自身数据的billType,billCode				
				purchaseInquiryDetail.setGroupCode(this.genNewGroupCode(BillType.PUR_INQUERY_ITEM, purchaseInquiryDetail.getCode(), purchaseInquire.getGroupCode()));

				this.baseHibernateService.save(purchaseInquiryDetail);
			}
			renderText(String.valueOf(this.purchaseInquiryDetail.getId()));
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

	/** 获取采购订单明细json数据 */
	public void getPurchaseInquiryDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseInquire pi = purchaseInquireController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseInquiryDetail>(pi.getPurchaseInquiryDetails()), pi.getPurchaseInquiryDetails().size());
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

	public String goShowBeforeAndAfterPurchaseInquire() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseInquire = purchaseInquireController.doListPurchaseInquireById(id);
				if (purchaseInquire != null && purchaseInquire.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseInquire.getCreateTime()));
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseInquire = (PurchaseInquire) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseInquire.getCreateTime()), params, purchaseInquire, "before");
						} else if ("after".equals(str)) {
							purchaseInquire = (PurchaseInquire) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseInquire.getCreateTime()), params, purchaseInquire, "after");
						}
					}
					if (purchaseInquire == null || StringUtils.isEmpty(purchaseInquire.getId())) {
						purchaseInquire = purchaseInquireController.doListPurchaseInquireById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseInquire";
	}

	public String goSearch() {
		return "goSearch";
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
	 * @return the purchaseInquire
	 */
	public PurchaseInquire getPurchaseInquire() {
		return purchaseInquire;
	}

	/**
	 * @param purchaseInquire
	 *            the purchaseInquire to set
	 */
	public void setPurchaseInquire(PurchaseInquire purchaseInquire) {
		this.purchaseInquire = purchaseInquire;
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
	 * @return the purchaseInquireList
	 */
	public List<PurchaseInquire> getPurchaseInquireList() {
		return purchaseInquireList;
	}

	/**
	 * @param purchaseInquireList
	 *            the purchaseInquireList to set
	 */
	public void setPurchaseInquireList(List<PurchaseInquire> purchaseInquireList) {
		this.purchaseInquireList = purchaseInquireList;
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
	 * @return the purchaseInquiryDetail
	 */
	public PurchaseInquiryDetail getPurchaseInquiryDetail() {
		return purchaseInquiryDetail;
	}

	/**
	 * @param purchaseInquiryDetail
	 *            the purchaseInquiryDetail to set
	 */
	public void setPurchaseInquiryDetail(PurchaseInquiryDetail purchaseInquiryDetail) {
		this.purchaseInquiryDetail = purchaseInquiryDetail;
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

	public PurchaseInquiryDetail getPurchaseItem() {
		return purchaseInquiryDetail;
	}

	public void setPurchaseItem(PurchaseInquiryDetail purchaseItem) {
		this.purchaseInquiryDetail = purchaseItem;
	}
}
