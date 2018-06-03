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

package com.vix.purchase.apply.action;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.apply.entity.PurchaseApplyDetails;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.purchase.apply.controller.PurchaseApplyController;
import com.vix.purchase.share.PurchaseAction;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-24
 */
@Controller
@Scope("prototype")
public class PurchaseApplyAction extends PurchaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchasePlan.class);
	/** 注入service */
	@Autowired
	private PurchaseApplyController purchaseApplyController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDataAccuracy dataAccuracy;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private String id;
	private String purchaseId;
	private PurchaseApply purchaseApply;
	private String pageNo;

	/** 币种集合 */
	private List<CurrencyType> currencyTypeList;
	/** 申请集合 */
	private List<PurchaseApply> purchaseApplyList;

	PurchaseApplyDetails purchaseApplyItem;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String purchaseType;

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

			this.indexEntityList = this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseApply.class, params).getResultList();
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

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");
			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

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
			String createTime = getRequestParameter("createTime");
			if (StrUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.EQUAL, sdf.parse(createTime));
			}
			this.baseHibernateService.findPagerByHqlConditions(pager, PurchaseApply.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchaseApply purchaseApply = purchaseApplyController.doListEntityById(id);
			if (null != purchaseApply) {
				//purchaseApplyController.doDeleteByEntity(purchaseApply);
				this.setEntityDeleteValue(purchaseApply);
				this.baseHibernateService.update(purchaseApply);
				renderText("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseApply = purchaseApplyController.doListEntityById(id);
			} else {
				purchaseApply = new PurchaseApply();
				purchaseApply.setCode(autoCreateCode.getBillNO(BillType.PUR_APPLY));
				purchaseApply.setPurchasePerson(this.currentUserName());
				purchaseApply.setCreateTime(new Date());
				//				purchaseApply = purchaseApplyController.doSavePurchaseApply(purchaseApply);

				if (SecurityUtil.getCurrentEmpId() != null && !"0".equals(SecurityUtil.getCurrentEmpId())) {
					Employee employee = this.baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						OrganizationUnit org = employee.getOrganizationUnit();
						if (org != null) {
							purchaseApply.setPurchaseOrgId(org.getId());
							purchaseApply.setPurchaseOrg(org.getFs());
						}
					}
				}
			}
			currencyTypeList = purchaseApplyController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			purchaseApply.setPinyin(ChnToPinYin.getPYString(purchaseApply.getName()));

			if (StringUtils.isEmpty(purchaseApply.getId())) {
				//新建时处理groupCode
				purchaseApply.setGroupCode(this.genNewGroupCode(BillType.PUR_APPLY, purchaseApply.getCode(), purchaseApply.getGroupCode()));
			}
			//处理修改留痕
			billMarkProcessController.processMark(purchaseApply, updateField);
			initEntityBaseController.initEntityBaseAttribute(purchaseApply);
			purchaseApply = purchaseApplyController.doSavePurchaseApply(purchaseApply, null);

			for (PurchaseApplyDetails detailItem : purchaseApply.getPurchaseApplyDetails()) {
				detailItem.setProject(purchaseApply.getProject());
				detailItem.setReceivingWarehouse(purchaseApply.getRecieveWarehouse());
			}

			renderText(purchaseApply.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				pager = purchaseApplyController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null != purchasePerson && !"".equals(purchasePerson)) {
					params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
				}
				pager = purchaseApplyController.goSingleList(params, getPager());
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = purchaseApplyController.goSubSingleList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 获取采购申请明细json数据 */
	public void getPurchaseApplyItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseApply pa = purchaseApplyController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseApplyDetails>(pa.getPurchaseApplyDetails()), pa.getPurchaseApplyDetails().size(), "purchaseApply");
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

	public String goSaveOrUpdatePurchaseApplyItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseApplyItem = this.baseHibernateService.findEntityById(PurchaseApplyDetails.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseApplyItem";
	}

	public void saveOrUpdatePurchaseApplyItem() {
		try {

			if (this.purchaseApplyItem != null && StringUtils.isNotEmpty(this.purchaseApplyItem.getId()) && !"0".equals(this.purchaseApplyItem.getId())) {
				//update
				PurchaseApplyDetails paItem = this.baseHibernateService.findEntityById(PurchaseApplyDetails.class, this.purchaseApplyItem.getId());
				paItem.setItemId(purchaseApplyItem.getItemId());
				paItem.setItemName(purchaseApplyItem.getItemName());
				paItem.setItemCode(purchaseApplyItem.getItemCode());
				paItem.setSpecification(purchaseApplyItem.getSpecification());
				paItem.setPrice(purchaseApplyItem.getPrice());

				paItem.setPrice(this.dataAccuracy.getAmountDecimal(paItem.getPrice()));
				paItem.setAmount(purchaseApplyItem.getAmount());
				paItem.setTaxRate(purchaseApplyItem.getTaxRate());
				paItem.genTatalFee();

				paItem.setTotal(this.dataAccuracy.getAmountDecimal(paItem.getTotal()));
				paItem.setRequireTime(purchaseApplyItem.getRequireTime());
				paItem.setSkuCode(purchaseApplyItem.getSkuCode());

				this.baseHibernateService.merge(paItem);
			} else {
				if (purchaseApplyItem == null || purchaseApplyItem.getItemId() == null || "0".equals(purchaseApplyItem.getItemId())) {
					return;
				}

				Item item = this.baseHibernateService.findEntityById(Item.class, purchaseApplyItem.getItemId());
				if (item == null) {
					return;
				}

				if (purchaseApplyItem.getPurchaseApply() == null || purchaseApplyItem.getPurchaseApply().getId() == null || "0".equals(purchaseApplyItem.getPurchaseApply().getId()))
					return;
				PurchaseApply purchaseapply = this.baseHibernateService.findEntityById(PurchaseApply.class, purchaseApplyItem.getPurchaseApply().getId());
				if (purchaseapply == null)
					return;

				if (StrUtils.isEmpty(purchaseApplyItem.getBarCode()))
					purchaseApplyItem.setBarCode(item.getBarCode());
				if (StrUtils.isEmpty(purchaseApplyItem.getSkuCode()))
					purchaseApplyItem.setSkuCode(item.getSkuCode());

				purchaseApplyItem.setItemCode(item.getCode());
				purchaseApplyItem.setItemName(item.getName());
				purchaseApplyItem.setItemType(item.getItemType());
				if (StrUtils.isEmpty(purchaseApplyItem.getUnit()))
					purchaseApplyItem.setUnit(item.getSaleUnit());
				//purchaseApplyItem.setTaxRate(item.getInTax());
				purchaseApplyItem.genTatalFee();

				if (item.getSupplierId() != null) {
					Supplier supplier = this.baseHibernateService.findEntityById(Supplier.class, item.getSupplierId());
					if (supplier != null)
						purchaseApplyItem.setSupplier(supplier.getName());
				}

				//				purchaseApplyItem
				purchaseApplyItem.setProject(purchaseapply.getProject());
				//warehouse
				purchaseApplyItem.setReceivingWarehouse(purchaseapply.getRecieveWarehouse());
				//receivingAddress

				purchaseApplyItem.setCode(VixUUID.getUUID());
				//处理groupCode，与主数据相同的groupCode，对应自身数据的billType,billCode				
				purchaseApplyItem.setGroupCode(this.genNewGroupCode(BillType.PUR_APPLY_ITEM, purchaseApplyItem.getCode(), purchaseapply.getGroupCode()));

				this.baseHibernateService.save(purchaseApplyItem);
			}
			renderText(String.valueOf(this.purchaseApplyItem.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePurchaseApplyItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseApplyDetails pad = this.baseHibernateService.findEntityById(PurchaseApplyDetails.class, this.id);
				if (pad != null) {
					this.baseHibernateService.deleteByEntity(pad);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取明细数据行数 */
	public void getPurchaseApplyDetailsCount() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			if (null != id && !"".equals(id)) {
				PurchaseApply pa = purchaseApplyController.findEntityById(id);
				if (null != pa && null != pa.getPurchaseApplyDetails()) {
					double totalTax = 0;
					double totalFee = 0;
					for (PurchaseApplyDetails detailItem : pa.getPurchaseApplyDetails()) {
						Double price = detailItem.getPrice();
						Double amount = detailItem.getAmount();
						if (price != null && price > 0 && amount != null && amount > 0) {
							Double tax = detailItem.getTaxRate();
							if (tax == null)
								tax = 0d;

							double itemTotal = price * amount;
							double itemTax = itemTotal * (tax / 100);

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

	public List<BizType> listPurchaseApplyBizType() {
		return this.listBizType();
	}

	/** 获取附件json数据 */
	public void getAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseApply pa = purchaseApplyController.findEntityById(id);
				json = convertListToJson(new ArrayList<Attachments>(pa.getAttachments()), pa.getAttachments().size(), "purchaseOrder");
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

	public String addAttachments() {
		return "addAttachments";
	}

	/** 上传附件 */
	public void uploadAttachments() {
		try {
			if (null != purchaseId && this.fileToUpload != null) {
				PurchaseApply pa = purchaseApplyController.findEntityById(this.purchaseId);
				if (pa == null)
					return;

				String[] pathAndName = this.saveUploadFile("purchase");
				Attachments atts = new Attachments();
				atts.setName(pathAndName[1]);
				atts.setPath(pathAndName[0]);
				atts.setPurchaseApply(pa);
				purchaseApplyController.mergeAttachments(atts);
				renderText("success");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteAttachment() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				Attachments atts = this.baseHibernateService.findEntityById(Attachments.class, this.id);
				if (atts != null) {
					File file = new File(atts.getPath() + atts.getName());
					if (file.exists()) {
						file.delete();
					}
					this.baseHibernateService.deleteByEntity(atts);
					renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String downloadAttachment() {
		try {
			Attachments att = this.baseHibernateService.findEntityById(Attachments.class, this.id);
			String fileName = att.getName();
			String filePath = att.getPath();
			String title = att.getName();

			this.setOriFileName(title);

			String downloadFile = filePath + fileName;
			this.setInputStream(new FileInputStream(downloadFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadAttachment";
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowPurchaseApply() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseApply = purchaseApplyController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseApply";
	}

	public String goPrintPurchaseApply() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseApply = purchaseApplyController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintPurchaseApply";
	}

	/**
	 * 
	 * @return
	 */
	public String goShowBeforeAndAfterPurchaseApply() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseApply = purchaseApplyController.doListEntityById(id);
				if (purchaseApply != null && purchaseApply.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseApply.getCreateTime()));
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseApply = (PurchaseApply) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseApply.getCreateTime()), params, purchaseApply, "before");
						} else if ("after".equals(str)) {
							purchaseApply = (PurchaseApply) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseApply.getCreateTime()), params, purchaseApply, "after");
						}
					}
					if (purchaseApply == null || StringUtils.isEmpty(purchaseApply.getId())) {
						purchaseApply = purchaseApplyController.doListEntityById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseApply";
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}

	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
	}

	public List<PurchaseApply> getPurchaseApplyList() {
		return purchaseApplyList;
	}

	public void setPurchaseApplyList(List<PurchaseApply> purchaseApplyList) {
		this.purchaseApplyList = purchaseApplyList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
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

	public PurchaseApplyDetails getPurchaseApplyItem() {
		return purchaseApplyItem;
	}

	public void setPurchaseApplyItem(PurchaseApplyDetails purchaseApplyItem) {
		this.purchaseApplyItem = purchaseApplyItem;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
}
