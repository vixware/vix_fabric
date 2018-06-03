package com.vix.nvix.wx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vix.core.constant.SearchCondition;
import com.vix.nvix.wx.entity.WxpReply;
import com.vix.nvix.wx.entity.WxpReplyMatch;
import com.vix.nvix.wx.entity.WxpReplyRule;
import com.vix.nvix.wx.service.IWxpMenuService;

public class MsgReplyMap
{
	public static final String user_msg_image_type = "user_msg_image_type";
	public static final String user_msg_video_type = "user_msg_video_type";
	public static final String user_msg_voice_type = "user_msg_voice_type";
	
	IWxpMenuService myService;
	
	String appId;
	long loadedMaxId;
	WxpReply globalReply;
	WxpReply subscribeReply;
	
	HashMap<String,List<WxpReply>> msgReplyMap;		//消息、菜单事件回复map
	
	public MsgReplyMap(String appId, IWxpMenuService workService)
	{
		this.appId = appId;
		this.myService = workService;
		
		try{
			this.loadAllReply();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户消息回复
	 * 规则：
	 * 		userMsg=null时为订阅消息
	 * 		匹配userMsg后回复定义内容（消息、菜单事件）
	 * 		无法匹配后回复全局消息
	 * @param userMsg
	 * @return
	 */
	public WxpReply getReply(String userMsg)
	{
		try{
			if(this.msgReplyMap==null)
				this.loadAllReply();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(userMsg==null)
		{
			//订阅
			return this.subscribeReply;
		}
		else if(user_msg_image_type.equals(userMsg))
		{
			//暂时无法处理
			//return this.globalReply;
			return null;
		}
		else if(user_msg_video_type.equals(userMsg))
		{
			//暂时无法处理
			//return this.globalReply;
			return null;
		}
		else if(user_msg_voice_type.equals(userMsg))
		{
			//暂时无法处理
			//return this.globalReply;
			return null;
		}
		else
		{
			if(this.msgReplyMap.containsKey(userMsg))
			{
				List<WxpReply> replys = this.msgReplyMap.get(userMsg);
				if(replys==null || replys.size()==0){
					//这种情况
					return this.globalReply;
				}
				
				int randomIdx = 0;
				if(replys!=null && replys.size()>1)
					randomIdx = PadUtils.genRandomNum(0, replys.size());
				return replys.get(randomIdx);
			}
			else
			{
				return this.globalReply;
			}
		}
	}
	
	public void setGlobalReply(WxpReply reply)
	{
		this.globalReply = reply;
	}
	
	public void setSucscribeReply(WxpReply reply)
	{
		this.subscribeReply = reply;
	}
	
	public void loadAllReply() throws Exception {
		//加载消息、菜单事件回复
		this.msgReplyMap = new HashMap<String,List<WxpReply>>();
		
		Map<String,Object> matchParams = new HashMap<String,Object>();
		matchParams.put("appId,"+SearchCondition.EQUAL, this.appId);
		//matchParams.put("replyRule.isActive,"+SearchCondition.EQUAL, 1);
		List<WxpReplyMatch> matchList = this.myService.findAllByConditions(WxpReplyMatch.class, matchParams);
		if(matchList != null && matchList.size() > 0){
			for(WxpReplyMatch match:matchList){
				if(match != null && match.getReplyRule() != null && StrUtils.objectIsNotNull(match.getReplyRule().getId())){
					WxpReplyRule wxpReplyRule = myService.findEntityById(WxpReplyRule.class, match.getReplyRule().getId());
					if(wxpReplyRule != null && wxpReplyRule.getIsActive() != null && wxpReplyRule.getIsActive() == 1){
						Map<String,Object> replysParams = new HashMap<String,Object>();
						replysParams.put("appId,"+SearchCondition.EQUAL, this.appId);
						replysParams.put("wxpReplyRule.id,"+SearchCondition.EQUAL, wxpReplyRule.getId());
						List<WxpReply> replys = this.myService.findAllByConditions(WxpReply.class, replysParams); 
						String keyword = match.getKeyword();
						if(this.msgReplyMap.containsKey(keyword)){
							List<WxpReply> list = this.msgReplyMap.get(keyword);
							list.addAll(replys);
							this.msgReplyMap.put(keyword, list);
						}else{
							List<WxpReply> list = new ArrayList<WxpReply>();
							list.addAll(replys);
							this.msgReplyMap.put(keyword, list);
						}
					}
				}
			}
		}
		//加载全局回复
		globalReply = myService.findEntityByAttribute(WxpReply.class, "replyMark", WxpReply.reply_mark_global, appId);
		
		//加载订阅回复
		subscribeReply = myService.findEntityByAttribute(WxpReply.class,"replyMark", WxpReply.reply_mark_subscribe, appId);
	}
}
