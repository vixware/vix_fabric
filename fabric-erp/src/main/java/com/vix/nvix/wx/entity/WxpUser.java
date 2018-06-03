package com.vix.nvix.wx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUser
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpUser extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final int direct_reply_status_good = 0;
	public static final int direct_reply_status_bad_unsubscribe = 1; // 未关注
	public static final int direct_reply_status_bad_no_opt = 2; // 未操作
	public static final int direct_reply_status_bad_timeout = 3; // 已超时

	String openId; // 用户的标识，对当前公众号唯一
	int subscribe; // 1：关注，0：未关注
	Date subscribeTime; // 关注时间
	Date unSubscribeTime;

	Integer msgCount; // 用户消息数量
	Integer replyCount; // 回复用户消息数量（用户发送消息时立即回复的）
	Integer directReplyCount; // 直接回复数量（客服消息）
	Integer viewCount; // 点击菜单跳转链接次数

	String note;
	int markStar;

	String nickName; // 昵称
	Integer sex; // 1男，2女
	String city; // 城市
	String country; // 国家
	String province; // 省份
	String language; // 语言：zh_CN
	String headImgUrl; // 头像,最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空

	Date lastMessageTime; // 最后消息时间，可以直接回复
							// 包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权

	String oauthAccessToken; // 页面oauth使用的access_token
	Date oauthTokenExpireTime; // 页面oauth使用的access_token过期时间
	String oauthRefreshToken; // access_token过期后使用refresh_token刷新

	String remarkName; // 备注名称
	Integer isStar; // 是否加星

	Integer groupSendCount;
	String groupSendMonth;

	Set<WxpUserTag> userTags = new HashSet<WxpUserTag>();

	public String getShowName() {
		String name = null;
		if (StringUtils.isNotEmpty(this.remarkName))
			name = this.remarkName + " [" + this.nickName + "]";
		else
			name = this.getNickName();

		return name;
	}

	public boolean hasRemarkName() {
		return StringUtils.isNotEmpty(this.remarkName);
	}

	public int directReplyStatus() {
		if (this.subscribe != 1)
			return WxpUser.direct_reply_status_bad_unsubscribe; // 未关注

		if (this.lastMessageTime == null)
			return WxpUser.direct_reply_status_bad_no_opt; // 未操作

		long replyBefore = this.lastMessageTime.getTime() + 48L * 3600000L;// 48小时内
		if (System.currentTimeMillis() > replyBefore) {
			return WxpUser.direct_reply_status_bad_timeout; // 已超时
		} else {
			return WxpUser.direct_reply_status_good;
		}
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public Date getUnSubscribeTime() {
		return unSubscribeTime;
	}

	public void setUnSubscribeTime(Date unSubscribeTime) {
		this.unSubscribeTime = unSubscribeTime;
	}

	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getDirectReplyCount() {
		return directReplyCount;
	}

	public void setDirectReplyCount(Integer directReplyCount) {
		this.directReplyCount = directReplyCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getMarkStar() {
		return markStar;
	}

	public void setMarkStar(int markStar) {
		this.markStar = markStar;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Date getLastMessageTime() {
		return lastMessageTime;
	}

	public void setLastMessageTime(Date lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}

	public String getOauthAccessToken() {
		return oauthAccessToken;
	}

	public void setOauthAccessToken(String oauthAccessToken) {
		this.oauthAccessToken = oauthAccessToken;
	}

	public Date getOauthTokenExpireTime() {
		return oauthTokenExpireTime;
	}

	public void setOauthTokenExpireTime(Date oauthTokenExpireTime) {
		this.oauthTokenExpireTime = oauthTokenExpireTime;
	}

	public String getOauthRefreshToken() {
		return oauthRefreshToken;
	}

	public void setOauthRefreshToken(String oauthRefreshToken) {
		this.oauthRefreshToken = oauthRefreshToken;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public Integer getIsStar() {
		return isStar;
	}

	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}

	public Integer getGroupSendCount() {
		return groupSendCount;
	}

	public void setGroupSendCount(Integer groupSendCount) {
		this.groupSendCount = groupSendCount;
	}

	public String getGroupSendMonth() {
		return groupSendMonth;
	}

	public void setGroupSendMonth(String groupSendMonth) {
		this.groupSendMonth = groupSendMonth;
	}

	public Set<WxpUserTag> getUserTags() {
		return userTags;
	}

	public void setUserTags(Set<WxpUserTag> userTags) {
		this.userTags = userTags;
	}
}
