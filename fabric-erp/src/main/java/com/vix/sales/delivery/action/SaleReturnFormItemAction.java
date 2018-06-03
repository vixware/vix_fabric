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
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;

/**
 * @Description: 接收页面发送的请求并调用Contoller
 * @author ivan
 * @date 2013-07-17
 */
@Controller
@Scope("prototype")
public class SaleReturnFormItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private SaleReturnFormItem saleReturnFormItem;
	private String pageNo;


	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				saleReturnFormItem = baseHibernateService.findEntityById(SaleReturnFormItem.class, id);
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
			if (null != saleReturnFormItem.getId()) {
				isSave = false;
			}else{
				saleReturnFormItem.setCreateTime(new Date());
				loadCommonData(saleReturnFormItem);
				dealBillGroupCode();
			}
			baseHibernateService.merge(saleReturnFormItem);
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
			SaleReturnFormItem ddi = baseHibernateService.findEntityById(SaleReturnFormItem.class, id);
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
	public void getSaleReturnFormItemJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				SaleReturnForm dd = baseHibernateService.findEntityById(SaleReturnForm.class, id);
				json = convertListToJson(
						new ArrayList<SaleReturnFormItem>(dd.getSaleReturnFormItems()), dd.getSaleReturnFormItems().size(),
						"saleReturnForm");
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
		if(null != saleReturnFormItem && null != saleReturnFormItem.getId()){
			Map<String,Object> p = new HashMap<String,Object>();
			p.put("billType,"+SearchCondition.EQUAL, BillType.SA_DELIVERY);
			p.put("billCode,"+SearchCondition.EQUAL, saleReturnFormItem.getId().toString());
			List<BillGroupCode> bgcList = baseHibernateService.findAllByConditions(BillGroupCode.class, p);
			for(BillGroupCode bgc : bgcList){
				baseHibernateService.deleteByEntity(bgc);
			}
			SaleReturnForm dd = baseHibernateService.findEntityById(SaleReturnForm.class, saleReturnFormItem.getId());
			Set<String> bgcSet = new HashSet<String>();
			for(SaleReturnFormItem ddi : dd.getSaleReturnFormItems()){
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

	public SaleReturnFormItem getSaleReturnFormItem() {
		return saleReturnFormItem;
	}

	public void setSaleReturnFormItem(SaleReturnFormItem saleReturnFormItem) {
		this.saleReturnFormItem = saleReturnFormItem;
	}
}
