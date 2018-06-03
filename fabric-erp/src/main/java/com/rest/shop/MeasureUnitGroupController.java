package com.rest.shop;

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
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.constant.SearchCondition;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.restService.shop.service.IItemCurrencyDateRestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 计量单位组
 * 
 * @类全名 com.rest.shop.MeasureUnitGroupController
 *
 * @author Bluesnow
 *
 * @date 2016年9月05日
 */

@Controller
@RequestMapping(value = "restService/measureUnitGroup")
public class MeasureUnitGroupController extends BaseRestController {

	// 商品通用数据service
	@Autowired
	private IItemCurrencyDateRestService itemCurrencyDateRestService;
	@Autowired
	private IVixntBaseService vixntBaseService;
	// 日志
	@Autowired
	public IOperateLog vixOperateLog;

	@RequestMapping(value = "upDownMeasureUnitGroup", method = RequestMethod.POST)
	@ResponseBody
	public void upDownMeasureUnitGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		JSONObject returnJson = new JSONObject();
		JSONArray ja = new JSONArray();
		String channeldistributorcode = request.getParameter("CHANNELDISTRIBUTORCODE");
		// String itemCatalogId = request.getParameter("ItemCatalogId");
		if (StringUtils.isNotEmpty(channeldistributorcode)) {
			ChannelDistributor channelDistributor = itemCurrencyDateRestService.findEntityByAttribute(ChannelDistributor.class, "code", channeldistributorcode);
			if (null != channelDistributor) {
				params.put("isTemp," + SearchCondition.NOEQUAL, "1");
				List<MeasureUnitGroup> groupList = vixntBaseService.findAllByConditions(MeasureUnitGroup.class, params);
				JSONObject json = new JSONObject();
				if (null != groupList && groupList.size() > 0) {
					JSONArray jsonarray = new JSONArray();
					for (MeasureUnitGroup measureUnitGroup : groupList) {
						if (null != measureUnitGroup && StringUtils.isNotEmpty(measureUnitGroup.getId())) {
							JSONObject json1 = new JSONObject();
							MeasureUnitGroup groupTemp = new MeasureUnitGroup();
							BeanUtils.copyProperties(measureUnitGroup, groupTemp, new String[]{"organization", "measureUnits"});
							JSONObject jsonObject = JSONObject.fromObject(groupTemp);
							json1.put("unitgroup", jsonObject.toString());
							Map<String, Object> param = new HashMap<String, Object>();
							params.put("measureUnitGroupId", measureUnitGroup.getId());
							StringBuilder hql = findMeasureUnitHql(param);
							JSONArray jsonarray1 = new JSONArray();
							List<MeasureUnit> measureUnitList = vixntBaseService.findAllMeasureUnitByHql(hql.toString(), param);
							for (MeasureUnit measureUnit : measureUnitList) {
								MeasureUnit b = new MeasureUnit();
								BeanUtils.copyProperties(measureUnit, b, new String[]{"measureUnitGroup"});
								JSONObject j = JSONObject.fromObject(b);
								jsonarray1.add(j);
							}
							json1.put("unitarray", jsonarray1);
							jsonarray.add(json1);
						}

					}
					json.put("MeasureUnitGroupArray", jsonarray);
					writeClientMsg(response, json.toString());
				} else {
					JSONObject jo = new JSONObject();
					jo.put("MESSAGE", "计量信息为空，请确认计量信息是否维护!");
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
			jo.put("MESSAGE", "操作失败!缺少门店编码!");
			jo.put("SUCCESS", false);
			jo.put("ORDERID", "");
			ja.add(jo);
			returnJson.put("RETURNVALUE", ja);
			writeClientMsg(response, returnJson.toString());
		}
	}

	private StringBuilder findMeasureUnitHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "measureUnit";
		hql.append(" from MeasureUnit ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".measureUnitGroup as mug");
		hql.append(" where 1=1 ");
		if (params.containsKey("measureUnitGroupId")) {
			Object measureUnitGroupId = params.get("measureUnitGroupId");
			if (measureUnitGroupId == null) {
				hql.append(" and ").append("mug.id is null");
				params.remove("measureUnitGroupId");
			} else {
				hql.append(" and ").append("mug.id = :measureUnitGroupId ");
			}
		}
		return hql;
	}
}