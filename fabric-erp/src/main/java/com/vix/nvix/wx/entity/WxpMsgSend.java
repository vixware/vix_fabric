package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMsgSend
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpMsgSend extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String title; // 群发备注名称
	private String msgType; // article,news,text,image,voice,video,music,material
	private String msgJson; // 消息相关内容json格式，text就是text，article是文章序列，news、image等是对应表的id
	private String mediaId; // 上传到腾讯服务器后的mediaId（类型：article,news,image,voice,video,music）
	private String textContent; // 文本发送内容(类型：text)
	private int isReady; // 是否完成初始化，可以发送
	private Integer isSent = 0; // DEFAULT 0
	private Integer targetType; // 0:all;1:group;2:tag;3:openIds
	private String targetIds; // 所选的标签id、openId，逗号分隔
	private String createUserId; // 创建人
	private Integer isNowSent = 0;
	private Date sendTime; // 发送时间
	private String sendMsgId; // 发送后消息id
	private Integer isApply;
	private String scheduleJobId;
	private WxpReply wxpReply;

	public String getSendTimeStr() {
		String sendTimeStr = "";
		if (sendTime != null) {
			sendTimeStr = DateUtil.formatTime(sendTime);
		}
		return sendTimeStr;
	}

	public String getSendDateStr() {
		String sendDateStr = "";
		if (sendTime != null) {
			sendDateStr = DateUtil.formatTime(sendTime);
			sendDateStr = sendDateStr.substring(0, 10);
		}
		return sendDateStr;
	}

	public String getSendTimesStr() {
		String sendTimesStr = "";
		if (sendTime != null) {
			sendTimesStr = DateUtil.formatTime(sendTime);
			sendTimesStr = sendTimesStr.substring(11);
		}
		return sendTimesStr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getIsSent() {
		return isSent;
	}

	public void setIsSent(Integer isSent) {
		this.isSent = isSent;
	}

	public Integer getTargetType() {
		return targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public String getTargetIds() {
		return targetIds;
	}

	public void setTargetIds(String targetIds) {
		this.targetIds = targetIds;
	}

	public String getMsgJson() {
		return msgJson;
	}

	public void setMsgJson(String msgJson) {
		this.msgJson = msgJson;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public int getIsReady() {
		return isReady;
	}

	public void setIsReady(int isReady) {
		this.isReady = isReady;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendMsgId() {
		return sendMsgId;
	}

	public void setSendMsgId(String sendMsgId) {
		this.sendMsgId = sendMsgId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getIsNowSent() {
		return isNowSent;
	}

	public void setIsNowSent(Integer isNowSent) {
		this.isNowSent = isNowSent;
	}

	public WxpReply getWxpReply() {
		return wxpReply;
	}

	public void setWxpReply(WxpReply wxpReply) {
		this.wxpReply = wxpReply;
	}

	public Integer getIsApply() {
		return isApply;
	}

	public void setIsApply(Integer isApply) {
		this.isApply = isApply;
	}

	public String getScheduleJobId() {
		return scheduleJobId;
	}

	public void setScheduleJobId(String scheduleJobId) {
		this.scheduleJobId = scheduleJobId;
	}
}
