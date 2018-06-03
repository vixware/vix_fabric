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

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
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
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquiryDetail;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;

import net.sf.json.JSONObject;

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
public class VixntPurchaseInquireAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private String id;
	private String purchaseInquireId;
	private PurchaseInquire purchaseInquire;
	private List<PurchaseInquire> purchaseInquireList;
	// 币种集合
	private List<CurrencyType> currencyTypeList;
	// 交货地址集合
	private List<ReceivedAddress> receivedAddressesList;
	private PurchaseInquiryDetail purchaseInquiryDetail;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private String approval;
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
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (StringUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("appDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			Pager pager = this.getPager();
			pager = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.PUR_INQUERY);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
			} else {
				purchaseInquire = new PurchaseInquire();
				//purchaseArrival.setCode(autoCreateCode.getBillNO(BillType.PUR_ARRIVAL));
				purchaseInquire.setName("采购询价" + dateFormat.format(new Date()));
				purchaseInquire.setCode(autoCreateCode.getBillNO(BillType.PUR_INQUERY));
				purchaseInquire.setCreateTime(new Date());
				purchaseInquire.setAppDate(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					purchaseInquire.setCreator(employee.getName());
					purchaseInquire.setPurchasePerson(employee.getName());
				}
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
			if(StringUtils.isNotEmpty(purchaseInquire.getId())){
				isSave = false;
			}
			purchaseInquire.setCreateTime(new Date());
			purchaseInquire.setUpdateTime(new Date());
			if("1".equals(isAllowAudit(BillType.PUR_INQUERY))){
				purchaseInquire.setApprovalStatus("0");
			}
			purchaseInquire = vixntBaseService.merge(purchaseInquire);
			if("approval".equals(approval)){
				if("1".equals(isAllowAudit(BillType.PUR_INQUERY))){
					String response = dealStartAndSubmitByBillsCode(BillType.PUR_INQUERY, purchaseInquire);
					if(StringUtils.isNotEmpty(response)){
						JSONObject json = JSONObject.fromObject(response);
						if(json.has("status")){
							if("1".equals(json.getString("status"))){
								purchaseInquire.setApprovalStatus("0");
								purchaseInquire = vixntBaseService.merge(purchaseInquire);
								renderText("0:"+purchaseInquire.getId()+":提交成功!");
							}
						}
					}
				}
			}
			if(isSave){
				renderText("0:"+purchaseInquire.getId()+":"+SAVE_SUCCESS);
			}else{
				renderText("0:"+purchaseInquire.getId()+":"+UPDATE_SUCCESS);
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
	public void audit() {
		try {
			if(StringUtils.isNotEmpty(purchaseInquire.getId())){
				purchaseInquire.setUpdateTime(new Date());
			}else {
				purchaseInquire.setCreateTime(new Date());
			}
			if("1".equals(isAllowAudit(BillType.PUR_INQUERY))){
				purchaseInquire.setApprovalStatus("0");
			}
			purchaseInquire = vixntBaseService.merge(purchaseInquire);
			if("1".equals(isAllowAudit(BillType.PUR_INQUERY))){
				String response = dealStartAndSubmitByBillsCode(BillType.PUR_INQUERY, purchaseInquire);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if(json.has("status")){
						if("1".equals(json.getString("status"))){
							purchaseInquire.setApprovalStatus("0");
							purchaseInquire = vixntBaseService.merge(purchaseInquire);
							renderText(purchaseInquire.getId()+":提交成功!");
						}
					}
				}
			}else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				PurchaseInquire purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
				if (null != purchaseInquire) {
					vixntBaseService.deleteByEntity(purchaseInquire);
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
	public void goPurchaseInquiryDetailList() {
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
				params.put("purchaseInquire.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseInquiryDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdatePurchaseInquireDetail() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseInquiryDetail = vixntBaseService.findEntityById(PurchaseInquiryDetail.class, id);
			} else {
				purchaseInquiryDetail = new PurchaseInquiryDetail();
				if (StringUtils.isNotEmpty(purchaseInquireId)) {
					purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, purchaseInquireId);
					purchaseInquiryDetail.setPurchaseInquire(purchaseInquire);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseInquireDetail";
	}
	
	/**
	 * 增加到货订单明细
	 * 
	 * @return
	 */
	public void saveOrUpdatePurchaseInquireDetail() {
		try {
			initEntityBaseController.initEntityBaseAttribute(purchaseInquiryDetail);
			purchaseInquiryDetail = vixntBaseService.merge(purchaseInquiryDetail);
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
				PurchaseInquire po = vixntBaseService.findEntityById(PurchaseInquire.class, id);
				if (null != po) {
					Double totalAmount = 0d;
					for (PurchaseInquiryDetail orderLineItem : po.getPurchaseInquiryDetails()) {
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
	
	public void deletePurchaseInquireDetail() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				PurchaseInquiryDetail purchaseInquiryDetail = vixntBaseService.findEntityById(PurchaseInquiryDetail.class, id);
				if (purchaseInquiryDetail != null) {
					vixntBaseService.deleteByEntity(purchaseInquiryDetail);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public String goChoosePurchaseInquire() {
		return "goChoosePurchaseInquire";
	}
	public void createPurchaseOrder(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
				Map<String, Object> params = getParams();
				params.put("purchaseInquire.id,"+SearchCondition.EQUAL, purchaseInquire.getId());
				PurchaseOrder purchaseOrder = new PurchaseOrder();
				purchaseOrder.setName("采购订单" + dateFormat.format(new Date()));
				purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				purchaseOrder.setCreateTime(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					purchaseOrder.setCreator(employee.getName());
					purchaseOrder.setPurchasePerson(employee.getName());
					purchaseOrder.setPurchasePersonId(employee.getId());
				}
				if(StringUtils.isNotEmpty(purchaseInquire.getSupplierId())){
					Supplier supplier = vixntBaseService.findEntityById(Supplier.class, purchaseInquire.getSupplierId());
					purchaseOrder.setSupplier(supplier);
				}
				purchaseOrder.setMark(3L);
				purchaseOrder.setStatus("4");
				purchaseOrder.setType("1");
				purchaseOrder.setTotalAmount(purchaseInquire.getTotal());
				purchaseOrder.setSourceBillCode(purchaseInquire.getCode());
				purchaseOrder.setSourceClassName(purchaseInquire.getClass().getName());
				purchaseOrder = vixntBaseService.merge(purchaseOrder);
				Double totalAmount = 0d;
				List<PurchaseInquiryDetail> purchaseInquireDetailList = vixntBaseService.findAllByConditions(PurchaseInquiryDetail.class, params);
				if(null != purchaseInquireDetailList && purchaseInquireDetailList.size() > 0){
					for (PurchaseInquiryDetail purchaseInquiryDetail : purchaseInquireDetailList) {
						if(purchaseInquiryDetail != null){
							PurchaseOrderLineItem temp = new PurchaseOrderLineItem();
							temp.setPiCode(purchaseInquire.getCode());
							temp.setPiItemsCode(purchaseInquiryDetail.getCode());
							temp.setItemCode(purchaseInquiryDetail.getItemCode());
							temp.setItemName(purchaseInquiryDetail.getItemName());
							temp.setSpecification(purchaseInquiryDetail.getSpecification());
							temp.setItemType(purchaseInquiryDetail.getItemType());
							temp.setUnit(purchaseInquiryDetail.getUnit());
							temp.setPrice(purchaseInquiryDetail.getPrice());
							temp.setAmount(purchaseInquiryDetail.getAmount());
							temp.setNetTotal(purchaseInquiryDetail.getTotal());
							temp.setPurchaseOrder(purchaseOrder);
							temp = vixntBaseService.merge(temp);
							totalAmount += temp.getPrice() * temp.getAmount();
						}
					}
				}
				purchaseInquire.setStatus("2");
				purchaseInquire = vixntBaseService.merge(purchaseInquire);
				renderText("1:转换成功!:"+purchaseOrder.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:转换失败!");
		}
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseInquire = baseHibernateService.findEntityById(PurchaseInquire.class,id);
				if (purchaseInquire != null && purchaseInquire.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseInquire.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					params.put("tenantId", purchaseInquire.getTenantId());
					params.put("companyInnerCode", purchaseInquire.getCompanyInnerCode());
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseInquire = (PurchaseInquire) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseInquire.getCreateTime()), params, purchaseInquire, "before");
						} else if ("after".equals(str)) {
							purchaseInquire = (PurchaseInquire) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseInquire.getCreateTime()), params, purchaseInquire, "after");
						}
					}
					if (purchaseInquire == null || StrUtils.isEmpty(purchaseInquire.getId())) {
						purchaseInquire = baseHibernateService.findEntityById(PurchaseInquire.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, purchaseInquire.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String show() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String goSourceList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
				if (purchaseInquire != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(purchaseInquire);
					if (StringUtils.isNotEmpty(purchaseInquire.getSourceClassName()) && StringUtils.isNotEmpty(purchaseInquire.getSourceBillCode())) {
						getSourceBaseEntity(baseEntityList, purchaseInquire);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public String printPurchaseInquire() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseInquire = vixntBaseService.findEntityById(PurchaseInquire.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printPurchaseInquire";
	}
	public void exportPurchaseInquireExcel(){
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "询价单.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			List<PurchaseInquire> purchaseInquireList = vixntBaseService.findAllByConditions(PurchaseInquire.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseInquire_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseInquire_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseInquireList", purchaseInquireList);
					xlsArea.applyAt(new CellRef("purchaseInquire!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
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

	public String getPurchaseInquireId() {
		return purchaseInquireId;
	}

	public void setPurchaseInquireId(String purchaseInquireId) {
		this.purchaseInquireId = purchaseInquireId;
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

	public PurchaseInquiryDetail getPurchaseInquiryDetail() {
		return purchaseInquiryDetail;
	}

	public void setPurchaseInquiryDetail(PurchaseInquiryDetail purchaseInquiryDetail) {
		this.purchaseInquiryDetail = purchaseInquiryDetail;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
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
