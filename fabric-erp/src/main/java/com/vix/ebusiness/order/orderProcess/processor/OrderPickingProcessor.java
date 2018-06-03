package com.vix.ebusiness.order.orderProcess.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.InvoiceList;
import com.vix.ebusiness.entity.InvoiceListDetail;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.entity.PickingList;
import com.vix.ebusiness.entity.PickingListDetail;
import com.vix.ebusiness.entity.Shipping;
import com.vix.ebusiness.option.entity.OnLineStoreParameters;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.standingbook.entity.GroupInventoryCurrentStock;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;

import net.sf.json.JSONObject;

/**
 * 订单分拣处理 com.vix.ebusiness.order.orderProcess.processor.OrderPickingProcessor
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
@Controller("orderPickingProcessor")
public class OrderPickingProcessor {
	// 拣货数量没求和
	private Long packageTotal = 0l;
	@Autowired
	private IOrderProcessService orderProcessService;
	/**
	 * 初始化基础数据
	 */
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	/**
	 * 
	 * @param orderList
	 * @throws Exception
	 */
	public void preproccessOrderData(List<Order> orderList) throws Exception {
		// 创建批次
		OrderBatch orderBatch = new OrderBatch();
		orderBatch.setCode(VixUUID.createCode(12));
		orderBatch.setStatus("1");
		initEntityBaseController.initEntityBaseAttribute(orderBatch);
		orderBatch = orderProcessService.merge(orderBatch);
		// 创建拣货单
		PickingList pickingList = new PickingList();
		pickingList.setOrderBatch(orderBatch);
		initEntityBaseController.initEntityBaseAttribute(pickingList);
		pickingList = orderProcessService.merge(pickingList);
		int printIndex = 1;

		StringBuffer orderNoList = new StringBuffer();
		if (orderList != null && orderList.size() > 0) {
			// 处理订单
			for (Order order : orderList) {
				if (order != null) {
					dealOrder(orderBatch, order, order.getInvWarehouse(), printIndex, pickingList);
					orderNoList.append(order.getOuterId()).append(" ");
				}
				printIndex++;
			}

			if (orderNoList.toString().endsWith(",")) {
				pickingList.setOrderNoList(orderNoList.toString().substring(0, orderNoList.toString().length() - 1));
			} else {
				pickingList.setOrderNoList(orderNoList.toString());
			}
			pickingList.setPackageTotal(packageTotal);
			pickingList = orderProcessService.merge(pickingList);
		}
	}

	/**
	 * 
	 * @param channel
	 * @param logistics
	 * @param order
	 * @param invWarehouse
	 * @throws Exception
	 */
	private void dealOrder(OrderBatch orderBatch, Order order, InvWarehouse invWarehouse, int printIndex, PickingList pickingList) throws Exception {

		ChannelDistributor channel = order.getChannelDistributor();
		Double total = Double.parseDouble(order.getTotalFee());
		Double payment = Double.parseDouble(order.getPayment());
		// Long amount = order.getNum();
		Long deliveryAmount = 0l;
		Double discountFee = null;
		if (order.getDiscountFee() != null) {
			discountFee = Double.parseDouble(order.getDiscountFee());
		}
		Long channelTypeId = null;
		String props = null;
		if (channel != null) {
			channelTypeId = channel.getChannelTypeId();
			if (channelTypeId != null && channelTypeId == 3) {
				props = channel.getChannelProps();
			}
		}
		List<OrderDetail> orderDetailList = getOrderDetailList(order.getId());
		InvoiceList invoiceList = dealInvoiceList(orderBatch, order, total, discountFee, payment, printIndex);
		for (OrderDetail orderDetail : orderDetailList) {
			if (orderDetail != null) {
				// 查询是否是虚拟商品
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("itemcode," + SearchCondition.EQUAL, orderDetail.getBn());
				List<GroupInventoryCurrentStock> groupInventoryCurrentStockList = orderProcessService.findAllByConditions(GroupInventoryCurrentStock.class, p);
				if (groupInventoryCurrentStockList != null && groupInventoryCurrentStockList.size() > 0) {
					for (GroupInventoryCurrentStock groupInventoryCurrentStock : groupInventoryCurrentStockList) {
						if (groupInventoryCurrentStock != null) {
							if (groupInventoryCurrentStock.getSubInventoryCurrentStocks() != null && groupInventoryCurrentStock.getSubInventoryCurrentStocks().size() > 0) {
								for (InventoryCurrentStock inventoryCurrentStock : groupInventoryCurrentStock.getSubInventoryCurrentStocks()) {
									packageTotal += inventoryCurrentStock.getGroupAmount();
									deliveryAmount += inventoryCurrentStock.getGroupAmount();
									dealInvoiceListDetail(invWarehouse, invoiceList, orderDetail);
									Map<String, Object> params = new HashMap<String, Object>();
									params.put("pickingList.id," + SearchCondition.EQUAL, pickingList.getId());
									params.put("goodsCode," + SearchCondition.EQUAL, inventoryCurrentStock.getItemcode());
									PickingListDetail pickingListDetail = orderProcessService.findObjectFirstByHqlConditions(PickingListDetail.class, params);
									StringBuffer orderAmount = new StringBuffer();
									orderAmount.append(printIndex).append("(").append(inventoryCurrentStock.getGroupAmount()).append(")");
									List<String> orderAmountList = new ArrayList<String>();
									orderAmountList.add(orderAmount.toString());
									if (pickingListDetail != null) {
										orderAmountList.add(pickingListDetail.getOrderAmount());
										pickingListDetail.setPickAmount(pickingListDetail.getPickAmount() + inventoryCurrentStock.getGroupAmount());
										pickingListDetail.setOrderAmount(orderAmountList.toString().replace("[", "").replace("]", ""));
										pickingListDetail = orderProcessService.merge(pickingListDetail);
									} else {
										pickingListDetail = new PickingListDetail();
										pickingListDetail.setGoodsCode(inventoryCurrentStock.getItemcode());
										pickingListDetail.setGoodsName(inventoryCurrentStock.getItemname());
										if (inventoryCurrentStock != null) {
											pickingListDetail.setShelfCode(inventoryCurrentStock.getInvShelf().getCode());
										}
										pickingListDetail.setPickAmount(inventoryCurrentStock.getGroupAmount());
										pickingListDetail.setOrderAmount(orderAmount.toString());
										pickingListDetail.setPickingList(pickingList);
										initEntityBaseController.initEntityBaseAttribute(pickingListDetail);
										pickingListDetail = orderProcessService.merge(pickingListDetail);
									}
								}
							}

						} else {
						}
					}
				} else {
					if (orderDetail.getNum() != null) {
						// packageTotal += orderDetail.getNum();
						// deliveryAmount += orderDetail.getNum();
					}
					dealInvoiceListDetail(invWarehouse, invoiceList, orderDetail);
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("pickingList.id," + SearchCondition.EQUAL, pickingList.getId());
					params.put("goodsCode," + SearchCondition.EQUAL, orderDetail.getBn());
					PickingListDetail pickingListDetail = orderProcessService.findObjectFirstByHqlConditions(PickingListDetail.class, params);
					StringBuffer orderAmount = new StringBuffer();
					orderAmount.append(printIndex).append("(").append(orderDetail.getNum()).append(")");
					List<String> orderAmountList = new ArrayList<String>();
					orderAmountList.add(orderAmount.toString());
					if (pickingListDetail != null) {
						orderAmountList.add(pickingListDetail.getOrderAmount());
						// pickingListDetail.setPickAmount(pickingListDetail.getPickAmount()
						// + orderDetail.getNum());
						pickingListDetail.setOrderAmount(orderAmountList.toString().replace("[", "").replace("]", ""));
						pickingListDetail = orderProcessService.merge(pickingListDetail);
					} else {
						pickingListDetail = new PickingListDetail();
						pickingListDetail.setGoodsCode(orderDetail.getBn());
						pickingListDetail.setGoodsName(orderDetail.getTitle());
						// InventoryCurrentStock inventoryCurrentStock =
						// orderProcessService.findEntityByAttribute(InventoryCurrentStock.class,
						// "skuCode", orderDetail.getBn());
						/*
						 * if (inventoryCurrentStock != null) {
						 * pickingListDetail.setShelfCode(inventoryCurrentStock.
						 * getInvShelf().getCode()); }
						 */
						// pickingListDetail.setPickAmount(orderDetail.getNum());
						pickingListDetail.setOrderAmount(orderAmount.toString());
						pickingListDetail.setPickingList(pickingList);
						initEntityBaseController.initEntityBaseAttribute(pickingListDetail);
						pickingListDetail = orderProcessService.merge(pickingListDetail);
					}

				}

			}

		}
		if (channelTypeId != null && channelTypeId == 3) {
			if (props != null && !"".equals(props)) {
				JSONObject json = JSONObject.fromObject(props);
				if (json.getInt("mallType") == 6) {
					// dealShipping(orderBatch, order, channel, itemTitle,
					// amount, printIndex);
				}
			}
		} else {
			// dealShipping(orderBatch, order, channel, itemTitle, amount,
			// printIndex);
		}
		// StockRecords stockRecords = dealDeliveryList(orderBatch,
		// invWarehouse, order, deliveryAmount);
		for (OrderDetail orderDetail : orderDetailList) {
			if (orderDetail != null) {
				/*
				 * InventoryCurrentStock inventoryCurrentStock =
				 * orderProcessService.findEntityByAttribute(
				 * InventoryCurrentStock.class, "skuCode", orderDetail.getBn());
				 * if (inventoryCurrentStock != null) {
				 * dealDeliveryListDetail(stockRecords, orderDetail,
				 * orderDetail.getBn(), orderDetail.getNum(),
				 * inventoryCurrentStock.getInvShelf()); }
				 */
			}
		}
		order.setDealState(2);
		order.setOrderBatch(orderBatch);
		order = orderProcessService.merge(order);
	}

	// 生成出库单
	public StockRecords dealDeliveryList(OrderBatch orderBatch, InvWarehouse invWarehouse, Order order, Long deliveryAmount) throws Exception {
		StockRecords stockRecords = new StockRecords();
		stockRecords.setTradeNo(order.getOuterId());
		stockRecords.setDeliveryType(1);
		stockRecords.setAmount(deliveryAmount);
		stockRecords.setInvWarehouse(invWarehouse);
		stockRecords.setPickingNo(null);
		stockRecords.setCorpCode(order.getLogistics().getCode());
		stockRecords.setCreateTime(new Date());
		stockRecords.setMemo(order.getBuyerMemo());
		stockRecords.setOrderBatch(orderBatch);
		initEntityBaseController.initEntityBaseAttribute(stockRecords);
		stockRecords = orderProcessService.merge(stockRecords);
		return stockRecords;
	}

	// 出库单明细
	public void dealDeliveryListDetail(StockRecords stockRecords, OrderDetail orderDetail, String bn, Long amount, InvShelf invShelf) throws Exception {
		// 查询是否是虚拟商品
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("itemcode," + SearchCondition.EQUAL, orderDetail.getBn());
		List<GroupInventoryCurrentStock> groupInventoryCurrentStockList = orderProcessService.findAllByConditions(GroupInventoryCurrentStock.class, p);

		if (groupInventoryCurrentStockList != null && groupInventoryCurrentStockList.size() > 0) {
			for (GroupInventoryCurrentStock groupInventoryCurrentStock : groupInventoryCurrentStockList) {
				if (groupInventoryCurrentStock != null) {
					if (groupInventoryCurrentStock.getSubInventoryCurrentStocks() != null && groupInventoryCurrentStock.getSubInventoryCurrentStocks().size() > 0) {
						for (InventoryCurrentStock inventoryCurrentStock : groupInventoryCurrentStock.getSubInventoryCurrentStocks()) {
							if (inventoryCurrentStock != null) {
								StockRecordLines stockRecordLines = new StockRecordLines();
								if (inventoryCurrentStock.getPrice() != null) {
									stockRecordLines.setPrice(inventoryCurrentStock.getPrice());
								}
								stockRecordLines.setOrderDetail(orderDetail);
								stockRecordLines.setBn(inventoryCurrentStock.getItemcode());
								if (inventoryCurrentStock.getGroupAmount() != null) {
									stockRecordLines.setAmount(inventoryCurrentStock.getGroupAmount());
								}
								stockRecordLines.setProductName(inventoryCurrentStock.getItemname());
								stockRecordLines.setProductSpec(orderDetail.getSkuPropertiesName());
								stockRecordLines.setInvShelf(invShelf);
								stockRecordLines.setStockRecords(stockRecords);
								initEntityBaseController.initEntityBaseAttribute(stockRecordLines);
								stockRecordLines = orderProcessService.merge(stockRecordLines);
							}
						}
					}
				}
			}
		} else {
			StockRecordLines stockRecordLines = new StockRecordLines();
			if (orderDetail != null) {
				if (orderDetail.getPrice() != null) {
					stockRecordLines.setPrice(orderDetail.getPrice());
				}
				stockRecordLines.setOrderDetail(orderDetail);
				stockRecordLines.setBn(bn);
				stockRecordLines.setAmount(amount);
				stockRecordLines.setProductName(orderDetail.getTitle());
				stockRecordLines.setProductSpec(orderDetail.getSkuPropertiesName());
				stockRecordLines.setInvShelf(invShelf);
				stockRecordLines.setStockRecords(stockRecords);
				initEntityBaseController.initEntityBaseAttribute(stockRecordLines);
				stockRecordLines = orderProcessService.merge(stockRecordLines);
			}
		}

	}

	// 生成发货单
	private InvoiceList dealInvoiceList(OrderBatch orderBatch, Order order, Double total, Double discountFee, Double payment, int printIndex) throws Exception {
		InvoiceList invoiceList = new InvoiceList();
		invoiceList.setDealTime(new Date());
		invoiceList.setCode(VixUUID.createCode(12));
		invoiceList.setInvoiceTime(new Date());
		invoiceList.setIsPrint(0);
		invoiceList.setDiscountFee(discountFee);
		if (order.getPostFee() != null) {
			invoiceList.setPostageFee(Double.parseDouble(order.getPostFee()));
		}
		invoiceList.setOrder(order);
		invoiceList.setOrderBatch(orderBatch);
		invoiceList.setTotal(total);
		invoiceList.setPayment(payment);
		invoiceList.setPrintIndex(printIndex);
		invoiceList.setOrderBatch(orderBatch);

		initEntityBaseController.initEntityBaseAttribute(invoiceList);
		OnLineStoreParameters onLineStoreParameters = orderProcessService.findEntityByAttribute(OnLineStoreParameters.class, "tenantId", orderBatch.getTenantId());
		if (onLineStoreParameters != null) {
			invoiceList.setGreetings(onLineStoreParameters.getGreetings());
			invoiceList.setFromCompany(onLineStoreParameters.getFromCompany());
		}
		invoiceList = orderProcessService.merge(invoiceList);
		return invoiceList;
	}

	/**
	 * 
	 * @param invWarehouse
	 * @param invoiceList
	 * @param orderDetail
	 * @throws Exception
	 */
	private void dealInvoiceListDetail(InvWarehouse invWarehouse, InvoiceList invoiceList, OrderDetail orderDetail) throws Exception {
		// 查询是否是虚拟商品
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("itemcode," + SearchCondition.EQUAL, orderDetail.getBn());
		List<GroupInventoryCurrentStock> groupInventoryCurrentStockList = orderProcessService.findAllByConditions(GroupInventoryCurrentStock.class, p);
		if (groupInventoryCurrentStockList != null && groupInventoryCurrentStockList.size() > 0) {
			for (GroupInventoryCurrentStock groupInventoryCurrentStock : groupInventoryCurrentStockList) {
				if (groupInventoryCurrentStock != null) {
					if (groupInventoryCurrentStock.getSubInventoryCurrentStocks() != null && groupInventoryCurrentStock.getSubInventoryCurrentStocks().size() > 0) {
						for (InventoryCurrentStock inventoryCurrentStock : groupInventoryCurrentStock.getSubInventoryCurrentStocks()) {
							if (inventoryCurrentStock != null) {
								InvoiceListDetail invoiceListDetail = new InvoiceListDetail();
								if (inventoryCurrentStock.getGroupAmount() != null && inventoryCurrentStock.getPrice() != null) {
									Double price = inventoryCurrentStock.getPrice();
									invoiceListDetail.setPrice(price);
									Long num = inventoryCurrentStock.getGroupAmount();
									invoiceListDetail.setAmount(num);
									invoiceListDetail.setTotalFee(price * num);
								}
								invoiceListDetail.setInvWarehouse(invWarehouse);
								invoiceListDetail.setGoodsName(inventoryCurrentStock.getItemname());
								invoiceListDetail.setOrderDetail(orderDetail);
								invoiceListDetail.setInvoiceList(invoiceList);
								initEntityBaseController.initEntityBaseAttribute(invoiceListDetail);
								invoiceListDetail = orderProcessService.merge(invoiceListDetail);
							}
						}
					}
				}
			}
		} else {
			InvoiceListDetail invoiceListDetail = new InvoiceListDetail();
			if (orderDetail != null) {
				if (orderDetail.getPrice() != null && orderDetail.getNum() != null) {
					Double price = orderDetail.getPrice();
					// Long num = orderDetail.getNum();
					invoiceListDetail.setPrice(price);
					// invoiceListDetail.setAmount(num);
					// invoiceListDetail.setTotalFee(price * num);
				}
				invoiceListDetail.setInvWarehouse(invWarehouse);
				invoiceListDetail.setGoodsName(orderDetail.getTitle());
				invoiceListDetail.setOrderDetail(orderDetail);
				invoiceListDetail.setInvoiceList(invoiceList);
				initEntityBaseController.initEntityBaseAttribute(invoiceListDetail);
				invoiceListDetail = orderProcessService.merge(invoiceListDetail);

			}
		}

	}

	private List<OrderDetail> getOrderDetailList(String orderId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order.id," + SearchCondition.EQUAL, orderId);
		return orderProcessService.findAllByConditions(OrderDetail.class, params);
	}

	// 物流单
	public void dealShipping(OrderBatch orderBatch, Order order, ChannelDistributor channelDistributor, String itemTitle, Long amount, int printIndex) throws Exception {
		Shipping shipping = new Shipping();
		shipping.setLogisticCode(order.getLogistics().getCode());
		shipping.setLogisticName(order.getLogistics().getName());
		shipping.setDealTime(new Date());
		shipping.setBuyerNick(order.getBuyerNick());
		shipping.setCreateTime(new Date());
		shipping.setUpdateTime(new Date());
		shipping.setMemo(order.getBuyerMemo());
		shipping.setCode(order.getOuterId());
		shipping.setSellerConfirm(0);
		shipping.setSellerNick(order.getSellerNick());
		shipping.setStatus("0");
		shipping.setIsPrint(0);// 未打印
		shipping.setTradeNo(order.getOuterId());
		shipping.setType(order.getShippingType());
		shipping.setPrintIndex(printIndex);
		shipping.setItemTitle(itemTitle);
		shipping.setAmount(amount);
		shipping.setInvoiceName(order.getInvoiceName());
		shipping.setBuyerMemo(order.getBuyerMemo());
		shipping.setReceiverZip(order.getReceiverZip());
		shipping.setReceiverState(order.getReceiverState());
		shipping.setReceiverAddress(order.getReceiverAddress());
		shipping.setReceiverCity(order.getReceiverCity());
		shipping.setReceiverDistrict(order.getReceiverDistrict());
		shipping.setReceiverMobile(order.getReceiverMobile());
		shipping.setReceiverName(order.getReceiverName());
		shipping.setReceiverPhone(order.getReceiverPhone());
		// 建立关系
		shipping.setChannelDistributor(channelDistributor);
		shipping.setOrderBatch(orderBatch);
		shipping.setOrder(order);
		initEntityBaseController.initEntityBaseAttribute(shipping);

		OnLineStoreParameters onLineStoreParameters = orderProcessService.findEntityByAttribute(OnLineStoreParameters.class, "tenantId", channelDistributor.getTenantId());
		if (onLineStoreParameters != null) {
			shipping.setFromCompany(onLineStoreParameters.getFromCompany());
			shipping.setContactPerson(onLineStoreParameters.getContactPerson());
			shipping.setFromAddress(onLineStoreParameters.getFromAddress());
			shipping.setFromPhone(onLineStoreParameters.getFromPhone());

		}
		shipping = orderProcessService.merge(shipping);
	}
}
