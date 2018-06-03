package com.vix.wechat;

import com.vix.wechat.util.Sha1Util;

public class WxRefundPackage {
	public String appid = "";
	public String device_info = "";
	public String mch_id = "";
	public String nonce_str = Sha1Util.getNonceStr();
	public String op_user_id = "";
	public String out_trade_no = "";
	public String out_refund_no = "";
	public String refund_account = "";
	public String refund_fee = "0";
	public String refund_fee_type = "CNY";
	public String sign = "";
	public String sign_type = "";
	public String total_fee = "0";
	public String transaction_id = "";
	public String private_key;
	public String request_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

}
