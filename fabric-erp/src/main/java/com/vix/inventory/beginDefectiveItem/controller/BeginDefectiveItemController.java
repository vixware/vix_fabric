/**
 * 
 */
package com.vix.inventory.beginDefectiveItem.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.web.Pager;
import com.vix.inventory.beginDefectiveItem.domain.BeginDefectiveItemDomain;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("beginDefectiveItemController")
@Scope("prototype")
public class BeginDefectiveItemController {

	Logger logger = Logger.getLogger(BeginDefectiveItemController.class);

	@Autowired
	private BeginDefectiveItemDomain beginDefectiveItemDomain;

	/**
	 * 
	 * @param inventoryCurrentStock
	 * @return
	 * @throws Exception
	 */
	public InventoryCurrentStock doSaveInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		InventoryCurrentStock inventoryCurrentStock1 = null;
		try {
			// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 执行保存操作
			inventoryCurrentStock1 = beginDefectiveItemDomain.mergeInventoryCurrentStock(inventoryCurrentStock);
			// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存：" + inventoryCurrentStock + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + inventoryCurrentStock + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return inventoryCurrentStock1;
	}

	public List<ObjectExpand> doListObjectExpandList() {
		List<ObjectExpand> objectExpandList = null;
		try {
			objectExpandList = beginDefectiveItemDomain.findObjectExpandList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectExpandList;
	}

	public List<Specification> doListSpecification(String attribute, String value, Map<String, Object> params) {
		List<Specification> specificationList = null;
		try {
			specificationList = beginDefectiveItemDomain.findSpecificationList(attribute, value, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return specificationList;
	}

	public List<SpecificationDetail> doListSpecificationDetail(String attribute, String value, Map<String, Object> params) {
		List<SpecificationDetail> specificationDetailList = null;
		try {
			specificationDetailList = beginDefectiveItemDomain.findSpecificationDetailList(attribute, value, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return specificationDetailList;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListItem(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 执行保存操作
			p = beginDefectiveItemDomain.findItemPagerByHqlConditions(params, pager);

			// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	public Item doListItemById(String id) {
		Item item = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			item = beginDefectiveItemDomain.findItemById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return item;
	}

	public SpecificationDetail doListSpecificationDetailById(String id) {
		SpecificationDetail specificationDetail = null;
		try {
			specificationDetail = beginDefectiveItemDomain.findSpecificationDetailById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return specificationDetail;
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
