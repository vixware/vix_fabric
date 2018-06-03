package com.rest.ebusiness;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.item.entity.StoreItem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.ebusiness.ItemPurchasePriceUpdateController
 *
 * @author zhanghaibing
 *
 * @date 2016年11月3日
 */

@Controller
@RequestMapping(value = "restService/itemPurchasePriceUpdate")
public class ItemPurchasePriceUpdateController extends NvixBaseController {
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	@RequestMapping(value = "itemPurchasePriceUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void itemPurchasePriceUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		System.out.println(orderData);
		if (StringUtils.isNotEmpty(orderData)) {
			JSONObject orderDataJson = JSONObject.fromObject(orderData);
			String channelDistributorCode = null;
			if (orderDataJson.has("channeldistributorcode")) {
				channelDistributorCode = orderDataJson.getString("channeldistributorcode").trim();
			}
			ChannelDistributor channelDistributor = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "code", channelDistributorCode);
			JSONObject returnJson = new JSONObject();
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			if (StrUtils.objectIsNull(channelDistributorCode)) {
				jo.put("message", "操作失败!缺少门店编码.");
				jo.put("success", false);
				ja.add(jo);
			}
			if (channelDistributor == null) {
				jo.put("message", "操作失败!门店不存在.");
				jo.put("success", false);
				ja.add(jo);
			}
			if (orderDataJson.has("itemdatalist")) {
				String itemdata = orderDataJson.getString("itemdatalist");
				if (StringUtils.isNotEmpty(itemdata)) {
					JSONArray ecOrderItemList = JSONArray.fromObject(itemdata);
					for (int j = 0; j < ecOrderItemList.size(); j++) {
						JSONObject json = new JSONObject();
						JSONObject o = ecOrderItemList.getJSONObject(j);
						if (o.has("itemcode")) {
							StoreItem t = null;
							Map<String, Object> itemparams = new HashMap<String, Object>();
							itemparams.put("code", o.getString("itemcode"));
							itemparams.put("channelDistributorId", channelDistributor.getId());
							StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
							t = submoduleRestService.findObjectByHqlNoTenantId(itemhql.toString(), itemparams);
							if (t != null) {
								if (o.has("price")) {
									t.setPrice(Double.parseDouble(o.getString("price")));
								}
								// 修改采购价格
								t = submoduleRestService.mergeOriginal(t);
								json.put("message", "操作成功! " + o.getString("itemcode") + " 商品采购价格修改成功.");
								json.put("success", true);
								json.put("itemcode", o.getString("itemcode"));
								ja.add(json);
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