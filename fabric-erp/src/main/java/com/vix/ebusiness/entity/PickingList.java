package com.vix.ebusiness.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 拣货单 com.vix.ebusiness.entity.PickingList
 *
 * @author zhanghaibing
 *
 * @date 2014年9月24日
 */
public class PickingList extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer isPicking;
	private Date cutoffTime;
	private Integer isPrint;
	private Date dealTime;
	private Integer isArchived;
	private Date archivedTime;
	private Integer pickingType;
	private Integer isPack; // 0未打包出库1已打包出库
	// private String status;// 处理状态0为处理1已处理2处理中
	private Integer orderTotal; // 订单数
	private String distributionCenterCode; // 配送中心（LBP）
	private String pickingCondition;
	private Long packageTotal; // 包裹总数
	private Integer sellerShippingType;//
	private String orderNoList;
	/**
	 * 订单
	 */
	private Set<Order> subOrders;
	/**
	 * 订单批次
	 */
	private OrderBatch orderBatch;
	/**
	 * 拣货单明细
	 */
	private Set<PickingListDetail> subpickingListDetails;

	public Integer getIsPicking() {
		return isPicking;
	}

	public void setIsPicking(Integer isPicking) {
		this.isPicking = isPicking;
	}

	public Date getCutoffTime() {
		return cutoffTime;
	}

	public void setCutoffTime(Date cutoffTime) {
		this.cutoffTime = cutoffTime;
	}

	public Integer getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Integer isArchived) {
		this.isArchived = isArchived;
	}

	public Date getArchivedTime() {
		return archivedTime;
	}

	public void setArchivedTime(Date archivedTime) {
		this.archivedTime = archivedTime;
	}

	public Integer getPickingType() {
		return pickingType;
	}

	public void setPickingType(Integer pickingType) {
		this.pickingType = pickingType;
	}

	public Integer getIsPack() {
		return isPack;
	}

	public void setIsPack(Integer isPack) {
		this.isPack = isPack;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getDistributionCenterCode() {
		return distributionCenterCode;
	}

	public void setDistributionCenterCode(String distributionCenterCode) {
		this.distributionCenterCode = distributionCenterCode;
	}

	public String getPickingCondition() {
		return pickingCondition;
	}

	public void setPickingCondition(String pickingCondition) {
		this.pickingCondition = pickingCondition;
	}

	public Long getPackageTotal() {
		return packageTotal;
	}

	public void setPackageTotal(Long packageTotal) {
		this.packageTotal = packageTotal;
	}

	public Integer getSellerShippingType() {
		return sellerShippingType;
	}

	public void setSellerShippingType(Integer sellerShippingType) {
		this.sellerShippingType = sellerShippingType;
	}

	public Set<Order> getSubOrders() {
		return subOrders;
	}

	public void setSubOrders(Set<Order> subOrders) {
		this.subOrders = subOrders;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

	public Set<PickingListDetail> getSubpickingListDetails() {
		return subpickingListDetails;
	}

	public void setSubpickingListDetails(Set<PickingListDetail> subpickingListDetails) {
		this.subpickingListDetails = subpickingListDetails;
	}

	public String getOrderNoList() {
		return orderNoList;
	}

	public void setOrderNoList(String orderNoList) {
		this.orderNoList = orderNoList;
	}

}
