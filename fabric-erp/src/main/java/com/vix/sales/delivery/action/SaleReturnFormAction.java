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

package com.vix.sales.delivery.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import com.vix.common.share.entity.CMNApprovalRecord;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.sales.common.entity.ReturnGoodsType;
import com.vix.sales.delivery.controller.SalesReturnController;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @Description: 销售退货
 * @author ivan
 * @date 2013-08-26
 */
@Controller
@Scope("prototype")
public class SaleReturnFormAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SaleReturnForm.class);
	/** 注入service */
	@Autowired
	private SalesReturnController salesReturnController;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private SaleReturnForm saleReturnForm;
	private String pageNo;
	private String str;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	private List<SaleReturnForm> saleReturnFormList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			baseHibernateService.findPagerByHqlConditions(getPager(),SaleReturnForm.class, params);
			saleReturnFormList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	/** 获取列表数据 */
	public String goReturnList() {
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
			Pager pager = salesReturnController.goSingleList(params, getPager());
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goReturnList";
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
			// 退货单号
			String returnOrderCode = getRequestParameter("returnOrderCode");
			// 客户名称
			String customerName = getRequestParameter("customerName");
			if (null != customerName && !"".equals(customerName)) {
				customerName = URLDecoder.decode(customerName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			
			if ("0".equals(i)) {// 简单搜索
				params.put("customerName," + SearchCondition.ANYLIKE,searchContent);
				params.put("returnOrderCode," + SearchCondition.ANYLIKE,searchContent);
				pager = salesReturnController.goSearchList(params, getPager());
			} else {// 高级搜索
				if (null != returnOrderCode && !"".equals(returnOrderCode)) {
					params.put("returnOrderCode," + SearchCondition.ANYLIKE,returnOrderCode);
				}
				if (null != customerName && !"".equals(customerName)) {
					params.put("customerName.name," + SearchCondition.ANYLIKE,customerName);
				}
				pager = salesReturnController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goReturnList";
	}
	
	//高级查询
	public String goSearch() {
		return "goSearch";
	}
	
	public String showSaleReturnForm() {
		try {
			if (null != id && !"".equals(id) && !id.equals("0")) {
				saleReturnForm = baseHibernateService.findEntityById(SaleReturnForm.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return "showSaleReturnForm";
	}
	
	//打印
	public String goPrintSaleReturnForm() {
		try {
			saleReturnForm = baseHibernateService.findEntityById(SaleReturnForm.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintSaleReturnForm";
	}
	
	//上一条   下一条
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				saleReturnForm = baseHibernateService.findEntityById(SaleReturnForm.class,id);
				if (saleReturnForm != null && saleReturnForm.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(saleReturnForm.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							saleReturnForm = (SaleReturnForm) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(saleReturnForm.getCreateTime()), params, saleReturnForm, "before");
						} else if ("after".equals(str)) {
							saleReturnForm = (SaleReturnForm) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(saleReturnForm.getCreateTime()), params, saleReturnForm, "after");
						}
					}
					if (saleReturnForm == null || StrUtils.isEmpty(saleReturnForm.getId())) {
						saleReturnForm = baseHibernateService.findEntityById(SaleReturnForm.class,id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showSaleReturnForm";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = salesReturnController.goSubSingleList(params,getPager());
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	//退换货单类型
	private List<ReturnGoodsType> returnGoodsTypeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			returnGoodsTypeList = baseHibernateService.findAllByEntityClass(ReturnGoodsType.class);
			isAllowAudit = isAllowAudit(BillType.SA_RETURN);
			if (null != id && !"".equals(id) && !"0".equals(id)) {
				saleReturnForm = salesReturnController.doListEntityById(id);
			} else {
				saleReturnForm = new SaleReturnForm();
				saleReturnForm.setIsTemp("1");
				saleReturnForm.setIsDeleted("0");
				saleReturnForm.setCreateTime(new Date());
				saleReturnForm.setReturnTime(new Date());
				loadCommonData(saleReturnForm);
				saleReturnForm = baseHibernateService.merge(saleReturnForm);
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
			if (null != saleReturnForm.getId()) {
				isSave = false;
			} else {
				saleReturnForm.setCreateTime(new Date());
				loadCommonData(saleReturnForm);
			}
			String name = saleReturnForm.getReturnOrderCode();
			if (null != name && !"".equals(name)) {
				String py = ChnToPinYin.getPYString(name);
				saleReturnForm.setChineseFirstLetter(py.toUpperCase());
			}
			saleReturnForm.setIsTemp("0");
			saleReturnForm.setIsDeleted("0");
			saleReturnForm = baseHibernateService.merge(saleReturnForm);
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
			SaleReturnForm saleReturnForm = salesReturnController
					.doListEntityById(id);
			if (null != saleReturnForm) {
				salesReturnController.doDeleteByEntity(saleReturnForm);
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
	public void getSaleReturnFormItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SaleReturnForm sf = salesReturnController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<SaleReturnFormItem>(
								sf.getSaleReturnFormItems()), sf
								.getSaleReturnFormItems().size(),
						"deliveryDocumentItem");
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

	/** 获取采购订单明细json数据 */
	public void getApprovalRecordJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SaleReturnForm sf = salesReturnController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<CMNApprovalRecord>(
								sf.getApprovalRecords()), sf
								.getApprovalRecords().size(),
						"deliveryDocumentItem");
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

	public void uploadAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				SaleReturnForm sf = salesReturnController.findEntityById(idStr);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					String baseDir = getServletContext().getRealPath(
							separator + "richContent");
					BufferedInputStream bufIn = new BufferedInputStream(
							new FileInputStream(fileToUpload));
					String[] fileNames = fileToUploadFileName.split("\\.");
					String fileName = fileNames[0];
					String extFileName = fileNames[fileNames.length - 1];
					String newFilePath = "";
					long timeTemp = System.currentTimeMillis();
					String newFileName = fileName + "_" + timeTemp + "."
							+ extFileName;
					newFilePath = baseDir + separator + newFileName;
					BufferedOutputStream bufOut = new BufferedOutputStream(
							new FileOutputStream(newFilePath));
					byte[] buf = new byte[1024 * 100];
					int len = -1;
					while ((len = bufIn.read(buf)) != -1) {
						bufOut.write(buf, 0, len);
					}
					bufOut.close();
					bufIn.close();
					Attachments atts = new Attachments();
					atts.setName(newFileName);
					atts.setSaleReturnForm(sf);
					salesReturnController.mergeAttachments(atts);
					renderJson("文件上传成功!");
				}
			} else {
				renderJson("订单id获取失败!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}

	}

	/** 获取附件json数据 */
	public void getAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SaleReturnForm sf = salesReturnController.findEntityById(id);
				json = convertListToJson(
						new ArrayList<Attachments>(sf.getAttachments()), sf
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
					Attachments atts = salesReturnController
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
						salesReturnController.deleteAttachmentsEntity(atts);
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

	public String convertSalesOrderToSaleReturnForm() {
		try {
			saleReturnForm = new SaleReturnForm();
			String salesOrderIds = getRequestParameter("salesOrderIds");
			if (null != salesOrderIds && !"".equals(salesOrderIds)) {
				String[] soStr = salesOrderIds.split(",");
				Set<String> soIdSet = new HashSet<String>();
				for (String soId : soStr) {
					if (null != soId && !"".equals(soId)) {
						soIdSet.add(soId);
					}
				}
				saleReturnForm.setCreateTime(new Date());
				loadCommonData(saleReturnForm);
				saleReturnForm = baseHibernateService.merge(saleReturnForm);
				returnGoodsTypeList = baseHibernateService.findAllByEntityClass(ReturnGoodsType.class);
				for (String soId : soIdSet) {
					SalesOrder so = baseHibernateService.findEntityById(SalesOrder.class, soId);
					if (null != so && so.getSaleOrderItems().size() > 0) {
						if (null != so.getCustomerAccount() && null == saleReturnForm.getCustomerAccount()) {
							saleReturnForm.setCustomerAccount(so.getCustomerAccount());
						}
						for (SaleOrderItem soi : so.getSaleOrderItems()) {
							SaleReturnFormItem srf = new SaleReturnFormItem();
							srf.setSaleReturnForm(saleReturnForm);
							if (null != soi.getItem()) {
								srf.setItemName(soi.getItem().getName());
								srf.setItemCode(soi.getItem().getCode());
								srf.setItemType(soi.getItem().getType());
							}
							srf.setSpecification(soi.getSpecification());
							srf.setNetTotal(soi.getNetTotal());
							srf.setNetPrice(soi.getNetPrice());
							srf.setGroupCode(soi.getGroupCode());
							loadCommonData(srf);
							baseHibernateService.merge(srf);
						}
					}
				}
				saleReturnForm = baseHibernateService.merge(saleReturnForm);
			} else {
				saleReturnForm.setCreateTime(new Date());
				loadCommonData(saleReturnForm);
				saleReturnForm = baseHibernateService.merge(saleReturnForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	private void dealBillGroupCode() throws Exception {
		if (null != saleReturnForm && null != saleReturnForm.getId()) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("billType," + SearchCondition.EQUAL, BillType.SA_RETURN);
			p.put("billCode," + SearchCondition.EQUAL, saleReturnForm.getId()
					.toString());
			List<BillGroupCode> bgcList = baseHibernateService
					.findAllByConditions(BillGroupCode.class, p);
			for (BillGroupCode bgc : bgcList) {
				baseHibernateService.deleteByEntity(bgc);
			}
			SaleReturnForm sf = baseHibernateService.findEntityById(
					SaleReturnForm.class, saleReturnForm.getId());
			Set<String> bgcSet = new HashSet<String>();
			for (SaleReturnFormItem srfi : sf.getSaleReturnFormItems()) {
				if (null != srfi && null != srfi.getGroupCode()
						&& !"".equals(srfi.getGroupCode())) {
					bgcSet.add(srfi.getGroupCode());
				}
			}
			if (bgcSet.size() > 0) {
				for (String bgc : bgcSet) {
					BillGroupCode billGroupCode = new BillGroupCode();
					billGroupCode.setBillType(BillType.SA_DELIVERY);
					billGroupCode.setBillCode(sf.getId().toString());
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

	public SaleReturnForm getSaleReturnForm() {
		return saleReturnForm;
	}

	public void setSaleReturnForm(SaleReturnForm saleReturnForm) {
		this.saleReturnForm = saleReturnForm;
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

	public List<SaleReturnForm> getSaleReturnFormList() {
		return saleReturnFormList;
	}

	public void setSaleReturnFormList(List<SaleReturnForm> saleReturnFormList) {
		this.saleReturnFormList = saleReturnFormList;
	}

	public List<ReturnGoodsType> getReturnGoodsTypeList() {
		return returnGoodsTypeList;
	}

	public void setReturnGoodsTypeList(List<ReturnGoodsType> returnGoodsTypeList) {
		this.returnGoodsTypeList = returnGoodsTypeList;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	

}
