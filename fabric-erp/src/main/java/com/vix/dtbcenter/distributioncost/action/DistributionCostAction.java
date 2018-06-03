package com.vix.dtbcenter.distributioncost.action;

import java.util.ArrayList;
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
import com.vix.dtbcenter.costestimating.action.CostEstimatingAction;
import com.vix.dtbcenter.costestimating.entity.SaleOrderCost;
import com.vix.dtbcenter.costestimating.entity.SaleOrderCostItem;
import com.vix.dtbcenter.costsset.entity.CostItem;
import com.vix.dtbcenter.distributioncost.controller.DistributionCostController;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class DistributionCostAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CostEstimatingAction.class);
	@Autowired
	private DistributionCostController distributionCostController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 派车单
	 */
	private SaleOrderCost saleOrderCost;
	private List<SaleOrderCost> saleOrderCostList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			saleOrderCostList = distributionCostController.doListSaleOrderCostList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询派车单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			Pager pager = distributionCostController.doListSaleOrderCost(params, getPager());
			setPager(pager);
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改派车单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				saleOrderCost = distributionCostController.doListSaleOrderCostById(id);
				logger.info("");
			} else {
				saleOrderCost = new SaleOrderCost();
				saleOrderCost.setIsTemp("1");
				saleOrderCost = distributionCostController.doSaveOrUpdateSaleOrderCost(saleOrderCost, null);
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
			if (null != saleOrderCost.getId() && !"".equals(saleOrderCost.getId())) {
				isSave = false;
			}
			saleOrderCost.setIsTemp("");
			/** 出库明细 */
			String saleOrderCostItemJson = getRequestParameter("saleOrderCostItemJson");
			List<SaleOrderCostItem> saleOrderCostItemList = new ArrayList<SaleOrderCostItem>();
			if (saleOrderCostItemJson != null && !"".equals(saleOrderCostItemJson)) {
				saleOrderCostItemList = new JSONDeserializer<List<SaleOrderCostItem>>().use("values", SaleOrderCostItem.class).deserialize(saleOrderCostItemJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(saleOrderCost, updateField);
			saleOrderCost = distributionCostController.doSaveOrUpdateSaleOrderCost(saleOrderCost, saleOrderCostItemList);
			logger.info("对派车单进行了修改！");
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

	/**
	 * 选择项目费用
	 * 
	 * @return
	 */
	public String goChooseCostItem() {
		return "goChooseCostItem";
	}

	/**
	 * 项目费用列表
	 * 
	 * @return
	 */
	public String goCostItemList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按名称
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = distributionCostController.doListCostItem(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCostItemList";
	}

	// 将项目费用转化成订单成本的明细
	public String converterCostItemToSaleOrderCostItem() {
		String costItemids = getRequestParameter("costItemid");
		// 获取订单成本
		saleOrderCost = distributionCostController.doListSaleOrderCostById(id);
		// 将选择的项目费用编号转存成数组格式
		String[] costItemidArr = costItemids.split(",");
		if (costItemidArr != null && !"".equals(costItemidArr) && costItemidArr.length > 0) {
			for (String costItemidid : costItemidArr) {
				// 遍历项目费用编号数组
				if (costItemidid != null && !"".equals(costItemidid)) {
					// 取出项目费用编号 通过项目费用获取对应的项目
					CostItem costItem = distributionCostController.doListCostItemById(costItemidid);
					if (costItem != null) {
						try {
							// 将项目费用转化成订单成本明细
							distributionCostController.convCostItemToSaleOrderCostItem(saleOrderCost, costItem);
							renderText(SAVE_SUCCESS);
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	/**
	 * 获取订单成本明细
	 */
	public void getSaleOrderCostItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<SaleOrderCostItem> saleOrderCostItemList = new ArrayList<SaleOrderCostItem>();
			if (null != id && !"".equals(id)) {
				// 首先根据ID查询到订单成本
				SaleOrderCost saleOrderCost = distributionCostController.doListSaleOrderCostById(id);
				if (saleOrderCost != null) {
					json = convertListToJson(new ArrayList<SaleOrderCostItem>(saleOrderCost.getSaleOrderCostItems()), saleOrderCost.getSaleOrderCostItems().size(), "saleOrderCost");
				}
			}
			if (saleOrderCostItemList != null && saleOrderCostItemList.size() > 0) {
				json = convertListToJson(saleOrderCostItemList, saleOrderCostItemList.size(), "saleOrderCost");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择销售订单
	 * 
	 * @return
	 */
	public String goChooseSalesOrder() {
		return "goChooseSalesOrder";
	}

	/**
	 * 配载计划 销售订单列表
	 * 
	 * @return
	 */
	public String goSalesOrderList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按名称
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = distributionCostController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
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

	public SaleOrderCost getSaleOrderCost() {
		return saleOrderCost;
	}

	public void setSaleOrderCost(SaleOrderCost saleOrderCost) {
		this.saleOrderCost = saleOrderCost;
	}

	public List<SaleOrderCost> getSaleOrderCostList() {
		return saleOrderCostList;
	}

	public void setSaleOrderCostList(List<SaleOrderCost> saleOrderCostList) {
		this.saleOrderCostList = saleOrderCostList;
	}

}
