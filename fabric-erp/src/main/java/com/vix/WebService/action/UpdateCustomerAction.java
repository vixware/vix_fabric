package com.vix.WebService.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.WebService.hql.StoreManagementHqlProvider;
import com.vix.WebService.service.IStoreWebService;
import com.vix.common.base.action.BaseAction;
import com.vix.common.base.action.BaseSecAction;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.crm.entity.CustomerAccount;

import net.sf.json.JSONObject;

/**
 * 
 * com.vix.WebService.action.UpdateCustomerAction
 *
 * @author zhanghaibing
 *
 * @date 2014年11月6日
 */
@Controller
@Scope("prototype")
public class UpdateCustomerAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name = "storeWebService")
	private IStoreWebService storeWebService;
	@Resource(name = "storeManagementHqlProvider")
	private StoreManagementHqlProvider storeManagementHqlProvider;

	/**
	 * 
	 * @param customerjson
	 * @return
	 * @throws Exception
	 */
	public void UpdateCustomer() throws Exception {

		String customerjson = null;
		try {
			customerjson = this.readStreamParameter(BaseSecAction.getRequest().getInputStream());
			System.out.println("UpdateCustomer.customerjson: " + customerjson);
			if (StrUtils.isEmpty(customerjson)) {
				BaseSecAction.renderText("UpdateCustomer.customerjson: no data received");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BaseSecAction.renderText("UpdateCustomer.customerjson: exception_receive_stream_data");
			return;
		}

		String vipCardNo = "";
		if (customerjson != null && !"".equals(customerjson)) {
			JSONObject json = JSONObject.fromObject(customerjson);
			if (json != null && json.size() > 0) {
				{
					CustomerAccount customerAccount = new CustomerAccount();
					MemberShipCard memberShipCard = new MemberShipCard();
					if (json.has("openId")) {
						customerAccount.setCode(json.getString("openId"));
					}
					if (json.has("realName")) {
						customerAccount.setName(json.getString("realName"));
					}
					if (json.has("nickName")) {
						customerAccount.setShorterName(json.getString("nickName"));
					}
					if (json.has("memberLevel")) {
						customerAccount.setLevelId(json.getString("memberLevel"));
					}
					if (json.has("phone")) {
						customerAccount.setMobilePhone(json.getString("phone"));
					}
					if (json.has("email")) {
						customerAccount.setEmail(json.getString("email"));
					}
					if (json.has("address")) {
						customerAccount.setAddress(json.getString("address"));
					}
					if (json.has("postcode")) {
						customerAccount.setShippingAddressPostalcode(json.getString("postcode"));
					}
					if (json.has("note")) {
						customerAccount.setMemo(json.getString("note"));
					}
					if (json.has("score")) {
						customerAccount.setPoint(Double.parseDouble(json.getString("score")));

						if (json.getString("score") != null && !"".equals(json.getString("score"))) {
							memberShipCard.setPoint(Long.parseLong(json.getString("score")));
						} else {
							memberShipCard.setPoint(0L);
						}

					}
					if (json.has("scoreSum")) {
					}
					if (json.has("money")) {
						if (json.getString("money") != null && !"".equals(json.getString("money"))) {
							memberShipCard.setBalance_amount(Double.parseDouble(json.getString("money")));
						} else {
							memberShipCard.setBalance_amount(0D);
						}
					}
					if (json.has("moneySum")) {
						if (json.getString("moneySum") != null && !"".equals(json.getString("moneySum"))) {
							memberShipCard.setConsume_amount(Double.parseDouble(json.getString("moneySum")));
						} else {
							memberShipCard.setConsume_amount(0D);
						}
					}
					if (json.has("userName")) {
					}
					if (json.has("password")) {
					}
					if (json.has("birthdayYear")) {
					}
					if (json.has("birthdayMonth")) {
					}
					if (json.has("birthdayDay")) {
					}
					if (json.has("idNo")) {
						customerAccount.setIdentityId(json.getString("idNo"));
					}
					if (json.has("wxId")) {
						customerAccount.setSicCode(json.getString("wxId"));
					}
					ChannelDistributor channelDistributor = null;
					List<ChannelDistributor> channelDistributorList = null;

					Map<String, Object> params = new HashMap<String, Object>();
					if (json.has("shopCode")) {
						params.put("storecode", json.getString("shopCode"));
					}
					StringBuilder hql = storeManagementHqlProvider.findChannelDistributorList(params);
					channelDistributorList = storeWebService.findEntityList(hql.toString(), params);
					if (channelDistributorList != null && channelDistributorList.size() > 0) {
						channelDistributor = channelDistributorList.get(0);
					}
					if (channelDistributor != null) {
						customerAccount.setChannelDistributor(channelDistributor);
						customerAccount.setType("personal");
						customerAccount.setVipChannel("31");
						customerAccount.setTenantId(channelDistributor.getTenantId());
						customerAccount.setCompanyCode(channelDistributor.getCompanyCode());
						customerAccount.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
						// 以9开头的卡号为总部开卡
						String vipCardId = "";
						if (channelDistributor.getCode() != null) {
							vipCardId = autoCreateCode.getBillNO(channelDistributor.getCode());
							memberShipCard.setVipcardid(vipCardId);
						}

						memberShipCard.setChannelDistributor(channelDistributor);
						memberShipCard.setTenantId(channelDistributor.getTenantId());
					}
					memberShipCard.setIsOpenCard("1");
					customerAccount = storeWebService.mergeOriginal(customerAccount);
					memberShipCard.setCustomerAccount(customerAccount);
					memberShipCard = storeWebService.mergeOriginal(memberShipCard);
					vipCardNo = memberShipCard.getVipcardid();

				}
			}
		}
		if (StrUtils.isNotEmpty(vipCardNo))
			BaseSecAction.renderText(vipCardNo);
	}

	public void getChannelDistributorList() {
		List<ChannelDistributor> channelDistributorList = null;
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = storeManagementHqlProvider.findChannelDistributorList(params);
		try {
			channelDistributorList = storeWebService.findEntityList(hql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = "";
		if (null != channelDistributorList && channelDistributorList.size() > 0) {
			json = JSonUtils.simpleEntityListToJson(channelDistributorList, "id", "code", "name");

			String callback = BaseSecAction.getRequestParameter("callback");
			if (StrUtils.isNotEmpty(callback)) {
				BaseSecAction.renderText(callback + "(" + json + ");");
			} else {
				renderJson(json);
			}
		}

		/*
		 * this.getResponse().setContentType(
		 * "text/html; text/plain;  charset=UTF-8"); if (!"".equals(json)) { //
		 * renderJson(json); } else { json = "{\"total\":0,\"rows\":[]}"; }
		 * String callback = this.getRequestParameter("callback");
		 * System.out.print(callback); if (callback != null)
		 * this.renderText(callback + "(" + json + ");");
		 */
	}

	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
}
