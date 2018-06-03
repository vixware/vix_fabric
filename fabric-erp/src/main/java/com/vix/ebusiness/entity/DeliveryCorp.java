/*
 * Copyright 2009 CloudSoft, Inc. All rights reserved.
 * Website: http://www.kloudsoft.cn
 */

package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-21
 */
public class DeliveryCorp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3385465142054637238L;
	private Long corpId;
	private String corpName;
	private String corpCode;
	private String queryCode;
	private String jingdongCode;
	private String paipaiCode;
	private String dangdangxode;
	private String amazonCode;
	private String yihaodianCode;
	private String vanclCode;
	private String rakutenCode;
	private String mosamasoCode;
	private String m18Code;
	private String type;
	private Integer state;
	private Integer orderNum;
	private String webSite;
	private String taobaoCode;
	private String contactPhone;
	private Integer isDefault;
	private Integer priority;

	public Long getCorpId() {
		return corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getJingdongCode() {
		return jingdongCode;
	}

	public void setJingdongCode(String jingdongCode) {
		this.jingdongCode = jingdongCode;
	}

	public String getPaipaiCode() {
		return paipaiCode;
	}

	public void setPaipaiCode(String paipaiCode) {
		this.paipaiCode = paipaiCode;
	}

	public String getDangdangxode() {
		return dangdangxode;
	}

	public void setDangdangxode(String dangdangxode) {
		this.dangdangxode = dangdangxode;
	}

	public String getAmazonCode() {
		return amazonCode;
	}

	public void setAmazonCode(String amazonCode) {
		this.amazonCode = amazonCode;
	}

	public String getYihaodianCode() {
		return yihaodianCode;
	}

	public void setYihaodianCode(String yihaodianCode) {
		this.yihaodianCode = yihaodianCode;
	}

	public String getVanclCode() {
		return vanclCode;
	}

	public void setVanclCode(String vanclCode) {
		this.vanclCode = vanclCode;
	}

	public String getRakutenCode() {
		return rakutenCode;
	}

	public void setRakutenCode(String rakutenCode) {
		this.rakutenCode = rakutenCode;
	}

	public String getMosamasoCode() {
		return mosamasoCode;
	}

	public void setMosamasoCode(String mosamasoCode) {
		this.mosamasoCode = mosamasoCode;
	}

	public String getM18Code() {
		return m18Code;
	}

	public void setM18Code(String m18Code) {
		this.m18Code = m18Code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getTaobaoCode() {
		return taobaoCode;
	}

	public void setTaobaoCode(String taobaoCode) {
		this.taobaoCode = taobaoCode;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
