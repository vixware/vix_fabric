package com.rest.ebusiness;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.WebService.service.IStoreWebService;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.core.utils.StrUtils;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * com.rest.ebusiness.OrderDownController
 *
 * @author bjitzhang
 *
 * @date 2015年4月8日
 */

@Controller
@RequestMapping(value = "restService/orderReturn")
public class OrderReturnController extends BaseRestController {

	@Autowired
	private IStoreWebService storeWebService;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "returnOrder", method = RequestMethod.POST)
	@ResponseBody
	public String returnOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonData = request.getParameter("jsonData");
		JSONObject json = JSONObject.fromObject(jsonData);
		String validateCode = null;
		if (json.has("validateCode")) {
			validateCode = json.getString("validateCode");
		}
		String onlineStoreCode = null;
		if (json.has("channelDistributorCode")) {
			onlineStoreCode = json.getString("channelDistributorCode");
		}
		String authCode = null;
		if (StrUtils.objectIsNotNull("VIX")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
			Md5Encoder m5 = new Md5EncoderImpl();
			authCode = m5.encodeString("VIX", sdf.format(new Date()));
			System.out.println("authCode:" + authCode);
		}
		if (StrUtils.objectIsNull(validateCode) || !validateCode.equals(authCode)) {
			// return convertListToJsonNoEscape("0", "上传失败" + validateCode);
		}
		if (StrUtils.objectIsNull(onlineStoreCode)) {
			// return convertListToJsonNoEscape("0", "上传失败" + validateCode);
		}
		System.out.println("onlineStoreCode:" + onlineStoreCode);
		ChannelDistributor channelDistributor = storeWebService.findEntityByAttributeNoTenantId(ChannelDistributor.class, "appKey", onlineStoreCode);
		SaleReturnForm saleReturnForm = new SaleReturnForm();
		if (json.has("createDate")) {
			saleReturnForm.setCreateTime(dateFormat.parse(json.getString("createDate")));
		}
		if (json.has("code")) {
			saleReturnForm.setCode(json.getString("code"));
		}
		if (json.has("ecOrderCode")) {
			saleReturnForm.setReturnOrderCode(json.getString("ecOrderCode"));
		}
		if (channelDistributor != null) {
			saleReturnForm.setTenantId(channelDistributor.getTenantId());
			saleReturnForm.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
			saleReturnForm.setCreator(channelDistributor.getCreator());
		}
		SaleReturnForm s = storeWebService.findEntityByAttributeNoTenantId(SaleReturnForm.class, "code", json.getString("code"));
		if (s == null) {
			saleReturnForm = storeWebService.mergeOriginal(saleReturnForm);
		} else {
			saleReturnForm = s;
		}

		// returnGoodsAmount
		// amountStatus
		// status
		// memo
		String returnGoodsItemBills = json.getString("returnGoodsItemBills");
		JSONArray ecOrderItemList = JSONArray.fromObject(returnGoodsItemBills);
		if (ecOrderItemList != null) {
			for (int i = 0; i < ecOrderItemList.size(); i++) {
				SaleReturnFormItem saleReturnFormItem = new SaleReturnFormItem();
				JSONObject o = ecOrderItemList.getJSONObject(i);
				// "sku":"2323221","returnAmount":116.0,"takeOffPointValue":11,"returnGoodsBillCode":"1112232"
				if (json.has("createDate")) {
					saleReturnFormItem.setCreateTime(dateFormat.parse(o.getString("createDate")));
				}
				if (json.has("companyCode")) {
					saleReturnFormItem.setCompanyCode(o.getString("companyCode"));
				}
				if (json.has("ecProductCode")) {
					saleReturnFormItem.setItemCode(o.getString("ecProductCode"));
				}
				if (json.has("returnAmount")) {
					saleReturnFormItem.setNetTotal(Double.parseDouble(o.getString("returnAmount")));
				}
				saleReturnFormItem.setTenantId(channelDistributor.getTenantId());
				saleReturnFormItem.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
				saleReturnFormItem.setCreator(channelDistributor.getCreator());
				saleReturnFormItem.setSaleReturnForm(saleReturnForm);
				SaleReturnFormItem item = storeWebService.findEntityByAttributeNoTenantId(SaleReturnFormItem.class, "code", json.getString("code"));
				if (item == null) {
					storeWebService.mergeOriginal(saleReturnFormItem);
				}
			}
		}

		return convertListToJsonNoEscape("0", "上传成功");

	}
}
