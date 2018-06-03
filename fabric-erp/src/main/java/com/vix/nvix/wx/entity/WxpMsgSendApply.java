package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 群发申请
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMsgSendApply
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpMsgSendApply extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** *群发申请标题 */
	private String title;

	/** *计划发送时间 */
	private String sendTime;

	/** *审批状态，0待审批，1通过，-1驳回 */
	private int isApprove;

	/**
	 * *text,news,其他类型待定 ,
	 * 与msgSend的type一样，article,news,text,image,voice,video,music
	 */
	private String contentType;

	/** *文本内容，文本类型时有效 */
	private String textContent;

	/**
	 * 文章id/title，json格式，
	 * 与msgSend的msgJson一样，消息相关内容json格式，text就是text，article是文章序列，news、
	 * image等是对应表的id article:
	 * {"msg_type":"article","article_list":[{"article_id":1,"article_title":
	 * "abc"},{"article_id":2,"article_title":"def"}]}
	 */
	private String contentJson;

	/** *申请时间 */
	private Date createTime;

	/** *审批时间 */
	private Date approveTime;

	/** *门店id，null的话就是当前微信号 */
	// private VipShopInfo shopInfo;

	/** *wxp_msg_send,审批通过后创建 */
	private WxpMsgSend msgSend;

	/** *创建人id */
	private Employee createUser;

	/** *审批人id */
	private Employee approveUser;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(int isApprove) {
		this.isApprove = isApprove;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getContentJson() {
		return contentJson;
	}

	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	/*
	 * public VipShopInfo getShopInfo() { return shopInfo; } public void
	 * setShopInfo(VipShopInfo shopInfo) { this.shopInfo = shopInfo; }
	 */
	public WxpMsgSend getMsgSend() {
		return msgSend;
	}

	public void setMsgSend(WxpMsgSend msgSend) {
		this.msgSend = msgSend;
	}

	public Employee getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Employee createUser) {
		this.createUser = createUser;
	}

	public Employee getApproveUser() {
		return approveUser;
	}

	public void setApproveUser(Employee approveUser) {
		this.approveUser = approveUser;
	}
}
