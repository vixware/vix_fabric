/**
 * 
 */
package com.vix.drp.MembershipCardmanagement.entity;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 会员卡日志信息(消费记录)
 * 
 * @author zhanghaibing
 * 
 * @date 2014-4-14
 */
public class MemberShipCardType extends BaseBOEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 963981276356963651L;
	/**
	 * 会员卡类型编码
	 */
	private String vipTypeId;
	/**
	 * 会员卡类型名称
	 */
	private String vipTypeName;
	/**
	 * 预售金额
	 */
	private Double preSaleAmount;
	/**
	 * 销售金额
	 */
	private Double salePrice;
	/**
	 * 是否有期限限制
	 */
	private String isValidity;
	/**
	 * 有效年数
	 */
	private Integer validYear;
	/**
	 * 有效月数
	 */
	private Integer validMonth;
	/**
	 * 有效天数
	 */
	private Integer validDay;
	/**
	 * 是否停用
	 */
	private String isStopUse;
	/**
	 * 是否要求售卡
	 */
	private String isRequireSale;
	/**
	 * 是否为IC卡
	 */
	private String isICCard;
	/**
	 * 顺序号
	 */
	private String seqid;
	/**
	 * 工本费
	 */
	private Double fee;
	/**
	 * 是否生日
	 */
	private String isBirthday;

	public String getVipTypeId() {
		return vipTypeId;
	}

	public void setVipTypeId(String vipTypeId) {
		this.vipTypeId = vipTypeId;
	}

	public String getVipTypeName() {
		return vipTypeName;
	}

	public void setVipTypeName(String vipTypeName) {
		this.vipTypeName = vipTypeName;
	}

	public Double getPreSaleAmount() {
		return preSaleAmount;
	}

	public void setPreSaleAmount(Double preSaleAmount) {
		this.preSaleAmount = preSaleAmount;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(String isValidity) {
		this.isValidity = isValidity;
	}

	public Integer getValidYear() {
		return validYear;
	}

	public void setValidYear(Integer validYear) {
		this.validYear = validYear;
	}

	public Integer getValidMonth() {
		return validMonth;
	}

	public void setValidMonth(Integer validMonth) {
		this.validMonth = validMonth;
	}

	public Integer getValidDay() {
		return validDay;
	}

	public void setValidDay(Integer validDay) {
		this.validDay = validDay;
	}

	public String getIsStopUse() {
		return isStopUse;
	}

	public void setIsStopUse(String isStopUse) {
		this.isStopUse = isStopUse;
	}

	public String getIsRequireSale() {
		return isRequireSale;
	}

	public void setIsRequireSale(String isRequireSale) {
		this.isRequireSale = isRequireSale;
	}

	public String getIsICCard() {
		return isICCard;
	}

	public void setIsICCard(String isICCard) {
		this.isICCard = isICCard;
	}

	public String getSeqid() {
		return seqid;
	}

	public void setSeqid(String seqid) {
		this.seqid = seqid;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getIsBirthday() {
		return isBirthday;
	}

	public void setIsBirthday(String isBirthday) {
		this.isBirthday = isBirthday;
	}

}
