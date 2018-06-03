/**
 * 
 */
package com.vix.ebusiness.util;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.order.OrderSearchRequest;
import com.jd.open.api.sdk.response.order.OrderSearchResponse;

/**
 * @author zhanghaibing
 * 
 * @date 2014-3-13
 */
public class Test {

	public static void main(String args[]) {
		JdClient client = new DefaultJdClient("http://gw.api.360buy.com/routerjson", "c6825e57-eb6b-472a-8f32-b1130399881e", "3DFB09549BB1458DADF034733BCDFD97", "1083d62c83004fc6b5d20ac086abb2ae");
		OrderSearchRequest request = new OrderSearchRequest();
		request.setOrderState("WAIT_GOODS_RECEIVE_CONFIRM");
		request.setPage("1");
		request.setPageSize("100");
		request.setOptionalFields("vender_id,order_id,pay_type,order_total_price,freight_price,seller_discount,order_payment,delivery_type,order_state,order_state_remark,invoice_info,order_remark,order_start_time,order_end_time,consignee_info,item_info_list");
		try {
			OrderSearchResponse response = client.execute(request);
			System.out.println(response.getMsg());
		} catch (JdException e) {
			e.printStackTrace();
		}

	}
	/*public static void main(String args[]) {
		
		JSONObject prop = new JSONObject();
		prop.put("url",  "http://auth.sandbox.360buy.com/oauth/token");
		prop.put("cName", "");
		prop.put("nick", "sandbox_zjk");
		prop.put("session_key", "d5996f4f-c40c-4865-8bc2-d8e2e189412a");
		prop.put("mallType", "1");
		prop.put("app_id", "9DCA74C8E4172496BA26EB07CD92BEA7");
		prop.put("app_secret", "2afade37fbe34ecca786bc1e63c8e374");
		prop.put("apiVersion", 2);
		prop.put("mode", false);
		prop.put("sellerId", "9DCA74C8E4172496BA26EB07CD92BEA7");
		prop.put("accredit", 2); // 授权类型淘宝新的授权方式Oauth2.0
		prop.put("marketPlaceId", "sandbox_zjk");
		System.out.println(prop.toCompactString());
		JdClient client = new DefaultJdClient("http://gw.api.sandbox.360buy.com/routerjson", "d5996f4f-c40c-4865-8bc2-d8e2e189412a", "9DCA74C8E4172496BA26EB07CD92BEA7", "2afade37fbe34ecca786bc1e63c8e374");
		OrderSearchRequest request = new OrderSearchRequest();
		request.setOrderState("WAIT_SELLER_STOCK_OUT");
		request.setPage("1");
		request.setPageSize("100");
		request.setOptionalFields("vender_id,order_id,pay_type,order_total_price,freight_price,seller_discount,order_payment,delivery_type,order_state,order_state_remark,invoice_info,order_remark,order_start_time,order_end_time,consignee_info,item_info_list");
		try {
			OrderSearchResponse response = client.execute(request);
			System.out.println(response.getMsg());
		} catch (JdException e) {
			e.printStackTrace();
		}
		
	}*/
	/*
	 * public static void main(String args[]) { JdClient client = new
	 * DefaultJdClient("http://gw.api.sandbox.360buy.com/routerjson",
	 * 
	 * "d5996f4f-c40c-4865-8bc2-d8e2e189412a",
	 * "9DCA74C8E4172496BA26EB07CD92BEA7", "2afade37fbe34ecca786bc1e63c8e374");
	 * WareListingGetRequest wareListingGetRequest = new
	 * WareListingGetRequest(); wareListingGetRequest.setPage("1");
	 * wareListingGetRequest.setPageSize("100"); try { WareListingGetResponse
	 * wareListingGetResponse = client.execute(wareListingGetRequest);
	 * System.out.println(wareListingGetResponse.getMsg()); } catch (JdException
	 * e) { e.printStackTrace(); }
	 * 
	 * }
	 */
}
