package com.vix.purchase.plan.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.purchase.plan.domain.PurchaseApproveDomain;

/**
 * 
 * com.vix.purchase.plan.controller.PurchasePackageController
 *
 * @author bjitzhang
 *
 * @date 2015年11月12日
 */
@Controller("purchaseApproveController")
@Scope("prototype")
public class PurchaseApproveController extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger("PurchasePlanController");

	@Autowired
	private PurchaseApproveDomain purchaseApproveDomain;

	/** 获取列表数据 */
	public Pager goPurchasePlanPackagePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = purchaseApproveDomain.findPurchasePlanPackagePager(params, pager);
		return p;
	}

	/**
	 * 
	 * @param purchasePlanPackage
	 * @return
	 * @throws Exception
	 */
	public PurchasePlanPackage doSavePurchasePlanPackage(PurchasePlanPackage purchasePlanPackage) throws Exception {
		PurchasePlanPackage purchasePlanPackageTemp = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		purchasePlanPackageTemp = purchaseApproveDomain.mergePurchasePlanPackage(purchasePlanPackage);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchasePlanPackageTemp;
	}

	public PurchasePlanItems doSavePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		PurchasePlanItems purchasePlanItems1 = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		purchasePlanItems1 = purchaseApproveDomain.merge(purchasePlanItems);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchasePlanItems1;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 * @throws Exception
	 */
	public void doDeleteByEntity(PurchasePlan purchasePlan) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		purchaseApproveDomain.deleteByEntity(purchasePlan);
		// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	public void deletePurchasePlanItems(PurchasePlanItems purchasePlanItems) throws Exception {
		purchaseApproveDomain.deletePurchasePlanItems(purchasePlanItems);
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

	public PurchasePlanPackage doListPurchasePlanPackageById(String id) throws Exception {
		return purchaseApproveDomain.findPurchasePlanPackageById(id);
	}

	public Employee doListEmployeeById(String id) throws Exception {
		Employee employee = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		employee = purchaseApproveDomain.findEmployeeById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return employee;
	}

	public PurchasePlan findPurchasePlanById(String id) throws Exception {
		return purchaseApproveDomain.findPurchasePlanById(id);
	}

	public void deleteByEntity(PurchasePlan purchasePlan) throws Exception {
		purchaseApproveDomain.deleteByEntity(purchasePlan);
	}

	public List<PurchasePlanPackage> findPurchasePlanPackageList(Map<String, Object> params) throws Exception {
		return purchaseApproveDomain.findPurchasePlanPackageList(params);
	}
	public List<PurchasePlan> findPurchasePlanList(Map<String, Object> params) throws Exception {
		return purchaseApproveDomain.findPurchasePlanList(params);
	}
}
