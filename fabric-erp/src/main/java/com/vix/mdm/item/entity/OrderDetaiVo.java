package com.vix.mdm.item.entity;

import java.text.NumberFormat;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.mdm.item.entity.OrderDetaiVo
 *
 * @author zhanghaibing
 *
 * @date 2016年9月22日
 */

public class OrderDetaiVo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编码
	 */
	private String itemcode;
	/**
	 * 名称
	 */
	private String itemname;
	/**
	 * 时间
	 */
	private String salesDate;
	/**
	 * 消费金额
	 */
	private Double price;
	/**
	 * 购买数量
	 */
	private Double num;
	/**
	 * 采购价格
	 */
	private Double purchasePrice;

	public OrderDetaiVo() {
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

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public Double getPrice() {
		return price;
	}

	public Double getCostPrice() {
		if (purchasePrice != null && num != null) {
			return purchasePrice * num;
		}
		return 0D;
	}

	public String getMargin() {
		if (purchasePrice != null && num != null) {
			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);
			// 最后格式化并输出
			return nt.format((price - purchasePrice * num) / price);
		}
		return "";
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

}