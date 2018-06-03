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

package com.vix.pm.milestonereport.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.pm.milestonereport.entity.MilestoneReport;

@Controller
@Scope("prototype")
public class MilestoneReportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String pageNo;
	private MilestoneReport milestoneReport;

	private List<MilestoneReport> milestoneReportList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			milestoneReportList = baseHibernateService.findAllByEntityClass(MilestoneReport.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			/* 高级查询 */
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MilestoneReport.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				milestoneReport = baseHibernateService.findEntityById(MilestoneReport.class, id);
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
			if (null != milestoneReport.getId()) {
				isSave = false;
			}
			milestoneReport = baseHibernateService.merge(milestoneReport);
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
			MilestoneReport cd = baseHibernateService.findEntityById(MilestoneReport.class, id);
			if (null != cd) {
				baseHibernateService.deleteByEntity(cd);
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

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(MilestoneReport.class, delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MilestoneReport getMilestoneReport() {
		return milestoneReport;
	}

	public void setMilestoneReport(MilestoneReport milestoneReport) {
		this.milestoneReport = milestoneReport;
	}

	public List<MilestoneReport> getMilestoneReportList() {
		return milestoneReportList;
	}

	public void setMilestoneReportList(List<MilestoneReport> milestoneReportList) {
		this.milestoneReportList = milestoneReportList;
	}

}
