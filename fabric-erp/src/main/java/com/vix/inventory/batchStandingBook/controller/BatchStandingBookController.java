/**
 * 
 */
package com.vix.inventory.batchStandingBook.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.inventory.batchStandingBook.domain.BatchStandingBookDomain;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.inbound.entity.StockRecordLines;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("batchStandingBookController")
@Scope("prototype")
public class BatchStandingBookController {

	Logger logger = Logger.getLogger(BatchStandingBookController.class);

	@Autowired
	private BatchStandingBookDomain batchStandingBookDomain;

	/**
	 * 
	 * 
	 * @param invMainBatch
	 * @return
	 * @throws Exception
	 */
	public InvMainBatch doSaveInvMainBatch(InvMainBatch invMainBatch) throws Exception {
		InvMainBatch invMainBatch1 = null;
		try {
			// 执行保存操作
			invMainBatch1 = batchStandingBookDomain.saveOrUpdateInvMainBatch(invMainBatch);
			
		} catch (Exception ex) {
			
			
		}
		return invMainBatch1;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListInvMainBatch(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			p = batchStandingBookDomain.findInvMainBatchPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 显示出入库单明细
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListInvStockRecordLines(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			p = batchStandingBookDomain.findInvStockRecordLinesPagerByHqlConditions(params, pager);

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
	 * @param invMainBatch
	 */
	public void doDeleteByEntity(InvMainBatch invMainBatch) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			batchStandingBookDomain.deleteByEntity(invMainBatch);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public InvMainBatch doListInvMainBatchById(String id) {
		InvMainBatch invMainBatch = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			invMainBatch = batchStandingBookDomain.findInvMainBatchById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return invMainBatch;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public StockRecordLines doListInvStockRecordLinesById(String id) {
		StockRecordLines invStockRecordLines = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			invStockRecordLines = batchStandingBookDomain.findInvStockRecordLinesById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return invStockRecordLines;
	}

	/**
	 * 创建表之间的关系
	 * 
	 * @param invMainBatch
	 * @param invStockRecordLines
	 * @throws Exception
	 */
	public void createRelationship(InvMainBatch invMainBatch, StockRecordLines invStockRecordLines) throws Exception {
		if (invMainBatch != null && invStockRecordLines != null) {
			invStockRecordLines.setInvMainBatch(invMainBatch);
			batchStandingBookDomain.mergeInvStockRecordLines(invStockRecordLines);
		}
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 */
	public List<InvMainBatch> doListInvMainBatchIndex() {
		List<InvMainBatch> invMainBatch = null;

		try {
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			invMainBatch = batchStandingBookDomain.findInvMainBatchIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
		} catch (Exception ex) {
			
		}
		return invMainBatch;
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
		batchStandingBookDomain.evict(obj);
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
