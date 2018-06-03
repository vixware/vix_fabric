package com.vix.WebService.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ec.api.trade.domain.Customer;
import com.ec.api.trade.domain.Order;
import com.ec.api.trade.domain.OrderPrintData;
import com.ec.api.trade.domain.Trade;
import com.ec.api.trade.domain.TradePromotionDetail;
import com.vix.WebService.service.IOrderDownloadService;
import com.vix.WebService.vo.TradeVo;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.OrderAndGoods;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.inventory.inbound.domain.InboundWarehouseDomain;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;

import net.sf.cglib.beans.BeanCopier;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单下载处理
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-21
 */
@Component(value = "order")
@WebService(endpointInterface = "com.vix.WebService.impl.IOrderDownloadService", targetNamespace = "http://192.168.0.101/cxfservices/")
public class OrderDownloadServiceImpl implements IOrderDownloadService {
	@Resource(name = "orderProcessService")
	private IOrderProcessService orderProcessService;
	@Autowired
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private InboundWarehouseDomain inboundWarehouseDomain;

	/**
	 * tradeVo 订单信息
	 * 
	 * channelId 门店/店铺编码
	 */
	@Override
	public com.vix.ebusiness.entity.Order dealOrders(TradeVo tradeVo, String channelDistributorCode) throws Exception {

		BeanCopier copier = BeanCopier.create(TradeVo.class, Trade.class, false);
		Trade trade = new Trade();
		copier.copy(tradeVo, trade, null);

		/* 获取渠道信息 */
		ChannelDistributor channelDistributor = orderProcessService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "appKey", channelDistributorCode);
		// 处理会员信息
		Customer apicustomer = trade.getCustomer();
		com.vix.ebusiness.entity.BusinessCustomer customer = null;
		if (apicustomer != null && apicustomer.getCustomerName() != null && !"".equals(apicustomer.getCustomerName()) && !"null".equals(apicustomer.getCustomerName())) {
			/* 查询数据库是否已经存在该会员信息 */
			StringBuffer hql = new StringBuffer();
			hql.append("from com.vix.ebusiness.entity.BusinessCustomer where 1=1 and customerName = '" + apicustomer.getCustomerName() + "' and channelDistributor.id=" + channelDistributor.getId() + " and mobile='" + apicustomer.getMobile() + "' and tel='" + apicustomer.getTel() + "'");
			Map<String, Object> params = new HashMap<String, Object>();
			customer = orderProcessService.findObjectByHqlNoTenantId(hql.toString(), params);
			if (customer != null) {
			} else {
				customer = dealCustomer(apicustomer, channelDistributor);
				customer = orderProcessService.merge(customer);
			}
		} else {
			customer = new com.vix.ebusiness.entity.BusinessCustomer();
			customer.setChannelDistributor(channelDistributor);
			customer = orderProcessService.merge(customer);
		}
		// 处理订单
		com.vix.ebusiness.entity.Order tradeBo = dealTrade(trade, customer, channelDistributor);
		return tradeBo;
	}

	/* 保存客户信息 */
	private com.vix.ebusiness.entity.BusinessCustomer dealCustomer(Customer customer, ChannelDistributor channel) {

		com.vix.ebusiness.entity.BusinessCustomer customerBo = new com.vix.ebusiness.entity.BusinessCustomer();
		/* 关联 */
		customerBo.setChannelDistributor(channel);
		customerBo.setAddr(customer.getAddr());
		customerBo.setBirthMonth(customer.getBirthMonth());
		customerBo.setBirthYear(customer.getBirthYear());
		customerBo.setCreateTime(Calendar.getInstance().getTime());
		customerBo.setBirthDay(customer.getBirthDay());
		customerBo.setEmail(customer.getEmail());
		customerBo.setIsDelete(customer.getIsDelete());
		customerBo.setLevelId(customer.getLevelId());
		customerBo.setOuterId(customer.getOuterId());
		customerBo.setPassword(customer.getPassword());
		customerBo.setPasswordAnswer(customer.getPasswordAnswer());
		customerBo.setPasswordQuestion(customer.getPasswordQuestion());
		customerBo.setPoint(customer.getPoint());
		customerBo.setPointHistory(customer.getPointHistory());
		customerBo.setPointFreeze(customer.getPointFreeze());
		customerBo.setSecurityEmail(customer.getSecurityEmail());
		customerBo.setStatus(customer.getState());
		customerBo.setStateTime(customer.getStateTime());
		if (customer.getCustomerName() != null && !"".equals(customer.getCustomerName()) && !"null".equals(customer.getCustomerName())) {
			customerBo.setCustomerName(customer.getCustomerName().trim());
		}
		if (customer.getMobile() != null && !"".equals(customer.getMobile()) && !"null".equals(customer.getMobile())) {
			customerBo.setMobile(customer.getMobile().trim());
		}
		if (customer.getTel() != null && !"".equals(customer.getTel()) && !"null".equals(customer.getTel())) {
			customerBo.setTel(customer.getTel().trim());
		}
		return customerBo;
	}

	private com.vix.ebusiness.entity.Order dealTrade(Trade trade, com.vix.ebusiness.entity.BusinessCustomer customer, ChannelDistributor channelDistributor) throws Exception {

		com.vix.ebusiness.entity.Order tradeBo = null;
		if (trade.getOuterId() != null && !"".equals(trade.getOuterId())) {
			tradeBo = orderProcessService.findEntityByAttribute(com.vix.ebusiness.entity.Order.class, "outerId", trade.getOuterId());
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
		//tradeBo.setNum(trade.getNum());
		tradeBo.setTotalFee(trade.getTotalFee());
		if (trade.getDealState() != null && !"".equals(trade.getDealState()) && !"null".equals(trade.getDealState())) {
			tradeBo.setDealState(trade.getDealState());
		} else
			tradeBo.setDealState(5);
		if (trade.getEndTime() != null && !"".equals(trade.getEndTime())) {
			tradeBo.setEndTime(trade.getEndTime());
		}
		tradeBo = orderProcessService.merge(tradeBo);
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
			}
		}
		tradeBo.setTradeClass(tradeClass);
		//自动分仓
		InvWarehouse invWarehouse = allocateWarehouse(tradeBo);
		if (invWarehouse != null) {
			tradeBo.setInvWarehouse(invWarehouse);
		}
		//自动分仓
		tradeBo = orderProcessService.merge(tradeBo);
		return tradeBo;
	}

	/**
	 * 自动分仓
	 * 
	 * @param order
	 * @throws Exception
	 */
	public InvWarehouse allocateWarehouse(com.vix.ebusiness.entity.Order order) throws Exception {
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
		return null;
	}

	private void dealOrder(Order order, com.vix.ebusiness.entity.BusinessCustomer customer, ChannelDistributor channel, com.vix.ebusiness.entity.Order tradeBo) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCustomer(customer);
		orderDetail.setOrder(tradeBo);
		orderDetail.setBuyerNick(order.getBuyerNick());
		orderDetail.setOuterGoodsId(order.getOuterGoodsId());
		if (order.getOuterSkuId() != null && !"".equals(order.getOuterSkuId()) && !"null".equals(order.getOuterSkuId())) {
			orderDetail.setOuterSkuId(order.getOuterSkuId());
		} else {
			orderDetail.setOuterSkuId(order.getOuterGoodsId());
		}
		orderDetail.setIsOverSold(order.getIsOverSold());
		//orderDetail.setNum(order.getNum());
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
		orderProcessService.merge(orderDetail);
		//处理订单锁定
		dealLockOrder(null, null, tradeBo, orderDetail);
	}

	//处理订单锁定
	public void dealLockOrder(String itemcode, String skuCode, com.vix.ebusiness.entity.Order order, OrderDetail orderDetail) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤临时数据
		params.put("isQualfied", 1);
		params.put("skuCode", skuCode);
		params.put("itemcode", itemcode);
		StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
		InventoryCurrentStock inventoryCurrentStock = inboundWarehouseDomain.findInventoryCurrentStockByHql(hql.toString(), params);
		if (inventoryCurrentStock != null) {
			Map<String, Object> orderAndGoodsparams = new HashMap<String, Object>();
			orderAndGoodsparams.put("orderDetailId", orderDetail.getId());
			orderAndGoodsparams.put("goodId", inventoryCurrentStock.getId());
			StringBuilder orderAndGoodshql = standingBookHqlProvider.findOrderAndGoods(orderAndGoodsparams);
			OrderAndGoods orderAndGoods = orderProcessService.findObjectByHql(orderAndGoodshql.toString(), orderAndGoodsparams);
			if (orderAndGoods != null) {
			} else {
				orderAndGoods = new OrderAndGoods();
				orderAndGoods.setOrderId(order.getId());
				orderAndGoods.setCreateTime(new Date());
				orderAndGoods.setOrderDetailId(orderDetail.getId());
				orderAndGoods.setGoodId(inventoryCurrentStock.getId());
				orderAndGoods.setItemcode(itemcode);
				orderAndGoods.setSkuCode(skuCode);
				//orderAndGoods.setNum(orderDetail.getNum());
				//1,锁定 2,解除锁定
				orderAndGoods.setStatus("1");
				orderProcessService.merge(orderAndGoods);
				//改变可用数量
				inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - orderDetail.getNum());
				//改变锁定数量
				//inventoryCurrentStock.setFreezequantity(inventoryCurrentStock.getFreezequantity() + orderDetail.getNum());
				orderProcessService.merge(inventoryCurrentStock);
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
