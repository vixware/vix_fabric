package com.vix.ebusiness.order.orderProcess.processor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ec.api.adapter.CloudShopsClientAdapter;
import com.ec.api.trade.domain.Customer;
import com.ec.api.trade.domain.Order;
import com.ec.api.trade.domain.OrderPrintData;
import com.ec.api.trade.domain.Trade;
import com.ec.api.trade.domain.TradePromotionDetail;
import com.ec.api.trade.request.TradesSoldGetRequest;
import com.ec.api.trade.response.TradesSoldGetResponse;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.vixlog.IOperateLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.ebusiness.entity.OrderAndGoods;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.ebusiness.option.entity.OnLineStoreParameters;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.ebusiness.util.channel.CloudShopsChannelGet;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单下载处理
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-21
 */
@Controller("orderDownloadProcessor")
public class OrderDownloadProcessor {
	@Autowired
	private IOrderProcessService orderProcessService;
	@Autowired
	public IOperateLog vixOperateLog;
	@Autowired
	private StandingBookHqlProvider standingBookHqlProvider;

	public void process(String channelId) throws Exception {
		// 获取所有订单
		TradesSoldGetRequest tradesSoldGetRequest = new TradesSoldGetRequest();
		tradesSoldGetRequest.setPage(1L);
		tradesSoldGetRequest.setPageSize(100L);
		tradesSoldGetRequest.setChannelId(String.valueOf(channelId));
		getOrdersNextPage(tradesSoldGetRequest, channelId);
	}

	private void dealTrades(List<Trade> trades, String channelId) throws Exception {

		if (trades != null && trades.size() > 0) {
			for (Trade trade : trades) {
				// 处理订单
				if (trade != null && trade.getTradeNo() != null && !"".equals(trade.getTradeNo()) && !"null".equals(trade.getTradeNo())) {
					dealOrders(trade, channelId);
				}
			}
		}
	}

