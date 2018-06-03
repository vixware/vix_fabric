package com.vix.ebusiness.order.outboundprocess.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.vix.ebusiness.entity.InvoiceList;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.expressFeeRules.entity.DeliveryArea;
import com.vix.ebusiness.expressFeeRules.entity.ExpressFeeRules;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.ebusiness.order.outboundprocess.controller.OutBoundProcessController;
import com.vix.ebusiness.postageRecords.entity.PostageRecords;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

@Controller
@Scope("prototype")
public class OutBoundProcessAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(OutBoundProcessAction.class);
	@Autowired
	private OutBoundProcessController outBoundProcessController;
	private String id;
	private String invoiceListId;
	private String orderBatchId;
	private String pageNo;
	/**
	 * 分拣批次
	 */
	private OrderBatch orderBatch;
	private List<OrderBatch> orderBatchList;
	private Order order;

	private List<Order> orderList;
	private String businussOrderId;
	/**
	 * 发货单列表
	 */
	private List<InvoiceList> invoiceListList;
	/**
	 * 发货单
	 */
	private InvoiceList invoiceList;

	@Override
	public String goList() {
		try {
			orderBatchList = outBoundProcessController.doListOrderBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList1() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager.setPageSize(100);
			params.put("dealState," + SearchCondition.EQUAL, 7);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// 获取员工信息
			/*Employee employee = outBoundProcessController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
			if (employee != null && employee.getOrganizationUnit() != null) {

			}*/
			pager = outBoundProcessController.doListOrderPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList1";
	}

	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("DESC");
			pager.setOrderField("id");
			pager.setPageSize(100);
			params.put("dealState," + SearchCondition.EQUAL, 7);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			//处理查询条件
			String orderCode = getRequestParameter("orderCode");
			if (null != orderCode && !"".equals(orderCode)) {
				params.put("tradeNo," + SearchCondition.EQUAL, orderCode);
			}
			String buyerNick = getDecodeRequestParameter("buyerNick");
			if (null != buyerNick && !"".equals(buyerNick)) {
				params.put("buyerNick," + SearchCondition.ANYLIKE, buyerNick);
			}
			String receiverMobile = getRequestParameter("receiverMobile");
			if (null != receiverMobile && !"".equals(receiverMobile)) {
				params.put("receiverMobile," + SearchCondition.EQUAL, receiverMobile);
			}
			String payTypeCn = getDecodeRequestParameter("payTypeCn");
			if (null != payTypeCn && !"".equals(payTypeCn)) {
				params.put("payTypeCn," + SearchCondition.ANYLIKE, payTypeCn);
			}
			//处理查询条件
			pager = outBoundProcessController.doListOrderPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList1";
	}

	public String goSingleList2() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("DESC");
			pager.setPageSize(100);
			pager.setOrderField("id");
			params.put("status," + SearchCondition.EQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = outBoundProcessController.doListOrderBatch(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList2";
	}

	public String goSingleList3() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("DESC");
			pager.setOrderField("id");
			pager.setPageSize(100);
			params.put("status," + SearchCondition.EQUAL, "2");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = outBoundProcessController.doListOrderBatch(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList3";
	}

	public String goSingleList4() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("DESC");
			pager.setPageSize(100);
			pager.setOrderField("id");
			params.put("status," + SearchCondition.EQUAL, "3");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = outBoundProcessController.doListOrderBatch(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList4";
	}

	/**
	 * 显示已发货
	 * 
	 * @return
	 */
	public String goSingleList5() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("DESC");
			pager.setPageSize(100);
			pager.setOrderField("id");
			params.put("status," + SearchCondition.EQUAL, "4");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = outBoundProcessController.doListOrderBatch(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList5";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				orderBatch = outBoundProcessController.doListOrderBatchById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				order = outBoundProcessController.doListOrderById(id);
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
	public String goOrderBatch() {
		try {
			if (StringUtils.isNotEmpty(orderBatchId) && !"0".equals(orderBatchId)) {
				orderBatch = outBoundProcessController.doListOrderBatchById(orderBatchId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOrderBatch";
	}

	/**
	 * 弹出查询条件界面
	 * 
	 * @return
	 */
	public String goSearchOrder() {
		return "goSearchOrder";
	}

	public String goPrintTemp() {
		return "goPrintTemp";
	}

	// 按批次打印发货单
	public String goPrintPickingList() {
		try {
			Map<String, Object> params = getParams();
			params.put("orderBatch.id," + SearchCondition.EQUAL, id);
			invoiceListList = outBoundProcessController.doListInvoiceListList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrint";
	}

	//打印单个发货单
	public String goPrintSinglePickingList() {
		try {
			if (StringUtils.isNotEmpty(invoiceListId) && !"0".equals(invoiceListId)) {
				invoiceList = outBoundProcessController.doListInvoiceListById(invoiceListId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintSinglePickingList";
	}

	public String searchOrder() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = outBoundProcessController.doListOrderBatch(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goOrderPickingList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("DESC");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 0);
			pager = outBoundProcessController.doListOrderBatch(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOrderPickingList";
	}

	public void getOrderDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				OrderBatch orderBatch = outBoundProcessController.doListOrderBatchById(id);
				if (orderBatch != null) {
					json = convertListToJson(new ArrayList<Order>(orderBatch.getOrderList()), orderBatch.getOrderList().size());
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

	/** 处理修改操作 */
	public String updateOrderBatch() {
		try {
			orderBatch = outBoundProcessController.doListOrderBatchById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 配货
	 * 
	 * 保存订单信息 ,并将所有订单明细的购买数量跟配货数据进行对比 所有购买数量跟配货数量都一致时 修改主订单状态为待发货
	 * 
	 * @return
	 */
	public String updateOrderDetail() {
		boolean isSave = true;
		boolean isPicking = true;
		boolean check = true;
		try {
			/* 订单明细 */
			String orderDetailJson = getRequestParameter("orderDetailJson");
			List<OrderDetail> orderDetailList = null;
			// 判断入库明细是否为空
			if (orderDetailJson != null && !"".equals(orderDetailJson)) {
				DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
				orderDetailList = new JSONDeserializer<List<OrderDetail>>().use(Date.class, dateTransformer).use("values", OrderDetail.class).deserialize(orderDetailJson);
			}

			if (null != order.getId() && !"".equals(order.getId())) {
				List<OrderDetail> orderDetails = outBoundProcessController.doSaveOrderDetail(order, orderDetailList);
				if (orderDetails != null) {
					for (OrderDetail orderDetail : orderDetails) {
						if (orderDetail != null) {
							/*if (orderDetail.getNum() != orderDetail.getPickingNum()) {
								isPicking = false;
							} else {
								orderDetail.setStatus("已配货");
							}*/
						}
					}
					/* 当所有子订单都配货完成后，主订单处理状态改完待打单 */
					if (isPicking) {
						order = outBoundProcessController.doListOrderById(order.getId());
						order.setDealState(3);
						outBoundProcessController.doSaveOrder(order);
					}
				}
			}
			//更改分拣批次单状态
			if (order.getOrderBatch() != null) {
				OrderBatch orderBatch = order.getOrderBatch();
				if (orderBatch != null && orderBatch.getOrderList() != null && orderBatch.getOrderList().size() > 0) {
					for (Order order : orderBatch.getOrderList()) {
						if (order != null) {
							if (order.getDealState() != 3) {
								return UPDATE;
							} else {
								check = true;
							}
						}
					}

					if (check) {
						orderBatch.setStatus("3");
						outBoundProcessController.doSaveOrderBatch(orderBatch);
					}
				}
			}

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

	/**
	 * 包裹称重
	 * 
	 * @return
	 */
	public String saveOrUpdateWeighing() {
		boolean isSave = true;
		try {
			if (null != order.getId() && !"".equals(order.getId())) {
				Double weight = 0d;
				if (order.getWeight() != null) {
					weight = order.getWeight();
				}
				order = outBoundProcessController.doListOrderById(order.getId());
				//包裹称重
				order.setWeight(weight);
				order.setIsWeight("1");
				order = outBoundProcessController.doSaveOrder(order);
				//计算邮费
				Double expressFee = getExpressFee(order.getLogistics().getCode(), order.getReceiverState(), weight);
				//生成邮费记录
				if (expressFee != null) {
					PostageRecords postageRecords = new PostageRecords();
					postageRecords.setExpressFee(expressFee);
					postageRecords.setLogistics(order.getLogistics());
					postageRecords.setWeight(weight);
					postageRecords.setCreateTime(new Date());
					postageRecords.setOrder(order);
					initEntityBaseController.initEntityBaseAttribute(postageRecords);
					baseHibernateService.merge(postageRecords);
				}
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
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

	/**
	 * 获取邮费
	 * 
	 * @param logisticsCode
	 * @param receiverState
	 * @param weight
	 * @return
	 */
	private Double getExpressFee(String logisticsCode, String receiverState, Double weight) {

		Double expressFee = 0d;
		Map<String, Object> params = getParams();
		String deliveryAreaId = "";
		try {
			List<DeliveryArea> deliveryAreaList = baseHibernateService.findAllByEntityClass(DeliveryArea.class);
			if (deliveryAreaList != null && deliveryAreaList.size() > 0) {
				for (DeliveryArea deliveryArea : deliveryAreaList) {
					if (deliveryArea != null && deliveryArea.getSubProvincess() != null) {
						for (Provinces provinces : deliveryArea.getSubProvincess()) {
							if (receiverState.startsWith(provinces.getName())) {
								deliveryAreaId = deliveryArea.getId();
								break;
							}
						}
					}
				}
			}
			params.put("logistics.code," + SearchCondition.EQUAL, logisticsCode);
			params.put("deliveryArea.id," + SearchCondition.EQUAL, deliveryAreaId);
			List<ExpressFeeRules> expressFeeRulesList = outBoundProcessController.doListExpressFeeRulesList(params);
			if (expressFeeRulesList != null && expressFeeRulesList.size() > 0) {
				for (ExpressFeeRules expressFeeRules : expressFeeRulesList) {
					if (expressFeeRules != null) {
						if (weight <= expressFeeRules.getFirstWeight()) {
							expressFee = expressFeeRules.getFirstCost();
						} else
							expressFee = expressFeeRules.getFirstCost() + Math.ceil((weight - expressFeeRules.getFirstWeight()) / expressFeeRules.getPerWeight()) * expressFeeRules.getPerCost();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return expressFee;
	}

	public String goListOrderBatchContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			orderList = outBoundProcessController.doListOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListOrderBatchContent";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

	public List<OrderBatch> getOrderBatchList() {
		return orderBatchList;
	}

	public void setOrderBatchList(List<OrderBatch> orderBatchList) {
		this.orderBatchList = orderBatchList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public String getBusinussOrderId() {
		return businussOrderId;
	}

	public void setBusinussOrderId(String businussOrderId) {
		this.businussOrderId = businussOrderId;
	}

	public InvoiceList getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(InvoiceList invoiceList) {
		this.invoiceList = invoiceList;
	}

	public List<InvoiceList> getInvoiceListList() {
		return invoiceListList;
	}

	public void setInvoiceListList(List<InvoiceList> invoiceListList) {
		this.invoiceListList = invoiceListList;
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

	/**
	 * @return the invoiceListId
	 */
	public String getInvoiceListId() {
		return invoiceListId;
	}

	/**
	 * @param invoiceListId
	 *            the invoiceListId to set
	 */
	public void setInvoiceListId(String invoiceListId) {
		this.invoiceListId = invoiceListId;
	}

	/**
	 * @return the orderBatchId
	 */
	public String getOrderBatchId() {
		return orderBatchId;
	}

	/**
	 * @param orderBatchId
	 *            the orderBatchId to set
	 */
	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
	}

}
