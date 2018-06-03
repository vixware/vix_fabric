/**
 * 
 */
package com.vix.inventory.transfer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.transfer.domain.TransferDomain;
import com.vix.inventory.transfer.entity.WimTransvouch;
import com.vix.inventory.transfer.entity.WimTransvouchitem;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.inventory.transfer.controller.TransferController
 *
 * @date 2017年12月27日
 */
@Controller("transferController")
@Scope("prototype")
public class TransferController {

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger(TransferController.class);

	@Autowired
	private TransferDomain transferDomain;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/**
	 * 处理库存数据
	 * 
	 * @param transvouch
	 * @return
	 * @throws Exception
	 */
	public WimTransvouch doSaveWimTransvouch(WimTransvouch transvouch) throws Exception {
		WimTransvouch transvouch1 = transferDomain.mergeWimTransvouch(transvouch);
		if (transvouch1 != null) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("wimTransvouch.id," + SearchCondition.EQUAL, transvouch1.getId());
			p.put("isUpdateStore," + SearchCondition.EQUAL, "1");
			List<WimTransvouchitem> wimTransvouchitemList = transferDomain.findWimTransvouchitemList(p);
			if (wimTransvouchitemList != null && wimTransvouchitemList.size() > 0) {
				for (WimTransvouchitem transvouchitem : wimTransvouchitemList) {
					if (transvouchitem != null) {
						// 调出仓库的库存数量减少
						// 需要通过仓库 及sku编码进行唯一存储
						Map<String, Object> params = new HashMap<String, Object>();
						// 过滤临时数据
						params.put("isQualfied", 1);
						if (StringUtils.isNotEmpty(transvouchitem.getSkuCode())) {
							params.put("skuCode", transvouchitem.getSkuCode());
						}
						if (StringUtils.isNotEmpty(transvouchitem.getItemcode())) {
							params.put("itemcode", transvouchitem.getItemcode());
						}
						params.put("invWarehouseId", transvouch1.getOutInvWarehouse().getId());
						StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
						InventoryCurrentStock inventoryCurrentStock = transferDomain.findInventoryCurrentStockByHql(hql.toString(), params);
						if (inventoryCurrentStock != null) {
							if (transvouchitem.getTvquantity() != null) {
								if (inventoryCurrentStock.getQuantity() != null) {
									inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() - transvouchitem.getTvquantity());
								}
								if (inventoryCurrentStock.getAvaquantity() != null) {
									inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - transvouchitem.getTvquantity());
								}
							}
							transferDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
						}
						// 调入仓库的库存数量增加
						// 需要通过仓库 及sku编码进行唯一存储
						Map<String, Object> params1 = new HashMap<String, Object>();
						// 过滤临时数据
						params1.put("isQualfied", 1);
						if (StringUtils.isNotEmpty(transvouchitem.getSkuCode())) {
							params1.put("skuCode", transvouchitem.getSkuCode());
						}
						if (StringUtils.isNotEmpty(transvouchitem.getItemcode())) {
							params1.put("itemcode", transvouchitem.getItemcode());
						}
						params1.put("invWarehouseId", transvouch1.getInInvWarehouse().getId());
						StringBuilder hql1 = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params1);
						InventoryCurrentStock inv = transferDomain.findInventoryCurrentStockByHql(hql1.toString(), params1);
						if (inv != null) {
							// 调入仓库 存在该商品 相应库存增加
							if (transvouchitem.getTvquantity() != null) {
								if (inv.getQuantity() != null) {
									inv.setQuantity(inv.getQuantity() + transvouchitem.getTvquantity());
								}
								if (inv.getAvaquantity() != null) {
									inv.setAvaquantity(inv.getAvaquantity() + transvouchitem.getTvquantity());
								}
							}
							transferDomain.saveOrUpdateInventoryCurrentStock(inv);
						} else {
							// 调入仓库不存在该商品 创建商品库存数据
							InventoryCurrentStock inventory = new InventoryCurrentStock();
							inventory.setIsQualfied(1);
							inventory.setItemcode(transvouchitem.getItemcode());
							inventory.setItemname(transvouchitem.getItemname());
							inventory.setQuantity(transvouchitem.getTvquantity());
							inventory.setAvaquantity(transvouchitem.getTvquantity());
							inventory.setUnit(transvouchitem.getMasterUnit());
							if (transvouch1.getInInvWarehouse() != null) {
								inventory.setInvWarehouse(transvouch1.getInInvWarehouse());
								inventory.setWarehousecode(transvouch1.getInInvWarehouse().getCode());
								inventory.setWarehouse(transvouch1.getInInvWarehouse().getName());
							}
							inventory.setIsTemp("");
							if (transvouchitem.getInvShelf() != null) {
								inventory.setInvShelf(transvouchitem.getInvShelf());
								inventory.setInvShelfName(transvouchitem.getInvShelf().getName());
							}
							inventory.setSkuCode(transvouchitem.getSkuCode());
							inventory.setPrice(transvouchitem.getSalecost());
							initEntityBaseController.initEntityBaseAttribute(inventory);
							transferDomain.saveOrUpdateInventoryCurrentStock(inventory);
						}
						transvouchitem.setIsUpdateStore("2");
						transvouchitem = transferDomain.mergeTransvouchItem(transvouchitem);
					}
				}
			}
		}
		return transvouch1;
	}

	/**
	 * 保存调拨单明细
	 * 
	 * @param wimTransvouchitem
	 * @return
	 * @throws Exception
	 */
	public WimTransvouchitem doSaveWimTransvouchitem(WimTransvouchitem wimTransvouchitem) throws Exception {
		WimTransvouchitem wimTransvouchitem1 = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		wimTransvouchitem1 = transferDomain.mergeTransvouchItem(wimTransvouchitem);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "保存订单：" + wimTransvouchitem + "成功！");
		return wimTransvouchitem1;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListTransvouch(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = transferDomain.findWimTransvouchPagerByHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return p;
	}

	public Pager doListInventory(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = transferDomain.findInventoryCurrentStockPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

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
	public Pager doListTransvouchByCon(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = transferDomain.findTransvouchPagerByOrHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @throws Exception
	 */
	public void doDeleteByEntity(WimTransvouch salesOrder) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		transferDomain.deleteByEntity(salesOrder);
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
		transferDomain.deleteByIds(ids);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doSubSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3.执行查询操作
		p = transferDomain.findWimTransvouchPagerByHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return p;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WimTransvouch doListWimTransvouchById(String id) throws Exception {
		WimTransvouch wimTransvouch = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		wimTransvouch = transferDomain.findWimTransvouchById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return wimTransvouch;
	}

	public List<MeasureUnit> doListMeasureUnitList(Map<String, Object> params) throws Exception {
		List<MeasureUnit> measureUnit = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		measureUnit = transferDomain.findMeasureUnitList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return measureUnit;
	}

	public WimTransvouchitem doListWimTransvouchitemById(String id) throws Exception {
		WimTransvouchitem wimTransvouchitem = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		wimTransvouchitem = transferDomain.findWimTransvouchitemById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询入库单明细信息成功！");
		return wimTransvouchitem;
	}

	public void deleteWimTransvouchitemEntity(WimTransvouchitem wimTransvouchitem) throws Exception {
		transferDomain.deleteWimTransvouchitemEntity(wimTransvouchitem);
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<WimTransvouch> doListWimTransvouchList(Map<String, Object> params) throws Exception {
		List<WimTransvouch> wimTransvouch = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		wimTransvouch = transferDomain.findWimTransvouchList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return wimTransvouch;
	}

	public List<WimTransvouchitem> doListWimTransvouchitemList(Map<String, Object> params) throws Exception {
		List<WimTransvouchitem> wimTransvouchitem = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		wimTransvouchitem = transferDomain.findWimTransvouchitemList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return wimTransvouchitem;
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
