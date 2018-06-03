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

package com.vix.srm.config.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierCategory;
import com.vix.srm.config.controller.SupplierCategoryController;

/**
 * @Description: 供应商分类
 * @author ivan
 * @date 2013-12-2
 */
@Controller
@Scope("prototype")
public class SupplierCategoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SupplierCategory.class);
	/** 注入service */
	@Autowired
	private SupplierCategoryController supplierCategoryController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private String id;
	private String parentId;
	private String pageNo;
	/** 供应商分类 */
	private SupplierCategory supplierCategory;
	/** 供应商分类集合 */
	private List<SupplierCategory> supplierCategoryList;

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

	public SupplierCategory getSupplierCategory() {
		return supplierCategory;
	}

	public void setSupplierCategory(SupplierCategory supplierCategory) {
		this.supplierCategory = supplierCategory;
	}

	public List<SupplierCategory> getSupplierCategoryList() {
		return supplierCategoryList;
	}

	public void setSupplierCategoryList(
			List<SupplierCategory> supplierCategoryList) {
		this.supplierCategoryList = supplierCategoryList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			supplierCategoryList = supplierCategoryController
					.findSupplierCategoryIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<SupplierCategory> listCategory = new ArrayList<SupplierCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = supplierCategoryController.findAllSubEntity(
						"supplierCategory.id", id, params);
			} else {
				listCategory = supplierCategoryController.findAllSubEntity(
						"supplierCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				SupplierCategory sc = listCategory.get(i);
				if (sc.getSupplierCategories().size() > 0) {
					strSb.append("{id:");
					strSb.append(sc.getId());
					strSb.append(",name:\"");
					strSb.append(sc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(sc.getId());
					strSb.append(",name:\"");
					strSb.append(sc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
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
			// 按选中分类
			if (null != parentId && !"".equals(parentId)) {
				params.put("supplierCategory.id," + SearchCondition.EQUAL,
						parentId);
			}
			// 记录当前页面
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = supplierCategoryController.goSingleList(params,
					getPager());
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
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
			// 名称
			String name = getRequestParameter("name");
			// 联系人
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contacts," + SearchCondition.ANYLIKE, searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = supplierCategoryController.goSearchList(params,
						getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("contacts," + SearchCondition.ANYLIKE, contacts);
				}
				// 如果名称为空，则需要将封装好的名称条件移除
				if (null == name || "".equals(name)) {
					params.remove(name);
				}
				pager = supplierCategoryController.goSingleList(params,
						getPager());
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
			Pager pager = supplierCategoryController.goSubSingleList(params,
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
				supplierCategory = supplierCategoryController
						.doListSupplierCategoryById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 弹出选择所属分类 */
	public String goChooseSupplierCategory() {
		return "goChooseSupplierCategory";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != supplierCategory.getId()) {
				isSave = false;
			}
			// 设置供应商状态为为审批
			supplierCategory.setStatus("S1");
			initEntityBaseController.initEntityBaseAttribute(supplierCategory);
			if (null != parentId && !"".equals(parentId)) {
				SupplierCategory sCategory = supplierCategoryController
						.findSupplierCategoryById(parentId);
				supplierCategory.setSupplierCategory(sCategory);
			}
			supplierCategory = supplierCategoryController
					.doSaveSupplierCategory(supplierCategory);
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
			SupplierCategory supplierCategoryTemp = supplierCategoryController
					.doListSupplierCategoryById(id);
			if (null != supplierCategoryTemp) {
				supplierCategoryController
						.doDeleteBySupplierCategory(supplierCategoryTemp);
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
