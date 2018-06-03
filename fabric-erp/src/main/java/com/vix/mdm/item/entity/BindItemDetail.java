package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 捆绑商品明细
 * 
 * @类全名 com.vix.mdm.item.entity.BindItemDetail
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月5日
 */
public class BindItemDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 商品编码 */
	private String code;
	/** 商品名称 */
	private String name;
	/** 绑定单价 */
	private Double bindPrice;
	/** 绑定数量 */
	private Integer bindCount;
	/** 绑定商品 */
	private BindItem bindItem;
	/** 商品 */
	private Item item;

	public BindItemDetail() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Double getBindPrice() {
		return bindPrice;
	}

	public void setBindPrice(Double bindPrice) {
		this.bindPrice = bindPrice;
	}

	public Integer getBindCount() {
		return bindCount;
	}

	public void setBindCount(Integer bindCount) {
		this.bindCount = bindCount;
	}

	public BindItem getBindItem() {
		return bindItem;
	}

	public void setBindItem(BindItem bindItem) {
		this.bindItem = bindItem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}