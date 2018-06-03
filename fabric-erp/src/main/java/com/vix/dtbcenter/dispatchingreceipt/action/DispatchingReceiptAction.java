package com.vix.dtbcenter.dispatchingreceipt.action;

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
import com.vix.dtbcenter.dispatchingreceipt.controller.DispatchingReceiptController;
import com.vix.dtbcenter.dispatchingreceipt.entity.DispatchingReceipt;
import com.vix.dtbcenter.dispatchingreceipt.entity.DispatchingReceiptToSalesOrder;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToSalesOrder;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class DispatchingReceiptAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(DispatchingReceiptAction.class);
	@Autowired
	private DispatchingReceiptController dispatchingReceiptController;
	private String id;
	private String ids;
	private String pageNo;
	private DispatchingReceipt dispatchingReceipt;
	private List<DispatchingReceipt> dispatchingReceiptList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			dispatchingReceiptList = dispatchingReceiptController.doListDispatchingReceiptList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
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
			Pager pager = dispatchingReceiptController.doListDeliveryReceipt(params, getPager());
			setPager(pager);
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				dispatchingReceipt = dispatchingReceiptController.doListDispatchingReceiptById(id);
				logger.info("");
			} else {
				dispatchingReceipt = new DispatchingReceipt();
				dispatchingReceipt.setIsTemp("1");
				dispatchingReceipt = dispatchingReceiptController.doSaveDeliveryReceipt(dispatchingReceipt);
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
			if (null != dispatchingReceipt.getId()) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(dispatchingReceipt, updateField);
			dispatchingReceipt = dispatchingReceiptController.doSaveDeliveryReceipt(dispatchingReceipt);
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
			DispatchingReceipt pb = dispatchingReceiptController.doListDispatchingReceiptById(id);
			if (null != pb) {
				dispatchingReceiptController.doDeleteDispatchingReceipt(pb);
				renderText(DELETE_SUCCESS);
			}
			logger.info(DELETE_SUCCESS);
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
				dispatchingReceiptController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
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
			Pager pager = dispatchingReceiptController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
	}

	// 将销售订单跟配送回执单关联
	public String converterDispatchingReceiptToSalesOrder() {
		String deliveryReceiptId = getRequestParameter("deliveryReceiptId");
		// 获取派车单
		DeliveryReceipt deliveryReceipt = dispatchingReceiptController.doListDeliveryReceiptById(deliveryReceiptId);
		dispatchingReceipt = dispatchingReceiptController.doListDispatchingReceiptById(id);
		// 将选择的订单编号转存成数组格式
		if (deliveryReceipt != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deliveryReceiptId," + SearchCondition.EQUAL, deliveryReceipt.getId());
			// 查询关联表信息
			List<DeliveryReceiptToSalesOrder> deliveryReceiptToSalesOrderList = dispatchingReceiptController.DeliveryReceiptToSalesOrder(params);
			if (deliveryReceiptToSalesOrderList != null && deliveryReceiptToSalesOrderList.size() > 0) {
				for (DeliveryReceiptToSalesOrder deliveryReceiptToSalesOrder : deliveryReceiptToSalesOrderList) {
					if (deliveryReceiptToSalesOrder != null) {
						SalesOrder salesOrder = dispatchingReceiptController.doListSalesOrderById(deliveryReceiptToSalesOrder.getSalesOrderId());
						try {
							if (salesOrder != null) {
								dispatchingReceiptController.convSalesOrderToDeliveryReceipt(dispatchingReceipt, salesOrder);
							}
						} catch (Exception e) {
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
				DispatchingReceipt dispatchingReceipt = dispatchingReceiptController.doListDispatchingReceiptById(id);
				if (dispatchingReceipt != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("dispatchingReceiptId," + SearchCondition.EQUAL, dispatchingReceipt.getId());
					// 查询关联表信息
					List<DispatchingReceiptToSalesOrder> dispatchingReceiptToSalesOrderList = dispatchingReceiptController.doListDispatchingReceiptToSalesOrderByParams(params);
					if (dispatchingReceiptToSalesOrderList != null && dispatchingReceiptToSalesOrderList.size() > 0) {
						for (DispatchingReceiptToSalesOrder dispatchingReceiptToSalesOrder : dispatchingReceiptToSalesOrderList) {
							if (dispatchingReceiptToSalesOrder != null) {
								SalesOrder salesOrder = dispatchingReceiptController.doListSalesOrderById(dispatchingReceiptToSalesOrder.getSalesOrderId());
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

	/* 派车单 */
	public String goChooseTruckingOrder() {
		return "goChooseTruckingOrder";
	}

	/**
	 * 获取派车单列表
	 * 
	 * @return
	 */
	public String goTruckingOrderList() {
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
			Pager pager = dispatchingReceiptController.doListgoTruckingOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTruckingOrderList";
	}

	public String goSearch() {
		return "goSearch";
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

	public DispatchingReceipt getDispatchingReceipt() {
		return dispatchingReceipt;
	}

	public void setDispatchingReceipt(DispatchingReceipt dispatchingReceipt) {
		this.dispatchingReceipt = dispatchingReceipt;
	}

	public List<DispatchingReceipt> getDispatchingReceiptList() {
		return dispatchingReceiptList;
	}

	public void setDispatchingReceiptList(List<DispatchingReceipt> dispatchingReceiptList) {
		this.dispatchingReceiptList = dispatchingReceiptList;
	}
}
