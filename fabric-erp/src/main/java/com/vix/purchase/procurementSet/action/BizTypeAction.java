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

package com.vix.purchase.procurementSet.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.purchase.procurementSet.controller.BizTypeController;

/**
 * @Description: 采购业务类型
 * @author ivan
 * @date 2014-01-22
 */
@Controller
@Scope("prototype")
public class BizTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(BizType.class);
	/** 注入service */
	@Autowired
	private BizTypeController bizTypeController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private String id;
	private String pageNo;
	private BizType bizType;
	private List<BizType> bizTypeList;

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

	public BizType getBizType() {
		return bizType;
	}

	public void setBizType(BizType bizType) {
		this.bizType = bizType;
	}

	public List<BizType> getBizTypeList() {
		return bizTypeList;
	}

	public void setBizTypeList(List<BizType> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			bizTypeList = bizTypeController.findBizTypeIndex();
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
			Pager pager = bizTypeController.goSingleList(params, getPager());
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
			String searchContent = getRequestParameter("searchContent");
			if (null != searchContent && !"".equals(searchContent)) {
				searchContent = URLDecoder.decode(searchContent, "utf-8");
			}
			String code = getRequestParameter("code");
			String searchname = getRequestParameter("searchname");
			if (null != searchname && !"".equals(searchname)) {
				searchname = URLDecoder.decode(searchname, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if ("0".equals(i)) {
				if (null != searchContent && !"".equals(searchContent)) {
					params.put("name," + SearchCondition.ANYLIKE, searchContent);
				}
				pager = bizTypeController.goSearchList(params, getPager());
			} else {
				if (null != code && !"".equals(code)) {
					params.put("code," + SearchCondition.EQUAL, code);
				}
				if (null != searchname && !"".equals(searchname)) {
					params.put("name," + SearchCondition.ANYLIKE, searchname);
				}
				pager = bizTypeController.goSingleList(params, getPager());
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
			Pager pager = bizTypeController.goSubSingleList(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				bizType = bizTypeController.doListBizTypeById(id);
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
			if (null != bizType.getId()) {
				isSave = false;
			}
			//initEntityBaseController.initEntityBaseAttribute(bizType);

			//处理修改留痕
			billMarkProcessController.processMark(bizType, updateField);
			bizType = bizTypeController.doSaveBizType(bizType);
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
			BizType bizType = bizTypeController.doListBizTypeById(id);
			if (null != bizType) {
				bizTypeController.doDeleteByBizType(bizType);
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
