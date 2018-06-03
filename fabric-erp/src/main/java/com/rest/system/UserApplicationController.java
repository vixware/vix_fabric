package com.rest.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.common.org.entity.UserApplication;

import net.sf.json.JSONObject;

/**
 * 处理网站用户申请
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.rest.system.UserApplicationController
 *
 * @date 2018年1月11日
 */

@Controller
@RequestMapping(value = "restService/userApplication")
public class UserApplicationController extends BaseRestController {

	@RequestMapping(value = "saveAndUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void saveAndUpdate(HttpServletRequest request, HttpServletResponse response) {
		String userApplicationData = request.getParameter("userApplicationData");
		if (StringUtils.isNotEmpty(userApplicationData)) {
			JSONObject userApplicationJson = JSONObject.fromObject(userApplicationData);
			JSONObject returnJson = new JSONObject();
			UserApplication userApplication = null;
			String orgName = null;
			if (userApplicationJson.has("orgName")) {
				try {
					orgName = userApplicationJson.getString("orgName");
					userApplication = userAccountService.findEntityByAttributeNoTenantId(UserApplication.class, "orgName", orgName);
				} catch (Exception e) {
					returnJson.put("MESSAGE", "操作失败! " + e.getLocalizedMessage());
					returnJson.put("SUCCESS", false);
					writeClientMsg(response, returnJson.toString());
				}
			}
			String address = null;
			if (userApplicationJson.has("address")) {
				address = userApplicationJson.getString("address");
			}
			String industry = null;
			if (userApplicationJson.has("industry")) {
				industry = userApplicationJson.getString("industry");
			}
			String companyOrgContact = null;
			if (userApplicationJson.has("companyOrgContact")) {
				companyOrgContact = userApplicationJson.getString("companyOrgContact");
			}
			String telephone = null;
			if (userApplicationJson.has("telephone")) {
				telephone = userApplicationJson.getString("telephone");
			}
			String email = null;
			if (userApplicationJson.has("email")) {
				email = userApplicationJson.getString("email");
			}
			if (userApplication != null) {
				returnJson.put("MESSAGE", "操作失败! " + orgName + " 该用户已经申请.");
				returnJson.put("SUCCESS", false);
				writeClientMsg(response, returnJson.toString());
			} else {
				userApplication = new UserApplication();
				userApplication.setOrgName(orgName);
				userApplication.setAddress(address);
				userApplication.setIndustry(industry);
				userApplication.setCompanyOrgContact(companyOrgContact);
				userApplication.setTelephone(telephone);
				userApplication.setEmail(email);
				userApplication = dealUserApplication(userApplication);
				if (userApplication != null) {
					returnJson.put("MESSAGE", "操作成功! " + orgName + " 的信息填写成功.");
					returnJson.put("SUCCESS", true);
					writeClientMsg(response, returnJson.toString());
				}
			}
		}
	}
	@RequestMapping(value = "getUserApplication", method = RequestMethod.GET)
	public void getUserApplication(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			UserApplication userApplication = userAccountService.findEntityByAttributeNoTenantId(UserApplication.class, "id", id);
			if (userApplication != null) {
				JSONObject returnJson = new JSONObject();
				returnJson.put("id", userApplication.getId());
				returnJson.put("orgName", userApplication.getOrgName());
				writeClientMsg(response, returnJson.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserApplication dealUserApplication(UserApplication userApplication) {
		try {
			userApplication = userAccountService.mergeOriginal(userApplication);
			return userApplication;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}