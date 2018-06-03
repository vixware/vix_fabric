package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

public class WxpPayDirect extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startDate; // 有效期起始日期
	private Date endDate; // 有效期结束日期

	String mendian; // 门店
	String productNo; // 商品唯一标识
	String productType; // 商品类型
	String title; // 名称
	Double productFee; // 商品价格
	Double transportFee; // 物流费用
	String productUrl; // 商品链接
	String note; // 商品说明
	String image; // 商品图片
	String imageSmall; // 商品小图

	WxpPayDirectGroup pdGroup; // 分组

	public WxpPayDirect() {
		super();
		this.setStartDate(new Date());
		this.setEndDate(DateUtil.praseSqlDate("9999-12-31"));
	}

	public boolean getExpired() {
		if (this.endDate != null) {
			Date now = new Date();
			return now.after(this.endDate);
		}
		return false;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getProductFee() {
		return productFee;
	}

	public void setProductFee(Double productFee) {
		this.productFee = productFee;
	}

	public Double getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(Double transportFee) {
		this.transportFee = transportFee;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageSmall() {
		return imageSmall;
	}

	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public WxpPayDirectGroup getPdGroup() {
		return pdGroup;
	}

	public void setPdGroup(WxpPayDirectGroup pdGroup) {
		this.pdGroup = pdGroup;
	}

	public String getMendian() {
		return mendian;
	}

	public void setMendian(String mendian) {
		this.mendian = mendian;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

}
