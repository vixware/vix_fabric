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
import com.vix.common.vixlog.IOperateLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.item.entity.ItemBrand;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.restService.shop.service.IItemCurrencyDateRestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 品牌
 * 
 * @类全名 com.rest.shop.ItemBrandController
 *
 * @author Bluesnow
 *
 * @date 2016年9月05日
 */

@Controller
@RequestMapping(value = "restService/itemBrand")
public class ItemBrandController extends BaseRestController {

	// 商品通用数据service
	@Autowired
	private IItemCurrencyDateRestService itemCurrencyDateRestService;
	@Autowired
	private IVixntBaseService vixntBaseService;
	// 日志
	@Autowired
	public IOperateLog vixOperateLog;

	@RequestMapping(value = "upDownItemBrand", method = RequestMethod.POST)
	@ResponseBody
	public void upDownItemBrand(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject returnJson = new JSONObject();
		JSONArray ja = new JSONArray();
		List<ItemBrand> resList = new ArrayList<ItemBrand>();
		String channeldistributorcode = request.getParameter("CHANNELDISTRIBUTORCODE");
		if (StringUtils.isNotEmpty(channeldistributorcode)) {
			ChannelDistributor channelDistributor = itemCurrencyDateRestService.findEntityByAttribute(ChannelDistributor.class, "code", channeldistributorcode);
			if (null != channelDistributor) {
				resList = vixntBaseService.findAllByEntityClass(ItemBrand.class);
				if (null != resList && resList.size() > 0) {
					writeClientMsg(response, convertListToJson(resList, resList.size()));
				} else {
					JSONObject jo = new JSONObject();
					jo.put("MESSAGE", "品牌信息为空，请确认品牌信息是否维护!");
					jo.put("SUCCESS", false);
					jo.put("ORDERID", "");
					ja.add(jo);
					returnJson.put("RETURNVALUE", ja);
					writeClientMsg(response, returnJson.toString());
				}

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
}