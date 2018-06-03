package com.vix.ebusiness.expressFeeRules.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 省市 com.vix.ebusiness.expressFeeRules.entity.Provinces
 *
 * @author zhanghaibing
 *
 * @date 2014年12月19日
 */
public class Provinces extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 区域
	 */
	private DeliveryArea deliveryArea;
	/**
	 * 仓库
	 */
	private Set<InvWarehouse> subInvWarehouses = new HashSet<InvWarehouse>();

	public DeliveryArea getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(DeliveryArea deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public Set<InvWarehouse> getSubInvWarehouses() {
		return subInvWarehouses;
	}

	public void setSubInvWarehouses(Set<InvWarehouse> subInvWarehouses) {
		this.subInvWarehouses = subInvWarehouses;
	}

}
