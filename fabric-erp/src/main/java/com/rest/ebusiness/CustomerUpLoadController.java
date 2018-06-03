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
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.crm.entity.CustomerAccount;

import net.sf.json.JSONObject;

/**
 * 
 * @类全名 com.rest.ebusiness.CustomerUpLoadController
 *
 * @author zhanghaibing
 *
 * @date 2016年11月11日
 */

@Controller
@RequestMapping(value = "restService/customerUpLoad")
public class CustomerUpLoadController extends NvixBaseController {
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	@RequestMapping(value = "customerUpLoad", method = RequestMethod.POST)
	@ResponseBody
	public void customerUpLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderData = this.readStreamParameter(request.getInputStream());
		System.out.println(orderData);
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
			if (orderDataJson.has("mobilePhone")) {
				if (StringUtils.isNotEmpty(orderDataJson.getString("mobilePhone"))) {
					CustomerAccount customerAccount = submoduleRestService.findEntityByAttributeNoTenantId(CustomerAccount.class, "mobilePhone", orderDataJson.getString("mobilePhone"));
					if (customerAccount == null) {
						customerAccount = new CustomerAccount();
						customerAccount.setCode(orderDataJson.getString("code"));
						customerAccount.setName(orderDataJson.getString("name"));
						customerAccount.setMobilePhone(orderDataJson.getString("mobilePhone"));
						customerAccount.setTenantId(channelDistributor.getTenantId());
						customerAccount.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
						customerAccount = submoduleRestService.mergeOriginal(customerAccount);
						returnMsg(response, "操作成功! " + orderDataJson.getString("mobilePhone") + " 会员同步成功.", true, orderDataJson.getString("mobilePhone"));
					} else {
						returnMsg(response, "操作失败! " + orderDataJson.getString("mobilePhone") + " 会员已经存在.", false, orderDataJson.getString("mobilePhone"));
					}
				} else {
					returnMsg(response, "操作失败! 手机号必填.", false, orderDataJson.getString("mobilePhone"));
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
