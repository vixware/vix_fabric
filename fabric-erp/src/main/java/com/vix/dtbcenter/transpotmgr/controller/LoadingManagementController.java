/**
 * 
 */
package com.vix.dtbcenter.transpotmgr.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.transpotmgr.domain.LoadingManagementDomain;
import com.vix.dtbcenter.transpotmgr.entity.LoadingManagement;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("loadingManagementController")
@Scope("prototype")
public class LoadingManagementController {

	Logger logger = Logger.getLogger(LoadingManagementController.class);

	@Autowired
	private LoadingManagementDomain loadingManagementDomain;

	/**
	 * 
	 * @param truckingOrder
	 * @param stockTakingItemList
	 * @return
	 * @throws Exception
	 */
	public LoadingManagement doSaveTruckingOrder(LoadingManagement truckingOrder, List<Object> stockTakingItemList) throws Exception {
		LoadingManagement truckingOrder1 = null;
		try {
			// 执行保存操作
			truckingOrder.setName("派车单" + truckingOrder.getCode());
			truckingOrder1 = loadingManagementDomain.mergeTruckingOrder(truckingOrder);
			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存：" + truckingOrder.getCode() + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + truckingOrder.getCode() + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return truckingOrder1;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListTruckingOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = loadingManagementDomain.findTruckingOrderPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
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
	public Pager doListTruckingOrderByCon(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 3.执行查询操作
			p = loadingManagementDomain.findTruckingOrderPagerByOrHqlConditions(params, pager);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询派车单成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询派车单失败！失败原因：" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 
	 * 
	 * @param truckingOrder
	 */
	public void doDeleteByTruckingOrder(LoadingManagement truckingOrder) {
		try {
			// 3. 执行删除操作
			loadingManagementDomain.deleteByEntity(truckingOrder);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
	}

	/**
	 * doDeleteByIds实现删除派车单的业务逻辑处理
	 * 
	 * @param ids
	 */
	public void doDeleteByIds(List<String> ids) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			loadingManagementDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public LoadingManagement doListTruckingOrderById(String id) {
		LoadingManagement truckingOrder = null;
		try {

			// 3.执行查询操作
			truckingOrder = loadingManagementDomain.findTruckingOrderById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return truckingOrder;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<LoadingManagement> doListTruckingOrderList(Map<String, Object> params) throws Exception {
		List<LoadingManagement> truckingOrder = null;

		// 3. 执行查询操作
		truckingOrder = loadingManagementDomain.findTruckingOrderList(params);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "");
		return truckingOrder;
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
