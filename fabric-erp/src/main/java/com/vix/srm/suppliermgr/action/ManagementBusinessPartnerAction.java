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

import java.io.File;
import java.io.FileInputStream;
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
import com.vix.common.base.action.BaseSecAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAccountingInfo;
import com.vix.mdm.srm.share.entity.SupplierAddress;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;
import com.vix.mdm.srm.share.entity.SupplierBankInfo;
import com.vix.mdm.srm.share.entity.SupplierCategory;
import com.vix.mdm.srm.share.entity.SupplierCreditInfo;
import com.vix.mdm.srm.share.entity.SupplierIndicators;
import com.vix.srm.suppliermgr.controller.SupplierController;

import flexjson.transformer.DateTransformer;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-06-27
 */
/**
 * @author Bluesnow
 *
 */
@Controller
@Scope("prototype")
public class ManagementBusinessPartnerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(Supplier.class);
	/** 注入service */
	@Autowired
	private SupplierController supplierController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private String id;
	private String parentId;
	private String pageNo;
	/** 附件 */
	// private File fileToUpload;
	/** 附件的名称 */
	// private String fileToUploadFileName;
	/** 供应商 */
	private Supplier supplier;
	/** 供应商结合 */
	private List<Supplier> supplierList;
	/** 供应商分类 */
	private SupplierCategory supplierCategory;
	/** 供应商分类集合 */
	private List<SupplierCategory> supplierCategoryList;
	/** 职员 */
	private Employee employee;
	/** 职员集合 */
	private List<Employee> employeeList;

	String supplierId;

	private SupplierAptitudeInfo aptitudeInfo;
	private SupplierAddress supplierAddress;
	private SupplierBankInfo supplierBankInfo;
	private SupplierAccountingInfo accountingInfo;
	private SupplierCreditInfo creditInfo;
	String lastDealTimeStr;
	private SupplierIndicators supplierIndicators;

	protected String usedAction = "managementBusinessPartnerAction";
	protected String supplierStatus = Supplier.status_formal;

	public String supplierAptitudeInfoEdit() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.aptitudeInfo = this.baseHibernateService.findEntityById(
						SupplierAptitudeInfo.class, this.id);
				if (this.aptitudeInfo != null)
					this.supplierId = this.aptitudeInfo.getSupplier().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "supplierAptitudeInfoEdit";
	}

	public void saveOrUpdateSupplierAptitudeInfo() {
		if (this.aptitudeInfo != null) {
			try {
				// if(this.fileToUpload!=null)
				// {
				// String[] savePathAndName = this.saveUploadFile();
				// if(savePathAndName!=null && savePathAndName.length==2)
				// {
				// }
				// }

				if (this.aptitudeInfo.getId() != null
						&& !aptitudeInfo.getId().trim().equals("")
						&& !aptitudeInfo.getId().trim().equals("0")) {
					this.baseHibernateService.update(this.aptitudeInfo);
				} else {
					if (!this.supplierId.trim().equals("")
							&& !this.supplierId.trim().equals("0")) {
						this.supplier = this.baseHibernateService.findEntityById(
								Supplier.class, this.supplierId);
						this.aptitudeInfo.setSupplier(this.supplier);
						this.aptitudeInfo.setSupplierCode(this.supplier
								.getCode());

						this.baseHibernateService.save(this.aptitudeInfo);
					}
				}

				BaseSecAction.renderText(String.valueOf(this.aptitudeInfo.getId()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void supplierAptitudeInfoDelete() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.aptitudeInfo = this.baseHibernateService.findEntityById(
						SupplierAptitudeInfo.class, this.id);
				if (this.aptitudeInfo != null) {
					this.baseHibernateService.deleteByEntity(this.aptitudeInfo);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String supplierAddressEdit() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.supplierAddress = this.baseHibernateService.findEntityById(
						SupplierAddress.class, this.id);
				if (this.supplierAddress != null)
					this.supplierId = this.supplierAddress.getSupplier()
							.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "supplierAddressEdit";
	}

	public void saveOrUpdateSupplierAddress() {
		if (this.supplierAddress != null) {
			try {
				if (this.supplierAddress.getId() != null
						&& !supplierAddress.getId().trim().equals("")) {
					this.baseHibernateService.update(this.supplierAddress);
				} else {
					if (!this.supplierId.trim().equals("")) {
						this.supplier = this.baseHibernateService.findEntityById(
								Supplier.class, this.supplierId);
						this.supplierAddress.setSupplier(this.supplier);
						this.supplierAddress.setSupplierCode(this.supplier
								.getCode());

						this.baseHibernateService.save(this.supplierAddress);
					}
				}

				BaseSecAction.renderText(String.valueOf(this.supplierAddress.getId()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void supplierAddressDelete() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.supplierAddress = this.baseHibernateService.findEntityById(
						SupplierAddress.class, this.id);
				if (this.supplierAddress != null) {
					this.baseHibernateService.deleteByEntity(this.supplierAddress);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String supplierBankInfoEdit() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.supplierBankInfo = this.baseHibernateService.findEntityById(
						SupplierBankInfo.class, this.id);
				if (this.supplierBankInfo != null)
					this.supplierId = this.supplierBankInfo.getSupplier()
							.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "supplierBankInfoEdit";
	}

	public void saveOrUpdateSupplierBankInfo() {
		if (this.supplierBankInfo != null) {
			try {
				if (this.supplierBankInfo.getId() != null
						&& !supplierBankInfo.getId().trim().equals("")) {
					this.baseHibernateService.update(this.supplierBankInfo);
				} else {
					if (!this.supplierId.trim().equals("")) {
						this.supplier = this.baseHibernateService.findEntityById(
								Supplier.class, this.supplierId);
						this.supplierBankInfo.setSupplier(this.supplier);
						this.supplierBankInfo.setSupplierCode(this.supplier
								.getCode());

						this.baseHibernateService.save(this.supplierBankInfo);
					}
				}

				BaseSecAction.renderText(String.valueOf(this.supplierBankInfo.getId()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void supplierBankInfoDelete() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.supplierBankInfo = this.baseHibernateService.findEntityById(
						SupplierBankInfo.class, this.id);
				if (this.supplierBankInfo != null) {
					this.baseHibernateService
							.deleteByEntity(this.supplierBankInfo);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String supplierAccountingInfoEdit() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.accountingInfo = this.baseHibernateService.findEntityById(
						SupplierAccountingInfo.class, this.id);
				if (this.accountingInfo != null)
					this.supplierId = this.accountingInfo.getSupplier().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "supplierAccountingInfoEdit";
	}

	public void saveOrUpdateSupplierAccountingInfo() {
		if (this.accountingInfo != null) {
			try {
				if (this.accountingInfo.getId() != null
						&& !accountingInfo.getId().trim().equals("")) {
					this.baseHibernateService.update(this.accountingInfo);
				} else {
					if (!this.supplierId.trim().equals("")) {
						this.supplier = this.baseHibernateService.findEntityById(
								Supplier.class, this.supplierId);
						this.accountingInfo.setSupplier(this.supplier);
						this.accountingInfo.setSupplierCode(this.supplier
								.getCode());

						this.baseHibernateService.save(this.accountingInfo);
					}
				}

				BaseSecAction.renderText(String.valueOf(this.accountingInfo.getId()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void supplierAccountingInfoDelete() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.accountingInfo = this.baseHibernateService.findEntityById(
						SupplierAccountingInfo.class, this.id);
				if (this.accountingInfo != null) {
					this.baseHibernateService.deleteByEntity(this.accountingInfo);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String supplierCreditInfoEdit() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.creditInfo = this.baseHibernateService.findEntityById(
						SupplierCreditInfo.class, this.id);
				if (this.creditInfo != null)
					this.supplierId = this.creditInfo.getSupplier().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "supplierCreditInfoEdit";
	}

	public void saveOrUpdateSupplierCreditInfo() {
		if (this.creditInfo != null) {
			Date lastDealTime = null;
			if (StrUtils.isNotEmpty(lastDealTimeStr)) {
				try {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					lastDealTime = sf.parse(getUpdateTime(lastDealTimeStr));
					this.creditInfo.setLastDealTime(lastDealTime);
				} catch (Exception e) {
					this.creditInfo.setLastDealTime(null);
					e.printStackTrace();
				}
			}

			try {
				if (this.creditInfo.getId() != null
						&& !creditInfo.getId().trim().equals("")) {
					this.baseHibernateService.update(this.creditInfo);
				} else {
					if (!this.supplierId.trim().equals("")) {
						this.supplier = this.baseHibernateService.findEntityById(
								Supplier.class, this.supplierId);
						this.creditInfo.setSupplier(this.supplier);
						this.creditInfo
								.setSupplierCode(this.supplier.getCode());
						this.creditInfo
								.setSupplierName(this.supplier.getName());

						this.baseHibernateService.save(this.creditInfo);
					}
				}

				BaseSecAction.renderText(String.valueOf(this.creditInfo.getId()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void supplierCreditInfoDelete() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.creditInfo = this.baseHibernateService.findEntityById(
						SupplierCreditInfo.class, this.id);
				if (this.creditInfo != null) {
					this.baseHibernateService.deleteByEntity(this.creditInfo);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String supplierIndicatorsEdit() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.supplierIndicators = this.baseHibernateService
						.findEntityById(SupplierIndicators.class, this.id);
				if (this.supplierIndicators != null)
					this.supplierId = this.supplierIndicators.getSupplier()
							.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "supplierIndicatorsEdit";
	}

	public void saveOrUpdateSupplierIndicators() {
		if (this.supplierIndicators != null) {
			try {

				if (this.supplierIndicators.getId() != null
						&& !supplierIndicators.getId().trim().equals("")) {
					this.baseHibernateService.update(this.supplierIndicators);
				} else {
					if (!this.supplierId.trim().equals("")) {
						this.supplier = this.baseHibernateService.findEntityById(
								Supplier.class, this.supplierId);
						this.supplierIndicators.setSupplier(this.supplier);
						this.supplierIndicators.setSupplierCode(this.supplier
								.getCode());

						this.baseHibernateService.save(this.supplierIndicators);
					}
				}

				BaseSecAction.renderText(String.valueOf(this.supplierIndicators.getId()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void supplierIndicatorsDelete() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				this.supplierIndicators = this.baseHibernateService
						.findEntityById(SupplierIndicators.class, this.id);
				if (this.supplierIndicators != null) {
					this.baseHibernateService.deleteByEntity(
							this.supplierIndicators);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Supplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
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

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<SupplierCategory> listCategory = new ArrayList<SupplierCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = supplierController.findAllSubEntity(
						"supplierCategory.id", id, params);
			} else {
				listCategory = supplierController.findAllSubEntity(
						"supplierCategory.id", "0", params);
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

	public String goSingleList() {
		return this.goSingleList(Supplier.status_formal);
	}

	protected String goSingleList(String supplierStatus) {
		try {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);

			if (parentId != null && !"".equals(parentId))
				params.put("supplierCategory.id," + SearchCondition.EQUAL,
						this.parentId);

			if (StrUtils.isNotEmpty(supplierStatus))
				params.put("status," + SearchCondition.EQUAL, supplierStatus);

			Pager pager = this.getPager();
			pager.setOrderField("createTime");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try {
				this.baseHibernateService.findPagerByHqlConditions(pager,
						Supplier.class, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
				pager = supplierController.goSearchList(params, getPager());
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
				pager = supplierController.goSingleList(params, getPager());
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
			Pager pager = supplierController
					.goSubSingleList(params, getPager());
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
				supplier = supplierController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至选择职员界面 */
	public String goChooseRadioEmployee() {
		logger.info("选择职员");
		return "goChooseRadioEmployee";
	}

	/** 获取列表数据 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			// 按状态
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
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = supplierController.goSingleList2(params, getPager());
			logger.info("获取选择供应商的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}

	/** 跳转绑定职员帐号页面 */
	public String goBindEmployee() {
		try {
			if (null != id && !"".equals(id)) {
				supplier = supplierController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBindEmployee";
	}

	/** 绑定职员帐号 */
	public String bindEmployee() {
		boolean isSave = true;
		try {
			if (null != employee.getId()) {
				isSave = false;
			}
			employee.setSupplier(supplier);
			employee = supplierController.doSaveEmployee(employee);
			logger.info("对订单进行了修改！");
			setMessage("绑定成功！");
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

	/** 弹出选择所属分类 */
	public String goChooseSupplierCategory() {
		return "goChooseSupplierCategory";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != supplier.getId()) {
				isSave = false;
			}

			DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
			/*
			 * // 资质信息 String dlJson = getRequestParameter("dlJson");
			 * List<SupplierAptitudeInfo> saList = new
			 * JSONDeserializer<List<SupplierAptitudeInfo>>() .use(Date.class,
			 * dateTransformer) .use("values",
			 * SupplierAptitudeInfo.class).deserialize(dlJson); // 地址 String
			 * sdJson = getRequestParameter("sdJson"); List<SupplierAddress>
			 * sdList = new JSONDeserializer<List<SupplierAddress>>()
			 * .use(Date.class, dateTransformer) .use("values",
			 * SupplierAddress.class).deserialize(sdJson); // 银行信息 String sbJson
			 * = getRequestParameter("sbJson"); List<SupplierBankInfo> sbList =
			 * new JSONDeserializer<List<SupplierBankInfo>>() .use(Date.class,
			 * dateTransformer) .use("values",
			 * SupplierBankInfo.class).deserialize(sbJson); // 财务信息 String
			 * saiJson = getRequestParameter("saiJson");
			 * List<SupplierAccountingInfo> saiList = new
			 * JSONDeserializer<List<SupplierAccountingInfo>>() .use(Date.class,
			 * dateTransformer) .use("values",
			 * SupplierAccountingInfo.class).deserialize(saiJson); // 信用 String
			 * scJson = getRequestParameter("scJson"); List<SupplierCreditInfo>
			 * scList = new JSONDeserializer<List<SupplierCreditInfo>>()
			 * .use(Date.class, dateTransformer) .use("values",
			 * SupplierCreditInfo.class).deserialize(scJson); // 指标 String
			 * siJson = getRequestParameter("siJson"); List<SupplierIndicators>
			 * siList = new JSONDeserializer<List<SupplierIndicators>>()
			 * .use(Date.class, dateTransformer) .use("values",
			 * SupplierIndicators.class).deserialize(siJson); //
			 * initEntityBaseController.initEntityBaseAttribute(supplier);
			 */
			// supplier = supplierController.doSaveSupplier(supplier, saList,
			// sdList, sbList, saiList, scList, siList, parentId);
			supplier = supplierController.doSaveSupplier(supplier, null, null,
					null, null, null, null, parentId);
			logger.info("对订单进行了修改！");

			BaseSecAction.renderText(String.valueOf(supplier.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			Supplier supplier = supplierController.doListEntityById(id);
			if (null != supplier) {
				supplier.setEndTime(new Date());
				this.baseHibernateService.update(supplier);
				// supplierController.doDeleteByEntity(supplier);
				logger.info("");
				BaseSecAction.renderText("success");
			}
			logger.info("删除供应商信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void uploadAttachments() {
		try {
			if (!this.supplierId.trim().equals("") && this.fileToUpload != null) {
				Supplier sr = supplierController.findEntityById(supplierId);

				// String separator = System.getProperty("file.separator");

				// String baseDir = getServletContext().getRealPath(separator +
				// "richContent");
				// File baseDirF =new File(baseDir);
				// if(!baseDirF.exists())
				// baseDirF.mkdirs();

				String[] pathAndName = this.saveUploadFile("supplier");

				Attachments atts = new Attachments();
				atts.setName(pathAndName[1]);
				atts.setPath(pathAndName[0]);
				atts.setSupplier(sr);
				supplierController.mergeAttachments(atts);
				BaseSecAction.renderText("success");
			} else {
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}

	}

	public void deleteAttachment() {
		if (this.id != null && !this.id.trim().equals("")) {
			try {
				Attachments atts = this.baseHibernateService.findEntityById(
						Attachments.class, this.id);
				if (atts != null) {
					this.baseHibernateService.deleteByEntity(atts);
					BaseSecAction.renderText("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String downloadSupplierAttachment() {
		if (!this.id.trim().equals("")) {
			try {
				Attachments att = this.baseHibernateService.findEntityById(
						Attachments.class, this.id);
				String fileName = att.getName();
				String filePath = att.getPath();
				String title = att.getName();
				// int idx = fileName.lastIndexOf(".");
				// if(idx!=-1)
				// {
				// title = title + fileName.substring(idx);
				// }

				this.setOriFileName(title);

				String downloadFile = filePath + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadSupplierAttachment";
	}

	/** 获取供应商资质json数据 */
	public void getSupplierAptitudeInfoJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<SupplierAptitudeInfo>(
								sr.getSupplieAptitudeInfo()), sr
								.getSupplieAptitudeInfo().size(), "supplier");
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

	/** 获取供应商地址json数据 */
	public void getAddressJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<SupplierAddress>(
								sr.getSupplierAddresses()), sr
								.getSupplierAddresses().size(), "supplier");
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

	/** 获取供应商银行json数据 */
	public void getBankInfoJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<SupplierBankInfo>(
								sr.getSupplierBankInfos()), sr
								.getSupplierBankInfos().size(), "supplier");
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

	/** 获取供应商财务json数据 */
	public void getAccountingInfoJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(new ArrayList<SupplierAccountingInfo>(
						sr.getSupplierAccountingInfos()), sr
						.getSupplierAccountingInfos().size(), "supplier");
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

	/** 获取供应商信用json数据 */
	public void getCreditInfoJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<SupplierCreditInfo>(
								sr.getSupplierCreditInfos()), sr
								.getSupplierCreditInfos().size(), "supplier");
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

	/** 获取供应商信用json数据 */
	public void getIndicatorsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<SupplierIndicators>(
								sr.getSupplierIndicators()), sr
								.getSupplierIndicators().size(), "supplier");
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

	/** 获取附件json数据 */
	public void getAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Supplier sr = supplierController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<Attachments>(sr.getAttachments()), sr
								.getAttachments().size(), "supplier");
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

	/** 删除附件 */
	public void deleteAttachments() {
		try {
			String afId = getRequestParameter("afId");
			if (null != afId && !"".equals(afId)) {
				String afIdLong = afId;
				if (!afIdLong.equals("")) {
					Attachments atts = supplierController
							.findAttachmentsEntityById(afIdLong);
					if (null != atts) {
						/** 上传目录 */
						String separator = System.getProperty("file.separator");
						String baseDir = getServletContext().getRealPath(
								separator + "richContent");
						baseDir += separator;
						baseDir += atts.getName();
						File file = new File(baseDir);
						if (file.exists()) {
							file.delete();
						}
						supplierController.deleteAttachmentsEntity(atts);
					}
				}
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

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public SupplierController getSupplierController() {
		return supplierController;
	}

	public void setSupplierController(SupplierController supplierController) {
		this.supplierController = supplierController;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public SupplierAptitudeInfo getAptitudeInfo() {
		return aptitudeInfo;
	}

	public void setAptitudeInfo(SupplierAptitudeInfo aptitudeInfo) {
		this.aptitudeInfo = aptitudeInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public SupplierAddress getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(SupplierAddress supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public SupplierBankInfo getSupplierBankInfo() {
		return supplierBankInfo;
	}

	public void setSupplierBankInfo(SupplierBankInfo supplierBankInfo) {
		this.supplierBankInfo = supplierBankInfo;
	}

	public SupplierAccountingInfo getAccountingInfo() {
		return accountingInfo;
	}

	public void setAccountingInfo(SupplierAccountingInfo accountingInfo) {
		this.accountingInfo = accountingInfo;
	}

	public SupplierCreditInfo getCreditInfo() {
		return creditInfo;
	}

	public void setCreditInfo(SupplierCreditInfo creditInfo) {
		this.creditInfo = creditInfo;
	}

	public String getLastDealTimeStr() {
		return lastDealTimeStr;
	}

	public void setLastDealTimeStr(String lastDealTimeStr) {
		this.lastDealTimeStr = lastDealTimeStr;
	}

	public SupplierIndicators getSupplierIndicators() {
		return supplierIndicators;
	}

	public void setSupplierIndicators(SupplierIndicators supplierIndicators) {
		this.supplierIndicators = supplierIndicators;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getUsedAction() {
		return usedAction;
	}

	public void setUsedAction(String usedAction) {
		this.usedAction = usedAction;
	}
}
