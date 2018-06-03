package com.vix.nvix.common.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.common.base.entity.HomeColumnDetail
 *
 * @date 2018年1月5日
 */
public class HomeColumnDetail extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/** 栏目明细对象标识 */
	private String columnInfoId;
	/** 栏目图片 */
	/** 文件名称 */
	private String imageFileName;
	/** 真实名称 */
	private String imageFileRealName;
	/** 路径 */
	private String imageFilePath;
	/** 地址 */
	private String linkAddress;
	/** 栏目明细顺序 */
	private Long orderBy;
	// 栏目明细状态 status; '1' 展示 '0' 不展示
	/** 栏目明细所属栏目 */
	private HomeColumn homeColumn;

	public HomeColumnDetail() {
	}

	public String getColumnInfoId() {
		return columnInfoId;
	}

	public void setColumnInfoId(String columnInfoId) {
		this.columnInfoId = columnInfoId;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageFileRealName() {
		return imageFileRealName;
	}

	public void setImageFileRealName(String imageFileRealName) {
		this.imageFileRealName = imageFileRealName;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the homeColumn
	 */
	public HomeColumn getHomeColumn() {
		return homeColumn;
	}

	/**
	 * @param homeColumn
	 *            the homeColumn to set
	 */
	public void setHomeColumn(HomeColumn homeColumn) {
		this.homeColumn = homeColumn;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getWcdImagePath() {
		String path = "";
		if (null != imageFilePath && !"".equals(imageFilePath) && null != imageFileRealName && !"".equals(imageFileRealName)) {
			path = imageFilePath + System.getProperty("file.separator") + imageFileRealName;
			path = path.replace("\\", "/");
			path = path.replace("\t", "");
			path = path.replace("\n", "");
			path = path.replace(" ", "");
		}
		return path;
	}
}
