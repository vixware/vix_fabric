package com.vix.drp.rides.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 机台交易信息
 * 
 * @author bjitzhang
 *
 */
public class TranLog extends BaseBOEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String msgguid;
	/**
	 * 客户端
	 */
	private String mandt;
	/**
	 * 会员卡号
	 */
	private String vipCardId;

	/**
	 * 机台编号
	 */
	private String macId;

	/**
	 * 机台名称
	 */
	private String macName;

	/**
	 * 机台类型编号
	 */
	private String macTypeId;
	/**
	 * 机台类型名称
	 */
	private String macTypeName;
	/**
	 * 机台大类编码
	 */
	private String macDepartId;
	/**
	 * 机台大类名称
	 */
	private String macDepartName;
	/**
	 * 货币数量
	 */
	private Double amount;
	/**
	 * 登记日期
	 */
	private Date regDate;
	/**
	 * 登记时间
	 */
	private Date regTime;
	/**
	 * 营业日期
	 */
	private Date tradeDate;

	public String getMsgguid() {
		return msgguid;
	}

	public void setMsgguid(String msgguid) {
		this.msgguid = msgguid;
	}

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getVipCardId() {
		return vipCardId;
	}

	public void setVipCardId(String vipCardId) {
		this.vipCardId = vipCardId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public String getMacTypeId() {
		return macTypeId;
	}

	public void setMacTypeId(String macTypeId) {
		this.macTypeId = macTypeId;
	}

	public String getMacTypeName() {
		return macTypeName;
	}

	public void setMacTypeName(String macTypeName) {
		this.macTypeName = macTypeName;
	}

	public String getMacDepartId() {
		return macDepartId;
	}

	public void setMacDepartId(String macDepartId) {
		this.macDepartId = macDepartId;
	}

	public String getMacDepartName() {
		return macDepartName;
	}

	public void setMacDepartName(String macDepartName) {
		this.macDepartName = macDepartName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

}
