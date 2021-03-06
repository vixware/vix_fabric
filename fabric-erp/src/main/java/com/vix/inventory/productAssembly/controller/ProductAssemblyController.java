/**
 * 
 */
package com.vix.inventory.productAssembly.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.inventory.productAssembly.domain.ProductAssemblyDomain;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("productAssemblyController")
@Scope("prototype")
public class ProductAssemblyController {

	Logger logger = Logger.getLogger(ProductAssemblyController.class);

	@Autowired
	private ProductAssemblyDomain productAssemblyDomain;
	/**
	 * 初始化基础数据
	 */
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	/**
	 * 
	 * @param inventoryCurrentStock
	 * @return
	 * @throws Exception
	 */
	public InventoryCurrentStock doSaveInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		InventoryCurrentStock inventoryCurrentStock1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		if (inventoryCurrentStock != null && inventoryCurrentStock.getId() != null && !"".equals(inventoryCurrentStock.getId())) {
			inventoryCurrentStock1 = productAssemblyDomain.findInventoryCurrentStockById(inventoryCurrentStock.getId());
			inventoryCurrentStock1.setItemcode(inventoryCurrentStock.getItemcode());
			inventoryCurrentStock1.setItemname(inventoryCurrentStock.getItemname());
			inventoryCurrentStock1.setSpecification(inventoryCurrentStock.getSpecification());
			inventoryCurrentStock1.setPrice(inventoryCurrentStock.getPrice());
			inventoryCurrentStock1.setMasterUnit(inventoryCurrentStock.getMasterUnit());
			inventoryCurrentStock1.setBindingNumber(inventoryCurrentStock.getBindingNumber());
			inventoryCurrentStock1.setQuantity(inventoryCurrentStock.getQuantity());
			inventoryCurrentStock1.setIsTemp(inventoryCurrentStock.getIsTemp());
			inventoryCurrentStock1.setIsBinding(inventoryCurrentStock.getIsBinding());
			inventoryCurrentStock1.setInventoryCurrentStock(inventoryCurrentStock.getInventoryCurrentStock());
			inventoryCurrentStock1.setWarehouse(inventoryCurrentStock.getWarehouse());
			inventoryCurrentStock1.setWarehousecode(inventoryCurrentStock.getWarehousecode());
			inventoryCurrentStock1.setIsQualfied(inventoryCurrentStock.getIsQualfied());
			inventoryCurrentStock1.setAvaquantity(inventoryCurrentStock.getQuantity());
			inventoryCurrentStock1 = productAssemblyDomain.mergeInventoryCurrentStock(inventoryCurrentStock1);
		} else {
			initEntityBaseController.initEntityBaseAttribute(inventoryCurrentStock1);
			inventoryCurrentStock1 = productAssemblyDomain.mergeInventoryCurrentStock(inventoryCurrentStock);
		}
		/* 处理库存的增减问题 */
		Set<InventoryCurrentStock> inventoryCurrentStockList = inventoryCurrentStock1.getInventoryCurrentStockList();
		if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
			for (InventoryCurrentStock invCurrentStock : inventoryCurrentStockList) {
				if (invCurrentStock != null && invCurrentStock.getQuantity() != null && inventoryCurrentStock1 != null && invCurrentStock.getBindingNumber() != null && inventoryCurrentStock1.getQuantity() != null) {
					invCurrentStock.setQuantity(invCurrentStock.getQuantity() - invCurrentStock.getBindingNumber() * inventoryCurrentStock1.getQuantity());

					if (invCurrentStock.getAvaquantity() != null) {
						invCurrentStock.setAvaquantity(invCurrentStock.getAvaquantity() - invCurrentStock.getBindingNumber() * inventoryCurrentStock1.getQuantity());
					} else {
					}
					invCurrentStock = productAssemblyDomain.mergeInventoryCurrentStock(invCurrentStock);
				}
			}
		}
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return inventoryCurrentStock1;
	}

	public Item doSaveItem(Item item) throws Exception {
		Item item1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		item1 = productAssemblyDomain.saveItem(item);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return item1;
	}

	public List<MeasureUnit> doListMeasureUnitList(Map<String, Object> params) throws Exception {
		List<MeasureUnit> measureUnitList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		measureUnitList = productAssemblyDomain.findMeasureUnitList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return measureUnitList;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListInventoryCurrentStock(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		p = productAssemblyDomain.findInventoryCurrentStockPagerByHqlConditions(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

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
	public InventoryCurrentStock doListInventoryCurrentStockById(String id) throws Exception {
		InventoryCurrentStock inventoryCurrentStock = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStock = productAssemblyDomain.findInventoryCurrentStockById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStock;
	}

	public List<InventoryCurrentStock> doListInventoryCurrentStock() throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStockList = productAssemblyDomain.findInventoryCurrentStock();
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStockList;
	}

	/**
	 * 
	 * 
	 * @param inventoryCurrentStock
	 * @throws Exception
	 */
	public void doDeleteByEntity(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		productAssemblyDomain.deleteByEntity(inventoryCurrentStock);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void doDeleteByIds(List<String> ids) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		productAssemblyDomain.deleteByIds(ids);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<InventoryCurrentStock> doListInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		inventoryCurrentStockList = productAssemblyDomain.findInventoryCurrentStockList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return inventoryCurrentStockList;
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
