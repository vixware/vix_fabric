package com.vix.mdm.crm.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 会员卡定义
 * 
 * @类全名 com.vix.mdm.crm.entity.CardEntity
 * 
 * @author zhanghaibing
 *
 * @date 2017年10月27日
 */
public class CardEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 销售金额
	 */
	private Double payMoney;
	/**
	 * 是否挂失:Y,是 N,否
	 */
	private String isReport;
	/**
	 * 是否可以储值:Y,是 N,否
	 */
	private String isStoredValue;
	/**
	 * 会员卡类型 1,余额卡 ;2,次卡3,余额+次数
	 */
	private String type;

	private String pictureUrl;
	/** 有效期  */
	private Date expiryDate;

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getIsStoredValue() {
		return isStoredValue;
	}

	public void setIsStoredValue(String isStoredValue) {
		this.isStoredValue = isStoredValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public String getExpiryDateStr() {
		if(expiryDate != null){
			return DateUtil.format(expiryDate);
		}
		return "";
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
