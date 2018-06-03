package com.vix.nvix.wx.util;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.JSonUtils;
import com.vix.nvix.wx.entity.WxpArticle;
import com.vix.nvix.wx.entity.WxpArticleGroupItem;
import com.vix.nvix.wx.entity.WxpMsgSend;
import com.vix.nvix.wx.entity.WxpQfMessageConfig;
import com.vix.nvix.wx.entity.WxpUserTag;
import com.vix.nvix.wx.entity.WxpUserTagRel;
import com.vix.wechat.util.WxRestUtil;

public class WxpMessageUtil{
	
	public static String getUploadMsgSendArticleJson(WxpArticle wxpArticle,
			IBaseHibernateService baseHibernateService) {
		if(wxpArticle != null){
			StringBuilder sb = new StringBuilder("{\"articles\":[");
			sb.append("{\"thumb_media_id\":\"").append(wxpArticle.getThumbMediaId()).append("\",");
			sb.append("\"author\":\"").append(wxpArticle.getAuthor()).append("\",");
			sb.append("\"title\":\"").append(wxpArticle.getTitle()).append("\",");
			sb.append("\"content_source_url\":\"").append(wxpArticle.getSource()).append("\",");
			sb.append("\"content\":\"").append(wxpArticle.getContent()).append("\",");
			sb.append("\"digest\":\"").append(wxpArticle.getSummary()).append("\",");
			sb.append("\"show_cover_pic\":").append(1).append("}");
			sb.append("]}");
			return sb.toString();
		}
		return null;
	}
	
