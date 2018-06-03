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
import com.vix.sales.common.entity.SalesBillType;
import com.vix.sales.common.entity.SalesBusinessType;
import com.vix.sales.delivery.controller.SalesDeliveryController;
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.DeliveryDocumentItem;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class DeliveryDocumentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(DeliveryDocument.class);
	/** 注入service */
	@Autowired
	private SalesDeliveryController salesDeliveryController;
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private String id;
	private DeliveryDocument deliveryDocument;
	private String pageNo;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	private List<DeliveryDocument> deliveryDocumentList;

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
			baseHibernateService.findPagerByHqlConditions(getPager(),DeliveryDocument.class, params);
			deliveryDocumentList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	/** 获取列表数据 */
	public String goDeliveryList() {
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
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			Pager pager = salesDeliveryController.goSingleList(params,getPager());
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDeliveryList";
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
			String salePerson = getRequestParameter("salePerson");
			if(null != salePerson && !"".trim().equals(salePerson)){
				salePerson = decode(salePerson, "UTF-8");
				params.put("salePerson.fs," + SearchCondition.ANYLIKE , salePerson);
			}
			// 发货单号
			String ddCode = getRequestParameter("ddCode");
			// 客户名称
			String customerName = getRequestParameter("customerName");
			if (null != customerName && !"".equals(customerName)) {
				customerName = URLDecoder.decode(customerName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, searchContent);
				pager = salesDeliveryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != ddCode && !"".equals(ddCode)) {
					params.put("ddCode," + SearchCondition.ANYLIKE, ddCode);
				}
				if (null != customerName && !"".equals(customerName)) {
					params.put("customerName," + SearchCondition.ANYLIKE,customerName);
				}
				pager = salesDeliveryController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDeliveryList";
	}
	
	//发货单业务类型
	private List<SalesBusinessType> salesBusinessTypeList;
	//发货单类型
	private List<SalesBillType> salesBillTypeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			salesBillTypeList = baseHibernateService.findAllByEntityClass(SalesBillType.class);
			salesBusinessTypeList = baseHibernateService.findAllByEntityClass(SalesBusinessType.class);
			isAllowAudit = isAllowAudit(BillType.SA_DELIVERY);
			if (null != id && !"".equals(id)) {
				deliveryDocument = salesDeliveryController.doListEntityById(id);
			} else {
				deliveryDocument = new DeliveryDocument();
				deliveryDocument.setCreateTime(new Date());
				deliveryDocument.setShippedDate(new Date());
				deliveryDocument.setIsTemp("1");
				deliveryDocument.setIsDeleted("0");
				loadCommonData(deliveryDocument);
				deliveryDocument = baseHibernateService.merge(deliveryDocument);
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
			if (null != deliveryDocument.getId()) {
				isSave = false;
			} else {
				deliveryDocument.setCreateTime(new Date());
				loadCommonData(deliveryDocument);
			}
			deliveryDocument.setIsTemp("0");
			deliveryDocument.setIsDeleted("0");
			
			String[] attrArray = {"salePerson","customerAccount"};
			checkEntityNullValue(deliveryDocument, attrArray);
			
			String name = deliveryDocument.getDdCode();
			if (null != name && !"".equals(name)) {
				String py = ChnToPinYin.getPYString(name);
				deliveryDocument.setChineseFirstLetter(py.toUpperCase());
			}
			deliveryDocument = baseHibernateService.merge(deliveryDocument);
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
			DeliveryDocument deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class, id);
			if (null != deliveryDocument) {
				deliveryDocument.setIsDeleted("1");
				baseHibernateService.merge(deliveryDocument);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除发货单信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取采购订单明细json数据 */
	public void getDeliveryDocumentItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				DeliveryDocument dd = salesDeliveryController.findEntityById(id);
				json = convertListToJson(new ArrayList<DeliveryDocumentItem>(dd.getDeliveryDocumentItems()), 
						dd.getDeliveryDocumentItems().size(),"deliveryDocumentItem");
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
	public void getApprovalOpinionJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				DeliveryDocument dd = salesDeliveryController.findEntityById(id);
				json = convertListToJson(new ArrayList<CMNApprovalRecord>(dd.getApprovalRecords()), 
						dd.getApprovalRecords().size(), "deliveryDocumentItem");
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
				DeliveryDocument dd = salesDeliveryController.findEntityById(idStr);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					String baseDir = getServletContext().getRealPath(separator + "richContent");
					BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
					String[] fileNames = fileToUploadFileName.split("\\.");
					String fileName = fileNames[0];
					String extFileName = fileNames[fileNames.length - 1];
					String newFilePath = "";
					long timeTemp = System.currentTimeMillis();
					String newFileName = fileName + "_" + timeTemp + "." + extFileName;
					newFilePath = baseDir + separator + newFileName;
					BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
					byte[] buf = new byte[1024 * 100];
					int len = -1;
					while ((len = bufIn.read(buf)) != -1) {
						bufOut.write(buf, 0, len);
					}
					bufOut.close();
					bufIn.close();
					Attachments atts = new Attachments();
					atts.setName(newFileName);
					atts.setDeliveryDocument(dd);
					salesDeliveryController.mergeAttachments(atts);
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
				DeliveryDocument dd = salesDeliveryController.findEntityById(id);
				json = convertListToJson(new ArrayList<Attachments>(dd.getAttachments()), 
						dd.getAttachments().size(), "supplier");
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
					Attachments atts = salesDeliveryController.findAttachmentsEntityById(afIdLong);
					if (null != atts) {
						/** 上传目录 */
						String separator = System.getProperty("file.separator");
						String baseDir = getServletContext().getRealPath(separator + "richContent");
						baseDir += separator;
						baseDir += atts.getName();
						File file = new File(baseDir);
						if (file.exists()) {
							file.delete();
						}
						salesDeliveryController.deleteAttachmentsEntity(atts);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String showDelivery() {
		try {
			if (null != id && !"".equals(id) && !id.equals("0")) {
				deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return "showDelivery";
	}
	
	//打印
	public String goPrintDelivery() {
		try {
			deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintDelivery";
	}
	
	//上一条   下一条
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class,id);
				if (deliveryDocument != null && deliveryDocument.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(deliveryDocument.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							deliveryDocument = (DeliveryDocument) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(deliveryDocument.getCreateTime()), params, deliveryDocument, "before");
						} else if ("after".equals(str)) {
							deliveryDocument = (DeliveryDocument) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(deliveryDocument.getCreateTime()), params, deliveryDocument, "after");
						}
					}
					if (deliveryDocument == null || StrUtils.isEmpty(deliveryDocument.getId())) {
						deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class,id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showDelivery";
	}
	
	
//	高级搜索
	public String goSearch() {
		return "goSearch";
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

	public String convertSalesOrderToDeliveryDocument() {
		try {
			salesBillTypeList = baseHibernateService.findAllByEntityClass(SalesBillType.class);
			salesBusinessTypeList = baseHibernateService.findAllByEntityClass(SalesBusinessType.class);
			deliveryDocument = new DeliveryDocument();
			String salesOrderIds = getRequestParameter("salesOrderIds");
			if (null != salesOrderIds && !"".equals(salesOrderIds)) {
				String[] soStr = salesOrderIds.split(",");
				Set<String> soIdSet = new HashSet<String>();
				for (String soId : soStr) {
					if (null != soId && !"".equals(soId)) {
						soIdSet.add(soId);
					}
				}
				deliveryDocument.setCreateTime(new Date());
				loadCommonData(deliveryDocument);
				deliveryDocument = baseHibernateService.merge(deliveryDocument);
				for (String soId : soIdSet) {
					SalesOrder so = baseHibernateService.findEntityById(SalesOrder.class, soId);
					if (null != so && so.getSaleOrderItems().size() > 0) {
						if (null != so.getCustomerAccount() && null == deliveryDocument.getCustomerAccount()) {
							deliveryDocument.setCustomerAccount(so.getCustomerAccount());
						}
						for (SaleOrderItem soi : so.getSaleOrderItems()) {
							DeliveryDocumentItem ddi = new DeliveryDocumentItem();
							ddi.setDeliveryDocument(deliveryDocument);
							if (null != soi.getItem()) {
								ddi.setItemName(soi.getItem().getName());
								ddi.setItemCode(soi.getItem().getCode());
								ddi.setItemType(soi.getItem().getType());
							}
							ddi.setSpecification(soi.getSpecification());
							ddi.setNetTotal(soi.getNetTotal());
							ddi.setNetPrice(soi.getNetPrice());
							ddi.setGroupCode(soi.getGroupCode());
							loadCommonData(ddi);
							baseHibernateService.merge(ddi);
						}
					}
				}
				deliveryDocument = baseHibernateService.merge(deliveryDocument);
			} else {
				deliveryDocument.setCreateTime(new Date());
				loadCommonData(deliveryDocument);
				deliveryDocument = baseHibernateService.merge(deliveryDocument);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	private void dealBillGroupCode() throws Exception {
		if (null != deliveryDocument && null != deliveryDocument.getId()) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("billType," + SearchCondition.EQUAL, BillType.SA_DELIVERY);
			p.put("billCode," + SearchCondition.EQUAL, deliveryDocument.getId().toString());
			List<BillGroupCode> bgcList = baseHibernateService.findAllByConditions(BillGroupCode.class, p);
			for (BillGroupCode bgc : bgcList) {
				baseHibernateService.deleteByEntity(bgc);
			}
			DeliveryDocument dd = baseHibernateService.findEntityById(DeliveryDocument.class, deliveryDocument.getId());
			Set<String> bgcSet = new HashSet<String>();
			for (DeliveryDocumentItem ddi : dd.getDeliveryDocumentItems()) {
				if (null != ddi && null != ddi.getGroupCode() && !"".equals(ddi.getGroupCode())) {
					bgcSet.add(ddi.getGroupCode());
				}
			}
			if (bgcSet.size() > 0) {
				for (String bgc : bgcSet) {
					BillGroupCode billGroupCode = new BillGroupCode();
					billGroupCode.setBillType(BillType.SA_DELIVERY);
					billGroupCode.setBillCode(dd.getId().toString());
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

	public DeliveryDocument getDeliveryDocument() {
		return deliveryDocument;
	}

	public void setDeliveryDocument(DeliveryDocument deliveryDocument) {
		this.deliveryDocument = deliveryDocument;
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

	public List<DeliveryDocument> getDeliveryDocumentList() {
		return deliveryDocumentList;
	}

	public void setDeliveryDocumentList(
			List<DeliveryDocument> deliveryDocumentList) {
		this.deliveryDocumentList = deliveryDocumentList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public List<SalesBusinessType> getSalesBusinessTypeList() {
		return salesBusinessTypeList;
	}

	public void setSalesBusinessTypeList(
			List<SalesBusinessType> salesBusinessTypeList) {
		this.salesBusinessTypeList = salesBusinessTypeList;
	}

	public List<SalesBillType> getSalesBillTypeList() {
		return salesBillTypeList;
	}

	public void setSalesBillTypeList(List<SalesBillType> salesBillTypeList) {
		this.salesBillTypeList = salesBillTypeList;
	}
	
	
	
}
