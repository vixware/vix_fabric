package com.vix.nvix.warehouse.vo;

import java.util.Date;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.vo.StockRecordLinesVo
 *
 * @author zhanghaibing
 *
 * @date 2016年9月7日
 */

public class SalesOrderItemVo {
	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * 物料名称
	 */
	private String itemname;
	/**
	 * 数量
	 */
	private Long quantity;
	/**
	 * 金额
	 */
	private Double price;
	/**
	 * 时间
	 */
	private Date createTime;

	public SalesOrderItemVo() {
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}