package com.rest.shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorSet;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.restService.test.submodule.service.ISubmoduleRestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.shop.SalesOrderUpLoadController
 *
 * @author zhanghaibing
 *
 * @date 2016年7月29日
 */

@Controller
@RequestMapping(value = "restService/salesOrderUpLoad")
public class SalesOrderUpLoadController extends BaseRestController {

	@Autowired
	private ISubmoduleRestService submoduleRestService;
	@Autowired
	public IOperateLog vixOperateLog;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	@RequestMapping(value = "upLoadOrder", method = RequestMethod.POST)
	@ResponseBody
	public void upLoadOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		if (StringUtils.isNotEmpty(orderData)) {
			System.out.println(orderData);
			JSONObject orderDataJson = JSONObject.fromObject(orderData);
			if (orderDataJson.has("ORDERDATALIST")) {
				JSONArray orderDataJSONArray = orderDataJson.getJSONArray("ORDERDATALIST");
				JSONArray ja = new JSONArray();
				JSONObject returnJson = new JSONObject();
				for (int i = 0; i < orderDataJSONArray.size(); i++) {
					JSONObject json = orderDataJSONArray.getJSONObject(i);
					JSONObject jo = new JSONObject();
					String channelDistributorCode = null;
					if (json.has("CHANNELDISTRIBUTORCODE")) {
						channelDistributorCode = json.getString("CHANNELDISTRIBUTORCODE").trim();
					}
					if (StrUtils.objectIsNull(channelDistributorCode)) {
						jo.put("MESSAGE", "操作失败!缺少门店编码.");
						jo.put("SUCCESS", false);
						jo.put("ORDERID", "");
						ja.add(jo);
						continue;
					}
					ChannelDistributorSet channelDistributorSet = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributorSet.class, "innerCode", channelDistributorCode);
					if (channelDistributorSet != null && channelDistributorSet.getChannelDistributor() != null && StringUtils.isNotEmpty(channelDistributorSet.getChannelDistributor().getId())) {
						ChannelDistributor channelDistributor = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "id", channelDistributorSet.getChannelDistributor().getId());
						String outerId = json.getString("CODE").trim();
						if (channelDistributor == null) {
							jo.put("MESSAGE", "操作失败!门店不存在.");
							jo.put("SUCCESS", false);
							jo.put("ORDERID", "");
							ja.add(jo);
							continue;
						} else {
							/* 获取渠道信息 */
							Order order = null;
							if (StringUtils.isNotEmpty(outerId)) {
								Map<String, Object> params = new HashMap<String, Object>();
								params.put("outerId", outerId);
								params.put("channelDistributorId", channelDistributor.getId());
								StringBuilder hql = findOrderHql(params);
								order = submoduleRestService.findObjectByHql(hql.toString(), params);
								if (order != null) {
									jo.put("MESSAGE", "操作成功! " + outerId + " 订单同步成功.");
									jo.put("SUCCESS", true);
									jo.put("ORDERID", outerId);
									ja.add(jo);
									continue;
								} else {
									order = new Order();
									order.setOuterId(outerId);
								}
							}
							if (json.has("CREATEDATE")) {
								String createDate = json.getString("CREATEDATE").trim();
								order.setCreateTime(getCreateDate(createDate));
							}
							if (json.has("TOTALPRODUCTPRICE")) {
								String totalProductPrice = json.getString("TOTALPRODUCTPRICE").trim();
								order.setTotalFee(totalProductPrice);
							}
							if (json.has("TOTALPRICE")) {
								String totalPrice = json.getString("TOTALPRICE").trim();
								order.setPayment(totalPrice);
							}
							if (json.has("MEMO")) {
								order.setMemo(json.getString("MEMO").trim());
							}
							if (json.has("BIZTYPE")) {
								order.setType(json.getString("BIZTYPE").trim());
							}
							order.setShippingType("");
							order.setBuyerCodFee("0");
							order.setSellerCodFee("0");
							order.setExpressAgencyfee(0L);
							order.setBuyerFlag(0L);
							order.setSellerFlag(0L);
							order.setPayNo("");
							order.setCodFee(0L);
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
							// 支付方式
							if (json.has("PAYTYPEDATA")) {
								String payTypeData = json.getString("PAYTYPEDATA");
								if (StringUtils.isNotEmpty(payTypeData)) {
									JSONObject payTypeDataJson = JSONObject.fromObject(payTypeData);
									if (payTypeDataJson != null) {
										if (payTypeDataJson.has("PAYTYPECODE")) {
											order.setPayType(payTypeDataJson.getString("PAYTYPECODE").trim());
										}
										if (payTypeDataJson.has("PAYTYPENAME")) {
											order.setPayTypeCn(payTypeDataJson.getString("PAYTYPENAME").trim());
										}
									}
								}
							}
							// 会员信息
							if (json.has("CUSTOMERDATA")) {
								String customerData = json.getString("CUSTOMERDATA");
								if (StringUtils.isNotEmpty(customerData)) {
									JSONObject customerJson = JSONObject.fromObject(customerData);
									if (customerJson != null) {
										BusinessCustomer businessCustomer = new BusinessCustomer();
										if (customerJson.has("CUSTOMERCODE")) {
											businessCustomer.setCode(customerJson.getString("CUSTOMERCODE").trim());
										}
										if (customerJson.has("CUSTOMERNAME")) {
											businessCustomer.setCustomerName(customerJson.getString("CUSTOMERNAME").trim());
										}
										if (customerJson.has("MOBILE")) {
											businessCustomer.setMobile(customerJson.getString("MOBILE").trim());
										}
										order.setCustomer(businessCustomer);
									}
								}
							}
							// 订单明细
							List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
							if (json.has("ORDERDETAILDATA")) {
								String orderDetailData = json.getString("ORDERDETAILDATA");
								if (StringUtils.isNotEmpty(orderDetailData)) {
									JSONArray ecOrderItemList = JSONArray.fromObject(orderDetailData);
									for (int j = 0; j < ecOrderItemList.size(); j++) {
										OrderDetail orderDetail = new OrderDetail();
										JSONObject o = ecOrderItemList.getJSONObject(j);
										if (o.has("PRICE")) {
											orderDetail.setPrice(Double.parseDouble(o.getString("PRICE").trim()));
										}
										if (o.has("COUNT")) {
											orderDetail.setNum(Double.parseDouble(o.getString("COUNT").trim()));
										}
										if (o.has("PAYMENT")) {
											orderDetail.setPayment(o.getString("PAYMENT").trim());
										}
										if (o.has("ITEMNAME")) {
											orderDetail.setTitle(decode(o.getString("ITEMNAME").trim(), "UTF-8"));
										}
										if (o.has("ITEMCODE")) {
											orderDetail.setOuterGoodsId(o.getString("ITEMCODE").trim());// 商品编码
										}
										orderDetail.setIsOverSold(0);
										orderDetail.setSellerType("B");
										orderDetail.setShipStatus(0);
										orderDetail.setUserStatus(0);
										orderDetail.setIsDelivery(0);
										orderDetail.setConfirm(1);
										orderDetail.setStatus("未配货");
										orderDetailList.add(orderDetail);
									}
								}
							}
							order = dealOrders(order, channelDistributor, orderDetailList);
						}
						jo.put("MESSAGE", "操作成功! " + outerId + " 订单同步成功.");
						jo.put("SUCCESS", true);
						jo.put("ORDERID", outerId);
						ja.add(jo);
					}
				}
				returnJson.put("RETURNVALUE", ja);
				System.out.println(returnJson.toString());
				writeClientMsg(response, returnJson.toString());
			}
		}
	}

	public ChannelDistributorSet getChannelDistributorSet(String id) {
		ChannelDistributorSet channelDistributorSet = null;
		try {
			if (StringUtils.isNotEmpty(id)) {
				channelDistributorSet = submoduleRestService.findEntityByAttribute(ChannelDistributorSet.class, "channelDistributor.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelDistributorSet;
	}

	private Order dealOrders(Order order, ChannelDistributor channelDistributor, List<OrderDetail> orderDetailList) throws Exception {

		// 处理会员信息
		/* 查询数据库是否已经存在该会员信息 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerName", order.getCustomer().getCustomerName());
		params.put("outerId", order.getCustomer().getCode());
		params.put("mobile", order.getCustomer().getMobile());
		params.put("channelDistributorId", channelDistributor.getId());
		StringBuilder hql = findBusinessCustomerHql(params);
		BusinessCustomer businessCustomer = submoduleRestService.findObjectByHqlNoTenantId(hql.toString(), params);
		if (businessCustomer != null) {
		} else {
			businessCustomer = new BusinessCustomer();
			businessCustomer.setTenantId(channelDistributor.getTenantId());
			businessCustomer.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
			businessCustomer.setCreator(channelDistributor.getCreator());
			businessCustomer.setCustomerName(order.getCustomer().getCustomerName());
			businessCustomer.setOuterId(order.getCustomer().getCode());
			businessCustomer.setChannelDistributor(channelDistributor);
			businessCustomer.setMobile(order.getCustomer().getMobile());
			businessCustomer.setCreateTime(order.getCreateTime());
			businessCustomer = submoduleRestService.mergeOriginal(businessCustomer);
		}
		// 处理订单
		Order tradeBo = dealTrade(order, businessCustomer, channelDistributor, orderDetailList);
		return tradeBo;
	}

	private Order dealTrade(Order order, BusinessCustomer businessCustomer, ChannelDistributor channelDistributor, List<OrderDetail> orderDetailList) throws Exception {

		if (channelDistributor != null) {
			order.setChannelDistributor(channelDistributor);
			order.setTenantId(channelDistributor.getTenantId());
			order.setCreator(channelDistributor.getCreator());
			order.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
		}
		order.setCustomer(businessCustomer);
		order = submoduleRestService.mergeOriginal(order);
		/* 处理订单明细 */
		JSONArray orderDetails = new JSONArray();
		if (orderDetailList != null && orderDetailList.size() > 0) {
			for (OrderDetail orderDetail : orderDetailList) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("title", orderDetail.getTitle());
				jSONObject.put("price", orderDetail.getPrice());
				jSONObject.put("num", orderDetail.getNum());
				jSONObject.put("payment", orderDetail.getPayment());
				jSONObject.put("outerGoodsId", orderDetail.getOuterGoodsId());
				orderDetails.add(jSONObject);
				// 处理订单明细
				dealOrder(order, orderDetail, businessCustomer, channelDistributor);
			}
		}
		order.setOrderDetails(orderDetails.toString());
		order.setCreateTime(order.getCreateTime());
		order = submoduleRestService.mergeOriginal(order);
		//vixOperateLog.saveOperateLog("", "", "OrderProcessAction", "订单:" + order.getOuterId() + "上传成功", order.getTenantId(), order.getCreator(), null, order.getCompanyCode(), channelDistributor.getCompanyInnerCode());
		return order;
	}

	/**
	 * 
	 * @param order
	 * @param orderDetail
	 * @param businessCustomer
	 * @param channelDistributor
	 * @throws Exception
	 */

	private void dealOrder(Order order, OrderDetail orderDetail, BusinessCustomer businessCustomer, ChannelDistributor channelDistributor) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("outerGoodsId", orderDetail.getOuterGoodsId());
		params.put("orderId", order.getId());
		StringBuilder hql = findOrderDetailHql(params);
		OrderDetail newOrderDetail = submoduleRestService.findObjectByHql(hql.toString(), params);
		if (newOrderDetail != null) {

		} else {
			orderDetail.setCustomer(businessCustomer);
			orderDetail.setOrder(order);
			orderDetail.setStatus("未配货");
			if (channelDistributor != null) {
				orderDetail.setTenantId(channelDistributor.getTenantId());
				orderDetail.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
				orderDetail.setCreator(channelDistributor.getCreator());
				orderDetail.setCreateTime(order.getCreateTime());
			}
			orderDetail = submoduleRestService.mergeOriginal(orderDetail);
		}
		if (!"1".equals(orderDetail.getIsUpdateInv())) {
			// 处理库存
			dealInventory(order, orderDetail, channelDistributor);
		}
	}

	/**
	 * 商品编码 门店
	 * 
	 * @param order
	 * @param orderDetail
	 * @param channelDistributor
	 * @throws Exception
	 */
	private void dealInventory(Order order, OrderDetail orderDetail, ChannelDistributor channelDistributor) throws Exception {
		if (orderDetail != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("itemcode", orderDetail.getOuterGoodsId());
			params.put("channelDistributorId", channelDistributor.getId());
			StringBuilder hql = findInventoryCurrentStockHql(params);
			InventoryCurrentStock inventoryCurrentStock = submoduleRestService.findObjectByHql(hql.toString(), params);
			if (inventoryCurrentStock != null) {
				if (order != null && StringUtils.isNotEmpty(order.getType())) {
					if ("1".equals(order.getType())) {
						if (inventoryCurrentStock.getAvaquantity() != null && inventoryCurrentStock.getAvaquantity() > orderDetail.getNum()) {
							inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - orderDetail.getNum());
						}
						if (inventoryCurrentStock.getQuantity() != null && inventoryCurrentStock.getQuantity() > orderDetail.getNum()) {
							inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() - orderDetail.getNum());
						}
					} else if ("2".equals(order.getType())) {
						if (inventoryCurrentStock.getAvaquantity() != null) {
							inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() + orderDetail.getNum());
						}
						if (inventoryCurrentStock.getQuantity() != null) {
							inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() + orderDetail.getNum());
						}
					}
				}
				inventoryCurrentStock = submoduleRestService.mergeOriginal(inventoryCurrentStock);
				orderDetail.setIsUpdateInv("1");
				orderDetail = submoduleRestService.mergeOriginal(orderDetail);
			}
		}
	}

	public StringBuilder findBusinessCustomerHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "businessCustomer";
		hql.append(" from BusinessCustomer ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("customerName")) {
				Object customerName = params.get("customerName");
				if (customerName == null) {
					hql.append(" and ").append(ename).append(".customerName is null");
					params.remove("customerName");
				} else {
					hql.append(" and ").append(ename).append(".customerName = :customerName ");
				}
			}
			if (params.containsKey("outerId")) {
				Object outerId = params.get("outerId");
				if (outerId == null) {
					hql.append(" and ").append(ename).append(".outerId is null");
					params.remove("outerId");
				} else {
					hql.append(" and ").append(ename).append(".outerId = :outerId ");
				}
			}
			if (params.containsKey("mobile")) {
				Object mobile = params.get("mobile");
				if (mobile == null) {
					hql.append(" and ").append(ename).append(".mobile is null");
					params.remove("mobile");
				} else {
					hql.append(" and ").append(ename).append(".mobile = :mobile ");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findInventoryCurrentStockHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "inventoryCurrentStock";
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findOrderHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "order";
		hql.append(" from com.vix.ebusiness.entity.Order ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("outerId")) {
				Object outerId = params.get("outerId");
				if (outerId == null) {
					hql.append(" and ").append(ename).append(".outerId is null");
					params.remove("outerId");
				} else {
					hql.append(" and ").append(ename).append(".outerId = :outerId ");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findOrderDetailHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "orderDetail";
		hql.append(" from com.vix.ebusiness.entity.OrderDetail ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("outerGoodsId")) {
				Object outerGoodsId = params.get("outerGoodsId");
				if (outerGoodsId == null) {
					hql.append(" and ").append(ename).append(".outerGoodsId is null");
					params.remove("outerGoodsId");
				} else {
					hql.append(" and ").append(ename).append(".outerGoodsId = :outerGoodsId ");
				}
			}
			if (params.containsKey("orderId")) {
				Object orderId = params.get("orderId");
				if (orderId == null) {
					hql.append(" and ").append(ename).append(".order.id is null");
					params.remove("orderId");
				} else {
					hql.append(" and ").append(ename).append(".order.id = :orderId ");
				}
			}
		}
		return hql;
	}

	@Override
	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return buffer.toString();
	}

	private Date getCreateDate(String dateStr) {
		try {
			if (StringUtils.isNotEmpty(dateStr)) {
				dateStr += " " + sdf.format(new Date());
				System.out.println(dateStr);
				System.out.println(simpleDateFormat.parse(dateStr));
				return simpleDateFormat.parse(dateStr);
			}
			return new Date();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/** 解码 */
	@Override
	public String decode(String str, String enc) throws Exception {
		str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
		str = URLDecoder.decode(str, enc);
		return str;
	}

	@Override
	public void writeClientMsg(HttpServletResponse response, final String content) {
		String fullContentType = "application/json;charset=UTF-8";
		response.setContentType(fullContentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				if (StringUtils.isNotEmpty(content)) {
					out.write(content);
				}
				out.flush();
				out.close();
			}
		}
	}
}
