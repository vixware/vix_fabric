package com.vix.common.share.entity;

import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 基础业务对象
 * 
 * @author Administrator
 * 
 */
public class BaseBOEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 条形码 */
	private String barCode;
	/** 审批人 */
	private String approver;
	/** 二维条形码 */
	private String barCodeTwoDimen;
	/** 自定义扩展字段1 */
	private String selfExtendStringField1;
	/** 自定义扩展字段2 */
	private String selfExtendStringField2;
	/** 自定义扩展字段3 */
	private String selfExtendStringField3;
	/** 自定义扩展字段4 */
	private String selfExtendStringField4;
	/** 自定义扩展字段5 */
	private String selfExtendStringField5;
	/** 自定义扩展字段6 */
	private String selfExtendStringField6;
	/** 自定义扩展字段7 */
	private Double selfExtendStringField7;
	/** 自定义扩展字段8 */
	private Double selfExtendStringField8;
	/** 自定义扩展字段9 */
	private Integer selfExtendStringField9;
	/** 自定义扩展字段10 */
	private Integer selfExtendStringField10;
	/** 自定义扩展字段11 */
	private Long selfExtendStringField11;
	/** 自定义扩展字段12 */
	private Long selfExtendStringField12;
	/** 自定义扩展字段13 */
	private String selfExtendXMLField1;
	/** 自定义扩展字段14 */
	private String selfExtendXMLField2;
	/** 使用机器IP */
	private String ipAddress;
	/** 流程阶段 */
	private String bizflowPhase;
	/** 删除标志  1删除；0不删除*/
	private String isDeleted = "0";
	/**
	 * 产品渠道/经销商/代理销售商/门店 类型: 渠道、分销商、代理商、门店
	 */
	protected ChannelDistributor channelDistributor;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getBarCodeTwoDimen() {
		return barCodeTwoDimen;
	}

	public void setBarCodeTwoDimen(String barCodeTwoDimen) {
		this.barCodeTwoDimen = barCodeTwoDimen;
	}

	public String getSelfExtendStringField1() {
		return selfExtendStringField1;
	}

	public void setSelfExtendStringField1(String selfExtendStringField1) {
		this.selfExtendStringField1 = selfExtendStringField1;
	}

	public String getSelfExtendStringField2() {
		return selfExtendStringField2;
	}

	public void setSelfExtendStringField2(String selfExtendStringField2) {
		this.selfExtendStringField2 = selfExtendStringField2;
	}

	public String getSelfExtendStringField3() {
		return selfExtendStringField3;
	}

	public void setSelfExtendStringField3(String selfExtendStringField3) {
		this.selfExtendStringField3 = selfExtendStringField3;
	}

	public String getSelfExtendStringField4() {
		return selfExtendStringField4;
	}

	public void setSelfExtendStringField4(String selfExtendStringField4) {
		this.selfExtendStringField4 = selfExtendStringField4;
	}

	public String getSelfExtendStringField5() {
		return selfExtendStringField5;
	}

	public void setSelfExtendStringField5(String selfExtendStringField5) {
		this.selfExtendStringField5 = selfExtendStringField5;
	}

	public String getSelfExtendStringField6() {
		return selfExtendStringField6;
	}

	public void setSelfExtendStringField6(String selfExtendStringField6) {
		this.selfExtendStringField6 = selfExtendStringField6;
	}

	public Double getSelfExtendStringField7() {
		return selfExtendStringField7;
	}

	public void setSelfExtendStringField7(Double selfExtendStringField7) {
		this.selfExtendStringField7 = selfExtendStringField7;
	}

	public Double getSelfExtendStringField8() {
		return selfExtendStringField8;
	}

	public void setSelfExtendStringField8(Double selfExtendStringField8) {
		this.selfExtendStringField8 = selfExtendStringField8;
	}

	public Integer getSelfExtendStringField9() {
		return selfExtendStringField9;
	}

	public void setSelfExtendStringField9(Integer selfExtendStringField9) {
		this.selfExtendStringField9 = selfExtendStringField9;
	}

	public Integer getSelfExtendStringField10() {
		return selfExtendStringField10;
	}

	public void setSelfExtendStringField10(Integer selfExtendStringField10) {
		this.selfExtendStringField10 = selfExtendStringField10;
	}

	public Long getSelfExtendStringField11() {
		return selfExtendStringField11;
	}

	public void setSelfExtendStringField11(Long selfExtendStringField11) {
		this.selfExtendStringField11 = selfExtendStringField11;
	}

	public Long getSelfExtendStringField12() {
		return selfExtendStringField12;
	}

	public void setSelfExtendStringField12(Long selfExtendStringField12) {
		this.selfExtendStringField12 = selfExtendStringField12;
	}

	public String getSelfExtendXMLField1() {
		return selfExtendXMLField1;
	}

	public void setSelfExtendXMLField1(String selfExtendXMLField1) {
		this.selfExtendXMLField1 = selfExtendXMLField1;
	}

	public String getSelfExtendXMLField2() {
		return selfExtendXMLField2;
	}

	public void setSelfExtendXMLField2(String selfExtendXMLField2) {
		this.selfExtendXMLField2 = selfExtendXMLField2;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getBizflowPhase() {
		return bizflowPhase;
	}

	public void setBizflowPhase(String bizflowPhase) {
		this.bizflowPhase = bizflowPhase;
	}

	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	@Override
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
