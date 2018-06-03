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

package com.vix.purchase.tender.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.mdm.srm.share.entity.SupplierTender;
import com.vix.purchase.tender.controller.PurchaseTenderController;

/**
 * @Description: 报价监控
 * @author ivan
 * @date 2013-11-05
 */
@Controller
@Scope("prototype")
public class QuoteMonitorAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SupplierTender.class);
	/** 注入service */
	@Autowired
	private PurchaseTenderController purchaseTenderController;

	private String id;
	private String pageNo;
	/** 招标 */
	private PurchaseTender purchaseTender;
	/** 招标集合 */
	private List<PurchaseTender> purchaseTenders;

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

	public PurchaseTender getPurchaseTender() {
		return purchaseTender;
	}

	public void setPurchaseTender(PurchaseTender purchaseTender) {
		this.purchaseTender = purchaseTender;
	}

	public List<PurchaseTender> getPurchaseTenders() {
		return purchaseTenders;
	}

	public void setPurchaseTenders(List<PurchaseTender> purchaseTenders) {
		this.purchaseTenders = purchaseTenders;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			purchaseTenders = purchaseTenderController.findPurchaseTenderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goQuoteMonitorList() {
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
			params.put("isParent," + SearchCondition.NOEQUAL, "null");
			Pager pager = purchaseTenderController.goSingleList(params, getPager());
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goQuoteMonitorList";
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
			String title = getRequestParameter("title");
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
				StringBuffer hql = new StringBuffer();
				hql.append("from PurchaseTender where 1=1 and isParent != 'null' ");
				if (searchContent != null && !"".equals(searchContent)) {
					hql.append(" and (contact like '%" + searchContent + "%'");
					hql.append(" or ");
					hql.append("title like '%" + searchContent + "%')");
				}
				pager = purchaseTenderController.goSearchList(hql.toString(), getPager());
			}
			// 高级搜索
			else {
				if (null != purchasePerson && !"".equals(purchasePerson)) {
					params.put("contact," + SearchCondition.ANYLIKE, purchasePerson);
				}
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				params.put("isParent," + SearchCondition.NOEQUAL, "null");
				pager = purchaseTenderController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goQuoteMonitorList";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = purchaseTenderController.goSubSingleList(params, getPager());
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至供应商报价监控页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseTender = purchaseTenderController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至选择供应商窗口 */
	public String goChooseChkSupplier() {
		String ptID = getRequestParameter("ptID");
		if (null != ptID) {
			try {
				purchaseTender = purchaseTenderController.findEntityById(ptID);
				getRequest().setAttribute("purchaseTender", purchaseTender);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("选择供应商");
		return "goChooseChkSupplier";
	}

	/** 获取供应商列表数据 */
	public String goSubChkSupplierList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			// 搜索
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
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
			Pager pager = purchaseTenderController.goSingleList2(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubChkSupplierList";
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
}
