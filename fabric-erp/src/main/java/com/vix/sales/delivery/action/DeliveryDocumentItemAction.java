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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.core.constant.SearchCondition;
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.DeliveryDocumentItem;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class DeliveryDocumentItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private DeliveryDocumentItem deliveryDocumentItem;
	private String pageNo;


	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				deliveryDocumentItem = baseHibernateService.findEntityById(DeliveryDocumentItem.class, id);
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
			if (null != deliveryDocumentItem.getId()) {
				isSave = false;
			}else{
				deliveryDocumentItem.setCreateTime(new Date());
				loadCommonData(deliveryDocumentItem);
				dealBillGroupCode();
			}
			baseHibernateService.merge(deliveryDocumentItem);
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
			DeliveryDocumentItem ddi = baseHibernateService.findEntityById(DeliveryDocumentItem.class, id);
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
	public void getDeliveryDocumentItemJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				DeliveryDocument dd = baseHibernateService.findEntityById(DeliveryDocument.class, id);
				json = convertListToJson(
						new ArrayList<DeliveryDocumentItem>(dd.getDeliveryDocumentItems()), dd.getDeliveryDocumentItems().size(),
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

	private void dealBillGroupCode() throws Exception{
		if(null != deliveryDocumentItem && null != deliveryDocumentItem.getId()){
			Map<String,Object> p = new HashMap<String,Object>();
			p.put("billType,"+SearchCondition.EQUAL, BillType.SA_DELIVERY);
			p.put("billCode,"+SearchCondition.EQUAL, deliveryDocumentItem.getId().toString());
			List<BillGroupCode> bgcList = baseHibernateService.findAllByConditions(BillGroupCode.class, p);
			for(BillGroupCode bgc : bgcList){
				baseHibernateService.deleteByEntity(bgc);
			}
			DeliveryDocument dd = baseHibernateService.findEntityById(DeliveryDocument.class, deliveryDocumentItem.getId());
			Set<String> bgcSet = new HashSet<String>();
			for(DeliveryDocumentItem ddi : dd.getDeliveryDocumentItems()){
				if(null != ddi && null != ddi.getGroupCode() && !"".equals(ddi.getGroupCode())){
					bgcSet.add(ddi.getGroupCode());
				}
			}
			if(bgcSet.size() > 0){
				for(String bgc : bgcSet){
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

	public DeliveryDocumentItem getDeliveryDocumentItem() {
		return deliveryDocumentItem;
	}

	public void setDeliveryDocumentItem(DeliveryDocumentItem deliveryDocumentItem) {
		this.deliveryDocumentItem = deliveryDocumentItem;
	}
}
