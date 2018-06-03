package com.vix.ebusiness.order.orderProcess.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ec.api.CloudShopsException;
import com.ec.api.adapter.CloudShopsClientAdapter;
import com.ec.api.trade.domain.OrderDetailInfo;
import com.ec.api.trade.request.LBPOutStorageRequest;
import com.ec.api.trade.request.OrderGetRequest;
import com.ec.api.trade.request.SopOutStorageRequest;
import com.ec.api.trade.request.SoplOutstorageRequest;
import com.ec.api.trade.request.TradeUpdateStateRequest;
import com.ec.api.trade.response.LBPOutStorageResponse;
import com.ec.api.trade.response.OrderGetResponse;
import com.ec.api.trade.response.SopOutStorageResponse;
import com.ec.api.trade.response.SoplOutstorageResponse;
import com.ec.api.trade.response.TradeUpdateStateResponse;
import com.vix.core.constant.SearchCondition;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.InvoiceList;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderOperationLog;
import com.vix.ebusiness.entity.Shipping;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.ebusiness.order.orderProcess.vo.WaitDelivery;
import com.vix.ebusiness.util.channel.CloudShopsChannelGet;

import net.sf.json.JSONObject;

/**
 * 订单发货处理 com.vix.ebusiness.order.orderProcess.processor.OrderUploadProcessor
 *
 * @author zhanghaibing
 *
 * @date 2014年9月23日
 */
@Controller("orderUploadProcessor")
public class OrderUploadProcessor {
	@Autowired
	private IOrderProcessService orderProcessService;

	/**
	 * @param orders
	 *            订单编码 暂不考虑通过配送中心发货
	 * @param channelDistributor
	 * @throws Exception
	 */
	public void dealOrder(String orders, ChannelDistributor channelDistributor) throws Exception {
		if (orders != null && !"".equals(orders)) {
			// 处理京东订单发货
			List<Order> allJingDongOrders = getJingDongOrders(orders);
			if (allJingDongOrders != null && allJingDongOrders.size() > 0) {
				// 京东发货
				sysOrderStateToJingDong(allJingDongOrders, channelDistributor);
			} else {
				allJingDongOrders = getOffJingdongRows(orders);
				if (allJingDongOrders != null && allJingDongOrders.size() > 0) {
					// 京东发货
					sysOrderStateToJingDong(allJingDongOrders, channelDistributor);
				}
			}

			// 处理其他网店订单发货
			List<Order> orderList = getAllOrders(orders);
			if (orderList != null && orderList.size() > 0) {
				for (Order order : orderList) {
					// 订单发货处理
					updateOrdersState(order, channelDistributor);
				}
			} else {
				orderList = getOrders(orders);
				if (orderList != null && orderList.size() > 0) {
					for (Order order : orderList) {
						// 订单发货处理
						updateOrdersState(order, channelDistributor);
					}
				}
			}

		} else {
			if (channelDistributor == null)
				return;
			List<Order> orderList = getAllOrderByChannelId(channelDistributor.getId());
			if (orderList != null && orderList.size() > 0) {
				Long typeId = channelDistributor.getChannelTypeId();
				if (typeId == 3) {
					sysOrderStateToJingDong(orderList, channelDistributor);
				} else {
					for (Order order : orderList) {
						// 订单发货处理
						updateOrdersState(order, channelDistributor);
					}
				}
			}
		}
	}

