/**
 * 
 */
package com.vix.dtbcenter.distributioncost.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.costestimating.controller.CostEstimatingController;
import com.vix.dtbcenter.costestimating.entity.SaleOrderCost;
import com.vix.dtbcenter.costestimating.entity.SaleOrderCostItem;
import com.vix.dtbcenter.costsset.entity.CostItem;
import com.vix.dtbcenter.distributioncost.domain.DistributionCostDomain;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("distributionCostController")
@Scope("prototype")
public class DistributionCostController {

	Logger logger = Logger.getLogger(CostEstimatingController.class);

	@Autowired
	private DistributionCostDomain distributionCostDomain;

	/**
	 * 
	 * @param saleOrderCost
	 * @param saleOrderCostItemList
	 * @return
	 * @throws Exception
	 */
	public SaleOrderCost doSaveOrUpdateSaleOrderCost(SaleOrderCost saleOrderCost, List<SaleOrderCostItem> saleOrderCostItemList) throws Exception {
		SaleOrderCost saleOrderCost1 = null;
		try {
			// 执行保存操作
			saleOrderCost1 = distributionCostDomain.mergeSaleOrderCost(saleOrderCost);
			if (saleOrderCost1 != null) {
				if (saleOrderCostItemList != null && saleOrderCostItemList.size() > 0) {
					for (SaleOrderCostItem saleOrderCostItem : saleOrderCostItemList) {
						saleOrderCostItem.setSaleOrderCost(saleOrderCost1);
						distributionCostDomain.mergeSaleOrderCostItem(saleOrderCostItem);
					}
				}
			}
			// 保存日志
			executeLogger(true, "保存：" + saleOrderCost.getCode() + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + saleOrderCost.getCode() + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return saleOrderCost1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListSaleOrderCost(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			p = distributionCostDomain.findSaleOrderCostPagerByHql(params, pager);
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public Pager doListCostItem(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			p = distributionCostDomain.findCostItemPagerByHql(params, pager);
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListSaleOrderCostByCon(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			p = distributionCostDomain.findSaleOrderCostPagerByOrHql(params, pager);
		} catch (Exception ex) {
		}
		return p;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public SaleOrderCost doListSaleOrderCostById(String id) {
		SaleOrderCost saleOrderCost = null;
		try {
			// 执行查询操作
			saleOrderCost = distributionCostDomain.findSaleOrderCostById(id);
			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return saleOrderCost;
	}

	public Pager doListSalesOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = distributionCostDomain.findSalesOrderPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public CostItem doListCostItemById(String id) {
		CostItem costItem = null;
		try {
			// 3.执行查询操作
			costItem = distributionCostDomain.findCostItemById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return costItem;
	}

	/**
	 * 将费用项目转化成订单成本的明细
	 * 
	 * @param saleOrderCost
	 * @param costItem
	 * @throws Exception
	 */
	public SaleOrderCostItem convCostItemToSaleOrderCostItem(SaleOrderCost saleOrderCost, CostItem costItem) throws Exception {
		SaleOrderCostItem delieryNotification1 = null;
		if (costItem != null && saleOrderCost != null) {
			// 订单成本明细
			SaleOrderCostItem saleOrderCostItem = new SaleOrderCostItem();
			saleOrderCostItem.setCostCode(costItem.getCostCode());
			saleOrderCostItem.setCostName(costItem.getCostName());
			saleOrderCostItem.setGroupCode(costItem.getGroupCode());
			saleOrderCostItem.setCostType(costItem.getCostType());
			saleOrderCostItem.setPayments(costItem.getPayments());
			saleOrderCostItem.setUnit(costItem.getUnit());
			saleOrderCostItem.setSaleOrderCost(saleOrderCost);
			delieryNotification1 = distributionCostDomain.mergeSaleOrderCostItem(saleOrderCostItem);
		}
		return delieryNotification1;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<SaleOrderCost> doListSaleOrderCostList(Map<String, Object> params) throws Exception {
		List<SaleOrderCost> saleOrderCostList = null;

		// 3. 执行查询操作
		saleOrderCostList = distributionCostDomain.findSaleOrderCostList(params);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return saleOrderCostList;
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	/**
	 * 异常错误日志方便程序员调试
	 * 
	 * @param message
	 */
	public void executeErrorLogger(String... errorMessage) {
	}

	/**
	 * bizFlowExecute 需要对流程进行操作时调用
	 * 
	 * @param flowName
	 * @param flowParameter
	 */
	protected void bizFlowExecute(String flowName, Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName, Map<String, Object> flowParameter) {
		// to do something

	}

	/**
 * 
 */
	public void doProcessEvent() {
		// to do something

	}

	/**
	 * 
	 */
	public void doExecute() {
		// to do something
	}

	/**
	 * 
	 * @return
	 */
	public Object getIndustryOrderAction() {
		return null;
	}

	/**
	 * 
	 */
	public void doPrint() {
		// to do something
	}

	/**
	 * 
	 */
	public void doDrools() {
		// to do something
	}
}