	public static String getUploadMsgSendNewsJson(java.util.List<WxpArticleGroupItem> wxpArticleGroupItemList,IBaseHibernateService baseHibernateService) throws Exception{
		if(wxpArticleGroupItemList != null && wxpArticleGroupItemList.size() > 0){
			StringBuilder sb = new StringBuilder("{\"articles\":[");
			int i = 1;
			for(WxpArticleGroupItem wxpArticleGroupItem : wxpArticleGroupItemList){
				if(wxpArticleGroupItem != null && wxpArticleGroupItem.getAppId() != null && StrUtils.objectIsNotNull(wxpArticleGroupItem.getArticle().getId())){
					WxpArticle wxpArticle = baseHibernateService.findEntityById(WxpArticle.class, wxpArticleGroupItem.getArticle().getId());
					if(wxpArticle != null){
						sb.append("{\"thumb_media_id\":\"").append(wxpArticle.getThumbMediaId()).append("\",");
						sb.append("\"author\":\"").append(wxpArticle.getAuthor()).append("\",");
						sb.append("\"title\":\"").append(wxpArticle.getTitle()).append("\",");
						sb.append("\"content_source_url\":\"").append(wxpArticle.getSource()).append("\",");
						sb.append("\"content\":\"").append(wxpArticle.getContent()).append("\",");
						sb.append("\"digest\":\"").append(wxpArticle.getSummary()).append("\",");
						sb.append("\"show_cover_pic\":").append(1).append("}");
						if(wxpArticleGroupItemList.size() > 1 && i != wxpArticleGroupItemList.size()){
							sb.append(",");
						}
						i++;
					}
				}
			}
			sb.append("]}");
			return sb.toString();
		}
		return null;
	}
	
	
	/**
	 * 上传群发图文内容，获取mediaId用于发送
	 * @param accessToken
	 * @param newsJson
	 * @return
	 * @throws Exception
	 */
	public static String uploadMsgSendNews(String accessToken, String newsJson) throws Exception{
		//https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN
		URI uri = new URIBuilder()
				        .setScheme("https")
				        .setHost("api.weixin.qq.com")
				        .setPath("/cgi-bin/media/uploadnews")
				        .setParameter("access_token", accessToken)
				        .build();
		try{
			RestTemplate restTemplate = new RestTemplate();
	        StringEntity entity = new StringEntity(newsJson, ContentType.create("plain/text", Consts.UTF_8));
	        newsJson = EntityUtils.toString(entity);
	        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),newsJson);
	        System.out.println("response:" + response);
		    if (response != null) {
		    	//success return: {"errcode":0,"errmsg":"ok"}
	    		String createUGResponse = response;
		        createUGResponse = new String(createUGResponse.getBytes("iso8859-1"),"UTF-8");
		        Map ugMap = JSonUtils.readValue(createUGResponse, Map.class);
				String mediaId = (String)ugMap.get("media_id");
				return mediaId;
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String postMsgSend(WxpMsgSend msgSend, String accessToken,IBaseHibernateService baseHibernateService){
		try{
			//return "test";
			String postJson = genMsgSendJsonMap(msgSend,baseHibernateService);
			if(StrUtils.objectIsNotNull(postJson)){
				WxpQfMessageConfig wxpQfMessageConfig = getWxpQfMessageConfig(msgSend,baseHibernateService);
				if(wxpQfMessageConfig != null){
					URI uri = new URIBuilder()
			        .setScheme(wxpQfMessageConfig.getSyncScheme())
			        .setHost(wxpQfMessageConfig.getSyncHost())
			        .setPath(wxpQfMessageConfig.getSyncPath())
			        .setParameter("access_token", accessToken)
			        .build();
					RestTemplate restTemplate = new RestTemplate();
			        StringEntity entity = new StringEntity(postJson, ContentType.create("plain/text", Consts.UTF_8));
			        postJson = EntityUtils.toString(entity);
			        String response = WxRestUtil.postForObject(restTemplate,uri.toString(),postJson);
			        System.out.println("response:" + response);
			        if (response != null) {
				    	//success return: {"errcode":0,"errmsg":"ok"}
			    		String createUGResponse = response;
				        createUGResponse = new String(createUGResponse.getBytes("iso8859-1"),"UTF-8");
				        Map ugMap = JSonUtils.readValue(createUGResponse, Map.class);
				        String errcode= String.valueOf(ugMap.get("errcode"));
						if("0".equals(errcode)){
							Long msgId = (Long)ugMap.get("msg_id");
							return msgId.toString();
						}
				    }
				}else{
					System.out.println("后台尚未配置群发信息接口！");
				}
			}else{
				System.out.println("群发json串没有数据！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private static WxpQfMessageConfig getWxpQfMessageConfig(WxpMsgSend wxpMsgSend,IBaseHibernateService  baseHibernateService) throws Exception{
		WxpQfMessageConfig wxpQfMessageConfig = new WxpQfMessageConfig();
		if(wxpMsgSend.getTargetType()==0 || wxpMsgSend.getTargetType()==1){
			wxpQfMessageConfig = baseHibernateService.findEntityByAttribute(WxpQfMessageConfig.class, "uriType","sendall");
		}else if(wxpMsgSend.getTargetType()==2 || wxpMsgSend.getTargetType()==3){
			wxpQfMessageConfig = baseHibernateService.findEntityByAttribute(WxpQfMessageConfig.class, "uriType","send");
		}
		return wxpQfMessageConfig;
	}

	/*public static String previewMsgSend(WxpMsgSend msgSend, String accessToken)
	{
		Map<String,Object> jsonMap = genMsgSendJsonMap(msgSend);
		jsonMap.remove("filter");
		jsonMap.put("touser", "obUuKjhPQjMCRp5BTf3kDhyv9n6I");
		String postJson = JSONObject.toJSONString(jsonMap);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		try
		{
			//https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder()
					        .setScheme("https")
					        .setHost(api_weixin_server)
					        .setPath("/cgi-bin/message/mass/preview")
					        .setParameter("access_token", accessToken)
					        .build();
			
			httppost = new HttpPost(uri);
			StringEntity entity = new StringEntity(postJson, ContentType.create("plain/text", Consts.UTF_8));
			entity.setChunked(true);
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
		    if (responseEntity != null) 
		    {
		    	try{
			        String reponseStr = EntityUtils.toString(responseEntity);
System.out.println("$$# msgSend post reply : " + reponseStr);
					Map retMap = JSonUtils.readValue(reponseStr, Map.class);
					String errcode= String.valueOf(retMap.get("errcode"));
					if("0".equals(errcode))
					{
						String msgId = String.valueOf(retMap.get("msg_id"));
						return msgId;
					}
					
		    	}catch(Exception rwe){
		    		rwe.printStackTrace();
		    	}
		    }
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			if(response!=null)
			{
				try{
					response.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}*/
	
	private static String genMsgSendJsonMap(WxpMsgSend msgSend,IBaseHibernateService baseHibernateService) throws Exception{
		String postJson  = null;
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		//发送的类型：图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video
		//msgSend的类型：//article,news,text,image,voice,video,music,material
		if("article".equals(msgSend.getMsgType()) || "news".equals(msgSend.getMsgType()) || "material".equals(msgSend.getMsgType())){
			//消息类型
			jsonMap.put("msgtype", "mpnews");
			//消息内容
			Map<String,Object> msgMap = new HashMap<String,Object>();
			msgMap.put("media_id", msgSend.getMediaId());
			jsonMap.put("mpnews", msgMap);
		}else if("text".equals(msgSend.getMsgType())){
			//消息类型
			jsonMap.put("msgtype", "text");
			//消息内容
			Map<String,Object> msgMap = new HashMap<String,Object>();
			msgMap.put("content", msgSend.getTextContent());
			jsonMap.put("text", msgMap);
		}else if("image".equals(msgSend.getMsgType())){
			//消息类型
			jsonMap.put("msgtype", "image");
			//消息内容
			Map<String,Object> msgMap = new HashMap<String,Object>();
			msgMap.put("media_id", msgSend.getMediaId());
			jsonMap.put("image", msgMap);
		}
		WxpUserTag wxpUserTag = null;
		//发送对象:0,all;2,tag;3,openIds
		if(msgSend.getTargetType()==0 || msgSend.getTargetType()==1){
			if(msgSend.getTargetType()==0){
				Map<String,Object> filterMap = new HashMap<String,Object>();
				filterMap.put("is_to_all", true);
				jsonMap.put("filter", filterMap);
			}else if(msgSend.getTargetType()==2){
				wxpUserTag = baseHibernateService.findEntityById(WxpUserTag.class, msgSend.getTargetIds());
				if(wxpUserTag != null && StrUtils.objectIsNotNull(wxpUserTag.getIsWxTag()) && "1".equals(wxpUserTag.getIsWxTag())){
					Map<String,Object> filterMap = new HashMap<String,Object>();
					filterMap.put("is_to_all", false);
					filterMap.put("tag_id", msgSend.getTargetIds());
					jsonMap.put("filter", filterMap);		
				}
			}
		}
		if(jsonMap != null){
			postJson = JSONObject.toJSONString(jsonMap);
		}
		if(msgSend.getTargetType()==2){
			if(wxpUserTag != null && StrUtils.objectIsNotNull(wxpUserTag.getIsWxTag()) && "0".equals(wxpUserTag.getIsWxTag())){
				StringBuilder sb = new StringBuilder("{");
				String opendIds = "";
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("appId,"+SearchCondition.EQUAL, msgSend.getAppId());
				params.put("tag.id,"+SearchCondition.EQUAL, msgSend.getTargetIds());
				List<WxpUserTagRel> wxpUserTagRelList = baseHibernateService.findAllByConditions(WxpUserTagRel.class, params);
				if(wxpUserTagRelList != null && wxpUserTagRelList.size() > 0){
					for(WxpUserTagRel wxpUserTagRel : wxpUserTagRelList){
						if(wxpUserTagRel != null && wxpUserTagRel.getUser() != null && StrUtils.objectIsNotNull(wxpUserTagRel.getUser().getOpenId())){
							if(wxpUserTagRel.getUser().getSubscribe() == 1){
								String openId = wxpUserTagRel.getUser().getOpenId();
								opendIds = opendIds + ",\""+openId+"\"";
							}
						}
					}
				}
				opendIds = opendIds.substring(1);
				sb.append("\"touser\":[").append(opendIds).append("],");
				if(StrUtils.objectIsNotNull(postJson)){
					postJson = postJson.trim();
					postJson = postJson.substring(1);
					postJson = sb.toString() + postJson;
				}
			}
		}else if(msgSend.getTargetType()==3){
			StringBuilder sb = new StringBuilder("{");
			String opendIds = msgSend.getTargetIds();
			String[] str = opendIds.split(",");
			opendIds = "";
			if(str != null && str.length > 0){
				for(int i = 0;i < str.length;i++){
					String openId = str[i];
					if(StrUtils.objectIsNotNull(openId)){
						opendIds = opendIds + ",\""+openId+"\"";
					}
				}
			}
			opendIds = opendIds.substring(1);
			sb.append("\"touser\":[").append(opendIds).append("],");
			if(StrUtils.objectIsNotNull(postJson)){
				postJson = postJson.trim();
				postJson = postJson.substring(1);
				postJson = sb.toString() + postJson;
			}
		}
		return postJson;
	}
}
