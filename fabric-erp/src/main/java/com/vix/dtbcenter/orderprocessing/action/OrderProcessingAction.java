package com.vix.dtbcenter.orderprocessing.action;

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
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.orderprocessing.controller.OrderProcessingController;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesOrder;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class OrderProcessingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(OrderProcessingAction.class);
	@Autowired
	private OrderProcessingController orderProcessingController;
	@Autowired
	private IItemService itemService;
	private String id;
	private String ids;
	private String pageNo;
	private String parentId;
	/**
	 * 销售订单(服务单)
	 */
	private SalesOrder salesOrder;
	private List<SalesOrder> salesOrderList;
	private SaleOrderItem saleOrderItem;
	private List<SaleOrderItem> saleOrderItemList;
	/**
	 * 计量单位
	 */
	private List<MeasureUnit> measureUnitList;
	/**
	 * 销售订单明细发运计划
	 */
	private SalesDeliveryPlan saleOrderItemsDeliveryPlan;
	private List<SalesDeliveryPlan> saleOrderItemsDeliveryPlanList;
	/**
	 * 商品信息
	 */
	private Item item;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			salesOrderList = orderProcessingController.doListSalesOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
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
			Pager pager = orderProcessingController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				salesOrder = orderProcessingController.doListSalesOrderById(id);
				logger.info("");
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				salesOrder = orderProcessingController.doSaveSalesOrder(salesOrder, null);
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
			if (null != salesOrder.getId() && !"".equals(salesOrder.getId())) {
				isSave = false;
			}
			salesOrder.setIsTemp("");
			// 销售订单明细
			String saleOrderItemsDeliveryPlanJson = getRequestParameter("saleOrderItemsDeliveryPlanJson");
			List<SaleOrderItem> saleOrderItemList = null;
			if (saleOrderItemsDeliveryPlanJson != null && saleOrderItemsDeliveryPlanJson.length() > 0) {
				saleOrderItemList = new JSONDeserializer<List<SaleOrderItem>>().use("values", SaleOrderItem.class).deserialize(saleOrderItemsDeliveryPlanJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(salesOrder, updateField);

			salesOrder = orderProcessingController.doSaveSalesOrder(salesOrder, saleOrderItemList);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SalesOrder pb = orderProcessingController.doListSalesOrderById(id);
			if (null != pb) {
				orderProcessingController.doDeleteSalesOrder(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteSaleOrderItemById() {
		try {
			SaleOrderItem pb = orderProcessingController.doListSaleOrderItemById(id);
			if (null != pb) {
				orderProcessingController.doDeleteSaleOrderItem(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取销售订单明细发运计划的json数据 */
	public void getSaleOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesOrder salesOrder = orderProcessingController.doListSalesOrderById(id);
				if (salesOrder != null) {
					json = convertListToJson(new ArrayList<SaleOrderItem>(salesOrder.getSaleOrderItems()), salesOrder.getSaleOrderItems().size(), "salesOrder");
				}
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

	public String saveOrUpdateSaleOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				salesOrder = orderProcessingController.doListSalesOrderById(id);
				saleOrderItem.setSalesOrder(salesOrder);
				orderProcessingController.doSaveSaleOrderItem(saleOrderItem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	// 跳转到添加物料页面
	public String goAddSaleOrderItem() {

		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				saleOrderItem = orderProcessingController.doListSaleOrderItemById(id);
			} else {
				saleOrderItem = new SaleOrderItem();
				saleOrderItem = orderProcessingController.doSaveSaleOrderItem(saleOrderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goAddSaleOrderItem";
	}

	public String goSaveOrUpdateSaleOrderItem() {
		try {
			Map<String, Object> params = getParams();
			measureUnitList = orderProcessingController.doListMeasureUnitList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				saleOrderItem = orderProcessingController.doListSaleOrderItemById(id);
			} else {
				saleOrderItem = new SaleOrderItem();
				if (StringUtils.isNotEmpty(parentId)) {
					SalesOrder salesOrder = orderProcessingController.doListSalesOrderById(parentId);
					saleOrderItem.setSalesOrder(salesOrder);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateSaleOrderItem";
	}

	public String goChooseItemListContent() {
		try {
			Map<String, Object> params = getParams();
			getParams().put("isTemp," + SearchCondition.NOEQUAL, "1");
			getPager().setPageSize(10);
			itemService.findPagerByHqlConditions(getPager(), Item.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseItemListContent";
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public List<SaleOrderItem> getSaleOrderItemList() {
		return saleOrderItemList;
	}

	public void setSaleOrderItemList(List<SaleOrderItem> saleOrderItemList) {
		this.saleOrderItemList = saleOrderItemList;
	}

	public SalesDeliveryPlan getSaleOrderItemsDeliveryPlan() {
		return saleOrderItemsDeliveryPlan;
	}

	public void setSaleOrderItemsDeliveryPlan(SalesDeliveryPlan saleOrderItemsDeliveryPlan) {
		this.saleOrderItemsDeliveryPlan = saleOrderItemsDeliveryPlan;
	}

	public List<SalesDeliveryPlan> getSaleOrderItemsDeliveryPlanList() {
		return saleOrderItemsDeliveryPlanList;
	}

	public void setSaleOrderItemsDeliveryPlanList(List<SalesDeliveryPlan> saleOrderItemsDeliveryPlanList) {
		this.saleOrderItemsDeliveryPlanList = saleOrderItemsDeliveryPlanList;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the measureUnitList
	 */
	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	/**
	 * @param measureUnitList
	 *            the measureUnitList to set
	 */
	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

}
