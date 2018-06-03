package com.vix.mdm.purchase.order.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 拣货单
 * 
 * @类全名 com.vix.mdm.purchase.order.entity.NvixOrderBatch
 *
 * @author zhanghaibing
 *
 * @date 2016年11月30日
 */
public class NvixOrderBatch extends BaseEntity {

	/**
	 * status 0,待配货;1,待发货;2,配送中;3,已完成
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 采购单
	 */
	private Set<PurchaseOrder> subPurchaseOrders;
	/**
	 * 门店要货单
	 */
	private Set<RequireGoodsOrder> subRequireGoodsOrders;
	/**
	 * 供应商
	 */
	private Supplier supplier;
	/**
	 * 拣货单明细
	 */
	private Set<NvixPickingListDetail> subNvixPickingListDetails;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<PurchaseOrder> getSubPurchaseOrders() {
		return subPurchaseOrders;
	}

	public void setSubPurchaseOrders(Set<PurchaseOrder> subPurchaseOrders) {
		this.subPurchaseOrders = subPurchaseOrders;
	}

	public Set<NvixPickingListDetail> getSubNvixPickingListDetails() {
		return subNvixPickingListDetails;
	}

	public void setSubNvixPickingListDetails(Set<NvixPickingListDetail> subNvixPickingListDetails) {
		this.subNvixPickingListDetails = subNvixPickingListDetails;
	}

	public Set<RequireGoodsOrder> getSubRequireGoodsOrders() {
		return subRequireGoodsOrders;
	}

	public void setSubRequireGoodsOrders(Set<RequireGoodsOrder> subRequireGoodsOrders) {
		this.subRequireGoodsOrders = subRequireGoodsOrders;
	}

}
