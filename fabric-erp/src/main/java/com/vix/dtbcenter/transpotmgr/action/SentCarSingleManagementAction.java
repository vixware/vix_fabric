package com.vix.dtbcenter.transpotmgr.action;

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
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceiptToRoute;
import com.vix.dtbcenter.transpotmgr.controller.SentCarSingleManagementController;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToSalesOrder;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class SentCarSingleManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SentCarSingleManagementAction.class);
	@Autowired
	private SentCarSingleManagementController sentCarSingleManagementController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 派车单
	 */
	private DeliveryReceipt deliveryReceipt;
	private List<DeliveryReceipt> deliveryReceiptList;

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
			deliveryReceiptList = sentCarSingleManagementController.doListDeliveryReceiptList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询派车单信息
	 */
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
			Pager pager = sentCarSingleManagementController.doListDeliveryReceipt(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				deliveryReceipt = sentCarSingleManagementController.doListDeliveryReceiptById(id);
				logger.info("");
			} else {
				deliveryReceipt = new DeliveryReceipt();
				deliveryReceipt.setIsTemp("1");
				deliveryReceipt = sentCarSingleManagementController.doSaveDeliveryReceipt(deliveryReceipt);
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
			if (null != deliveryReceipt.getId() && !"".equals(deliveryReceipt.getId())) {
				isSave = false;
			}
			deliveryReceipt.setIsTemp("");
			//处理修改留痕
			billMarkProcessController.processMark(deliveryReceipt, updateField);

			deliveryReceipt = sentCarSingleManagementController.doSaveDeliveryReceipt(deliveryReceipt);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			DeliveryReceipt pb = sentCarSingleManagementController.doListDeliveryReceiptById(id);
			if (null != pb) {
				sentCarSingleManagementController.doDeleteByDeliveryReceipt(pb);
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
				sentCarSingleManagementController.doDeleteByIds(delIds);
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
			Pager pager = sentCarSingleManagementController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
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
	 * 配载计划 运输路线列表
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
			Pager pager = sentCarSingleManagementController.doListDispatchRoute(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDispatchRouteList";
	}

	// 将销售订单与派车单关联
	public String converterSalesOrderToTruckingOrder() {
		String salesOrderids = getRequestParameter("salesOrderid");
		// 获取派车单
		deliveryReceipt = sentCarSingleManagementController.doListDeliveryReceiptById(id);
		// 将选择的订单编号转存成数组格式
		String[] salesOrderidArr = salesOrderids.split(",");
		if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
			for (String salesOrderidid : salesOrderidArr) {
				// 遍历订单编号数组
				if (salesOrderidid != null && !"".equals(salesOrderidid)) {
					// 取出订单编号 通过订单编号获取对应的订单信息
					SalesOrder salesOrder = sentCarSingleManagementController.doListSalesOrderById(salesOrderidid);
					if (salesOrder != null) {
						try {
							// 将订单跟派车单关联
							sentCarSingleManagementController.convSalesOrderToDeliveryReceipt(deliveryReceipt, salesOrder);
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

	// 将运输路线与派车单关联
	public String converterDispatchRouteToDeliveryReceipt() {
		String salesOrderids = getRequestParameter("dispatchRouteid");
		deliveryReceipt = sentCarSingleManagementController.doListDeliveryReceiptById(id);
		String[] salesOrderidArr = salesOrderids.split(",");
		if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
			for (String salesOrderidid : salesOrderidArr) {
				if (salesOrderidid != null && !"".equals(salesOrderidid)) {
					DispatchRoute dispatchRoute = sentCarSingleManagementController.doListDispatchRouteById(salesOrderidid);
					if (dispatchRoute != null) {
						try {
							sentCarSingleManagementController.convDispatchRouteToDeliveryReceipt(deliveryReceipt, dispatchRoute);
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
	 * 获取销售订单
	 */
	public void getSalesOrderJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
			if (null != id && !"".equals(id)) {
				DeliveryReceipt deliveryReceipt = sentCarSingleManagementController.doListDeliveryReceiptById(id);
				if (deliveryReceipt != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("deliveryReceiptId," + SearchCondition.EQUAL, deliveryReceipt.getId());
					// 查询关联表信息
					List<DeliveryReceiptToSalesOrder> deliveryReceiptToSalesOrderList = sentCarSingleManagementController.doListDeliveryReceiptToSalesOrderByParams(params);
					if (deliveryReceiptToSalesOrderList != null && deliveryReceiptToSalesOrderList.size() > 0) {
						for (DeliveryReceiptToSalesOrder deliveryReceiptToSalesOrder : deliveryReceiptToSalesOrderList) {
							if (deliveryReceiptToSalesOrder != null) {
								SalesOrder salesOrder = sentCarSingleManagementController.doListSalesOrderById(deliveryReceiptToSalesOrder.getSalesOrderId());
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

	/**
	 * 获取运输路线
	 */
	public void getDispatchRouteJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			List<DispatchRoute> dispatchRouteList = new ArrayList<DispatchRoute>();
			if (null != id && !"".equals(id)) {
				DeliveryReceipt deliveryReceipt = sentCarSingleManagementController.doListDeliveryReceiptById(id);
				if (deliveryReceipt != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("deliveryreceiptid," + SearchCondition.EQUAL, deliveryReceipt.getId());
					// 查询关联表信息
					List<DeliveryReceiptToRoute> deliveryReceiptToRouteList = sentCarSingleManagementController.doListDeliveryReceiptToRouteByParams(params);
					if (deliveryReceiptToRouteList != null && deliveryReceiptToRouteList.size() > 0) {
						for (DeliveryReceiptToRoute deliveryReceiptToRoute : deliveryReceiptToRouteList) {
							if (deliveryReceiptToRoute != null) {
								DispatchRoute dispatchRoute = sentCarSingleManagementController.doListDispatchRouteById(deliveryReceiptToRoute.getRouteid());
								dispatchRouteList.add(dispatchRoute);
							}
						}
					}
				}
			}
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
			Pager pager = sentCarSingleManagementController.doListVehicle(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goVehicleList";
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

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

	public DeliveryReceipt getDeliveryReceipt() {
		return deliveryReceipt;
	}

	public void setDeliveryReceipt(DeliveryReceipt deliveryReceipt) {
		this.deliveryReceipt = deliveryReceipt;
	}

	public List<DeliveryReceipt> getDeliveryReceiptList() {
		return deliveryReceiptList;
	}

	public void setDeliveryReceiptList(List<DeliveryReceipt> deliveryReceiptList) {
		this.deliveryReceiptList = deliveryReceiptList;
	}

}
