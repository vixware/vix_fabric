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

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CMNApprovalRecord;
import com.vix.sales.delivery.entity.DeliveryDocument;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class DeliveryCocumentApprovalOpinionAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private CMNApprovalRecord approvalRecord;
	private String pageNo;


	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				approvalRecord = baseHibernateService.findEntityById(CMNApprovalRecord.class, id);
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
			if (null != approvalRecord.getId()) {
				isSave = false;
			}else{
				approvalRecord.setCreateTime(new Date());
				loadCommonData(approvalRecord);
			}
			baseHibernateService.merge(approvalRecord);
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
			CMNApprovalRecord ddi = baseHibernateService.findEntityById(CMNApprovalRecord.class, id);
			if (null != ddi) {
				baseHibernateService.deleteByEntity(ddi);
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

	/** 获取采购订单明细json数据 */
	public void getApprovalRecordJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				DeliveryDocument dd = baseHibernateService.findEntityById(DeliveryDocument.class, id);
				json = convertListToJson(
						new ArrayList<CMNApprovalRecord>(dd.getApprovalRecords()), dd.getApprovalRecords().size(),
						"approvalRecord");
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

	public CMNApprovalRecord getApprovalRecord() {
		return approvalRecord;
	}

	public void setApprovalRecord(CMNApprovalRecord approvalRecord) {
		this.approvalRecord = approvalRecord;
	}
}
