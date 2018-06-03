/*
 * Copyright (C) 2013 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  供应商管理库存
 * Author:   ivan
 * Date:     2013.08.14
 * Version:  1.0
 *
 */

package com.vix.srm.portal.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.srm.share.entity.SupplierEvaluation;
import com.vix.mdm.srm.share.entity.SupplierVMI;

/**
 * @Description: 供应商管理库存
 * @author ivan
 * @date 2013-08-14
 */
@Controller
@Scope("prototype")
public class SupplierVMIAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SupplierEvaluation.class);
	/** 注入service */

	private String id;
	private SupplierVMI supplierVMI;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	private List<SupplierVMI> supplierVMIList;

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

	public SupplierVMI getSupplierVMI() {
		return supplierVMI;
	}

	public void setSupplierVMI(SupplierVMI supplierVMI) {
		this.supplierVMI = supplierVMI;
	}

	public List<SupplierVMI> getSupplierVMIList() {
		return supplierVMIList;
	}

	public void setSupplierVMIList(List<SupplierVMI> supplierVMIList) {
		this.supplierVMIList = supplierVMIList;
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

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goVMIList() {
		try {
			Map<String, Object> params = getParams();
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("itemname," + SearchCondition.ANYLIKE, name.trim());
			}
			String itemname = getDecodeRequestParameter("itemname");
			if (itemname != null && !"".equals(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			String itemcode = getRequestParameter("itemcode");
			if (itemcode != null && !"".equals(itemcode)) {
				params.put("itemcode," + SearchCondition.EQUAL, itemcode.trim());
			}
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			Employee employee = getEmployee();
			if (employee != null && employee.getSupplier() != null) {
				params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
				pager = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goVMIList";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		return "goVMIList";
	}

	public String goSubSingleList() {
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
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
