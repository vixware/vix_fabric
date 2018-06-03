package com.rest.ebusiness;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderAndGoods;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.ebusiness.option.entity.OnLineStoreParameters;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.restService.test.submodule.service.ISubmoduleRestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * com.rest.ebusiness.OrderDownController
 *
 * @author bjitzhang
 *
 * @date 2015年4月8日
 */

@Controller
@RequestMapping(value = "restService/orderDown")
public class OrderDownController extends BaseRestController {

	@Autowired
	private ISubmoduleRestService submoduleRestService;
	@Autowired
	public IOperateLog vixOperateLog;
	@Autowired
	private StandingBookHqlProvider standingBookHqlProvider;

	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	@ResponseBody
	public Boolean addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonData = request.getParameter("jsonData");
		JSONObject json = JSONObject.fromObject(jsonData);
		String validateCode = null;
		if (json.has("validateCode")) {
			validateCode = json.getString("validateCode");
		}
		String onlineStoreCode = null;
		if (json.has("channelDistributorCode")) {
			onlineStoreCode = json.getString("channelDistributorCode");
		}
		String authCode = null;
		if (StrUtils.objectIsNotNull("VIX")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
			Md5Encoder m5 = new Md5EncoderImpl();
			authCode = m5.encodeString("VIX", sdf.format(new Date()));
			System.out.println("authCode:" + authCode);
		}
		if (StrUtils.objectIsNull(validateCode) || !validateCode.equals(authCode)) {
			// return false;
		}
		if (StrUtils.objectIsNull(onlineStoreCode)) {
			// return false;
		}
		System.out.println("onlineStoreCode:" + onlineStoreCode);
		ChannelDistributor channelDistributor = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "appKey", onlineStoreCode);
		if (channelDistributor == null) {
			return false;
		} else {
			/* 获取渠道信息 */
			try {
				Order order = null;
				String outerId = json.getString("code");
				if (outerId != null && !"".equals(outerId)) {
					System.out.println("outerId:" + outerId);
					order = submoduleRestService.findEntityByAttributeNoTenantId(com.vix.ebusiness.entity.Order.class, "outerId", outerId);
					if (order == null) {
						order = new Order();
					}

				}
				order.setOuterId(outerId);
				String totalProductPrice = json.getString("totalProductPrice");
				order.setTotalFee(totalProductPrice);
				String totalPrice = json.getString("totalPrice");
				order.setPayment(totalPrice);
				if (json.has("memo")) {
					order.setMemo(json.getString("memo"));
				}
				order.setShippingType("");
				order.setBuyerCodFee("0");
				order.setSellerCodFee("0");
				order.setExpressAgencyfee(0L);
				order.setBuyerFlag(0L);
				order.setSellerFlag(0L);
				order.setPayNo("");
				order.setCodFee(0L);
				order.setType("fixed");
				order.setSellerRate(0);
				order.setBuyerRate(0);
				order.setPointFee(0);
				order.setRealPointFee(0);
				order.setPromotion("");
				order.setDealState(1);
				order.setIsWeight("0");
				order.setStatus("5");
				order.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
				order.setCompanyCode(channelDistributor.getCompanyCode());
				order.setTenantId(channelDistributor.getTenantId());
				if (json.has("invoiceTitle")) {
					order.setInvoiceName(decode(json.getString("invoiceTitle"), "UTF-8"));
				}
				if (json.has("freight")) {
					order.setPostFee(json.getString("freight"));
				}
				if (json.has("payType")) {
					String payType = json.getString("payType");
					order.setPayType(payType.split(",")[0]);
					order.setPayTypeCn(decode(payType.split(",")[1], "UTF-8"));
				}

				if (json.has("consignee")) {
					String consignee = json.getString("consignee");
					String[] consigneearr = consignee.split(",");
					if (consigneearr != null && consigneearr.length == 3) {
						order.setReceiverName(decode(consigneearr[0], "UTF-8"));
						order.setReceiverAddress(decode(consigneearr[1], "UTF-8"));
						order.setReceiverMobile(decode(consigneearr[2], "UTF-8"));
					}
				}
				if (json.has("ecCustomer")) {
					String ecCustomer = json.getString("ecCustomer");
					String[] ecCustomerarr = ecCustomer.split(",");
					if (ecCustomer != null && ecCustomerarr != null && ecCustomerarr.length == 2) {
						BusinessCustomer businessCustomer = new BusinessCustomer();
						businessCustomer.setCode(ecCustomerarr[0]);
						businessCustomer.setName(decode(ecCustomerarr[1], "UTF-8"));
						//
						businessCustomer.setBirthDay(0);
						businessCustomer.setBirthMonth(0);
						businessCustomer.setBirthYear(0);
						businessCustomer.setCreateTime(new Date());
						businessCustomer.setStateTime(new Date());
						businessCustomer.setLevelId(0L);
						businessCustomer.setIsDelete(0);
						//
						order.setCustomer(businessCustomer);
					}
				}

				List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

				if (json.has("ecOrderItem")) {
					String ecOrderItem = json.getString("ecOrderItem");
					if (ecOrderItem != null) {
						JSONArray ecOrderItemList = JSONArray.fromObject(ecOrderItem);
						for (int i = 0; i < ecOrderItemList.size(); i++) {
							OrderDetail orderDetail = new OrderDetail();
							JSONObject o = ecOrderItemList.getJSONObject(i);
							orderDetail.setPrice(Double.parseDouble(o.getString("price")));
							orderDetail.setNum(Double.parseDouble(o.getString("count")));
							Double payment = Double.parseDouble(o.getString("price")) * Double.parseDouble(o.getString("count"));
							orderDetail.setPayment(String.valueOf(payment));
							orderDetail.setTitle(decode(o.getString("itemName"), "UTF-8"));
							orderDetail.setOuterId(o.getString("sku"));
							orderDetail.setOuterGoodsId(o.getString("code"));
							//
							orderDetail.setIsOverSold(0);
							orderDetail.setSellerType("B");
							orderDetail.setShipStatus(0);
							orderDetail.setUserStatus(0);
							orderDetail.setIsDelivery(0);
							orderDetail.setConfirm(1);
							orderDetail.setStatus("未配货");
							//
							orderDetailList.add(orderDetail);
						}
					}
				}

				dealOrders(order, channelDistributor, orderDetailList);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	private Order dealOrders(Order order, ChannelDistributor channelDistributor, List<OrderDetail> orderDetailList) throws Exception {

		// 处理会员信息
		BusinessCustomer businessCustomer = order.getCustomer();
		if (businessCustomer != null && businessCustomer.getCustomerName() != null && !"".equals(businessCustomer.getCustomerName()) && !"null".equals(businessCustomer.getCustomerName())) {
			/* 查询数据库是否已经存在该会员信息 */
			StringBuffer hql = new StringBuffer();
			hql.append("from BusinessCustomer where 1=1 and customerName = '" + businessCustomer.getCustomerName() + "' and channelDistributor.code='" + channelDistributor.getCode() + "' and mobile='" + businessCustomer.getMobile() + "' and tel='" + businessCustomer.getTel() + "'");
			Map<String, Object> params = new HashMap<String, Object>();
			businessCustomer = submoduleRestService.findObjectByHql(hql.toString(), params);
			if (businessCustomer != null) {
			} else {
				businessCustomer = dealCustomer(businessCustomer, channelDistributor);
				businessCustomer.setTenantId(channelDistributor.getTenantId());
				businessCustomer.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
				businessCustomer.setCreator(channelDistributor.getCreator());
				businessCustomer = submoduleRestService.mergeOriginal(businessCustomer);
			}
		} else {
			businessCustomer = new BusinessCustomer();
			businessCustomer.setChannelDistributor(channelDistributor);
			businessCustomer.setTenantId(channelDistributor.getTenantId());
			businessCustomer.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
			businessCustomer.setCreator(channelDistributor.getCreator());
			businessCustomer = submoduleRestService.mergeOriginal(businessCustomer);
		}
		// 处理订单
		Order tradeBo = dealTrade(order, businessCustomer, channelDistributor, orderDetailList);
		//vixOperateLog.saveOperateLog("", "", "OrderProcessAction", "电商订单:" + order.getOuterId() + "下载成功", "", "abc", null, "", channelDistributor.getCompanyInnerCode());
		return tradeBo;
	}

	/* 保存客户信息 */
	private BusinessCustomer dealCustomer(BusinessCustomer businessCustomer, ChannelDistributor channelDistributor) {

		// 获取当前员工信息
		BaseEmployee e = SecurityUtil.getCurrentRealUser();
		if (e != null && e.getCompanyCode() != null) {
			businessCustomer.setCompanyCode(e.getCompanyCode());
		}
		/* 关联 */
		businessCustomer.setChannelDistributor(channelDistributor);
		// 设置会员状态为待处理 1 待处理
		businessCustomer.setStatus("1");
		return businessCustomer;
	}

	private Order dealTrade(Order order, BusinessCustomer businessCustomer, ChannelDistributor channelDistributor, List<OrderDetail> orderDetailList) throws Exception {

		if (channelDistributor != null) {
			order.setChannelDistributor(channelDistributor);
		}
		order.setChannelDistributor(channelDistributor);
		order.setCustomer(businessCustomer);

		boolean typeCheck = false;
		if (order.getReceiverAddress() != null && !"".equals(order.getReceiverAddress())) {
			typeCheck = getShippingType(order.getReceiverAddress());
		}
		if (typeCheck) {
			order.setShippingCompanyCode("EMS");
			order.setShippingCompanyName("邮政EMS");
		} else {
		}
		order.setTenantId(channelDistributor.getTenantId());
		order.setCreator(channelDistributor.getCreator());
		order.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
		order = submoduleRestService.mergeOriginal(order);
		/* 处理订单明细 */
		int orderDetailsNum = 0;
		JSONArray orderDetails = new JSONArray();
		if (orderDetailList != null && orderDetailList.size() > 0) {
			orderDetailsNum = orderDetailList.size();
			for (OrderDetail orderDetail : orderDetailList) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("title", orderDetail.getTitle());
				jSONObject.put("bn", orderDetail.getBn());
				jSONObject.put("sku_properties_name", orderDetail.getSkuPropertiesName());
				jSONObject.put("price", orderDetail.getPrice());
				jSONObject.put("num", orderDetail.getNum());
				jSONObject.put("payment", orderDetail.getPayment());
				jSONObject.put("outerskuid", orderDetail.getOuterSkuId());
				orderDetails.add(jSONObject);
				// 处理订单明细
				dealOrder(orderDetail, businessCustomer, channelDistributor, order);
			}
		}
		order.setOrderDetails(orderDetails.toString());
		// 订单明细个数
		order.setOrderDetailsNum(orderDetailsNum);

		OnLineStoreParameters onLineStoreParameters = submoduleRestService.findEntityByAttributeNoTenantId(OnLineStoreParameters.class, "tenantId", channelDistributor.getTenantId());
		if (onLineStoreParameters != null) {
			String isOpenAutomaticWarehouse = onLineStoreParameters.getIsOpenAutomaticWarehouse();
			if ("1".equals(isOpenAutomaticWarehouse)) {
				// 自动分仓
				InvWarehouse invWarehouse = allocateWarehouse(order);
				if (invWarehouse != null) {
					order.setInvWarehouse(invWarehouse);
					order.setDealState(7);
				}
				// 自动分仓
			}
		}
		order = submoduleRestService.mergeOriginal(order);
		//vixOperateLog.saveOperateLog("", "", "OrderProcessAction", "订单:" + order.getOuterId() + "下载成功", order.getTenantId(), order.getCreator(), null, order.getCompanyCode(), channelDistributor.getCompanyInnerCode());
		return order;
	}

	/**
	 * 
	 * @param tradePromotionDetail
	 * @param tradeId
	 * @throws Exception
	 */

	private void dealOrder(OrderDetail orderDetail, BusinessCustomer businessCustomer, ChannelDistributor channelDistributor, Order order) throws Exception {
		orderDetail.setCustomer(businessCustomer);
		orderDetail.setOrder(order);
		orderDetail.setStatus("未配货");
		orderDetail.setTenantId(channelDistributor.getTenantId());
		orderDetail.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
		orderDetail.setCreator(channelDistributor.getCreator());
		submoduleRestService.mergeOriginal(orderDetail);
		// 处理订单锁定
		dealLockOrder(null, null, order, orderDetail);
	}

	// 处理订单锁定
	public void dealLockOrder(String itemcode, String skuCode, Order order, OrderDetail orderDetail) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤临时数据
		params.put("isQualfied", 1);
		params.put("skuCode", skuCode);
		params.put("itemcode", itemcode);
		StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
		InventoryCurrentStock inventoryCurrentStock = submoduleRestService.findObjectByHql(hql.toString(), params);
		if (inventoryCurrentStock != null) {
			Map<String, Object> orderAndGoodsparams = new HashMap<String, Object>();
			orderAndGoodsparams.put("orderDetailId", orderDetail.getId());
			orderAndGoodsparams.put("goodId", inventoryCurrentStock.getId());
			StringBuilder orderAndGoodshql = standingBookHqlProvider.findOrderAndGoods(orderAndGoodsparams);
			OrderAndGoods orderAndGoods = submoduleRestService.findObjectByHql(orderAndGoodshql.toString(), orderAndGoodsparams);
			if (orderAndGoods != null) {
			} else {
				orderAndGoods = new OrderAndGoods();
				orderAndGoods.setOrderId(order.getId());
				orderAndGoods.setCreateTime(new Date());
				orderAndGoods.setOrderDetailId(orderDetail.getId());
				orderAndGoods.setGoodId(inventoryCurrentStock.getId());
				orderAndGoods.setItemcode(itemcode);
				orderAndGoods.setSkuCode(skuCode);
				orderAndGoods.setNum(orderDetail.getNum());
				// 1,锁定 2,解除锁定
				orderAndGoods.setStatus("1");
				submoduleRestService.mergeOriginal(orderAndGoods);
				// 改变可用数量
				inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - orderDetail.getNum());
				// 改变锁定数量
				inventoryCurrentStock.setFreezequantity(inventoryCurrentStock.getFreezequantity() + orderDetail.getNum());
				submoduleRestService.mergeOriginal(inventoryCurrentStock);
			}
		}
	}

	/**
	 * 自动分仓
	 * 
	 * @param order
	 * @throws Exception
	 */
	public InvWarehouse allocateWarehouse(Order order) throws Exception {
		if (SecurityUtil.getCurrentUserTenantId() != null) {
			OnLineStoreParameters onLineStoreParameters = submoduleRestService.findEntityByAttributeNoTenantId(OnLineStoreParameters.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
			if (onLineStoreParameters != null) {
				if ("1".equals(onLineStoreParameters.getIsOpenAutomaticWarehouse())) {
					Map<String, Object> params = new HashMap<String, Object>();
					List<InvWarehouse> invWarehouseList = submoduleRestService.findAllByConditions(InvWarehouse.class, params);
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

	/** 解码 */
	@Override
	public String decode(String str, String enc) throws Exception {
		str = URLDecoder.decode(str, enc);
		return str;
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
