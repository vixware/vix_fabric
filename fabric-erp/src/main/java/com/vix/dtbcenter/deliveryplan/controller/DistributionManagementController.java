/**
 * 
 */
package com.vix.dtbcenter.deliveryplan.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryplan.domain.DistributionManagementDomain;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPerson;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlan;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToDeliveryPerson;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToDispatchRoute;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToSalesOrder;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToVehicle;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("distributionManagementController")
@Scope("prototype")
public class DistributionManagementController {

	Logger logger = Logger.getLogger(DistributionManagementController.class);

	@Autowired
	private DistributionManagementDomain distributionManagementDomain;

	/**
	 * 
	 * @param deliveryPlan
	 * @param salesOrderList
	 * @return
	 * @throws Exception
	 */
	public DeliveryPlan doSaveDeliveryPlan(DeliveryPlan deliveryPlan, List<SalesOrder> salesOrderList, List<Vehicle> vehicleList, List<DeliveryPerson> deliveryPersonList, List<DispatchRoute> dispatchRouteList) throws Exception {
		DeliveryPlan deliveryPlan1 = null;
		try {
			// 保存配送计划单
			deliveryPlan.setName("配送计划单" + deliveryPlan.getDpCode());
			deliveryPlan1 = distributionManagementDomain.mergeDeliveryPlan(deliveryPlan);
			// 保存配送计划 销售订单关系表
			if (deliveryPlan1 != null) {
				if (salesOrderList != null && salesOrderList.size() > 0) {
					for (SalesOrder salesOrder : salesOrderList) {
						if (salesOrder != null) {
							DeliveryPlanToSalesOrder deliveryPlanToSalesOrder = new DeliveryPlanToSalesOrder();
							deliveryPlanToSalesOrder.setDeliveryPlanId(deliveryPlan1.getId());
							deliveryPlanToSalesOrder.setSalesOrderId(salesOrder.getId());
							distributionManagementDomain.mergeDeliveryPlanToSalesOrder(deliveryPlanToSalesOrder);
						}
					}
				}
			}
			// 保存配送计划 车辆系表
			if (deliveryPlan1 != null) {
				if (vehicleList != null && vehicleList.size() > 0) {
					for (Vehicle vehicle : vehicleList) {
						if (vehicle != null) {
							DeliveryPlanToVehicle deliveryPlanToVehicle = new DeliveryPlanToVehicle();
							deliveryPlanToVehicle.setDeliveryPlanId(deliveryPlan1.getId());
							deliveryPlanToVehicle.setVehicleId(vehicle.getId());
							distributionManagementDomain.mergeDeliveryPlanToVehicle(deliveryPlanToVehicle);
						}
					}
				}
			}
			// 保存配送计划 配送人员系表
			if (deliveryPlan1 != null) {
				if (deliveryPersonList != null && deliveryPersonList.size() > 0) {
					for (DeliveryPerson deliveryPerson : deliveryPersonList) {
						if (deliveryPerson != null) {
							DeliveryPlanToDeliveryPerson deliveryPlanToDeliveryPerson = new DeliveryPlanToDeliveryPerson();
							deliveryPlanToDeliveryPerson.setDeliveryPlanId(deliveryPlan1.getId());
							deliveryPlanToDeliveryPerson.setDeliveryPersonId(deliveryPerson.getId());
							distributionManagementDomain.mergeDeliveryPlanToDeliveryPerson(deliveryPlanToDeliveryPerson);
						}
					}
				}
			}
			// 保存配送计划 配送人员系表
			if (deliveryPlan1 != null) {
				if (dispatchRouteList != null && dispatchRouteList.size() > 0) {
					for (DispatchRoute dispatchRoute : dispatchRouteList) {
						if (dispatchRoute != null) {
							DeliveryPlanToDispatchRoute deliveryPlanToDispatchRoute = new DeliveryPlanToDispatchRoute();
							deliveryPlanToDispatchRoute.setDeliveryPlanId(deliveryPlan1.getId());
							deliveryPlanToDispatchRoute.setDispatchRouteId(dispatchRoute.getId());
							distributionManagementDomain.mergeDeliveryPlanToDispatchRoute(deliveryPlanToDispatchRoute);
						}
					}
				}
			}
			executeLogger(true, "保存：" + deliveryPlan.getCode() + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + deliveryPlan.getCode() + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return deliveryPlan1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListDeliveryPlan(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = distributionManagementDomain.findDeliveryPlanPagerByHqlConditions(params, pager);

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
	public Pager doListDeliveryPlanByCon(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 3.执行查询操作
			p = distributionManagementDomain.findDeliveryPlanPagerByOrHqlConditions(params, pager);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 
	 * 
	 * @param deliveryPlan
	 */
	public void doDeleteByDeliveryPlan(DeliveryPlan deliveryPlan) {
		try {
			// 3. 执行删除操作
			distributionManagementDomain.deleteByEntity(deliveryPlan);

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
			distributionManagementDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
	}

	public Pager doListSalesOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = distributionManagementDomain.findSalesOrderPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public DeliveryPlan doListDeliveryPlanById(String id) {
		DeliveryPlan deliveryPlan = null;
		try {
			// 3.执行查询操作
			deliveryPlan = distributionManagementDomain.findDeliveryPlanById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryPlan;
	}

	public SalesOrder doListSalesOrderById(String id) {
		SalesOrder salesOrder = null;
		try {
			// 3.执行查询操作
			salesOrder = distributionManagementDomain.findSalesOrderById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return salesOrder;
	}

	/**
	 * 建立销售订单跟派车单的关系表
	 * 
	 * @param deliveryReceipt
	 * @param salesOrder
	 * @throws Exception
	 */
	public void convSalesOrderToDeliveryReceipt(DeliveryPlan deliveryPlan, SalesOrder salesOrder) throws Exception {
		if (salesOrder != null && deliveryPlan != null) {
			// 生成派车单 发货通知单关联表 同时保存关联表到数据库
			DeliveryPlanToSalesOrder deliveryPlanToSalesOrder = new DeliveryPlanToSalesOrder();
			deliveryPlanToSalesOrder.setDeliveryPlanId(deliveryPlan.getId());
			deliveryPlanToSalesOrder.setSalesOrderId(salesOrder.getId());
			distributionManagementDomain.mergeDeliveryPlanToSalesOrder(deliveryPlanToSalesOrder);
		}
	}

	public void convVehicleToDeliveryPlan(DeliveryPlan deliveryPlan, Vehicle vehicle) throws Exception {
		if (vehicle != null && deliveryPlan != null) {
			// 生成派车单 发货通知单关联表 同时保存关联表到数据库
			DeliveryPlanToVehicle deliveryPlanToSalesOrder = new DeliveryPlanToVehicle();
			deliveryPlanToSalesOrder.setDeliveryPlanId(deliveryPlan.getId());
			deliveryPlanToSalesOrder.setVehicleId(vehicle.getId());
			distributionManagementDomain.mergeDeliveryPlanToVehicle(deliveryPlanToSalesOrder);
		}
	}

	public Vehicle doListVehicleById(String id) {
		Vehicle vehicle = null;
		try {
			// 3.执行查询操作
			vehicle = distributionManagementDomain.findVehicleById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return vehicle;
	}

	public DeliveryPerson doListDeliveryPersonById(String id) {
		DeliveryPerson deliveryPerson = null;
		try {
			// 3.执行查询操作
			deliveryPerson = distributionManagementDomain.findDeliveryPersonById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryPerson;
	}

	public DispatchRoute doListDispatchRouteById(String id) {
		DispatchRoute dispatchRoute = null;
		try {
			// 3.执行查询操作
			dispatchRoute = distributionManagementDomain.findDispatchRouteById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return dispatchRoute;
	}

	public void convDispatchRouteToDeliveryReceipt(DeliveryPlan deliveryPlan, DispatchRoute dispatchRoute) throws Exception {
		if (dispatchRoute != null && deliveryPlan != null) {
			DeliveryPlanToDispatchRoute deliveryPlanToDispatchRoute = new DeliveryPlanToDispatchRoute();
			deliveryPlanToDispatchRoute.setDeliveryPlanId(deliveryPlan.getId());
			deliveryPlanToDispatchRoute.setDispatchRouteId(dispatchRoute.getId());
			distributionManagementDomain.mergeDeliveryPlanToDispatchRoute(deliveryPlanToDispatchRoute);
		}
	}

	public void convDeliveryPersonToDeliveryPlan(DeliveryPlan deliveryPlan, DeliveryPerson deliveryPerson) throws Exception {
		if (deliveryPerson != null && deliveryPlan != null) {
			DeliveryPlanToDeliveryPerson deliveryPlanToDeliveryPerson = new DeliveryPlanToDeliveryPerson();
			deliveryPlanToDeliveryPerson.setDeliveryPersonId(deliveryPerson.getId());
			deliveryPlanToDeliveryPerson.setDeliveryPlanId(deliveryPlan.getId());
			distributionManagementDomain.mergeDeliveryPlanToDeliveryPerson(deliveryPlanToDeliveryPerson);
		}
	}

	public List<DeliveryPlanToSalesOrder> doListDeliveryPlanToSalesOrderByParams(Map<String, Object> params) {
		List<DeliveryPlanToSalesOrder> deliveryPlanToSalesOrderList = null;
		try {
			// 3.执行查询操作
			deliveryPlanToSalesOrderList = distributionManagementDomain.findDeliveryPlanToSalesOrderByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryPlanToSalesOrderList;
	}

	public List<DeliveryPlanToVehicle> doListDeliveryPlanToVehicleByParams(Map<String, Object> params) {
		List<DeliveryPlanToVehicle> deliveryPlanToVehicleList = null;
		try {
			// 3.执行查询操作
			deliveryPlanToVehicleList = distributionManagementDomain.findDeliveryPlanToVehicleByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryPlanToVehicleList;
	}

	public List<DeliveryPlanToDeliveryPerson> doListDeliveryPlanToDeliveryPersonByParams(Map<String, Object> params) {
		List<DeliveryPlanToDeliveryPerson> deliveryPlanToDeliveryPersonList = null;
		try {
			// 3.执行查询操作
			deliveryPlanToDeliveryPersonList = distributionManagementDomain.findDeliveryPlanToDeliveryPersonByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryPlanToDeliveryPersonList;
	}

	public List<DeliveryPlanToDispatchRoute> doListDeliveryPlanToDispatchRouteByParams(Map<String, Object> params) {
		List<DeliveryPlanToDispatchRoute> deliveryPlanToDispatchRouteList = null;
		try {
			// 3.执行查询操作
			deliveryPlanToDispatchRouteList = distributionManagementDomain.findDeliveryPlanToDispatchRouteByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return deliveryPlanToDispatchRouteList;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<DeliveryPlan> doListDeliveryPlanList(Map<String, Object> params) throws Exception {
		List<DeliveryPlan> deliveryPlan = null;

		// 3. 执行查询操作
		deliveryPlan = distributionManagementDomain.findDeliveryPlanList(params);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return deliveryPlan;
	}

	public List<SalesOrder> doListSalesOrder() throws Exception {
		List<SalesOrder> salesOrder = null;
		// 3. 执行查询操作
		salesOrder = distributionManagementDomain.findSalesOrder();
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "");
		return salesOrder;
	}

	public List<Vehicle> doListVehicle() {
		List<Vehicle> vehicle = null;
		try {
			// 3. 执行查询操作
			vehicle = distributionManagementDomain.findVehicle();
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return vehicle;
	}

	public List<DeliveryPerson> doListDeliveryPerson() {
		List<DeliveryPerson> deliveryPerson = null;
		try {
			// 3. 执行查询操作
			deliveryPerson = distributionManagementDomain.findDeliveryPerson();
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return deliveryPerson;
	}

	public List<DispatchRoute> doListDispatchRoute() {
		List<DispatchRoute> dispatchRoute = null;
		try {
			// 3. 执行查询操作
			dispatchRoute = distributionManagementDomain.findDispatchRoute();
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return dispatchRoute;
	}

	public Pager doListDispatchRoutePager(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = distributionManagementDomain.findDispatchRoutePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public Pager doListDeliveryPersonPager(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = distributionManagementDomain.findDeliveryPersonPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public Pager doListVehicle(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = distributionManagementDomain.findVehiclePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
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