	private void getOrdersNextPage(TradesSoldGetRequest tradesSoldGetRequest, String channelId) throws Exception {
		ChannelDistributor channel = orderProcessService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "id", channelId);
		TradesSoldGetResponse tradesSoldGetResponse = CloudShopsClientAdapter.getInstance().execute(tradesSoldGetRequest, CloudShopsChannelGet.getChannel(channel));
		if (tradesSoldGetResponse != null) {
			if (tradesSoldGetResponse.isSuccess()) {
				dealTrades(tradesSoldGetResponse.getTrades(), channelId);
				if (tradesSoldGetResponse.isHasNext()) {
					tradesSoldGetRequest.setPage(tradesSoldGetRequest.getPage() + 1L);
					getOrdersNextPage(tradesSoldGetRequest, channelId);
				}
			} else {
				// 输出异常信息
				System.out.println(tradesSoldGetResponse.getBody());
			}
		}
	}

	private com.vix.ebusiness.entity.Order dealOrders(Trade trade, String channelId) throws Exception {
		/* 获取渠道信息 */
		ChannelDistributor channelDistributor = orderProcessService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "id", channelId);
		// 处理会员信息
		Customer apicustomer = trade.getCustomer();
		com.vix.ebusiness.entity.BusinessCustomer customer = null;
		if (apicustomer != null && apicustomer.getCustomerName() != null && !"".equals(apicustomer.getCustomerName()) && !"null".equals(apicustomer.getCustomerName())) {
			/* 查询数据库是否已经存在该会员信息 */
			StringBuffer hql = new StringBuffer();
			hql.append("from com.vix.ebusiness.entity.BusinessCustomer where 1=1 and customerName = '" + apicustomer.getCustomerName() + "' and channelDistributor.id=" + channelId + " and mobile='" + apicustomer.getMobile() + "' and tel='" + apicustomer.getTel() + "'");
			Map<String, Object> params = new HashMap<String, Object>();
			customer = orderProcessService.findObjectByHqlNoTenantId(hql.toString(), params);
			if (customer != null) {
			} else {
				customer = dealCustomer(apicustomer, channelDistributor);
				initEntityBaseAttribute(customer, channelDistributor);
				customer = orderProcessService.mergeOriginal(customer);
			}
		} else {
			customer = new com.vix.ebusiness.entity.BusinessCustomer();
			customer.setChannelDistributor(channelDistributor);
			initEntityBaseAttribute(customer, channelDistributor);
			customer = orderProcessService.mergeOriginal(customer);
		}
		// 处理订单
		com.vix.ebusiness.entity.Order tradeBo = dealTrade(trade, customer, channelDistributor);
		return tradeBo;
	}

	/* 保存客户信息 */
	private BusinessCustomer dealCustomer(Customer customer, ChannelDistributor channelDistributor) {

		BusinessCustomer businessCustomer = new BusinessCustomer();
		businessCustomer.setCompanyCode(channelDistributor.getCompanyCode());
		/* 关联 */
		businessCustomer.setChannelDistributor(channelDistributor);
		businessCustomer.setAddr(customer.getAddr());
		businessCustomer.setBirthMonth(customer.getBirthMonth());
		businessCustomer.setBirthYear(customer.getBirthYear());
		businessCustomer.setCreateTime(Calendar.getInstance().getTime());
		businessCustomer.setBirthDay(customer.getBirthDay());
		businessCustomer.setEmail(customer.getEmail());
		businessCustomer.setIsDelete(customer.getIsDelete());
		businessCustomer.setLevelId(customer.getLevelId());
		businessCustomer.setOuterId(customer.getOuterId());
		businessCustomer.setPassword(customer.getPassword());
		businessCustomer.setPasswordAnswer(customer.getPasswordAnswer());
		businessCustomer.setPasswordQuestion(customer.getPasswordQuestion());
		businessCustomer.setPoint(customer.getPoint());
		businessCustomer.setPointHistory(customer.getPointHistory());
		businessCustomer.setPointFreeze(customer.getPointFreeze());
		businessCustomer.setSecurityEmail(customer.getSecurityEmail());
		// 设置会员状态为待处理 1 待处理
		businessCustomer.setStatus("1");
		businessCustomer.setStateTime(customer.getStateTime());
		if (customer.getCustomerName() != null && !"".equals(customer.getCustomerName()) && !"null".equals(customer.getCustomerName())) {
			businessCustomer.setCustomerName(customer.getCustomerName().trim());
		}
		if (customer.getMobile() != null && !"".equals(customer.getMobile()) && !"null".equals(customer.getMobile())) {
			businessCustomer.setMobile(customer.getMobile().trim());
		}
		if (customer.getTel() != null && !"".equals(customer.getTel()) && !"null".equals(customer.getTel())) {
			businessCustomer.setTel(customer.getTel().trim());
		}
		return businessCustomer;
	}

	private com.vix.ebusiness.entity.Order dealTrade(Trade trade, com.vix.ebusiness.entity.BusinessCustomer customer, ChannelDistributor channelDistributor) throws Exception {

		com.vix.ebusiness.entity.Order tradeBo = null;
		if (trade.getOuterId() != null && !"".equals(trade.getOuterId())) {
			tradeBo = orderProcessService.findEntityByAttributeNoTenantId(com.vix.ebusiness.entity.Order.class, "outerId", trade.getOuterId());
			if (tradeBo != null) {
				return null;
			} else {
				tradeBo = new com.vix.ebusiness.entity.Order();
			}
		}
		if (channelDistributor != null) {
			tradeBo.setChannelDistributor(channelDistributor);
		}
		tradeBo.setChannelDistributor(channelDistributor);
		tradeBo.setCustomer(customer);
		tradeBo.setOuterId(trade.getOuterId());
		tradeBo.setPayment(trade.getPayment());
		tradeBo.setBuyerMessage(trade.getBuyerMessage());
		tradeBo.setShippingType(trade.getShippingType());
		tradeBo.setBuyerCodFee(trade.getBuyerCodFee());
		tradeBo.setSellerCodFee(trade.getSellerCodFee());
		tradeBo.setExpressAgencyfee(trade.getExpressAgencyfee());
		tradeBo.setBuyerMemo(trade.getBuyerMemo());
		tradeBo.setBuyerFlag(trade.getBuyerFlag());
		tradeBo.setSellerFlag(trade.getSellerFlag());
		tradeBo.setPayType(trade.getPayType());
		tradeBo.setPayTypeCn(trade.getPayTypeCn());
		tradeBo.setPayNo(trade.getPayNo());
		tradeBo.setCodFee(trade.getCodFee());
		tradeBo.setSellerNick(trade.getSellerNick());
		tradeBo.setType(trade.getType());
		tradeBo.setCreateTime(trade.getCreated());
		tradeBo.setSellerRate(trade.getSellerRate());
		tradeBo.setBuyerRate(trade.getBuyerRate());
		tradeBo.setTradeMemo(trade.getTradeMemo());
		tradeBo.setPointFee(trade.getPointFee());
		tradeBo.setRealPointFee(trade.getRealPointFee());
		tradeBo.setPostFee(trade.getPostFee());
		tradeBo.setStatus(trade.getStatus());
		tradeBo.setBuyerNick(trade.getBuyerNick());
		tradeBo.setReceiverState(trade.getReceiverState());
		tradeBo.setReceiverCity(trade.getReceiverCity());
		tradeBo.setReceiverDistrict(trade.getReceiverDistrict());
		tradeBo.setReceiverName(trade.getReceiverName());
		tradeBo.setReceiverAddress(trade.getReceiverAddress());
		tradeBo.setReceiverMobile(trade.getReceiverMobile());
		tradeBo.setReceiverPhone(trade.getReceiverPhone());
		tradeBo.setPromotion(trade.getPromotion());
		tradeBo.setInvoiceName(trade.getInvoiceName());
		tradeBo.setDiscountFee(trade.getDiscountFee());
		tradeBo.setIsWeight("0");
		if (trade.getSellerShippingType() != null && !"".equals(trade.getSellerShippingType()) && !"null".equals(trade.getSellerShippingType())) {
			tradeBo.setSellerShippingType(trade.getSellerShippingType());
		}

		OrderPrintData orderPrintData = trade.getOrderPrintData();
		if (orderPrintData != null) {
			tradeBo.setDistributionCenterName(orderPrintData.getDistributionCenterName());
			tradeBo.setPartnerName(orderPrintData.getPartnerName());
			tradeBo.setSortingCode(orderPrintData.getSortingCode());
			tradeBo.setDistributionCenterCode(orderPrintData.getDistributionCenterCode());
		} else {
		}
		boolean typeCheck = false;
		if (trade.getReceiverAddress() != null && !"".equals(trade.getReceiverAddress())) {
			typeCheck = getShippingType(trade.getReceiverAddress());
		}
		if (typeCheck) {
			tradeBo.setShippingCompanyCode("EMS");
			tradeBo.setShippingCompanyName("邮政EMS");
		} else {
		}
		// 对会员积分进行赋值
		tradeBo.setBuyerObtainPointFee(trade.getBuyerObtainPointFee());
		// tradeBo.setNum(trade.getNum());
		tradeBo.setTotalFee(trade.getTotalFee());
		if (trade.getDealState() != null && !"".equals(trade.getDealState()) && !"null".equals(trade.getDealState())) {
			tradeBo.setDealState(trade.getDealState());
		} else
			tradeBo.setDealState(5);
		if (trade.getEndTime() != null && !"".equals(trade.getEndTime())) {
			tradeBo.setEndTime(trade.getEndTime());
		}
		initEntityBaseAttribute(tradeBo, channelDistributor);
		tradeBo = orderProcessService.mergeOriginal(tradeBo);
		/* 处理订单明细 */
		int orderDetailsNum = 0;
		List<Order> orderList = trade.getOrders();
		JSONArray orderDetails = new JSONArray();
		if (orderList != null && orderList.size() > 0) {
			orderDetailsNum = orderList.size();
			for (Order order : orderList) {
				JSONObject orderDetail = new JSONObject();
				orderDetail.put("title", order.getTitle());
				orderDetail.put("bn", order.getBn());
				orderDetail.put("sku_properties_name", order.getSkuPropertiesName());
				orderDetail.put("price", order.getPrice());
				orderDetail.put("num", order.getNum());
				orderDetail.put("payment", order.getPayment());
				orderDetail.put("outerskuid", order.getOuterSkuId());
				orderDetails.add(orderDetail);
				// 处理订单明细
				dealOrder(order, customer, channelDistributor, tradeBo);
			}
		}
		tradeBo.setOrderDetails(orderDetails.toString());
		// 订单明细个数
		tradeBo.setOrderDetailsNum(orderDetailsNum);

		/* 处理交易优惠详情 */
		List<TradePromotionDetail> tradePromotionDetails = trade.getTradePromotionDetail();
		int tradeClass = 0;
		if (tradePromotionDetails != null && tradePromotionDetails.size() > 0) {
			for (TradePromotionDetail tradePromotionDetail : tradePromotionDetails) {
				if (tradePromotionDetail != null && tradePromotionDetail.getPromotionName() != null && "团购优惠".equals(tradePromotionDetail.getPromotionName())) {
					tradeClass = 1;
				}
				// 交易优惠详情
				/* dealTradePromotionDetail(tradePromotionDetail, tradeBo); */
			}
		}
		tradeBo.setTradeClass(tradeClass);

		OnLineStoreParameters onLineStoreParameters = orderProcessService.findEntityByAttributeNoTenantId(OnLineStoreParameters.class, "tenantId", channelDistributor.getTenantId());
		if (onLineStoreParameters != null) {
			String isOpenAutomaticWarehouse = onLineStoreParameters.getIsOpenAutomaticWarehouse();
			if ("1".equals(isOpenAutomaticWarehouse)) {
				// 自动分仓
				InvWarehouse invWarehouse = allocateWarehouse(tradeBo, channelDistributor);
				if (invWarehouse != null) {
					tradeBo.setInvWarehouse(invWarehouse);
					tradeBo.setDealState(7);
				}
				// 自动分仓
			}
		}
		tradeBo = orderProcessService.mergeOriginal(tradeBo);
		vixOperateLog.saveOperateLog("", "", "OrderProcessAction", "订单:" + tradeBo.getOuterId() + "下载成功");
		return tradeBo;
	}

	/**
	 * 自动分仓
	 * 
	 * @param order
	 * @throws Exception
	 */
	public InvWarehouse allocateWarehouse(com.vix.ebusiness.entity.Order order, ChannelDistributor channelDistributor) throws Exception {
		if (channelDistributor.getTenantId() != null) {
			OnLineStoreParameters onLineStoreParameters = orderProcessService.findEntityByAttributeNoTenantId(OnLineStoreParameters.class, "tenantId", channelDistributor.getTenantId());
			if (onLineStoreParameters != null) {
				if ("1".equals(onLineStoreParameters.getIsOpenAutomaticWarehouse())) {
					Map<String, Object> params = new HashMap<String, Object>();
					List<InvWarehouse> invWarehouseList = orderProcessService.findAllByConditions(InvWarehouse.class, params);
					if (invWarehouseList != null) {
						for (InvWarehouse invWarehouse : invWarehouseList) {
							if (invWarehouse.getSubProvincess() != null && invWarehouse.getSubProvincess().size() > 0) {
								for (Provinces provinces : invWarehouse.getSubProvincess()) {
									if (provinces.getName().equals(order.getReceiverState())) {
										return invWarehouse;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param tradePromotionDetail
	 * @param tradeId
	 * @throws Exception
	 */

	private void dealOrder(Order order, BusinessCustomer businessCustomer, ChannelDistributor channelDistributor, com.vix.ebusiness.entity.Order tradeBo) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setTenantId(channelDistributor.getTenantId());
		orderDetail.setCustomer(businessCustomer);
		orderDetail.setOrder(tradeBo);
		orderDetail.setBuyerNick(order.getBuyerNick());
		orderDetail.setOuterGoodsId(order.getOuterGoodsId());
		if (order.getOuterSkuId() != null && !"".equals(order.getOuterSkuId()) && !"null".equals(order.getOuterSkuId())) {
			orderDetail.setOuterSkuId(order.getOuterSkuId());
		} else {
			orderDetail.setOuterSkuId(order.getOuterGoodsId());
		}
		orderDetail.setIsOverSold(order.getIsOverSold());
		// orderDetail.setNum(order.getNum());
		orderDetail.setOuterId(order.getOuterId());
		orderDetail.setPayment(order.getPayment());
		orderDetail.setBn(order.getBn());
		orderDetail.setTradeNo(order.getTradeNo());
		orderDetail.setPrice(Double.parseDouble(order.getPrice()));
		orderDetail.setSellerNick(order.getSellerNick());
		orderDetail.setSellerType(order.getSellerType());
		orderDetail.setSkuPropertiesName(order.getSkuPropertiesName());
		orderDetail.setTitle(order.getTitle());
		orderDetail.setConfirm(order.getConfirm());
		orderDetail.setStatus(order.getStatus());
		if (order.getPayStatus() == null)
			orderDetail.setPayStatus(0);
		else
			orderDetail.setPayStatus(order.getPayStatus());
		orderDetail.setShipStatus(order.getShipStatus());
		orderDetail.setUserStatus(order.getUserStatus());
		orderDetail.setIsDelivery(order.getIsDelivery());
		orderDetail.setDiscountFee(order.getDiscountFee());
		orderDetail.setStatus("未配货");
		initEntityBaseAttribute(orderDetail, channelDistributor);
		orderProcessService.mergeOriginal(orderDetail);
		// 处理订单锁定
		dealLockOrder(null, null, tradeBo, orderDetail);
	}

	// 处理订单锁定
	public void dealLockOrder(String itemcode, String skuCode, com.vix.ebusiness.entity.Order order, OrderDetail orderDetail) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤临时数据
		params.put("isQualfied", 1);
		params.put("skuCode", skuCode);
		params.put("itemcode", itemcode);
		StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
		InventoryCurrentStock inventoryCurrentStock = orderProcessService.findObjectByHqlNoTenantId(hql.toString(), params);
		if (inventoryCurrentStock != null) {
			Map<String, Object> orderAndGoodsparams = new HashMap<String, Object>();
			orderAndGoodsparams.put("orderDetailId", orderDetail.getId());
			orderAndGoodsparams.put("goodId", inventoryCurrentStock.getId());
			StringBuilder orderAndGoodshql = standingBookHqlProvider.findOrderAndGoods(orderAndGoodsparams);
			OrderAndGoods orderAndGoods = orderProcessService.findObjectByHqlNoTenantId(orderAndGoodshql.toString(), orderAndGoodsparams);
			if (orderAndGoods != null) {
			} else {
				orderAndGoods = new OrderAndGoods();
				orderAndGoods.setOrderId(order.getId());
				orderAndGoods.setCreateTime(new Date());
				orderAndGoods.setOrderDetailId(orderDetail.getId());
				orderAndGoods.setGoodId(inventoryCurrentStock.getId());
				orderAndGoods.setItemcode(itemcode);
				orderAndGoods.setSkuCode(skuCode);
				// orderAndGoods.setNum(orderDetail.getNum());
				// 1,锁定 2,解除锁定
				orderAndGoods.setStatus("1");
				orderProcessService.mergeOriginal(orderAndGoods);
				// 改变可用数量
				inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - orderDetail.getNum());
				// 改变锁定数量
				// inventoryCurrentStock.setFreezequantity(inventoryCurrentStock.getFreezequantity()
				// + orderDetail.getNum());
				orderProcessService.mergeOriginal(inventoryCurrentStock);
			}
		}
	}

	public void initEntityBaseAttribute(BaseEntity baseEntity, ChannelDistributor channelDistributor) throws Exception {

		if (null != baseEntity) {
			if (baseEntity.getCompanyInnerCode() != null && !"".equals(baseEntity.getCompanyInnerCode())) {
			} else {
				baseEntity.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
			}
			if (baseEntity.getCreator() != null && !"".equals(baseEntity.getCreator())) {
			} else {
				baseEntity.setCreator(channelDistributor.getCreator());
			}
			if (baseEntity.getTenantId() != null && !"".equals(baseEntity.getTenantId())) {
			} else {
				baseEntity.setTenantId(channelDistributor.getTenantId());
			}
			if (baseEntity.getCreateTime() != null && !"".equals(baseEntity.getCreateTime())) {
				baseEntity.setUpdateTime(new Date());
			} else {
				baseEntity.setCreateTime(new Date());
			}
			if (baseEntity.getCompanyCode() != null && !"".equals(baseEntity.getCompanyCode())) {
			} else {
				baseEntity.setCompanyCode(channelDistributor.getCompanyCode());
			}
		}
	}

	public boolean getShippingType(String receiverAddress) {
		String[] str = "县,乡,村".split(",");
		for (String element : str) {
			if (receiverAddress.contains(element)) {
				return true;
			}
		}
		return false;
	}

}
