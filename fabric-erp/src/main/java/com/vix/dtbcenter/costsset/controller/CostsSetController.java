/**
 * 
 */
package com.vix.dtbcenter.costsset.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.costsset.domain.CostsSetDomain;
import com.vix.dtbcenter.costsset.entity.CostItem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("costsSetController")
@Scope("prototype")
public class CostsSetController {

	Logger logger = Logger.getLogger(CostsSetController.class);

	@Autowired
	private CostsSetDomain costsSetDomain;

	/**
	 * 
	 * @param costItem
	 * @param cardList
	 * @return
	 * @throws Exception
	 */
	public CostItem doSaveOrUpdateCostItem(CostItem costItem) throws Exception {
		CostItem costItem1 = null;
		costItem.setUpdateTime(new Date());
		costItem1 = costsSetDomain.mergeCostItem(costItem);
		return costItem1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListCostItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;

		// 执行查询操作
		p = costsSetDomain.findCostItemPager(params, pager);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return p;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CostItem doListCostItemById(String id) throws Exception {
		CostItem costItem = null;
		// 执行查询操作
		costItem = costsSetDomain.findCostItemById(id);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return costItem;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<CostItem> doListCostItem(Map<String, Object> params) throws Exception {
		List<CostItem> costItem = null;
		costItem = costsSetDomain.findCostItemIndex(params);
		return costItem;
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
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		costsSetDomain.evict(obj);
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
