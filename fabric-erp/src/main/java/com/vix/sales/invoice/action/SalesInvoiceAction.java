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

package com.vix.sales.invoice.action;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.invoice.controller.SalesInvoiceController;
import com.vix.sales.invoice.entity.SalesInvoice;
import com.vix.sales.invoice.entity.SalesInvoiceItem;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @Description: 销售发票
 * @author ivan
 * @date 2013-09-04
 */
@Controller
@Scope("prototype")
public class SalesInvoiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SalesInvoice.class);
	/** 注入service */
	@Autowired
	private SalesInvoiceController salesInvoiceController;

	private String id;
	private SalesInvoice salesInvoice;
	private String pageNo;
	private String invoiceType;
	private List<SalesInvoice> salesInvoiceList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			salesInvoiceList = salesInvoiceController.findSalesInvoiceIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSalesInvoiceList() {
		try {
			Map<String, Object> params = getParams();
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
			// 记录当前页面
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = salesInvoiceController.goSingleList(params,
					getPager());
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesInvoiceList";
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
			String name = getRequestParameter("name");
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
				params.put("purchasePerson," + SearchCondition.ANYLIKE,
						searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = salesInvoiceController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != purchasePerson && !"".equals(purchasePerson)) {
					params.put("purchasePerson," + SearchCondition.ANYLIKE,
							purchasePerson);
				}
				// 如果名称为空，则需要将封装好的名称条件移除
				if (null == name || "".equals(name)) {
					params.remove(name);
				}
				pager = salesInvoiceController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesInvoiceList";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = salesInvoiceController.goSubSingleList(params,
					getPager());
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				salesInvoice = salesInvoiceController.doListEntityById(id);
			} else {
				salesInvoice = new SalesInvoice();
				salesInvoice.setInvoiceType(invoiceType);
				salesInvoice.setCreateTime(new Date());
				loadCommonData(salesInvoice);
				salesInvoice = baseHibernateService.merge(salesInvoice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesInvoice.getId()) {
				isSave = false;
			} else {
				salesInvoice.setCreateTime(new Date());
				loadCommonData(salesInvoice);
			}
			salesInvoice.setStatus("S1");
			salesInvoice.setIsTemp("0");
			salesInvoice = baseHibernateService.merge(salesInvoice);
			dealBillGroupCode();
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SalesInvoice salesInvoice = baseHibernateService.findEntityById(
					SalesInvoice.class, id);
			if (null != salesInvoice) {
				if (null != salesInvoice.getSalesInvoiceItems()
						&& salesInvoice.getSalesInvoiceItems().size() > 0) {
					setMessage("发票信息存在明细,不允许删除!");
				} else {
					baseHibernateService.deleteByEntity(salesInvoice);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("发票信息不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细json数据 */
	public void getSalesInvoiceItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesInvoice si = salesInvoiceController.findEntityById(id);
				List<SalesInvoiceItem> salesInvoiceItems = new ArrayList<SalesInvoiceItem>(
						si.getSalesInvoiceItems());
				json = convertListToJson(salesInvoiceItems,
						salesInvoiceItems.size(), "salesInvoice");
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

	/** 计算最近使用日期 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
	}

	public String convertSalesOrderToSalesInvoice() {
		try {
			salesInvoice = new SalesInvoice();
			String salesOrderIds = getRequestParameter("salesOrderIds");
			if (null != salesOrderIds && !"".equals(salesOrderIds)) {
				String[] soStr = salesOrderIds.split(",");
				Set<String> soIdSet = new HashSet<String>();
				for (String soId : soStr) {
					if (null != soId && !"".equals(soId)) {
						soIdSet.add(soId);
					}
				}
				salesInvoice.setCreateTime(new Date());
				loadCommonData(salesInvoice);
				salesInvoice = baseHibernateService.merge(salesInvoice);
				for (String soId : soIdSet) {
					SalesOrder so = baseHibernateService.findEntityById(
							SalesOrder.class, soId);
					if (null != so && so.getSaleOrderItems().size() > 0) {
						if (null != so.getCustomerAccount()
								&& null == salesInvoice.getCustomerAccount()) {
							salesInvoice.setCustomerAccount(so
									.getCustomerAccount());
						}
						for (SaleOrderItem soi : so.getSaleOrderItems()) {
							SalesInvoiceItem sii = new SalesInvoiceItem();
							sii.setSalesInvoice(salesInvoice);
							sii.setIsTemp("0");
							if (null != soi.getItem()) {
								sii.setItemName(soi.getItem().getName());
								sii.setItemCode(soi.getItem().getCode());
								sii.setItemType(soi.getItem().getType());
							}
							sii.setNetTotal(soi.getNetTotal());
							sii.setNetPrice(soi.getNetPrice());
							sii.setGroupCode(soi.getGroupCode());
							loadCommonData(sii);
							sii = baseHibernateService.merge(sii);
							salesInvoice.getSalesInvoiceItems().add(sii);
						}
					}
				}
				salesInvoice = baseHibernateService.merge(salesInvoice);
			} else {
				salesInvoice.setCreateTime(new Date());
				loadCommonData(salesInvoice);
				salesInvoice = baseHibernateService.merge(salesInvoice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdateForCustomerAccount() {
		saveOrUpdate();
		return UPDATE;
	}

	public String goSaveOrUpdateForCustomerAccount() {
		goSaveOrUpdate();
		return "goSaveOrUpdateForCustomerAccount";
	}

	public String goListContentForCustomerAccount() {
		Map<String, Object> params = getParams();
		if (null != id && !"".equals(id)) {
			params.put("customerAccount.id," + SearchCondition.EQUAL, id);
		}
		goSalesInvoiceList();
		return "goSingleListForCustomerAccount";
	}

	private void dealBillGroupCode() throws Exception {
		if (null != salesInvoice && null != salesInvoice.getId()) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("billType," + SearchCondition.EQUAL, BillType.SA_DELIVERY);
			p.put("billCode," + SearchCondition.EQUAL, salesInvoice.getId()
					.toString());
			List<BillGroupCode> bgcList = baseHibernateService
					.findAllByConditions(BillGroupCode.class, p);
			for (BillGroupCode bgc : bgcList) {
				baseHibernateService.deleteByEntity(bgc);
			}
			SalesInvoice si = baseHibernateService.findEntityById(
					SalesInvoice.class, salesInvoice.getId());
			Set<String> bgcSet = new HashSet<String>();
			for (SalesInvoiceItem sii : si.getSalesInvoiceItems()) {
				if (null != sii && null != sii.getGroupCode()
						&& !"".equals(sii.getGroupCode())) {
					bgcSet.add(sii.getGroupCode());
				}
			}
			if (bgcSet.size() > 0) {
				for (String bgc : bgcSet) {
					BillGroupCode billGroupCode = new BillGroupCode();
					billGroupCode.setBillType(BillType.SA_DELIVERY);
					billGroupCode.setBillCode(si.getId().toString());
					billGroupCode.setGroupCode(bgc);
					loadCommonData(billGroupCode);
					baseHibernateService.merge(billGroupCode);
				}
			}
		}
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SalesInvoice getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public List<SalesInvoice> getSalesInvoiceList() {
		return salesInvoiceList;
	}

	public void setSalesInvoiceList(List<SalesInvoice> salesInvoiceList) {
		this.salesInvoiceList = salesInvoiceList;
	}
}
