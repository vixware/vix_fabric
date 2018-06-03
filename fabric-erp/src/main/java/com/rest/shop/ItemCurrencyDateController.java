package com.rest.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.common.vixlog.IOperateLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.item.entity.Item;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.restService.shop.service.IItemCurrencyDateRestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 商品
 * 
 * @类全名 com.rest.shop.ItemCurrencyDateController
 *
 * @author Bluesnow
 *
 * @date 2016年9月05日
 */

@Controller
@RequestMapping(value = "restService/itemCurrencyDate")
public class ItemCurrencyDateController extends BaseRestController {

	// 商品通用数据service
	@Autowired
	private IItemCurrencyDateRestService itemCurrencyDateRestService;
	@Autowired
	private IVixntBaseService vixntBaseService;
	// 日志
	@Autowired
	public IOperateLog vixOperateLog;

	@RequestMapping(value = "upDownItem", method = RequestMethod.POST)
	@ResponseBody
	public void upDownItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		JSONObject returnJson = new JSONObject();
		JSONArray ja = new JSONArray();
		List<Item> resList = new ArrayList<Item>();
		String channeldistributorcode = request.getParameter("CHANNELDISTRIBUTORCODE");
		if (StringUtils.isNotEmpty(channeldistributorcode)) {
			ChannelDistributor channelDistributor = itemCurrencyDateRestService.findEntityByAttribute(ChannelDistributor.class, "code", channeldistributorcode);
			if (null != channelDistributor) {
				params.put("channelDistributorId", channelDistributor.getId());
				StringBuilder hql = findItemHql(params);
				List<Item> itemList = vixntBaseService.findAllItemByHql(hql.toString(), params);
				if (null != itemList && itemList.size() > 0) {
					for (Item item : itemList) {
						Item itemTemp = new Item();
						BeanUtils.copyProperties(item, itemTemp, new String[]{"objectExpand", "currencyType", "measureUnitGroup", "measureUnit", "assitMeasureUnit", "itemBrand", "itemGroup", "itemCostProperties", "itemGernalProperties", "itemPurchaseProperties", "itemSaleProperties", "itemInventoryProperties", "itemMRPProperties", "itemPackageProperties", "itemPlanProperties", "itemQualityProperties"});
						resList.add(itemTemp);
					}
				} else {
					JSONObject jo = new JSONObject();
					jo.put("MESSAGE", "商品信息为空,请确认商品信息是否维护!");
					jo.put("SUCCESS", false);
					jo.put("ORDERID", "");
					ja.add(jo);
					returnJson.put("RETURNVALUE", ja);
					writeClientMsg(response, returnJson.toString());
				}
				writeClientMsg(response, convertListToJson(resList, resList.size()));
			} else {
				JSONObject jo = new JSONObject();
				jo.put("MESSAGE", "操作失败!没有找到该门店!");
				jo.put("SUCCESS", false);
				jo.put("ORDERID", "");
				ja.add(jo);
				returnJson.put("RETURNVALUE", ja);
				writeClientMsg(response, returnJson.toString());
			}
		} else {
			JSONObject jo = new JSONObject();
			jo.put("MESSAGE", "操作失败!缺少门店编码.");
			jo.put("SUCCESS", false);
			jo.put("ORDERID", "");
			ja.add(jo);
			returnJson.put("RETURNVALUE", ja);
			writeClientMsg(response, returnJson.toString());
		}
	}

	private StringBuilder findItemHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "item";
		hql.append(" from Item ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors as ch");
		hql.append(" where 1=1 ");
		if (params.containsKey("channelDistributorId")) {
			Object channelDistributorId = params.get("channelDistributorId");
			if (channelDistributorId == null) {
				hql.append(" and ").append("ch.id is null");
				params.remove("channelDistributorId");
			} else {
				hql.append(" and ").append("ch.id = :channelDistributorId ");
			}
		}
		return hql;
	}

}