/**
 * 
 */
package com.vix.inventory.positionadjustment.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.inventory.positionadjustment.domain.PositionAdjustmentDomain;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouch;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouchItem;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvShelf;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("positionAdjustmentController")
@Scope("prototype")
public class PositionAdjustmentController {

	Logger logger = Logger.getLogger(PositionAdjustmentController.class);

	@Autowired
	private PositionAdjustmentDomain positionAdjustmentDomain;

	/**
	 * 
	 * @param wimAdjustpvouch
	 * @return
	 * @throws Exception
	 */
	public WimAdjustpvouch doSaveOrUpdateWimAdjustpvouch(WimAdjustpvouch wimAdjustpvouch) throws Exception {
		return positionAdjustmentDomain.mergeWimAdjustpvouch(wimAdjustpvouch);
	}

	public InventoryCurrentStock doSaveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return positionAdjustmentDomain.mergeInventoryCurrentStock(inventoryCurrentStock);
	}

	/**
	 * 
	 * @param wimAdjustpvouchItem
	 * @return
	 * @throws Exception
	 */
	public WimAdjustpvouchItem doSaveWimAdjustpvouchItem(WimAdjustpvouchItem wimAdjustpvouchItem) throws Exception {
		// 执行保存操作
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return positionAdjustmentDomain.mergeWimAdjustpvouchItem(wimAdjustpvouchItem);
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListWimAdjustpvouchPager(Map<String, Object> params, Pager pager) throws Exception {

		// 执行保存操作

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return positionAdjustmentDomain.findWimAdjustpvouchPagerByHqlConditions(params, pager);
	}

	public List<WimAdjustpvouchItem> doListWimAdjustpvouchItemList(Map<String, Object> params) throws Exception {
		List<WimAdjustpvouchItem> wimAdjustpvouchItemList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		// 3. 执行查询操作
		wimAdjustpvouchItemList = positionAdjustmentDomain.findWimAdjustpvouchItemList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return wimAdjustpvouchItemList;
	}

	public Pager doListInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；

		// 3.执行保存操作

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return positionAdjustmentDomain.findInventoryCurrentStockPager(params, pager);
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListWimAdjustpvouchByCon(Map<String, Object> params, Pager pager) throws Exception {

		// 3.执行查询操作

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return positionAdjustmentDomain.findWimAdjustpvouchPagerByOrHqlConditions(params, pager);
	}

	/**
	 * 
	 * @param wimAdjustpvouch
	 * @throws Exception
	 */
	public void doDeleteByEntity(WimAdjustpvouch wimAdjustpvouch) throws Exception {
		// 3. 执行删除操作
		positionAdjustmentDomain.deleteWimAdjustpvouchByEntity(wimAdjustpvouch);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void doDeleteByIds(List<String> ids) throws Exception {
		// 执行删除操作
		positionAdjustmentDomain.deleteByIds(ids);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WimAdjustpvouch doListWimAdjustpvouchById(String id) throws Exception {

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return positionAdjustmentDomain.findWimAdjustpvouchById(id);
	}

	public InventoryCurrentStock doListInventoryCurrentStockById(String id) throws Exception {

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return positionAdjustmentDomain.findInventoryCurrentStockById(id);
	}

	public InvShelf doListInvShelfById(String id) throws Exception {

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return positionAdjustmentDomain.findInvShelfById(id);
	}

	public WimAdjustpvouchItem doListWimAdjustpvouchItemById(String id) throws Exception {

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return positionAdjustmentDomain.findWimAdjustpvouchItemById(id);
	}

	public void doDeleteWimAdjustpvouchItem(WimAdjustpvouchItem wimAdjustpvouchItem) throws Exception {
		positionAdjustmentDomain.deleteWimAdjustpvouchItemEntity(wimAdjustpvouchItem);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<WimAdjustpvouch> doListWimAdjustpvouchList(Map<String, Object> params) throws Exception {

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return positionAdjustmentDomain.findWimAdjustpvouchList(params);
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
}
