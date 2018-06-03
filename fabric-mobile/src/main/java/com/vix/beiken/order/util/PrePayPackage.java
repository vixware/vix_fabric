package com.vix.beiken.order.util;

public class PrePayPackage 
{

    //appid   
    public String APPID = "";  
    public String auth_code = Sha1Util.getNonceStr();  
    public String body = "";  
    public String device_info = "";  
    public String mch_id = "";  
    public String nonce_str = Sha1Util.getNonceStr();  
    public String out_trade_no = Sha1Util.getNonceStr();
    public String product_id = "";
    public String spbill_create_ip = "";  
    public String notify_url = "";  
    public String total_fee = "";  
    public String trade_type = "NATIVE";  
    public String private_key = "";  
//    public static String key = "G1jH4MORcyNR45_2hBV7fxiXo1c-0VhcTF9ntmTwZy5THQQJFgMruYQ_PM3z9Qax";  
    
    public String openId = "";//jsapi支付时需要
              
    //请求地址  
    public static String request_url="https://api.mch.weixin.qq.com/pay/unifiedorder";      
}
