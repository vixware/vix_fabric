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
import com.vix.mdm.purchase.tender.entity.PurchaseTenderItem;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.purchase.tender.controller.PurchaseTenderController;

import flexjson.JSONDeserializer;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-31
 */
@Controller
@Scope("prototype")
public class PurchaseTenderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PurchaseTender.class);
	/** 注入service */
	@Autowired
	private PurchaseTenderController purchaseTenderController;

	private String id;
	private PurchaseTender purchaseTender;
	private PurchaseTender purchaseTenderDetails;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	private List<PurchaseTender> purchaseTenderList;

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

	public PurchaseTender getPurchaseTenderDetails() {
		return purchaseTenderDetails;
	}

	public void setPurchaseTenderDetails(PurchaseTender purchaseTenderDetails) {
		this.purchaseTenderDetails = purchaseTenderDetails;
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

	public List<PurchaseTender> getPurchaseTenderList() {
		return purchaseTenderList;
	}

	public void setPurchaseTenderList(List<PurchaseTender> purchaseTenderList) {
		this.purchaseTenderList = purchaseTenderList;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			purchaseTenderList = purchaseTenderController.findPurchaseTenderIndex();
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
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isParent," + SearchCondition.NOEQUAL, "null");
			Pager pager = purchaseTenderController.goSingleList(params, getPager());
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
			Pager pager = getPager();
			String i = getRequestParameter("i");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 内容
			String searchContent = getRequestParameter("searchContent");
			if (null != searchContent && !"".equals(searchContent)) {
				searchContent = URLDecoder.decode(searchContent, "utf-8");
			}
			// 名称
			String code = getRequestParameter("code");
			// 业务员
			String searchName = getRequestParameter("searchName");
			if (null != searchName && !"".equals(searchName)) {
				searchName = URLDecoder.decode(searchName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				if (searchContent != null && !"".equals(searchContent)) {
					params.put("title," + SearchCondition.ANYLIKE, searchContent);
				}
				pager = purchaseTenderController.goSingleList(params, pager);
			} else {
				if (null != searchName && !"".equals(searchName)) {
					params.put("title," + SearchCondition.ANYLIKE, searchName);
				}
				if (null != code && !"".equals(code)) {
					params.put("tenderCode," + SearchCondition.EQUAL, code);
				}
				params.put("isParent," + SearchCondition.NOEQUAL, "null");
				pager = purchaseTenderController.goSingleList(params, getPager());
			}
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
			Pager pager = purchaseTenderController.goSubSingleList(params, getPager());
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
				purchaseTender = purchaseTenderController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate2() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseTender = purchaseTenderController.doListEntityById(id);
			} else {
				purchaseTender = new PurchaseTender();
				purchaseTender.setIsTemp("1");
				purchaseTender = purchaseTenderController.doSavePurchaseTender(purchaseTender);
			}
			String isS = getRequestParameter("isS");
			String pId = getRequestParameter("pId");
			getRequest().setAttribute("isS", isS);
			getRequest().setAttribute("pId", pId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate2";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != purchaseTender.getId()) {
				isSave = false;
			}
			purchaseTender.setStatus("S1");
			// 设置采购订单状态为未审批
			purchaseTender.setIsParent("0");
			purchaseTender.setIsTemp("");
			purchaseTender = purchaseTenderController.doSavePurchaseTender(purchaseTender);
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

	/** 处理修改操作 */
	public String saveOrUpdate2() {
		boolean isSave = true;
		String isS = "";
		try {
			if (null != purchaseTender.getId()) {
				isSave = false;
			}
			purchaseTender.setIsTemp("");
			purchaseTender.setStatus("S1");
			// 设置采购订单状态为未审批
			isS = getRequestParameter("isS");
			if (!"0".equals(isS)) {
				purchaseTender.setIsParent("1");
			} else {
				String pId = getRequestParameter("pId");
				if (null != pId && !"".equals(pId)) {
					purchaseTender.setParentId(pId);
				}
			}
			// 采购订单明细
			String tiJson = getRequestParameter("tiJson").replace("\"\"", "null");
			List<PurchaseTenderItem> tiList = new JSONDeserializer<List<PurchaseTenderItem>>().use("values", PurchaseTenderItem.class).deserialize(tiJson);
			purchaseTender = purchaseTenderController.doSavePurchaseTender(purchaseTender, tiList);
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
			PurchaseTender purchaseTender = purchaseTenderController.doListEntityById(id);
			if (null != purchaseTender) {
				purchaseTenderController.doDeleteByEntity(purchaseTender);
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
	public void getPurchaseTenderJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				List<PurchaseTender> pt = purchaseTenderController.findPurchaseTenderList(id);
				json = convertListToJson(pt, pt.size(), "purchaseTender");
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
	 * 
	 * 保存子项目
	 */
	public String saveOrUpdateTenderDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseTender = purchaseTenderController.doListEntityById(id);
				if (purchaseTender != null) {
					purchaseTenderDetails.setParentId(id);
					purchaseTenderDetails.setPurchaseTender(purchaseTender);
				}
				purchaseTenderController.doSavePurchaseTender(purchaseTenderDetails);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 获取子项目json数据 */
	public void getTenderDetailsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				List<PurchaseTender> ptTemp = purchaseTenderController.findPurchaseTenderList(id);
				for (PurchaseTender var : ptTemp) {
					if ("1".equals(var.getType())) {
						var.setType("类型1");
					} else if ("2".equals(var.getType())) {
						var.setType("类型2");
					}
				}
				json = convertListToJson(ptTemp, ptTemp.size());
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

	/** 跳转到招聘计划明细页面 */
	public String goAddTenderDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseTenderDetails = purchaseTenderController.doListEntityById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTenderDetails";
	}

	/** 处理删除子项目操作 */
	public String deleteTenderDetailsById() {
		try {
			PurchaseTender ptTemp = purchaseTenderController.doListEntityById(id);
			if (null != ptTemp) {
				purchaseTenderController.doDeleteByEntity(ptTemp);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细json数据 */
	public void getTenderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchaseTender pt = purchaseTenderController.findEntityById(id);
				json = convertListToJson(new ArrayList<PurchaseTenderItem>(pt.getPurchaseTenderItems()), pt.getPurchaseTenderItems().size());
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
				PurchaseTender pt = purchaseTenderController.findEntityById(id);
				json = convertListToJson(new ArrayList<Attachments>(pt.getAttachments()), pt.getAttachments().size());
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

	/** 上传附件 */
	public void uploadAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				PurchaseTender pt = purchaseTenderController.findEntityById(id);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					String baseDir = "E:/upload";
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
					atts.setPurchaseTender(pt);
					purchaseTenderController.mergeAttachments(atts);
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

	/** 删除附件 */
	public void deleteAttachments() {
		try {
			String afId = getRequestParameter("afId");
			if (null != afId && !"".equals(afId)) {
				Attachments atts = purchaseTenderController.findAttachmentsEntityById(afId);
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
					purchaseTenderController.deleteAttachmentsEntity(atts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
