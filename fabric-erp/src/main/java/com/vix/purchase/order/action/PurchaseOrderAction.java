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

package com.vix.purchase.order.action;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.DecimalFormat;
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
import com.vix.purchase.order.controller.PurchaseOrderController;
import com.vix.purchase.share.PurchaseAction;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-15
 */
@Controller
@Scope("prototype")
public class PurchaseOrderAction extends PurchaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchaseOrder.class);
	/** 注入service */
	@Autowired
	private PurchaseOrderController purchaseOrderController;
	@Autowired
	private AutoCreateCode autoCreateCode;
	@Autowired
	private IDataAccuracy dataAccuracy;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private String id;
	private PurchaseOrder purchaseOrder;
	// 采购订单明细
	private PurchaseOrderLineItem purchaseOrderLineItem;
	// 采购订单集合
	private List<PurchaseOrder> purchaseOrderList;
	// 采购订单明细集合
	private List<PurchaseOrderLineItem> purchaseOrderLineItems;
	// 交货地址集合
	private List<ReceivedAddress> receivedAddressesList;
	// 单据类型集合
	private List<OrderType> orderTypeList;
	// 业务类型集合
	private List<BizType> bizTypeList;
	// 币种集合
	private List<CurrencyType> currencyTypeList;

	// 采购计划
	private PurchasePlan purchasePlan;
	// 采购计划列表
	private List<PurchasePlan> purchasePlanList;
	// 采购计划明细
	private PurchasePlanItems purchasePlanItems;
	// 采购计划明细列表
	private List<PurchasePlanItems> purchasePlanItemsList;

	// 采购申请
	private PurchaseApply purchaseApply;
	// 采购申请列表
	private List<PurchaseApply> purchaseApplyList;
	// 采购申请明细
	private PurchaseApplyDetails purchaseApplyItems;
	// 采购申请明细列表
	private List<PurchaseApplyDetails> purchaseApplyItemsList;

	// 采购询价
	private PurchaseInquire purchaseInquire;
	// 采购询价列表
	private List<PurchaseInquire> purchaseInquireList;
	// 采购询价明细
	private PurchaseInquiryDetail purchaseInquireItems;
	// 采购询价明细列表
	private List<PurchaseInquiryDetail> purchaseInquireItemsList;

	// 供应商
	private String supplierId;
	private Supplier supplier;

	PurchaseOrderLineItem purchaseItem;

	private String purchaseId;
	private String purchaseType;

	ReceivedAddress address;
	DeliveryPlan delivery;
	PurchaseInvoice invoice;
	PriceConditions conditions;
	ApprovalOpinion approvalOpinion;

	public String orderAddressEdit() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				address = baseHibernateService.findEntityById(ReceivedAddress.class, id);
				if (address != null)
					purchaseId = address.getPurchaseOrder().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "orderAddressEdit";
	}

	public void orderAddressSave() {
		if (address != null) {
			try {
				if (StringUtils.isNotEmpty(address.getId()) && !"0".equals(address.getId())) {
					baseHibernateService.update(address);
				} else {
					purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
					address.setPurchaseOrder(purchaseOrder);

					baseHibernateService.save(address);
				}

				renderText(address.getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteOrderAddress() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ReceivedAddress entity = baseHibernateService.findEntityById(ReceivedAddress.class, id);
				if (entity != null) {
					baseHibernateService.deleteByEntity(entity);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String orderDeliveryEdit() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				delivery = baseHibernateService.findEntityById(DeliveryPlan.class, id);
				if (delivery != null)
					purchaseId = delivery.getPurchaseOrder().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "orderDeliveryEdit";
	}

	public void orderDeliverySave() {
		if (delivery != null) {
			try {
				if (StringUtils.isNotEmpty(delivery.getId()) && !"0".equals(delivery.getId())) {// if
																								// (delivery.getId()
																								// !=
																								// null)
																								// {
					baseHibernateService.update(delivery);
				} else {
					purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
					delivery.setPurchaseOrder(purchaseOrder);

					baseHibernateService.save(delivery);
				}

				renderText(delivery.getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteOrderDelivery() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				DeliveryPlan entity = baseHibernateService.findEntityById(DeliveryPlan.class, id);
				if (entity != null) {
					baseHibernateService.deleteByEntity(entity);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String orderConditionsEdit() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				conditions = baseHibernateService.findEntityById(PriceConditions.class, id);
				if (conditions != null)
					purchaseId = conditions.getPurchaseOrder().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "orderConditionsEdit";
	}

	public void orderConditionsSave() {
		if (conditions != null) {
			try {
				if (StringUtils.isNotEmpty(conditions.getId()) && !"0".equals(conditions.getId())) {
					baseHibernateService.update(conditions);
				} else {
					purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
					conditions.setPurchaseOrder(purchaseOrder);

					baseHibernateService.save(conditions);
				}

				renderText(conditions.getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteOrderConditions() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PriceConditions entity = baseHibernateService.findEntityById(PriceConditions.class, id);
				if (entity != null) {
					baseHibernateService.deleteByEntity(entity);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String orderInvoiceEdit() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				invoice = baseHibernateService.findEntityById(PurchaseInvoice.class, id);
				if (invoice != null)
					purchaseId = invoice.getPurchaseOrder().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			invoice = new PurchaseInvoice();
			try {
				purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
				if (purchaseOrder != null) {
					invoice.setSupplierName(purchaseOrder.getSupplierName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "orderInvoiceEdit";
	}

	public void orderInvoiceSave() {
		if (invoice != null) {
			try {
				if (StringUtils.isNotEmpty(invoice.getId()) && !"0".equals(invoice.getId())) {
					baseHibernateService.update(invoice);
				} else {
					purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
					invoice.setPurchaseOrder(purchaseOrder);

					baseHibernateService.save(invoice);
				}

				renderText(invoice.getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteOrderInvoice() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseInvoice entity = baseHibernateService.findEntityById(PurchaseInvoice.class, id);
				if (entity != null) {
					baseHibernateService.deleteByEntity(entity);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String addPOAttachments() {
		return "addPOAttachments";
	}

	public String downloadOrderAttachment() {
		try {
			Attachments att = baseHibernateService.findEntityById(Attachments.class, id);
			String fileName = att.getName();
			String filePath = att.getPath();
			String title = att.getName();
			// int idx = fileName.lastIndexOf(".");
			// if(idx!=-1)
			// {
			// title = title + fileName.substring(idx);
			// }

			setOriFileName(title);

			String downloadFile = filePath + fileName;
			setInputStream(new FileInputStream(downloadFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadOrderAttachment";
	}

	public String orderApprovalOpinionEdit() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				approvalOpinion = baseHibernateService.findEntityById(ApprovalOpinion.class, id);
				if (approvalOpinion != null)
					purchaseId = approvalOpinion.getPurchaseOrder().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			approvalOpinion = new ApprovalOpinion();
			approvalOpinion.setCheckPerson(currentUserName());
			approvalOpinion.setCheckPersonId(String.valueOf(SecurityUtil.getCurrentUserId()));
		}
		return "orderApprovalOpinionEdit";
	}

	public void orderApprovalOpinionSave() {
		if (approvalOpinion != null) {
			try {
				if (StringUtils.isNotEmpty(approvalOpinion.getId()) && !"0".equals(approvalOpinion.getId())) {
					baseHibernateService.update(approvalOpinion);
				} else {
					purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
					approvalOpinion.setCreateTime(new Date());
					approvalOpinion.setPurchaseOrder(purchaseOrder);

					baseHibernateService.save(approvalOpinion);
				}

				renderText(approvalOpinion.getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteOrderApprovalOpinion() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ApprovalOpinion entity = baseHibernateService.findEntityById(ApprovalOpinion.class, id);
				if (entity != null) {
					baseHibernateService.deleteByEntity(entity);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goShowPurchaseOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseOrder";
	}

	public String goPrintPurchaseOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintPurchaseOrder";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			addTimeLimitToParam(params);

			Pager pager = getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");
			pager.setPageSize(1000);

			indexEntityList = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// addTimeLimitToParam(params);
			// addQueryDeleteParam(params);

			String status = getRequestParameter("status");
			if (StrUtils.isNotEmpty(status))
				params.put("status," + SearchCondition.EQUAL, status);
			String code = getRequestParameter("code");
			if (StrUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String name = getDecodeRequestParameter("name");
			if (StrUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.EQUAL, name);
			}
			String supplierName = getDecodeRequestParameter("supplierName");
			if (StrUtils.isNotEmpty(supplierName)) {
				params.put("supplierName," + SearchCondition.EQUAL, supplierName);
			}
			String contactPerson = getDecodeRequestParameter("contactPerson");
			if (StrUtils.isNotEmpty(contactPerson)) {
				params.put("contactPerson," + SearchCondition.EQUAL, contactPerson);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (StrUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.EQUAL, purchasePerson);
			}
			String deliveryDate = getRequestParameter("deliveryDate");
			if (StrUtils.isNotEmpty(deliveryDate)) {
				params.put("deliveryDate," + SearchCondition.EQUAL, formatStringToDate(deliveryDate));
			}
			String postingDate = getRequestParameter("postingDate");
			if (StrUtils.isNotEmpty(postingDate)) {
				params.put("postingDate," + SearchCondition.EQUAL, formatStringToDate(postingDate));
			}
			String createTime = getRequestParameter("createTime");
			if (StrUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.EQUAL, formatStringToDate(createTime));
			}
			Pager pager = getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			// addAdvFilterAndSort(params, pager);

			baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至订单修改界面 */
	public String goOrderInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = purchaseOrderController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOrderInfo";
	}

	/** 跳转至订单修改界面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = purchaseOrderController.doListEntityById(id);
			} else {
				if (StrUtils.isNotEmpty(purchaseType)) {
					purchaseOrder = (PurchaseOrder) copyPurchaseEntity(purchaseType, purchaseId, "order");
				} else {
					purchaseOrder = new PurchaseOrder();
					purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
					purchaseOrder.setPurchasePerson(currentUserName());
					purchaseOrder.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));

					if (SecurityUtil.getCurrentEmpId() != null) {
						Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if (employee != null) {
							OrganizationUnit org = employee.getOrganizationUnit();
							if (org != null) {
								purchaseOrder.setPurchaseOrgId(org.getId());
								purchaseOrder.setPurchaseOrg(org.getFs());
							}
						}
					}
				}
			}
			// orderTypeList = purchaseOrderController.findOrderTypeIndex();
			// bizTypeList = purchaseOrderController.findBizTypeIndex();
			currencyTypeList = purchaseOrderController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面--计划 */
	public String goSaveOrUpdatePlan() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = purchaseOrderController.doListEntityById(id);
				logger.info("采购订单来源自采购计划-修改");
			} else {
				purchaseOrder = new PurchaseOrder();
				purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				purchaseOrder = purchaseOrderController.doSavePurchaseOrder(purchaseOrder);
				logger.info("采购订单来源自采购计划-新增");
			}
			orderTypeList = purchaseOrderController.findOrderTypeIndex();
			bizTypeList = purchaseOrderController.findBizTypeIndex();
			currencyTypeList = purchaseOrderController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePlan";
	}

	/** 跳转至用户修改页面--申请 */
	public String goSaveOrUpdateApply() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = purchaseOrderController.doListEntityById(id);
				logger.info("采购订单来源自采购申请-修改");
			} else {
				purchaseOrder = new PurchaseOrder();
				purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				purchaseOrder = purchaseOrderController.doSavePurchaseOrder(purchaseOrder);
				logger.info("采购订单来源自采购申请-新增");
			}
			orderTypeList = purchaseOrderController.findOrderTypeIndex();
			bizTypeList = purchaseOrderController.findBizTypeIndex();
			currencyTypeList = purchaseOrderController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateApply";
	}

	/** 跳转至用户修改页面--询价 */
	public String goSaveOrUpdateInquire() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = purchaseOrderController.doListEntityById(id);
				logger.info("采购订单来源自采购询价-修改");
			} else {
				purchaseOrder = new PurchaseOrder();
				purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				purchaseOrder = purchaseOrderController.doSavePurchaseOrder(purchaseOrder);
				logger.info("采购订单来源自采购申请-新增");
			}
			orderTypeList = purchaseOrderController.findOrderTypeIndex();
			bizTypeList = purchaseOrderController.findBizTypeIndex();
			currencyTypeList = purchaseOrderController.findCurrencyTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateInquire";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			if (null != purchaseOrder.getName()) {
				purchaseOrder.setChineseCharacter(ChnToPinYin.getPYString(purchaseOrder.getName()));
			}

			if (purchaseOrder.getId() == null || "0".equals(purchaseOrder.getId())) {
				// 新建时处理groupCode
				purchaseOrder.setGroupCode(genNewGroupCode(BillType.PUR_ORDER, purchaseOrder.getCode(), purchaseOrder.getGroupCode()));
			}

			// purchaseOrder.setTotal(dataAccuracy.getAmountDecimal(purchaseOrder.getTotal()));

			purchaseOrder.setCreateTime(new Date());
			// 处理修改留痕
			billMarkProcessController.processMark(purchaseOrder, updateField);
			purchaseOrder = purchaseOrderController.doSavePurchaseOrder(purchaseOrder, null, null, null, null, null);

			renderText(purchaseOrder.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到 全拼
	 * 
	 * @param src
	 * @return
	 */
	/*
	 * public static String getPingYin(String src) { char[] t1 = null; t1 =
	 * src.toCharArray(); String[] t2 = new String[t1.length];
	 * HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
	 * t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	 * t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	 * t3.setVCharType(HanyuPinyinVCharType.WITH_V); String t4 = ""; int t0 =
	 * t1.length; try { for (int i = 0; i < t0; i++) { // 判断是否为汉字字符 if
	 * (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) { t2
	 * = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3); t4 += t2[0]; } else {
	 * t4 += java.lang.Character.toString(t1[i]); } } return t4; } catch
	 * (BadHanyuPinyinOutputFormatCombination e1) { e1.printStackTrace(); }
	 * return t4; }
	 */

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchaseOrder purchaseOrder = purchaseOrderController.doListEntityById(id);

			if (null != purchaseOrder) {
				setEntityDeleteValue(purchaseOrder);
				baseHibernateService.update(purchaseOrder);
				renderText("success");
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BizType> listPurchaseOrderBizType() {
		return listBizType();
	}

	/** 获取附件json数据 */
	public void getAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<Attachments>(po.getAttachments()), po.getAttachments().size(), "purchaseOrder");
				logger.info("获取附件的json数据");
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
		logger.info("新增附件");
		return "addAttachments";
	}

	/** 上传附件 */
	public void uploadAttachments() {
		try {
			if (fileToUpload != null) {
				PurchaseOrder po = purchaseOrderController.findEntityById(purchaseId);
				if (po == null)
					return;

				String[] pathAndName = saveUploadFile("purchase");
				Attachments atts = new Attachments();
				atts.setName(pathAndName[1]);
				atts.setPath(pathAndName[0]);
				atts.setPurchaseOrder(po);
				purchaseOrderController.mergeAttachments(atts);
				renderText("success");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 删除附件 */
	public void deleteAttachments() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				Attachments atts = baseHibernateService.findEntityById(Attachments.class, id);
				if (atts != null) {
					File file = new File(atts.getPath() + atts.getName());
					if (file.exists()) {
						file.delete();
					}
					baseHibernateService.deleteByEntity(atts);
					renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** 获取采购订单明细json数据 */
	public void getPurchaseOrderLineItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				List<PurchaseOrderLineItem> orderLineItems = new ArrayList<PurchaseOrderLineItem>(po.getPurchaseOrderLineItems());
				json = convertListToJson(orderLineItems, orderLineItems.size(), "purchaseOrder");
				logger.info("获取采购订单明细json数据");
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

	/** 获取交货地址json数据 */
	public void getReceivedAddressJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<ReceivedAddress>(po.getReceivedAddresses()), po.getReceivedAddresses().size(), "purchaseOrder");
				logger.info("获取交货地址json数据");
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

	/** 获取发运计划json数据 */
	public void getDeliveryPlanJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<DeliveryPlan>(po.getDeliveryPlans()), po.getDeliveryPlans().size(), "purchaseOrder");
				logger.info("获取发运计划json数据");
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

	/** 获取采购发票json数据 */
	public void getPurchaseInvoiceJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseInvoice>(po.getPurchaseInvoices()), po.getPurchaseInvoices().size(), "purchaseOrder");
				logger.info("获取采购发票json数据");
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

	/** 获取价格条件json数据 */
	public void getPriceConditionsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<PriceConditions>(po.getPriceConditions()), po.getPriceConditions().size(), "purchaseOrder");
				logger.info("获取价格条件json数据");
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

	/** 获取审批json数据 */
	public void getApprovalOpinionJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = purchaseOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<ApprovalOpinion>(po.getApprovalOpinions()), po.getApprovalOpinions().size(), "purchaseOrder");
				logger.info("获取审批json数据");
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

	/** 获取列表数据 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			params.put("status," + SearchCondition.EQUAL, "3");
			Pager pager = purchaseOrderController.goSupplierList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}

	public String goChooseRadioSupplier() {
		return "goChooseRadioSupplier";
	}

	/** 获取列表数据 */
	public String goSubRadioInquireList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 搜索
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}

			// 不记得为什么要加这行，现在不支持同名参数两个，所以搜索时出问题
			// params.put("name," + SearchCondition.NOEQUAL, "null");
			Pager pager = purchaseOrderController.goSingleList3(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioInquireList";
	}

	public String goChooseRadioInquire() {
		return "goChooseRadioInquire";
	}

	public void deletePurchaseOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseOrderLineItem pid = baseHibernateService.findEntityById(PurchaseOrderLineItem.class, id);
				if (pid != null) {
					baseHibernateService.deleteByEntity(pid);
					renderText("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取发货地址数据行数 */
	public void getPurchaseOrderLineItemsCount() {
		try {
			Map<String, Object> retMap = new HashMap<String, Object>();
			if (null != id && !"".equals(id)) {
				PurchaseOrder po = baseHibernateService.findEntityById(PurchaseOrder.class, id);
				if (null != po && null != po.getPurchaseOrderLineItems()) {
					double totalTax = 0;
					double totalFee = 0;
					for (PurchaseOrderLineItem detailItem : po.getPurchaseOrderLineItems()) {
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

	// 跳转到添加明细页面
	public String goAddPurchaseOrderLineItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseItem = purchaseOrderController.findPurchaseOrderLineItemById(id);
			}
			receivedAddressesList = purchaseOrderController.findReceivedAddressIndex();
			logger.info("添加采购订单明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddPurchaseOrderLineItem";
	}

	/**
	 * 增加采购订单明细
	 * 
	 * @return
	 */
	public void saveOrUpdatePurchaseOrderLineItem() {
		try {

			if (purchaseItem != null && purchaseItem.getId() != null && !"".equals(purchaseItem.getId())) {
				// update
				PurchaseOrderLineItem piItem = baseHibernateService.findEntityById(PurchaseOrderLineItem.class, purchaseItem.getId());
				if (piItem != null) {
					piItem.setItemId(purchaseItem.getItemId());
					piItem.setItemName(purchaseItem.getItemName());
					piItem.setItemCode(purchaseItem.getItemCode());
					piItem.setSpecification(purchaseItem.getSpecification());
					piItem.setPrice(purchaseItem.getPrice());

					piItem.setPrice(dataAccuracy.getAmountDecimal(piItem.getPrice()));
					piItem.setAmount(purchaseItem.getAmount());
					piItem.setTaxRate(purchaseItem.getTaxRate());

					piItem.setTotal(dataAccuracy.getAmountDecimal(piItem.getTotal()));
					piItem.setRequireTime(purchaseItem.getRequireTime());
					piItem.setUnit(purchaseItem.getUnit());
					piItem.setRecieveWareHouseId(purchaseItem.getRecieveWareHouseId());
					piItem.setRecieveWareHouse(purchaseItem.getRecieveWareHouse());
					piItem.setSkuCode(purchaseItem.getSkuCode());

					baseHibernateService.merge(piItem);
				}
			} else {
				if (purchaseItem == null || purchaseItem.getItemId() == null || "0".equals(purchaseItem.getItemId()) || "".equals(purchaseItem.getItemId())) {
					return;
				}

				Item item = baseHibernateService.findEntityById(Item.class, purchaseItem.getItemId());
				if (item == null) {
					return;
				}

				if (purchaseItem.getPurchaseOrder() == null || purchaseItem.getPurchaseOrder().getId() == null)
					return;
				PurchaseOrder purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseItem.getPurchaseOrder().getId());
				if (purchaseOrder == null)
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
				// purchaseItem.setTaxRate(item.getInTax());

				if (item.getSupplierId() != null) {
					Supplier supplier = baseHibernateService.findEntityById(Supplier.class, item.getSupplierId());
					if (supplier != null) {
						purchaseItem.setSupplier(supplier.getName());
					}
				}

				purchaseItem.setProject(purchaseOrder.getProject());
				// receivingAddress

				purchaseItem.setCode(VixUUID.getUUID());
				// 处理groupCode，与主数据相同的groupCode，对应自身数据的billType,billCode
				purchaseItem.setGroupCode(genNewGroupCode(BillType.PUR_ORDER_ITEM, purchaseItem.getCode(), purchaseOrder.getGroupCode()));

				baseHibernateService.save(purchaseItem);
			}
			renderText(purchaseItem.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadPurchaseItemInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> info = loadItemInfoMap(id);

				renderJson(JSonUtils.toJSon(info));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转到快速新建供应商页面 */
	public String goAddQuicklySupplier() {
		return "goAddQuicklySupplier";
	}

	/**
	 * 保存供应商明细
	 * 
	 * @return
	 */
	public void saveOrUpdateSupplier() {
		try {
			supplier.setStatus("3");
			supplier = baseHibernateService.merge(supplier);

			renderText(supplier.getId() + "," + supplier.getName() + "," + supplier.getCode());
			logger.info("保存快速新建供应商");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 子项价格汇总 */
	public void getOrderItemTotal() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseOrder po = purchaseOrderController.doListEntityById(id);
				if (null != po) {
					Double totalAmount = 0d;
					for (PurchaseOrderLineItem orderLineItem : po.getPurchaseOrderLineItems()) {
						if (null != orderLineItem) {
							if ((null != orderLineItem.getPrice() && !"".equals(orderLineItem.getPrice())) && (null != orderLineItem.getAmount() && !"".equals(orderLineItem.getAmount()))) {
								totalAmount += orderLineItem.getPrice() * orderLineItem.getAmount();
								logger.info("计算采购订单的总额");
							}
						}
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转到选择采购计划单页面 */
	public String goChoosePurchasePlan() {
		return "goChoosePurchasePlan";
	}

	/** 获取采购计划列表数据 */
	public String goPurchasePlanList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			params.put("purchasePlanName," + SearchCondition.NOEQUAL, "null");
			Pager pager = purchaseOrderController.goPurchasePlans(params, getPager());
			logger.info("获取采购计划数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchasePlanList";
	}

	public String getPurchasePlanItemList() {
		String purchasePlanid = getRequestParameter("purchasePlanid");
		try {
			if (null != purchasePlanid && !"".equals(purchasePlanid)) {
				purchasePlan = purchaseOrderController.doListPurchasePlanById(purchasePlanid);
				Set<PurchasePlanItems> purchasePlanItemsTemp = purchasePlan.getPurchasePlanItems();
				if (purchasePlanItemsTemp.size() > 0) {
					purchasePlanItemsList = new ArrayList<PurchasePlanItems>(purchasePlanItemsTemp);
					logger.info("获取采购计划明细数据");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getPurchasePlanItemList";
	}

	public String converterPlanToOrder() {
		String purchasePlanids = getRequestParameter("purchasePlanid");
		try {
			purchaseOrder = purchaseOrderController.doListEntityById(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] purchasePlanidArr = purchasePlanids.split(",");
		if (purchasePlanidArr != null && !"".equals(purchasePlanidArr) && purchasePlanidArr.length > 0) {
			for (String purchasePlanid : purchasePlanidArr) {
				if (purchasePlanid != null && !"".equals(purchasePlanid)) {
					// 根据id查询采购订单
					PurchasePlan purchasePlanTemp = purchaseOrderController.doListPurchasePlanById(purchasePlanid);
					if (purchaseOrder != null) {
						try {
							purchaseOrderController.converterPlanToOrder(purchaseOrder, purchasePlanTemp);
							logger.info("将采购计划明细转化成采购订单明细");
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

	/** 跳转到选择采购申请单页面 */
	public String goChoosePurchaseApply() {
		return "goChoosePurchaseApply";
	}

	/** 获取采购申请列表数据 */
	public String goPurchaseApplyList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			addTimeLimitToParam(params);

			// 不记得为什么要加这行，现在不支持同名参数两个，所以搜索时出问题
			// params.put("name," + SearchCondition.NOEQUAL, "null");
			Pager pager = purchaseOrderController.goPurchaseApply(params, getPager());
			logger.info("获取采购申请数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseApplyList";
	}

	public String getPurchaseApplyItemList() {
		String purchaseApplyid = getRequestParameter("purchaseApplyid");
		try {
			if (null != purchaseApplyid && !"".equals(purchaseApplyid)) {
				purchaseApply = purchaseOrderController.doListPurchaseApplyById(purchaseApplyid);
				Set<PurchaseApplyDetails> purchaseApplyItemsTemp = purchaseApply.getPurchaseApplyDetails();
				if (purchaseApplyItemsTemp.size() > 0) {
					purchaseApplyItemsList = new ArrayList<PurchaseApplyDetails>(purchaseApplyItemsTemp);
					logger.info("获取采购申请明细数据");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getPurchaseApplyItemList";
	}

	public String converterApplyToOrder() {
		String purchaseApplyids = getRequestParameter("purchaseApplyid");
		try {
			purchaseOrder = purchaseOrderController.doListEntityById(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] purchaseApplyidArr = purchaseApplyids.split(",");
		if (purchaseApplyidArr != null && !"".equals(purchaseApplyidArr) && purchaseApplyidArr.length > 0) {
			for (String purchaseApplyid : purchaseApplyidArr) {
				if (purchaseApplyid != null && !"".equals(purchaseApplyid)) {
					// 根据id查询采购订单
					PurchaseApply purchaseApplyTemp = purchaseOrderController.doListPurchaseApplyById(purchaseApplyid);
					if (purchaseOrder != null) {
						try {
							purchaseOrderController.converterApplyToOrder(purchaseOrder, purchaseApplyTemp);
							logger.info("将采购申请明细转化成采购订单明细");
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

	/** 跳转到选择采购询价单页面 */
	public String goChoosePurchaseInquire() {
		return "goChoosePurchaseInquire";
	}

	/** 获取采购询价列表数据 */
	public String goPurchaseInquireList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = purchaseOrderController.goPurchaseInquire(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseInquireList";
	}

	public String getPurchaseInquireItemList() {
		String purchaseInquireid = getRequestParameter("purchaseInquireid");
		try {
			if (null != purchaseInquireid && !"".equals(purchaseInquireid)) {
				purchaseInquire = purchaseOrderController.doListPurchaseInquireById(purchaseInquireid);
				Set<PurchaseInquiryDetail> purchaseInquireItemsTemp = purchaseInquire.getPurchaseInquiryDetails();
				if (purchaseInquireItemsTemp.size() > 0) {
					purchaseInquireItemsList = new ArrayList<PurchaseInquiryDetail>(purchaseInquireItemsTemp);
					logger.info("获取采购询价明细数据");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getPurchaseInquireItemList";
	}

	public String converterInquireToOrder() {
		String purchaseInquireids = getRequestParameter("purchaseInquireid");
		try {
			purchaseOrder = purchaseOrderController.doListEntityById(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] purchaseInquireidArr = purchaseInquireids.split(",");
		if (purchaseInquireidArr != null && !"".equals(purchaseInquireidArr) && purchaseInquireidArr.length > 0) {
			for (String purchaseInquireid : purchaseInquireidArr) {
				if (purchaseInquireid != null && !"".equals(purchaseInquireid)) {
					// 根据id查询采购订单
					PurchaseInquire purchaseInquireTemp = purchaseOrderController.doListPurchaseInquireById(purchaseInquireid);
					if (purchaseOrder != null) {
						try {
							purchaseOrderController.converterInquireToOrder(purchaseOrder, purchaseInquireTemp);
							logger.info("将采购询价明细转化成采购订单明细");
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

	/** 选择项目 */
	public String goChooseRadioProject() {
		return "goChooseRadioProject";
	}

	/** 获取列表数据 */
	public String goSubRadioProjectList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = purchaseOrderController.goSingleList4(params, getPager());
			logger.info("获取选择供应商的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioProjectList";
	}

	public String goShowBeforeAndAfterPurchaseOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseOrder = purchaseOrderController.doListEntityById(id);
				if (purchaseOrder != null && purchaseOrder.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseOrder.getCreateTime()));
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseOrder = (PurchaseOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseOrder.getCreateTime()), params, purchaseOrder, "before");
						} else if ("after".equals(str)) {
							purchaseOrder = (PurchaseOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseOrder.getCreateTime()), params, purchaseOrder, "after");
						}
					}
					if (purchaseOrder == null || StringUtils.isEmpty(purchaseOrder.getId())) {
						purchaseOrder = purchaseOrderController.doListEntityById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseOrder";
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

	public PurchaseOrderLineItem getPurchaseOrderLineItem() {
		return purchaseOrderLineItem;
	}

	public void setPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
		this.purchaseOrderLineItem = purchaseOrderLineItem;
	}

	public List<PurchaseOrderLineItem> getPurchaseOrderLineItems() {
		return purchaseOrderLineItems;
	}

	public void setPurchaseOrderLineItems(List<PurchaseOrderLineItem> purchaseOrderLineItems) {
		this.purchaseOrderLineItems = purchaseOrderLineItems;
	}

	public List<ReceivedAddress> getReceivedAddressesList() {
		return receivedAddressesList;
	}

	public void setReceivedAddressesList(List<ReceivedAddress> receivedAddressesList) {
		this.receivedAddressesList = receivedAddressesList;
	}

	public List<OrderType> getOrderTypeList() {
		return orderTypeList;
	}

	public void setOrderTypeList(List<OrderType> orderTypeList) {
		this.orderTypeList = orderTypeList;
	}

	public List<BizType> getBizTypeList() {
		return bizTypeList;
	}

	public void setBizTypeList(List<BizType> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public List<PurchasePlan> getPurchasePlanList() {
		return purchasePlanList;
	}

	public void setPurchasePlanList(List<PurchasePlan> purchasePlanList) {
		this.purchasePlanList = purchasePlanList;
	}

	public PurchasePlanItems getPurchasePlanItems() {
		return purchasePlanItems;
	}

	public void setPurchasePlanItems(PurchasePlanItems purchasePlanItems) {
		this.purchasePlanItems = purchasePlanItems;
	}

	public List<PurchasePlanItems> getPurchasePlanItemsList() {
		return purchasePlanItemsList;
	}

	public void setPurchasePlanItemsList(List<PurchasePlanItems> purchasePlanItemsList) {
		this.purchasePlanItemsList = purchasePlanItemsList;
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

	public PurchaseApplyDetails getPurchaseApplyItems() {
		return purchaseApplyItems;
	}

	public void setPurchaseApplyItems(PurchaseApplyDetails purchaseApplyItems) {
		this.purchaseApplyItems = purchaseApplyItems;
	}

	public List<PurchaseApplyDetails> getPurchaseApplyItemsList() {
		return purchaseApplyItemsList;
	}

	public void setPurchaseApplyItemsList(List<PurchaseApplyDetails> purchaseApplyItemsList) {
		this.purchaseApplyItemsList = purchaseApplyItemsList;
	}

	public PurchaseInquire getPurchaseInquire() {
		return purchaseInquire;
	}

	public void setPurchaseInquire(PurchaseInquire purchaseInquire) {
		this.purchaseInquire = purchaseInquire;
	}

	public List<PurchaseInquire> getPurchaseInquireList() {
		return purchaseInquireList;
	}

	public void setPurchaseInquireList(List<PurchaseInquire> purchaseInquireList) {
		this.purchaseInquireList = purchaseInquireList;
	}

	public PurchaseInquiryDetail getPurchaseInquireItems() {
		return purchaseInquireItems;
	}

	public void setPurchaseInquireItems(PurchaseInquiryDetail purchaseInquireItems) {
		this.purchaseInquireItems = purchaseInquireItems;
	}

	public List<PurchaseInquiryDetail> getPurchaseInquireItemsList() {
		return purchaseInquireItemsList;
	}

	public void setPurchaseInquireItemsList(List<PurchaseInquiryDetail> purchaseInquireItemsList) {
		this.purchaseInquireItemsList = purchaseInquireItemsList;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public PurchaseOrderLineItem getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseOrderLineItem purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
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
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public ReceivedAddress getAddress() {
		return address;
	}

	public void setAddress(ReceivedAddress address) {
		this.address = address;
	}

	public DeliveryPlan getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliveryPlan delivery) {
		this.delivery = delivery;
	}

	public PurchaseInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(PurchaseInvoice invoice) {
		this.invoice = invoice;
	}

	public PriceConditions getConditions() {
		return conditions;
	}

	public void setConditions(PriceConditions conditions) {
		this.conditions = conditions;
	}

	public ApprovalOpinion getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(ApprovalOpinion approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

}
