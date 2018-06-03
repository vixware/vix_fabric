package com.vix.WebService.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.WebService.hql.StoreManagementHqlProvider;
import com.vix.WebService.service.IStoreWebService;
import com.vix.WebService.vo.CustomerInformation;
import com.vix.common.base.action.BaseAction;
import com.vix.common.base.action.BaseSecAction;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;

/**
 * 通过会员卡或手机号查询积分及余额
 * 
 * com.vix.WebService.action.StoreWebServiceAction
 *
 * @author bjitzhang
 *
 * @date 2014年8月11日
 */
@Controller
@Scope("prototype")
public class StoreWebServiceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name = "storeWebService")
	private IStoreWebService storeWebService;
	@Resource(name = "storeManagementHqlProvider")
	private StoreManagementHqlProvider storeManagementHqlProvider;

	/**
	 * 通过会员卡号查询会员积分及余额
	 * 
	 * @param shipCardCode
	 * @return
	 * @throws Exception
	 */
	public void findCustomerInformationByShipCardCode() throws Exception {
		String shipCardCode = getRequestParameter("shipCardCode");
		List<CustomerInformation> customerInformationList = new ArrayList<CustomerInformation>();
		MemberShipCard memberShipCard = storeWebService.findEntity(MemberShipCard.class, "vipcardid", shipCardCode);
		if (memberShipCard != null) {
			CustomerInformation customerInformation = new CustomerInformation();
			customerInformation.setVipcardCode(memberShipCard.getVipcardid());
			customerInformation.setIntegral(memberShipCard.getPoint());
			customerInformation.setResidual(memberShipCard.getBalance_amount());
			customerInformationList.add(customerInformation);
		}

		String json = "";
		if (null != customerInformationList && customerInformationList.size() > 0) {
			json = convertListToJson(customerInformationList, customerInformationList.size());
		}
		BaseSecAction.getResponse().setContentType("text/html; text/plain;  charset=UTF-8");
		if (!"".equals(json)) {
			/* renderJson(json); */
		} else {
			json = "{\"total\":0,\"rows\":[]}";
		}
		String callback = BaseSecAction.getRequestParameter("callback");
		System.out.print(callback);
		if (callback != null)
			BaseSecAction.renderText(callback + "(" + json + ");");
	}

	/**
	 * 通过手机号查询会员积分及余额
	 * 
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public void findCustomerInformationByMobile() throws Exception {
		String mobile = getRequestParameter("mobile");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneOffice", mobile);
		StringBuilder hql = storeManagementHqlProvider.findMSCardList(params);
		List<CustomerInformation> customerInformationList = storeWebService.findEntityList(hql.toString(), params);
		String json = "";
		if (null != customerInformationList && customerInformationList.size() > 0) {
			json = convertListToJson(customerInformationList, customerInformationList.size());
		}
		BaseSecAction.getResponse().setContentType("text/html; text/plain;  charset=UTF-8");
		if (!"".equals(json)) {
			/* renderJson(json); */
		} else {
			json = "{\"total\":0,\"rows\":[]}";
		}
		String callback = BaseSecAction.getRequestParameter("callback");
		System.out.print(callback);
		if (callback != null)
			BaseSecAction.renderText(callback + "(" + json + ");");
	}
}
