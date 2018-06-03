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

public class SalesOrderItemDay {
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

	public SalesOrderItemDay() {
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