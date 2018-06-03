package com.rest.ebusiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.ebusiness.ItemPriceAndNumDownLoadController
 *
 * @author zhanghaibing
 *
 * @date 2016年10月17日
 */

@Controller
@RequestMapping(value = "restService/itemPriceAndNumDownLoad")
public class ItemPriceAndNumDownLoadController extends NvixBaseController {
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	@RequestMapping(value = "itemPriceAndNumDownLoad", method = RequestMethod.POST)
	@ResponseBody
	public void orderConfirm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		System.out.println(orderData);
		if (StringUtils.isNotEmpty(orderData)) {
			JSONObject orderDataJson = JSONObject.fromObject(orderData);
			String channelDistributorCode = null;
			if (orderDataJson.has("channeldistributorcode")) {
				channelDistributorCode = orderDataJson.getString("channeldistributorcode").trim();
			}
			ChannelDistributor channelDistributor = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "code", channelDistributorCode);
			if (StrUtils.objectIsNull(channelDistributorCode)) {
			}
			if (channelDistributor == null) {
			}
			JSONObject returnJson = new JSONObject();
			JSONArray ja = new JSONArray();
			if (orderDataJson.has("itemdatalist")) {
				String itemdata = orderDataJson.getString("itemdatalist");
				if (StringUtils.isNotEmpty(itemdata)) {
					JSONArray ecOrderItemList = JSONArray.fromObject(itemdata);
					for (int j = 0; j < ecOrderItemList.size(); j++) {
						JSONObject o = ecOrderItemList.getJSONObject(j);
						JSONObject jo = new JSONObject();
						if (o.has("itemcode")) {
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("itemcode", o.getString("itemcode"));

							Map<String, Object> p = new HashMap<String, Object>();
							p.put("type," + SearchCondition.EQUAL, "2");
							if (channelDistributor != null) {
								// 如果登录的员工属于经销商或门店
								p.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
							}
							List<InvWarehouse> invWarehouseList = submoduleRestService.findAllByConditions(InvWarehouse.class, p);
							if (invWarehouseList != null && invWarehouseList.size() > 0) {
								for (InvWarehouse invWarehouse : invWarehouseList) {
									if ("0".equals(invWarehouse.getIsDefault())) {
										params.put("invWarehouseId", invWarehouse.getId());
									}
								}
							}
							StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockByItemcode(params);
							InventoryCurrentStock inventoryCurrentStock = submoduleRestService.findObjectByHqlNoTenantId(hql.toString(), params);
							if (inventoryCurrentStock != null) {
								jo.put("itemcode", o.getString("itemcode"));
								jo.put("price", inventoryCurrentStock.getPrice());
								jo.put("num", inventoryCurrentStock.getAvaquantity());
								ja.add(jo);
							}
						}
					}
				}
				returnJson.put("returnvalue", ja);
			}
			writeClientMsg(response, returnJson.toString());
		}
	}
}