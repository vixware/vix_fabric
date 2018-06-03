/**
 * 
 */
package com.vix.dtbcenter.driver.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.driver.domain.DriverDomain;
import com.vix.dtbcenter.driver.entity.Card;
import com.vix.dtbcenter.driver.entity.Driver;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("driverController")
@Scope("prototype")
public class DriverController {

	Logger logger = Logger.getLogger(DriverController.class);

	@Autowired
	private DriverDomain driverDomain;

	/**
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public Driver doDriver(Driver salesOrder, List<Card> cardList) throws Exception {
		Driver driver1 = null;
		salesOrder.setUpdateTime(new Date());
		driver1 = driverDomain.mergeDriver(salesOrder);
		if (cardList != null && cardList.size() > 0) {
			for (Card card : cardList) {
				card.setDriver(driver1);
				driverDomain.mergeCard(card);
			}
		}
		return driver1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListDriver(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;

		// 执行查询操作
		p = driverDomain.findDriverPagerByHqlConditions(params, pager);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListTakeDeliveryByCon(String hql, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = driverDomain.findTakeDeliveryPagerByOrHqlConditions(hql, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * @param salesOrder
	 * @throws Exception
	 */
	public void doDeleteDriver(Driver driver) throws Exception {
		// 执行删除操作
		driverDomain.deleteDriverByEntity(driver);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "！");
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Driver doListDriverById(String id) throws Exception {
		Driver driver = null;
		// 执行查询操作
		driver = driverDomain.findDriverById(id);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return driver;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<Driver> doListDriverList(Map<String, Object> params) throws Exception {
		List<Driver> driverList = null;

		// 3. 执行查询操作
		driverList = driverDomain.findDriverList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return driverList;
	}

	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void beforeEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName) {
		// to do something
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
		driverDomain.evict(obj);
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
