package com.vix.nvix.warehouse.vo;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.vo.CustomerSalesVo
 *
 * @author zhanghaibing
 *
 * @date 2016年9月20日
 */

public class CustomerSalesVo {
	/**
	 * 姓名
	 */
	private String customerName;
	/**
	 * 购买金额
	 */
	private Double price;

	public CustomerSalesVo() {
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}