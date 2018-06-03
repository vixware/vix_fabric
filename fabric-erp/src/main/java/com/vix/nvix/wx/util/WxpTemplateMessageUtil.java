package com.vix.nvix.wx.util;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import com.vix.core.utils.JSonUtils;
import com.vix.nvix.wx.entity.WxpTemplateMessageConfig;
import com.vix.nvix.wx.entity.WxpTemplateMsgTemplate;
import com.vix.nvix.wx.entity.WxpTemplateMsgTemplateValue;
import com.vix.wechat.util.WxRestUtil;

public class WxpTemplateMessageUtil{
	
	public static String sendTemplateMessageJson(String openId,WxpTemplateMsgTemplateValue wxpTemplateMsgTemplateValue,
			WxpTemplateMsgTemplate wxpTemplateMsgTemplate){
		if(StrUtils.objectIsNotNull(openId) && wxpTemplateMsgTemplateValue != null && wxpTemplateMsgTemplate != null){
			/**
			 * {
           "touser":"OPENID",
           "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
           "url":"http://weixin.qq.com/download",            
           "data":{
                   "first": {
                       "value":"恭喜你购买成功！",
                       "color":"#173177"
                   },
                   "keynote1":{
                       "value":"巧克力",
                       "color":"#173177"
                   },
                   "keynote2": {
                       "value":"39.8元",
                       "color":"#173177"
                   },
                   "keynote3": {
                       "value":"2014年9月22日",
                       "color":"#173177"
                   },
                   "remark":{
                       "value":"欢迎再次购买！",
                       "color":"#173177"
                   }
           }
       }
			 */
			StringBuilder sb = new StringBuilder("");
			sb.append("{\"touser\":\"").append(openId).append("\",");
			sb.append("\"template_id\":\"").append(wxpTemplateMsgTemplate.getTemplateId()).append("\",");
			sb.append("\"url\":\"").append("").append("\",");
			sb.append("\"data\":{");
			if(wxpTemplateMsgTemplate.getCount() > 0){
				sb.append("\"").append(wxpTemplateMsgTemplate.getContentData1()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueI()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorI()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 1){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData2()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorII()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 2){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData3()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueIII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorIII()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 3){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData4()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueIV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorIV()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 4){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData5()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorV()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 5){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData6()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueVI()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorVI()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 6){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData7()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueVII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorVII()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 7){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData8()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueVIII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorVIII()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 8){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData9()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueIX()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorIX()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 9){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData10()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueX()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorX()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 10){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData11()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXI()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXI()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 11){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData12()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXII()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 12){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData13()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXIII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXIII()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 13){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData13()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXIV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXIV()).append("\"");
				sb.append("}");
			}
			if(wxpTemplateMsgTemplate.getCount() > 14){
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData15()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXV()).append("\"");
				sb.append("}");
			}
			sb.append("}}");
			return sb.toString();
		}
		return null;
	}
	
	public static String apiSetTemplateMessageTndustry(WxpTemplateMessageConfig wxpTemplateMessageConfig,String accessToken, String jsonStr) throws Exception{
		try{
			if(wxpTemplateMessageConfig != null){
				URI uri = new URIBuilder()
		        .setScheme(wxpTemplateMessageConfig.getSyncScheme())
		        .setHost(wxpTemplateMessageConfig.getSyncHost())
		        .setPath(wxpTemplateMessageConfig.getSyncPath())
		        .setParameter("access_token", accessToken)
		        .build();
				RestTemplate restTemplate = new RestTemplate();
		        StringEntity entity = new StringEntity(jsonStr, ContentType.create("plain/text", Consts.UTF_8));
		        jsonStr = EntityUtils.toString(entity);
		        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),jsonStr);
		        System.out.println("response:" + response);
		        /** {"errcode":0,"errmsg":"ok"}*/
		        if(StrUtils.objectIsNotNull(response)){
		        	Map ugMap = JSonUtils.readValue(response, Map.class);
			        Integer errcode = (Integer)ugMap.get("errcode");
			        if(errcode != null && errcode == 0){
			        	return "success";
			        }
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,Map<String,String>> apiGetTemplateMessageTndustry(WxpTemplateMessageConfig wxpTemplateMessageConfig,String accessToken) throws Exception{
		try{
			if(wxpTemplateMessageConfig != null){
				URI uri = new URIBuilder()
		        .setScheme(wxpTemplateMessageConfig.getSyncScheme())
		        .setHost(wxpTemplateMessageConfig.getSyncHost())
		        .setPath(wxpTemplateMessageConfig.getSyncPath())
		        .setParameter("access_token", accessToken)
		        .build();
				RestTemplate restTemplate = new RestTemplate();
		        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),null);
		        System.out.println("response:" + response);
		        response = new String(response.getBytes("iso8859-1"),"UTF-8");
		        if(StrUtils.objectIsNotNull(response)){
		        	Map<String,Map<String,String>> ugMap = JSonUtils.readValue(response, Map.class);
		        	return ugMap;
		        }
		        /**
		         * {
					"primary_industry":{"first_class":"运输与仓储","second_class":"快递"},
					"secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"}
					}
		         */
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Map<String,String>> apiGetAllPrivateTemplate(WxpTemplateMessageConfig wxpTemplateMessageConfig,String accessToken) throws Exception{
		try{
			if(wxpTemplateMessageConfig != null){
				URI uri = new URIBuilder()
		        .setScheme(wxpTemplateMessageConfig.getSyncScheme())
		        .setHost(wxpTemplateMessageConfig.getSyncHost())
		        .setPath(wxpTemplateMessageConfig.getSyncPath())
		        .setParameter("access_token", accessToken)
		        .build();
				RestTemplate restTemplate = new RestTemplate();
		        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),null);
		        response = new String(response.getBytes("iso8859-1"),"UTF-8");
		        System.out.println("response:" + response);
		        if(StrUtils.objectIsNotNull(response)){
		        	Map ugMap = JSonUtils.readValue(response, Map.class);
		        	List<Map<String,String>> templateList = (List<Map<String,String>>) ugMap.get("template_list");
		        	if(templateList != null && templateList.size() > 0){
		        		return templateList;
		        	}
		        }
		        /**
		         * {	
					 "template_list": [{
					      "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
					      "title": "领取奖金提醒",
					      "primary_industry": "IT科技",
					      "deputy_industry": "互联网|电子商务",
					      "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
					      "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
					   }]
					}
		         */
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String apiAddTemplate(WxpTemplateMessageConfig wxpTemplateMessageConfig,String accessToken,String jsonStr) throws Exception{
		try{
			if(wxpTemplateMessageConfig != null){
				URI uri = new URIBuilder()
		        .setScheme(wxpTemplateMessageConfig.getSyncScheme())
		        .setHost(wxpTemplateMessageConfig.getSyncHost())
		        .setPath(wxpTemplateMessageConfig.getSyncPath())
		        .setParameter("access_token", accessToken)
		        .build();
				RestTemplate restTemplate = new RestTemplate();
				StringEntity entity = new StringEntity(jsonStr, ContentType.create("plain/text", Consts.UTF_8));
		        jsonStr = EntityUtils.toString(entity);
		        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),jsonStr);
		        /*{"errcode":0,"errmsg":"ok", "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"}*/
		        System.out.println("response:" + response);
		        if(StrUtils.objectIsNotNull(response)){
		        	Map ugMap = JSonUtils.readValue(response, Map.class);
			        Integer errcode = (Integer)ugMap.get("errcode");
			        String templateId = (String) ugMap.get("template_id");
			        if(errcode != null && errcode == 0 && StrUtils.objectIsNotNull(templateId)){
			        	return templateId;
			        }
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String apiDelPrivateTemplate(WxpTemplateMessageConfig wxpTemplateMessageConfig,String accessToken,String jsonStr) throws Exception{
		try{
			if(wxpTemplateMessageConfig != null){
				URI uri = new URIBuilder()
		        .setScheme(wxpTemplateMessageConfig.getSyncScheme())
		        .setHost(wxpTemplateMessageConfig.getSyncHost())
		        .setPath(wxpTemplateMessageConfig.getSyncPath())
		        .setParameter("access_token", accessToken)
		        .build();
				RestTemplate restTemplate = new RestTemplate();
				StringEntity entity = new StringEntity(jsonStr, ContentType.create("plain/text", Consts.UTF_8));
		        jsonStr = EntityUtils.toString(entity);
		        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),jsonStr);
		        System.out.println("response:" + response);
		        if(StrUtils.objectIsNotNull(response)){
		        	Map ugMap = JSonUtils.readValue(response, Map.class);
			        Integer errcode = (Integer)ugMap.get("errcode");
			        if(errcode != null && errcode == 0){
			        	return "success";
			        }
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sendTemplateMessage(WxpTemplateMessageConfig wxpTemplateMessageConfig,String accessToken,String jsonStr) throws Exception{
		try{
			if(wxpTemplateMessageConfig != null){
				URI uri = new URIBuilder()
		        .setScheme(wxpTemplateMessageConfig.getSyncScheme())
		        .setHost(wxpTemplateMessageConfig.getSyncHost())
		        .setPath(wxpTemplateMessageConfig.getSyncPath())
		        .setParameter("access_token", accessToken)
		        .build();
				RestTemplate restTemplate = new RestTemplate();
				StringEntity entity = new StringEntity(jsonStr, ContentType.create("plain/text", Consts.UTF_8));
		        jsonStr = EntityUtils.toString(entity);
		        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),jsonStr);
		        System.out.println("response:" + response);
		        /**  {"errcode":0,"errmsg":"ok","msgid":200228332 } */
		        if(StrUtils.objectIsNotNull(response)){
		        	Map ugMap = JSonUtils.readValue(response, Map.class);
			        Integer errcode = (Integer)ugMap.get("errcode");
			        String msgid = ugMap.get("msgid").toString();
			        if(errcode != null && errcode == 0 && StrUtils.objectIsNotNull(msgid)){
			        	return msgid;
			        }
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
