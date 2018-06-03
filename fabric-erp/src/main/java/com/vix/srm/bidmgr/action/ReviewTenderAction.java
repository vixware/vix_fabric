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

package com.vix.srm.bidmgr.action;

import java.io.File;
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
import com.vix.common.cache.IUseCache;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierTender;
import com.vix.srm.bidmgr.controller.SupplierTenderController;

/**
 * @Description: 评标管理
 * @author ivan 
 * @date 2014-01-23
 */
@Controller
@Scope("prototype")
public class ReviewTenderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SupplierTender.class);
	/** 注入service */
	@Autowired
	private SupplierTenderController supplierTenderController;
	@Autowired
	private IUseCache useCache;

	private String id;
	private SupplierTender supplierTender;
	private PurchaseTender purchaseTender;
	private Supplier supplier;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	private List<SupplierTender> supplierTenderList;

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

	public SupplierTender getSupplierTender() {
		return supplierTender;
	}

	public void setSupplierTender(SupplierTender supplierTender) {
		this.supplierTender = supplierTender;
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

	public List<SupplierTender> getSupplierTenderList() {
		return supplierTenderList;
	}

	public void setSupplierTenderList(List<SupplierTender> supplierTenderList) {
		this.supplierTenderList = supplierTenderList;
	}

	public PurchaseTender getPurchaseTender() {
		return purchaseTender;
	}

	public void setPurchaseTender(PurchaseTender purchaseTender) {
		this.purchaseTender = purchaseTender;
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
			supplierTenderList = supplierTenderController.findSupplierTenderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSrmTenderList() {
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
			Supplier sTemp = supplierTenderController.findSupplierById(useCache.getSupplier().getId());
			params.put("id," + SearchCondition.EQUAL, sTemp.getId());
			Pager pager = supplierTenderController.goSingleList(params, getPager());
			pager.setResultList(new ArrayList<PurchaseTender>(sTemp.getPurchaseTenders()));
			logger.info("获取供应商列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSrmTenderList";
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
			String name = getRequestParameter("purchaseName");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "utf-8");
			}
			// 投标公司
			String purchasePerson = getRequestParameter("purchasePerson");
			if (null != purchasePerson && !"".equals(purchasePerson)) {
				purchasePerson = URLDecoder.decode(purchasePerson, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("tenderName," + SearchCondition.ANYLIKE,
						searchContent);
				params.put("name," + SearchCondition.ANYLIKE,
						searchContent);
				pager = supplierTenderController
						.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE,
							name);
				}
				if (null != purchasePerson && !"".equals(purchasePerson)) {
					params.put("tenderName," + SearchCondition.ANYLIKE,
							purchasePerson);
				}
				pager = supplierTenderController.goSingleList(params, getPager());
			}
			logger.info("获取供应商列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSrmTenderList";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = supplierTenderController.goSubSingleList(params,
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
				purchaseTender = supplierTenderController.doListPurchaseTenderById(id);
				supplier = supplierTenderController.findSupplierById("13");
				logger.info("进入投标页面");
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
			if (null != supplierTender.getId()) {
				isSave = false;
			}
			// 设置采购订单状态为未审批
			supplierTender.setStatus("S1");
			supplierTender = supplierTenderController.doSaveSupplierTender(supplierTender);
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
	

	/** 跳转至查看评标结果界面 */
	public String goResultList() {
		return "goResultList";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SupplierTender supplierTender = supplierTenderController
					.doListEntityById(id);
			if (null != supplierTender) {
				supplierTenderController.doDeleteByEntity(supplierTender);
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

	/** 获取明细json数据 */
	public void getSupplierTendersJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseTender pt = supplierTenderController.findPurchaseTenderById(id);
				for (SupplierTender st : pt.getSupplierTenders()) {
					if(st.getType().equals("1")){
						st.setType("类型1");
					}
					if(st.getType().equals("2")){
						st.setType("类型2");
					}
					if(st.getCurrency().equals("1")){
						st.setCurrency("人民币");
					}
					if(st.getCurrency().equals("2")){
						st.setCurrency("美元");
					}
				}
				json = convertListToJson(new ArrayList<SupplierTender>(pt.getSupplierTenders()), pt.getSupplierTenders().size(), "purchaseTender");
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
//	public void getAttachmentsJson() {
//		try {
//			String json = "";
//			String id = getRequestParameter("id");
//			if (null != id && !"".equals(id)) {
//				PurchaseApply pa = supplierTenderController.findEntityById(Long
//						.parseLong(id));
//				json = convertListToJson(
//						new ArrayList<Attachments>(pa.getAttachments()), pa
//								.getAttachments().size(), "purchaseOrder");
//			}
//			if (!"".equals(json)) {
//				renderJson(json);
//			} else {
//				renderJson("{\"total\":0,\"rows\":[]}");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public String addAttachments() {
//		return "addAttachments";
//	}

	/** 上传附件 */
//	public void uploadAttachments() {
//		try {
//			String idStr = getRequestParameter("id");
//			if (null != idStr && !"".equals(idStr)) {
//				PurchaseApply pa = supplierTenderController.findEntityById(Long
//						.parseLong(idStr));
//				if (null != fileToUpload) {
//					String separator = System.getProperty("file.separator");
//					/** 上传目录 */
//					String baseDir = getServletContext().getRealPath(
//							separator + "richContent");
//					BufferedInputStream bufIn = new BufferedInputStream(
//							new FileInputStream(fileToUpload));
//					String[] fileNames = fileToUploadFileName.split("\\.");
//					String fileName = fileNames[0];
//					String extFileName = fileNames[fileNames.length - 1];
//					String newFilePath = "";
//					long timeTemp = System.currentTimeMillis();
//					String newFileName = fileName + "_" + timeTemp + "."
//							+ extFileName;
//					newFilePath = baseDir + separator + newFileName;
//					BufferedOutputStream bufOut = new BufferedOutputStream(
//							new FileOutputStream(newFilePath));
//					byte[] buf = new byte[1024 * 100];
//					int len = -1;
//					while ((len = bufIn.read(buf)) != -1) {
//						bufOut.write(buf, 0, len);
//					}
//					bufOut.close();
//					bufIn.close();
//					Attachments atts = new Attachments();
//					atts.setName(newFileName);
//					atts.setPurchaseApply(pa);
//					supplierTenderController.mergeAttachments(atts);
//					renderJson("文件上传成功!");
//				}
//			} else {
//				renderJson("订单id获取失败!");
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			renderJson("文件上传失败!");
//		}
//
//	}

	/** 删除附件 */
//	public void deleteAttachments() {
//		try {
//			String afId = getRequestParameter("afId");
//			if (null != afId && !"".equals(afId)) {
//				long afIdLong = Long.parseLong(afId);
//				if (afIdLong > 0) {
//					Attachments atts = supplierTenderController
//							.findAttachmentsEntityById(afIdLong);
//					if (null != atts) {
//						/** 上传目录 */
//						String separator = System.getProperty("file.separator");
//						String baseDir = getServletContext().getRealPath(
//								separator + "richContent");
//						baseDir += separator;
//						baseDir += atts.getName();
//						File file = new File(baseDir);
//						if (file.exists()) {
//							file.delete();
//						}
//						supplierTenderController.deleteAttachmentsEntity(atts);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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
