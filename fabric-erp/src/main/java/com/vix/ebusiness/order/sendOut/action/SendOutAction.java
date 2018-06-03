package com.vix.ebusiness.order.sendOut.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.order.orderProcess.processor.OrderUploadProcessor;
import com.vix.ebusiness.order.sendOut.controller.SendOutController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class SendOutAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SendOutAction.class);
	@Autowired
	private SendOutController sendOutController;
	@Autowired
	private OrderUploadProcessor orderUploadProcessor;
	private String id;
	private String pageNo;
	/* 销售订单 */
	private Order order;
	private List<Order> orderList;
	private String orderBatchId;
	private OrderBatch orderBatch;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			orderList = sendOutController.doListOrder(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(orderBatchId) && !"0".equals(orderBatchId)) {
				params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			}
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = sendOutController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				order = sendOutController.doListOrderById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 发货
	 * 
	 * @return
	 */
	public String sendOutOrder() {
		boolean isSave = true;
		try {
			String orders = "";
			ChannelDistributor channelDistributor = null;
			if (StringUtils.isNotEmpty(id)) {
				Order order = sendOutController.doListOrderById(id);
				channelDistributor = sendOutController.doListChannelDistributorById(order.getChannelDistributor().getId());
				orders = id;
			} else {
				if (StringUtils.isNotEmpty(orderBatchId)) {
					orderBatch = sendOutController.doListOrderBatchById(orderBatchId);
					channelDistributor = sendOutController.doListChannelDistributorById(orderBatch.getChannelDistributor().getId());
					if (orderBatch != null) {
						if (orderBatch.getOrderList() != null) {
							for (Order order : orderBatch.getOrderList()) {
								orders += "," + order.getId();
							}
						}
					}
				} else {
				}
			}
			orderUploadProcessor.dealOrder(orders, channelDistributor);
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
	 * 电商发货
	 * 
	 * @return
	 */
	public String sendOrderToBusiness() {
		boolean isSave = false;
		try {
			if (StringUtils.isNotEmpty(id)) {
				Order order = sendOutController.doListOrderById(id);
				sendOrder(order);
			} else {
				if (StringUtils.isNotEmpty(orderBatchId)) {
					orderBatch = sendOutController.doListOrderBatchById(orderBatchId);
					if (orderBatch != null) {
						if (orderBatch.getOrderList() != null) {
							for (Order order : orderBatch.getOrderList()) {
								isSave = sendOrder(order);
							}
						}
						if (isSave) {
							orderBatch.setStatus("4");
							orderBatch = sendOutController.doSaveOrUpdateOrderBatch(orderBatch);
						}
					}
				}
			}
			if (isSave) {
				setMessage("发货成功");
			} else {
				setMessage("发货失败");
			}
		} catch (Exception e) {
			if (isSave) {
				this.setMessage("发货失败");
			} else {
				this.setMessage("发货失败");
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	private Boolean sendOrder(Order order) {
		Boolean check = false;
		try {
			if (order != null) {
				RestTemplate restTemplate = new RestTemplate();
				Map<String, Object> urlVariables = new HashMap<String, Object>();
				urlVariables.put("code", order.getOuterId());
				urlVariables.put("expressNo", order.getShippingNo());
				urlVariables.put("expressType", order.getLogistics().getCode());
				String authCode = null;
				if (StrUtils.objectIsNotNull("VIX")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
					Md5Encoder m5 = new Md5EncoderImpl();
					authCode = m5.encodeString("VIX", sdf.format(new Date()));
					System.out.println("authCode:" + authCode);
				}
				urlVariables.put("validateCode", authCode);

				String message = restTemplate.postForObject("http://121.41.36.11/SnowX/restService/ecOrder/updateEcOrderExpressNoByCode.rs?code={code}&validateCode={validateCode}&expressNo={expressNo}&expressType={expressType}", String.class, String.class, urlVariables);
				JSONObject json = JSONObject.fromObject(message);
				if (json.has("success")) {
					if (!"false".equals(json.getString("success"))) {
						check = true;
					}
				}
				Set<OrderDetail> orderDetails = order.getOrderDetailList();
				if (orderDetails != null && orderDetails.size() > 0) {
					for (OrderDetail orderDetail : orderDetails) {
						Map<String, Object> p = new HashMap<String, Object>();
						p.put("skuCode," + SearchCondition.EQUAL, orderDetail.getBn());
						List<InventoryCurrentStock> inventoryCurrentStockList = baseHibernateService.findAllByConditions(InventoryCurrentStock.class, p);
						if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
							for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
								inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() - orderDetail.getNum());
								baseHibernateService.merge(inventoryCurrentStock);
							}
						}
					}
					order.setStatus("2");
					order.setDealState(5);
					sendOutController.doSaveOrUpdateOrder(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderBatchId() {
		return orderBatchId;
	}

	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

}
