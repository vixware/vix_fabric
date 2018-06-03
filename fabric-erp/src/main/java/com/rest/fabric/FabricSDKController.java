package com.rest.fabric;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.core.utils.StrUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 区块链接口
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.rest.fabric.FabricSDKController
 *
 * @date 2018年4月19日
 */
@Controller
@RequestMapping(value = "restService/fabricSDK")
public class FabricSDKController extends BaseRestController {

	@RequestMapping(value = "instantiateChainCode", method = RequestMethod.POST)
	@ResponseBody
	public void upDownCurrencyType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = this.readStreamParameter(request.getInputStream());
		if (StringUtils.isNotEmpty(data)) {
			System.out.println(data);
			JSONObject dataJson = JSONObject.fromObject(data);
			if (dataJson.has("dataList")) {
				JSONArray dataJSONArray = dataJson.getJSONArray("dataList");
				JSONArray ja = new JSONArray();
				JSONObject returnJson = new JSONObject();
				for (int i = 0; i < dataJSONArray.size(); i++) {
					JSONObject json = dataJSONArray.getJSONObject(i);
					JSONObject jo = new JSONObject();
					String arg0 = null;
					if (json.has("arg0")) {
						arg0 = json.getString("arg0").trim();
					}
					if (StrUtils.objectIsNull(arg0)) {
						jo.put("MESSAGE", "操作失败!");
						jo.put("SUCCESS", false);
						ja.add(jo);
						continue;
					}
					String arg2 = null;
					if (json.has("arg2")) {
						arg2 = json.getString("arg2").trim();
					}
					if (StrUtils.objectIsNull(arg2)) {
						jo.put("MESSAGE", "操作失败!");
						jo.put("SUCCESS", false);
						ja.add(jo);
						continue;
					}
					String TESTUSER_1_NAME = null;
					if (json.has("TESTUSER_1_NAME")) {
						TESTUSER_1_NAME = json.getString("TESTUSER_1_NAME").trim();
					}
					if (StrUtils.objectIsNull(TESTUSER_1_NAME)) {
						jo.put("MESSAGE", "操作失败!");
						jo.put("SUCCESS", false);
						ja.add(jo);
						continue;
					}
				}
				returnJson.put("RETURNVALUE", ja);
				System.out.println(returnJson.toString());
				writeClientMsg(response, returnJson.toString());
			}
		}
	}
}