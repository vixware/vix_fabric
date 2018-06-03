package com.rest.ebusiness;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vix.core.utils.StrUtils;
import com.vix.crm.member.entity.Requirement;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;

import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.ebusiness.RequirementUpLoadController
 *
 * @author zhanghaibing
 *
 * @date 2016年11月21日
 */

@Controller
@RequestMapping(value = "restService/requirementUpLoad")
public class RequirementUpLoadController extends NvixBaseController {

	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	@RequestMapping(value = "requirementUpLoad", method = RequestMethod.POST)
	@ResponseBody
	public void requirementUpLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		if (StringUtils.isNotEmpty(orderData)) {
			JSONObject orderDataJson = JSONObject.fromObject(orderData);
			String channelDistributorCode = null;
			if (orderDataJson.has("channelDistributorCode")) {
				channelDistributorCode = orderDataJson.getString("channelDistributorCode").trim();
			} else {
				returnMsg(response, "操作失败!缺少门店编码.", false, null);
				return;
			}
			ChannelDistributor channelDistributor = submoduleRestService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "code", channelDistributorCode);
			if (StrUtils.objectIsNull(channelDistributorCode)) {
				returnMsg(response, "操作失败!缺少门店编码.", false, null);
				return;
			}
			if (channelDistributor == null) {
				returnMsg(response, "操作失败!门店不存在.", false, null);
				return;
			}
			if (orderDataJson.has("content")) {
				if (StringUtils.isNotEmpty(orderDataJson.getString("content"))) {
					Requirement requirement = new Requirement();
					requirement.setContent(orderDataJson.getString("content"));
					requirement.setTenantId(channelDistributor.getTenantId());
					requirement.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
					requirement.setChannelDistributor(channelDistributor);
					requirement = submoduleRestService.mergeOriginal(requirement);
					returnMsg(response, "操作成功! 新需求提交成功.", true, null);
				} else {
					returnMsg(response, "操作失败! 需求内容必填.", false, null);
				}
			}
		}
	}

	/**
	 * 
	 * @param response
	 * @param message
	 * @param flag
	 * @param code
	 */
	private void returnMsg(HttpServletResponse response, String message, Boolean flag, String code) {
		JSONObject returnJson = new JSONObject();
		JSONObject jo = new JSONObject();
		jo.put("message", message);
		jo.put("success", flag);
		jo.put("code", code);
		returnJson.put("returnvalue", jo);
		writeClientMsg(response, returnJson.toString());
	}
}
