package com.vix.nvix.wx.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.vix.common.share.entity.BaseEntity;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.wx.util.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserMessage
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpUserMessage extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static String MSG_TYPE_TEXT = "text";
	public static String MSG_TYPE_IMAGE = "image";
	public static String MSG_TYPE_VOICE = "voice";
	public static String MSG_TYPE_VIDEO = "video";
	public static String MSG_TYPE_LOCATION = "location";
	public static String MSG_TYPE_LINK = "link";
	public static String MSG_TYPE_EVENT = "event";

	public static String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	public static String EVENT_TYPE_SCAN = "scan";
	public static String EVENT_TYPE_LOCATION = "location";
	public static String EVENT_TYPE_CLICK = "click";
	public static String EVENT_TYPE_VIEW = "view";
	public static String EVENT_TYPE_MSG = "msg"; // 非微信传值，当用户信息为消息而非事件时，程序赋值
	public static String EVENT_TYPE_OTHER = "other"; // 非微信传值，当用户信息为参与活动消息时，程序赋值

	// 主键是msgId
	private String msgId; // 消息id,64位整型，如消息为事件，此值为FromUserName+CreateTime',
	private String appId;
	private String toUserName; // 开发者微信号',
	private String createTimeOri; // 微信传送的数字型消息时间
	private Date createTime; // 消息创建时间',
	private String msgType; // text,image,voice,video,location,link,event',
	private String event; // subscribe,unsubscribe,SCAN,LOCATION,CLICK,VIEW,用户消息时保存msg',
	private String content; // 文本消息内容',
	private String picUrl; // 图片链接',
	private String mediaId; // 媒体id，可以调用多媒体文件下载接口拉取数据,图片，语音，视频',
	private String voiceFormat; // 语音格式，如amr，speex等',
	private String voiceRecognition; // 如果开启了语音识别，语音识别结果，UTF8编码
	private String videoThumbMediaId; // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据',
	private Double locationX; // 地理位置维度',
	private Double locationY; // 地理位置经度',
	private Double locationScale; // 地图缩放大小',
	private String locationLabel; // 地理位置信息',
	private String linkTitle; // 消息标题',
	private String linkDescription; // 消息描述',
	private String linkUrl; // 消息链接',
	private String eventKey; // 二维码场景标识，自定义菜单key，菜单跳转url',
	private String eventTicket; // 二维码的ticket，可用来换取二维码图片',
	private Double xyLatitude; // 地理位置纬度',
	private Double xyLongitude; // 地理位置经度',
	private Double xyPrecision; // 地理位置精度',

	private Integer isRead = 0; // 是否已读
	private Integer directReplyCount = 0; // 直接回复消息量
	WxpUser user; // 发送方帐号

	private String replyRecordId;

	private boolean isSubscribe;
	private boolean isUnSubscribe;
	private boolean isQrCode; // 是否扫二维码事件
	private boolean isMediaMsg; // 是否需要通过media_id获取信息

	private boolean isEventMsg;
	private boolean isMenuClick;
	private boolean isMenuJumpUrl; // 是否url菜单
	private boolean isLocation;

	private String mediaFileId;

	private Integer markStar = 0; // 标星

	private Integer matchReply = 0; // 是否有匹配消息回复

	/**
	 * 消息/事件xml属性,2014-04-10
	 * 
	 * 普通消息 ToUserName,FromUserName,CreateTime,MsgType,Content,MsgId 图片消息
	 * ToUserName,FromUserName,CreateTime,MsgType,PicUrl,MediaId,MsgId 语音消息
	 * ToUserName,FromUserName,CreateTime,MsgType,MediaId,Format,MsgId 视频消息
	 * ToUserName,FromUserName,CreateTime,MsgType,MediaId,ThumbMediaId,MsgId
	 * 地理位置消息
	 * ToUserName,FromUserName,CreateTime,MsgType,Location_X,Location_Y,Scale,
	 * Label,MsgId 链接消息
	 * ToUserName,FromUserName,CreateTime,MsgType,Title,Description,Url,MsgId
	 * 
	 * 语音识别
	 * ToUserName,FromUserName,CreateTime,MsgType,MediaId,Format,Recognition,
	 * MsgID
	 * 
	 * 关注/取消关注 ToUserName,FromUserName,CreateTime,MsgType,Event 扫描二维码,未关注
	 * ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey,Ticket
	 * 扫描二维码,已关注
	 * ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey,Ticket 上报地理位置
	 * ToUserName,FromUserName,CreateTime,MsgType,Event,Latitude,Longitude,
	 * Precision 自定义菜单 ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey
	 * 点击菜单跳转链接 ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey
	 * 
	 * MsgType:text,image,voice,video,location,link,event
	 * Event:subscribe,unsubscribe,SCAN,LOCATION,CLICK,VIEW,msg
	 */
	private void loadXmlDataToAttribute(Document xmlData, IVixntBaseService workService) {
		// 通用属性
		Element root = xmlData.getRootElement();
		// this.user = new WxpUser();
		String fromUserName = root.elementText("FromUserName");
		// this.user.setOpenId(fromUserName);
		// String openId = user.getOpenId();
		try {
			this.user = workService.findEntityByAttribute(WxpUser.class, "openId", fromUserName);
			System.out.println("user：" + user);
			this.toUserName = root.elementText("ToUserName");
			this.msgType = root.elementTextTrim("MsgType");
			this.createTimeOri = root.elementTextTrim("CreateTime");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = dateFormat.format(Long.parseLong(this.createTimeOri) * 1000);
			super.setCreateTime(dateFormat.parse(dateStr));
			this.createTime = dateFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("msgType：" + msgType);
		if (WxpUserMessage.MSG_TYPE_EVENT.equalsIgnoreCase(this.msgType)) {
			// 事件属性
			this.isEventMsg = true;

			this.msgId = this.appId + "_" + fromUserName + this.createTimeOri;
			this.event = root.elementTextTrim("Event");

			if (WxpUserMessage.EVENT_TYPE_SUBSCRIBE.equalsIgnoreCase(this.event)) {
				this.isSubscribe = true;

				// 如果扫二维码关注则有eventKey和ticket
				this.eventKey = root.elementTextTrim("EventKey");
				// if(this.eventKey!=null && this.eventKey.length()>0)
				this.eventTicket = root.elementTextTrim("Ticket");
				if (StringUtils.isNotEmpty(this.eventTicket))
					this.isQrCode = true;
			} else if (WxpUserMessage.EVENT_TYPE_UNSUBSCRIBE.equalsIgnoreCase(this.event)) {
				this.isUnSubscribe = true;
			} else if (WxpUserMessage.EVENT_TYPE_SCAN.equalsIgnoreCase(this.event)) {
				this.eventKey = root.elementTextTrim("EventKey");
				this.eventTicket = root.elementTextTrim("Ticket");
				this.isQrCode = true;
			} else if (WxpUserMessage.EVENT_TYPE_LOCATION.equalsIgnoreCase(this.event)) {
				this.isLocation = true;
				try {
					this.xyLatitude = Double.parseDouble(root.elementTextTrim("Latitude"));
					this.xyLongitude = Double.parseDouble(root.elementTextTrim("Longitude"));
					this.xyPrecision = Double.parseDouble(root.elementTextTrim("Precision"));
				} catch (Exception le) {
					le.printStackTrace();
				}
			} else if (WxpUserMessage.EVENT_TYPE_CLICK.equalsIgnoreCase(this.event)) {
				this.isMenuClick = true;
				this.eventKey = root.elementTextTrim("EventKey");
			} else if (WxpUserMessage.EVENT_TYPE_VIEW.equalsIgnoreCase(this.event)) {
				this.eventKey = root.elementTextTrim("EventKey");
				this.isMenuJumpUrl = true;
			}
		} else {
			// 消息属性
			this.msgId = this.appId + "_" + root.elementTextTrim("MsgId");
			// 系统内自定义event属性
			this.event = WxpUserMessage.EVENT_TYPE_MSG;

			if (WxpUserMessage.MSG_TYPE_TEXT.equalsIgnoreCase(this.msgType)) {
				// 普通消息 ToUserName,FromUserName,CreateTime,MsgType,Content,MsgId
				this.content = root.elementTextTrim("Content");
			} else if (WxpUserMessage.MSG_TYPE_IMAGE.equalsIgnoreCase(this.msgType)) {
				// 图片消息
				// ToUserName,FromUserName,CreateTime,MsgType,PicUrl,MediaId,MsgId
				this.picUrl = root.elementTextTrim("PicUrl");
				this.mediaId = root.elementTextTrim("MediaId");
				this.isMediaMsg = true;
			} else if (WxpUserMessage.MSG_TYPE_VOICE.equalsIgnoreCase(this.msgType)) {
				// 语音消息
				// ToUserName,FromUserName,CreateTime,MsgType,MediaId,Format,MsgId
				this.mediaId = root.elementTextTrim("MediaId");
				this.voiceFormat = root.elementTextTrim("Format");
				this.voiceRecognition = root.elementTextTrim("Recognition");
				this.isMediaMsg = true;
			} else if (WxpUserMessage.MSG_TYPE_VIDEO.equalsIgnoreCase(this.msgType)) {
				// 视频消息
				// ToUserName,FromUserName,CreateTime,MsgType,MediaId,ThumbMediaId,MsgId
				this.mediaId = root.elementTextTrim("MediaId");
				this.videoThumbMediaId = root.elementTextTrim("ThumbMediaId");
				this.isMediaMsg = true;
			} else if (WxpUserMessage.MSG_TYPE_LOCATION.equalsIgnoreCase(this.msgType)) {
				// 地理位置消息
				// ToUserName,FromUserName,CreateTime,MsgType,Location_X,Location_Y,Scale,Label,MsgId
				this.isLocation = true;
				try {
					this.locationX = Double.parseDouble(root.elementTextTrim("Location_X"));
					this.locationY = Double.parseDouble(root.elementTextTrim("Location_Y"));
					this.locationScale = Double.parseDouble(root.elementTextTrim("Scale"));
					this.locationLabel = root.elementTextTrim("Label");
				} catch (Exception le) {
					le.printStackTrace();
				}
			} else if (WxpUserMessage.MSG_TYPE_LINK.equalsIgnoreCase(this.msgType)) {
				// 链接消息
				// ToUserName,FromUserName,CreateTime,MsgType,Title,Description,Url,MsgId
				this.linkTitle = root.elementTextTrim("Title");
				this.linkDescription = root.elementTextTrim("Description");
				this.linkUrl = root.elementTextTrim("Url");
			}
		}

		if (StringUtils.isEmpty(this.msgId)) {
			// 现在msgId是主键，如果没有赋值，自动生成一个，前缀为gen_
			this.msgId = "gen_" + StrUtils.genShortUUID();
		}
	}

	public WxpUserMessage() {
	}

	public WxpUserMessage(String xmlStr, String appId, IVixntBaseService workService) {
		if (xmlStr == null || "".equals(xmlStr.trim()))
			return;

		this.setAppId(appId);
		;
		Document xmlData = null;
		try {
			xmlData = DocumentHelper.parseText(xmlStr);
		} catch (Exception exml) {
			exml.printStackTrace();
			return;
		}
		if (null == xmlData) {
			return;
		}
		System.out.println("xmlData：" + xmlData);
		this.loadXmlDataToAttribute(xmlData, workService);
	}

	public String getTyptStr() {
		if (WxpUserMessage.MSG_TYPE_TEXT.equals(this.msgType)) {
			return "文本";
		} else if (WxpUserMessage.MSG_TYPE_IMAGE.equals(this.msgType)) {
			return "图片";
		} else if (WxpUserMessage.MSG_TYPE_VOICE.equals(this.msgType)) {
			return "语音";
		} else if (WxpUserMessage.MSG_TYPE_VIDEO.equals(this.msgType)) {
			return "视频";
		} else if (WxpUserMessage.MSG_TYPE_LINK.equals(this.msgType)) {
			return "链接";
		} else if (WxpUserMessage.MSG_TYPE_LOCATION.equals(this.msgType)) {
			// 坐标信息
			return "坐标";
		} else if (WxpUserMessage.MSG_TYPE_EVENT.equals(this.msgType)) {
			// 有必要的话可以细化一下
			if (WxpUserMessage.EVENT_TYPE_CLICK.equals(this.getEvent())) {
				return "菜单";
			} else if (WxpUserMessage.EVENT_TYPE_SCAN.equals(this.getEvent())) {
				return "扫码";
			} else if (WxpUserMessage.EVENT_TYPE_LOCATION.equals(this.getEvent())) {
				// 上报自己的位置信息
				return "位置";
			} else if (WxpUserMessage.EVENT_TYPE_SUBSCRIBE.equals(this.getEvent())) {
				return "订阅";
			} else if (WxpUserMessage.EVENT_TYPE_UNSUBSCRIBE.equals(this.getEvent())) {
				return "退订";
			} else if (WxpUserMessage.EVENT_TYPE_VIEW.equals(this.getEvent())) {
				return "跳转";
			} else {
				return "位置";
			}
		} else {
			return "未知";
		}
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getVoiceFormat() {
		return voiceFormat;
	}

	public void setVoiceFormat(String voiceFormat) {
		this.voiceFormat = voiceFormat;
	}

	public String getVideoThumbMediaId() {
		return videoThumbMediaId;
	}

	public void setVideoThumbMediaId(String videoThumbMediaId) {
		this.videoThumbMediaId = videoThumbMediaId;
	}

	public Double getLocationX() {
		return locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public Double getLocationY() {
		return locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public Double getLocationScale() {
		return locationScale;
	}

	public void setLocationScale(Double locationScale) {
		this.locationScale = locationScale;
	}

	public String getLocationLabel() {
		return locationLabel;
	}

	public void setLocationLabel(String locationLabel) {
		this.locationLabel = locationLabel;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkDescription() {
		return linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getEventTicket() {
		return eventTicket;
	}

	public void setEventTicket(String eventTicket) {
		this.eventTicket = eventTicket;
	}

	public String getVoiceRecognition() {
		return voiceRecognition;
	}

	public void setVoiceRecognition(String voiceRecognition) {
		this.voiceRecognition = voiceRecognition;
	}

	public boolean isSubscribe() {
		return isSubscribe;
	}

	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public boolean isUnSubscribe() {
		return isUnSubscribe;
	}

	public void setUnSubscribe(boolean isUnSubscribe) {
		this.isUnSubscribe = isUnSubscribe;
	}

	public boolean isMenuClick() {
		return isMenuClick;
	}

	public void setMenuClick(boolean isMenuClick) {
		this.isMenuClick = isMenuClick;
	}

	public boolean isLocation() {
		return isLocation;
	}

	public void setLocation(boolean isLocation) {
		this.isLocation = isLocation;
	}

	public String getCreateTimeOri() {
		return createTimeOri;
	}

	public void setCreateTimeOri(String createTimeOri) {
		this.createTimeOri = createTimeOri;
	}

	public boolean isQrCode() {
		return isQrCode;
	}

	public void setQrCode(boolean isQrCode) {
		this.isQrCode = isQrCode;
	}

	public boolean isMenuJumpUrl() {
		return isMenuJumpUrl;
	}

	public void setMenuJumpUrl(boolean isMenuJumpUrl) {
		this.isMenuJumpUrl = isMenuJumpUrl;
	}

	public Double getXyLatitude() {
		return xyLatitude;
	}

	public void setXyLatitude(Double xyLatitude) {
		this.xyLatitude = xyLatitude;
	}

	public Double getXyLongitude() {
		return xyLongitude;
	}

	public void setXyLongitude(Double xyLongitude) {
		this.xyLongitude = xyLongitude;
	}

	public Double getXyPrecision() {
		return xyPrecision;
	}

	public void setXyPrecision(Double xyPrecision) {
		this.xyPrecision = xyPrecision;
	}

	public Integer getMarkStar() {
		return markStar;
	}

	public void setMarkStar(Integer markStar) {
		this.markStar = markStar;
	}

	public WxpUser getUser() {
		return user;
	}

	public void setUser(WxpUser user) {
		this.user = user;
	}

	public Integer getMatchReply() {
		return matchReply;
	}

	public void setMatchReply(Integer matchReply) {
		this.matchReply = matchReply;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getDirectReplyCount() {
		return directReplyCount;
	}

	public void setDirectReplyCount(Integer directReplyCount) {
		this.directReplyCount = directReplyCount;
	}

	public String getReplyRecordId() {
		return replyRecordId;
	}

	public void setReplyRecordId(String replyRecordId) {
		this.replyRecordId = replyRecordId;
	}

	public String getMediaFileId() {
		return mediaFileId;
	}

	public void setMediaFileId(String mediaFileId) {
		this.mediaFileId = mediaFileId;
	}

	public boolean isMediaMsg() {
		return isMediaMsg;
	}

	public void setMediaMsg(boolean isMediaMsg) {
		this.isMediaMsg = isMediaMsg;
	}

	public boolean isEventMsg() {
		return isEventMsg;
	}

	public void setEventMsg(boolean isEventMsg) {
		this.isEventMsg = isEventMsg;
	}

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public void setAppId(String appId) {
		this.appId = appId;
	}
}
