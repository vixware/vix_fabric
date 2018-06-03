package com.vix.inventory.initInventory.service;

import java.util.Date;

public interface ImportItem {
	/**
	 * 
	 * @param skuCode   sku编码
	 * @param massunitEndTime  产品到期日
	 * @param warehouseId  仓库ID
	 * @param quantity  数量
	 * @param itemCode  商品编码
	 * @param itemName  商品名称
	 * @param masterUnit  主计量单位
	 * @param itemtype  商品类型
	 * @param price 价格
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrUpdate(String skuCode, Date massunitEndTime, String warehouseId, Double quantity, String itemCode, String itemName, String masterUnit, String itemtype, Double price) throws Exception;
}
