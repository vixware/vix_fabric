package com.vix.ebusiness.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvShelf;

/**
 * 
 * com.vix.ebusiness.entity.PickingListDetail
 *
 * @author zhanghaibing
 *
 * @date 2014年9月22日
 */
public class PickingListDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String shelfCode;// 货位号
	private Long pickAmount;// 拣货数量
	private String orderAmount;// 播种数量
	private String goodsCode;// 商品条码
	private String goodsName;// 商品名称

	private String centerName; // 配送中心
	private Integer packageNum; // 包裹数量
	private String spec;
	private Integer storeAmount;
	/**
	 * 货位
	 */
	private InvShelf invShelf;
	/**
	 * 拣货单
	 */
	private PickingList pickingList;
	/**
	 * 货品
	 */
	private Products products;
	/**
	 * 订单
	 */
	private Set<Order> subOrders;

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public Long getPickAmount() {
		return pickAmount;
	}

	public void setPickAmount(Long pickAmount) {
		this.pickAmount = pickAmount;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getStoreAmount() {
		return storeAmount;
	}

	public void setStoreAmount(Integer storeAmount) {
		this.storeAmount = storeAmount;
	}

	public InvShelf getInvShelf() {
		return invShelf;
	}

	public void setInvShelf(InvShelf invShelf) {
		this.invShelf = invShelf;
	}

	public PickingList getPickingList() {
		return pickingList;
	}

	public void setPickingList(PickingList pickingList) {
		this.pickingList = pickingList;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Set<Order> getSubOrders() {
		return subOrders;
	}

	public void setSubOrders(Set<Order> subOrders) {
		this.subOrders = subOrders;
	}

}