	/**
	 * 处理京东发货
	 * 
	 * @param row
	 * @throws Exception
	 */
	private void sysOrderStateToJingDong(List<Order> orderList, ChannelDistributor channelDistributor) throws Exception {
		if (orderList != null && orderList.size() > 0) {
			if (channelDistributor != null) {
				String props = channelDistributor.getChannelProps();
				if (props != null && !"".equals(props)) {
					JSONObject json = JSONObject.fromObject(props);
					int mallType = Integer.parseInt(json.getString("mallType"));
					if (mallType == 5) {
						// 京东店LBP商户发货处理
						try {
							dealLBP(orderList, channelDistributor);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (mallType == 6) {
						// 京东店SOP商户发货处理
						try {
							dealSOP(orderList, channelDistributor);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (mallType == 7) {
						// 京东店SOPL商户发货处理
						try {
							dealSOPL(orderList, channelDistributor);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * 处理SOPL出库
	 * 
	 * @param orderList
	 * @param tradeIdList
	 * @throws Exception
	 */
	private void dealSOPL(List<Order> orderList, ChannelDistributor channelDistributor) throws Exception {
		if (orderList != null && orderList.size() > 0) {
			for (Order order : orderList) {
				SoplOutstorageRequest soplOutstorageRequest = new SoplOutstorageRequest();
				soplOutstorageRequest.setChannelId(String.valueOf(order.getChannelDistributor().getId()));
				soplOutstorageRequest.setOrderId(order.getOuterId());
				soplOutstorageRequest.setNum("1");
				soplOutstorageRequest.setTenantId(order.getTenantId());
				soplOutstorageRequest.setSenderType("1");
				SoplOutstorageResponse soplOutstorageResponse = CloudShopsClientAdapter.getInstance().execute(soplOutstorageRequest, CloudShopsChannelGet.getChannel(channelDistributor));
				if (soplOutstorageResponse.isSuccess()) {
					// String sql = "UPDATE trade set status = 2,DEAL_STATE = 5
					// WHERE trade_id = " + tradeId;
					order.setStatus("2");// 订单状态置为已发货
					order.setDealState(5);// 处理状态置为已发货
					orderProcessService.merge(order);

					// 处理发货单
					InvoiceList invoiceList = orderProcessService.findEntityByAttribute(InvoiceList.class, "order.id", order.getId());
					if (invoiceList != null) {
						// String sql2 = "UPDATE invoice_list set status =
						// 1,confirm_time = CURRENT_TIMESTAMP,archived_time =
						// CURRENT_TIMESTAMP,is_archived = 1 WHERE
						// invoice_list.TRADE_ID=" + tradeId;
						invoiceList.setStatus("1");
						invoiceList.setConfirmTime(new Date());
						invoiceList.setArchivedTime(new Date());
						invoiceList.setIsArchived(1);
						orderProcessService.merge(invoiceList);
					}

					// 插入订单操作日志
					// String logSql = "INSERT into trade_operation_log
					// (log_id,trade_id,staff_id,operate_time,operate_desc)
					// VALUES (" + VixUUID.getUUID() + "," + tradeId +
					// ",1,CURRENT_TIMESTAMP,'自动出库成功!')";
					OrderOperationLog orderOperationLog = new OrderOperationLog();
					orderOperationLog.setOrder(order);
					orderOperationLog.setOperateTime(new Date());
					orderOperationLog.setOperateDesc("自动出库成功!");
					orderProcessService.merge(orderOperationLog);

				} else {
					String msg = null;
					if (soplOutstorageResponse != null) {
						msg = "------自动出库失败:错误编码为 " + soplOutstorageResponse.getSubCode() + " 错误信息为 " + soplOutstorageResponse.getSubMsg();
						// String sql = "UPDATE trade set out_interacting_state
						// = 1,latest_interacting_error='" + msg +
						// "',latest_interacting_error_time = CURRENT_TIMESTAMP
						// WHERE trade_id=" + tradeId;
						order.setOutInteractingState(1);
						order.setLatestInteractingError(msg);
						order.setLatestInteractingErrorTime(new Date());
						orderProcessService.merge(order);

						// String logSql = "INSERT into trade_operation_log
						// (log_id,trade_id,staff_id,operate_time,operate_desc)
						// VALUES (" + VixUUID.getUUID() + "," + tradeId +
						// ",1,CURRENT_TIMESTAMP,'" + msg + "')";
						OrderOperationLog orderOperationLog = new OrderOperationLog();
						orderOperationLog.setOrder(order);
						orderOperationLog.setOperateTime(new Date());
						orderOperationLog.setOperateDesc(msg);
						orderProcessService.merge(orderOperationLog);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param orderList
	 * @param tradeIdList
	 * @throws Exception
	 */
	private void dealSOP(List<Order> orderList, ChannelDistributor channelDistributor) throws Exception {

		List<WaitDelivery> waitDeliveryList = null;
		if (orderList != null && orderList.size() > 0) {
			waitDeliveryList = new ArrayList<WaitDelivery>();
			for (Order order : orderList) {
				String tradeId = order.getId();
				String orderId = order.getOuterId();
				String logisticsId = order.getLogistics().getCode();
				String outsid = order.getShippingNo();
				if (outsid == null || "".equals(outsid) || logisticsId == null || "".equals(logisticsId)) {
					continue;
				}
				syscSOPOrder(waitDeliveryList, tradeId, orderId, logisticsId, outsid, false, channelDistributor.getId(), channelDistributor);
			}

			if (waitDeliveryList != null && waitDeliveryList.size() > 0) {
				try {
					Thread.sleep(5 * 1000 * 60);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for (WaitDelivery waitDelivery : waitDeliveryList) {
					syscSOPOrder(waitDeliveryList, waitDelivery.getTradeId(), waitDelivery.getOrderId(), waitDelivery.getLogisticsId(), waitDelivery.getOutsid(), true, waitDelivery.getChannelDistributorId(), channelDistributor);
				}
			}
		}

	}

	/**
	 * 处理sop 发货
	 * 
	 * @param waitDeliveryList
	 * @param tradeId
	 * @param orderId
	 * @param logisticsId
	 * @param outsid
	 * @param isCheck
	 * @param channelId
	 * @throws Exception
	 */
	private void syscSOPOrder(List<WaitDelivery> waitDeliveryList, String tradeId, String orderId, String logisticsId, String outsid, Boolean isCheck, String channelDistributorId, ChannelDistributor channelDistributor) throws Exception {
		OrderDetailInfo orderDetail = getOrderState(orderId, channelDistributor);
		if (orderDetail != null && orderDetail.getOrderInfo() != null) {
			if (orderDetail.getOrderInfo().getOrderState().equals("WAIT_SELLER_STOCK_OUT")) {
				String code = orderSOPOutstorage(orderId, tradeId, channelDistributor, logisticsId, outsid);
				if (code != null && !"".equals(code) && (code.equals("10010") || code.equals("11001"))) {
					String sopDeliveryCode;
					sopDeliveryCode = sopDelivery(orderId, outsid, logisticsId, tradeId, channelDistributor);
					if (sopDeliveryCode != null && !isCheck) {
						WaitDelivery waitDelivery = new WaitDelivery();
						waitDelivery.setTradeId(tradeId);
						waitDelivery.setOrderId(orderId);
						waitDelivery.setLogisticsId(logisticsId);
						waitDelivery.setOutsid(outsid);
						waitDelivery.setChannelDistributorId(channelDistributor.getId());
						waitDeliveryList.add(waitDelivery);
					}
				} else if (code != null && !"".equals(code) && code.equals("10010V2.0")) {
					// V2.0 API
					sopDelivery(orderId, outsid, logisticsId, tradeId, channelDistributor);
				} else {
					WaitDelivery waitDelivery = new WaitDelivery();
					waitDelivery.setTradeId(tradeId);
					waitDelivery.setOrderId(orderId);
					waitDelivery.setLogisticsId(logisticsId);
					waitDelivery.setOutsid(outsid);
					waitDelivery.setChannelDistributorId(channelDistributor.getId());
					waitDeliveryList.add(waitDelivery);
				}

			} else if (orderDetail.getOrderInfo().getOrderState().equals("WAIT_SELLER_DELIVERY")) {
				sopDelivery(orderId, outsid, logisticsId, tradeId, channelDistributor);
			} else if (orderDetail.getOrderInfo().getOrderState().equals("TRADE_CANCELED")) {
				tradeCanceled(tradeId);
			} else if (orderDetail.getOrderInfo().getOrderState().equals("WAIT_GOODS_RECEIVE_CONFIRM")) {
				tradeUpdate(tradeId, orderId);
			} else if (orderDetail.getOrderInfo().getOrderState().equals("FINISHED_L")) {
				tradeUpdate(tradeId, orderId);
			}
		}
	}

	/**
	 * 更改交易完成 确认收货的 订单状态
	 * 
	 * @param tradeId
	 *            订单主键
	 * @param orderId
	 *            订单编码
	 * @throws Exception
	 */
	private void tradeUpdate(String tradeId, String orderId) throws Exception {
		// String sql = "UPDATE trade set DEAL_STATE = 5,status = 2 WHERE
		// trade_id=" + tradeId;
		Order order = orderProcessService.findEntityById(Order.class, tradeId);
		order.setStatus("2");
		order.setDealState(5);
		orderProcessService.merge(order);

		// 处理发货单
		InvoiceList invoiceList = orderProcessService.findEntityByAttribute(InvoiceList.class, "order.id", order.getId());
		if (invoiceList != null) {
			// String sql2 = "UPDATE invoice_list set status = 1,confirm_time =
			// CURRENT_TIMESTAMP,archived_time = CURRENT_TIMESTAMP,is_archived =
			// 1 WHERE invoice_list.TRADE_ID=" + tradeId;
			invoiceList.setStatus("1");
			invoiceList.setConfirmTime(new Date());
			invoiceList.setArchivedTime(new Date());
			invoiceList.setIsArchived(1);
			orderProcessService.merge(invoiceList);
		}
		// 处理快递单
		// String sql3 = "update shipping set is_archived = 1,archived_time =
		// CURRENT_TIMESTAMP where shipping.TRADE_NO='" + orderId + "'";
		Shipping shipping = orderProcessService.findEntityByAttribute(Shipping.class, "order.id", order.getId());
		if (shipping != null) {
			shipping.setIsArchived(1);
			shipping.setArchivedTime(new Date());
			orderProcessService.merge(shipping);
		}
		// 插入订单操作日志
		// String logSql = "INSERT into trade_operation_log
		// (log_id,trade_id,staff_id,operate_time,operate_desc) VALUES (" +
		// VixUUID.getUUID() + "," + tradeId + ",1,CURRENT_TIMESTAMP,'发货成功!')";
		OrderOperationLog orderOperationLog = new OrderOperationLog();
		orderOperationLog.setOrder(order);
		orderOperationLog.setOperateTime(new Date());
		orderOperationLog.setOperateDesc("发货成功!");
		orderProcessService.merge(orderOperationLog);
	}

	/**
	 * 处理交易关闭订单
	 * 
	 * @param tradeId
	 * @throws Exception
	 */
	private void tradeCanceled(String tradeId) throws Exception {
		// String sql = "UPDATE trade set DEAL_STATE = 5,status = 7 WHERE
		// trade_id=" + tradeId;
		Order order = orderProcessService.findEntityById(Order.class, tradeId);
		order.setStatus("7");
		order.setDealState(5);
		orderProcessService.merge(order);

		// 处理发货单
		InvoiceList invoiceList = orderProcessService.findEntityByAttribute(InvoiceList.class, "order.id", order.getId());
		if (invoiceList != null) {
			// String sql2 = "UPDATE invoice_list set status = 2,confirm_time =
			// CURRENT_TIMESTAMP,archived_time = CURRENT_TIMESTAMP,is_archived =
			// 1 WHERE invoice_list.TRADE_ID=" + tradeId;
			invoiceList.setStatus("2");
			invoiceList.setConfirmTime(new Date());
			invoiceList.setArchivedTime(new Date());
			invoiceList.setIsArchived(1);
			orderProcessService.merge(invoiceList);
		}
	}

	/**
	 * @param orderId
	 * @param outsid
	 * @param logisticsId
	 * @param tradeId
	 * @param listId
	 * @return
	 * @throws Exception
	 */
	private String sopDelivery(String orderId, String outsid, String logisticsId, String tradeId, ChannelDistributor channelDistributor) throws Exception {
		TradeUpdateStateRequest tradeUpdateStateRequest = new TradeUpdateStateRequest();
		tradeUpdateStateRequest.setChannelId(channelDistributor.getId().toString());
		tradeUpdateStateRequest.setLogisticsId(logisticsId);
		tradeUpdateStateRequest.setOrderId(orderId);
		tradeUpdateStateRequest.setShippingOutSid(outsid);
		tradeUpdateStateRequest.setTenantId(null);
		TradeUpdateStateResponse tradeUpdateStateResponse = CloudShopsClientAdapter.getInstance().execute(tradeUpdateStateRequest, CloudShopsChannelGet.getChannel(channelDistributor));
		if (tradeUpdateStateResponse.isSuccess()) {
			// String sql = "UPDATE trade set DEAL_STATE = 5,status = 2 WHERE
			// trade_id=" + tradeId;
			Order order = orderProcessService.findEntityById(Order.class, tradeId);
			order.setStatus("2");
			order.setDealState(5);
			orderProcessService.merge(order);

			// 处理发货单
			InvoiceList invoiceList = orderProcessService.findEntityByAttribute(InvoiceList.class, "order.id", order.getId());
			if (invoiceList != null) {
				// String sql2 = "UPDATE invoice_list set status =
				// 1,confirm_time = CURRENT_TIMESTAMP,archived_time =
				// CURRENT_TIMESTAMP,is_archived = 1 WHERE
				// invoice_list.TRADE_ID=" + tradeId;
				invoiceList.setStatus("1");
				invoiceList.setConfirmTime(new Date());
				invoiceList.setArchivedTime(new Date());
				invoiceList.setIsArchived(1);
				orderProcessService.merge(invoiceList);
			}
			// 处理快递单
			// String sql3 = "update shipping set is_archived = 1,archived_time
			// = CURRENT_TIMESTAMP where shipping.TRADE_NO='" + orderId + "'";
			Shipping shipping = orderProcessService.findEntityByAttribute(Shipping.class, "order.id", order.getId());
			if (shipping != null) {
				shipping.setIsArchived(1);
				shipping.setArchivedTime(new Date());
				orderProcessService.merge(shipping);
			}

			// 插入订单操作日志
			// String logSql = "INSERT into trade_operation_log
			// (log_id,trade_id,staff_id,operate_time,operate_desc) VALUES (" +
			// VixUUID.getUUID() + "," + tradeId +
			// ",1,CURRENT_TIMESTAMP,'自动发货成功!')";
			OrderOperationLog orderOperationLog = new OrderOperationLog();
			orderOperationLog.setOrder(order);
			orderOperationLog.setOperateTime(new Date());
			orderOperationLog.setOperateDesc("自动发货成功!");
			orderProcessService.merge(orderOperationLog);
		} else {
			if (tradeUpdateStateResponse != null) {
				String msg = "------自动发货失败:错误编码为 " + tradeUpdateStateResponse.getErrorCode() + " 错误信息为 " + tradeUpdateStateResponse.getMsg() + "," + tradeUpdateStateResponse.getSubMsg();
				// String sql = "UPDATE trade set out_interacting_state =
				// 1,latest_interacting_error='" + msg +
				// "',latest_interacting_error_time = CURRENT_TIMESTAMP WHERE
				// trade_id=" + tradeId;
				Order order = orderProcessService.findEntityById(Order.class, tradeId);
				order.setOutInteractingState(1);
				order.setLatestInteractingError(msg);
				order.setLatestInteractingErrorTime(new Date());
				orderProcessService.merge(order);
				// String logSql = "INSERT into trade_operation_log
				// (log_id,trade_id,staff_id,operate_time,operate_desc) VALUES
				// (" + VixUUID.getUUID() + "," + tradeId +
				// ",1,CURRENT_TIMESTAMP,'" + msg + "')";
				OrderOperationLog orderOperationLog = new OrderOperationLog();
				orderOperationLog.setOrder(order);
				orderOperationLog.setOperateTime(new Date());
				orderOperationLog.setOperateDesc(msg);
				orderProcessService.merge(orderOperationLog);
			}
		}
		return tradeUpdateStateResponse.getErrorCode();
	}

	/**
	 * SOP订单出库操作 并把订单状态改为待发货状态
	 * 
	 * @param orderId
	 * @param tradeId
	 * @param channelId
	 * @param logisticsId
	 * @param outsid
	 * @return
	 * @throws Exception
	 */
	private String orderSOPOutstorage(String orderId, String tradeId, ChannelDistributor channelDistributor, String logisticsId, String outsid) throws Exception {
		SopOutStorageRequest sopOutStorageRequest = new SopOutStorageRequest();
		sopOutStorageRequest.setOrderId(orderId);
		sopOutStorageRequest.setTenantId(null);
		sopOutStorageRequest.setChannelId(channelDistributor.getId().toString());
		sopOutStorageRequest.setLogisticsId(logisticsId);
		sopOutStorageRequest.setShippingOutSid(outsid);
		SopOutStorageResponse sopOutStorageResponse = CloudShopsClientAdapter.getInstance().execute(sopOutStorageRequest, CloudShopsChannelGet.getChannel(channelDistributor));
		if (sopOutStorageResponse.isSuccess()) {
			// 更改订单状态为待发货状态
			// String sql = "UPDATE trade set status = 1 WHERE trade_id = "
			// +tradeId;
			Order order = orderProcessService.findEntityById(Order.class, tradeId);
			order.setStatus("1");
			orderProcessService.merge(order);
		}
		return sopOutStorageResponse.getErrorCode();
	}

	private OrderDetailInfo getOrderState(String orderId, ChannelDistributor channelDistributor) throws CloudShopsException {

		OrderGetRequest orderGetRequest = new OrderGetRequest();
		orderGetRequest.setChannelId(channelDistributor.getId().toString());
		orderGetRequest.setOrderId(orderId);
		orderGetRequest.setTenantId(null);
		OrderGetResponse orderGetResponse = CloudShopsClientAdapter.getInstance().execute(orderGetRequest, CloudShopsChannelGet.getChannel(channelDistributor));
		if (orderGetResponse.isSuccess()) {
			return orderGetResponse.getOrderDetailInfo();
		}
		return null;
	}

	/**
	 * 京东LBP订单发货
	 * 
	 * @param orderList
	 * @param tradeIdList
	 * @throws Exception
	 */
	private void dealLBP(List<Order> orderList, ChannelDistributor channelDistributor) throws Exception {
		if (orderList != null && orderList.size() > 0) {
			for (Order order : orderList) {
				String orderId = order.getOuterId();
				String num = order.getPackageNum().toString();
				String channelId = channelDistributor.getId();
				String logisticsId = order.getShippingCompanyCode();
				String shippingOutSid = order.getShippingNo();
				LBPOutStorageRequest LBPOutStorageRequest = new LBPOutStorageRequest();
				LBPOutStorageRequest.setOrderId(orderId);
				LBPOutStorageRequest.setChannelId(channelId.toString());
				LBPOutStorageRequest.setLogisticsId(logisticsId);
				LBPOutStorageRequest.setShippingOutSid(shippingOutSid);
				LBPOutStorageRequest.setNum(num);
				LBPOutStorageRequest.setTenantId(null);
				LBPOutStorageResponse LBPOutStorageResponse = CloudShopsClientAdapter.getInstance().execute(LBPOutStorageRequest, CloudShopsChannelGet.getChannel(channelDistributor));
				if (LBPOutStorageResponse.isSuccess()) {
					// String sql = "UPDATE trade set status = 2,DEAL_STATE = 5
					// WHERE trade_id = '" + orderId + "'";
					order.setStatus("2");// 订单状态置为已发货
					order.setDealState(5);// 处理状态置为已发货
					orderProcessService.merge(order);

					// 处理发货单
					InvoiceList invoiceList = orderProcessService.findEntityByAttribute(InvoiceList.class, "order.id", order.getId());
					if (invoiceList != null) {
						// String sql2 ="UPDATE invoice_list set status =
						// 1,confirm_time = CURRENT_TIMESTAMP,archived_time =
						// CURRENT_TIMESTAMP,is_archived = 1 WHERE
						// invoice_list.TRADE_ID ="+ orderId;
						invoiceList.setStatus("1");
						invoiceList.setConfirmTime(new Date());
						invoiceList.setArchivedTime(new Date());
						invoiceList.setIsArchived(1);
						orderProcessService.merge(invoiceList);
					}

					// 插入订单操作日志
					// String logSql ="INSERT into trade_operation_log
					// (log_id,trade_id,staff_id,operate_time,operate_desc)
					// VALUES ("+ VixUUID.getUUID() + "," + orderId +
					// ",1,CURRENT_TIMESTAMP,'自动出库成功!')";
					OrderOperationLog orderOperationLog = new OrderOperationLog();
					orderOperationLog.setOrder(order);
					orderOperationLog.setOperateTime(new Date());
					orderOperationLog.setOperateDesc("自动出库成功!");
					orderProcessService.merge(orderOperationLog);

				} else {
					String msg = "------自动发货失败:错误编码为 " + LBPOutStorageResponse.getErrorCode() + " 错误信息为 " + LBPOutStorageResponse.getSubCode() + "," + LBPOutStorageResponse.getSubMsg();
					// String sql = "UPDATE trade set out_interacting_state =
					// 1,latest_interacting_error='" + msg
					// +"',latest_interacting_error_time = CURRENT_TIMESTAMP
					// WHERE trade_id=" + tradeId;
					order.setOutInteractingState(1);
					order.setLatestInteractingError(msg);
					order.setLatestInteractingErrorTime(new Date());
					orderProcessService.merge(order);
					// String logSql = "INSERT into trade_operation_log
					// (log_id,trade_id,staff_id,operate_time,operate_desc)
					// VALUES (" + VixUUID.getUUID() + "," + orderId +
					// ",1,CURRENT_TIMESTAMP,'" + msg + "')";
					OrderOperationLog orderOperationLog = new OrderOperationLog();
					orderOperationLog.setOrder(order);
					orderOperationLog.setOperateTime(new Date());
					orderOperationLog.setOperateDesc(msg);
					orderProcessService.merge(orderOperationLog);
				}
			}
		}
	}

	private void updateOrdersState(Order order, ChannelDistributor channelDistributor) {
		if (order != null) {
			try {
				String tradeId = order.getId();
				String orderId = order.getOuterId();
				String logisticsId = order.getShippingCompanyCode();
				String outsid = order.getShippingNo();
				String outerId = order.getOuterId();
				if (outsid == null || "".equals(outsid) || logisticsId == null || "".equals(logisticsId)) {
					return;
				}
				offLineSend(tradeId, orderId, logisticsId, outsid, channelDistributor, outerId);
			} catch (Exception e) {
				e.fillInStackTrace();
			}
		}
	}

	private void offLineSend(String tradeId, String orderId, String logisticsId, String outsid, ChannelDistributor channelDistributor, String outerId) throws Exception {
		TradeUpdateStateRequest tradeUpdateStateRequest = new TradeUpdateStateRequest();
		tradeUpdateStateRequest.setChannelId(channelDistributor.getId().toString());
		tradeUpdateStateRequest.setOrderId(orderId);
		tradeUpdateStateRequest.setOuterId(outerId);
		tradeUpdateStateRequest.setShippingOutSid(outsid);
		tradeUpdateStateRequest.setLogisticsId(logisticsId);
		tradeUpdateStateRequest.setTenantId("");
		TradeUpdateStateResponse tradeUpdateStateResponse = CloudShopsClientAdapter.getInstance().execute(tradeUpdateStateRequest, CloudShopsChannelGet.getChannel(channelDistributor));
		if (tradeUpdateStateResponse != null && tradeUpdateStateResponse.isSuccess()) {
			// String sql ="UPDATE trade set DEAL_STATE = 5,status = 2 WHERE
			// trade_id=" +tradeId;
			// 更改订单状态
			Order order = orderProcessService.findEntityById(Order.class, tradeId);
			order.setStatus("2");
			order.setDealState(5);
			orderProcessService.merge(order);

			// 处理发货单
			// String sql2 = "UPDATE invoice_list set status = 1,confirm_time =
			// CURRENT_TIMESTAMP,archived_time = CURRENT_TIMESTAMP,is_archived =
			// 1 WHERE invoice_list.TRADE_ID=" + tradeId;
			InvoiceList invoiceList = orderProcessService.findEntityByAttribute(InvoiceList.class, "order.id", order.getId());
			if (invoiceList != null) {
				invoiceList.setStatus("1");
				invoiceList.setConfirmTime(new Date());
				invoiceList.setArchivedTime(new Date());
				invoiceList.setIsArchived(1);
				orderProcessService.merge(invoiceList);
			}

			// String sql3 = "update shipping set is_archived = 1,archived_time
			// = CURRENT_TIMESTAMP where shipping.TRADE_NO='" + orderId + "'";
			Shipping shipping = orderProcessService.findEntityByAttribute(Shipping.class, "order.id", order.getId());
			if (shipping != null) {
				shipping.setIsArchived(1);
				shipping.setArchivedTime(new Date());
				orderProcessService.merge(shipping);
			}

			// String logSql = "INSERT into trade_operation_log
			// (log_id,trade_id,staff_id,operate_time,operate_desc) VALUES (" +
			// VixUUID.getUUID() + "," + tradeId +
			// ",1,CURRENT_TIMESTAMP,'自动发货成功!')";
			OrderOperationLog orderOperationLog = new OrderOperationLog();
			orderOperationLog.setOrder(order);
			orderOperationLog.setOperateTime(new Date());
			orderOperationLog.setOperateDesc("自动发货成功!");
			orderProcessService.merge(orderOperationLog);

		} else {
			String msg = null;
			if (tradeUpdateStateResponse != null) {
				msg = "------自动发货失败:错误编码为 " + tradeUpdateStateResponse.getErrorCode() + " 错误信息为 " + tradeUpdateStateResponse.getMsg() + "," + tradeUpdateStateResponse.getSubMsg();
				// String sql = "UPDATE trade set out_interacting_state =
				// 1,latest_interacting_error='" + msg +
				// "',latest_interacting_error_time = CURRENT_TIMESTAMP WHERE
				// trade_id=" + tradeId;
				Order order = orderProcessService.findEntityById(Order.class, tradeId);
				order.setOutInteractingState(1);
				order.setLatestInteractingError(msg);
				order.setLatestInteractingErrorTime(new Date());
				orderProcessService.merge(order);
				// String logSql = "INSERT into trade_operation_log
				// (log_id,trade_id,staff_id,operate_time,operate_desc) VALUES
				// (" + VixUUID.getUUID() + "," + tradeId +
				// ",1,CURRENT_TIMESTAMP,'" + msg + "')";
				OrderOperationLog orderOperationLog = new OrderOperationLog();
				orderOperationLog.setOrder(order);
				orderOperationLog.setOperateTime(new Date());
				orderOperationLog.setOperateDesc(msg);
				orderProcessService.merge(orderOperationLog);
			}
		}
	}

	/**
	 * 获取不是京东的订单
	 * 
	 * String sql = "SELECT DISTINCT trade.*,channel.* FROM
	 * invoice_list,invoice_list_trade,trade,channel WHERE invoice_list.LIST_ID
	 * = invoice_list_trade.LIST_ID and
	 * invoice_list_trade.TRADE_NO=trade.TRADE_NO and
	 * channel.CHANNEL_ID=trade.CHANNEL_ID and channel.CHANNEL_TYPE_ID !=3 and
	 * trade.DEAL_STATE not in (5,7,8) and trade.SHIPPING_OUT_SID is not null
	 * and trade.SHIPPING_COMPANY_CODE is not null and invoice_list.TRADE_ID in
	 * (" + orders + ")";
	 * 
	 * @param orders
	 * @return
	 */
	private List<Order> getAllOrders(String orders) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, orders);
		params.put("shippingNo," + SearchCondition.NOEQUAL, null);
		params.put("shippingCompanyCode," + SearchCondition.NOEQUAL, null);
		params.put("channelDistributor.channelTypeId," + SearchCondition.NOEQUAL, 3L);
		try {
			List<Order> orderList = orderProcessService.findAllByConditions(Order.class, params);
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * String sql =
	 * "SELECT trade.*,channel.* from trade,channel where trade.CHANNEL_ID =channel.CHANNEL_ID and trade.DEAL_STATE not in (5,7,8) and trade.SHIPPING_COMPANY_CODE is not null and TRADE.SHIPPING_OUT_SID is not NULL and  TRADE_ID in ("
	 * + orders + ")";
	 * 
	 * @param orders
	 * @return
	 */
	private List<Order> getOrders(String orders) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, orders);
		params.put("shippingNo," + SearchCondition.NOEQUAL, null);
		params.put("shippingCompanyCode," + SearchCondition.NOEQUAL, null);
		params.put("channelDistributor.channelTypeId," + SearchCondition.NOEQUAL, 3L);
		try {
			List<Order> orderList = orderProcessService.findAllByConditions(Order.class, params);
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取所有待发货订单
	 * 
	 * String sql =
	 * "select trade.*,channel.* from trade,channel where trade.DEAL_STATE not in (5,7,8) and trade.SHIPPING_OUT_SID is not null and trade.SHIPPING_COMPANY_CODE is not null and trade.CHANNEL_ID =channel.CHANNEL_ID and trade.CHANNEL_ID ="
	 * + channelId;
	 * 
	 * @param channelId
	 * @return
	 */
	private List<Order> getAllOrderByChannelId(String channelId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("channelDistributor.id," + SearchCondition.EQUAL, channelId);
		params.put("shippingNo," + SearchCondition.NOEQUAL, null);
		params.put("shippingCompanyCode," + SearchCondition.NOEQUAL, null);
		try {
			List<Order> orderList = orderProcessService.findAllByConditions(Order.class, params);
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @param orders
	 * @param distributionCenterCode
	 * @return
	 */
	/*
	 * private List<Order> getAllJingDongOrdersByDistribution(String orders,
	 * String distributionCenterCode) { String sql =
	 * "SELECT DISTINCT trade.*,channel.* FROM invoice_list,invoice_list_trade,trade,channel  WHERE invoice_list.LIST_ID = invoice_list_trade.LIST_ID and invoice_list_trade.TRADE_NO=trade.TRADE_NO and channel.CHANNEL_ID=trade.CHANNEL_ID and channel.CHANNEL_TYPE_ID=3 and trade.distribution_center_code='"
	 * + distributionCenterCode + "' and invoice_list.TRADE_ID in (" + orders +
	 * ")"; return null; }
	 */

	/**
	 * @param orders
	 *            String sql =
	 *            "SELECT DISTINCT trade.*,channel.* FROM invoice_list,invoice_list_trade,trade,channel  WHERE invoice_list.LIST_ID = invoice_list_trade.LIST_ID and invoice_list_trade.TRADE_NO=trade.TRADE_NO and channel.CHANNEL_ID=trade.CHANNEL_ID and channel.CHANNEL_TYPE_ID=3 and invoice_list.TRADE_ID in ("
	 *            + orders + ")";
	 * @return
	 */
	private List<Order> getJingDongOrders(String orders) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, orders);
		params.put("channelDistributor.channelTypeId," + SearchCondition.EQUAL, 3l);
		try {
			List<Order> orderList = orderProcessService.findAllByConditions(Order.class, params);
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * String sql =
	 * "select trade.*,channel.* from trade,channel where trade.CHANNEL_ID=channel.CHANNEL_ID and channel.CHANNEL_TYPE_ID=3 and trade.TRADE_ID in ("
	 * + orders + ")";
	 * 
	 * @param orders
	 * @return
	 */
	private List<Order> getOffJingdongRows(String orders) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, orders);
		params.put("channelDistributor.channelTypeId," + SearchCondition.EQUAL, 3);
		try {
			List<Order> orderList = orderProcessService.findAllByConditions(Order.class, params);
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
