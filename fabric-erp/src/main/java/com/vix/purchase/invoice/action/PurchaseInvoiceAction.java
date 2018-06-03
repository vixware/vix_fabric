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

package com.vix.purchase.invoice.action;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.billtype.BillType;
import com.vix.common.code.AutoCreateCode;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoice;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoiceItem;
import com.vix.purchase.invoice.controller.PurchaseInvoiceController;

import flexjson.JSONDeserializer;

/**
 * @Description: 采购发票
 * @author ivan
 * @date 2013-08-15
 */
@Controller
@Scope("prototype")
public class PurchaseInvoiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchaseInvoice.class);
	@Autowired
	private PurchaseInvoiceController purchaseInvoiceController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private AutoCreateCode autoCreateCode;

	private String id;
	private PurchaseInvoice purchaseInvoice;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private List<PurchaseInvoice> purchaseInvoiceList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			purchaseInvoiceList = purchaseInvoiceController.findPurchaseInvoiceIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goInvoiceList() {
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
			params.put("name," + SearchCondition.NOEQUAL, "null");
			Pager pager = purchaseInvoiceController.goSingleList(params, getPager());
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInvoiceList";
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
			String code = getRequestParameter("code");
			String searchname = getRequestParameter("searchname");
			if (null != searchname && !"".equals(searchname)) {
				searchname = URLDecoder.decode(searchname, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if ("0".equals(i)) {
				params.put("name," + SearchCondition.ANYLIKE, searchContent.trim());
				pager = purchaseInvoiceController.goSearchList(params, getPager());
			} else {
				if (null != code && !"".equals(code)) {
					params.put("code," + SearchCondition.EQUAL, code.trim());
				}
				if (null != searchname && !"".equals(searchname)) {
					params.put("name," + SearchCondition.ANYLIKE, searchname.trim());
				}
				pager = purchaseInvoiceController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInvoiceList";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = purchaseInvoiceController.goSubSingleList(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseInvoice = purchaseInvoiceController.doListEntityById(id);
				logger.info("");
			} else {
				purchaseInvoice = new PurchaseInvoice();
				purchaseInvoice.setCode(autoCreateCode.getBillNO(BillType.PUR_INVOICE));
				purchaseInvoice = purchaseInvoiceController.doSavePurchaseInvoice(purchaseInvoice);
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
			if (null != purchaseInvoice.getId()) {
				isSave = false;
			}
			// 设置采购订单状态为未审批
			purchaseInvoice.setStatus("S1");
			// 交货地址
			String iiJson = getRequestParameter("iiJson");
			List<PurchaseInvoiceItem> iiList = new JSONDeserializer<List<PurchaseInvoiceItem>>().use("values", PurchaseInvoiceItem.class).deserialize(iiJson);
			//			initEntityBaseController.initEntityBaseAttribute(purchaseInvoice);
			purchaseInvoice = purchaseInvoiceController.doSavePurchaseInvoice(purchaseInvoice, iiList);
			logger.info("对订单进行了修改！");
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
			PurchaseInvoice purchaseInvoice = purchaseInvoiceController.doListEntityById(id);
			if (null != purchaseInvoice) {
				purchaseInvoiceController.doDeleteByEntity(purchaseInvoice);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取采购订单明细json数据 */
	public void getPurchaseInvoiceItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseInvoice pi = purchaseInvoiceController.findEntityById(id);
				List<PurchaseInvoiceItem> purchaseInvoiceItems = new ArrayList<PurchaseInvoiceItem>(pi.getPurchaseInvoiceItems());
				json = convertListToJson(purchaseInvoiceItems, purchaseInvoiceItems.size(), "purchaseInvoiceItems");
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
	 * @return the purchaseInvoice
	 */
	public PurchaseInvoice getPurchaseInvoice() {
		return purchaseInvoice;
	}

	/**
	 * @param purchaseInvoice
	 *            the purchaseInvoice to set
	 */
	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		this.purchaseInvoice = purchaseInvoice;
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
	 * @return the fileToUpload
	 */
	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	/**
	 * @param fileToUpload
	 *            the fileToUpload to set
	 */
	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	/**
	 * @return the fileToUploadFileName
	 */
	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	/**
	 * @param fileToUploadFileName
	 *            the fileToUploadFileName to set
	 */
	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	/**
	 * @return the purchaseInvoiceList
	 */
	public List<PurchaseInvoice> getPurchaseInvoiceList() {
		return purchaseInvoiceList;
	}

	/**
	 * @param purchaseInvoiceList
	 *            the purchaseInvoiceList to set
	 */
	public void setPurchaseInvoiceList(List<PurchaseInvoice> purchaseInvoiceList) {
		this.purchaseInvoiceList = purchaseInvoiceList;
	}

}
