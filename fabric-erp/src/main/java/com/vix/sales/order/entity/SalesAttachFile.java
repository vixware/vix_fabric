package com.vix.sales.order.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 单据附件
 * @author Administrator
 *
 */
public class SalesAttachFile extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 文件名称 */
	private String fileName;
	/** 类型 */
	private String type;
	/** 路径 */
	private String path;
	/** 下载次数 */
	private Integer downloadNum;
	/** 销售订单 */
	private SalesOrder salesOrder;
	
	public SalesAttachFile(){}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
}
