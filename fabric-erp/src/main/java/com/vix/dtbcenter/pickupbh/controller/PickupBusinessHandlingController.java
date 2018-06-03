/**
 * 
 */
package com.vix.dtbcenter.pickupbh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.pickupbh.domain.PickupBusinessHandlingDomain;
import com.vix.dtbcenter.pickupbh.entity.LoadBill;
import com.vix.dtbcenter.pickupbh.entity.LoadBillItem;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("pickupBusinessHandlingController")
@Scope("prototype")
public class PickupBusinessHandlingController {

	Logger logger = Logger.getLogger(PickupBusinessHandlingController.class);

	@Autowired
	private PickupBusinessHandlingDomain pickupBusinessHandlingDomain;

	/**
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public LoadBill doSaveLoadBill(LoadBill loadBill, List<LoadBillItem> loadBillItemList) throws Exception {
		LoadBill loadBill1 = null;
		try {
			loadBill.setUpdateTime(new Date());
			loadBill.setName("提货单" + loadBill.getLoadBillCode());
			loadBill1 = pickupBusinessHandlingDomain.mergeLoadBill(loadBill);
			if (loadBillItemList != null && loadBillItemList.size() > 0) {
				for (LoadBillItem loadBillItem : loadBillItemList) {
					loadBillItem.setLoadBill(loadBill1);
					pickupBusinessHandlingDomain.mergeLoadBillItem(loadBillItem);
				}
			}
			executeLogger(true, "" + loadBill1 + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + loadBill1 + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return loadBill1;
	}

	public LoadBillItem doSaveLoadBillItem(LoadBillItem loadBillItem) throws Exception {
		LoadBillItem loadBillItem1 = null;
		try {
			loadBillItem.setUpdateTime(new Date());
			if (loadBillItem.getPrice() != null && !"".equals(loadBillItem.getPrice()) && loadBillItem.getAmount() != null && !"".equals(loadBillItem.getAmount())) {
				loadBillItem.setTaxTotal(loadBillItem.getPrice() * loadBillItem.getAmount());
			}
			loadBillItem1 = pickupBusinessHandlingDomain.mergeLoadBillItem(loadBillItem);
			executeLogger(true, "" + loadBillItem1 + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + loadBillItem1 + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return loadBillItem1;
	}

	public Item findItemById(String id) throws Exception {
		return pickupBusinessHandlingDomain.findItemById(id);
	}

	public SpecificationDetail findSpecificationDetailEntityById(String id) throws Exception {
		return pickupBusinessHandlingDomain.findSpecificationDetailEntityById(id);
	}

	/** 获取列表数据 */
	public Pager goSingleItemList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = pickupBusinessHandlingDomain.findItemPagerByHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListTakeDelivery(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			p = pickupBusinessHandlingDomain.findTakeDeliveryPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
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
	public Pager doListTakeDeliveryByCon(String hql, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			p = pickupBusinessHandlingDomain.findTakeDeliveryPagerByOrHqlConditions(hql, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public LoadBill doListLoadBillById(String id) {
		LoadBill loadBill = null;
		try {

			// 3.执行查询操作
			loadBill = pickupBusinessHandlingDomain.findLoadBillById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return loadBill;
	}

	public Item doListItemById(String id) {
		Item item = null;
		try {

			// 3.执行查询操作
			item = pickupBusinessHandlingDomain.findItemById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询商品信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询商品信息失败！" + "失败原因:" + ex.getMessage());
		}

		return item;
	}

	public Pager getItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = pickupBusinessHandlingDomain.findItem(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception 
	 */
	public List<LoadBill> doListLoadBillList(Map<String, Object> params) throws Exception {
		List<LoadBill> loadBill = null;

		// 3. 执行查询操作
		loadBill = pickupBusinessHandlingDomain.findLoadBillList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return loadBill;
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
		pickupBusinessHandlingDomain.evict(obj);
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
