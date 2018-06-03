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
import com.vix.contract.mamanger.entity.Contract;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.ItemPurchaseProperties;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.NvixOrderBatch;
import com.vix.mdm.purchase.order.entity.NvixPickingListDetail;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.purchase.hql.VixntPurchaseOrderHqlProvider;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.vix.nvix.purchase.action.VixntPurchaseOrderAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月29日
 */
@Controller
@Scope("prototype")
public class VixntPurchaseOrderAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private PurchaseOrder purchaseOrder;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	// 采购订单明细
	private PurchaseOrderLineItem purchaseOrderLineItem;
	private String purchaseOrderId;
	private String parentId;
	private String pickingListDetailIds;
	private String approval;
	private List<BaseEntity> baseEntityList;
	private PurchasePlan purchasePlan;
	@Autowired
	public VixntPurchaseOrderHqlProvider vixntPurchaseOrderHqlProvider;
	@Autowired
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	private List<BizType> bizTypeList;
	private List<OrderType> orderTypeList;
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("status,"+SearchCondition.EQUAL, "PUR");
			bizTypeList = vixntBaseService.findAllByConditions(BizType.class, params);
			orderTypeList = vixntBaseService.findAllByConditions(OrderType.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}
	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();

			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
				params.put("code," + SearchCondition.ANYLIKE, title);
			}
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String orderTypeCode = getDecodeRequestParameter("orderTypeCode");
			if (StringUtils.isNotEmpty(orderTypeCode)) {
				params.put("orderTypeCode," + SearchCondition.EQUAL, orderTypeCode);
			}
			String businessTypeCode = getDecodeRequestParameter("businessTypeCode");
			if (StringUtils.isNotEmpty(businessTypeCode)) {
				params.put("businessTypeCode," + SearchCondition.EQUAL, businessTypeCode);
			}
			String purchasePerson = getDecodeRequestParameter("purchasePerson");
			if (StringUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
			}
			String supplierId = getDecodeRequestParameter("supplierId");
			if (StringUtils.isNotEmpty(supplierId)) {
				params.put("supplier.id," + SearchCondition.EQUAL, supplierId);
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			// params.put("type," + SearchCondition.EQUAL, "1");
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至订单修改界面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			params.put("status,"+SearchCondition.EQUAL, "PUR");
			bizTypeList = vixntBaseService.findAllByConditions(BizType.class, params);
			orderTypeList = vixntBaseService.findAllByConditions(OrderType.class, params);
			isAllowAudit = isAllowAudit(BillType.PUR_ORDER);
			if (StringUtils.isNotEmpty(id)) {
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			} else {
				purchaseOrder = new PurchaseOrder();
				// purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				purchaseOrder.setName("采购订单" + dateFormat.format(new Date()));
				purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				purchaseOrder.setCreateTime(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					purchaseOrder.setCreator(employee.getName());
					purchaseOrder.setPurchasePerson(employee.getName());
					purchaseOrder.setPurchasePersonId(employee.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateByStoreOrder() {
		try {
			Map<String, Object> params = getParams();
			params.put("status,"+SearchCondition.EQUAL, "PUR");
			bizTypeList = vixntBaseService.findAllByConditions(BizType.class, params);
			if (StringUtils.isNotEmpty(id)) {
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			} else {
				purchaseOrder = new PurchaseOrder();
				purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
				Employee employee = getEmployee();
				if (employee != null) {
					purchaseOrder.setCreator(employee.getName());
					purchaseOrder.setPurchasePerson(employee.getName());
					purchaseOrder.setPurchasePersonId(employee.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateByStoreOrder";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			initEntityBaseController.initEntityBaseAttribute(purchaseOrder);
			purchaseOrder.setType("1");
			purchaseOrder.setStatus("4");
			purchaseOrder.setIsInventory("0");
			if("1".equals(isAllowAudit(BillType.PUR_ORDER))){
				purchaseOrder.setApprovalType("0");
			}
			purchaseOrder = vixntBaseService.merge(purchaseOrder);
			renderText("1:"+SAVE_SUCCESS+":"+purchaseOrder.getId());
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:"+OPER_FAIL);
		}
	}
	public void audit() {
		try {
			initEntityBaseController.initEntityBaseAttribute(purchaseOrder);
			purchaseOrder.setType("1");
			purchaseOrder.setStatus("4");
			purchaseOrder.setIsInventory("0");
			if("1".equals(isAllowAudit(BillType.PUR_ORDER))){
				purchaseOrder.setApprovalType("0");
			}
			purchaseOrder = vixntBaseService.merge(purchaseOrder);
			if("1".equals(isAllowAudit(BillType.PUR_ORDER))){
				String response = dealStartAndSubmitByBillsCode(BillType.PUR_ORDER, purchaseOrder);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if(json.has("status")){
						if("1".equals(json.getString("status"))){
							purchaseOrder.setApprovalType("1");//设置订单提交了审批
							purchaseOrder = vixntBaseService.merge(purchaseOrder);
							renderText(purchaseOrder.getId()+":提交成功!");
						}
					}
				}
			}else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("提交失败!");
		}
	}
	
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
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
			PurchaseOrder purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			if (null != purchaseOrder) {
				List<PurchaseOrderLineItem> purchaseOrderLineItems = vixntBaseService.findAllByEntityClassAndAttribute(PurchaseOrderLineItem.class, "purchaseOrder.id", purchaseOrder.getId());
				if (purchaseOrderLineItems != null && purchaseOrderLineItems.size() > 0) {
					renderText("订单不能删除!");
				} else {
					vixntBaseService.deleteByEntity(purchaseOrder);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public void goSupplierList() {
		try {
			Map<String, Object> params = getParams();
			String supplierName = getDecodeRequestParameter("supplierName");
			if (StringUtils.isNotEmpty(supplierName)) {
				params.put("name," + SearchCondition.ANYLIKE, supplierName);
			}
			params.put("status," + SearchCondition.EQUAL, "3");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Supplier.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseSupplier() {
		return "goChooseSupplier";
	}

	public void deletePurchaseOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				PurchaseOrderLineItem purchaseOrderLineItem = baseHibernateService.findEntityById(PurchaseOrderLineItem.class, id);
				if (purchaseOrderLineItem != null) {
					vixntBaseService.deleteByEntity(purchaseOrderLineItem);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 增加采购订单明细
	 * 
	 * @return
	 */
	public void saveOrUpdatePurchaseOrderLineItem() {
		try {
			initEntityBaseController.initEntityBaseAttribute(purchaseOrderLineItem);
			vixntBaseService.merge(purchaseOrderLineItem);
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	/** 子项价格汇总 */
	public void getOrderItemTotal() {
		if (StringUtils.isNotEmpty(id)) {
			PurchaseOrder po = null;
			try {
				po = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			} catch (Exception e) {
				renderText("计算合计失败!" + e.getStackTrace());
				// e.printStackTrace();
			}
			if (null != po) {
				Double totalAmount = 0d;
				for (PurchaseOrderLineItem orderLineItem : po.getPurchaseOrderLineItems()) {
					if (null != orderLineItem) {
						if (null != orderLineItem.getPrice() && null != orderLineItem.getAmount()) {
							totalAmount += orderLineItem.getPrice() * orderLineItem.getAmount();
							logger.info("计算采购订单的总额");
						}
					}
				}
				DecimalFormat df = new DecimalFormat(".##");
				if (totalAmount != 0) {
					String st = df.format(totalAmount);
					renderJson(st);
				} else {
					renderJson(totalAmount.toString());
				}
			}
		}
	}

	public void goVehicleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String searchvehicleno = getRequestParameter("searchvehicleno");
			if (StringUtils.isNotEmpty(searchvehicleno)) {
				params.put("vehicleNO," + SearchCondition.ANYLIKE, searchvehicleno);
			}
			params.put("status," + SearchCondition.EQUAL, "1");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Vehicle.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdatePurchaseOrderLineItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseOrderLineItem = vixntBaseService.findEntityById(PurchaseOrderLineItem.class, id);
			} else {
				purchaseOrderLineItem = new PurchaseOrderLineItem();
				if (StringUtils.isNotEmpty(purchaseOrderId)) {
					purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, purchaseOrderId);
					purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseOrderLineItem";
	}

	public String goSaveOrUpdateOrderItem() {
		return "goSaveOrUpdateOrderItem";
	}

	public void goNvixPickingListDetailList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(parentId)) {
				params.put("status," + SearchCondition.EQUAL, "0");
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, parentId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, NvixPickingListDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过要货单汇总生成采购订单明细
	public void pickingListDetailToPurchaseOrderLineItem() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrderId) && StringUtils.isNotEmpty(pickingListDetailIds)) {
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, purchaseOrderId);
				if (purchaseOrder != null) {
					Map<String, Object> params = getParams();
					params.put("id," + SearchCondition.IN, pickingListDetailIds);
					List<NvixPickingListDetail> nvixPickingListDetailList = vixntBaseService.findAllByConditions(NvixPickingListDetail.class, params);
					if (nvixPickingListDetailList != null && nvixPickingListDetailList.size() > 0) {
						for (NvixPickingListDetail nvixPickingListDetail : nvixPickingListDetailList) {
							if (nvixPickingListDetail != null) {
								Map<String, Object> itemparams = new HashMap<String, Object>();
								itemparams.put("itemCode", nvixPickingListDetail.getItemCode());
								itemparams.put("purchaseOrderId", purchaseOrder.getId());
								StringBuilder itemhql = standingBookHqlProvider.findPurchaseOrderLineItemByItemCode(itemparams);
								PurchaseOrderLineItem purchaseOrderLineItem = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
								if (purchaseOrderLineItem != null) {
								} else {
									purchaseOrderLineItem = new PurchaseOrderLineItem();
									purchaseOrderLineItem.setItemCode(nvixPickingListDetail.getItemCode());
									purchaseOrderLineItem.setItemName(nvixPickingListDetail.getItemName());
									purchaseOrderLineItem.setSpecification(nvixPickingListDetail.getSpecification());
									purchaseOrderLineItem.setPrice(nvixPickingListDetail.getPrice());
									purchaseOrderLineItem.setAmount(nvixPickingListDetail.getAmount());
									purchaseOrderLineItem.setUnit(nvixPickingListDetail.getUnit());
									initEntityBaseController.initEntityBaseAttribute(purchaseOrderLineItem);
									purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
									purchaseOrderLineItem = vixntBaseService.merge(purchaseOrderLineItem);
								}
								nvixPickingListDetail.setStatus("1");
								nvixPickingListDetail = vixntBaseService.merge(nvixPickingListDetail);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理入库
	 */
	public void inbond() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrder.getId())) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("purchaseOrder.id," + SearchCondition.EQUAL, purchaseOrder.getId());
				List<PurchaseOrderLineItem> purchaseOrderLineItemList = vixntBaseService.findAllByConditions(PurchaseOrderLineItem.class, params);
				if (purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0) {
					for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItemList) {
						Employee employee = getEmployee();
						Map<String, Object> p = new HashMap<String, Object>();
						p.put("itemcode", purchaseOrderLineItem.getItemCode());
						if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
							p.put("channelDistributorId", employee.getChannelDistributor().getId());
						}
						StringBuilder hql = vixntPurchaseOrderHqlProvider.findInventoryCurrentStockHql(p);
						InventoryCurrentStock inventoryCurrentStock = vixntBaseService.findObjectByHql(hql.toString(), p);
						if (inventoryCurrentStock != null) {
							if (purchaseOrderLineItem.getAmount() != null) {
								if (inventoryCurrentStock.getQuantity() != null) {
									inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() + purchaseOrderLineItem.getAmount());
								}
								if (inventoryCurrentStock.getAvaquantity() != null) {
									inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() + purchaseOrderLineItem.getAmount());
								}
							}
							inventoryCurrentStock = vixntBaseService.merge(inventoryCurrentStock);
						} else {
							// 库存没有该商品的情况下需要重新创建
							inventoryCurrentStock = new InventoryCurrentStock();
							inventoryCurrentStock.setItemcode(purchaseOrderLineItem.getItemCode());
							inventoryCurrentStock.setItemname(purchaseOrderLineItem.getItemName());
							// 合格品
							inventoryCurrentStock.setIsQualfied(1);
							// 处理库存商品名称拼音化
							String py = ChnToPinYin.getPYString(purchaseOrderLineItem.getItemName());
							inventoryCurrentStock.setChineseCharacter(py.toUpperCase());
							//
							inventoryCurrentStock.setQuantity(purchaseOrderLineItem.getAmount());
							inventoryCurrentStock.setAvaquantity(purchaseOrderLineItem.getAmount());
							inventoryCurrentStock.setUnit(purchaseOrderLineItem.getUnit());
							if (employee != null && employee.getChannelDistributor() != null) {
								inventoryCurrentStock.setChannelDistributor(employee.getChannelDistributor());
							}
							inventoryCurrentStock.setSkuCode(purchaseOrderLineItem.getSkuCode());
							inventoryCurrentStock.setSpecification(purchaseOrderLineItem.getSpecification());
							inventoryCurrentStock.setPrice(purchaseOrderLineItem.getPrice());
							initEntityBaseController.initEntityBaseAttribute(inventoryCurrentStock);
							inventoryCurrentStock = vixntBaseService.merge(inventoryCurrentStock);
						}
					}
					purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, purchaseOrder.getId());
					purchaseOrder.setStatus("3");
					purchaseOrder = vixntBaseService.merge(purchaseOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取采购订单明细
	 */
	public void goPurchaseOrderLineItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("itemName," + SearchCondition.ANYLIKE, searchName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("purchaseOrder.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrderLineItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 已汇总
	public void goNvixOrderBatchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "0");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void goPurchasePlanSingleList(){
		try {
			Map<String, Object> params = getParams();
			params.put("isReport," + SearchCondition.EQUAL, "0");
			params.put("status," + SearchCondition.EQUAL, "1");
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String choosePurchasePlan(){
		return "choosePurchasePlan";
	}
	public String showPurchaseOrder(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showPurchaseOrder";
	}
	/**
	 * 打印采购订单
	 * @return
	 */
	public String printPurchaseOrder(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printPurchaseOrder";
	}
	public void exportPurchaseOrderExcel(){
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "采购单.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			List<PurchaseOrder> purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseOrder_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseOrder_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseOrderList", purchaseOrderList);
					xlsArea.applyAt(new CellRef("purchaseOrder!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goChooseContract(){
		return "goChooseContract";
	}
	
	public void getContractSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("contractName,"+SearchCondition.ANYLIKE, searchName);
			}
			params.put("contractType,"+SearchCondition.IN, "1,2");
			pager = vixntBaseService.findPagerByHqlConditions(pager, Contract.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goShowPurchaseOrder(){
		try {
			Map<String, Object> params1 = getParams();
			params1.put("status,"+SearchCondition.EQUAL, "PUR");
			bizTypeList = vixntBaseService.findAllByConditions(BizType.class, params1);
			orderTypeList = vixntBaseService.findAllByConditions(OrderType.class, params1);
			if(StringUtils.isNotEmpty(id)){
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseOrder";
	}
	
	public String goSourceList(){
		try {
			try {
				if(StringUtils.isNotEmpty(id)){
					purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
					if(null != purchaseOrder){
						baseEntityList = new ArrayList<>();
						baseEntityList.add(purchaseOrder);
						if(StringUtils.isNotEmpty(purchaseOrder.getSourceBillCode())&&StringUtils.isNotEmpty(purchaseOrder.getSourceClassName())){
							getSourceBaseEntity(baseEntityList, purchaseOrder);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class,id);
				if (purchaseOrder != null && purchaseOrder.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseOrder.getCreateTime()));
					params.put("isTemp", "0");
					params.put("tenantId", purchaseOrder.getTenantId());
					params.put("companyInnerCode", purchaseOrder.getCompanyInnerCode());
					params.put("isDelete", "0");
					if (StringUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseOrder = (PurchaseOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseOrder.getCreateTime()), params, purchaseOrder, "before");
						} else if ("after".equals(str)) {
							purchaseOrder = (PurchaseOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseOrder.getCreateTime()), params, purchaseOrder, "after");
						}
					}
					if (purchaseOrder == null || StringUtils.isEmpty(purchaseOrder.getId())) {
						purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, purchaseOrder.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseOrder";
	}
	public void createPurchaseOrderBySalesOrder() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				SalesOrder salesOrder = vixntBaseService.findEntityById(SalesOrder.class, id);
				if(salesOrder != null) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setName("来源于销售订单" + dateFormat.format(new Date()));
					purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
					purchaseOrder.setCreateTime(new Date());
					Employee employee = getEmployee();
					if (employee != null) {
						purchaseOrder.setCreator(employee.getName());
						purchaseOrder.setPurchasePerson(employee.getName());
						purchaseOrder.setPurchasePersonId(employee.getId());
					}
					purchaseOrder.setMark(4L);
					purchaseOrder.setStatus("4");
					purchaseOrder.setType("1");
					purchaseOrder.setTotalAmount(salesOrder.getAmountTotal());
					purchaseOrder.setSourceBillCode(salesOrder.getCode());
					purchaseOrder.setSourceClassName(salesOrder.getClass().getName());
					purchaseOrder = vixntBaseService.merge(purchaseOrder);
					Map<String, Object> params = getParams();
					params.put("salesOrder.id,"+SearchCondition.EQUAL, salesOrder.getId());
					List<SaleOrderItem> saleOrderItemList = vixntBaseService.findAllByConditions(SaleOrderItem.class, params);
					if(saleOrderItemList != null && saleOrderItemList.size() > 0) {
						for (SaleOrderItem saleOrderItem : saleOrderItemList) {
							if(null != saleOrderItem){
								PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();
								purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
								if(saleOrderItem.getItem() != null) {
									purchaseOrderLineItem.setItemCode(saleOrderItem.getItem().getCode());
									purchaseOrderLineItem.setItemName(saleOrderItem.getItemName());
								}
								purchaseOrderLineItem.setSpecification(saleOrderItem.getSpecification());
								purchaseOrderLineItem.setUnit(saleOrderItem.getUnit());
								purchaseOrderLineItem.setCreateTime(new Date());
								purchaseOrderLineItem.setUpdateTime(new Date());
								purchaseOrderLineItem.setAmount(saleOrderItem.getAmount());
								purchaseOrderLineItem.setPrice(saleOrderItem.getPrice());
								purchaseOrderLineItem = vixntBaseService.merge(purchaseOrderLineItem);
							}
						}
					}
					renderText("1:转单成功!:"+purchaseOrder.getId());
				}else {
					renderText("0:转单失败!单据不存在!");
				}
			}
		} catch (Exception e) {
			renderText("0:转单失败!");
			e.printStackTrace();
		}
	}
	public String goAdvancedSearch() {
		return "goAdvancedSearch";
	}
	public void findItemPurchasePropertiesByItemId() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				ItemPurchaseProperties itemPurchaseProperties = vixntBaseService.findEntityByAttribute(ItemPurchaseProperties.class,"item.id", id);
				if(itemPurchaseProperties != null) {
					if(itemPurchaseProperties.getRecieveWarehouse() != null && StringUtils.isNotEmpty(itemPurchaseProperties.getRecieveWarehouse().getId())){
						renderText("0:"+itemPurchaseProperties.getRecieveWarehouse().getId()+":"+itemPurchaseProperties.getRecieveWarehouse().getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseOrderLineItem getPurchaseOrderLineItem() {
		return purchaseOrderLineItem;
	}

	public void setPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
		this.purchaseOrderLineItem = purchaseOrderLineItem;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPickingListDetailIds() {
		return pickingListDetailIds;
	}

	public void setPickingListDetailIds(String pickingListDetailIds) {
		this.pickingListDetailIds = pickingListDetailIds;
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

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
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

	public List<BizType> getBizTypeList() {
		return bizTypeList;
	}

	public void setBizTypeList(List<BizType> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}

	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}

	public List<OrderType> getOrderTypeList() {
		return orderTypeList;
	}

	public void setOrderTypeList(List<OrderType> orderTypeList) {
		this.orderTypeList = orderTypeList;
	}
}
