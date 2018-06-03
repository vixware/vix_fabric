/**
 * 
 */
package com.vix.inventory.unacceptedproduct.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.unacceptedproduct.domain.NonconformingProductDomain;
import com.vix.inventory.unacceptedproduct.entity.NonconformingProduct;
import com.vix.inventory.unacceptedproduct.entity.NonconformingProductDetails;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("nonconformingProductController")
@Scope("prototype")
public class NonconformingProductController {

	Logger logger = Logger.getLogger(NonconformingProductController.class);

	@Autowired
	private NonconformingProductDomain nonconformingProductDomain;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/**
	 * 
	 * @param nonconformingProduct
	 * @return
	 * @throws Exception
	 */
	public NonconformingProduct doSaveNonconformingProduct(NonconformingProduct nonconformingProduct) throws Exception {
		NonconformingProduct nonconformingProduct1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		nonconformingProduct1 = nonconformingProductDomain.mergeNonconformingProduct(nonconformingProduct);
		// 根据不合格品明细 增加库存残次品的数量
		if (nonconformingProduct1.getNonconformingProductDetailss() != null && nonconformingProduct1.getNonconformingProductDetailss().size() > 0) {
			for (NonconformingProductDetails nonconformingProductDetails : nonconformingProduct1.getNonconformingProductDetailss()) {
				if (nonconformingProductDetails != null) {
					// 需要通过仓库 及sku编码进行唯一存储
					Map<String, Object> params = new HashMap<String, Object>();
					// 过滤临时数据
					params.put("isQualfied", 1);
					params.put("skuCode", nonconformingProductDetails.getSkuCode());
					params.put("invWarehouseId", nonconformingProductDetails.getInvWarehouse().getId());
					params.put("batchcode", nonconformingProductDetails.getBatchcode());
					StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
					InventoryCurrentStock inv = nonconformingProductDomain.findInventoryCurrentStockByHql(hql.toString(), params);
					if (inv != null) {
						if (nonconformingProductDetails.getQuantity() != null) {
							if (inv.getQuantity() != null) {
								inv.setQuantity(inv.getQuantity() + nonconformingProductDetails.getQuantity());
							}
						}
						nonconformingProductDomain.saveOrUpdateInventoryCurrentStock(inv);
					} else {
						InventoryCurrentStock inventory = new InventoryCurrentStock();
						inventory.setItemcode(nonconformingProductDetails.getItemcode());
						inventory.setItemname(nonconformingProductDetails.getItemname());
						inventory.setUnit(nonconformingProductDetails.getUnit());
						inventory.setIsTemp("");
						inventory.setSkuCode(nonconformingProductDetails.getSkuCode());
						inventory.setPrice(nonconformingProductDetails.getUnitcost());
						inventory.setInvWarehouse(nonconformingProductDetails.getInvWarehouse());
						inventory.setWarehousecode(nonconformingProductDetails.getInvWarehouse().getCode());
						inventory.setWarehouse(nonconformingProductDetails.getInvWarehouse().getName());
						inventory.setIsQualfied(1);
						//不合格品可用数量为0
						inventory.setAvaquantity(0D);
						inventory.setInvShelf(nonconformingProductDetails.getInvShelf());
						inventory.setInvShelfName(nonconformingProductDetails.getInvShelf().getName());
						inventory.setBatchcode(nonconformingProductDetails.getBatchcode());
						inventory.setQuantity(nonconformingProductDetails.getQuantity());
						initEntityBaseController.initEntityBaseAttribute(inventory);
						inventory = nonconformingProductDomain.saveOrUpdateInventoryCurrentStock(inventory);
						//查询是否开启保质期管理
						InventoryParameters inventoryParameters = nonconformingProductDomain.findInventoryParametersByByAttribute("tenantId", nonconformingProductDetails.getTenantId());
						// 根据库存设置如果启用批次管理,要创建批次主文件 ,同时入库单明细上增加批次号
						if (inventoryParameters != null && "1".equals(inventoryParameters.getIsBatch())) {
							if (nonconformingProductDetails.getBatchcode() != null && !"".equals(nonconformingProductDetails.getBatchcode())) {
								InvMainBatch invMainBatch = nonconformingProductDomain.findInvMainBatch("batchCode", nonconformingProductDetails.getBatchcode());
								if (invMainBatch != null) {
									/* 将商品与批次建立关系 */
									inventory.setInvMainBatch(invMainBatch);
									inventory = nonconformingProductDomain.saveOrUpdateInventoryCurrentStock(inventory);
								} else {
									invMainBatch = new InvMainBatch();
									/* 向批次主文件赋值 */
									invMainBatch.setItemName(nonconformingProductDetails.getItemname());
									invMainBatch.setBatchCode(nonconformingProductDetails.getBatchcode());
									invMainBatch = nonconformingProductDomain.saveOrUpdateInvMainBatch(invMainBatch);
									/* 将商品与批次建立关系 */
									inventory.setInvMainBatch(invMainBatch);
									inventory = nonconformingProductDomain.saveOrUpdateInventoryCurrentStock(inventory);
								}
							}
						}
					}
				}
			}
		}
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return nonconformingProduct1;
	}

	/**
	 * 
	 * @param nonconformingProductDetails
	 * @return
	 * @throws Exception
	 */
	public NonconformingProductDetails doSaveNonconformingProductDetails(NonconformingProductDetails nonconformingProductDetails) throws Exception {
		NonconformingProductDetails nonconformingProductDetails1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		nonconformingProductDetails1 = nonconformingProductDomain.mergeNonconformingProductDetails(nonconformingProductDetails);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return nonconformingProductDetails1;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListNonconformingProduct(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 执行保存操作
		p = nonconformingProductDomain.findNonconformingProduct(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public List<NonconformingProductDetails> doListNonconformingProductDetailsList(Map<String, Object> params) throws Exception {
		List<NonconformingProductDetails> nonconformingProductDetails = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		nonconformingProductDetails = nonconformingProductDomain.findNonconformingProductDetailsList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return nonconformingProductDetails;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListNonconformingProductByCon(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = nonconformingProductDomain.findNonconformingProductPagerByOrHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param nonconformingProduct
	 * @throws Exception
	 */
	public void doDeleteByEntity(NonconformingProduct nonconformingProduct) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		nonconformingProductDomain.deleteByEntity(nonconformingProduct);
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
		nonconformingProductDomain.deleteByIds(ids);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NonconformingProduct doListNonconformingProductById(String id) throws Exception {
		NonconformingProduct nonconformingProduct = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		nonconformingProduct = nonconformingProductDomain.findNonconformingProductById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return nonconformingProduct;
	}

	public NonconformingProductDetails doListNonconformingProductDetailsById(String id) throws Exception {
		NonconformingProductDetails nonconformingProductDetails = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		nonconformingProductDetails = nonconformingProductDomain.findNonconformingProductDetailsById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return nonconformingProductDetails;
	}

	public void deleteNonconformingProductDetails(NonconformingProductDetails nonconformingProductDetails) throws Exception {
		nonconformingProductDomain.deleteNonconformingProductDetailsEntity(nonconformingProductDetails);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<NonconformingProduct> doListNonconformingProductList(Map<String, Object> params) throws Exception {

		List<NonconformingProduct> nonconformingProduct = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		nonconformingProduct = nonconformingProductDomain.findNonconformingProductList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return nonconformingProduct;
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
