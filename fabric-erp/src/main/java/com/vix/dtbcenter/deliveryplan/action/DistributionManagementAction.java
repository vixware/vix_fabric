package com.vix.dtbcenter.deliveryplan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryplan.controller.DistributionManagementController;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPerson;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlan;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToDeliveryPerson;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToDispatchRoute;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToSalesOrder;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToVehicle;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.sales.order.entity.SalesOrder;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class DistributionManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(DistributionManagementAction.class);
	@Autowired
	private DistributionManagementController distributionManagementController;
	private String id;
	private String ids;
	private String pageNo;

	private DeliveryPlan deliveryPlan;
	private List<DeliveryPlan> deliveryPlanList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			deliveryPlanList = distributionManagementController.doListDeliveryPlanList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			Pager pager = distributionManagementController.doListDeliveryPlan(params, getPager());
			setPager(pager);
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改派车单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
				logger.info("");
			} else {
				deliveryPlan = new DeliveryPlan();
				deliveryPlan.setIsTemp("1");
				deliveryPlan = distributionManagementController.doSaveDeliveryPlan(deliveryPlan, null, null, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != deliveryPlan.getId()) {
				isSave = false;
			}
			deliveryPlan.setIsTemp("");
			/* 销售订单 */
			String salesOrderJson = getRequestParameter("salesOrderJson");
			List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
			if (salesOrderJson != null && !"".equals(salesOrderJson)) {
				salesOrderList = new JSONDeserializer<List<SalesOrder>>().use("values", SalesOrder.class).deserialize(salesOrderJson);
			}
			/* 车辆 */
			String vehicleJson = getRequestParameter("vehicleJson");
			List<Vehicle> vehicleList = new ArrayList<Vehicle>();
			if (vehicleJson != null && !"".equals(vehicleJson)) {
				vehicleList = new JSONDeserializer<List<Vehicle>>().use("values", Vehicle.class).deserialize(vehicleJson);
			}
			/* 配送人员 */
			String deliveryPersonJson = getRequestParameter("deliveryPersonJson");
			List<DeliveryPerson> deliveryPersonList = new ArrayList<DeliveryPerson>();
			if (deliveryPersonJson != null && !"".equals(deliveryPersonJson)) {
				deliveryPersonList = new JSONDeserializer<List<DeliveryPerson>>().use("values", DeliveryPerson.class).deserialize(deliveryPersonJson);
			}
			/* 路线 */
			String dispatchRouteJson = getRequestParameter("dispatchRouteJson");
			List<DispatchRoute> dispatchRouteList = new ArrayList<DispatchRoute>();
			if (dispatchRouteJson != null && !"".equals(dispatchRouteJson)) {
				dispatchRouteList = new JSONDeserializer<List<DispatchRoute>>().use("values", DispatchRoute.class).deserialize(dispatchRouteJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(deliveryPlan, updateField);
			deliveryPlan = distributionManagementController.doSaveDeliveryPlan(deliveryPlan, salesOrderList, vehicleList, deliveryPersonList, dispatchRouteList);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 获取销售订单的json数据 */

	public void getSalesOrderJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
			if (null != id && !"".equals(id)) {
				DeliveryPlan deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
				if (deliveryPlan != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("deliveryPlanId," + SearchCondition.EQUAL, deliveryPlan.getId());
					// 查询关联表信息
					List<DeliveryPlanToSalesOrder> deliveryPlanToSalesOrderList = distributionManagementController.doListDeliveryPlanToSalesOrderByParams(params);
					if (deliveryPlanToSalesOrderList != null && deliveryPlanToSalesOrderList.size() > 0) {
						for (DeliveryPlanToSalesOrder deliveryPlanToSalesOrder : deliveryPlanToSalesOrderList) {
							if (deliveryPlanToSalesOrder != null) {
								SalesOrder salesOrder = distributionManagementController.doListSalesOrderById(deliveryPlanToSalesOrder.getSalesOrderId());
								salesOrderList.add(salesOrder);
							}
						}
					}
				}
			}
			if (salesOrderList != null && salesOrderList.size() > 0) {
				json = convertListToJson(salesOrderList, salesOrderList.size(), "deliveryPlan");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取销售订单的json数据 */
	public void getVehicleJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<Vehicle> vehicleList = new ArrayList<Vehicle>();
			if (null != id && !"".equals(id)) {
				DeliveryPlan deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
				if (deliveryPlan != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("deliveryPlanId," + SearchCondition.EQUAL, deliveryPlan.getId());
					// 查询关联表信息
					List<DeliveryPlanToVehicle> deliveryPlanToVehicleList = distributionManagementController.doListDeliveryPlanToVehicleByParams(params);
					if (deliveryPlanToVehicleList != null && deliveryPlanToVehicleList.size() > 0) {
						for (DeliveryPlanToVehicle deliveryPlanToVehicle : deliveryPlanToVehicleList) {
							if (deliveryPlanToVehicle != null) {
								Vehicle vehicle = distributionManagementController.doListVehicleById(deliveryPlanToVehicle.getVehicleId());
								vehicleList.add(vehicle);
							}
						}
					}
				}
			} else {
				vehicleList = distributionManagementController.doListVehicle();
			}
			if (vehicleList != null && vehicleList.size() > 0) {
				json = convertListToJson(vehicleList, vehicleList.size(), "deliveryPlan");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取配送人员的json数据 */
	public void getDeliveryPersonJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<DeliveryPerson> deliveryPersonList = new ArrayList<DeliveryPerson>();
			if (null != id && !"".equals(id)) {
				DeliveryPlan deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
				if (deliveryPlan != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("deliveryPlanId," + SearchCondition.EQUAL, deliveryPlan.getId());
					// 查询关联表信息
					List<DeliveryPlanToDeliveryPerson> deliveryPlanToDeliveryPersonList = distributionManagementController.doListDeliveryPlanToDeliveryPersonByParams(params);
					if (deliveryPlanToDeliveryPersonList != null && deliveryPlanToDeliveryPersonList.size() > 0) {
						for (DeliveryPlanToDeliveryPerson deliveryPlanToDeliveryPerson : deliveryPlanToDeliveryPersonList) {
							if (deliveryPlanToDeliveryPerson != null) {
								DeliveryPerson deliveryPerson = distributionManagementController.doListDeliveryPersonById(deliveryPlanToDeliveryPerson.getDeliveryPersonId());
								deliveryPersonList.add(deliveryPerson);
							}
						}
					}
				}
			}/*
				* else { deliveryPersonList =
				* distributionManagementController.doListDeliveryPerson(); }
				*/
			if (deliveryPersonList != null && deliveryPersonList.size() > 0) {
				json = convertListToJson(deliveryPersonList, deliveryPersonList.size(), "deliveryPlan");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取运输路线的json数据 */
	public void getDispatchRouteJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<DispatchRoute> dispatchRouteList = new ArrayList<DispatchRoute>();
			if (null != id && !"".equals(id)) {
				DeliveryPlan deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
				if (deliveryPlan != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("deliveryPlanId," + SearchCondition.EQUAL, deliveryPlan.getId());
					// 查询关联表信息
					List<DeliveryPlanToDispatchRoute> deliveryPlanToDispatchRouteList = distributionManagementController.doListDeliveryPlanToDispatchRouteByParams(params);
					if (deliveryPlanToDispatchRouteList != null && deliveryPlanToDispatchRouteList.size() > 0) {
						for (DeliveryPlanToDispatchRoute deliveryPlanToDispatchRoute : deliveryPlanToDispatchRouteList) {
							if (deliveryPlanToDispatchRoute != null) {
								DispatchRoute dispatchRoute = distributionManagementController.doListDispatchRouteById(deliveryPlanToDispatchRoute.getDispatchRouteId());
								dispatchRouteList.add(dispatchRoute);
							}
						}
					}
				}
			}/*
				* else { dispatchRouteList =
				* distributionManagementController.doListDispatchRoute(); }
				*/
			if (dispatchRouteList != null && dispatchRouteList.size() > 0) {
				json = convertListToJson(dispatchRouteList, dispatchRouteList.size(), "deliveryPlan");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			DeliveryPlan pb = distributionManagementController.doListDeliveryPlanById(id);
			if (null != pb) {
				distributionManagementController.doDeleteByDeliveryPlan(pb);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除派车单信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				distributionManagementController.doDeleteByIds(delIds);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 选择销售订单
	 * 
	 * @return
	 */
	public String goChooseSalesOrder() {
		return "goChooseSalesOrder";
	}

	/**
	 * 配载计划 销售订单列表
	 * 
	 * @return
	 */
	public String goSalesOrderList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按名称
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = distributionManagementController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
	}

	// 将销售订单与派车单关联
	public String converterSalesOrderToTruckingOrder() {
		String salesOrderids = getRequestParameter("salesOrderid");
		// 获取派车单
		deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
		// 将选择的订单编号转存成数组格式
		String[] salesOrderidArr = salesOrderids.split(",");
		if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
			for (String salesOrderidid : salesOrderidArr) {
				// 遍历订单编号数组
				if (salesOrderidid != null && !"".equals(salesOrderidid)) {
					// 取出订单编号 通过订单编号获取对应的订单信息
					SalesOrder salesOrder = distributionManagementController.doListSalesOrderById(salesOrderidid);
					if (salesOrder != null) {
						try {
							// 将订单跟派车单关联
							distributionManagementController.convSalesOrderToDeliveryReceipt(deliveryPlan, salesOrder);
							renderText(SAVE_SUCCESS);
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	// 将运输路线与配送计划关联
	public String converterDispatchRouteToDeliveryReceipt() {
		String salesOrderids = getRequestParameter("dispatchRouteid");
		deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
		String[] salesOrderidArr = salesOrderids.split(",");
		if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
			for (String salesOrderidid : salesOrderidArr) {
				if (salesOrderidid != null && !"".equals(salesOrderidid)) {
					DispatchRoute dispatchRoute = distributionManagementController.doListDispatchRouteById(salesOrderidid);
					if (dispatchRoute != null) {
						try {
							distributionManagementController.convDispatchRouteToDeliveryReceipt(deliveryPlan, dispatchRoute);
							renderText(SAVE_SUCCESS);
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	// 将配送人员
	public String converterDeliveryPersonToDeliveryPlan() {
		String salesOrderids = getRequestParameter("deliveryPersonid");
		deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
		String[] salesOrderidArr = salesOrderids.split(",");
		if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
			for (String salesOrderidid : salesOrderidArr) {
				if (salesOrderidid != null && !"".equals(salesOrderidid)) {
					DeliveryPerson deliveryPerson = distributionManagementController.doListDeliveryPersonById(salesOrderidid);
					if (deliveryPerson != null) {
						try {
							distributionManagementController.convDeliveryPersonToDeliveryPlan(deliveryPlan, deliveryPerson);
							renderText(SAVE_SUCCESS);
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	// 将车辆与配送计划关联
	public String converterVehicleToDeliveryPlan() {
		String salesOrderids = getRequestParameter("vehicleid");
		deliveryPlan = distributionManagementController.doListDeliveryPlanById(id);
		String[] salesOrderidArr = salesOrderids.split(",");
		if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
			for (String salesOrderidid : salesOrderidArr) {
				if (salesOrderidid != null && !"".equals(salesOrderidid)) {
					Vehicle vehicle = distributionManagementController.doListVehicleById(salesOrderidid);
					if (vehicle != null) {
						try {
							distributionManagementController.convVehicleToDeliveryPlan(deliveryPlan, vehicle);
							renderText(SAVE_SUCCESS);
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	/**
	 * 选择运输路线
	 * 
	 * @return
	 */
	public String goChooseDispatchRoute() {
		return "goChooseDispatchRoute";
	}

	/**
	 * 选择配送人员
	 * 
	 * @return
	 */
	public String goChooseDeliveryPerson() {
		return "goChooseDeliveryPerson";
	}

	/**
	 * 运输路线列表
	 * 
	 * @return
	 */
	public String goDispatchRouteList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按名称
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = distributionManagementController.doListDispatchRoutePager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDispatchRouteList";
	}

	/**
	 * 配送人员列表
	 * 
	 * @return
	 */
	public String goDeliveryPersonList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按名称
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = distributionManagementController.doListDeliveryPersonPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDeliveryPersonList";
	}

	/* 选车 */
	public String goChooseVehicle() {
		return "goChooseVehicle";
	}

	/**
	 * 获取车辆列表
	 * 
	 * @return
	 */
	public String goVehicleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按名称
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = distributionManagementController.doListVehicle(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goVehicleList";
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public DeliveryPlan getDeliveryPlan() {
		return deliveryPlan;
	}

	public void setDeliveryPlan(DeliveryPlan deliveryPlan) {
		this.deliveryPlan = deliveryPlan;
	}

	public List<DeliveryPlan> getDeliveryPlanList() {
		return deliveryPlanList;
	}

	public void setDeliveryPlanList(List<DeliveryPlan> deliveryPlanList) {
		this.deliveryPlanList = deliveryPlanList;
	}

}
