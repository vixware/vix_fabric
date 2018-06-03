package com.vix.ebusiness.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class GoodsImage extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long goodsId;
	private Integer idRemote;
	private String source;// 地址
	private Integer srcWidthSize;
	private Integer srcHeightSize;

	private String big;
	private String small;
	private String thumbnail;// 缩略图
	private Date upDateTime;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getIdRemote() {
		return idRemote;
	}

	public void setIdRemote(Integer idRemote) {
		this.idRemote = idRemote;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSrcWidthSize() {
		return srcWidthSize;
	}

	public void setSrcWidthSize(Integer srcWidthSize) {
		this.srcWidthSize = srcWidthSize;
	}

	public Integer getSrcHeightSize() {
		return srcHeightSize;
	}

	public void setSrcHeightSize(Integer srcHeightSize) {
		this.srcHeightSize = srcHeightSize;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getUpDateTime() {
		return upDateTime;
	}

	public void setUpDateTime(Date upDateTime) {
		this.upDateTime = upDateTime;
	}

}
