package com.rest.ebusiness;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vix.common.org.entity.Organization;
import com.vix.core.utils.StrUtils;
import com.vix.crm.member.entity.MemberTag;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.ebusiness.CustomerTagUpLoadController
 *
 * @author zhanghaibing
 *
 * @date 2016年11月11日
 */

@Controller
@RequestMapping(value = "restService/customerTagUpLoad")
public class CustomerTagUpLoadController extends NvixBaseController {
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	@RequestMapping(value = "customerTagUpLoad", method = RequestMethod.POST)
	@ResponseBody
	public void customerTagUpLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		System.out.println(orderData);
		if (StringUtils.isNotEmpty(orderData)) {
			JSONObject orderDataJson = JSONObject.fromObject(orderData);
			String tenantid = null;
			if (orderDataJson.has("tenantid")) {
				tenantid = orderDataJson.getString("tenantid").trim();
			}
			Organization organization = submoduleRestService.findEntityByAttributeNoTenantId(Organization.class, "tenantId", tenantid);
			JSONObject returnJson = new JSONObject();
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			if (StrUtils.objectIsNull(tenantid)) {
				jo.put("message", "操作失败!缺少承租户编码.");
				jo.put("success", false);
				ja.add(jo);
				returnJson.put("returnvalue", ja);
				writeClientMsg(response, returnJson.toString());
				return;
			}
			if (organization == null) {
				jo.put("message", "操作失败!承租户不存在.");
				jo.put("success", false);
				ja.add(jo);
				returnJson.put("returnvalue", ja);
				writeClientMsg(response, returnJson.toString());
				return;
			}
			if (orderDataJson.has("code")) {
				JSONObject json = new JSONObject();
				MemberTag memberTag = submoduleRestService.findEntityByAttributeNoTenantId(MemberTag.class, "code", orderDataJson.getString("code"));
				if (memberTag == null) {
					memberTag = new MemberTag();
					memberTag.setCode(orderDataJson.getString("code"));
					memberTag.setName(orderDataJson.getString("name"));
					memberTag.setTenantId(tenantid);
					memberTag.setCompanyInnerCode(organization.getCompanyInnerCode());
					memberTag = submoduleRestService.mergeOriginal(memberTag);
					json.put("message", "操作成功! " + orderDataJson.getString("code") + " 会员标签成功.");
					json.put("success", true);
					json.put("code", orderDataJson.getString("code"));
					ja.add(json);
					returnJson.put("returnvalue", ja);
					writeClientMsg(response, returnJson.toString());
				} else {
					json.put("message", "操作失败! " + orderDataJson.getString("code") + " 会员标签已经存在.");
					json.put("success", true);
					json.put("code", orderDataJson.getString("code"));
					ja.add(json);
					returnJson.put("returnvalue", ja);
					writeClientMsg(response, returnJson.toString());
				}
			}
		}
	}
}
