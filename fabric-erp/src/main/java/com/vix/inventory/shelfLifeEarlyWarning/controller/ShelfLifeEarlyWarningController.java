/**
 * 
 */
package com.vix.inventory.shelfLifeEarlyWarning.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.inventory.shelfLifeEarlyWarning.domain.ShelfLifeEarlyWarningDomain;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("shelfLifeEarlyWarningController")
@Scope("prototype")
public class ShelfLifeEarlyWarningController {

	Logger logger = Logger.getLogger(ShelfLifeEarlyWarningController.class);

	@Autowired
	private ShelfLifeEarlyWarningDomain shelfLifeEarlyWarningDomain;

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {

		// 3.执行保存操作
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return shelfLifeEarlyWarningDomain.findInventoryCurrentStockPager(params, pager);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<InventoryCurrentStock> doListInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		// 3. 执行查询操作
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return shelfLifeEarlyWarningDomain.findInventoryCurrentStockList(params);
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
