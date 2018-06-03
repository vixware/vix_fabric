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

package com.vix.drp.demandOrder.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 
 * com.vix.drp.demandOrder.action.DemandOrderAction
 *
 * @author bjitzhang
 *
 * @date 2015年3月19日
 */
@Controller
@Scope("prototype")
public class DemandOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String pageNo;
	private String parentId;
	private String treeType;
	private String purchaseOrderId;
	private String salesChannelDistributorId;
	private PurchaseOrder purchaseOrder;
	private List<PurchaseOrder> purchaseOrderList;

	private PurchaseOrderLineItem purchaseOrderLineItem;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			if (parentId != null) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			}
			purchaseOrderList = baseHibernateService.findAllByConditions(PurchaseOrder.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			if (StringUtils.isEmpty(parentId)) {
				// 获取当前登录用户所在的公司或供应商
				// 获取当前员工信息
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null && employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					}
				}
			} else {
				if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType) && "CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String code = getRequestParameter("code");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.EQUAL, name.trim());
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code.trim());
			}

			//处理搜索条件
			pager = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, id);
			} else {
				purchaseOrder = new PurchaseOrder();
				purchaseOrder.setIsTemp("1");

				if (StringUtils.isNotEmpty(parentId) && !"undefined".equals(parentId) && StringUtils.isNotEmpty(treeType)) {
					if ("C".equals(treeType)) {
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, parentId);
						if (channelDistributor != null) {
							purchaseOrder.setChannelDistributor(channelDistributor);
							if (channelDistributor.getParentChannelDistributor() != null) {
								purchaseOrder.setSalesChannelDistributor(channelDistributor.getParentChannelDistributor());
							}
						}
					}
				}
				purchaseOrder.setCode(VixUUID.createCode(10));
				Employee employee = getEmployee();
				if (employee != null) {
					purchaseOrder.setCreator(employee.getName());
				} else {
					purchaseOrder.setCreator(SecurityUtil.getCurrentUserName());
				}
				purchaseOrder = baseHibernateService.merge(purchaseOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != purchaseOrder.getId() && !"".equals(purchaseOrder.getId())) {
				isSave = false;
			}

			if (StringUtils.isEmpty(salesChannelDistributorId) || StringUtils.isEmpty(treeType)) {
			} else {
				if ("C".equals(treeType)) {
					Organization organization = baseHibernateService.findEntityById(Organization.class, salesChannelDistributorId);
					if (organization != null) {
						purchaseOrder.setOrganization(organization);
					}
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, salesChannelDistributorId);
					if (channelDistributor != null) {
						purchaseOrder.setSalesChannelDistributor(channelDistributor);
					}
				}
			}
			purchaseOrder.setIsTemp("");
			//处理中文索引
			String py = ChnToPinYin.getPYString(purchaseOrder.getName());
			purchaseOrder.setChineseCharacter(py.toUpperCase());
			//处理修改留痕
			billMarkProcessController.processMark(purchaseOrder, updateField);
			purchaseOrder = baseHibernateService.merge(purchaseOrder);

			//生成销售订单
			SalesOrder salesOrder = new SalesOrder();
			salesOrder.setCode(autoCreateCode.getBillNO(BillType.SA_ORDER));
			salesOrder.setName(purchaseOrder.getName());
			salesOrder.setBuyerNick(purchaseOrder.getChannelDistributor().getName());
			salesOrder.setCreateTime(new Date());
			salesOrder.setIsTemp("");

			if (purchaseOrder.getSalesChannelDistributor() != null) {
				salesOrder.setChannelDistributor(purchaseOrder.getSalesChannelDistributor());
			}
			if (purchaseOrder.getOrganization() != null) {
				salesOrder.setOrganization(purchaseOrder.getOrganization());
			}
			salesOrder = baseHibernateService.merge(salesOrder);
			Double price = 0D;
			Double num = 0D;
			Set<PurchaseOrderLineItem> purchasePlanItemsset = purchaseOrder.getPurchaseOrderLineItems();
			for (PurchaseOrderLineItem purchasePlanItems : purchasePlanItemsset) {
				SaleOrderItem saleOrderItem = new SaleOrderItem();
				saleOrderItem.setOuterGoodsId(purchasePlanItems.getItemCode());
				saleOrderItem.setTitle(purchasePlanItems.getItemName());
				saleOrderItem.setPrice(purchasePlanItems.getPrice());
				//saleOrderItem.setNum(purchasePlanItems.getAmount());
				saleOrderItem.setPayment(purchasePlanItems.getNetTotal());
				saleOrderItem.setSalesOrder(salesOrder);
				saleOrderItem = baseHibernateService.merge(saleOrderItem);
				price += purchasePlanItems.getNetTotal();
				num += saleOrderItem.getNum();
			}
			salesOrder.setPayment(price);
			salesOrder.setNum(num);
			initEntityBaseController.initEntityBaseAttribute(salesOrder);
			salesOrder = baseHibernateService.merge(salesOrder);

			//生成销售订单

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
			PurchasePlan pb = baseHibernateService.findEntityById(PurchasePlan.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(new String(idStr));
					}
				}
				baseHibernateService.deleteById(PurchasePlan.class, id);
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

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdatePurchaseOrderLineItem() {
		boolean isSave = true;
		try {
			if (null != purchaseOrderLineItem.getId() && !"".equals(purchaseOrderLineItem.getId())) {
				isSave = false;
			}
			if (purchaseOrderLineItem.getAmount() != null && purchaseOrderLineItem.getPrice() != null) {
				purchaseOrderLineItem.setNetTotal(purchaseOrderLineItem.getAmount() * purchaseOrderLineItem.getPrice());
			}
			baseHibernateService.merge(purchaseOrderLineItem);
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

	/** 获取json数据 */
	public void getPurchaseOrderLineItemJson() {
		try {
			String json = "";
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PurchaseOrder purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, id);
				if (null != purchaseOrder) {
					json = convertListToJson(new ArrayList<PurchaseOrderLineItem>(purchaseOrder.getPurchaseOrderLineItems()), purchaseOrder.getPurchaseOrderLineItems().size());
				}
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

	/** 处理删除操作 */
	public String deletePurchaseOrderLineItemById() {
		try {
			PurchaseOrderLineItem purchaseOrderLineItem = baseHibernateService.findEntityById(PurchaseOrderLineItem.class, id);
			if (null != purchaseOrderLineItem) {
				baseHibernateService.deleteByEntity(purchaseOrderLineItem);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 弹出库存信息
	 * 
	 * @return
	 */
	public String goPurchaseOrderLineItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseOrderLineItem = baseHibernateService.findEntityById(PurchaseOrderLineItem.class, id);
			} else {
				purchaseOrderLineItem = new PurchaseOrderLineItem();
				if (purchaseOrderId != null && !"".equals(purchaseOrderId)) {
					PurchaseOrder purchaseOrder = baseHibernateService.findEntityById(PurchaseOrder.class, purchaseOrderId);
					purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goPurchaseOrderLineItem";
	}

	public String goPurchaseOrderLineItemListContent() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			//获取所选经销商信息
			ChannelDistributor channelDistributor = null;
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, parentId);
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null && employee.getChannelDistributor() != null) {
						channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
					}
				}
			}
			if (channelDistributor != null) {
				Set<Item> itemSet = channelDistributor.getMdmItem();
				String itemId = "";
				for (Item item : itemSet) {
					itemId += "," + item.getId();
				}
				params.put("id," + SearchCondition.IN, itemId);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goPurchaseOrderLineItemListContent";
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public PurchaseOrderLineItem getPurchaseOrderLineItem() {
		return purchaseOrderLineItem;
	}

	public void setPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
		this.purchaseOrderLineItem = purchaseOrderLineItem;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	/**
	 * @return the salesChannelDistributorId
	 */
	public String getSalesChannelDistributorId() {
		return salesChannelDistributorId;
	}

	/**
	 * @param salesChannelDistributorId
	 *            the salesChannelDistributorId to set
	 */
	public void setSalesChannelDistributorId(String salesChannelDistributorId) {
		this.salesChannelDistributorId = salesChannelDistributorId;
	}

}
