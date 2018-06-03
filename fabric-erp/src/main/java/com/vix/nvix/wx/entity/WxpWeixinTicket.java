package com.vix.nvix.wx.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.diandoc.base.entity.WxpWeixinTicket
 *
 * @author zhanghaibing
 *
 * @date 2017年3月28日
 */
public class WxpWeixinTicket extends BaseEntity {
	private static final long serialVersionUID = 1L;
	String qiyeCorpId; // 企业号id
	String qiyeTicket; // jsapi_ticket
	Date qiyeTokenExpireTime;

	public boolean hasQiyeAccount() {
		return StringUtils.isNotEmpty(this.qiyeCorpId);
	}

	public boolean needReloadQiyeTicket() {
		if (StringUtils.isEmpty(this.qiyeTicket)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.qiyeTokenExpireTime == null || timeMill >= this.qiyeTokenExpireTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the qiyeCorpId
	 */
	public String getQiyeCorpId() {
		return qiyeCorpId;
	}

	/**
	 * @param qiyeCorpId
	 *            the qiyeCorpId to set
	 */
	public void setQiyeCorpId(String qiyeCorpId) {
		this.qiyeCorpId = qiyeCorpId;
	}

	/**
	 * @return the qiyeTicket
	 */
	public String getQiyeTicket() {
		return qiyeTicket;
	}

	/**
	 * @param qiyeTicket
	 *            the qiyeTicket to set
	 */
	public void setQiyeTicket(String qiyeTicket) {
		this.qiyeTicket = qiyeTicket;
	}

	/**
	 * @return the qiyeTokenExpireTime
	 */
	public Date getQiyeTokenExpireTime() {
		return qiyeTokenExpireTime;
	}

	/**
	 * @param qiyeTokenExpireTime
	 *            the qiyeTokenExpireTime to set
	 */
	public void setQiyeTokenExpireTime(Date qiyeTokenExpireTime) {
		this.qiyeTokenExpireTime = qiyeTokenExpireTime;
	}

}