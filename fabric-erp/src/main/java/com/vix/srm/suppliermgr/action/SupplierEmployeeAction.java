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

package com.vix.srm.suppliermgr.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.srm.suppliermgr.controller.SupplierEmployeeController;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-12-27
 */
@Controller
@Scope("prototype")
public class SupplierEmployeeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(Employee.class);
	/** 注入service */
	@Autowired
	private SupplierEmployeeController supplierEmployeeController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private String id;
	private Employee supplierEmployee;
	private Supplier supplier;
	private String pageNo;

	private List<Employee> supplierEmployeeList;

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

	public Employee getSupplierEmployee() {
		return supplierEmployee;
	}

	public void setSupplierEmployee(Employee supplierEmployee) {
		this.supplierEmployee = supplierEmployee;
	}

	public List<Employee> getSupplierEmployeeList() {
		return supplierEmployeeList;
	}

	public void setSupplierEmployeeList(List<Employee> supplierEmployeeList) {
		this.supplierEmployeeList = supplierEmployeeList;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			supplierEmployeeList = supplierEmployeeController
					.findEmployeeIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
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
			params.put("empType," + SearchCondition.EQUAL, "SE");
			Pager pager = supplierEmployeeController.goSingleList(params,
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
			if (null != searchContent && !"".equals(searchContent)) {
				searchContent = URLDecoder.decode(searchContent, "utf-8");
			}
			// 名称
			String purchasePlanName = getRequestParameter("purchasePlanName");
			if (null != purchasePlanName && !"".equals(purchasePlanName)) {
				purchasePlanName = URLDecoder.decode(purchasePlanName, "utf-8");
			}
			// 业务员
			String executePerson = getRequestParameter("purchasePerson");
			if (null != executePerson && !"".equals(executePerson)) {
				executePerson = URLDecoder.decode(executePerson, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("executePerson," + SearchCondition.ANYLIKE,
						searchContent);
				params.put("purchasePlanName," + SearchCondition.ANYLIKE,
						searchContent);
				pager = supplierEmployeeController.goSearchList(params,
						getPager());
			}
			// 高级搜索
			else {
				if (null != purchasePlanName && !"".equals(purchasePlanName)) {
					params.put("purchasePlanName," + SearchCondition.ANYLIKE,
							purchasePlanName);
				}
				if (null != executePerson && !"".equals(executePerson)) {
					params.put("executePerson," + SearchCondition.ANYLIKE,
							executePerson);
				}
				params.remove("name");
				pager = supplierEmployeeController.goSingleList(params,
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
			Pager pager = supplierEmployeeController.goSubSingleList(params,
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
				supplierEmployee = supplierEmployeeController
						.doListEmployeeById(id);
				logger.info("");
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
			if (null != supplierEmployee.getId()) {
				isSave = false;
			}
			supplierEmployee.setEmpType("SE");
			initEntityBaseController.initEntityBaseAttribute(supplierEmployee);
			supplierEmployee = supplierEmployeeController
					.doSaveEmployee(supplierEmployee);
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
			Employee e = supplierEmployeeController.doListEmployeeById(id);
			if (null != e) {
				supplierEmployeeController.doDeleteByEmployee(e);
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
