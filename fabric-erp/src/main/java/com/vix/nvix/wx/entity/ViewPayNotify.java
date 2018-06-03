package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.excel.ExcelVOAttribute;

public class ViewPayNotify extends BaseEntity {
	// 付款通知视图，门店、产品、微信用户、总价、付款交易号、本地交易号、时间

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExcelVOAttribute(name = "门店", column = "A", isExport = true, prompt = "导入失败")
	private String productMendianTitle;

	@ExcelVOAttribute(name = "产品", column = "B", isExport = true, prompt = "导入失败")
	private String productTitle;

	@ExcelVOAttribute(name = "微信用户", column = "C", isExport = true, prompt = "导入失败")
	private String nickName;

	@ExcelVOAttribute(name = "总价(元)", column = "D", isExport = true, prompt = "导入失败")
	private Double totalFee;

	@ExcelVOAttribute(name = "付款交易号", column = "E", isExport = true, prompt = "导入失败")
	private String transactionId;

	@ExcelVOAttribute(name = "本地交易号", column = "F", isExport = true, prompt = "导入失败")
	private String tradeNo;

	@ExcelVOAttribute(name = "时间", column = "G", isExport = true, prompt = "导入失败")
	private Date createTime;

	@ExcelVOAttribute(name = "产品编号", column = "H", isExport = true, prompt = "导入失败")
	private String productNo;

	@ExcelVOAttribute(name = "门店编号", column = "I", isExport = true, prompt = "导入失败")
	private String productMendianNo;

	private Integer tradeState;
	private Integer isSubscribe;
	private String openId;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductMendianTitle() {
		return productMendianTitle;
	}

	public void setProductMendianTitle(String productMendianTitle) {
		this.productMendianTitle = productMendianTitle;
	}

	public Integer getTradeState() {
		return tradeState;
	}

	public void setTradeState(Integer tradeState) {
		this.tradeState = tradeState;
	}

	public Integer getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductMendianNo() {
		return productMendianNo;
	}

	public void setProductMendianNo(String productMendianNo) {
		this.productMendianNo = productMendianNo;
	}

}
