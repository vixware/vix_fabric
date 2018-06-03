package com.vix.nvix.warehouse.vo;

import java.util.Date;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.vo.SalesDataVo
 *
 * @author zhanghaibing
 *
 * @date 2016年10月30日
 */

public class SalesDataVo {
	/**
	 * 门店名称
	 */
	private String shopname;
	/**
	 * 金额
	 */
	private Double price;
	/**
	 * 时间
	 */
	private Date createTime;

	public SalesDataVo() {
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
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