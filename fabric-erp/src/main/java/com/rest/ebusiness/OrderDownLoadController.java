package com.rest.ebusiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vix.common.code.AutoCreateCode;
import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.wechat.base.service.IWechatBaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.ebusiness.OrderConfirmController
 *
 * @author zhanghaibing
 *
 * @date 2016年9月25日
 */

@Controller
@RequestMapping(value = "restService/orderDownLoad")
public class OrderDownLoadController extends NvixBaseController {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	public IWechatBaseService wechatBaseService;
	@Autowired
	public AutoCreateCode autoCreateCode;

	@RequestMapping(value = "orderDownLoad", method = RequestMethod.POST)
	@ResponseBody
	public void orderDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		System.out.println(orderData);
		if (StringUtils.isNotEmpty(orderData)) {
			JSONObject orderDataJson = JSONObject.fromObject(orderData);
			JSONObject returnJson = new JSONObject();
			String channelDistributorCode = null;
			if (orderDataJson.has("channeldistributorcode")) {
				channelDistributorCode = orderDataJson.getString("channeldistributorcode").trim();
			}
			ChannelDistributor channelDistributor = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "code", channelDistributorCode);
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			if (StrUtils.objectIsNull(channelDistributorCode)) {
				jo.put("message", "操作失败!缺少门店编码.");
				jo.put("success", false);
				ja.add(jo);
				returnJson.put("returnvalue", ja);
				writeClientMsg(response, returnJson.toString());
				return;
			}
			if (channelDistributor == null) {
				jo.put("message", "操作失败!门店不存在.");
				jo.put("success", false);
				ja.add(jo);
				returnJson.put("returnvalue", ja);
				writeClientMsg(response, returnJson.toString());
				return;
			}
			if (orderDataJson.has("orderdatalist")) {
				JSONArray orderDataJSONArray = orderDataJson.getJSONArray("orderdatalist");
				for (int i = 0; i < orderDataJSONArray.size(); i++) {
					// 要货单json
					JSONObject json = orderDataJSONArray.getJSONObject(i);
					// 要货单编码
					String code = json.getString("code").trim();
					String deliveryTime = json.getString("deliveryTime").trim();
					RequireGoodsOrder requireGoodsOrder = null;
					if (json.has("code")) {
						if (StringUtils.isNotEmpty(code)) {
							requireGoodsOrder = submoduleRestService.findEntityByAttributeNoTenantId(RequireGoodsOrder.class, "code", code);
							if (requireGoodsOrder == null) {
								requireGoodsOrder = new RequireGoodsOrder();
								requireGoodsOrder.setCode(code);
								requireGoodsOrder.setName("移动端要货单" + deliveryTime);
							} else {
								jo.put("message", "操作失败! " + code + " 订单已存在.");
								jo.put("success", false);
								jo.put("ordercode", code);
								ja.add(jo);
								continue;
							}
						}
					}
					if (channelDistributor != null) {
						requireGoodsOrder.setTenantId(channelDistributor.getTenantId());
						requireGoodsOrder.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
						requireGoodsOrder.setChannelDistributor(channelDistributor);
						requireGoodsOrder.setDeliveryTime(sdf.parse(deliveryTime));
					}
					// 保存要货单主体
					requireGoodsOrder.setStatus("0");
					requireGoodsOrder.setIsSettlement("1");
					requireGoodsOrder = submoduleRestService.mergeOriginal(requireGoodsOrder);
					Double totalAmount = 0d;
					if (json.has("orderdetaildata")) {
						String orderDetailData = json.getString("orderdetaildata");
						if (StringUtils.isNotEmpty(orderDetailData)) {
							JSONArray ecOrderItemList = JSONArray.fromObject(orderDetailData);
							for (int j = 0; j < ecOrderItemList.size(); j++) {
								RequireGoodsOrderItem requireGoodsOrderItem = null;
								JSONObject o = ecOrderItemList.getJSONObject(j);
								if (o.has("itemcode")) {
									Map<String, Object> itemparams = new HashMap<String, Object>();
									itemparams.put("itemCode", o.getString("itemcode"));
									itemparams.put("requireGoodsOrderId", requireGoodsOrder.getId());
									StringBuilder itemhql = standingBookHqlProvider.findRequireGoodsOrderItemByItemCode(itemparams);
									requireGoodsOrderItem = submoduleRestService.findObjectByHqlNoTenantId(itemhql.toString(), itemparams);
									if (requireGoodsOrderItem == null) {
										requireGoodsOrderItem = new RequireGoodsOrderItem();
										requireGoodsOrderItem.setItemCode(o.getString("itemcode"));// 商品编码
									}

									Map<String, Object> par = new HashMap<String, Object>();
									par.put("code", o.getString("itemcode"));
									par.put("channelDistributorId", channelDistributor.getId());
									StringBuilder phql = standingBookHqlProvider.findStoreItemByItemCode(par);
									StoreItem storeItem = submoduleRestService.findObjectByHqlNoTenantId(phql.toString(), par);
									if (storeItem != null) {
										if (StringUtils.isNotEmpty(storeItem.getSupplierId())) {
											Supplier supplier = submoduleRestService.findEntityByAttributeNoTenantId(Supplier.class, "id", storeItem.getSupplierId());
											if (supplier != null) {
												requireGoodsOrderItem.setSupplier(supplier);
												requireGoodsOrder.setSupplier(supplier);
												requireGoodsOrder = submoduleRestService.mergeOriginal(requireGoodsOrder);
											}
										}
									}
								}
								if (o.has("itemname")) {
									requireGoodsOrderItem.setTitle(o.getString("itemname"));// 商品名称
								}
								if (o.has("price")) {
									requireGoodsOrderItem.setPrice(Double.parseDouble(o.getString("price")));
								}
								// 商品数量
								if (o.has("amount")) {
									requireGoodsOrderItem.setAmount(Double.parseDouble(o.getString("amount")));
								}
								requireGoodsOrderItem.setRequireGoodsOrder(requireGoodsOrder);
								if (channelDistributor != null) {
									requireGoodsOrderItem.setTenantId(channelDistributor.getTenantId());
									requireGoodsOrderItem.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
								}
								if (requireGoodsOrderItem.getAmount() != null && requireGoodsOrderItem.getPrice() != null) {
									requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getAmount() * requireGoodsOrderItem.getPrice());
								}
								requireGoodsOrderItem.setCreateTime(new Date());
								requireGoodsOrderItem = submoduleRestService.mergeOriginal(requireGoodsOrderItem);
								if (requireGoodsOrderItem.getPrice() != null && requireGoodsOrderItem.getAmount() != null) {
									totalAmount += requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getAmount();
								}
							}
						}
					}
					requireGoodsOrder.setAmount(totalAmount);
					requireGoodsOrder = submoduleRestService.mergeOriginal(requireGoodsOrder);
					jo.put("message", "操作成功! " + code + " 门店订单上传成功.");
					jo.put("success", true);
					jo.put("ordercode", code);
					ja.add(jo);
					collectRequireGoodsOrder(requireGoodsOrder, channelDistributor);
				}
				returnJson.put("returnvalue", ja);
				writeClientMsg(response, returnJson.toString());
			}
		}
	}

	/**
	 * 要货单拆单
	 */
	public void collectRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder, ChannelDistributor channelDistributor) {
		try {
			Map<String, List<RequireGoodsOrderItem>> map = new HashMap<String, List<RequireGoodsOrderItem>>();
			if (requireGoodsOrder != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("requireGoodsOrder.id", requireGoodsOrder.getId());
				List<RequireGoodsOrderItem> requireGoodsOrderItemList = wechatBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, params);
				if (requireGoodsOrderItemList != null && requireGoodsOrderItemList.size() > 0) {
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
						if (requireGoodsOrderItem != null) {
							Map<String, Object> par = new HashMap<String, Object>();
							par.put("code", requireGoodsOrderItem.getItemCode());
							par.put("channelDistributorId", channelDistributor.getId());
							StringBuilder phql = standingBookHqlProvider.findStoreItemByItemCode(par);
							StoreItem storeItem = submoduleRestService.findObjectByHqlNoTenantId(phql.toString(), par);
							String supplierId = "";
							if (storeItem != null && StringUtils.isNotEmpty(storeItem.getSupplierId())) {
								Supplier supplier = submoduleRestService.findEntityByAttributeNoTenantId(Supplier.class, "id", storeItem.getSupplierId());
								if (supplier != null) {
									supplierId = supplier.getId();
								}
							}
							if (map.containsKey(supplierId)) {
								map.get(supplierId).add(requireGoodsOrderItem);
							} else {
								List<RequireGoodsOrderItem> l = new ArrayList<RequireGoodsOrderItem>();
								l.add(requireGoodsOrderItem);
								map.put(supplierId, l);
							}
						}
					}
				}
				dealBill(channelDistributor, map, requireGoodsOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param channelDistributor
	 * @param map
	 * @throws Exception
	 */
	private void dealBill(ChannelDistributor channelDistributor, Map<String, List<RequireGoodsOrderItem>> map, RequireGoodsOrder requireGoodsOrder) throws Exception {
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, List<RequireGoodsOrderItem>> entry : map.entrySet()) {
				Supplier supplier = submoduleRestService.findEntityByAttributeNoTenantId(Supplier.class, "id", entry.getKey());
				
				PurchaseOrder purchaseOrder = null;
				if (supplier != null) {
					purchaseOrder = submoduleRestService.findEntityByAttributeNoTenantId(PurchaseOrder.class, "code", supplier.getChineseCharacter() + sdf.format(new Date()));
					if (purchaseOrder != null) {

					} else {
						purchaseOrder = new PurchaseOrder();
						purchaseOrder.setName("订单" + dateFormat.format(new Date()));
						purchaseOrder.setStatus("4");
						if (supplier != null) {
							purchaseOrder.setSupplier(supplier);
							purchaseOrder.setCode(supplier.getChineseCharacter() + sdf.format(new Date()));
						}
						purchaseOrder.setCreateTime(new Date());
						purchaseOrder.setPurchasePerson(channelDistributor.getName());
						purchaseOrder.setChannelDistributor(channelDistributor);
						purchaseOrder.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
						purchaseOrder.setTenantId(channelDistributor.getTenantId());
						purchaseOrder = submoduleRestService.mergeOriginal(purchaseOrder);
					}
				}
				List<RequireGoodsOrderItem> requireGoodsOrderItemList = entry.getValue();
				for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
					if (requireGoodsOrderItem != null) {
						Map<String, Object> p = new HashMap<String, Object>();
						PurchaseOrderLineItem purchaseOrderLineItem = submoduleRestService.findObjectByHqlNoTenantId("select purchaseOrderLineItem from PurchaseOrderLineItem purchaseOrderLineItem where itemCode ='" + requireGoodsOrderItem.getItemCode() + "' and purchaseOrderLineItem.purchaseOrder.id ='" + purchaseOrder.getId() + "'", p);
						if (purchaseOrderLineItem != null) {
							purchaseOrderLineItem.setAmount(purchaseOrderLineItem.getAmount() + requireGoodsOrderItem.getAmount());
							purchaseOrderLineItem = submoduleRestService.mergeOriginal(purchaseOrderLineItem);
						} else {
							purchaseOrderLineItem = new PurchaseOrderLineItem();
							purchaseOrderLineItem.setItemCode(requireGoodsOrderItem.getItemCode());
							purchaseOrderLineItem.setItemName(requireGoodsOrderItem.getTitle());
							purchaseOrderLineItem.setSkuCode(requireGoodsOrderItem.getSkuCode());
							purchaseOrderLineItem.setPrice(requireGoodsOrderItem.getPrice());
							purchaseOrderLineItem.setAmount(requireGoodsOrderItem.getAmount());
							purchaseOrderLineItem.setUnit(requireGoodsOrderItem.getUnit());
							purchaseOrderLineItem.setCreateTime(new Date());
							purchaseOrderLineItem.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
							purchaseOrderLineItem.setTenantId(channelDistributor.getTenantId());
							if (purchaseOrder != null) {
								purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
							}
							purchaseOrderLineItem = submoduleRestService.mergeOriginal(purchaseOrderLineItem);
						}
					}
				}
				purchaseOrder.setRequireGoodsOrder(requireGoodsOrder);
				purchaseOrder = submoduleRestService.mergeOriginal(purchaseOrder);
			}
		}
	}
}