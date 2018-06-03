package com.rest.shop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.vixlog.IOperateLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.restService.shop.service.IItemCurrencyDateRestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 币种
 * 
 * @类全名 com.rest.shop.CurrenTypeController
 *
 * @author Bluesnow
 *
 * @date 2016年9月05日
 */

@Controller
@RequestMapping(value = "restService/currencyType")
public class CurrenTypeController extends BaseRestController {

	// 商品通用数据service
	@Autowired
	private IItemCurrencyDateRestService itemCurrencyDateRestService;
	@Autowired
	private IVixntBaseService vixntBaseService;
	// 日志
	@Autowired
	public IOperateLog vixOperateLog;

	@RequestMapping(value = "upDownCurrencyType", method = RequestMethod.POST)
	@ResponseBody
	public void upDownCurrencyType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject returnJson = new JSONObject();
		JSONArray ja = new JSONArray();
		List<CurrencyType> resList = new ArrayList<CurrencyType>();
		String channeldistributorcode = request.getParameter("CHANNELDISTRIBUTORCODE");
		// String itemCatalogId = request.getParameter("ItemCatalogId");
		if (StringUtils.isNotEmpty(channeldistributorcode)) {
			ChannelDistributor channelDistributor = itemCurrencyDateRestService.findEntityByAttribute(ChannelDistributor.class, "code", channeldistributorcode);
			if (null != channelDistributor) {
				resList = vixntBaseService.findAllByEntityClass(CurrencyType.class);
				if (null != resList && resList.size() > 0) {
					writeClientMsg(response, convertListToJson(resList, resList.size()));
				} else {
					JSONObject jo = new JSONObject();
					jo.put("MESSAGE", "币种信息为空，请确认币种信息是否维护!");
					jo.put("SUCCESS", false);
					jo.put("ORDERID", "");
					ja.add(jo);
					returnJson.put("RETURNVALUE", ja);
					writeClientMsg(response, returnJson.toString());
				}
				/*
				 * if(StringUtils.isNotEmpty(itemCatalogId)){
				 * params.put("ItemCatalogId", itemCatalogId); StringBuilder hql
				 * = findItemCatalogHql(params); List<ItemCatalog> itemList =
				 * vixntBaseService.findAllItemCatalogByHql(hql.toString(),
				 * params); if(null != itemList && itemList.size() > 0){
				 * for(ItemCatalog item : itemList){ ItemCatalog itemTemp = new
				 * ItemCatalog(); BeanUtils.copyProperties(item, itemTemp,new
				 * String[]{"objectExpand","currencyType","measureUnitGroup",
				 * "measureUnit", "assitMeasureUnit","itemBrand","itemGroup",
				 * "itemCostProperties","itemGernalProperties",
				 * "itemPurchaseProperties",
				 * "itemSaleProperties","itemInventoryProperties",
				 * "itemMRPProperties","itemPackageProperties",
				 * "itemPlanProperties", "itemQualityProperties"});
				 * resList.add(itemTemp); } }
				 * writeClientMsg(response,convertListToJson(resList,
				 * resList.size())); } else { JSONObject jo = new JSONObject();
				 * jo.put("MESSAGE", "操作失败!缺少分类编码!"); jo.put("SUCCESS", false);
				 * jo.put("ORDERID", ""); ja.put(jo);
				 * returnJson.put("RETURNVALUE", ja); writeClientMsg(response,
				 * returnJson.toCompactString()); }
				 */
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
			jo.put("MESSAGE", "操作失败!缺少门店编码!");
			jo.put("SUCCESS", false);
			jo.put("ORDERID", "");
			ja.add(jo);
			returnJson.put("RETURNVALUE", ja);
			writeClientMsg(response, returnJson.toString());
		}

	}

}