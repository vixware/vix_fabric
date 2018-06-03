/**
 * 
 */
package com.vix.drp.dayWithSalesOrder.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.dayWithSalesOrder.controller.DayWithSalesOrderController;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2014-5-6
 */

@Controller
@Scope("prototype")
public class DayWithSalesOrderAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private DayWithSalesOrderController dayWithSalesOrderController;
	private String id;
	/**
	 * 日配销售订单
	 */
	private SalesOrder salesOrder;
	private List<SalesOrder> salesOrderList;
	/**
	 * 日配销售订单明细
	 */
	private SaleOrderItem saleOrderItem;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = dayWithSalesOrderController.findSalesOrderPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesOrder = dayWithSalesOrderController.doListSalesOrderById(id);
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				salesOrder.setCode(VixUUID.createCode(10));
				salesOrder = dayWithSalesOrderController.doSaveSalesOrder(salesOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesOrder.getId() && !"".equals(salesOrder.getId())) {
				isSave = false;
			}
			salesOrder.setIsTemp("");
			salesOrder = dayWithSalesOrderController.doSaveSalesOrder(salesOrder);
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

	/**
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			SalesOrder salesOrder = dayWithSalesOrderController.doListSalesOrderById(id);
			if (null != salesOrder) {
				dayWithSalesOrderController.doDeleteSalesOrder(salesOrder);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getSaleOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				SalesOrder salesOrder = dayWithSalesOrderController.doListSalesOrderById(id);
				if (null != salesOrder) {
					json = convertListToJson(new ArrayList<SaleOrderItem>(salesOrder.getSaleOrderItems()), salesOrder.getSaleOrderItems().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateSaleOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				saleOrderItem = dayWithSalesOrderController.doListSaleOrderItemById(id);
			} else {
				saleOrderItem = new SaleOrderItem();
				saleOrderItem = dayWithSalesOrderController.doSaveSaleOrderItem(saleOrderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSaleOrderItem";
	}

	public String saveOrUpdateSaleOrderItem() {
		boolean isSave = true;
		try {
			if (saleOrderItem != null && StringUtils.isNotEmpty(saleOrderItem.getId()) && !"0".equals(saleOrderItem.getId())) {
				isSave = false;
			}
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				SalesOrder salesOrder = dayWithSalesOrderController.doListSalesOrderById(id);
				if (null != salesOrder) {
					saleOrderItem.setSalesOrder(salesOrder);
				}
			}
			saleOrderItem = dayWithSalesOrderController.doSaveSaleOrderItem(saleOrderItem);
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

	public String deleteSaleOrderItemById() {
		try {
			SaleOrderItem saleOrderItem = dayWithSalesOrderController.doListSaleOrderItemById(id);
			if (null != saleOrderItem) {
				dayWithSalesOrderController.doDeleteSaleOrderItemByEntity(saleOrderItem);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

}
