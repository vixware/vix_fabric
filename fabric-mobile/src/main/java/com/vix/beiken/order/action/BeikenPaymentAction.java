package com.vix.beiken.order.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.beiken.order.util.NumberUtil;
import com.vix.beiken.order.util.PadPayUtil;
import com.vix.beiken.order.util.PrePayPackage;
import com.vix.beiken.order.util.Sha1Util;
import com.vix.beiken.order.util.WxPayConfig;
import com.vix.common.base.action.VixSecAction;
import com.vix.core.utils.StrUtils;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.nvix.common.base.service.IVixntBaseService;

/**
 * 
 * 支付
 * @author arron
 *
 */
@Controller
@Scope("prototype")
public class BeikenPaymentAction extends VixSecAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IVixntBaseService vixntBaseService;
	
	private SortedMap<String, String> payData;
	private String id;
	private String salesPersonId;
	private double sendAmount;
	
	public void weixinJsPayPrepare(){		
		try{
			if(StrUtils.isNotEmpty(id)){
				String appId = "wxb8749fa32c221d73";
				String openId = getRequestParameter("openId");
				WxPayConfig wxPayConfig = vixntBaseService.findEntityByAttribute(WxPayConfig.class, "appId", appId);
				if(null == wxPayConfig || StrUtils.isNotEmpty(wxPayConfig.getDeviceInfo()) ||
						StrUtils.isNotEmpty(wxPayConfig.getMchId()) ||
						StrUtils.isNotEmpty(wxPayConfig.getNotifyUrl())||
						StrUtils.isNotEmpty(wxPayConfig.getPrivateKey()) ||
						StrUtils.isNotEmpty(wxPayConfig.getSpbillCreateIp()) ||
						StrUtils.isNotEmpty(wxPayConfig.getTradeType()) ||
						StrUtils.isNotEmpty(wxPayConfig.getBody())){
					System.out.println("error:后台微信支付配置数据不全！");
				}else{
					wxPayConfig.getDeviceInfo();
					id = id.replaceAll("\r|\n", "");
					RequireGoodsOrder requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
					
				    PrePayPackage prepayPack = new PrePayPackage();
				    //wx15734592ce5852aa
				    //wxedb07132d074f621
				    prepayPack.APPID = appId.toString(); 
				    
				    prepayPack.auth_code = Sha1Util.getNonceStr();  
				    //查询理疗项目
				    prepayPack.body = wxPayConfig.getBody();
				    if(StrUtils.objectIsNull(prepayPack.body))
				    	prepayPack.body = "订单支付";
				    
				    prepayPack.device_info = wxPayConfig.getDeviceInfo();
				    prepayPack.mch_id = wxPayConfig.getMchId();  
				    prepayPack.nonce_str = Sha1Util.getNonceStr();  
				    prepayPack.out_trade_no = String.valueOf(requireGoodsOrder.getCode());  
				    prepayPack.product_id = String.valueOf(requireGoodsOrder.getId());
				    prepayPack.spbill_create_ip = wxPayConfig.getSpbillCreateIp();  
				    prepayPack.notify_url = wxPayConfig.getNotifyUrl();  
				    prepayPack.total_fee = String.valueOf(Math.round(requireGoodsOrder.getAmount()*100));  
				    prepayPack.trade_type = wxPayConfig.getTradeType();  
				    prepayPack.private_key = wxPayConfig.getPrivateKey();
				    
				    prepayPack.openId = openId.toString();
				    //prepayPack.openId = openId;
				    String prePayXml = PadPayUtil.prePayRequest(prepayPack);
				    //prePayXml = new String(prePayXml.toString().getBytes(), "ISO8859-1");
				    System.out.println(prePayXml);
	
					Document xmlData = DocumentHelper.parseText(prePayXml);
			        Element root= xmlData.getRootElement();
			        String prepayId = root.elementText("prepay_id");
			        String packageStr = "prepay_id=" + prepayId;
			        
					payData = new TreeMap<String, String>();
					payData.put("appId", appId.toString());
					payData.put("timeStamp", Sha1Util.getTimeStamp());
					payData.put("nonceStr", Sha1Util.getNonceStr());
					payData.put("signType", "MD5");
					payData.put("package", packageStr);
			        
			        String sign = PadPayUtil.createSign(payData, wxPayConfig.getPrivateKey());
			        
			        payData.put("paySign", sign);
	
			        renderJson(JSONObject.toJSONString(payData));
				}	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void payNotify(){
		System.out.println("LGXZ pay callback message,status scaned");
		try{
			String postStr = this.readStreamParameter(getRequest().getInputStream());

			Document xmlData = null;
			try{  
				xmlData = DocumentHelper.parseText(postStr);  
	        }catch(Exception exml){  
	            exml.printStackTrace();
	            return;
	        }  
	        if(null == xmlData){  
	            return;  
	        }

	        Element root= xmlData.getRootElement();
	        
	        String orderCode = root.elementText("out_trade_no");
	        String  totalFee = root.elementText("total_fee");
	        if(StrUtils.objectIsNotNull(orderCode) && StrUtils.objectIsNotNull(totalFee) && NumberUtil.isDoubleNumeric(totalFee)){
	        	Double 	totalPrice = Double.valueOf(totalFee);
		        totalPrice = totalPrice /100;
		        //boolean tag = ecOrderService.confirmEcOrderPayment(orderCode,totalPrice);
	        }
	        System.out.println("LGXZ pay callback ORDER ID: "+orderCode);
	        renderXml("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("!!!!  payDirect callback msg exception");
		}
	}
	
	//从输入流读取post参数  
    public String readStreamParameter(ServletInputStream in){  
        StringBuilder buffer = new StringBuilder();  
        BufferedReader reader=null;  
        try{
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));  
            String line=null;  
            while((line = reader.readLine())!=null){  
                buffer.append(line);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(null!=reader){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return buffer.toString();  
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalesPersonId() {
		return salesPersonId;
	}

	public void setSalesPersonId(String salesPersonId) {
		this.salesPersonId = salesPersonId;
	}

	public double getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(double sendAmount) {
		this.sendAmount = sendAmount;
	}
}
